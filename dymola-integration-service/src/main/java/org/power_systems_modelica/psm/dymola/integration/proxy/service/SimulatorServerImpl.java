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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
@StreamingAttachment(parseEagerly = true, memoryThreshold = 5000000L, dir="/tmp")
@WebService(endpointInterface = "org.power_systems_modelica.psm.dymola.integration.proxy.service.SimulatorServer")
public class SimulatorServerImpl implements SimulatorServer {
    public SimulatorServerImpl() {
        this(DYMOLASERVICE_TEMP, 9000, 20, false);
    }


    public SimulatorServerImpl(String serviceWorkDir, int start, int size) {
        this(serviceWorkDir, start, size, false);
    }

    public SimulatorServerImpl(String serviceWorkDir, int start, int size, boolean debug) {
        // Set this flag to false if you want Dymola to be visible.
        // By default Dymola is hidden.
        //DymolaWrapper.nowindow = false;
        DymolaWrapper.nowindow = true;
        LOGGER.info("Dymola proxy service");
        LOGGER.info(" API version: {}",DymolaWrapper.dymola_version);
        LOGGER.info(" service working directory: {}", serviceWorkDir);
        LOGGER.info(" ports pool starting from {}, size {}", start, size);
        ArrayList<Integer> portArray = new ArrayList<>();
        for (int i = start; i < start + size; i++) {
            portArray.add(i);
        }
        portPool = new Pool<>(portArray);

        this.serviceWorkDir = serviceWorkDir;
        this.debug = debug;
    }


    //testing only
    public void setFakeSourceDir(String fakeSourceDir) {
        this.fakeSourceDir=fakeSourceDir;
    }

    protected void prepareOutputFile(Path workingDir, Map<String,String> fileNamesMap, Path outFile) throws IOException, URISyntaxException {
        Map<String, String> zip_properties = new HashMap<>();
        zip_properties.put("create", "true");
        zip_properties.put("encoding", "UTF-8");
        URI zip_disk = new URI("jar", outFile.toUri().toString(), null);
        try (FileSystem zipfs = FileSystems.newFileSystem(zip_disk, zip_properties)) {
            for (String filename : fileNamesMap.keySet()) {
                Path sourcePath = workingDir.resolve(filename);
                if (Files.exists(sourcePath)) {
                    Files.copy(workingDir.resolve(filename), zipfs.getPath(fileNamesMap.get(filename)));
                } else {
                    LOGGER.warn("file " + sourcePath + " does not exist. It won't be in " + outFile);
                }
            }
        }
    }

    public
    @XmlMimeType("application/octet-stream")
    DataHandler simulate(String inputFileName, String problem, double startTime, double stopTime, int numberOfIntervals, double outputInterval, String method, double tolerance, double fixedstepsize, String resultsFileName, String[] resultVariables, @XmlMimeType("application/octet-stream") DataHandler data) {
        Path workingDir = null;
        String outputZipFile = null;
        try {
            long startms=System.currentTimeMillis();
            Path inputZipFile;
            try (StreamingDataHandler inputDh = (StreamingDataHandler) data) {
                Files.createDirectories(Paths.get(serviceWorkDir));
                workingDir = Files.createTempDirectory(Paths.get(serviceWorkDir), DYMSERV_PREFIX);
                Files.createDirectories(workingDir);
                inputZipFile = workingDir.resolve(DYMOLASERVICE_INPUTFILENAME);
                inputDh.moveTo(inputZipFile.toFile());
            }
            try (ZipFile zipFile = new ZipFile(inputZipFile.toFile())) {
                ZipFileUtil.unzipFileIntoDirectory(zipFile, workingDir.toFile());
            }
            long endms=System.currentTimeMillis();
            LOGGER.info(" {} - dymola simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, fixedstepsize:{}, resultsFileName:{}, input data unzipped in: {} ms.", workingDir, inputFileName, problem, startTime, stopTime, numberOfIntervals,outputInterval,method,tolerance,fixedstepsize,resultsFileName,(endms - startms));
            startms=System.currentTimeMillis();
            if (fakeSourceDir==null) {
                simulateDymola(workingDir.toString(), inputFileName, problem, startTime, stopTime, numberOfIntervals, outputInterval, method, tolerance, fixedstepsize, resultsFileName, resultVariables);
            } else {
                simulateDymolaFake(workingDir.toString(), inputFileName, problem, startTime, stopTime, numberOfIntervals, outputInterval, method, tolerance, fixedstepsize, resultsFileName, resultVariables);
            }

            endms=System.currentTimeMillis();
            long simulationTime=endms - startms;

            startms=System.currentTimeMillis();
            outputZipFile = workingDir.getFileName() + ".zip";
            Map<String,String> fileNamesToInclude= MapUtils.asUnmodifiableMap(entry("log.txt", resultsFileName+"_log.txt"), 
            																	entry("dslog.txt",resultsFileName+"_dslog.txt"), 
            																	entry(resultsFileName + ".mat",resultsFileName + ".mat"),
            																	entry(resultsFileName + ".csv",resultsFileName + ".csv"));
            prepareOutputFile(workingDir, fileNamesToInclude, Paths.get(outputZipFile));
            endms=System.currentTimeMillis();
            LOGGER.info(" {} - dymola simulation terminated - simulation time: {} ms., output file zipped in: {} ms.", workingDir, simulationTime, (endms - startms));
            
            TemporaryFileDataSource outDataSource = new TemporaryFileDataSource(Paths.get(outputZipFile).toFile());
            //FileDataSource outDataSource = new FileDataSource(Paths.get(outputZipFile).toFile());
            DataHandler outputFileDataHandler = new DataHandler(outDataSource);
            return outputFileDataHandler;
        } catch (Exception e) {
            LOGGER.error(" {} - dymola simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, fixedstepsize:{}, resultsFileName:{}",
                     workingDir, inputFileName, problem, startTime, stopTime, numberOfIntervals,outputInterval,method,tolerance,fixedstepsize,resultsFileName,e);

            String errMessg=e.getMessage();
            errMessg=((errMessg != null) && (errMessg.length() > MSGERRLEN)) ? errMessg.substring(0, MSGERRLEN) +" ..." : errMessg;
            
            throw new WebServiceException("dymola simulation failed - remote working directory " + workingDir +", fileName: "+ inputFileName +", problem:"+ problem +", error message:" + errMessg, e);
        } finally {
            if (debug == false) {
                try {
                    Utils.deleteDirectoryRecursively(workingDir);
                } catch (IOException e) {
                	LOGGER.error("Error deleting directory {}, reason {}", workingDir, e.getMessage());
                }
            }
        }
    }

    //testing only, get results for a precomputed simulation
    protected void simulateDymolaFake(String workingDirectory, String inputFileName, String problem, double startTime, double stopTime, int numberOfIntervals, double outputInterval, String method, double tolerance, double fixedstepsize, String resultsFileName, String[] resultVariables) throws DymolaException {
        DymolaInterface dymola = null;
        int port = -1;
        try {
            boolean result = false;
            // Instantiate the Dymola interface and start Dymola
            try {
                port = portPool.getItem();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.debug("   START {} - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ", workingDirectory, (dymola!=null)?((DymolaWrapper) dymola).portnumber:"FAKE", Thread.currentThread().getId(), (dymola!=null)?dymola.hashCode():"FAKE", this.hashCode());

            try {
                Files.copy(Paths.get(fakeSourceDir).resolve("dymolasim_0.mat"),Paths.get(workingDirectory).resolve(resultsFileName+".mat"));
                Files.copy(Paths.get(fakeSourceDir).resolve("log.txt"),Paths.get(workingDirectory).resolve("log.txt"));
                Files.copy(Paths.get(fakeSourceDir).resolve("dslog.txt"),Paths.get(workingDirectory).resolve("dslog.txt"));
                try {
                    int waiting_time=1000*60*1;
                    LOGGER.info("simulating a real computation: waiting "+ (waiting_time/60000) + " minutes" );
                    Thread.sleep(waiting_time);
                    LOGGER.info("simulating a real computation: ended." );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(),e);
            }

            LOGGER.debug("   END {} - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ", workingDirectory, (dymola!=null)?((DymolaWrapper) dymola).portnumber:"FAKE", Thread.currentThread().getId(), (dymola!=null)? dymola.hashCode():"FAKE", this.hashCode());
        } finally {
            // The connection to Dymola is closed and Dymola is terminated
            if (dymola != null) {
                dymola.exit();
            }
            dymola = null;
            //release this port number to the pool
            if (port != -1) {
                portPool.putItem(port);
            }
        }

    }

    
    protected void simulateDymola(String workingDirectory, String inputFileName, String problem, double startTime, double stopTime, int numberOfIntervals, double outputInterval, String method, double tolerance, double fixedstepsize, String resultsFileName, String[] resultVariables) throws DymolaException {
    	
        DymolaInterface dymola = null;
        int port = -1;
        try {
            boolean result = false;
            // Instantiate the Dymola interface and start Dymola
            try {
                port = portPool.getItem();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dymola = DymolaWrapper.getInstance(true, port);
            LOGGER.debug("   START {} - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ", workingDirectory, ((DymolaWrapper) dymola).portnumber, Thread.currentThread().getId(), dymola.hashCode(), this.hashCode());

            result= dymola.clear(false);
            if (!result) {
                throw new RuntimeException("CLEAR : " + dymola.getLastError());
            }

            result = dymola.cd(workingDirectory);
            if (!result) {
                throw new RuntimeException("CD : " + dymola.getLastError());
            }

            result = dymola.openModel(workingDirectory + File.separator + inputFileName);
            
            if (!result) {
                throw new RuntimeException("openModel: " + dymola.getLastError());
            }

            boolean check = dymola.checkModel(problem);
            if (!check) {
            	throw new RuntimeException("checkModel: " + dymola.getLastError());
            }
            
            // Simulate the model         
            result = dymola.simulateModel(problem, startTime, stopTime, numberOfIntervals, outputInterval, method, tolerance, fixedstepsize, resultsFileName);

            if (!result) {
            	throw new RuntimeException("simulateModel: " + dymola.getLastError());
            }
            
            int trajSize = dymola.readTrajectorySize(resultsFileName + ".mat");
            String[] trajNames = dymola.readTrajectoryNames(resultsFileName + ".mat");
            
            double[][] trajVarsValues = dymola.readTrajectory(resultsFileName + ".mat", 
            													resultVariables.length == 0 ? trajNames : resultVariables, 
            													trajSize);

            if(trajVarsValues != null) {
	            try (PrintStream printStream = new PrintStream(Files.newOutputStream(Paths.get(workingDirectory + File.separator + resultsFileName + ".csv")))) {
	            	printResultVariables(printStream, resultVariables.length == 0 ? trajNames : resultVariables, Utils.transpose().apply(trajVarsValues));
	            } catch (IOException e) {
	                LOGGER.error("Error printing errors file. {}", e.getMessage());
	            }
            }
            
            result = dymola.savelog(DYMOLA_LOG_FILENAME);
            if (!result) {
                throw new RuntimeException("saveLog: " + dymola.getLastError());
            }

            LOGGER.error("   END {} - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ", workingDirectory, ((DymolaWrapper) dymola).portnumber, Thread.currentThread().getId(), dymola.hashCode(), this.hashCode());
        } finally {
            // The connection to Dymola is closed and Dymola is terminated
            if (dymola != null) {
                dymola.exit();
            }
            dymola = null;
            //release this port number to the pool
            if (port != -1) {
                portPool.putItem(port);
            }
        }

    }
    
    private void printResultVariables(PrintStream printStream, String[] resultVariables, double[][] trajVarsValues) {
    	String strLine = Stream.of(resultVariables).collect(Collectors.joining(",")).toString() + NEW_LINE;
		for (int i = 0; i < trajVarsValues.length; i++) {
			for (int k = 0; k < trajVarsValues[i].length; k++) {
				if(k == 0) strLine = strLine + trajVarsValues[i][k];
				else strLine = strLine + "," + trajVarsValues[i][k]; 
			}
			strLine = strLine + NEW_LINE;
		}
		printStream.print(strLine);
	}

	private class TemporaryFileDataSource extends FileDataSource {

        public TemporaryFileDataSource(File file) {
            super(file);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new TemporaryFileInputStream(this.getFile());
        }

    }

    private class TemporaryFileInputStream extends FileInputStream {
        File file;

        public TemporaryFileInputStream(File file) throws FileNotFoundException {
            super(file);
            this.file = file;
        }

        @Override
        public void close() throws IOException {
            super.close();
            boolean isDeleted = file.delete();
            LOGGER.trace(" **** " + file.getAbsoluteFile() + " :" + isDeleted);
        }
    }
    
    boolean debug;

    final Pool<Integer> portPool;

    final String serviceWorkDir;

    //testing only
    private String fakeSourceDir=null;
    
    static final String DYMOLASERVICE_TEMP = "/temp/Dymola/server";
    static final String DYMOLASERVICE_INPUTFILENAME = "dyninput.zip";
    static final String DYMSERV_PREFIX = "dymserv_";
    private static final String DYMOLA_LOG_FILENAME = "log.txt";
    private static final int MSGERRLEN = 400;
    
    private static final String	NEW_LINE = System.getProperty("line.separator").toString();
	private static final String			MO_EXTENSION		= ".mo";
	private static final String			MAT_EXTENSION 		= ".mat";
	private static final String			CSV_EXTENSION 		= ".csv";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorServerImpl.class);
}