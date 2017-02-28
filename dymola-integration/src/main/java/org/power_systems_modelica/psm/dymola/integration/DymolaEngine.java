package org.power_systems_modelica.psm.dymola.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipFileUtil;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipWriter;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineProgress;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.engine.utils.ModelicaEngineUtils;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DymolaEngine implements ModelicaEngine
{

	@Override
	public void configure(Configuration config)
	{
		this.wsdlService = Optional.ofNullable(config.getParameter("webService"))
				.orElse(this.properties.getProperty("webService"));
		this.workingDir = Paths
				.get(Optional.ofNullable(config.getParameter("modelicaEngineWorkingDir"))
						.orElse(this.properties.getProperty("modelicaEngineWorkingDir")));
		this.dymSimulationDir = Paths
				.get(this.workingDir.toString() + File.separator + DYM_SIM_PREFIX);

		this.libraryDir = Paths.get(Optional.ofNullable(config.getParameter("libraryDir"))
				.orElse(this.properties.getProperty("libraryDir")));
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
		this.intervalSize = this.stopTime / this.numOfIntervals;

		this.createFilteredMat = Boolean
				.valueOf(Optional.ofNullable(config.getParameter("createFilteredMat"))
						.orElse(this.properties.getProperty("createFilteredMat")));
		this.depth = Integer.valueOf(Optional.ofNullable(config.getParameter("depth"))
				.orElse(this.properties.getProperty("depth")));
	}

	@Override
	public void simulate(ModelicaDocument mo) throws Exception
	{
		prepareDynamicEnvironment();

		progress(String.format("Model %s", mo.getSystemModel().getId()));
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
			progress(String.format("Model %s", mo.getSystemModel().getId()));
			simulateModelicaDocument(mo);
		}

	}

	private void simulateModelicaDocument(ModelicaDocument mo) throws Exception
	{
		String modelName = mo.getSystemModel().getId();
		String moFileName = modelName + MO_EXTENSION;
		Path modelDirectory = Paths.get(moFileName);
		String simResults = "";

		// The first "result" in ModelicaSimulationResults is the simulation directory with var="simulation_path"
		this.results.addResult(modelName, "simulation_path",
				this.dymSimulationDir.resolve(modelName));
		this.results.addResult(modelName, "successful", true);
		Files.createDirectory(this.dymSimulationDir.resolve(modelName));
		if (Files.notExists(this.dymSimulationDir.resolve(modelName).resolve(modelDirectory)))
		{
			printModelicaDocument(mo, this.dymSimulationDir.resolve(modelName));
		}

		this.outputZipFileName = modelName + ".zip";
		this.outputDymolaFileName = modelName + "_res";
		this.outputErrorsFileName = modelName + "_errors.log";

		// Option 1 : Check
		// Option 2 : Verify
		// Option 0 : Simulate
		if (depth == 1)
		{
			progress(String.format("Checking model %s.", modelName));
			simResults = dymolaClient.check(this.dymSimulationDir, modelName, moFileName,
					outputDymolaFileName);
			if (!simResults.isEmpty())
			{
				this.results.addResult(modelName, "successful", false);
				progress(String.format("Model %s checked unsuccessfully.", modelName));
			}
			else progress(String.format("Model %s checked successfully.", modelName));

			fillLogs(this.dymSimulationDir.resolve(modelName).resolve(modelName + "_res_log.txt"));
			return;
		}

		if (depth != 0)
		{
			progress(String.format("Verifying model %s.", modelName));
			simResults = dymolaClient.verify(this.dymSimulationDir, modelName, moFileName,
					startTime,
					0.1, 10, 0.001, tolerance, outputDymolaFileName);
			if (!simResults.isEmpty())
			{
				this.results.addResult(modelName, "successful", false);
				progress(String.format("Model %s verified unsuccessfully.", modelName));
			}
			else
			{
				progress(String.format("Model %s verified successfully.", modelName));
				Path modelPath = this.dymSimulationDir.resolve(modelName);
				writeResults(outputZipFileName, modelName, modelPath);
			}
		}

		if (depth == 2)
		{
			fillLogs(this.dymSimulationDir.resolve(modelName).resolve(modelName + "_res_log.txt"));
			return;
		}

		progress(String.format("Simulating model %s.", modelName));
		simResults = dymolaClient.simulate(this.dymSimulationDir, modelName, moFileName, startTime,
				stopTime,
				numOfIntervals, intervalSize, tolerance, outputDymolaFileName);
		if (!simResults.isEmpty())
		{
			this.results.addResult(modelName, "successful", false);
			progress(String.format("Model %s simulated unsuccessfully.", modelName));
			writeErrorFile(simResults);
		}
		else
		{
			progress(String.format("Model %s simulated successfully.", modelName));
			Path modelPath = this.dymSimulationDir.resolve(modelName);
			writeResults(outputZipFileName, modelName, modelPath);
		}
		fillLogs(this.dymSimulationDir.resolve(modelName).resolve(modelName + "_res_log.txt"));
	}

	private void prepareDynamicEnvironment()
	{
		try
		{
			this.dymSimulationDir = Files.createTempDirectory(this.workingDir, DYM_SIM_PREFIX);
			// Copy models needed to the simulation directory
			try
			{
				Path libraryPath = Paths.get(this.dymSimulationDir.resolve("library").toString());
				Files.createDirectory(libraryPath);

				Files.list(this.libraryDir).forEach(file -> {
					try
					{

						FileUtils.copyFileToDirectory(file.toFile(), libraryPath.toFile(),
								false);
					}
					catch (IOException exc)
					{
						LOG.error("Error copying file from {} to {}.", file.toFile(),
								this.dymSimulationDir.toFile());
					}
				});
			}
			catch (Exception e1)
			{
				LOG.error("Error copying files from {} to {}.", this.libraryDir,
						this.dymSimulationDir);
			}
		}
		catch (IOException e)
		{
			LOG.error(
					"Could not create Dymola simulation directory in {} with prefix {}, reason is {}",
					this.workingDir, DYM_SIM_PREFIX, e.getMessage());
			throw new RuntimeException(e);
		}

		this.inputZipFileName = "dymola_input.zip";
		this.outputZipFileName = "dymola_output";

		DirectoryStream.Filter<Path> filesFilter = (entry) -> {
			File f = entry.toFile();
			return f.getName().endsWith(MO_EXTENSION);
		};

		ZipWriter zipper = new ZipWriter(this.inputZipFileName,
				this.dymSimulationDir.resolve("library"),
				this.dymSimulationDir.resolve("library"), filesFilter);
		zipper.createZip();

		dymolaClient = new StandaloneDymolaClient(this.dymSimulationDir, inputZipFileName,
				outputZipFileName, outputDymolaFileName,
				createFilteredMat, METHOD_LIST, wsdlService, resultVariables);
		LOG.info("Running Dymola client: {}", dymolaClient.toString());

		progress("Preparing dynamic environment.");
		String result = dymolaClient.prepareDynamicEnvironment(this.dymSimulationDir);
		if (!result.isEmpty())
		{
			progress("Error preparing dynamic environment.");
		}
	}

	private void writeErrorFile(String simResults)
	{
		try (PrintStream printStream = new PrintStream(
				Files.newOutputStream(workingDir.resolve(outputErrorsFileName))))
		{
			printStream.print(simResults);
		}
		catch (IOException e)
		{
			LOG.error("Error printing errors file. {}", e.getMessage());
		}
	}

	@Override
	public ModelicaSimulationFinalResults getSimulationResults()
	{
		return results;
	}

	/**
	 * Read the filtered csv file written by row and save simulation results in the ModelicaSimulationResults object and in a csv by column.
	 */
	private void writeResults(String outputZipFileName, String modelName, Path modelPath)
	{
		try (ZipFile zipFile = new ZipFile(modelPath.resolve(outputZipFileName).toFile()))
		{
			ZipFileUtil.unzipFileIntoDirectory(zipFile, modelPath.toFile());

			String fileName = this.outputDymolaFileName + CSV_EXTENSION;
			ModelicaEngineUtils.fillModelicaSimulationResults(modelPath.resolve(fileName),
					modelName, results);
		}
		catch (ZipException e)
		{
			LOG.error("Error unzippping file {}.", outputZipFileName);
		}
		catch (IOException e)
		{
			LOG.error("Error opening/reading file. {}", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	ArrayList<String> getResultsFor(String modelName, String d)
	{
		try
		{
			return ((ArrayList<String>) this.results.getValue(modelName, d));
		}
		catch (Exception e)
		{
			throw new RuntimeException("No results for model " + modelName + " field " + d);
		}
	}

	private void printModelicaDocument(ModelicaDocument mo, Path outputPath)
	{
		Path out = outputPath.resolve(mo.getSystemModel().getId() + ".mo");
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

	@Override
	public void close() throws Exception
	{
		// The Dymola service is independent of the Dymola Engine.
		LOG.info("Closing Dymola service.");
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

	public void setEngineProperties(Properties properties)
	{
		this.properties = properties;
	}

	@Override
	public Properties loadDefaultProperties()
	{
		Properties properties = new Properties();
		try (InputStream inputStream = Files.newInputStream(DEF_PROPERTIES))
		{
			properties.load(inputStream);

			this.wsdlService = Optional.ofNullable(properties.getProperty("webService"))
					.orElse("http://0.0.0.0:8888/dymservice?wsdl");
			this.workingDir = Paths.get(properties.getProperty("modelicaEngineWorkingDir"));
			this.libraryDir = Paths.get(properties.getProperty("libraryDir"));
			this.resultVariables = Optional.ofNullable(properties.getProperty("resultVariables"))
					.orElse("");

			this.startTime = Double.valueOf(Optional
					.ofNullable(properties.getProperty("startTime")).orElse("0.0"));
			this.stopTime = Double.valueOf(Optional.ofNullable(properties.getProperty("stopTime"))
					.orElse("1.0"));
			this.tolerance = Double.valueOf(Optional
					.ofNullable(properties.getProperty("tolerance")).orElse("0.0001"));

			this.numOfIntervalsPerSecond = Integer.valueOf(Optional
					.ofNullable(properties.getProperty("numOfIntervalsPerSecond"))
					.orElse("100"));
			this.numOfIntervals = (int) (this.stopTime * this.numOfIntervalsPerSecond);
			this.intervalSize = this.stopTime / this.numOfIntervals;

			this.createFilteredMat = Boolean.valueOf(Optional
					.ofNullable(properties.getProperty("createFilteredMat"))
					.orElse("false"));
			this.depth = Integer.valueOf(Optional.ofNullable(properties.getProperty("depth"))
					.orElse("0"));
		}
		catch (IOException e)
		{
			LOG.error("", e.getMessage());
		}
		return properties;
	}

	private void fillLogs(Path logPath)
	{
		try
		{
			if (Files.exists(logPath))
			{
				BufferedReader logReader = new BufferedReader(Files.newBufferedReader(logPath));
				String line;
				while ((line = logReader.readLine()) != null)
				{
					if (line.trim().matches(SKIP_NEXT_LINE))
					{
						logReader.readLine();
					}
					else if (line.trim().matches(SKIP_LINES))
					{
						continue;
					}
					else if (line.trim().matches(RESULT_EXPRESSION))
					{
						logs.result(line);
					}
					else if (line.trim().matches(ACTIVITY_EXPRESSION))
					{
						logs.newActivity(line);
					}
				}
				logReader.close();
			}
			else LOG.error(
					String.format("Error reading log file %s. File does not exist.", logPath));
		}
		catch (IOException e)
		{
			LOG.error(String.format("Error reading log file %s. \n\t %s", logPath.toString(),
					e.getMessage()));
		}
	}

	@Override
	public Logs getLogs()
	{
		return logs;
	}

	private Properties						properties			= loadDefaultProperties();
	private ModelicaEngineProgress			engineProgress		= new ModelicaEngineProgress();
	private Path							workingDir;
	private Path							dymSimulationDir;
	private int								numOfIntervalsPerSecond;
	private int								numOfIntervals;
	private double							intervalSize;
	private double							startTime;
	private double							stopTime;
	private double							tolerance;
	private String							wsdlService;
	private String							inputZipFileName;
	private String							outputZipFileName;
	private String							outputDymolaFileName;
	private String							outputErrorsFileName;
	private Path							libraryDir;
	private String							resultVariables;
	private boolean							createFilteredMat;
	private int								depth;

	private Logs							logs				= new Logs(
			"Dymola engine interaction");

	private ModelicaSimulationFinalResults	results				= new ModelicaSimulationFinalResults();
	private StandaloneDymolaClient			dymolaClient		= null;

	private static final String				DYM_SIM_PREFIX		= "dymsimulation_";
	private static final String				MO_EXTENSION		= ".mo";
	private static final String				CSV_EXTENSION		= ".csv";
	private static final String[]			METHOD_LIST			= new String[] { "Dassl" };
	private static final Path				DEF_PROPERTIES		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test").resolve("cfg").resolve("modelicaengine.properties");

	private static final String				ACTIVITY_EXPRESSION	= new StringBuilder()
			.append("((.*?)\\((.*?)\\);?)").append("|")
			.append("(^HTTP.*)").toString();
	private static final String				RESULT_EXPRESSION	= "^=.*";
	private static final String				SKIP_LINES			= new StringBuilder()
			.append("(^((writeTrajectory|\\[|\").*)|(=$))").append("|")
			.append("(([-+]?[0-9]*\\.?[0-9E\\-]*)(,|;|\\])\\s*?)*")
			.toString();
	private static final String				SKIP_NEXT_LINE		= "^readTrajectory.*";

	private static final Logger				LOG					= LoggerFactory
			.getLogger(DymolaEngine.class);
}
