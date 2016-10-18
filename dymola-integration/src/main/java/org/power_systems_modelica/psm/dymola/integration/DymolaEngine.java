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
		this.config = config;
		this.workingDir = Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		this.wsdlService = config.getParameter("webService");
		this.method = config.getParameter("method");
		this.numberOfIntervals = Integer.valueOf(config.getParameter("numberOfIntervals"));
		this.outputFixedStepSize = Double.valueOf(config.getParameter("outputFixedStepSize"));
		this.outputInterval = Double.valueOf(config.getParameter("outputInterval"));
		this.startTime = Double.valueOf(config.getParameter("startTime"));
		this.stopTime = Double.valueOf(config.getParameter("stopTime"));
		this.tolerance = Double.valueOf(config.getParameter("tolerance"));
		this.libraryDirectory = Paths.get(config.getParameter("libraryDirectory"));
		this.resultVariables = config.getParameter("resultVariables").split(",");	
		this.dymolaSimDir = Paths.get(this.workingDir.toString() + File.separator + DYMOLA_WORKING_DIR);
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
		
		//FIXME Run Dymola
		StandaloneDymolaClient dymolaClient = new StandaloneDymolaClient(method, numberOfIntervals, outputFixedStepSize, outputInterval, startTime, stopTime, tolerance, wsdlService, resultVariables);
		LOG.info("Running Dymola client: {}", dymolaClient.toString());
		
		String simResults = "";
		try {
			simResults = dymolaClient.runDymola(workingDir, inputZipFileName, outputZipFileName, moFileName, modelName, outputDymolaFileName);
		} catch (InterruptedException e) {
			LOG.error("Dymola execution interrupted unexpectedly: {}", e.getMessage());
		}

        try (PrintStream printStream = new PrintStream(Files.newOutputStream(workingDir.resolve(outputErrorsFileName)))) {
            printStream.print(simResults);
        } catch (IOException e) {
            LOG.error("Error printing errors file. {}", e.getMessage());
        }
        
        readSimulationResults(outputZipFileName, modelName);
	}
	
	public void simulateFake(Path modelicaPath) {
		String moFileName = modelicaPath.getFileName().toString();
		String modelName = moFileName.substring(0, moFileName.indexOf("."));
		
		prepareWorkingDirectory(modelicaPath, moFileName, modelName);
		
		StandaloneDymolaClient dymolaClient = new StandaloneDymolaClient(method, numberOfIntervals, outputFixedStepSize, outputInterval, startTime, stopTime, tolerance, wsdlService, resultVariables);
		LOG.info("Running Dymola client: {}", dymolaClient.toString());
		
		String simResults = "";
		try {
			simResults = dymolaClient.runDymola(this.dymolaSimDir, inputZipFileName, outputZipFileName, moFileName, modelName, outputDymolaFileName);
		} catch (InterruptedException e) {
			LOG.error("Dymola execution interrupted unexpectedly: {}", e.getMessage());
		}

        try (PrintStream printStream = new PrintStream(Files.newOutputStream(this.dymolaSimDir.resolve(outputErrorsFileName)))) {
            printStream.print(simResults);
        } catch (IOException e) {
            LOG.error("Error printing errors file. {}", e.getMessage());
        }
        
        readSimulationResults(outputZipFileName, modelName); 
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
		
		try (ZipFile zipFile = new ZipFile(Paths.get(dymolaSimDir + File.separator + outputZipFileName).toFile())) {
			ZipFileUtil.unzipFileIntoDirectory(zipFile, dymolaSimDir.toFile());
            
			String fileName = this.outputDymolaFileName + ".csv";
			try (BufferedReader in = Files.newBufferedReader(Paths.get(dymolaSimDir + File.separator + fileName));)
			{
				in.readLine(); //skip the header
				String line;
				while ((line = in.readLine()) != null)
				{
					String device = line.substring(0, line.indexOf(VALUES_SEPARATOR));
					String values = line.substring(line.indexOf(VALUES_SEPARATOR) + 1);
					double[] variableValues = Stream.of(values.split(VALUES_SEPARATOR)).mapToDouble(Double::parseDouble).toArray();
					this.results.addResult(modelName, device.split("\\.")[0], device.split("\\.")[1], variableValues);
				}
			}
        } catch (ZipException e) {
			LOG.error("Error unzippping file {}.", outputZipFileName);
		} catch (IOException e) {
			LOG.error("Error opening/writing file. {}", e.getMessage());
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
			LOG.error("Error printing Modelica File. {}", e.getMessage());
		}
	}
	
	private void prepareWorkingDirectory(Path modelicaPath, String moFileName, String modelName) {
		this.inputZipFileName = modelName + "_in.zip";
		this.outputZipFileName = modelName + "_out.zip";
		this.outputDymolaFileName = modelName + "_res";
		this.outputErrorsFileName = modelName + "_errors.log";

		try {
			FileUtils.copyFileToDirectory(modelicaPath.toFile(), this.workingDir.toFile(), false);
		} catch (IOException e1) {
			e1.printStackTrace();
			LOG.error("Error copying file from {} to {}.", modelicaPath, this.workingDir);
		}

		try {
			Files.list(this.libraryDirectory).forEach(file -> {
					try {
						FileUtils.copyFileToDirectory(file.toFile(), this.workingDir.toFile(), false);
					}
					catch(IOException exc) {
						LOG.error("Error copying file from {} to {}.", file.toFile(), this.workingDir.toFile());
					}
			});
		} catch (Exception e1) {
			LOG.error("Error copying file from {} to {}.", this.libraryDirectory, this.workingDir);
		}		 

		if(Files.notExists(this.dymolaSimDir)) {
			try {
				Files.createDirectory(this.dymolaSimDir);
			}
			catch(IOException e) {
				LOG.error("Error creating Dymola simulation directory {}.", this.dymolaSimDir);
			}
		}
		
		DirectoryStream.Filter<Path> filesFilter =  (entry)-> {
            File f = entry.toFile();
            return f.getName().endsWith(MO_EXTENSION);
		};
		
		ZipWriter zipper = new ZipWriter(this.inputZipFileName, this.workingDir, this.dymolaSimDir, filesFilter);
		zipper.createZip();		
	}

	
	
	private Configuration	config;
	private Path			workingDir;
	private Path			dymolaSimDir;
	private String			method;
	private int				numberOfIntervals;
	private double			outputFixedStepSize;
	private double			outputInterval;
	private double			startTime;
	private double			stopTime;
	private double			tolerance;
	private String			wsdlService;
	private String			inputZipFileName;
	private String			outputZipFileName;
	private String			outputDymolaFileName;
	private String			outputErrorsFileName;
	private Path			libraryDirectory;
	private String[]		resultVariables;
	
	private ModelicaSimulationResults	results;
	
	private static final String			DYMOLA_WORKING_DIR	= "dymola_simulation"; 			
	private static final String			MO_EXTENSION		= ".mo";
	private static final String			VALUES_SEPARATOR	= ",";
	private static final Logger			LOG					= LoggerFactory.getLogger(DymolaEngine.class);
}
