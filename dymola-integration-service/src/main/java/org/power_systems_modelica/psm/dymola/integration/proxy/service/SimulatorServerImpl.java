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
import javax.management.RuntimeErrorException;
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
        
        try {
            // Instantiate the Dymola interface and start Dymola
            try {
                port = portPool.getItem();
            } catch (InterruptedException e) {
                LOGGER.error("Error getting port item. Reasoin is {}.", e.getCause());
                e.printStackTrace();
            }
            dymola = DymolaWrapper.getInstance(true, port);
            LOGGER.debug("   START Dymola - portnumber: {} - ThreadID: {} - @Dymolainst: {} - @SimulatorServerImpl {} ", ((DymolaWrapper) dymola).portnumber, Thread.currentThread().getId(), dymola.hashCode(), this.hashCode());
        } catch(Exception e) {
        	LOGGER.error(" Error instantiating Dymola. Reason is {}.", e.getCause());        	
        }
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
    DataHandler simulate(String inputFileName, 
    						String problem, 
    						double startTime, 
    						double stopTime, 
    						int numberOfIntervals, 
    						double outputInterval, 
    						String method, 
    						double tolerance,  
    						String resultsFileName, 
    						String resultVariables, 
    						boolean createFilteredMat,
    						@XmlMimeType("application/octet-stream") DataHandler data) {
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
            LOGGER.info(" {} - dymola simulation started - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, resultsFileName:{}, input data unzipped in: {} ms.", 
            			workingDir, inputFileName, problem, startTime, stopTime, numberOfIntervals,outputInterval,method,tolerance,resultsFileName,(endms - startms));
            
            startms=System.currentTimeMillis();
            simulateDymola(workingDir.toString(), inputFileName, problem, startTime, stopTime, numberOfIntervals, outputInterval, method, tolerance, resultsFileName, resultVariables, createFilteredMat);
            endms=System.currentTimeMillis();
            
            long simulationTime=endms - startms;

            startms=System.currentTimeMillis();
            outputZipFile = workingDir.getFileName() + ".zip";
            Map<String,String> fileNamesToInclude= MapUtils.asUnmodifiableMap(entry("log.txt", resultsFileName+"_log.txt"), 
            																	entry("dslog.txt",resultsFileName+"_dslog.txt"), 
            																	entry(resultsFileName + ".mat",resultsFileName + ".mat"),
            																	entry(resultsFileName + "_filtered.mat",resultsFileName + "_filtered.mat"),
            																	entry(resultsFileName + "_filtered.csv",resultsFileName + "_filtered.csv")
            																	);
            prepareOutputFile(workingDir, fileNamesToInclude, Paths.get(outputZipFile));
            endms=System.currentTimeMillis();
            LOGGER.info(" {} - dymola simulation terminated - simulation time: {} ms., output file zipped in: {} ms.", workingDir, simulationTime, (endms - startms));
            
            TemporaryFileDataSource outDataSource = new TemporaryFileDataSource(Paths.get(outputZipFile).toFile());
            //FileDataSource outDataSource = new FileDataSource(Paths.get(outputZipFile).toFile());
            DataHandler outputFileDataHandler = new DataHandler(outDataSource);
            return outputFileDataHandler;
        } catch (Exception e) {
            LOGGER.error(" {} - dymola simulation failed - inputFileName:{}, problem:{}, startTime:{}, stopTime:{}, numberOfIntervals:{}, outputInterval:{}, method:{}, tolerance:{}, resultsFileName:{}",
                     workingDir, inputFileName, problem, startTime, stopTime, numberOfIntervals,outputInterval,method,tolerance,resultsFileName,e);

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

    protected void simulateDymola(String workingDirectory, String inputFileName, String problem, 
    		double startTime, double stopTime, int numberOfIntervals, double outputInterval, 
    		String method, double tolerance, String resultsFileName, String resultVariables,
    		boolean createFilteredMat) throws DymolaException {
    	
        try {
        	if(dymola == null) dymola = DymolaWrapper.getInstance(true, port);
        	
            boolean result = false;
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
            result = dymola.simulateModel(problem, startTime, stopTime, numberOfIntervals, outputInterval, method, tolerance, 0, resultsFileName);

            if (!result) {
            	throw new RuntimeException("simulateModel: " + dymola.getLastError());
            }
            
			String matResultsFile = resultsFileName + MAT_EXTENSION;
			String csvResultsFile = resultsFileName + "_filtered" + CSV_EXTENSION;
            
            int resultSize = dymola.readTrajectorySize(matResultsFile);
            String[] resultNames = dymola.readTrajectoryNames(matResultsFile);
            
    		Pattern pattern = Pattern.compile(resultVariables);
    		Matcher matcher = pattern.matcher(Arrays.toString(resultNames));
    		int count = 0;
    		for(int i=0; i< resultNames.length; i++) {
    			if(pattern.matcher(resultNames[i]).matches()) count++;
    		}
    		String[] filterResultVariables = new String[count+1];
    		filterResultVariables[0] = "Time";
    		
    		int j = 1;
    		for(int i=0; i< resultNames.length; i++) {
				matcher = pattern.matcher(resultNames[i]);
    			if(matcher.matches()) {
    				filterResultVariables[j] = matcher.group();
    				j++;
    			}
    		}
            
    		//Check if filterResultVariables only has the variable Time.
    		if(filterResultVariables[1] == null) {
    			filterResultVariables = resultNames;
    		}    		

            try (PrintStream printStream = new PrintStream(Files.newOutputStream(Paths.get(workingDirectory + File.separator + csvResultsFile)))) {
            	writeResultsCsv(printStream, matResultsFile, filterResultVariables, resultSize, createFilteredMat, resultsFileName);
            } catch (IOException e) {
                LOGGER.error("Error printing errors file. {}", e.getMessage());
            }
            
            //Create a .mat file with filtered variables.
            String matResultsFileFiltered = resultsFileName + "_filtered" + MAT_EXTENSION;
            double[][] trajVarsValues = dymola.readTrajectory(matResultsFile, filterResultVariables, resultSize);
        	dymola.writeTrajectory(matResultsFileFiltered, filterResultVariables, Utils.transpose().apply(trajVarsValues));
            
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
    
	private void writeResultsCsv(PrintStream printStream, String matResultsFile, String[] filterResultVariables, int resultSize,
			boolean createFitleredMat, String resultsFileName) throws DymolaException {
		double[][] resultValues = new double[resultSize][filterResultVariables.length];
		String strLine = Stream.of(filterResultVariables).collect(Collectors.joining(",")).toString() + NEW_LINE;
		
		for(int j=0; j<filterResultVariables.length; j++) 
		{
			String[] resVar = new String[1];
			resVar[0] = filterResultVariables[j];
			double[][] trajVarsValues;
			try {
				trajVarsValues = dymola.readTrajectory(matResultsFile, resVar, resultSize);
				
				if(trajVarsValues != null) {
					for(int i=0; i<resultSize; i++) {
						resultValues[i][j] = trajVarsValues[0][j];
					}
				}
				else {
					LOGGER.error("Error getting simulation result variable {} from {}.", resVar, matResultsFile);
				}
			} catch (DymolaException e) {
				LOGGER.error("Error getting simulation result variable {} from {}.", resVar, matResultsFile);
			}
		}
		
		for (int i = 0; i < resultValues.length; i++) {
			for (int k = 0; k < resultValues[i].length; k++) {
				if(k == 0) strLine = strLine + resultValues[i][k];
				else strLine = strLine + "," + resultValues[i][k]; 
			}
			strLine = strLine + NEW_LINE;
		}			
		printStream.print(strLine);
		
        //Create a .mat file with filtered variables.
		if(createFitleredMat) {
	        String matResultsFileFiltered = resultsFileName + "_filtered" + MAT_EXTENSION;
	        double[][] trajVarsValues;
			trajVarsValues = dymola.readTrajectory(matResultsFile, filterResultVariables, resultSize);
			dymola.writeTrajectory(matResultsFileFiltered, filterResultVariables, Utils.transpose().apply(trajVarsValues));
		}
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

    final String serviceWorkDir;
    private DymolaInterface dymola = null;
    final Pool<Integer> portPool;
    private int			port = -1;

    static final String DYMOLASERVICE_TEMP = "/temp/Dymola/server";
    static final String DYMOLASERVICE_INPUTFILENAME = "dyninput.zip";
    static final String DYMSERV_PREFIX = "dymserv_";
    private static final String DYMOLA_LOG_FILENAME = "log.txt";
    private static final int MSGERRLEN = 400;
    
    private static final String	NEW_LINE = System.getProperty("line.separator").toString();
	private static final String			MAT_EXTENSION 		= ".mat";
	private static final String			CSV_EXTENSION 		= ".csv";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorServerImpl.class);
}