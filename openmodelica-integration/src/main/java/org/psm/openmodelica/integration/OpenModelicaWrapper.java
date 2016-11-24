package org.psm.openmodelica.integration;

import java.util.List;

import org.openmodelica.corba.ConnectException;
import org.openmodelica.corba.Result;
import org.openmodelica.corba.SmartProxy;

public class OpenModelicaWrapper extends SmartProxy {
	//FIXME increase timeout

	public OpenModelicaWrapper(String corbaSessionName) {
		super(corbaSessionName);
	}
	
	/**
	 * Clears everything. 
	 * @return true if successful.
	 * @throws ConnectException
	 */
	public Result clear() throws ConnectException
	{
		return sendExpression("clear()");
	}
	
	
	
	/**
	 * Change the directory to the given path
	 *  @return new working directory
	 *  @throws ConnectException
	 */
	public Result cd(String workingDirectory) throws ConnectException {
		return sendExpression(new StringBuilder().append("cd(")
														.append(workingDirectory)
														.append(")")
														.toString());
	}

	/**
	 */
	public Result cd() throws ConnectException {
		return sendExpression(new StringBuilder().append("cd()").toString());
	}
	
	/**
	 * Loads the standard library: Modelica
	 * @return
	 * @throws ConnectException
	 */
	public Result loadStandardLibrary() throws ConnectException {
		Result result = null;
		if(!systemLibraryLoaded) {
			result = sendExpression("loadModel(Modelica)");
			
			systemLibraryLoaded = true;
		}
		return result;
	}


	/**
	 * Returns the current error message. With warningsAsErrors=true, it reports warnings as if they were errors.
	 * @param warningAsErrors
	 * @throws ConnectException 
	 */
	public Result getErrorString(boolean warningsAsErrors) throws ConnectException {
		return sendExpression(new StringBuilder().append("getErrorString(")
															.append(warningsAsErrors)
															.append(")")
															.toString());
	}
	
	/**
	 * Returns the current error message.
	 * @throws ConnectException 
	 */
	public Result getErrorString() throws ConnectException {
		return sendExpression(new StringBuilder().append("getErrorString(")
															.append(false)
															.append(")")
															.toString());
	}
	
	/**
	 * Loads the *.mo file
	 * @param fileName
	 * @return true if success
	 * @throws ConnectException
	 */
	public Result loadFile(String fileName) throws ConnectException {
		return sendExpression(new StringBuilder().append("loadFile(")
												.append(fileName)
												.append(")")
												.toString());
	}
	
	
	/**
	 * Checks a model.
	 * @param model
	 * @return number of variables and equations
	 * @throws ConnectException
	 */
	public Result checkModel(String model) throws ConnectException {
		return sendExpression(new StringBuilder().append("checkModel(")
												.append(model)
												.append(")")
												.toString());
	}

	/**
	 * Simulates a Modelica model, building it and run the simulation.
	 * @param modelName
	 * @param tstart
	 * @param tstop
	 * @param numberOfIntervals
	 * @param method
	 * @param tolerance
	 * @param resultVariables
	 * @return simulation results
	 * @throws ConnectException
	 */
	public Result simulate(String modelName, double tstart, double tstop, int numberOfIntervals, String method, double tolerance, String[] resultVariables) throws ConnectException
	{
		return sendExpression(new StringBuffer().append("simulate(className=").append(modelName)
													.append(", startTime=").append(tstart)
													.append(", stopTime=").append(tstop)
													.append(", numberOfIntervals=").append(numberOfIntervals)
													.append(", tolerance=").append(tolerance)
													.append(", method=").append(method)
													.append(", variableFilter=").append(resultVariables.toString())
													.append(")").toString()); //TODO write resultVariables properly
	}
	
	/**
	 * 
	 * @param modelName
	 * @param tstart
	 * @param tstop
	 * @param numberOfIntervals
	 * @param method
	 * @param tolerance
	 * @return
	 * @throws ConnectException
	 */
	public Result simulate(String modelName, double tstart, double tstop, int numberOfIntervals, String method, double tolerance) throws ConnectException
	{		
		return sendExpression(new StringBuffer().append("simulate(").append(modelName)
												.append(", startTime=").append(tstart)
												.append(", stopTime=").append(tstop)
												.append(", numberOfIntervals=").append(numberOfIntervals)
												.append(", method=").append(method)
												.append(", tolerance=").append(tolerance)
												.append(")").toString());
	}
	
	/**
	 * 
	 * @param resultsFile
	 * @param outputFile
	 * @param resultVariables
	 * @return
	 * @throws ConnectException
	 */
	public Result filterSimulationResults(String resultsFile, String outputFile, String[] resultVariables) throws ConnectException {
		String resultVars = null;
		for(String st : resultVariables) {
			if(resultVars == null) {
				resultVars = "\"" + st + "\"";
			}
			else {
				resultVars = resultVars + ",\"" + st + "\"";
			}
		}
		
		return sendExpression(new StringBuilder().append("filterSimulationResults(")
													.append("\"" + resultsFile + "\"")
													.append(", ").append("\"" + outputFile + "\"")
													.append(", ").append("{" + resultVars + "}")
													.append(")").toString());
	}
		
	public Result readSimulationResult(String resultsFile, List<String> resultVariables) throws ConnectException {
		String resultVars = null;
		for(String st : resultVariables) {
			if(resultVars == null) {
				resultVars = st.substring(1,st.length()-1);
			}
			else {
				resultVars = resultVars + "," + st.substring(1,st.length()-1);
			}
		}
		
		return sendExpression(new StringBuilder().append("readSimulationResult(\"").append(resultsFile).append("\"")
													.append(", ").append("{" + resultVars + "}")
													.append(")").toString());
	}
	
	public Result readSimulationResult(String resultsFile, String resultVariable) throws ConnectException {
		resultVariable = resultVariable.substring(1,  resultVariable.length()-1);
		
		return sendExpression(new StringBuilder().append("readSimulationResult(\"").append(resultsFile).append("\"")
													.append(", ").append("{" + resultVariable + "}")
													.append(")").toString());
	}
	
	/**
	 * Returns the variables in the simulation file.
	 * @param resultsFile
	 * @return
	 * @throws ConnectException
	 */
	public Result readSimulationResultVars(String resultsFile) throws ConnectException {
		return sendExpression(new StringBuilder().append("readSimulationResultVars(\"").append(resultsFile).append("\"")
//													.append(", readParameters=").append(false)
													.append(")").toString());
	}
	
	/**
	 * Reads a result file, returning a matrix corresponding to the variables and size given.
	 * @param resultsFile
	 * @return
	 * @throws ConnectException
	 */
	public Result readSimulationResultSize(String resultsFile) throws ConnectException {
		return sendExpression(new StringBuilder().append("readSimulationResultSize(\"").append(resultsFile).append("\"")
													.append(")").toString());
	}
	
	public Result filterSimulationResults(String resultsFile, String outputFile, List<String> resultVariables, int resultSize) throws ConnectException {
		String resultVars = null;
		for(String st : resultVariables) {
			if(resultVars == null) {
				resultVars = st; //.substring(1,st.length()-1);
			}
			else {
				resultVars = resultVars + "," + st; //.substring(1,st.length()-1);
			}
		}
		
		return sendExpression(new StringBuilder().append("filterSimulationResults(\"").append(resultsFile).append("\"")
				.append(", \"").append(outputFile).append("\"")
				.append(", ").append("{" + resultVars + "}")
				.append(", ").append(resultSize)
				.append(")").toString());
	}
	
	private boolean	systemLibraryLoaded		= false;
}
