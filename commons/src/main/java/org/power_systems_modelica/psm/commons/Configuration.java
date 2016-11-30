package org.power_systems_modelica.psm.commons;

import java.util.HashMap;
import java.util.Map;

public final class Configuration
{
	public String getParameter(String name)
	{
		return parameters.get(name);
	}

	public boolean getBoolean(String name)
	{
		return Boolean.valueOf(getParameter(name));
	}
	
	public double getDouble(String name)
	{
		return Double.valueOf(getParameter(name));
	}
	
	public Integer getInteger(String name)
	{
		return Integer.valueOf(getParameter(name));
	}


	public void setParameter(String name, String value)
	{
		parameters.put(name, value);
	}

	private final Map<String, String> parameters = new HashMap<>();
}
