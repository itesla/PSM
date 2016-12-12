/**
 * Copyright (c) 2016, All partners of the iTesla project (http://www.itesla-project.eu/consortium)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.power_systems_modelica.psm.dymola.integration;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

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
public class StandaloneDymolaClient {

    public StandaloneDymolaClient(String[] methodList, int numberOfIntervals, double outputInterval, double startTime, double stopTime, double tolerance, String wsdlService, String resultVariables) {
        this.numberOfIntervals = numberOfIntervals;
        this.outputInterval = outputInterval;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.tolerance = tolerance;
        this.wsdlService = wsdlService;
        this.resultVariables = resultVariables;
    }

    protected String runDymola(Path workingDir, String inputFileName, String outputFileName, String modelFileName, String modelName, String resultsFileName, boolean createFilteredMat) throws InterruptedException{
        Path pathIn = workingDir.resolve(inputFileName);
        Path pathOut = workingDir.resolve(outputFileName);
        String retCode="";
        RetryOnExceptionStrategy retry = new RetryOnExceptionStrategy(TRIES,2000);
        LOG.info(" - invoking remote dymola proxy service");
        while (retry.shouldRetry()) {
            try {
                SimulatorServerImplService service = new SimulatorServerImplService(new URL(wsdlService));
                SimulatorServer sport = service.getSimulatorServerImplPort(new MTOMFeature());
                ((BindingProvider) sport).getRequestContext().put(JAXWSProperties.CONNECT_TIMEOUT, CONNECTION_TIMEOUT);
                ((BindingProvider) sport).getRequestContext().put("javax.xml.ws.client.connectionTimeout", CONNECTION_TIMEOUT);
                ((BindingProvider) sport).getRequestContext().put(JAXWSProperties.REQUEST_TIMEOUT, REQUEST_TIMEOUT);
                ((BindingProvider) sport).getRequestContext().put("javax.xml.ws.client.receiveTimeout", REQUEST_TIMEOUT);
                DataHandler dhin = new DataHandler(new FileDataSource(pathIn.toFile()));
                DataHandler dhout = sport.simulate(modelFileName, 
                									modelName, 
                									startTime, 
                									stopTime, 
                									numberOfIntervals, 
                									outputInterval,  
                									tolerance,
                									resultsFileName, 
                									resultVariables,
                									createFilteredMat,
                									dhin);
                
                StreamingDataHandler sdh = (StreamingDataHandler) dhout;
                LOG.info(" - remote dymola proxy service ended successfully, retrieving simulation output");

                sdh.moveTo(pathOut.toFile());
                sdh.close();
                LOG.info(" - simulation output retrieved");
                break;
            } catch (Exception e) {
                try {
                    LOG.warn(" - retry ... ({})", e.getMessage());
                    retry.errorOccured(e);
                } catch (RuntimeException e1) {
                    LOG.error(" - remote dymola proxy service ended unsuccessfully", e);
                    retCode = e.toString();
                } catch (Exception e1) {
                    LOG.error(" - remote dymola proxy service ended unsuccessfully", e);
//                    retCode= Throwables.getRootCause(e).toString();
                    retCode = e1.toString();
                }
            }
        }
        
        return retCode;
    }


    @Override
    public String toString() {
        return "dymola client {" +
                ", wsdlService='" + wsdlService + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", numberOfIntervals=" + numberOfIntervals +
                ", outputInterval=" + outputInterval +
                ", tolerance=" + tolerance +
                '}';
    }

    String						wsdlService;
    private double				startTime;
    private double				stopTime;
    private int					numberOfIntervals;
    private double				outputInterval;
    private double				tolerance;
    private String				resultVariables;
//    private
    
    private static final int	TRIES				= 1; // number of soap remote service attempts, before giving up
    private static final int	CONNECTION_TIMEOUT	= 4 * 60 * 60 * 1000 ;// in milliseconds
    private static final int	REQUEST_TIMEOUT		=  4 * 60 * 60 * 1000 ;// in milliseconds
    private static final Logger LOG					= LoggerFactory.getLogger(StandaloneDymolaClient.class);
}
