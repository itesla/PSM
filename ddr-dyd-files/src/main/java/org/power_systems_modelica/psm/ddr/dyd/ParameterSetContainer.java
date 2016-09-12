package org.power_systems_modelica.psm.ddr.dyd;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParameterSetContainer
{
	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getFilename()
	{
		return filename;
	}

	public void add(ParameterSet parameterSet)
	{
		parameterSets.put(parameterSet.getId(), parameterSet);
	}

	public ParameterSet get(String setId)
	{
		return parameterSets.get(setId);
	}

	public Collection<ParameterSet> getSets()
	{
		return Collections.unmodifiableCollection(parameterSets.values());
	}

	private String							filename;
	private final Map<String, ParameterSet>	parameterSets	= new HashMap<>();
}
