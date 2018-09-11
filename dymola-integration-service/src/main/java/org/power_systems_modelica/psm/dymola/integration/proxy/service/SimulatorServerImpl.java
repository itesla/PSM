/**
 * Copyright (c) 2016, All partners of the iTesla project (http://www.itesla-project.eu/consortium)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.power_systems_modelica.psm.dymola.integration.proxy.service;

import static org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.MapUtils.entry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

import org.power_systems_modelica.psm.commons.FileUtils;
import org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.MapUtils;
import org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.Pool;
import org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.StaticData;
import org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.Utils;
import org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.ZipFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dassault_systemes.dymola.DymolaException;
import com.dassault_systemes.dymola.DymolaInterface;
import com.dassault_systemes.dymola.DymolaWrapper;
import com.sun.xml.internal.ws.developer.StreamingAttachment;
import com.sun.xml.internal.ws.developer.StreamingDataHandler;

/**
 * @author Quinary <itesla@quinary.com>
 */
@SuppressWarnings("restriction")
@MTOM
@StreamingAttachment(parseEagerly = true, memoryThreshold = 5000000L, dir = "/tmp")
@WebService(endpointInterface = "org.power_systems_modelica.psm.dymola.integration.proxy.service.SimulatorServer")
public class SimulatorServerImpl implements SimulatorServer
{
	public SimulatorServerImpl()
	{
		this(DYMOLASERVICE_TEMP, 9000, 20, false);
	}

	public SimulatorServerImpl(String serviceWorkDir, int start, int size)
	{
		this(serviceWorkDir, start, size, false);
	}

	public SimulatorServerImpl(String serviceWorkDir, int start, int size, boolean debug)
	{
		// Set this flag to false if you want Dymola to be visible.
		// By default Dymola is hidden.
		// DymolaWrapper.nowindow = false;
		DymolaWrapper.nowindow = true;
		LOG.info("Dymola proxy service");
		LOG.info(" API version: {}", DymolaWrapper.dymola_version);
		LOG.info(" service working directory: {}", serviceWorkDir);
		LOG.info(" ports pool starting from {}, size {}", start, size);
		ArrayList<Integer> portArray = new ArrayList<>();
		for (int i = start; i < start + size; i++)
		{
			portArray.add(i);
		}
		portPool = new Pool<>(portArray);

		this.serviceWorkDir = serviceWorkDir;
		this.debug = debug;

		try
		{
			// Instantiate the Dymola interface and start Dymola
			try
			{
				port = portPool.getItem();
			}
			catch (InterruptedException e)
			{
				LOG.error("Error getting port item. Reasoin is {}.", e.getCause());
				e.printStackTrace();
			}
			dymola = DymolaWrapper.getInstance(true, port);
			LOG.debug(
					"   START Dymola - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ",
					((DymolaWrapper) dymola).portnumber, Thread.currentThread().getId(),
					dymola.hashCode(), this.hashCode());
		}
		catch (Exception e)
		{
			LOG.error(" Error instantiating Dymola. Reason is {}.", e.getCause());
		}
	}

	public @XmlMimeType("text/plain") DataHandler prepareDynamicEnvironment(
			String workingDirectory,
			String resultsFileName,
			@XmlMimeType("application/octet-stream") DataHandler data)
	{
		Path dymSimulationDir = Paths.get(this.serviceWorkDir).resolve(workingDirectory);
		try
		{
			Path inputZipFile;
			try (StreamingDataHandler inputDh = (StreamingDataHandler) data)
			{
				if (!Files.exists(dymSimulationDir.resolve("library")))
				{
					dymSimulationDir = Files.createDirectories(dymSimulationDir.resolve("library"));
				}
				inputZipFile = dymSimulationDir.resolve(DYMOLASERVICE_INPUTFILENAME);
				inputDh.moveTo(inputZipFile.toFile());
			}
			try (ZipFile zipFile = new ZipFile(inputZipFile.toFile()))
			{
				ZipFileUtil.unzipFileIntoDirectory(zipFile, dymSimulationDir.toFile());
			}
			LOG.info(
					"PREPARING WORKSPACE - Dymola preparing workspace started \n\t workingDirectory: {} \n\t resultsFileName: {}.",
					workingDirectory, resultsFileName);

			boolean result = false;
			result = dymola.clear();
			if (!result)
			{
				throw new RuntimeException("Error clearing workspace : " + dymola.getLastError());
			}
			
			result = dymola.cd(dymSimulationDir.getParent().toString());
			if (!result)
			{
				LOG.error(String.format(
						"PREPARING WORKSPACE - Error setting the working directory %s. Reason is %s",
						workingDirectory, dymola.getLastError()));
				return new DataHandler("false", "text/plain");
			}

			try
			{
				List<Path> filesList = Files.list(dymSimulationDir)
						.filter(f -> !f.toFile().getName().endsWith(".zip"))
						.collect(Collectors.toList());
				for (Path file : filesList)
				{
					boolean loaded;
					if (Files.isDirectory(file))
					{
						try (DirectoryStream<Path> mofiles = Files.newDirectoryStream(file, "*.mo"))
						{
							for (Path p : mofiles)
							{
								String moFile = p.getFileName().toString();
								String moDir = p.toFile().getParent();
								loaded = dymola.openModel(moDir + "\\" + moFile);
								if (!loaded)
								{
									LOG.error(String.format(
											"PREPARING WORKSPACE - Error opening model (DIRECTORY) %s in directory %s.",
											moFile, workingDirectory));
									return new DataHandler("false", "text/plain");
								}
							}
						}
						catch (IOException e)
						{
							throw new RuntimeException("Error reading directory " + file, e);
						}
					}
					else
					{
						String moFile = file.getFileName().toString();
						loaded = dymola.openModel(file.toAbsolutePath().toString());
						if (!loaded)
						{
							LOG.error(String.format(
									"PREPARING WORKSPACE - Error opening model (FILE) %s in directory %s.",
									moFile, workingDirectory));
							return new DataHandler("false", "text/plain");
						}
					}
				}
			}
			catch (IOException e)
			{
				LOG.error("PREPARE - Error loading library models in directory {}. {}",
						workingDirectory, dymola.getLastError());
				return new DataHandler("false", "text/plain");
			}

			dymola.savelog(DYMOLA_LOG_FILENAME);

			return new DataHandler("true", "text/plain");
		}
		catch (Exception e)
		{
			LOG.error(String.format(
					"PREPARE - Dymola preparing workspace failed \n\t workingDirectory:%s. \n\t %s",
					workingDirectory, resultsFileName, e));

			String errMessg = e.getMessage();
			errMessg = ((errMessg != null) && (errMessg.length() > MSGERRLEN))
					? errMessg.substring(0, MSGERRLEN) + " ..." : errMessg;

			throw new WebServiceException(String.format(
					"PREPARE - Dymola preparing workspace failed \n\t workspace %s \n\t errorMessage %s",
					workingDirectory.toString(), errMessg), e);
		}
		finally
		{
			try
			{
				if (!debug) FileUtils.deleteDirectory(dymSimulationDir);
			}
			catch (IOException e)
			{
				LOG.error(String.format("PREPARE - Error deleting simulation files from %s",
						dymSimulationDir));
			}
		}
	}

	public @XmlMimeType("text/plain") DataHandler check(
			String workingDirectory,
			String inputFileName,
			String problem,
			String resultsFileName,
			@XmlMimeType("application/octet-stream") DataHandler data)
	{
		Path dymSimulationDir = Paths.get(this.serviceWorkDir).resolve(workingDirectory)
				.resolve(problem);
		try
		{
			Path inputFile;
			try (StreamingDataHandler inputDh = (StreamingDataHandler) data)
			{
				if (!Files.exists(dymSimulationDir))
				{
					dymSimulationDir = Files.createDirectories(dymSimulationDir);
				}
				inputFile = dymSimulationDir.resolve(inputFileName);
				inputDh.moveTo(inputFile.toFile());
			}

			LOG.info(
					"CHECK - dymola checking started \n\t inputFileName:{} \n\t problem:{} \n\t resultsFileName:{}.",
					dymSimulationDir, inputFileName, problem, resultsFileName);
			
			boolean result = dymola.cd(dymSimulationDir.getParent().toAbsolutePath().toString());
			if (!result)
			{
				LOG.error(String.format(
						"CHECK - Error setting the working directory %s. \n\t Reason is %s.",
						dymSimulationDir.getParent().toAbsolutePath().toString(),
						dymola.getLastError()));
				return new DataHandler("false", "text/plain");
			}

			boolean loaded = dymola.openModel(inputFile.toAbsolutePath().toString());
			if (!loaded)
			{
				LOG.error(String.format("CHECK - Error opening model %s in directory %s.",
						inputFile, dymSimulationDir));
				return new DataHandler("false", "text/plain");
			}

			result = checkDymola(dymSimulationDir, inputFileName, problem, resultsFileName);
			if (!result)
			{
				LOG.error("Error checking model {}. Reason is {}.", problem,
						dymola.getLastError());
				return new DataHandler("false", "text/plain");
			}
			else
			{
				LOG.info("CHECK - Model {} checked successfully.", problem);
			}

			return new DataHandler("true", "text/plain");
		}
		catch (Exception e)
		{
			LOG.error(String.format(
					"CHECK - Dymola check failed \n\t workspace:%s \n\t inputFileName:%s \n\t problem:%s \n\t resultsFileName:%s",
					workingDirectory, inputFileName, problem, resultsFileName, e));

			String errMessg = e.getMessage();
			errMessg = ((errMessg != null) && (errMessg.length() > MSGERRLEN))
					? errMessg.substring(0, MSGERRLEN) + " ..." : errMessg;

			throw new WebServiceException(
					"CHECK - Dymola check failed \n\t remote working directory "
							+ workingDirectory + "\n\t fileName: " + inputFileName + "\n\t problem:"
							+ problem
							+ "\n\t error message:" + errMessg,
					e);
		}
		finally
		{
			try
			{
				if (!debug) FileUtils.deleteDirectory(dymSimulationDir);
			}
			catch (IOException e)
			{
				LOG.error(String.format("CHECK - Error deleting simulation files from %s",
						dymSimulationDir));
			}
		}
	}

	public @XmlMimeType("application/octet-stream") DataHandler simulate(
			String workingDirectory,
			String inputFileName,
			String problem,
			double startTime,
			double stopTime,
			int numberOfIntervals,
			double outputInterval,
			double tolerance,
			String[] methodList,
			String resultsFileName,
			String resultVariables,
			boolean createFilteredMat,
			@XmlMimeType("application/octet-stream") DataHandler data)
	{
		Path dymSimulationDir = Paths.get(this.serviceWorkDir).resolve(workingDirectory)
				.resolve(problem);

		METHOD_LIST = methodList;
		try
		{
			Path inputFile;
			try (StreamingDataHandler inputDh = (StreamingDataHandler) data)
			{
				if (!Files.exists(dymSimulationDir))
				{
					dymSimulationDir = Files.createDirectories(dymSimulationDir);
				}
				inputFile = dymSimulationDir.resolve(inputFileName);
				inputDh.moveTo(inputFile.toFile());
			}
			LOG.info(
					"SIMULATION - Dymola simulation started \n\t workspace : {} \n\t inputFileName:{} \n\t problem:{} \n\t startTime:{} \n\t stopTime:{} \n\t numberOfIntervals:{} \n\t outputInterval:{} \n\t tolerance:{} \n\t resultsFileName:{}.",
					dymSimulationDir, inputFileName, problem, startTime, stopTime,
					numberOfIntervals,
					outputInterval, tolerance, resultsFileName);

			boolean result = dymola.cd(dymSimulationDir.getParent().toAbsolutePath().toString());

			if (!result)
			{
				LOG.error(String.format(
						"SIMULATION - Error setting the working directory %s. \n\t Reason is %s.",
						dymSimulationDir.getParent().toAbsolutePath().toString(),
						dymola.getLastError()));
			}

			boolean loaded = dymola.openModel(inputFile.toAbsolutePath().toString());
			if (!loaded)
			{
				LOG.error(String.format("SIMULATION - Error opening model %s in directory %s.",
						inputFile, dymSimulationDir));
			}

			result = checkDymola(dymSimulationDir, inputFileName, problem, resultsFileName);
			if (!result)
			{
				LOG.error("SIMULATION  - Error checking model {}. Reason is {}.", problem,
						dymola.getLastError());
			}
			else
			{
				LOG.info("SIMULATION - Model {} checked successfully.", problem);
			}

			simulateDymola(dymSimulationDir, inputFileName, problem, startTime, stopTime,
					numberOfIntervals, outputInterval, tolerance, resultsFileName,
					resultVariables, createFilteredMat);

			result = dymola.savelog(DYMOLA_LOG_FILENAME);

			DataHandler outputFileDataHandler = prepareOutputData(dymSimulationDir,
					resultsFileName);

			return outputFileDataHandler;
		}
		catch (Exception e)
		{
			LOG.error(
					"SIMULATION - dymola simulation failed - workspace:{} \n inputFileName:{} \n problem:{} \n startTime:{} \n stopTime:{} \n numberOfIntervals:{} \n outputInterval:{} \n tolerance:{} \n resultsFileName:{}",
					dymSimulationDir, inputFileName, problem, startTime, stopTime,
					numberOfIntervals,
					outputInterval, tolerance, resultsFileName, e);

			String errMessg = e.getMessage();
			errMessg = ((errMessg != null) && (errMessg.length() > MSGERRLEN))
					? errMessg.substring(0, MSGERRLEN) + " ..." : errMessg;

			throw new WebServiceException("Dymola simulation failed - remote working directory "
					+ dymSimulationDir + " \n\t fileName: " + inputFileName + "\n\t problem:"
					+ problem
					+ "\n\t error message:" + errMessg, e);
		}
		finally
		{
			try
			{
				if (!debug) FileUtils.deleteDirectory(dymSimulationDir);
			}
			catch (IOException e)
			{
				LOG.error("Error deleting simulation files from {}", dymSimulationDir);
			}
		}
	}

	private DataHandler prepareOutputData(Path workingDir, String resultsFileName)
			throws IOException, URISyntaxException
	{
		String outputZipFile = workingDir.getFileName() + ".zip";
		Map<String, String> fileNamesToInclude = MapUtils.asUnmodifiableMap(
				entry("log.txt", resultsFileName + "_log.txt"),
				entry("dslog.txt", resultsFileName + "_dslog.txt"),
				entry(resultsFileName + "_filtered.mat", resultsFileName + "_filtered.mat"),
				entry(resultsFileName + ".csv", resultsFileName + ".csv"));

		prepareOutputFile(workingDir, fileNamesToInclude, Paths.get(outputZipFile));

		TemporaryFileDataSource outDataSource = new TemporaryFileDataSource(
				Paths.get(outputZipFile).toFile());
		DataHandler outputFileDataHandler = new DataHandler(outDataSource);

		LOG.info(
				" SIMULATION - Dymola simulation terminated.",
				workingDir);

		return outputFileDataHandler;
	}

	protected boolean checkDymola(Path workingDirectory, String inputFileName, String problem,
			String resultsFileName)
	{
		boolean result = false;
		try
		{
			result = dymola.checkModel(problem);
			if (!result)
			{
				LOG.error("Error checking model {}. Reason is {}.", problem,
						dymola.getLastError());
			}
		}
		catch (Exception e)
		{
			LOG.error(
					" {} - Dymola checking failed - problem:{} : {}",
					workingDirectory, problem, e);
		}
		return result;
	}

	protected void simulateDymola(Path workingDirectory, String inputFileName, String problem,
			double startTime, double stopTime, int numberOfIntervals, double outputInterval,
			double tolerance, String resultsFileName, String resultVariables,
			boolean createFilteredMat) throws DymolaException
	{

		boolean result = dymola.cd(workingDirectory.toAbsolutePath().toString());
		if (!result)
		{
			LOG.error("Error setting the working directory {}. Reason is {}.",
					workingDirectory, dymola.getLastError());
		}

		result = dymola.openModel(workingDirectory + File.separator + inputFileName);
		if (!result)
		{
			throw new RuntimeException("openModel: " + dymola.getLastError());
		}
		
		boolean check = dymola.checkModel(problem);
		if (!check)
		{
			throw new RuntimeException("checkModel: " + dymola.getLastError());
		}

		// Simulate the model
		simulateModel(problem, startTime, stopTime, numberOfIntervals, tolerance, outputInterval,
				resultsFileName);

		String matResultsFile = resultsFileName + StaticData.MAT_EXTENSION;
		String csvResultsFile = resultsFileName + StaticData.CSV_EXTENSION;

		int resultSize = dymola.readTrajectorySize(matResultsFile);
		String[] resultNames = dymola.readTrajectoryNames(matResultsFile);

		Pattern pattern = Pattern.compile(resultVariables);
		Matcher matcher = pattern.matcher(Arrays.toString(resultNames));
		Stream<String> filterVarsStream = Stream.of(resultNames)
				.filter(el -> pattern.matcher(el).matches());

		String[] filterResultVariables = new String[filterVarsStream.toArray().length + 1];
		filterResultVariables[0] = "Time";
		boolean onlyTime = true;

		int j = 1;
		for (int i = 0; i < resultNames.length; i++)
		{
			matcher = pattern.matcher(resultNames[i]);
			if (matcher.matches())
			{
				filterResultVariables[j] = matcher.group();
				onlyTime = false;
				j++;
			}
		}

		// Check if filterResultVariables only has the variable Time.
		if (onlyTime)
		{
			filterResultVariables = resultNames;
		}

		try (PrintStream printStream = new PrintStream(Files
				.newOutputStream(Paths.get(workingDirectory + File.separator + csvResultsFile))))
		{
			writeResults(printStream, matResultsFile, filterResultVariables,
					resultSize, createFilteredMat, resultsFileName);
		}
		catch (IOException e)
		{
			LOG.error("Error printing errors file. {}", e.getMessage());
		}

		// Create a .mat file with filtered variables.
		String matResultsFileFiltered = resultsFileName + "_filtered" + StaticData.MAT_EXTENSION;
		double[][] trajVarsValues = dymola.readTrajectory(matResultsFile, filterResultVariables,
				resultSize);
		dymola.writeTrajectory(matResultsFileFiltered, filterResultVariables,
				Utils.transpose().apply(trajVarsValues));

		result = dymola.savelog(DYMOLA_LOG_FILENAME);
		if (!result)
		{
			throw new RuntimeException("saveLog: " + dymola.getLastError());
		}
	}

	private void simulateModel(String modelName, double startTime, double stopTime,
			int numOfIntervals, double tolerance, double outputInterval, String resultsFileName)
			throws DymolaException
	{
		// Simulate the model
		boolean result = false;
		int i = 0;
		while ((result == false) && (i < METHOD_LIST.length))
		{
			result = dymola.simulateModel(modelName, startTime, stopTime, numOfIntervals,
					outputInterval, METHOD_LIST[i], tolerance, 0, resultsFileName);

			if (!result)
			{
				LOG.error("Error simulating model {} with integration method {}.", modelName,
						METHOD_LIST[i]);
				throw new RuntimeException(
						"Error simulating model " + modelName + " with integration method "
								+ METHOD_LIST[i] + ". " + dymola.getLastError());
			}
			i++;
			
			// For now only one of the methods indicated is used
			i = METHOD_LIST.length;
		}
	}

	protected void prepareOutputFile(Path workingDir, Map<String, String> fileNamesMap,
			Path outFile) throws IOException, URISyntaxException
	{
		Map<String, String> zip_properties = new HashMap<>();
		zip_properties.put("create", "true");
		zip_properties.put("encoding", "UTF-8");
		URI zip_disk = new URI("jar", outFile.toAbsolutePath().toUri().toString(), null);
		try (FileSystem zipfs = FileSystems.newFileSystem(zip_disk, zip_properties))
		{
			for (String filename : fileNamesMap.keySet())
			{
				Path sourcePath = workingDir.resolve(filename);
				if (Files.exists(sourcePath))
				{
					Files.copy(workingDir.resolve(filename),
							zipfs.getPath(fileNamesMap.get(filename)));
				}
				else
				{
					LOG.warn(
							"file " + sourcePath + " does not exist. It won't be in " + outFile);
				}
			}
		}
	}

	private void writeResults(PrintStream printStream,
			String matResultsFile, String[] filterResultVariables, int resultSize,
			boolean createFitleredMat, String resultsFileName) throws DymolaException
	{
		double[][] resultValues = new double[resultSize][filterResultVariables.length];
		String strLine = Stream.of(filterResultVariables).collect(Collectors.joining(","))
				.toString();
		printStream.println(strLine);

		for (int j = 0; j < filterResultVariables.length; j++)
		{
			String[] resVar = new String[1];
			resVar[0] = filterResultVariables[j];
			double[][] trajVarsValues;
			try
			{
				strLine = resVar[0];
				trajVarsValues = dymola.readTrajectory(matResultsFile, resVar, resultSize);

				if (trajVarsValues != null)
				{
					for (int i = 0; i < resultSize; i++)
					{
						resultValues[i][j] = trajVarsValues[0][i];
						strLine = strLine + "," + trajVarsValues[0][i];
					}
				}
				else
				{
					LOG.error("Error getting simulation result variable {} from {}.", resVar,
							matResultsFile);
				}
			}
			catch (DymolaException e)
			{
				LOG.error("Error getting simulation result variable {} from {}.", resVar,
						matResultsFile);
			}
		}

		for (int i = 0; i < resultValues.length; i++)
		{
			for (int k = 0; k < resultValues[i].length; k++)
			{
				if (k == 0) strLine = String.valueOf(resultValues[i][k]);
				else strLine = strLine + "," + resultValues[i][k];
			}
			printStream.println(strLine);
		}

		// Create a .mat file with filtered variables.
		if (createFitleredMat)
		{
			String matResultsFileFiltered = resultsFileName + "_filtered"
					+ StaticData.MAT_EXTENSION;
			double[][] trajVarsValues;
			trajVarsValues = dymola.readTrajectory(matResultsFile, filterResultVariables,
					resultSize);
			dymola.writeTrajectory(matResultsFileFiltered, filterResultVariables,
					Utils.transpose().apply(trajVarsValues));
		}
	}

	private class TemporaryFileDataSource extends FileDataSource
	{

		public TemporaryFileDataSource(File file)
		{
			super(file);
		}

		@Override
		public InputStream getInputStream() throws IOException
		{
			return new TemporaryFileInputStream(this.getFile());
		}

	}

	private class TemporaryFileInputStream extends FileInputStream
	{
		File file;

		public TemporaryFileInputStream(File file) throws FileNotFoundException
		{
			super(file);
			this.file = file;
		}

		@Override
		public void close() throws IOException
		{
			super.close();
			boolean isDeleted = file.delete();
			LOG.trace(" **** " + file.getAbsoluteFile() + " :" + isDeleted);
		}
	}

	public void close()
	{
		try
		{
			if (this.dymola != null)
			{
				this.dymola.close();
				this.dymola = null;
			}
		}
		catch (Exception e)
		{
			LOG.error("Dymola server has not been closed successfuly.");
		}
	}

	boolean						debug;

	final String				serviceWorkDir;
	private DymolaInterface		dymola						= null;
	final Pool<Integer>			portPool;
	private int					port						= -1;

	static final String			DYMOLASERVICE_TEMP			= "/temp/Dymola/server";
	static final String			DYMOLASERVICE_INPUTFILENAME	= "dymola_input.zip";
	static final String			DYMSERV_SIM_PREFIX			= "dymserv_sim_";
	private static final String	DYMOLA_LOG_FILENAME			= "log.txt";
	private static final int	MSGERRLEN					= 400;
	private String[]			METHOD_LIST;

	private static final Logger	LOG							= LoggerFactory
			.getLogger(SimulatorServerImpl.class);
}
