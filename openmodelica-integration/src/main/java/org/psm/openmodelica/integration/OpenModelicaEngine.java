package org.psm.openmodelica.integration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openmodelica.corba.Result;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.emory.mathcs.backport.java.util.Arrays;

public class OpenModelicaEngine implements ModelicaEngine {

	@Override
	public void configure(Configuration config) {
		this.config				= config;
		this.omc				= new OpenModelicaWrapper(OMWRAPPER_NAME);
		this.workingDir			= Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		this.libraryDir			= Paths.get(config.getParameter("libraryDir"));
		this.resultVariables	= config.getParameter("resultVariables") == null ? new String[0] : config.getParameter("resultVariables").split(",");
		
		this.method				= Optional.ofNullable(config.getParameter("method")).orElse("Dassl");
		this.startTime			= Double.valueOf(Optional.ofNullable(config.getParameter("startTime")).orElse("0.0"));
		this.stopTime			= Double.valueOf(Optional.ofNullable(config.getParameter("stopTime")).orElse("1.0"));
		this.tolerance			= Double.valueOf(Optional.ofNullable(config.getParameter("tolerance")).orElse("0.0001"));
		
		this.numOfIntervals		= Integer.valueOf(Optional.ofNullable(config.getParameter("numOfIntervals")).orElse("500"));
		this.stepSize			= Double.valueOf(Optional.ofNullable(config.getParameter("stepSize")).orElse("0.002")); 
		this.intervalLength		= Double.valueOf(Optional.ofNullable(config.getParameter("intervalLength")).orElse("0.002"));
	}

	@Override
	public void simulate(ModelicaDocument mo) {
		String modelName = mo.getSystemModel().getName();
		String modelFileName = modelName + MO_EXTENSION;
		
		prepareWorkingDirectory(mo);

		try {
			long startms = System.currentTimeMillis();

			long endms = System.currentTimeMillis();
			LOGGER.info(" {} - OpenModelica simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, outputFixedStepSize:{}.", 
								omSimulationDir, modelFileName, modelName, startTime, stopTime, numOfIntervals, intervalLength, method,tolerance, stepSize);
			startms = System.currentTimeMillis();

			try {
				Result result;

				result = omc.clear();
				if (!result.res.contains("true")) {
					throw new RuntimeException("Clear : " + result.err.replace("\"", ""));
				}

				result = omc.cd("\"" + this.omSimulationDir.toString().replace("\\", "/") + "\"");
				 if (result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error setting the working directory " + omSimulationDir + ". " + result.err.replace("\"", ""));
				 }
				
				result = omc.loadStandardLibrary();
				if(result.err != null && !result.err.isEmpty()) {					
					if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error loading the standard library. " + result.err.replace("\"", ""));
				}

				//load to the engine all .mo files
				try (DirectoryStream<Path> stream = Files.newDirectoryStream(omSimulationDir, "*.mo")) {
					for(Path p : stream) {
						result = omc.loadFile("\"" + p.getFileName().toString() + "\"");
						if(result.err != null && !result.err.isEmpty()) {					
							if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
							else throw new RuntimeException("Error loading Modelica model: " + p.toFile().getName() + ". " + result.err.replace("\"", ""));
						}						
					}
				} catch (IOException e) {
					throw new RuntimeException("Error reading directory " + omSimulationDir +  ".", e);
				}
				
				result = omc.checkModel(modelName);
				if (result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error checking model " + modelName + ". {" + result.err.replace("\"", "") + "}"); 
				}
				
				// Simulate the model
				result = omc.simulate(modelName, startTime, stopTime, numOfIntervals, method, tolerance); //FIXME pending to think about get only filtered variables
				if(result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning:")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error simulating model " + modelName + ". " + result.err.replace("\"", ""));
				}
				
				String matResultsFile = modelName + "_res" + MAT_EXTENSION;
				String csvResultsFile = modelName + "_res" + CSV_EXTENSION;
				
				if(resultVariables.length == 0) {
					result = omc.readSimulationResultVars(matResultsFile);
										
					if(result.err != null && !result.err.isEmpty()) {
						if(result.err.contains("Warning:")) LOGGER.warn(result.err.replace("\"", ""));
						else throw new RuntimeException("Error reading simulation results variables from " + matResultsFile + ". " + result.err.replace("\"", ""));
					}
					String res = result.res;
					resultVariables = res.substring(1, res.length()-2).split(COMMA);
				}
				result = omc.readSimulationResult(matResultsFile, resultVariables);
				
				if(result.err != null && !result.err.isEmpty()) {
					if(result.err.startsWith("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else LOGGER.error("Error getting simulation results from " + matResultsFile + ". ", result.err.replace("\"", ""));
				}
				
				int resultSize = Integer.parseInt(omc.readSimulationResultSize(matResultsFile).res.replace("\n", ""));

				try (PrintStream printStream = new PrintStream(Files.newOutputStream(Paths.get(omSimulationDir + File.separator + csvResultsFile)))) {
					  	writeResultsCsv(printStream, resultVariables, result.res, resultSize);
				} catch (IOException e) {
				    LOGGER.error("Error printing errors file. {}", e.getMessage());
				}			
			} finally {
				//Delete all the C files created for the simulation
				deleteSimulationFiles();
				// The connection to OpenModelica is closed and OpenModelica is terminated
				if (omc != null) {
					omc.stopServer();
				}
				omc = null;
			}

			endms = System.currentTimeMillis();
			long simulationTime = endms - startms;
			LOGGER.info(" {} - openmodelica simulation terminated - simulation time: {} ms.", workingDir, simulationTime);

		} catch (Exception e) {
			LOGGER.error(" {} - openmodelica simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, outputFixedStepSize:{}",
					workingDir, modelFileName, modelName, startTime, stopTime, numOfIntervals, intervalLength, method, tolerance, stepSize, e);

			throw new RuntimeException("openmodelica simulation failed - remote working directory " + workingDir + ", fileName: " + modelFileName + ", problem:" + modelName + ", error message:" + e.getMessage(), e);
		}
//		TODO Read simulation results from CSV file and save it in ModelicaSimulationResults
		readSimulationResults(modelName);
	}
	
	public Path simulateFake(Path modelicaPath) {
		String modelFileName = modelicaPath.getFileName().toString();
		String modelName = modelFileName.substring(0, modelFileName.indexOf("."));

		prepareWorkingDirectoryFake(modelicaPath, modelFileName, modelName);

		try {
			long startms = System.currentTimeMillis();
			long endms = System.currentTimeMillis();
			LOGGER.info(" {} - openmodelica simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, outputFixedStepSize:{}.", 
								omSimulationDir, modelFileName, modelName, startTime, stopTime, numOfIntervals, intervalLength, method,tolerance, stepSize);
			startms = System.currentTimeMillis();

			try {
				Result result;

				result = omc.clear();
				if (!result.res.contains("true")) {
					throw new RuntimeException("Clear : " + result.err.replace("\"", ""));
				}

				result = omc.cd("\"" + this.omSimulationDir.toString().replace("\\", "/") + "\"");
				 if (result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error setting the working directory " + omSimulationDir + ". " + result.err.replace("\"", ""));
				 }
				
				result = omc.loadStandardLibrary();
				if(result.err != null && !result.err.isEmpty()) {					
					if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error loading the standard library. " + result.err.replace("\"", ""));
				}

				//load to the engine all .mo files
				try (DirectoryStream<Path> stream = Files.newDirectoryStream(omSimulationDir, "*.mo")) {
					for(Path p : stream) {
						result = omc.loadFile("\"" + p.getFileName().toString() + "\"");
						if(result.err != null && !result.err.isEmpty()) {					
							if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
							else throw new RuntimeException("Error loading Modelica model: " + p.toFile().getName() + ". " + result.err.replace("\"", ""));
						}						
					}
				} catch (IOException e) {
					throw new RuntimeException("Error reading directory " + omSimulationDir +  ".", e);
				}
				
				result = omc.checkModel(modelName);
				if (result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error checking model " + modelName + ". {" + result.err.replace("\"", "") + "}"); 
				}
				
				// Simulate the model
				result = omc.simulate(modelName, startTime, stopTime, numOfIntervals, method, tolerance); //FIXME pending to think about get only filtered variables
				if(result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning:")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error simulating model " + modelName + ". " + result.err.replace("\"", ""));
				}
				
				String matResultsFile = modelName + "_res" + MAT_EXTENSION;
				String csvResultsFile = modelName + "_res" + CSV_EXTENSION;
				
				if(resultVariables.length == 0) {
					result = omc.readSimulationResultVars(matResultsFile);
					if(result.err != null && !result.err.isEmpty()) {
						if(result.err.contains("Warning:")) LOGGER.warn(result.err.replace("\"", ""));
						else throw new RuntimeException("Error reading simulation results variables from " + matResultsFile + ". " + result.err.replace("\"", ""));
					}
					String res = result.res;
					resultVariables = res.substring(1, res.length()-2).split(COMMA);
				}
				result = omc.readSimulationResult(matResultsFile, resultVariables);
				
				if(result.err != null && !result.err.isEmpty()) {
					if(result.err.startsWith("Warning")) LOGGER.warn(result.err.replace("\"", ""));
					else LOGGER.error("Error getting simulation results from " + matResultsFile + ". ", result.err.replace("\"", ""));
				}
				
				int resultSize = Integer.parseInt(omc.readSimulationResultSize(matResultsFile).res.replace("\n", ""));

				try (PrintStream printStream = new PrintStream(Files.newOutputStream(Paths.get(omSimulationDir + File.separator + csvResultsFile)))) {
					  	writeResultsCsv(printStream, resultVariables, result.res, resultSize); //TODO pending to write results in a csv file
				} catch (IOException e) {
				    LOGGER.error("Error printing errors file. {}", e.getMessage());
				}			
			} finally {
				//Delete all the C files created for the simulation
				deleteSimulationFiles();
				// The connection to OpenModelica is closed and OpenModelica is terminated
				if (omc != null) {
					omc.stopServer();
				}
				omc = null;
			}

			endms = System.currentTimeMillis();
			long simulationTime = endms - startms;
			LOGGER.info(" {} - openmodelica simulation terminated - simulation time: {} ms.", workingDir, simulationTime);

		} catch (Exception e) {
			LOGGER.error(" {} - openmodelica simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, outputFixedStepSize:{}",
					workingDir, modelFileName, modelName, startTime, stopTime, numOfIntervals, intervalLength, method, tolerance, stepSize, e);

			throw new RuntimeException("OpenModelica simulation failed - remote working directory " + workingDir + ", fileName: " + modelFileName + ", problem:" + modelName + ", error message:" + e.getMessage(), e);
		}
		//TODO Read simulation results from CSV file and save it in ModelicaSimulationResults
		readSimulationResults(modelName); 
		return this.omSimulationDir;
	}

	@Override
	public void simulate(Collection<ModelicaDocument> mos) {
		// Just as an exercise, do it in parallel
		// Be careful with using parallel (https://dzone.com/articles/think-twice-using-java-8)
		mos.parallelStream().forEach(mo -> simulate(mo));		
	}

	@Override
	public ModelicaSimulationResults getSimulationResults() {
		return this.results;
	}
	
	private void printModelicaDocument(ModelicaDocument mo, Path outputPath) {
		String moFileName = mo.getSystemModel().getName() + MO_EXTENSION;
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		try (PrintWriter out = new PrintWriter(outputPath.resolve(moFileName).toFile());)
		{
			mop.print(out);
		}
		catch (Exception e)
		{
			LOGGER.error("Error printing Modelica File. {}", e.getMessage());
		}
	}
	
	private void writeResultsCsv(PrintStream printStream, String[] resultVariables, String result, int resultSize) {
		//The string result is the result obtained from the Modelica function readSimulationResult.
		Matcher matcher =  RESULTS_PATTERN.matcher(result);
		
		String[][] resultValues = new String[resultSize][resultVariables.length];
		String strLine = Stream.of(resultVariables).collect(Collectors.joining(",")).toString() + NEW_LINE;
		int j = 0;
		while (matcher.find()) {
			String val = matcher.group(1);
			val = val.substring(1, val.length()-1);
			String[] values = val.split(COMMA);
			for(int i=0; i<resultSize;i++) {
				resultValues[i][j] = values[i];
				if(debug) System.out.println("resultValues[" + i + "," + j + "] = " + values[i]);
			}
			j++;			
		}
		
		for (int i = 0; i < resultValues.length; i++) {
			for (int k = 0; k < resultValues[i].length; k++) {
				if(k == 0) strLine = strLine + resultValues[i][k];
				else strLine = strLine + "," + resultValues[i][k]; 
			}
			strLine = strLine + NEW_LINE;
		}
		printStream.print(strLine);
	}

	
	private void readSimulationResults(String modelName) {
		//TODO Read simulation results from CSV file and save it in ModelicaSimulationResults
		this.results = new ModelicaSimulationResults();
		//The first "result" in ModelicaSimulationResults is the simulation directory with component="simulation" and var="path"
		this.results.addResult(modelName, "simulation", "path", this.omSimulationDir);
	}
	
	private void prepareWorkingDirectoryFake(Path modelicaPath, String moFileName, String modelName) {		
		try {
			this.omSimulationDir = Files.createTempDirectory(this.workingDir, OM_PREFIX);
			
			//Copy the Modelica model to the simulation directory
			try {
				FileUtils.copyFileToDirectory(modelicaPath.toFile(), this.omSimulationDir.toFile(), false);
			} catch (IOException e1) {
				LOGGER.error("Error copying file from {} to {}, reason is {}", modelicaPath, this.omSimulationDir, e1.getMessage());
				throw new RuntimeException(e1);
			}

			//Copy Models models needed to the simulation directory
			try {
				Files.list(this.libraryDir).forEach(file -> {
						try {
							FileUtils.copyFileToDirectory(file.toFile(), this.omSimulationDir.toFile(), false);
						}
						catch(IOException exc) {
							LOGGER.error("Error copying file from {} to {}, reason is {}", file.toFile(), this.omSimulationDir, exc.getMessage());
							throw new RuntimeException(exc);
						}
				});
			} catch (Exception e1) {
				LOGGER.error("Error copying files from {} to {}, reason is {}", this.libraryDir, this.omSimulationDir, e1.getMessage());
			}
		} catch (IOException e) {
			LOGGER.error("Could not create OpenModelica simulation directory in {} with prefix {}, reason is {}",
						this.workingDir, OM_PREFIX, e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	private void prepareWorkingDirectory(ModelicaDocument mo) {
		String modelName = mo.getSystemModel().getName();
		String modelFileName = modelName + MO_EXTENSION;
		Path modelicaPath = Paths.get(modelFileName);
		
		try {
			this.omSimulationDir = Files.createTempDirectory(this.workingDir, OM_PREFIX);
			
			if(Files.notExists(this.omSimulationDir.resolve(modelicaPath))) { 
				printModelicaDocument(mo, this.omSimulationDir);
			}

			//Copy Models models needed to the simulation directory
			try {
				Files.list(this.libraryDir).forEach(file -> {
						try {
							FileUtils.copyFileToDirectory(file.toFile(), this.omSimulationDir.toFile(), false);
						}
						catch(IOException exc) {
							LOGGER.error("Error copying file from {} to {}, reason is {}", file.toFile(), this.omSimulationDir, exc.getMessage());
							throw new RuntimeException(exc);
						}
				});
			} catch (Exception e1) {
				LOGGER.error("Error copying files from {} to {}, reason is {}", this.libraryDir, this.omSimulationDir, e1.getMessage());
			}
		} catch (IOException e) {
			LOGGER.error("Could not create OpenModelica simulation directory in {} with prefix {}, reason is {}",
						this.workingDir, OM_PREFIX, e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	private void deleteSimulationFiles() throws IOException {  
		String[] filter = new String[]{MO_EXTENSION, MAT_EXTENSION, CSV_EXTENSION};
		List<String> filterList = Arrays.asList(filter);
		
		DirectoryStream.Filter<Path> filesFilter =  (entry)-> {
            File f = entry.toFile();
            if(f.getName().contains(".")) return !filterList.contains(f.getName().substring(f.getName().lastIndexOf(".")));
            return true;
		};
		
		DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.omSimulationDir, filesFilter);
		for(Path p : dirStream) {
			boolean deleted = Files.deleteIfExists(p);
			if(!deleted) LOGGER.error("File {} has not been deleted.", p); 
		}
	}
	
	private Configuration	config;
	private Path			workingDir;
	private Path			omSimulationDir;
	private String			method;
	private int				numOfIntervals;
	private double			stepSize;
	private double			intervalLength;
	private double			startTime;
	private double			stopTime;
	private double			tolerance;
	private Path			libraryDir;
	private String[]		resultVariables;
	
	private ModelicaSimulationResults	results;
	private OpenModelicaWrapper			omc = null;

	private boolean						debug = false;
	
	private static final String			OMWRAPPER_NAME 			= "OpenModelica";
	private static final String			OM_PREFIX				= "omsimulation_";
	private static final String			MO_EXTENSION			= ".mo";
	private static final String			MAT_EXTENSION 			= ".mat";
	private static final String			CSV_EXTENSION 			= ".csv";
	private static final String			COMMA					= ",";
	private static final Pattern		RESULTS_PATTERN			= Pattern.compile("(\\{([0-9]+(\\.[0-9]*)?\\,?)+\\})");
	private static final String			NEW_LINE				= System.getProperty("line.separator").toString();
	
	private static final Logger			LOGGER					= LoggerFactory.getLogger(OpenModelicaEngine.class);
}
