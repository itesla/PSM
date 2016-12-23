package org.psm.openmodelica.integration;

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

import org.apache.commons.io.FileUtils;
import org.openmodelica.corba.ConnectException;
import org.openmodelica.corba.Result;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Stage;

public class OpenModelicaEngine implements ModelicaEngine
{

	@Override
	public void configure(Configuration config)
	{
		this.workingDir = Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		this.libraryDir = Paths.get(config.getParameter("libraryDir"));
		this.resultVariables = Optional.ofNullable(config.getParameter("resultVariables"))
				.orElse("");

		this.startTime = Optional.ofNullable(config.getDouble("startTime")).orElse(0.0);
		this.stopTime = Optional.ofNullable(config.getDouble("stopTime")).orElse(1.0);
		this.tolerance = Optional.ofNullable(config.getDouble("tolerance")).orElse(0.0001);

		this.numOfIntervalsPerSecond = Optional
				.ofNullable(config.getInteger("numOfIntervalsPerSecond")).orElse(500);
		this.numOfIntervals = (int) this.stopTime * this.numOfIntervalsPerSecond;

		this.createFilteredMat = Optional.ofNullable(config.getBoolean("createFilteredMat"))
				.orElse(false);

		this.simFlags = Optional.ofNullable(config.getParameter("simFlags")).orElse("");
	}

	@Override
	public boolean validate(ModelicaDocument mo, int depth)
	{
		boolean validated = true;
		// Depth = 1 : only load and check model.
		// Depth = 2 : load, check and small simulation of the model.
		modelName = mo.getSystemModel().getId();
		String modelFileName = modelName + MO_EXTENSION;

		prepareWorkingDirectory(mo);

		try
		{
			Result result;

			result = omc.clear();
			if (!isSuccessful(result) || !result.res.contains("true"))
			{
				LOGGER.error("Error clearing workspace: {}.", result.err.replace("\"", ""));
			}

			result = omc.cd("\"" + this.omSimulationDir.toString().replace("\\", "/") + "\"");
			if (!isSuccessful(result))
			{
				LOGGER.error("Error setting the working directory {}. Reason is {}.",
						omSimulationDir, result.err.replace("\"", ""));

			}

			result = omc.loadStandardLibrary();
			if (!isSuccessful(result))
			{
				LOGGER.error("Error loading the standard library. {}",
						result.err.replace("\"", ""));
			}

			// load to the engine all .mo files
			loadModelsInDirectory(omSimulationDir);

			switch (depth)
			{
			case 1:
				result = omc.checkModel(modelName);
				if (!isSuccessful(result))
				{
					LOGGER.error(
							"Error checking model " + modelName + ". {"
									+ result.err.replace("\"", "") + "}");
					validated = false;
				}
				break;
			case 2:
				result = omc.checkModel(modelName);
				if (!isSuccessful(result))
				{
					LOGGER.error(
							"Error checking model " + modelName + ". Reason is "
									+ result.err.replace("\"", "") + ".");
					validated = false;
				}
				boolean simulated = simulateModel(modelName, startTime, 0.01 * stopTime,
						numOfIntervals, tolerance,
						simFlags);
				if (!simulated) validated = false;
				break;
			}
			return validated;
		}
		catch (Exception e)
		{
			LOGGER.error(
					" {} - openmodelica simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, tolerance:{}: {}",
					workingDir, modelFileName, modelName, startTime, stopTime,
					numOfIntervalsPerSecond, tolerance, e);
			return false;
		}
	}

	@Override
	public void simulate(ModelicaDocument mo)
	{
		modelName = mo.getSystemModel().getId();
		String modelFileName = modelName + MO_EXTENSION;

		prepareWorkingDirectory(mo);

		try
		{
			LOGGER.info(
					" {} - OpenModelica simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, tolerance:{}.",
					omSimulationDir, modelFileName, modelName, startTime, stopTime,
					numOfIntervalsPerSecond, tolerance);
			Result result;

			result = omc.clear();
			if (!isSuccessful(result) || !result.res.contains("true"))
			{
				LOGGER.error("Error clearing workspace: {}.", result.err.replace("\"", ""));
				throw new RuntimeException(
						"Error clearing workspace : " + result.err.replace("\"", ""));
			}

			result = omc.cd("\"" + this.omSimulationDir.toString().replace("\\", "/") + "\"");
			if (!isSuccessful(result))
			{
				throw new RuntimeException(
						"Error setting the working directory " + omSimulationDir + ". "
								+ result.err.replace("\"", ""));
			}

			result = omc.loadStandardLibrary();
			if (!isSuccessful(result))
			{
				throw new RuntimeException(
						"Error loading the standard library. " + result.err.replace("\"", ""));
			}

			// load to the engine all .mo files
			loadModelsInDirectory(omSimulationDir);

			result = omc.checkModel(modelName);
			if (!isSuccessful(result))
			{
				throw new RuntimeException(
						"Error checking model " + modelName + ". {"
								+ result.err.replace("\"", "") + "}");
			}

			// Simulate the model
			// Parameter "simFlags" can be a comma separated list of log level:
			// -lv LOG_INIT,LOG_SIMULATION,LOG_STATS,LOG_EVENTS
			// See
			// https://openmodelica.org/doc/OpenModelicaUsersGuide/latest/simulationflags.html
			// IMPORTANT: These simFlags can greatly increase the simulation
			// time.
			simulateModel(modelName, startTime, stopTime, numOfIntervals, tolerance, simFlags);

			// String matResultsFile = modelName + "_res" + MAT_EXTENSION;
			// String csvResultsFile = modelName + "_res_filtered" + CSV_EXTENSION;
			//
			// List<String> filterResultVariables = new ArrayList<String>();
			// int resultSize = 0;
			// readSimulationResults(matResultsFile, filterResultVariables, resultSize);
			//
			// try (PrintStream printStream = new PrintStream(
			// Files.newOutputStream(
			// Paths.get(omSimulationDir + File.separator + csvResultsFile))))
			// {
			// writeResults(printStream, matResultsFile, filterResultVariables, resultSize);
			// }
			// catch (IOException e)
			// {
			// LOGGER.error("Error printing csv file. {}", e.getMessage());
			// }
			// endms = Instant.now();
			// long simulationTime = Duration.between(startms, endms).toMillis();
			// LOGGER.info(" {} - openmodelica simulation terminated - simulation time: {} ms.",
			// workingDir,
			// simulationTime);
			//
			// deleteSimulationFiles();
		}
		catch (Exception e)
		{
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

	@Override
	public void close()
	{
		LOGGER.info("Closing OpenModelica server.");
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
		// The first "result" in ModelicaSimulationResults is the simulation
		// directory "simulation_path"
		this.results.addResult(modelName, "simulation_path", this.omSimulationDir);

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

			// LUMA resVar was quoted, remove quotes before storing in
			// simulation results
			// LUMA In SimulationResults put only the last observed value
			this.results.addResult(modelName, resVar.replaceAll("\"", ""),
					values[values.length - 1]);
		}

		String strLine = filterResultVariables.stream().collect(Collectors.joining(",")).toString();
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
			this.omSimulationDir = Files.createTempDirectory(this.workingDir, OM_PREFIX);

			if (Files.notExists(this.omSimulationDir.resolve(modelicaPath)))
			{
				printModelicaDocument(mo, this.omSimulationDir);
			}

			// Copy Models models needed to the simulation directory
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
					this.workingDir, OM_PREFIX, e.getMessage());
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
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(omSimulationDir, "*.mo"))
		{
			for (Path p : stream)
			{
				Result result = omc.loadFile("\"" + p.getFileName().toString() + "\"");
				if (!isSuccessful(result))
				{
					LOGGER.error("Error loading file {}. Reason is {}.", p.getFileName().toString(),
							result.err.replace("\"", ""));
				}
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException("Error reading directory " + omSimulationDir + ".", e);
		}
	}

	private boolean simulateModel(String modelName, double startTime, double stopTime,
			int numOfIntervals, double tolerance, String simFlags)
			throws ConnectException
	{
		Instant startms, endms;

		startms = Instant.now();
		int i = 0;
		boolean successful = false;
		while ((successful == false) && (i < METHOD_LIST.length))
		{
			LOGGER.info(
					"Simulating model {} with integration method {}. Start time = {}s. Stop time = {}s. Number of intervals = {}. Tolerance = {}. Simulation flags = {}.",
					modelName, METHOD_LIST[i], startTime, stopTime, numOfIntervals, tolerance,
					simFlags);

			Result result = omc.simulate(modelName, startTime, stopTime, numOfIntervals,
					METHOD_LIST[i], tolerance,
					simFlags);
			if (result != null)
			{
				SimulationResult simResult = new SimulationResult(result.res, result.err);
				if (simResult.getResultFile().isEmpty())
				{
					LOGGER.error(
							"Error simulating model {} with integration method {}. \n Reason : \n {}. \n {}",
							modelName,
							METHOD_LIST[i], simResult.getMessages(), simResult.getError());
				}
				else if (simResult.getError().contains("Warning:"))
				{
					LOGGER.warn(
							"Warning simulating model {} with integration method {}. \n Reason : \n {}.",
							modelName,
							METHOD_LIST[i], simResult.getMessages());
					successful = true;
				}
				else
				{
					successful = true;
				}
			}
			else
			{
				LOGGER.error("Error simulating model {} with integration method {}.", modelName,
						METHOD_LIST[i]);
			}
			i++;
		}

		if (!successful) return false;

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
			LOGGER.error(
					"Error reading simulation results variables from {}. Reason is {}. ",
					matResultsFile, result.err.replace("\"", ""));
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
		}
		catch (IOException e)
		{
			LOGGER.error("Error printing csv file. {}", e.getMessage());
		}

		deleteSimulationFiles();

		return true;
	}

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

	private OpenModelicaWrapper				omc				= new OpenModelicaWrapper(
			OMWRAPPER_NAME);;
	private ModelicaSimulationFinalResults	results			= new ModelicaSimulationFinalResults();

	private static final String				OMWRAPPER_NAME	= "OpenModelica";
	private static final String				OM_PREFIX		= "omsimulation_";
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
