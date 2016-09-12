package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.List;

public class ParameterSet
{
	public ParameterSet(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void add(List<Parameter> ps)
	{
		params.addAll(ps);
	}

	public void add(Parameter p)
	{
		params.add(p);
	}

	public List<Parameter> getParameters()
	{
		return params;
	}

	private final String			id;
	private final List<Parameter>	params	= new ArrayList<>();
}
