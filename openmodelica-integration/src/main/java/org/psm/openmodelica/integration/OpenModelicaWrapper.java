package org.psm.openmodelica.integration;

import java.util.List;
import java.util.StringTokenizer;

import org.openmodelica.corba.ConnectException;
import org.openmodelica.corba.Result;
import org.openmodelica.corba.SmartProxy;
import org.stringtemplate.v4.compiler.STParser.memberExpr_return;

public class OpenModelicaWrapper extends SmartProxy
{
	public OpenModelicaWrapper(String corbaSessionName)
	{
		super(corbaSessionName);
	}

	/**
	 * Clears everything in the workspace.
	 * 
	 * @return true if successful.
	 * @throws ConnectException
	 */
	public Result clear() throws ConnectException
	{
		return sendExpression("clear()");
	}

	/**
	 * Change the directory to the given path
	 * 
	 * @return new working directory
	 * @throws ConnectException
	 */
	public Result cd(String workingDirectory) throws ConnectException
	{
		return sendExpression(new StringBuilder().append("cd(")
				.append(workingDirectory)
				.append(")")
				.toString());
	}

	/**
	 * Loads the standard library: Modelica
	 * 
	 * @return
	 * @throws ConnectException
	 */
	public Result loadStandardLibrary() throws ConnectException
	{
		return sendExpression("loadModel(Modelica)");
	}

	/**
	 * Loads the a Modelica file
	 * 
	 * @param fileName
	 * @return true if success
	 * @throws ConnectException
	 */
	public Result loadFile(String fileName) throws ConnectException
	{
		return sendExpression(new StringBuilder().append("loadFile(")
				.append(fileName)
				.append(")")
				.toString());
	}

	/**
	 * Checks a model.
	 * 
	 * @param model
	 * @return number of variables and equations
	 * @throws ConnectException
	 */
	public Result checkModel(String model) throws ConnectException
	{
		return sendExpression(new StringBuilder().append("checkModel(")
				.append(model)
				.append(")")
				.toString());
	}

	/**
	 * Simulates a Modelica model, building it and run the simulation.
	 * 
	 * @param modelName
	 * @param tstart
	 * @param tstop
	 * @param numberOfIntervals
	 * @param method
	 * @param tolerance
	 * @param simFlags
	 * @return
	 * @throws ConnectException
	 */
	public Result simulate(String modelName, double tstart, double tstop, int numberOfIntervals,
			String method, double tolerance, String simFlags) throws ConnectException
	{
		return sendExpression(new StringBuffer().append("simulate(").append(modelName)
				.append(", startTime=").append(tstart)
				.append(", stopTime=").append(tstop)
				.append(", numberOfIntervals=").append(numberOfIntervals)
				.append(", method=").append(method)
				.append(", tolerance=").append(tolerance)
				.append(", simflags=\"").append(simFlags).append("\"")
				.append(")").toString());
	}

	/**
	 * Read the output variables from the result file (.mat)
	 * 
	 * @param resultsFile
	 * @param resultVariable
	 * @return
	 * @throws ConnectException
	 */
	public Result readSimulationResult(String resultsFile, String resultVariable)
			throws ConnectException
	{
		resultVariable = resultVariable.substring(1, resultVariable.length() - 1);

		return sendExpression(new StringBuilder().append("readSimulationResult(\"")
				.append(resultsFile).append("\"")
				.append(", ").append("{" + resultVariable + "}")
				.append(")").toString());
	}

	/**
	 * Returns the variables in the simulation file.
	 * 
	 * @param resultsFile
	 * @return
	 * @throws ConnectException
	 */
	public Result readSimulationResultVars(String resultsFile) throws ConnectException
	{
		return sendExpression(new StringBuilder().append("readSimulationResultVars(\"")
				.append(resultsFile).append("\"")
				// .append(", readParameters=").append(false)
				.append(")").toString());
	}

	/**
	 * Reads a result file, returning a matrix corresponding to the variables and size given.
	 * 
	 * @param resultsFile
	 * @return
	 * @throws ConnectException
	 */
	public Result readSimulationResultSize(String resultsFile) throws ConnectException
	{
		return sendExpression(new StringBuilder().append("readSimulationResultSize(\"")
				.append(resultsFile).append("\"")
				.append(")").toString());
	}

	/**
	 * Filter the desired result variables from the results file (.mat)
	 * 
	 * @param resultsFile
	 * @param outputFile
	 * @param resultVariables
	 * @param resultSize
	 * @return
	 * @throws ConnectException
	 */
	public Result filterSimulationResults(String resultsFile, String outputFile,
			List<String> resultVariables, int resultSize) throws ConnectException
	{
		String resultVars = null;
		for (String st : resultVariables)
		{
			if (resultVars == null)
			{
				resultVars = st;
			}
			else
			{
				resultVars = resultVars + "," + st;
			}
		}

		return sendExpression(new StringBuilder().append("filterSimulationResults(\"")
				.append(resultsFile).append("\"")
				.append(", \"").append(outputFile).append("\"")
				.append(", ").append("{" + resultVars + "}")
				.append(", ").append(resultSize)
				.append(")").toString());
	}

	@Override
	public void logOMCReply(String reply)
	{
		//TODO Temp
//		StringTokenizer tokenizer = new StringTokenizer(reply, "\n");
//
//		while (tokenizer.hasMoreTokens())
//		{
//			System.out.println("<<<< " + tokenizer.nextToken());
//		}
	}

	@Override
	public void logOMCCall(String expression)
	{
		//TODO Temp
//		System.out.println(">>>> " + expression);
	}

	@Override
	public void logOMCStatus(String message)
	{
		//TODO Temp
//		System.out.println(">>>><<<< " + message);
	}
}
