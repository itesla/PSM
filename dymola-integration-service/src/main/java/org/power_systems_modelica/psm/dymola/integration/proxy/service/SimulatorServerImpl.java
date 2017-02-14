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
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

import org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.MapUtils;
import org.power_systems_modelica.psm.dymola.integration.proxy.service.utils.Pool;
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
 *
 * @author Quinary <itesla@quinary.com>
 */
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
		LOGGER.info("Dymola proxy service");
		LOGGER.info(" API version: {}", DymolaWrapper.dymola_version);
		LOGGER.info(" service working directory: {}", serviceWorkDir);
		LOGGER.info(" ports pool starting from {}, size {}", start, size);
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
				LOGGER.error("Error getting port item. Reasoin is {}.", e.getCause());
				e.printStackTrace();
			}
			dymola = DymolaWrapper.getInstance(true, port);
			LOGGER.debug(
					"   START Dymola - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ",
					((DymolaWrapper) dymola).portnumber, Thread.currentThread().getId(),
					dymola.hashCode(), this.hashCode());
		}
		catch (Exception e)
		{
			LOGGER.error(" Error instantiating Dymola. Reason is {}.", e.getCause());
		}
	}

	public @XmlMimeType("application/octet-stream") DataHandler check(
			String inputFileName,
			String problem,
			String resultsFileName,
			@XmlMimeType("application/octet-stream") DataHandler data)
	{
		Path workingDir = null;
		String outputZipFile = null;
		try
		{
			Instant startms = Instant.now();
			Path inputZipFile;
			try (StreamingDataHandler inputDh = (StreamingDataHandler) data)
			{
				Files.createDirectories(Paths.get(serviceWorkDir));
				workingDir = Files.createTempDirectory(Paths.get(serviceWorkDir),
						DYMSERV_SIM_PREFIX);
				Files.createDirectories(workingDir);
				inputZipFile = workingDir.resolve(DYMOLASERVICE_INPUTFILENAME);
				inputDh.moveTo(inputZipFile.toFile());
			}
			try (ZipFile zipFile = new ZipFile(inputZipFile.toFile()))
			{
				ZipFileUtil.unzipFileIntoDirectory(zipFile, workingDir.toFile());
			}
			LOGGER.info(
					" {} - dymola checking started - inputFileName:{}, problem:{}, resultsFileName:{}.",
					workingDir, inputFileName, problem, resultsFileName);

			// Move to working directory, load all needed Modelica files.
			prepareDymola(workingDir, inputFileName);

			checkDymola(workingDir, inputFileName, problem, resultsFileName);

			boolean result = dymola.savelog(DYMOLA_LOG_FILENAME);

			DataHandler outputFileDataHandler = prepareOutputData(workingDir, resultsFileName);

			return outputFileDataHandler;
		}
		catch (Exception e)
		{
			LOGGER.error(
					" {} - dymola checking failed - inputFileName:{}, problem:{}, resultsFileName:{}",
					workingDir, inputFileName, problem, resultsFileName, e);

			String errMessg = e.getMessage();
			errMessg = ((errMessg != null) && (errMessg.length() > MSGERRLEN))
					? errMessg.substring(0, MSGERRLEN) + " ..." : errMessg;

			throw new WebServiceException("dymola simulation failed - remote working directory "
					+ workingDir + ", fileName: " + inputFileName + ", problem:" + problem
					+ ", error message:" + errMessg, e);
		}
		finally
		{
			try
			{
				if (!debug) Utils.deleteDirectoryRecursively(workingDir);
			}
			catch (IOException e)
			{
				LOGGER.error("Error deleting simulation files from {}", workingDir);
			}
		}
	}

	public @XmlMimeType("application/octet-stream") DataHandler simulate(String inputFileName,
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
		Path workingDir = null;
		METHOD_LIST = methodList;
		try
		{
			Instant startms = Instant.now();
			Path inputZipFile;
			try (StreamingDataHandler inputDh = (StreamingDataHandler) data)
			{
				Files.createDirectories(Paths.get(serviceWorkDir));
				workingDir = Files.createTempDirectory(Paths.get(serviceWorkDir),
						DYMSERV_SIM_PREFIX);
				Files.createDirectories(workingDir);
				inputZipFile = workingDir.resolve(DYMOLASERVICE_INPUTFILENAME);
				inputDh.moveTo(inputZipFile.toFile());
			}
			try (ZipFile zipFile = new ZipFile(inputZipFile.toFile()))
			{
				ZipFileUtil.unzipFileIntoDirectory(zipFile, workingDir.toFile());
			}
			LOGGER.info(
					" {} - dymola simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, tolerance:{}, resultsFileName:{}.",
					workingDir, inputFileName, problem, startTime, stopTime, numberOfIntervals,
					outputInterval, tolerance, resultsFileName);

			prepareDymola(workingDir, inputFileName);

			checkDymola(workingDir, inputFileName, problem, resultsFileName);

			simulateDymola(workingDir, inputFileName, problem, startTime, stopTime,
					numberOfIntervals, outputInterval, tolerance, resultsFileName,
					resultVariables, createFilteredMat);

			boolean result = dymola.savelog(DYMOLA_LOG_FILENAME);

			DataHandler outputFileDataHandler = prepareOutputData(workingDir, resultsFileName);

			return outputFileDataHandler;
		}
		catch (Exception e)
		{
			LOGGER.error(
					" {} - dymola simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, tolerance:{}, resultsFileName:{}",
					workingDir, inputFileName, problem, startTime, stopTime, numberOfIntervals,
					outputInterval, tolerance, resultsFileName, e);

			String errMessg = e.getMessage();
			errMessg = ((errMessg != null) && (errMessg.length() > MSGERRLEN))
					? errMessg.substring(0, MSGERRLEN) + " ..." : errMessg;

			throw new WebServiceException("dymola simulation failed - remote working directory "
					+ workingDir + ", fileName: " + inputFileName + ", problem:" + problem
					+ ", error message:" + errMessg, e);
		}
		finally
		{
			try
			{
				if (!debug) Utils.deleteDirectoryRecursively(workingDir);
			}
			catch (IOException e)
			{
				LOGGER.error("Error deleting simulation files from {}", workingDir);
			}
		}
	}

	private void prepareDymola(Path workingDir, String inputFileName) throws DymolaException
	{
		boolean result = dymola.clear();
//		 if (!result)
//		 {
//		 LOGGER.error("Error clearing workspace: {}.", dymola.getLastError());
//		 }
		// TODO

		result = dymola.cd(workingDir.toAbsolutePath().toString());
		if (!result)
		{
			LOGGER.error("Error setting the working directory {}. Reason is {}.",
					workingDir, dymola.getLastError());
		}

		result = dymola.openModel(workingDir + File.separator + inputFileName);
		if (!result)
		{
			LOGGER.error("Error opening model {}.", dymola.getLastError());
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
				entry(resultsFileName + "_filtered.csv", resultsFileName + "_filtered.csv"));

		prepareOutputFile(workingDir, fileNamesToInclude, Paths.get(outputZipFile));

		TemporaryFileDataSource outDataSource = new TemporaryFileDataSource(
				Paths.get(outputZipFile).toFile());
		DataHandler outputFileDataHandler = new DataHandler(outDataSource);

		LOGGER.info(
				" {} - dymola simulation terminated.",
				workingDir);

		return outputFileDataHandler;
	}

	protected void checkDymola(Path workingDirectory, String inputFileName, String problem,
			String resultsFileName)
	{
		try
		{
			boolean result = dymola.checkModel(problem);
			if (!result)
			{
				LOGGER.error("Error checking model {}. Reason is {}.", problem,
						dymola.getLastError());
			}
		}
		catch (Exception e)
		{
			LOGGER.error(
					" {} - openmodelica validation failed - problem:{} : {}",
					workingDirectory, problem, e);
		}
	}

	protected void simulateDymola(Path workingDirectory, String inputFileName, String problem,
			double startTime, double stopTime, int numberOfIntervals, double outputInterval,
			double tolerance, String resultsFileName, String resultVariables,
			boolean createFilteredMat) throws DymolaException
	{

		boolean result = false;
		result = dymola.clear();
//		 if (!result)
//		 {
//		 throw new RuntimeException("Error clearing workspace : " + dymola.getLastError());
//		 }

		result = dymola.cd(workingDirectory.toAbsolutePath().toString());
		if (!result)
		{
			LOGGER.error("Error setting the working directory {}. Reason is {}.",
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

		String matResultsFile = resultsFileName + MAT_EXTENSION;
		String csvResultsFile = resultsFileName + "_filtered" + CSV_EXTENSION;

		int resultSize = dymola.readTrajectorySize(matResultsFile);
		String[] resultNames = dymola.readTrajectoryNames(matResultsFile);

		Pattern pattern = Pattern.compile(resultVariables);
		Matcher matcher = pattern.matcher(Arrays.toString(resultNames));
		int count = 0;
		for (int i = 0; i < resultNames.length; i++)
		{
			if (pattern.matcher(resultNames[i]).matches()) count++;
		}
		String[] filterResultVariables = new String[count + 1];
		filterResultVariables[0] = "Time";

		int j = 1;
		for (int i = 0; i < resultNames.length; i++)
		{
			matcher = pattern.matcher(resultNames[i]);
			if (matcher.matches())
			{
				filterResultVariables[j] = matcher.group();
				j++;
			}
		}

		// Check if filterResultVariables only has the variable Time.
		if (filterResultVariables[1] == null)
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
			LOGGER.error("Error printing errors file. {}", e.getMessage());
		}

		// Create a .mat file with filtered variables.
		String matResultsFileFiltered = resultsFileName + "_filtered" + MAT_EXTENSION;
		double[][] trajVarsValues = dymola.readTrajectory(matResultsFile, filterResultVariables,
				resultSize);
		dymola.writeTrajectory(matResultsFileFiltered, filterResultVariables,
				Utils.transpose().apply(trajVarsValues));

		result = dymola.savelog(DYMOLA_LOG_FILENAME);
		if (!result)
		{
			throw new RuntimeException("saveLog: " + dymola.getLastError());
		}

		LOGGER.error(
				"   END {} - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ",
				workingDirectory, ((DymolaWrapper) dymola).portnumber,
				Thread.currentThread().getId(), dymola.hashCode(), this.hashCode());
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
				LOGGER.error("Error simulating model {} with integration method {}.", modelName,
						METHOD_LIST[i]);
				throw new RuntimeException(
						"Error simulating model " + modelName + " with integration method "
								+ METHOD_LIST[i] + ". " + dymola.getLastError());
			}
			i++;
		}
	}

	protected void prepareOutputFile(Path workingDir, Map<String, String> fileNamesMap,
			Path outFile) throws IOException, URISyntaxException
	{
		Map<String, String> zip_properties = new HashMap<>();
		zip_properties.put("create", "true");
		zip_properties.put("encoding", "UTF-8");
		URI zip_disk = new URI("jar", outFile.toUri().toString(), null);
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
					LOGGER.warn(
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
					LOGGER.error("Error getting simulation result variable {} from {}.", resVar,
							matResultsFile);
				}
			}
			catch (DymolaException e)
			{
				LOGGER.error("Error getting simulation result variable {} from {}.", resVar,
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
			String matResultsFileFiltered = resultsFileName + "_filtered" + MAT_EXTENSION;
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
			LOGGER.trace(" **** " + file.getAbsoluteFile() + " :" + isDeleted);
		}
	}
	
	public void close() {
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
			System.out.println("Dymola server has not been closed successfuly.");
		}
	}

	boolean						debug;

	final String				serviceWorkDir;
	private DymolaInterface		dymola						= null;
	final Pool<Integer>			portPool;
	private int					port						= -1;

	static final String			DYMOLASERVICE_TEMP			= "/temp/Dymola/server";
	static final String			DYMOLASERVICE_INPUTFILENAME	= "dyninput.zip";
	static final String			DYMSERV_SIM_PREFIX			= "dymserv_sim_";
	private static final String	DYMOLA_LOG_FILENAME			= "log.txt";
	private static final int	MSGERRLEN					= 400;

	private static final String	MAT_EXTENSION				= ".mat";
	private static final String	CSV_EXTENSION				= ".csv";
	private String[]			METHOD_LIST;

	private static final Logger	LOGGER						= LoggerFactory
			.getLogger(SimulatorServerImpl.class);
}