package org.power_systems_modelica.psm.modelica.engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			Stream.of(header)
					.forEach(d -> results.addResult(modelName, d, new ArrayList<String>()));
			String line;
			while ((line = in.readLine()) != null)
			{

				String[] values = line.split(COMMA);
				Stream.of(header)
						.forEach(d -> getResultsFor(results, modelName, d)
								.add(values[Arrays.asList(header).indexOf(d)]));
			}

			Stream.of(header).forEach(d -> {
				ArrayList<String> values = getResultsFor(results, modelName, d);
				results.addResult(modelName, d, values.get(values.size() - 1));
			});
		}
		catch (IOException e)
		{
			LOGGER.error("Error opening/reading file {}. {}", csvFilePath, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private static ArrayList<String> getResultsFor(ModelicaSimulationFinalResults results,
			String modelName, String d)
	{
		try
		{
			return ((ArrayList<String>) results.getValue(modelName, d));
		}
		catch (Exception e)
		{
			throw new RuntimeException("No results for model " + modelName + " field " + d);
		}
	}

	public static final String	COMMA	= ",";
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ModelicaEngineUtils.class);
}
