package org.power_systems_modelica.psm.commons;

import java.util.HashMap;
import java.util.Map;

public final class Configuration
{
	public String getParameter(String name)
	{
		return parameters.get(name);
	}

	public Boolean getBoolean(String name)
	{
		return Boolean.valueOf(getParameter(name));
	}
	
	public Double getDouble(String name)
	{
		String parameter = getParameter(name);
		if(parameter == null) return null;
		return Double.valueOf(parameter);
	}
	
	public Integer getInteger(String name)
	{
		String parameter = getParameter(name);
		if(parameter == null) return null;
		return Integer.valueOf(parameter);
	}


	public void setParameter(String name, String value)
	{
		parameters.put(name, value);
	}

	private final Map<String, String> parameters = new HashMap<>();
}
