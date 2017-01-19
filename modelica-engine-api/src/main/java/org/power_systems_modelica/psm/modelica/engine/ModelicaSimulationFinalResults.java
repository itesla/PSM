package org.power_systems_modelica.psm.modelica.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ModelicaSimulationFinalResults
{
	public void addResult(String model, String var, Object value)
	{
		results.put(buildKey(model, var), value);
	}

	public Object getValue(String model, String var) throws Exception
	{
		String key = buildKey(model, var);
		if (!results.containsKey(key))
		{
			String msg = "no results found for key " + key;
			LOG.error(msg);
			throw new Exception(msg);
		}
		return results.get(key);
	}

	public Set<Entry<String, Object>> getEntries()
	{
		return results.entrySet();
	}

	public void put(String key, String value)
	{
		results.put(key, value);
	}

	private static String buildKey(String model, String var)
	{
		return new StringBuilder()
				.append(model)
				.append(KEY_SEPARATOR)
				.append(var)
				.toString();
	}
	
	private final Map<String, Object>	results			= new HashMap<>();
	private static final String			KEY_SEPARATOR	= "::";
	private static final Logger			LOG				= LoggerFactory
			.getLogger(ModelicaSimulationFinalResults.class);
}
