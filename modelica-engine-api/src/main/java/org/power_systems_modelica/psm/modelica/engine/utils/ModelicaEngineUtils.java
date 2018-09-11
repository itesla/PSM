package org.power_systems_modelica.psm.modelica.engine.utils;

/*
 * #%L
 * Modelica Engine API
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaEngineUtils
{

	/**
	 * Read a csv file resulting from the Modelica engine simulation and save simulation results in a ModelicaSimulationFinalResults The only results saved are values for the last instant of time
	 */
	public static void fillModelicaSimulationResults(Path csvFilePath,
			String modelName,
			ModelicaSimulationFinalResults results)
	{
		BufferedReader in;
		try
		{
			in = Files.newBufferedReader(csvFilePath);

			String[] header = in.readLine().split(COMMA);
			String line;
			String[] lastValues = null;
			while ((line = in.readLine()) != null)
			{
				lastValues = line.split(COMMA);
			}

			for (int i = 0; i < header.length && lastValues != null; i++)
			{
				results.addResult(modelName, header[i], lastValues[i]);
			}
		}
		catch (IOException e)
		{
			LOGGER.error("Error opening/reading file {}. {}", csvFilePath, e.getMessage());
		}
	}

	public static final String	COMMA	= ",";
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ModelicaEngineUtils.class);
}
