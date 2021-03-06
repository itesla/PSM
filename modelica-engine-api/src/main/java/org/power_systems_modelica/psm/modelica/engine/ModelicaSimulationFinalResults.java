package org.power_systems_modelica.psm.modelica.engine;

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

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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

	public void writeToFile(Path filePath)
	{
		try (PrintStream printStream = new PrintStream(Files.newOutputStream(filePath)))
		{
			getEntries().forEach(e -> printStream.println(e.getKey() + "::" + e.getValue()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private final Map<String, Object>	results			= new HashMap<>();
	private static final String			KEY_SEPARATOR	= "::";
	private static final Logger			LOG				= LoggerFactory
			.getLogger(ModelicaSimulationFinalResults.class);
}
