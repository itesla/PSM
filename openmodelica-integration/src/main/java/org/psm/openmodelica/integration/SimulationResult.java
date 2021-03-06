package org.psm.openmodelica.integration;

/*
 * #%L
 * Dynamic simulation using OpenModelica
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class SimulationResult
{
	public SimulationResult(String simulationResult, String simError)
	{
		if (simulationResult != null)
		{
			String[] linesSimulationResult = simulationResult.split(",\n");
			for (String l : linesSimulationResult)
			{
				String l1 = l.trim();
				if (l1.startsWith("record")) resultFile = getFirstQuotedText(l1);
				if (l1.startsWith("simulationOptions")) simulationOptions = getFirstQuotedText(l1);
				if (l1.startsWith("messages")) messages = getFirstQuotedText(l1);
			}
		}

		error = simError;
		if(!error.isEmpty()) getFirstQuotedText(simError);
	}

	public String getResultFile()
	{
		return resultFile;
	}

	public void setResultFile(String resultFile)
	{
		this.resultFile = resultFile;
	}

	public String getSimulationOptions()
	{
		return simulationOptions;
	}

	public void setSimulationOptions(String simulationOptions)
	{
		this.simulationOptions = simulationOptions;
	}

	public String getMessages()
	{
		return messages;
	}

	public void setMessages(String messages)
	{
		this.messages = messages;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getError()
	{
		return error;
	}

	private static String getFirstQuotedText(String text)
	{
		int p0 = text.indexOf('"');
		int p1 = text.indexOf('"', p0 + 1);
		return text.substring(p0 + 1, p1);
	}

	private String	resultFile;
	private String	simulationOptions;
	private String	messages;
	private String	error;
}
