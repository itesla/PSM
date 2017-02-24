/**
 * Copyright (c) 2016, All partners of the iTesla project (http://www.itesla-project.eu/consortium)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.power_systems_modelica.psm.dymola.integration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.MTOMFeature;

import org.power_systems_modelica.psm.dymola.integration.service.SimulatorServer;
import org.power_systems_modelica.psm.dymola.integration.service.SimulatorServerImplService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.internal.ws.developer.JAXWSProperties;
import com.sun.xml.internal.ws.developer.StreamingDataHandler;

/**
 *
 * @author Quinary <itesla@quinary.com>
 */
public class StandaloneDymolaClient
{
	public StandaloneDymolaClient(Path workingDir, String inputFileName, String outputFileName,
			String outputDymolaFileName, boolean createFilteredMat,
			String[] methodList, String wsdlService, String resultVariables)
	{
		this.workingDirectory = workingDir;
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.outputDymolaFileName = outputDymolaFileName;
		this.createFilteredMat = createFilteredMat;
		this.wsdlService = wsdlService;
		this.resultVariables = resultVariables;
		this.methodList = methodList;
	}
	
	protected String prepareDynamicEnvironment(Path workingDirectory) {
		Path finalWorkingDir = workingDirectory.resolve("library"); 
		if(Files.notExists(finalWorkingDir)) {
			try
			{
				Files.createDirectory(finalWorkingDir);
			}
			catch (IOException e)
			{
				LOGGER.error(String.format("Error creating working directory %s.", finalWorkingDir));
				return e.toString();
			}
		}
		
		Path pathIn = finalWorkingDir.resolve(this.inputFileName);
		Path pathOut = finalWorkingDir.resolve(this.outputFileName + ".zip");
		String retCode = "";
		RetryOnExceptionStrategy retry = new RetryOnExceptionStrategy(TRIES, 2000);
		LOGGER.info(" - invoking remote dymola proxy service");
		while (retry.shouldRetry())
		{
			try
			{
				SimulatorServerImplService service = new SimulatorServerImplService(
						new URL(wsdlService));
				SimulatorServer sport = service.getSimulatorServerImplPort(new MTOMFeature());
				((BindingProvider) sport).getRequestContext().put(JAXWSProperties.CONNECT_TIMEOUT,
						CONNECTION_TIMEOUT);
				((BindingProvider) sport).getRequestContext()
						.put("javax.xml.ws.client.connectionTimeout", CONNECTION_TIMEOUT);
				((BindingProvider) sport).getRequestContext().put(JAXWSProperties.REQUEST_TIMEOUT,
						REQUEST_TIMEOUT);
				((BindingProvider) sport).getRequestContext()
						.put("javax.xml.ws.client.receiveTimeout", REQUEST_TIMEOUT);
				DataHandler dhin = new DataHandler(new FileDataSource(pathIn.toFile()));
				DataHandler dhout = sport.prepareDynamicEnvironment(
						workingDirectory.getFileName().toString(),
						outputFileName,
						dhin);
				StreamingDataHandler sdh = (StreamingDataHandler) dhout;
				LOGGER.info(
						" - remote dymola proxy service ended successfully, retrieving validation output");
				sdh.moveTo(pathOut.toFile());
				sdh.close();
				LOGGER.info(" - validation output retrieved");
				break;
			}
			catch (Exception e)
			{
				try
				{
					LOGGER.warn(" - retry ... ({})", e.getMessage());
					retry.errorOccured(e);
				}
				catch (RuntimeException e1)
				{
					LOGGER.error(" - remote dymola proxy service ended unsuccessfully", e);
					retCode = e.toString();
				}
				catch (Exception e1)
				{
					LOGGER.error(" - remote dymola proxy service ended unsuccessfully", e);
					// retCode= Throwables.getRootCause(e).toString();
					retCode = e1.toString();
				}
			}
		}

		return retCode;
	}

	protected String check(Path workingDirectory, String modelName, String modelFileName, String outputDymolaFileName)
			throws Exception
	{
		if(Files.notExists(workingDirectory.resolve(modelName))) {
			Files.createDirectory(workingDirectory.resolve(modelName));
		}
		Path finalWorkingDir = workingDirectory.resolve(modelName); 
		Path pathIn = finalWorkingDir.resolve(modelFileName);
		Path pathOut = finalWorkingDir.resolve(modelName + "_check.zip");
		String retCode = "";
		RetryOnExceptionStrategy retry = new RetryOnExceptionStrategy(TRIES, 2000);
		LOGGER.info(" - invoking remote dymola proxy service");
		while (retry.shouldRetry())
		{
			try
			{
				SimulatorServerImplService service = new SimulatorServerImplService(
						new URL(wsdlService));
				SimulatorServer sport = service.getSimulatorServerImplPort(new MTOMFeature());
				((BindingProvider) sport).getRequestContext().put(JAXWSProperties.CONNECT_TIMEOUT,
						CONNECTION_TIMEOUT);
				((BindingProvider) sport).getRequestContext()
						.put("javax.xml.ws.client.connectionTimeout", CONNECTION_TIMEOUT);
				((BindingProvider) sport).getRequestContext().put(JAXWSProperties.REQUEST_TIMEOUT,
						REQUEST_TIMEOUT);
				((BindingProvider) sport).getRequestContext()
						.put("javax.xml.ws.client.receiveTimeout", REQUEST_TIMEOUT);
				DataHandler dhin = new DataHandler(new FileDataSource(pathIn.toFile()));
				DataHandler dhout = sport.check(
						workingDirectory.getFileName().toString(),
						modelFileName,
						modelName,
						outputDymolaFileName,
						dhin);
				StreamingDataHandler sdh = (StreamingDataHandler) dhout;
				LOGGER.info(
						" - remote dymola proxy service ended successfully, retrieving validation output");
				sdh.moveTo(pathOut.toFile());
				sdh.close();
				LOGGER.info(" - validation output retrieved");
				break;
			}
			catch (Exception e)
			{
				try
				{
					LOGGER.warn(" - retry ... ({})", e.getMessage());
					retry.errorOccured(e);
				}
				catch (RuntimeException e1)
				{
					LOGGER.error(" - remote dymola proxy service ended unsuccessfully", e);
					retCode = e.toString();
				}
				catch (Exception e1)
				{
					LOGGER.error(" - remote dymola proxy service ended unsuccessfully", e);
					// retCode= Throwables.getRootCause(e).toString();
					retCode = e1.toString();
				}
			}
		}

		return retCode;
	}

	protected String verify(Path workingDirectory, String modelName, String modelFileName, double startTime,
			double stopTime,
			int numOfIntervals, double intervalSize, double tolerance, String outputDymolaFileName) throws Exception
	{
		String retCode = simulate(workingDirectory, modelName, modelFileName, startTime, stopTime, numOfIntervals,
				intervalSize, tolerance, outputDymolaFileName);

		return retCode;
	}

	protected String simulate(Path workingDirectory, String modelName, String modelFileName, double startTime,
			double stopTime, int numOfIntervals, double intervalSize, double tolerance, String outputDymolaFileName)
			throws Exception
	{
		if(Files.notExists(workingDirectory.resolve(modelName))) {
			Files.createDirectory(workingDirectory.resolve(modelName));
		}
		Path finalWorkingDir = workingDirectory.resolve(modelName); 
		Path pathIn = finalWorkingDir.resolve(modelFileName);
		Path pathOut = finalWorkingDir.resolve(modelName + ".zip");
		
//		Path pathIn = workingDirectory.resolve(inputFileName);
//		Path pathOut = workingDirectory.resolve(modelName);
		String retCode = "";
		RetryOnExceptionStrategy retry = new RetryOnExceptionStrategy(TRIES, 2000);
		LOGGER.info(" - invoking remote dymola proxy service");
		while (retry.shouldRetry())
		{
			try
			{
				SimulatorServerImplService service = new SimulatorServerImplService(
						new URL(wsdlService));
				SimulatorServer sport = service.getSimulatorServerImplPort(new MTOMFeature());
				((BindingProvider) sport).getRequestContext().put(JAXWSProperties.CONNECT_TIMEOUT,
						CONNECTION_TIMEOUT);
				((BindingProvider) sport).getRequestContext()
						.put("javax.xml.ws.client.connectionTimeout", CONNECTION_TIMEOUT);
				((BindingProvider) sport).getRequestContext().put(JAXWSProperties.REQUEST_TIMEOUT,
						REQUEST_TIMEOUT);
				((BindingProvider) sport).getRequestContext()
						.put("javax.xml.ws.client.receiveTimeout", REQUEST_TIMEOUT);
				DataHandler dhin = new DataHandler(new FileDataSource(pathIn.toFile()));
				DataHandler dhout = sport.simulate(
						workingDirectory.getFileName().toString(),
						modelFileName,
						modelName,
						startTime,
						stopTime,
						numOfIntervals,
						intervalSize,
						tolerance,
						methodList,
						outputDymolaFileName,
						resultVariables,
						createFilteredMat,
						dhin);
				StreamingDataHandler sdh = (StreamingDataHandler) dhout;
				LOGGER.info(
						" - remote dymola proxy service ended successfully, retrieving simulation output");

				sdh.moveTo(pathOut.toFile());
				sdh.close();
				LOGGER.info(" - simulation output retrieved");
				break;
			}
			catch (Exception e)
			{
				try
				{
					LOGGER.warn(" - retry ... ({})", e.getMessage());
					retry.errorOccured(e);
				}
				catch (Exception e1)
				{
					LOGGER.error(" - remote dymola proxy service ended unsuccessfully", e);
					throw e;
				}
			}
		}

		return retCode;
	}

	@Override
	public String toString()
	{
		return "dymola client {" +
				", wsdlService='" + wsdlService + '\'' +
				", workingDirectory=" + workingDirectory +
				", inputFileName=" + inputFileName +
				", outputDymolaFileName=" + outputDymolaFileName +
				", resultVariables=" + resultVariables +
				", methodList=" + methodList +
				", createFilteredMat=" + createFilteredMat +
				'}';
	}

	String						wsdlService;
	private Path				workingDirectory;
	private String				inputFileName;
	private String				outputFileName;
	private String				outputDymolaFileName;
	private String				resultVariables;
	private String[]			methodList;
	private boolean				createFilteredMat	= false;

	private static final int	TRIES				= 1;					// number of soap remote service attempts, before giving up
	private static final int	CONNECTION_TIMEOUT	= 4 * 60 * 60 * 1000;	// in milliseconds
	private static final int	REQUEST_TIMEOUT		= 4 * 60 * 60 * 1000;	// in milliseconds
	private static final Logger	LOGGER				= LoggerFactory
			.getLogger(StandaloneDymolaClient.class);
}
