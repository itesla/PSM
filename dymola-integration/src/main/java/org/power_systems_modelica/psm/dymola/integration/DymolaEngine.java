package org.power_systems_modelica.psm.dymola.integration;

/*
 * #%L
 * Dynamic simulation using Dymola
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipFileUtil;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipWriter;
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
				.get(this.workingDir.toString() + File.separator + StaticData.SIMULATION_PREFIX);

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
		this.intervalSize = this.stopTime / this.numOfIntervals;

		this.createFilteredMat = Boolean
				.valueOf(Optional.ofNullable(config.getParameter("createFilteredMat"))
						.orElse(this.properties.getProperty("createFilteredMat")));
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
		String moFileName = modelName + StaticData.MO_EXTENSION;
		Path modelDirectory = Paths.get(moFileName);
		String simResults = "";
		boolean res = false;

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
			res = dymolaClient.check(this.dymSimulationDir, modelName, moFileName,
					outputDymolaFileName);
			if (!res)
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
					0.1, 10, 0.01, tolerance, outputDymolaFileName);
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
			this.dymSimulationDir = Files.createTempDirectory(this.workingDir, "dym" + StaticData.SIMULATION_PREFIX);
			// Copy models needed to the simulation directory
			try
			{
				Path libraryPath = Paths.get(this.dymSimulationDir.resolve("library").toString());
				Files.createDirectory(libraryPath);
				
				for (Path libraryDir : this.libraryDirs)
				{
					loadLibrary(libraryPath, libraryDir);
				}
			}
			catch (Exception e1)
			{
				LOG.error("Error copying files from {} to {}.", this.libraryDirs,
						this.dymSimulationDir);
			}
		}
		catch (IOException e)
		{
			LOG.error(
					"Could not create Dymola simulation directory in {} with prefix {}, reason is {}",
					this.workingDir, StaticData.SIMULATION_PREFIX, e.getMessage());
			throw new RuntimeException(e);
		}

		this.inputZipFileName = "dymola_input.zip";
		this.outputZipFileName = "dymola_output";
	
		ZipWriter zipper = new ZipWriter(this.inputZipFileName,
				this.dymSimulationDir.resolve("library"),
				this.dymSimulationDir.resolve("library"), null);
		zipper.createZip();

		dymolaClient = new StandaloneDymolaClient(this.dymSimulationDir, inputZipFileName,
				outputZipFileName, outputDymolaFileName,
				createFilteredMat, methodList.stream().toArray(String[]::new), wsdlService, resultVariables);
		LOG.info("Running Dymola client: {}", dymolaClient.toString());

		progress("Preparing dynamic environment.");
		boolean result = dymolaClient.prepareDynamicEnvironment(this.dymSimulationDir);
		if (!result)
		{
			progress("Error preparing dynamic environment. See the Dymola server log for more information.");
		}		
	}
	
	private void loadLibrary(Path libraryPath, Path libraryDir) throws IOException
	{
		Files.list(libraryDir).forEach(file -> {
			try
			{
				if(Files.isDirectory(file))
				{
					FileUtils.copyDirectoryToDirectory(file.toFile(), libraryPath.toFile());
				}
				else
				{
					FileUtils.copyFileToDirectory(file.toFile(), libraryPath.toFile(),
							false);
				}
			}
			catch (IOException exc)
			{
				LOG.error("Error copying file from {} to {}.", file.toFile(),
						this.dymSimulationDir.toFile());
			}
		});
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
		
			String fileName = this.outputDymolaFileName + StaticData.CSV_EXTENSION;
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
		try (InputStream inputStream = Files.newInputStream(PathUtils.CONFIG
																.resolve("modelicaengine.properties")))
		{
			properties.load(inputStream);

			this.wsdlService = Optional.ofNullable(properties.getProperty("webService"))
					.orElse("http://0.0.0.0:8888/dymservice?wsdl");
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
			
			this.methodList = new ArrayList<>();
			String methods = Optional.ofNullable(properties.getProperty("solver"))
					.orElse("Dassl");
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
	private List<Path>						libraryDirs;
	private String							resultVariables;
	private boolean							createFilteredMat;
	private int								depth;
	private List<String>					methodList;

	private Logs							logs				= new Logs(
			"Dymola engine interaction");

	private ModelicaSimulationFinalResults	results				= new ModelicaSimulationFinalResults();
	private StandaloneDymolaClient			dymolaClient		= null;

//	private static final String				DYM_SIM_PREFIX		= "dymsimulation_";
//	private static final String				MO_EXTENSION		= ".mo";
//	private static final String				CSV_EXTENSION		= ".csv";

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
