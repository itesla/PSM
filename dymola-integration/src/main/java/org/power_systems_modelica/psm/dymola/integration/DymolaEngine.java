package org.power_systems_modelica.psm.dymola.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipFileUtil;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipWriter;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DymolaEngine implements ModelicaEngine {

	@Override
	public void configure(Configuration config) {
		this.config				= config;
		this.wsdlService		= config.getParameter("webService");
		this.workingDir			= Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		this.libraryDir			= Paths.get(config.getParameter("libraryDir"));
		this.dymSimulationDir	= Optional.ofNullable(Paths.get(this.workingDir.toString() + File.separator + DYM_PREFIX)).orElse(Paths.get("")); //TODO change by default dir
		this.resultVariables	= Optional.ofNullable(config.getParameter("resultVariables")).orElse(""); 
				
		this.method				= Optional.ofNullable(config.getParameter("method")).orElse("Dassl");
		this.startTime			= Double.valueOf(Optional.ofNullable(config.getParameter("startTime")).orElse("0.0"));
		this.stopTime			= Double.valueOf(Optional.ofNullable(config.getParameter("stopTime")).orElse("1.0"));
		this.tolerance			= Double.valueOf(Optional.ofNullable(config.getParameter("tolerance")).orElse("0.0001"));
		
		this.numOfIntervals		= Integer.valueOf(Optional.ofNullable(config.getParameter("numOfIntervals")).orElse("500"));
		this.stepSize			= Double.valueOf(Optional.ofNullable(config.getParameter("stepSize")).orElse("0.002"));
		this.intervalSize		= Double.valueOf(Optional.ofNullable(config.getParameter("intervalLength")).orElse("0.002"));
	}

	@Override
	public void simulate(ModelicaDocument mo) {
		String modelName = mo.getSystemModel().getName();
		String moFileName = modelName + ".mo";
		Path modelDirectory = Paths.get(moFileName);
		
		if(Files.notExists(this.workingDir.resolve(modelDirectory))) { 
			printModelicaDocument(mo, this.workingDir);
		}
		
		prepareWorkingDirectory(this.workingDir.resolve(modelDirectory), moFileName, modelName);
		
		StandaloneDymolaClient dymolaClient = new StandaloneDymolaClient(method, numOfIntervals, stepSize, intervalSize, startTime, stopTime, tolerance, wsdlService, resultVariables);
		LOGGER.info("Running Dymola client: {}", dymolaClient.toString());
		
		String simResults = "";
		try {
			simResults = dymolaClient.runDymola(this.dymSimulationDir, inputZipFileName, outputZipFileName, moFileName, modelName, outputDymolaFileName);
		} catch (InterruptedException e) {
			LOGGER.error("Dymola execution interrupted unexpectedly: {}", e.getMessage());
		}

        try (PrintStream printStream = new PrintStream(Files.newOutputStream(workingDir.resolve(outputErrorsFileName)))) {
            printStream.print(simResults);
        } catch (IOException e) {
            LOGGER.error("Error printing errors file. {}", e.getMessage());
        }
        //TODO pending to read simulation results from csv and put them into ModelicaResults
        readSimulationResults(outputZipFileName, modelName);
	}
	
	public Path simulateFake(Path modelicaPath) {
		String moFileName = modelicaPath.getFileName().toString();
		String modelName = moFileName.substring(0, moFileName.indexOf("."));
		
		prepareWorkingDirectory(modelicaPath, moFileName, modelName);
		
		StandaloneDymolaClient dymolaClient = new StandaloneDymolaClient(method, numOfIntervals, stepSize, intervalSize, startTime, stopTime, tolerance, wsdlService, resultVariables);
		LOGGER.info("Running Dymola client: {}", dymolaClient.toString());
		
		String simResults = "";
		try {
			simResults = dymolaClient.runDymola(this.dymSimulationDir, inputZipFileName, outputZipFileName, moFileName, modelName, outputDymolaFileName);
		} catch (InterruptedException e) {
			LOGGER.error("Dymola execution interrupted unexpectedly: {}", e.getMessage());
		}

        try (PrintStream printStream = new PrintStream(Files.newOutputStream(this.dymSimulationDir.resolve(outputErrorsFileName)))) {
            printStream.print(simResults);
        } catch (IOException e) {
            LOGGER.error("Error printing errors file. {}", e.getMessage());
        }
        
        readSimulationResults(outputZipFileName, modelName);
        return this.dymSimulationDir;
	}

	@Override
	public void simulate(Collection<ModelicaDocument> mos) {
		// Just as an exercise, do it in parallel
		// Be careful with using parallel (https://dzone.com/articles/think-twice-using-java-8)
		mos.parallelStream().forEach(mo -> simulate(mo));
	}

	@Override
	public ModelicaSimulationResults getSimulationResults() {
		return results;
	}
	
	private void readSimulationResults(String outputZipFileName, String modelName) {
		this.results = new ModelicaSimulationResults();
		//The first "result" in ModelicaSimulationResults is the simulation directory with component="simulation" and var="path"
		this.results.addResult(modelName, "simulation", "path", this.dymSimulationDir);
		
		try (ZipFile zipFile = new ZipFile(Paths.get(dymSimulationDir + File.separator + outputZipFileName).toFile())) {
			ZipFileUtil.unzipFileIntoDirectory(zipFile, dymSimulationDir.toFile());
            
			String fileName = this.outputDymolaFileName + ".csv";
			try (BufferedReader in = Files.newBufferedReader(Paths.get(dymSimulationDir + File.separator + fileName));)
			{
				in.readLine(); //skip the header
				String line;
				while ((line = in.readLine()) != null)
				{
					String device = line.substring(0, line.indexOf(COMMA));
					String values = line.substring(line.indexOf(COMMA) + 1);
					double[] variableValues = Stream.of(values.split(COMMA)).mapToDouble(Double::parseDouble).toArray();
					this.results.addResult(modelName, device.split("\\.")[0], device.split("\\.")[1], variableValues);
				}
			}
        } catch (ZipException e) {
			LOGGER.error("Error unzippping file {}.", outputZipFileName);
		} catch (IOException e) {
			LOGGER.error("Error opening/writing file. {}", e.getMessage());
		}
	}
	
	private void printModelicaDocument(ModelicaDocument mo, Path outputPath) {
		String moFileName = mo.getSystemModel().getName() + ".mo";
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		try (PrintWriter out = new PrintWriter(outputPath.resolve(moFileName).toFile());)
		{
			mop.print(out);
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			LOGGER.error("Error printing Modelica File. {}", e.getMessage());
		}
	}
	
	private void prepareWorkingDirectory(Path modelicaPath, String moFileName, String modelName) {
		this.inputZipFileName = modelName + "_in.zip";
		this.outputZipFileName = modelName + "_out.zip";
		this.outputDymolaFileName = modelName + "_res";
		this.outputErrorsFileName = modelName + "_errors.log";

		try {
			this.dymSimulationDir = Files.createTempDirectory(this.workingDir, DYM_PREFIX);
			
			//Copy the Modelica model to the simulation directory
			try {
				FileUtils.copyFileToDirectory(modelicaPath.toFile(), this.dymSimulationDir.toFile(), false);
			} catch (IOException e1) {
				LOGGER.error("Error copying file from {} to {}, reason is {}", modelicaPath, this.dymSimulationDir, e1.getMessage());
				throw new RuntimeException(e1);
			}
			
			//Copy Models models needed to the simulation directory
			try {
				Files.list(this.libraryDir).forEach(file -> {
						try {
							FileUtils.copyFileToDirectory(file.toFile(), this.dymSimulationDir.toFile(), false);
						}
						catch(IOException exc) {
							LOGGER.error("Error copying file from {} to {}.", file.toFile(), this.dymSimulationDir.toFile());
						}
				});
			} catch (Exception e1) {
				LOGGER.error("Error copying files from {} to {}.", this.libraryDir, this.dymSimulationDir);
			}			
		} catch (IOException e) {
			LOGGER.error("Could not create Dymola simulation directory in {} with prefix {}, reason is {}",
					this.workingDir, DYM_PREFIX, e.getMessage());
			throw new RuntimeException(e);
		}

		DirectoryStream.Filter<Path> filesFilter =  (entry)-> {
            File f = entry.toFile();
            return f.getName().endsWith(MO_EXTENSION);
		};
		
//		ZipWriter zipper = new ZipWriter(this.inputZipFileName, this.dymSimulationDir, this.workingDir, filesFilter);
		ZipWriter zipper = new ZipWriter(this.inputZipFileName, this.dymSimulationDir, this.dymSimulationDir, filesFilter);
		zipper.createZip();		
	}

	
	
	private Configuration	config;
	private Path			workingDir;
	private Path			dymSimulationDir;
	private String			method;
	private int				numOfIntervals;
	private double			stepSize;
	private double			intervalSize;
	private double			startTime;
	private double			stopTime;
	private double			tolerance;
	private String			wsdlService;
	private String			inputZipFileName;
	private String			outputZipFileName;
	private String			outputDymolaFileName;
	private String			outputErrorsFileName;
	private Path			libraryDir;
	private String			resultVariables;
	
	private ModelicaSimulationResults	results;
	
	
	private static final String			DYM_PREFIX			= "dymsimulation_"; 			
	private static final String			MO_EXTENSION		= ".mo";
	private static final String			COMMA				= ",";
	private static final Logger			LOGGER				= LoggerFactory.getLogger(DymolaEngine.class);
}
