package org.power_systems_modelica.psm.ddr.dyd;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParameterSetContainer implements DydContent
{
	public ParameterSetContainer()
	{
		this.setCounter = 0;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public ParameterSet newParameterSet()
	{
		int id = ++setCounter;
		return new ParameterSet("" + id);
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

	private String							name;
	private int								setCounter;

	private final Map<String, ParameterSet>	parameterSets	= new HashMap<>();
}
