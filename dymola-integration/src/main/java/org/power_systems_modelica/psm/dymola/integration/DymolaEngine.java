package org.power_systems_modelica.psm.dymola.integration;

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
import org.power_systems_modelica.psm.dymola.integration.utils.ZipFileUtil;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipWriter;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineProgress;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.engine.logs.Logs;
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
				.orElse(this.properties.getProperty("libraryDir"));

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
	public void simulate(Collection<ModelicaDocument> mos) throws Exception
	{
		// FIXME Just as an exercise, do it in parallel
		// Temporal files are overwritten if run in parallel (equations not
		// written properly)
		for (ModelicaDocument mo : mos)
			simulate(mo);
	}
	
	@Override
	public void simulate(ModelicaDocument mo) throws Exception
	{
		String modelName = mo.getSystemModel().getId();
		String moFileName = modelName + MO_EXTENSION;
		Path modelDirectory = Paths.get(moFileName);
		String simResults = "";

		if (Files.notExists(this.workingDir.resolve(modelDirectory)))
		{
			printModelicaDocument(mo, this.workingDir);
		}

		prepareWorkingDirectory(this.workingDir.resolve(modelDirectory), moFileName, modelName);
		// The first "result" in ModelicaSimulationResults is the simulation directory with var="simulation_path"
		this.results.addResult(modelName, "simulation_path", this.dymSimulationDir);
		this.results.addResult(modelName, "successful", true);

		dymolaClient = new StandaloneDymolaClient(dymSimulationDir, inputZipFileName,
				outputZipFileName, outputDymolaFileName,
				createFilteredMat, METHOD_LIST, wsdlService, resultVariables);
		LOG.info("Running Dymola client: {}", dymolaClient.toString());

		progress(String.format("Checking model %s.", modelName));
		simResults = dymolaClient.check(modelName, moFileName);
		if (!simResults.isEmpty())
		{
			this.results.addResult(modelName, "successful", false);
			progress(String.format("Model %s checked unsuccessfully.", modelName));
		}
		else progress(String.format("Model %s checked successfully.", modelName));
		if (depth == 1) return;

		if (depth != 0)
		{
			progress(String.format("Verifying model %s.", modelName));
			simResults = dymolaClient.verify(modelName, moFileName, startTime,
					0.1, 10, 0.001, tolerance);
			if (!simResults.isEmpty())
			{
				this.results.addResult(modelName, "successful", false);
				progress(String.format("Model %s verified unsuccessfully.", modelName));
			}
			else
			{
				progress(String.format("Model %s verified successfully.", modelName));
				writeResults(outputZipFileName, modelName);
			}
		}

		if (depth == 2) return;

		progress(String.format("Simulating model %s.", modelName));
		simResults = dymolaClient.simulate(modelName, moFileName, startTime, stopTime,
				numOfIntervals, intervalSize, tolerance);
		if (!simResults.isEmpty())
		{
			this.results.addResult(modelName, "successful", false);
			progress(String.format("Model %s simulated unsuccessfully.", modelName));
			writeErrorFile(simResults);
		}
		else
		{
			progress(String.format("Model %s simulated successfully.", modelName));
			writeResults(outputZipFileName, modelName);
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
	private void writeResults(String outputZipFileName, String modelName)
	{
		try (ZipFile zipFile = new ZipFile(
				Paths.get(dymSimulationDir + File.separator + outputZipFileName).toFile()))
		{
			ZipFileUtil.unzipFileIntoDirectory(zipFile, dymSimulationDir.toFile());

			String fileName = this.outputDymolaFileName + CSV_EXTENSION;
			ModelicaEngineUtils.fillModelicaSimulationResults(
					Paths.get(dymSimulationDir + File.separator + fileName), modelName, results);
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

	private void prepareWorkingDirectory(Path modelicaPath, String moFileName, String modelName)
	{
		this.inputZipFileName = modelName + "_in.zip";
		this.outputZipFileName = modelName + "_out.zip";
		this.outputDymolaFileName = modelName + "_res";
		this.outputErrorsFileName = modelName + "_errors.log";

		try
		{
			this.dymSimulationDir = Files.createTempDirectory(this.workingDir, DYM_SIM_PREFIX);

			// Copy the Modelica model to the simulation directory
			try
			{
				FileUtils.copyFileToDirectory(modelicaPath.toFile(), this.dymSimulationDir.toFile(),
						false);
			}
			catch (IOException e1)
			{
				LOG.error("Error copying file from {} to {}, reason is {}", modelicaPath,
						this.dymSimulationDir, e1.getMessage());
				throw new RuntimeException(e1);
			}

			// Copy models needed to the simulation directory
			try
			{
				Files.list(this.libraryDir).forEach(file -> {
					try
					{
						FileUtils.copyFileToDirectory(file.toFile(), this.dymSimulationDir.toFile(),
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

		DirectoryStream.Filter<Path> filesFilter = (entry) -> {
			File f = entry.toFile();
			return f.getName().endsWith(MO_EXTENSION);
		};

		ZipWriter zipper = new ZipWriter(this.inputZipFileName, this.dymSimulationDir,
				this.dymSimulationDir, filesFilter);
		zipper.createZip();
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
			this.dymSimulationDir = Optional
					.ofNullable(
							Paths.get(this.workingDir.toString() + File.separator + DYM_SIM_PREFIX))
					.orElse(Paths.get(""));
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
	
	@Override
	public Logs getLogs()
	{
		//FIXME Create structured logs for Dymola simulator.
		return new Logs();
	}

	private Properties						properties		= loadDefaultProperties();
	private ModelicaEngineProgress			engineProgress	= new ModelicaEngineProgress();
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

	private ModelicaSimulationFinalResults	results			= new ModelicaSimulationFinalResults();
	private StandaloneDymolaClient			dymolaClient	= null;

	private static final String				DYM_SIM_PREFIX	= "dymsimulation_";
	private static final String				MO_EXTENSION	= ".mo";
	private static final String				CSV_EXTENSION	= ".csv";
	private static final String[]			METHOD_LIST		= new String[] { "Dassl" };
	private static final Path				DEF_PROPERTIES	= Paths.get(System.getenv("PSM_DATA"))
			.resolve("test").resolve("cfg").resolve("modelicaengine.properties");

	private static final Logger				LOG			= LoggerFactory
			.getLogger(DymolaEngine.class);
}
