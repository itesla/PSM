package org.psm.openmodelica.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openmodelica.corba.ConnectException;
import org.openmodelica.corba.Result;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineProgress;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.engine.utils.ModelicaEngineUtils;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenModelicaEngine implements ModelicaEngine
{

	@Override
	public void configure(Configuration config)
	{
		System.setProperty("com.sun.CORBA.transport.ORBTCPReadTimeouts", "1:15000:30000:1");
		this.workingDir = Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		this.libraryDir = Paths.get(config.getParameter("libraryDir"));
		this.resultVariables = Optional.ofNullable(config.getParameter("resultVariables"))
				.orElse("");

		this.startTime = Optional.ofNullable(config.getDouble("startTime")).orElse(0.0);
		this.stopTime = Optional.ofNullable(config.getDouble("stopTime")).orElse(1.0);
		this.tolerance = Optional.ofNullable(config.getDouble("tolerance")).orElse(0.0001);

		this.numOfIntervalsPerSecond = Optional
				.ofNullable(config.getInteger("numOfIntervalsPerSecond")).orElse(100);
		this.numOfIntervals = (int) this.stopTime * this.numOfIntervalsPerSecond;

		this.createFilteredMat = Optional.ofNullable(config.getBoolean("createFilteredMat"))
				.orElse(false);
		this.simFlags = Optional.ofNullable(config.getParameter("simFlags")).orElse("");
		this.depth = Optional.ofNullable(config.getInteger("depth")).orElse(0);
	}

	@Override
	public void simulate(ModelicaDocument mo)
	{
		modelName = mo.getSystemModel().getId();
		String modelFileName = modelName + MO_EXTENSION;

		prepareWorkingDirectory(mo);
		// The first "result" in ModelicaSimulationResults is the simulation directory "simulation_path"
		this.results.addResult(modelName, "simulation_path", this.omSimulationDir);
		this.results.addResult(modelName, "successful", true);

		try
		{
			LOGGER.info(
					" {} - OpenModelica simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, tolerance:{}.",
					omSimulationDir, modelFileName, modelName, startTime, stopTime,
					numOfIntervalsPerSecond, tolerance);
			Result result;

			progress("Cleaning OpenModelica workspace.");
			result = omc.clear();
			if (!isSuccessful(result) || !result.res.contains("true"))
			{
				this.results.addResult(modelName, "successful", false);
				LOGGER.error("Error clearing workspace: {}.", result.err.replace("\"", ""));
				throw new RuntimeException(
						"Error clearing workspace : " + result.err.replace("\"", ""));
			}

			progress(String.format("Moving into working directory %s.", this.omSimulationDir));
			result = omc.cd("\"" + this.omSimulationDir.toString().replace("\\", "/") + "\"");
			if (!isSuccessful(result))
			{
				this.results.addResult(modelName, "successful", false);
				throw new RuntimeException(
						"Error setting the working directory " + omSimulationDir + ". "
								+ result.err.replace("\"", ""));
			}

			progress(String.format("Loading Modelica standard library."));
			result = omc.loadStandardLibrary();
			if (!isSuccessful(result))
			{
				this.results.addResult(modelName, "successful", false);
				throw new RuntimeException(
						"Error loading the standard library. " + result.err.replace("\"", ""));
			}

			// load to the engine all .mo files
			loadModelsInDirectory(omSimulationDir);

			boolean simulated = false, validated = false, verified = false;

			validated = validateModel(modelName);
			if (!validated || depth == 1)
			{
				this.results.addResult(modelName, "successful", false);
				return;
			}

			if (depth != 0)
			{
				verified = simulateModel(modelName, startTime, 0.0001 * stopTime, numOfIntervals,
						tolerance, simFlags, true);
				if (!simulated || depth == 2)
				{
					this.results.addResult(modelName, "successful", false);
					return;
				}
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
		catch (Exception e)
		{
			progress(String.format("Simulation of model %s completed unsuccessfully.", modelName));
			this.results.addResult(modelName, "successful", false);
			LOGGER.error(
					" {} - openmodelica simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, tolerance:{}: {}",
					workingDir, modelFileName, modelName, startTime, stopTime,
					numOfIntervalsPerSecond, tolerance, e);
			throw new RuntimeException(
					"openmodelica simulation failed - remote working directory " + workingDir
							+ ", fileName: " + modelFileName + ", problem:" + modelName
							+ ", error message:" + e.getMessage(),
					e);
		}
	}

	@Override
	public void simulate(Collection<ModelicaDocument> mos)
	{
		// FIXME Just as an exercise, do it in parallel
		// Temporal files are overwritten if run in parallel (equations not
		// written properly)
		mos.forEach(mo -> simulate(mo));
	}

	private boolean validateModel(String modelName) throws ConnectException
	{
		progress(String.format("Checking model %s.", modelName));
		Result result = omc.checkModel(modelName);
		if (!isSuccessful(result))
		{
			LOGGER.error(
					"Error checking model " + modelName + ". {"
							+ result.err.replace("\"", "") + "}");
			return false;
		}
		progress(String.format("Model %s checked successfully.", modelName));
		return true;
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
		while ((successful == false) && (i < METHOD_LIST.length))
		{
			if (verifying)
				text = "Verifying model %s with integration method %s - start time = %fs - stop time = %fs - number of intervals = %d - tolerance = %f - simulation flags = %s.";
			else text = "Simulating model %s with integration method %s - start time = %fs - stop time = %fs - number of intervals = %d - tolerance = %f - simulation flags = %s.";
			progress(String.format(text, modelName, METHOD_LIST[i], startTime, stopTime,
					numOfIntervals, tolerance, simFlags));

			Result result = omc.simulate(modelName, startTime, stopTime, numOfIntervals,
					METHOD_LIST[i], tolerance, simFlags);
			if (result != null)
			{
				SimulationResult simResult = new SimulationResult(result.res, result.err);
				if (simResult.getResultFile().isEmpty())
				{
					if (verifying)
						text = "Error verifying model {} with integration method {}. \n Reason : \n {} \n {}";
					else text = "Error simulating model {} with integration method {}. \n Reason : \n {} \n {}";
					LOGGER.error(text, modelName, METHOD_LIST[i], simResult.getMessages(),
							simResult.getError());
				}
				else if (simResult.getError().contains("Warning:"))
				{
					if (verifying)
						text = "Warning verifying model {} with integration method {}. \n Reason : \n {}";
					else text = "Warning simulating model {} with integration method {}. \n Reason : \n {}";
					LOGGER.warn(text, modelName, METHOD_LIST[i], simResult.getMessages());
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
				LOGGER.error(text, modelName, METHOD_LIST[i]);
			}
			i++;
		}

		if (!successful)
		{
			if (verifying) text = "Model %s verified unsuccessfully.";
			else text = "Model %s simulated unsuccessfully.";
			progress(String.format(text, modelName));
			return false;
		}
		if (verifying) text = "Model %s verified successfully.";
		else text = "Model %s simulated successfully.";
		progress(String.format(text, modelName));

		endms = Instant.now();
		long simulationTime = Duration.between(startms, endms).toMillis();
		LOGGER.info(" {} - openmodelica simulation terminated - simulation time: {} ms.",
				workingDir,
				simulationTime);

		String matResultsFile = modelName + "_res" + MAT_EXTENSION;
		String csvResultsFile = modelName + "_res_filtered" + CSV_EXTENSION;

		Result result = omc.readSimulationResultVars(matResultsFile);
		if (!isSuccessful(result))
		{
			if (verifying)
				text = "Error reading verification results variables from {}. Reason is {} ";
			else text = "Error reading simulation results variables from {}. Reason is {} ";
			LOGGER.error(text, matResultsFile, result.err.replace("\"", ""));
		}

		String res = result.res;
		List<String> allResultVariables = Arrays
				.asList(res.substring(1, res.length() - 2).split(COMMA));

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
			writeResults(printStream, matResultsFile, filterResultVariables, resultSize);
			ModelicaEngineUtils.fillModelicaSimulationResults(
					Paths.get(omSimulationDir + File.separator + csvResultsFile), modelName,
					this.results);
		}
		catch (IOException e)
		{
			LOGGER.error("Error printing csv file. {}", e.getMessage());
		}

		deleteSimulationFiles();

		return true;
	}

	@Override
	public void close() throws Exception
	{
		System.out.println(this.results.toString());
		LOGGER.info("Closing OpenModelica service.");
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
			LOGGER.error("OpenModelica server has not been closed successfuly.");
		}
	}

	@Override
	public ModelicaSimulationFinalResults getSimulationResults()
	{
		this.results.writeToFile(this.omSimulationDir.resolve("SimulationResults.csv"));
		return this.results;
	}

	private void printModelicaDocument(ModelicaDocument mo, Path outputPath)
	{
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
					LOGGER.warn(result.err.replace("\"", ""));
				else
					LOGGER.error("Error getting simulation result variable {} from  {}. {}", resVar,
							matResultsFile,
							result.err.replace("\"", ""));
				continue;
			}

			String[] values = result.res.substring(2, result.res.length() - 3).split(COMMA);
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
			String matResultsFileFiltered = modelName + "_res_filtered" + MAT_EXTENSION;
			Result result = omc.filterSimulationResults(matResultsFile, matResultsFileFiltered,
					filterResultVariables,
					resultSize);
			if (result.err.startsWith("Error"))
				LOGGER.error("Error creating filtered .mat file {}. Reason is {}",
						matResultsFileFiltered,
						result.err.replace("\"", ""));
			else
				LOGGER.warn(result.err.replace("\"", ""));
		}
	}

	private void prepareWorkingDirectory(ModelicaDocument mo)
	{
		modelName = mo.getSystemModel().getId();
		String modelFileName = modelName + MO_EXTENSION;
		Path modelicaPath = Paths.get(modelFileName);

		try
		{
			this.omSimulationDir = Files.createTempDirectory(this.workingDir, OM_SIM_PREFIX);
			if (Files.notExists(this.omSimulationDir.resolve(modelicaPath)))
			{
				printModelicaDocument(mo, this.omSimulationDir);
			}

			// Copy Modelica models needed to the simulation directory
			try
			{
				Files.list(this.libraryDir).forEach(file -> {
					try
					{
						FileUtils.copyFileToDirectory(file.toFile(), this.omSimulationDir.toFile(),
								false);
					}
					catch (IOException exc)
					{
						LOGGER.error("Error copying file from {} to {}, reason is {}",
								file.toFile(),
								this.omSimulationDir, exc.getMessage());
						throw new RuntimeException(exc);
					}
				});
			}
			catch (Exception e1)
			{
				LOGGER.error("Error copying files from {} to {}, reason is {}", this.libraryDir,
						this.omSimulationDir,
						e1.getMessage());
			}
		}
		catch (IOException e)
		{
			LOGGER.error(
					"Could not create OpenModelica simulation directory in {} with prefix {}, reason is {}",
					this.workingDir, OM_SIM_PREFIX, e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private void deleteSimulationFiles()
	{
		String[] filter = new String[] { MO_EXTENSION, MAT_EXTENSION, CSV_EXTENSION,
				LOG_EXTENSION };
		List<String> filterList = Arrays.asList(filter);

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
				boolean deleted = Files.deleteIfExists(p);
				if (!deleted)
					LOGGER.error("File {} has not been deleted.", p);
			}
		}
		catch (Exception e)
		{
			LOGGER.error("Error deleting Modelica simulator engine files.");
			e.printStackTrace();
		}
	}

	private static boolean isSuccessful(Result result)
	{
		boolean success = false;
		if (result != null)
		{
			if (result.res != null && !result.res.isEmpty())
			{
				success = true;
			}
			if (((result.err != null) && !result.err.isEmpty()))
			{
				if (!result.err.contains("Error"))
				{
					LOGGER.warn("Warning - {}.", result.err.replace("\"", ""));
					success = true;
				}
				else
				{
					LOGGER.error("Error - {}.", result.err.replace("\"", ""));
				}
			}
		}
		return success;
	}

	private void loadModelsInDirectory(Path omSimulationDir) throws ConnectException
	{
		progress("Loading all needed Modelica files.");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(omSimulationDir, "*.mo"))
		{
			for (Path p : stream)
			{
				Result result = omc.loadFile("\"" + p.getFileName().toString() + "\"");
				if (!isSuccessful(result))
				{
					this.results.addResult(modelName, "successful", false);
					LOGGER.error("Error loading file {}. Reason is {}.", p.getFileName().toString(),
							result.err.replace("\"", ""));
				}
				else
				{
					progress(String.format("\t Loading Modelica file : %s.",
							p.getFileName().toString()));
				}
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error reading directory " + omSimulationDir + ".", e);
		}
	}

	@Override
	public void progress(String message)
	{
		LOGGER.info(message);
		engineProgress.updateProgress(message);
	}

	@Override
	public ModelicaEngineProgress getModelicaEngineProgress()
	{
		return engineProgress;
	}

	private ModelicaEngineProgress			engineProgress	= new ModelicaEngineProgress();
	private Path							workingDir;
	private Path							omSimulationDir;
	private int								numOfIntervalsPerSecond;
	private int								numOfIntervals;
	private double							startTime;
	private double							stopTime;
	private double							tolerance;
	private Path							libraryDir;
	private String							resultVariables;
	private String							simFlags;
	private boolean							createFilteredMat;
	private String							modelName;
	public int								depth;

	private OpenModelicaWrapper				omc				= new OpenModelicaWrapper(
			OMWRAPPER_NAME);
	private ModelicaSimulationFinalResults	results			= new ModelicaSimulationFinalResults();

	private static final String				OMWRAPPER_NAME	= "OpenModelica";
	private static final String				OM_SIM_PREFIX	= "omsimulation_";
	private static final String				MO_EXTENSION	= ".mo";
	private static final String				MAT_EXTENSION	= ".mat";
	private static final String				CSV_EXTENSION	= ".csv";
	private static final String				LOG_EXTENSION	= ".log";
	private static final String				COMMA			= ",";

	// For now only Dassl, in the future also DAE-IDA
	private static final String[]			METHOD_LIST		= new String[] { "Dassl" };

	private static final Logger				LOGGER			= LoggerFactory
			.getLogger(OpenModelicaEngine.class);
}
