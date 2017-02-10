package org.power_systems_modelica.psm.dymola.integration;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
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
				.orElse("http://0.0.0.0:8888/dymservice?wsdl");
		this.workingDir = Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		this.libraryDir = Paths.get(config.getParameter("libraryDir"));
		this.dymSimulationDir = Optional
				.ofNullable(Paths.get(this.workingDir.toString() + File.separator + DYM_SIM_PREFIX))
				.orElse(Paths.get(""));
		this.resultVariables = Optional.ofNullable(config.getParameter("resultVariables"))
				.orElse("");

		this.startTime = Optional.ofNullable(config.getDouble("startTime")).orElse(0.0);
		this.stopTime = Optional.ofNullable(config.getDouble("stopTime")).orElse(1.0);
		this.tolerance = Optional.ofNullable(config.getDouble("tolerance")).orElse(0.0001);

		this.numOfIntervalsPerSecond = Optional
				.ofNullable(config.getInteger("numOfIntervalsPerSecond")).orElse(100);
		this.numOfIntervals = (int) (this.stopTime * this.numOfIntervalsPerSecond);
		this.intervalSize = this.stopTime / this.numOfIntervals;

		this.createFilteredMat = Optional.ofNullable(config.getBoolean("createFilteredMat"))
				.orElse(false);
		this.depth = Optional.ofNullable(config.getInteger("depth")).orElse(0);
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
		LOGGER.info("Running Dymola client: {}", dymolaClient.toString());

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
			LOGGER.error("Error printing errors file. {}", e.getMessage());
		}
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

			String fileName = this.outputDymolaFileName + "_filtered" + CSV_EXTENSION;
			ModelicaEngineUtils.fillModelicaSimulationResults(
					Paths.get(dymSimulationDir + File.separator + fileName), modelName, results);
		}
		catch (ZipException e)
		{
			LOGGER.error("Error unzippping file {}.", outputZipFileName);
		}
		catch (IOException e)
		{
			LOGGER.error("Error opening/reading file. {}", e.getMessage());
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
		String moFileName = mo.getSystemModel().getId() + ".mo";
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
				LOGGER.error("Error copying file from {} to {}, reason is {}", modelicaPath,
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
						LOGGER.error("Error copying file from {} to {}.", file.toFile(),
								this.dymSimulationDir.toFile());
					}
				});
			}
			catch (Exception e1)
			{
				LOGGER.error("Error copying files from {} to {}.", this.libraryDir,
						this.dymSimulationDir);
			}
		}
		catch (IOException e)
		{
			LOGGER.error(
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
		LOGGER.info("Closing Dymola service.");
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
	private static final String				COMMA			= ",";
	private static final String[]			METHOD_LIST		= new String[] { "Dassl" };

	private static final Logger				LOGGER			= LoggerFactory
			.getLogger(DymolaEngine.class);
}
