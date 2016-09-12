package org.power_systems_modelica.psm.workflow;

import java.util.HashMap;
import java.util.Map;

public final class TaskConfiguration
{
	public String getParameter(String name)
	{
		return parameters.get(name);
	}

	public boolean getBoolean(String name)
	{
		return Boolean.valueOf(getParameter(name));
	}

	public void setParameter(String name, String value)
	{
		parameters.put(name, value);
	}

	private final Map<String, String> parameters = new HashMap<>();
}
