package org.power_systems_modelica.psm.modelica.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ModelicaSimulationResults
{
	public void addResult(String model, String component, String var, Object value)
	{
		results.put(buildKey(model, component, var), value);
	}

	public Object getValue(String model, String component, String var)
	{
		return results.get(buildKey(model, component, var));
	}

	public Set<Entry<String, Object>> getEntries()
	{
		return results.entrySet();
	}

	public void put(String key, String value)
	{
		results.put(key, value);
	}

	private static String buildKey(String model, String component, String var)
	{
		return new StringBuilder()
				.append(model)
				.append(KEY_SEPARATOR)
				.append(component)
				.append(KEY_SEPARATOR)
				.append(var)
				.toString();
	}

	private final Map<String, Object>	results			= new HashMap<>();
	private static final String			KEY_SEPARATOR	= "::";
}
