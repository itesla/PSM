package org.power_systems_modelica.psm.modelica.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ModelicaSimulationFinalResults
{
	public void addResult(Stage stage, String model, String var, Object value)
	{
		results.put(buildKey(stage, model, var), value);
	}

	public Object getValue(Stage stage, String model, String var)
	{
		return results.get(buildKey(stage, model, var));
	}

	public Set<Entry<String, Object>> getEntries()
	{
		return results.entrySet();
	}

	public void put(String key, String value)
	{
		results.put(key, value);
	}

	private static String buildKey(Stage stage, String model, String var)
	{
		return new StringBuilder()
				.append(stage)
				.append(KEY_SEPARATOR)
				.append(model)
				.append(KEY_SEPARATOR)
				.append(var)
				.toString();
	}

	private final Map<String, Object>	results			= new HashMap<>();
	private static final String			KEY_SEPARATOR	= "::";
}
