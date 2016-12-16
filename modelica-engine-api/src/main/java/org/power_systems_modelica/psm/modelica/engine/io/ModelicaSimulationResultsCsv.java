package org.power_systems_modelica.psm.modelica.engine.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;

public class ModelicaSimulationResultsCsv
{
	// TODO Maybe use a csv library here
	public static void write(Path f, ModelicaSimulationFinalResults results) throws FileNotFoundException
	{
		try (PrintWriter out = new PrintWriter(f.toFile());)
		{
			out.printf("%s,%s%n", "key", "value");
			for (Entry<String, Object> e : results.getEntries())
			{
				out.printf("%s,%s%n", e.getKey(), e.getValue());
			}
		}
	}

	public static ModelicaSimulationFinalResults read(Path f) throws FileNotFoundException, IOException
	{
		try (BufferedReader in = Files.newBufferedReader(f);)
		{
			ModelicaSimulationFinalResults results = new ModelicaSimulationFinalResults();
			// Skip the header
			in.readLine();
			String line;
			while ((line = in.readLine()) != null)
			{
				String[] fields = line.split(",");
				String key = fields[0];
				String value = fields[1];
				results.put(key, value);
			}
			return results;
		}
	}
}
