package org.psm.openmodelica.integration;

/*
 * #%L
 * Dynamic simulation using OpenModelica
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openmodelica.corba.ConnectException;
import org.openmodelica.corba.Result;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineProgress;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.engine.utils.ModelicaEngineUtils;
import org.power_systems_modelica.psm.modelica.engine.utils.StaticData;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class OpenModelicaEngine implements ModelicaEngine
{
	@Override
	public void configure(Configuration config)
	{
		System.setProperty("com.sun.CORBA.transport.ORBTCPReadTimeouts", "1:15000:30000:1");

		this.workingDir = Paths
				.get(Optional.ofNullable(config.getParameter("modelicaEngineWorkingDir"))
						.orElse(this.properties.getProperty("modelicaEngineWorkingDir")));

		this.libraryDirs = new ArrayList<>();
		String paths = "";
		if (config.getParameter("libraryDir") != null)
		{
			paths = config.getParameter("libraryDir");
		}
		else if (this.properties.getProperty("libraryDir") == null
				|| this.properties.getProperty("libraryDir").isEmpty())
		{
			paths = PathUtils.LIBRARY.toString();
		}
		else
		{
			paths = this.properties.getProperty("libraryDir");
		}
		StringTokenizer st = new StringTokenizer(paths, ",");
		while (st.hasMoreTokens())
		{
			this.libraryDirs.add(Paths.get(st.nextToken()));
		}

		this.resultVariables = Optional.ofNullable(config.getParameter("resultVariables"))
				.orElse(this.properties.getProperty("resultVariables"));

		this.startTime = Double.valueOf(Optional.ofNullable(config.getParameter("startTime"))
				.orElse(this.properties.getProperty("startTime")));
		this.stopTime = Double.valueOf(Optional.ofNullable(config.getParameter("stopTime"))
				.orElse(this.properties.getProperty("stopTime")));
		this.tolerance = Double.valueOf(Optional.ofNullable(config.getParameter("tolerance"))
				.orElse(this.properties.getProperty("tolerance")));

		this.numOfIntervalsPerSecond = Integer.valueOf(Optional
				.ofNullable(config.getParameter("numOfIntervalsPerSecond"))
				.orElse(this.properties.getProperty("numOfIntervalsPerSecond")));
		this.numOfIntervals = (int) (this.stopTime * this.numOfIntervalsPerSecond);

		this.createFilteredMat = Boolean
				.valueOf(Optional.ofNullable(config.getParameter("createFilteredMat"))
						.orElse(this.properties.getProperty("createFilteredMat")));
		this.simFlags = Optional.ofNullable(config.getParameter("simFlags"))
				.orElse(this.properties.getProperty("simFlags"));
		this.depth = Integer.valueOf(Optional.ofNullable(config.getParameter("depth"))
				.orElse(this.properties.getProperty("depth")));

		this.methodList = new ArrayList<>();
		String methods = Optional.ofNullable(config.getParameter("solver"))
				.orElse(this.properties.getProperty("solver"));
		st = new StringTokenizer(methods, ",");
		while (st.hasMoreTokens())
		{
			this.methodList.add(st.nextToken());
		}
	}

	@Override
	public void simulate(ModelicaDocument mo) throws Exception
	{
		prepareDynamicEnvironment();

		progress(String.format("Model %s:", mo.getSystemModel().getId()));
		simulateModelicaDocument(mo);
	}

	@Override
	public void simulate(Collection<ModelicaDocument> mos) throws Exception
	{
		// FIXME Just as an exercise, do it in parallel
		// Temporal files are overwritten if run in parallel (equations not
		// written properly)

		prepareDynamicEnvironment();

		for (ModelicaDocument mo : mos)
		{
			progress(String.format("Model %s:", mo.getSystemModel().getId()));
			simulateModelicaDocument(mo);
		}
	}

	private void simulateModelicaDocument(ModelicaDocument mo) throws Exception
	{
		modelName = mo.getSystemModel().getId();
		String modelFileName = modelName + StaticData.MO_EXTENSION;
		Path modelicaPath = Paths.get(modelFileName);

		// The first "result" in ModelicaSimulationResults is the simulation directory "simulation_path"
		this.results.addResult(modelName, "simulation_path", this.omSimulationDir);
		this.results.addResult(modelName, "successful", true);

		if (Files.notExists(this.omSimulationDir.resolve(modelicaPath)))
		{
			printModelicaDocument(mo, this.omSimulationDir);
		}

		Result result = this.omc.loadFile(modelFileName);

		if (isSuccessful(result))
			progress(String.format("\tLoading model %s.", modelFileName));
		else results.addResult(modelName, "successful", false);

		LOG.info(
				" {} - OpenModelica simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, tolerance:{}.",
				omSimulationDir, modelFileName, modelName, startTime, stopTime,
				numOfIntervalsPerSecond, tolerance);

		boolean simulated = false, validated = false, verified = false;

		validated = validateModel(modelName, depth);
		if (!validated)
		{
			this.results.addResult(modelName, "successful", false);
			return;
		}
		if (depth == 1) return;

		if (depth != 0)
		{
			verified = simulateModel(modelName, startTime, 0.1, 10,
					tolerance, simFlags, true);
			if (!verified)
			{
				this.results.addResult(modelName, "successful", false);
				return;
			}
			if (depth == 2) return;
		}

		// Simulate the model
		// Parameter "simFlags" can be a comma separated list of log level:
		// -lv LOG_INIT,LOG_SIMULATION,LOG_STATS,LOG_EVENTS
		// See
		// https://openmodelica.org/doc/OpenModelicaUsersGuide/latest/simulationflags.html
		// IMPORTANT: These simFlags can greatly increase the simulation
		// time.
		simulated = simulateModel(modelName, startTime, stopTime, numOfIntervals, tolerance,
				simFlags, false);
		if (!simulated)
		{
			this.results.addResult(modelName, "successful", false);
			return;
		}
	}

	private boolean validateModel(String modelName, int depth) throws ConnectException
	{
		progress(String.format("\tChecking model %s.", modelName));
		Result result = depth == 1 ? omc.checkAllModels(modelName, false)
				: omc.checkModel(modelName);
		if (!isSuccessful(result))
		{
			LOG.error(
					"Error checking model " + modelName + ". {"
							+ result.err.replace("\"", "") + "}");
			return false;
		}
		progress(String.format("\tModel %s checked successfully.", modelName));
		return true;
	}

	private void prepareDynamicEnvironment() throws Exception
	{
		try
		{
			this.omSimulationDir = Files.createTempDirectory(this.workingDir,
					"om" + StaticData.SIMULATION_PREFIX);
			// Copy Modelica models needed to the simulation directory
			LOG.info("Copying files to the working directory");
			for (Path libraryDir : this.libraryDirs)
			{
				copyLibrary(libraryDir);
			}
		}
		catch (IOException e)
		{
			LOG.error(
					"Could not create OpenModelica simulation directory in {} with prefix {}, reason is {}",
					this.workingDir, StaticData.SIMULATION_PREFIX, e.getMessage());
			throw new RuntimeException(e);
		}

		progress("Cleaning OpenModelica workspace.");
		Result result = omc.clear();
		if (!isSuccessful(result) || !result.res.contains("true"))
		{
			LOG.error("Error clearing workspace: {}.", result.err.replace("\"", ""));
			throw new RuntimeException(
					"Error clearing workspace : " + result.err.replace("\"", ""));
		}

		progress(String.format("Moving to working directory %s", this.omSimulationDir));
		Path wdabs = omSimulationDir.toAbsolutePath();
		result = omc.cd("\"" + wdabs.toString().replace("\\", "/") + "\"");
		if (!isSuccessful(result))
		{
			throw new RuntimeException(
					"Error setting the working directory " + omSimulationDir + ". "
							+ result.err.replace("\"", ""));
		}

		progress(String.format("Loading Modelica standard library."));
		result = omc.loadStandardLibrary();
		if (!isSuccessful(result))
		{
			throw new RuntimeException(
					"Error loading the standard library. " + result.err.replace("\"", ""));
		}

		// load to the engine all .mo files
		progress(String.format("Loading user-defined libraries:"));
		try
		{
			for (Path libraryDir : this.libraryDirs)
			{
				loadLibrary(libraryDir);
			}
		}
		catch (Exception e1)
		{
			throw new RuntimeException("Error reading directory " + omSimulationDir, e1);
		}
	}
	
	private void copyLibrary(Path libraryDir)
	{
		try
		{
			Files.list(libraryDir).forEach(file -> {
				try
				{
					if (Files.isDirectory(file))
					{
						FileUtils.copyDirectoryToDirectory(file.toFile(),
								this.omSimulationDir.toFile());
					}
					else
					{
						FileUtils.copyFileToDirectory(file.toFile(),
								this.omSimulationDir.toFile(),
								false);
					}
				}
				catch (IOException exc)
				{
					LOG.error("Error copying file from {} to {}, reason is {}",
							file.toFile(),
							this.omSimulationDir, exc.getMessage());
					throw new RuntimeException(exc);
				}
			});
		}
		catch (Exception e1)
		{
			LOG.error("Error copying files from {} to {}, reason is {}", libraryDir,
					this.omSimulationDir,
					e1.getMessage());
		}
		
	}
	
	private void loadLibrary(Path libraryDir) throws IOException
	{
		Files.list(libraryDir).forEach(file -> {
			try
			{
				Result res;
				if (Files.isDirectory(file))
				{
					try (DirectoryStream<Path> mofiles = Files.newDirectoryStream(file, "*.mo"))
					{
						for (Path mofile : mofiles)
						{
							Path mofile0 = mofile.getFileName();
							System.out.println(
									"Loading file " + file.getFileName() + "/" + mofile0);
							res = omc.loadFile(file.getFileName() + "/" + mofile0);
							if (isSuccessful(res))
								progress(String.format("\tLoading %s", mofile0));
							else results.addResult(modelName, "successful", false);
						}
					}
					catch (IOException e)
					{
						throw new RuntimeException("Error reading directory " + omSimulationDir,
								e);
					}
				}
				else
				{
					Path mofile0 = file.getFileName();
					res = omc.loadFile(mofile0.toString());
					if (isSuccessful(res))
						progress(String.format("\tLoading %s", mofile0));
					else results.addResult(modelName, "successful", false);
				}
			}
			catch (ConnectException exc)
			{
				throw new RuntimeException(
						"Error loading user-defined libraries from directory "
								+ omSimulationDir,
						exc);
			}
		});
	}

	private boolean simulateModel(String modelName, double startTime, double stopTime,
			int numOfIntervals, double tolerance, String simFlags, boolean verifying)
			throws ConnectException
	{
		String text;
		Instant startms, endms;
		startms = Instant.now();
		int i = 0;
		boolean successful = false;
		String method = null;
		while ((successful == false) && (i < methodList.size()))
		{
			method = methodList.get(i);
			if (verifying)
				text = "\tVerifying model %s with integration method %s - start time = %fs - stop time = %fs - number of intervals = %d - tolerance = %f - simulation flags = %s.";
			else text = "\tSimulating model %s with integration method %s - start time = %fs - stop time = %fs - number of intervals = %d - tolerance = %f - simulation flags = %s.";
			progress(String.format(text, modelName, method, startTime, stopTime,
					numOfIntervals, tolerance, simFlags));

			Result result = omc.simulate(modelName, startTime, stopTime, numOfIntervals,
					method, tolerance, simFlags);
			if (result != null)
			{
				SimulationResult simResult = new SimulationResult(result.res, result.err);
				if (simResult.getResultFile().isEmpty())
				{
					if (verifying)
						text = "Error verifying model {} with integration method {}. \n Reason : \n {} \n {}";
					else text = "Error simulating model {} with integration method {}. \n Reason : \n {} \n {}";
					LOG.error(text, modelName, method, simResult.getMessages(),
							simResult.getError());
				}
				else if (simResult.getError().contains("Warning:"))
				{
					if (verifying)
						text = "Warning verifying model {} with integration method {}. \n Reason : \n {}";
					else text = "Warning simulating model {} with integration method {}. \n Reason : \n {}";
					LOG.warn(text, modelName, method, simResult.getMessages());
					successful = true;
				}
				else
				{
					successful = true;
				}
			}
			else
			{
				if (verifying) text = "Error verifying model {} with integration method {}.";
				else text = "Error simulating model {} with integration method {}.";
				LOG.error(text, modelName, method);
			}
			i++;
			
			// For now only one of the methods indicated is used
			i = methodList.size();
		}

		if (!successful)
		{
			if (verifying)
				text = "\tModel %s verified unsuccessfully with all integration methods : %s.";
			else text = "\tModel %s simulated unsuccessfully with all integration methods : %s.";
			progress(String.format(text, modelName, String.join(", ", methodList)));
			return false;
		}
		if (verifying) text = "\tModel %s verified successfully with method %s.";
		else text = "\tModel %s simulated successfully with method %s.";
		progress(String.format(text, modelName, method));

		endms = Instant.now();
		long simulationTime = Duration.between(startms, endms).toMillis();
		LOG.info(" {} - openmodelica simulation terminated - simulation time: {} ms.",
				workingDir,
				simulationTime);

		// Simulation result will be read so save log is disabled.
		logs.setSave(false);

		String matResultsFile = modelName + "_res" + StaticData.MAT_EXTENSION;
		String csvResultsFile = modelName + "_res" + StaticData.CSV_EXTENSION;

		Result result = omc.readSimulationResultVars(matResultsFile);
		if (!isSuccessful(result))
		{
			if (verifying)
				text = "Error reading verification results variables from {}. Reason is {} ";
			else text = "Error reading simulation results variables from {}. Reason is {} ";
			LOG.error(text, matResultsFile, result.err.replace("\"", ""));
		}

		String res = result.res;
		List<String> allResultVariables = Arrays
				.asList(res.substring(1, res.length() - 2).split(StaticData.COMMA));

		List<String> filterResultVariables = new ArrayList<String>();
		if (resultVariables != null && !resultVariables.isEmpty())
		{
			String regex = "\"" + resultVariables + "\"";
			Pattern pattern = Pattern.compile(regex);
			filterResultVariables = allResultVariables.stream().filter(pattern.asPredicate())
					.collect(Collectors.toList());
			filterResultVariables.add(0, "\"time\"");
		}
		else
		{
			filterResultVariables = allResultVariables;
		}

		int resultSize = Integer
				.parseInt(omc.readSimulationResultSize(matResultsFile).res.replace("\n", ""));

		try (PrintStream printStream = new PrintStream(
				Files.newOutputStream(
						Paths.get(omSimulationDir + File.separator + csvResultsFile))))
		{
			LOG.info("Filter variables length = " + filterResultVariables.size());
			writeResults(printStream, matResultsFile, filterResultVariables, resultSize);
			ModelicaEngineUtils.fillModelicaSimulationResults(
					Paths.get(omSimulationDir + File.separator + csvResultsFile), modelName,
					this.results);
		}
		catch (IOException e)
		{
			LOG.error("Error printing csv file. {}", e.getMessage());
		}
		// Simulation result will be read so save log is disabled.
		logs.setSave(true);
		deleteSimulationFiles();

		logs.dump();

		return true;
	}

	@Override
	public void close() throws Exception
	{
		LOG.info("Closing OpenModelica service.");
		try
		{
			if (this.omc != null)
			{
				// The connection to OpenModelica is closed and OpenModelica is
				// terminated
				omc.stopServer();
				omc = null;
			}
		}
		catch (Exception e)
		{
			LOG.error("OpenModelica server has not been closed successfuly.");
		}
	}

	@Override
	public ModelicaSimulationFinalResults getSimulationResults()
	{
		return this.results;
	}

	private void printModelicaDocument(ModelicaDocument mo, Path outputPath)
	{
		Path out = outputPath.resolve(modelName + StaticData.MO_EXTENSION);
		boolean printPsmAnnotations = true;
		try
		{
			ModelicaTextPrinter.print(mo, out, printPsmAnnotations);
		}
		catch (IOException x)
		{
			LOG.error("Error printing Modelica File. {}", x);
		}
	}

	private void writeResults(PrintStream printStream, String matResultsFile,
			List<String> filterResultVariables, int resultSize)
			throws ConnectException
	{

		String[][] resultValues = new String[resultSize][filterResultVariables.size()];

		for (int j = 0; j < filterResultVariables.size(); j++)
		{
			String resVar = filterResultVariables.get(j);
			Result result = omc.readSimulationResult(matResultsFile, resVar);

			if (result.err != null && !result.err.isEmpty())
			{
				if (result.err.startsWith("Warning"))
					LOG.warn(result.err.replace("\"", ""));
				else
					LOG.error("Error getting simulation result variable {} from  {}. {}", resVar,
							matResultsFile,
							result.err.replace("\"", ""));
				continue;
			}

			String[] values = result.res.substring(2, result.res.length() - 3)
					.split(StaticData.COMMA);
			for (int i = 0; i < resultSize; i++)
			{
				resultValues[i][j] = values[i];
			}
		}

		String strLine = filterResultVariables.stream().map(e -> e.replace("\"", ""))
				.collect(Collectors.joining(",")).toString();
		printStream.println(strLine);
		for (int i = 0; i < resultValues.length; i++)
		{
			printStream.println(String.join(",", resultValues[i]));
		}

		if (createFilteredMat)
		{
			String matResultsFileFiltered = modelName + "_res_filtered" + StaticData.MAT_EXTENSION;
			Result result = omc.filterSimulationResults(matResultsFile, matResultsFileFiltered,
					filterResultVariables,
					resultSize);
			if (result.err.startsWith("Error"))
				LOG.error("Error creating filtered .mat file {}. Reason is {}",
						matResultsFileFiltered,
						result.err.replace("\"", ""));
			else
				LOG.warn(result.err.replace("\"", ""));
		}
	}

	private void deleteSimulationFiles()
	{
		List<String> filterList = Arrays.asList(StaticData.MO_EXTENSION, StaticData.MAT_EXTENSION,
				StaticData.CSV_EXTENSION,
				StaticData.LOG_EXTENSION);

		DirectoryStream.Filter<Path> filesFilter = (entry) -> {
			File f = entry.toFile();
			if (f.getName().contains("."))
				return !filterList.contains(f.getName().substring(f.getName().lastIndexOf(".")));
			return true;
		};

		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(this.omSimulationDir,
				filesFilter))
		{
			for (Path p : dirStream)
			{
				if (!p.toFile().isDirectory())
				{
					org.power_systems_modelica.psm.commons.FileUtils.deleteDirectory(p);
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("Error deleting Modelica simulator engine files.");
			e.printStackTrace();
		}
	}

	private static boolean isSuccessful(Result result)
	{
		if (result == null) return false;
		if (result.res == null || result.res.isEmpty()) return false;
		if (result.res.equals("false")) return false;
		if (result.res.startsWith("Error")) return false;
		if (result.res.startsWith("\"Error")) return false;
		if (result.err != null)
		{
			if (result.err.contains("Error"))
			{
				LOG.error("OpenModelica error {}", result.err.replace("\"", ""));
				return false;
			}
			if (!result.err.isEmpty())
				LOG.warn("OpenModelica warn {}", result.err.replace("\"", ""));
		}
		return true;
	}

	@Override
	public void progress(String message)
	{
		LOG.info(message);
		engineProgress.updateProgress(message);
	}

	@Override
	public ModelicaEngineProgress getModelicaEngineProgress()
	{
		return engineProgress;
	}

	@Override
	public Properties loadDefaultProperties()
	{
		Properties properties = new Properties();
		try (InputStream inputStream = Files.newInputStream(PathUtils.CONFIG
				.resolve("modelicaengine.properties")))
		{
			properties.load(inputStream);

			this.workingDir = Paths.get(properties.getProperty("modelicaEngineWorkingDir"));
			this.libraryDirs = new ArrayList<>();
			String paths = properties.getProperty("libraryDir");
			StringTokenizer st = new StringTokenizer(paths, ",");
			while (st.hasMoreTokens())
			{
				this.libraryDirs.add(Paths.get(st.nextToken()));
			}

			this.resultVariables = Optional.ofNullable(properties.getProperty("resultVariables"))
					.orElse("");

			this.startTime = Double.valueOf(Optional
					.ofNullable(properties.getProperty("startTime")).orElse("0.0"));
			this.stopTime = Double
					.valueOf(Optional.ofNullable(properties.getProperty("stopTime"))
							.orElse("1.0"));
			this.tolerance = Double.valueOf(Optional
					.ofNullable(properties.getProperty("tolerance"))
					.orElse("0.0001"));

			this.numOfIntervalsPerSecond = Integer.valueOf(Optional
					.ofNullable(properties.getProperty("numOfIntervalsPerSecond"))
					.orElse("100"));
			this.numOfIntervals = (int) (this.stopTime * this.numOfIntervalsPerSecond);

			this.createFilteredMat = Boolean.valueOf(Optional
					.ofNullable(properties.getProperty("createFilteredMat"))
					.orElse("false"));
			this.depth = Integer.valueOf(Optional.ofNullable(properties.getProperty("depth"))
					.orElse("0"));

			this.methodList = new ArrayList<>();
			String methods = Optional.ofNullable(properties.getProperty("solver"))
					.orElse("dassl");
			st = new StringTokenizer(methods, ",");
			while (st.hasMoreTokens())
			{
				this.methodList.add(st.nextToken());
			}
		}
		catch (IOException e)
		{
			LOG.error("", e.getMessage());
		}
		return properties;
	}

	@Override
	public Logs getLogs()
	{
		return logs;
	}

	private Properties						properties		= loadDefaultProperties();
	private ModelicaEngineProgress			engineProgress	= new ModelicaEngineProgress();
	private Path							workingDir;
	private Path							omSimulationDir;
	private int								numOfIntervalsPerSecond;
	private int								numOfIntervals;
	private double							startTime;
	private double							stopTime;
	private double							tolerance;
	private List<Path>						libraryDirs;
	private String							resultVariables;
	private String							simFlags;
	private boolean							createFilteredMat;
	private String							modelName;
	public int								depth;
	private List<String>					methodList;

	private Logs							logs			= new Logs(
			"OpenModelica engine interaction");

	private OpenModelicaWrapper				omc				= new OpenModelicaWrapper(
			OMWRAPPER_NAME, logs);
	private ModelicaSimulationFinalResults	results			= new ModelicaSimulationFinalResults();

	private static final String				OMWRAPPER_NAME	= "OpenModelica";

	private static final Logger				LOG				= LoggerFactory
			.getLogger(OpenModelicaEngine.class);
}
