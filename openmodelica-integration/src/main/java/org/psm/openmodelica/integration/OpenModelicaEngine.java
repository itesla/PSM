package org.psm.openmodelica.integration;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openmodelica.corba.ConnectException;
import org.openmodelica.corba.Result;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenModelicaEngine implements ModelicaEngine {

	@Override
	public void configure(Configuration config) {
		this.omc				= new OpenModelicaWrapper(OMWRAPPER_NAME);
		this.workingDir			= Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		this.libraryDir			= Paths.get(config.getParameter("libraryDir"));	
		this.resultVariables	= Optional.ofNullable(config.getParameter("resultVariables")).orElse("");
		
		this.method				= Optional.ofNullable(config.getParameter("method")).orElse("Dassl");
		this.startTime			= Optional.ofNullable(config.getDouble("startTime")).orElse(0.0);
		this.stopTime			= Optional.ofNullable(config.getDouble("stopTime")).orElse(1.0);
		this.tolerance			= Optional.ofNullable(config.getDouble("tolerance")).orElse(0.0001);
		
		this.numOfIntervalsPerSecond	= Optional.ofNullable(config.getInteger("numOfIntervalsPerSecond")).orElse(500);
		this.numOfIntervals				= (int) this.stopTime * this.numOfIntervalsPerSecond;
		
		this.createFilteredMat			= Optional.ofNullable(config.getBoolean("createFilteredMat")).orElse(false);
		
		this.simFlags					= Optional.ofNullable(config.getParameter("simFlags")).orElse("");		
	}

	@Override
	public void simulate(ModelicaDocument mo) {
		modelName = mo.getSystemModel().getId();
		String modelFileName = modelName + MO_EXTENSION;
		
		prepareWorkingDirectory(mo);

		try {
			long startms = System.currentTimeMillis();

			long endms = System.currentTimeMillis();
			LOGGER.info(" {} - OpenModelica simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, method:{}, tolerance:{}.", 
								omSimulationDir, modelFileName, modelName, startTime, stopTime, numOfIntervalsPerSecond, method, tolerance);
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
				//Parameter "simFlags" can be a comma separated list of log level: -lv LOG_INIT,LOG_SIMULATION,LOG_STATS,LOG_EVENTS
				//See https://openmodelica.org/doc/OpenModelicaUsersGuide/latest/simulationflags.html
				//IMPORTANT: This can greatly increase the simulation time. 
				result = omc.simulate(modelName, startTime, stopTime, numOfIntervals, method, tolerance, this.simFlags);
				if(result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning:")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error simulating model " + modelName + ". " + result.err.replace("\"", ""));
				}
				
				String matResultsFile = modelName + "_res" + MAT_EXTENSION;
				String csvResultsFile = modelName + "_res_filtered" + CSV_EXTENSION;
				
				result = omc.readSimulationResultVars(matResultsFile);
				if(result.err != null && !result.err.isEmpty()) {
					if(result.err.contains("Warning:")) LOGGER.warn(result.err.replace("\"", ""));
					else throw new RuntimeException("Error reading simulation results variables from " + matResultsFile + ". " + result.err.replace("\"", ""));
				}
				String res = result.res;
				List<String> allResultVariables = Arrays.asList(res.substring(1, res.length()-2).split(COMMA));
				
				List<String> filterResultVariables;
				if(resultVariables != null && !resultVariables.isEmpty()) {
					String regex = "\"" + resultVariables + "\"";
					Pattern pattern = Pattern.compile(regex);
					filterResultVariables = allResultVariables.stream().filter(pattern.asPredicate()).collect(Collectors.toList());
					filterResultVariables.add(0, "\"time\"");
				}
				else {
					filterResultVariables = allResultVariables;
				}
				
				int resultSize = Integer.parseInt(omc.readSimulationResultSize(matResultsFile).res.replace("\n", ""));

				try (PrintStream printStream = new PrintStream(Files.newOutputStream(Paths.get(omSimulationDir + File.separator + csvResultsFile)))) {
					  	writeResultsToFile(printStream, matResultsFile, filterResultVariables, resultSize, createFilteredMat);
				} catch (IOException e) {
				    LOGGER.error("Error printing errors file. {}", e.getMessage());
				}				
				
//				TODO Read simulation results from CSV file and save it in ModelicaSimulationResults
				readSimulationResults(modelName);
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
			LOGGER.error(" {} - openmodelica simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, method:{}, tolerance:{}: {}",
					workingDir, modelFileName, modelName, startTime, stopTime, numOfIntervalsPerSecond, method, tolerance, e);

			throw new RuntimeException("openmodelica simulation failed - remote working directory " + workingDir + ", fileName: " + modelFileName + ", problem:" + modelName + ", error message:" + e.getMessage(), e);
		}
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
		String moFileName = modelName + MO_EXTENSION;
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
	
	private void writeResultsToFile(PrintStream printStream, String matResultsFile, List<String> filterResultVariables, int resultSize, boolean createFilteredMat) throws ConnectException {
		String[][] resultValues = new String[resultSize][filterResultVariables.size()];
		String strLine = filterResultVariables.stream().collect(Collectors.joining(",")).toString() + NEW_LINE;
		
		for(int j=0; j<filterResultVariables.size(); j++) 
		{
			String resVar = filterResultVariables.get(j);
			Result result = omc.readSimulationResult(matResultsFile, resVar);
			
			if(result.err != null && !result.err.isEmpty()) {
				if(result.err.startsWith("Warning")) LOGGER.warn(result.err.replace("\"", ""));
				else LOGGER.error("Error getting simulation result variable {} from  {}. {}", resVar, matResultsFile, result.err.replace("\"", ""));
				continue;
			}
			String[] values = result.res.substring(2, result.res.length()-3).split(COMMA);
			
			for(int i=0; i<resultSize; i++) {
				resultValues[i][j] = values[i];
			}
		}
		
		for (int i = 0; i < resultValues.length; i++) {
			for (int k = 0; k < resultValues[i].length; k++) {
				if(k == 0) strLine = strLine + resultValues[i][k];
				else strLine = strLine + "," + resultValues[i][k]; 
			}
			strLine = strLine + NEW_LINE;
		}			
		printStream.print(strLine);
		
		if(createFilteredMat) {
			String matResultsFileFiltered = modelName + "_res_filtered" + MAT_EXTENSION;
			Result result = omc.filterSimulationResults(matResultsFile, matResultsFileFiltered, filterResultVariables, resultSize);
			LOGGER.warn(result.err.replace("\"", ""));
		}
	}

	
	private void readSimulationResults(String modelName) {
		//TODO Read simulation results from CSV file and save it in ModelicaSimulationResults
		this.results = new ModelicaSimulationResults();
		//The first "result" in ModelicaSimulationResults is the simulation directory with component="simulation" and var="path"
		this.results.addResult(modelName, "simulation", "path", this.omSimulationDir);
	}
	
	private void prepareWorkingDirectory(ModelicaDocument mo) {
		modelName = mo.getSystemModel().getId();
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
		String[] filter = new String[]{MO_EXTENSION, MAT_EXTENSION, CSV_EXTENSION, LOG_EXTENSION};
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
	

	private Path			workingDir;
	private Path			omSimulationDir;
	private String			method;
	private int				numOfIntervalsPerSecond;
	private int				numOfIntervals;
	private double			startTime;
	private double			stopTime;
	private double			tolerance;
	private Path			libraryDir;
	private String			resultVariables;
	private String			simFlags;
	private boolean			createFilteredMat;
	private String			modelName;
	
	private ModelicaSimulationResults	results;
	private OpenModelicaWrapper			omc = null;
	
	private static final String			OMWRAPPER_NAME 			= "OpenModelica";
	private static final String			OM_PREFIX				= "omsimulation_";
	private static final String			MO_EXTENSION			= ".mo";
	private static final String			MAT_EXTENSION 			= ".mat";
	private static final String			CSV_EXTENSION 			= ".csv";
	private static final String			LOG_EXTENSION			= ".log";
	private static final String			COMMA					= ",";
	private static final String			NEW_LINE				= System.getProperty("line.separator").toString();
	
	private static final Logger			LOGGER					= LoggerFactory.getLogger(OpenModelicaEngine.class);
}
