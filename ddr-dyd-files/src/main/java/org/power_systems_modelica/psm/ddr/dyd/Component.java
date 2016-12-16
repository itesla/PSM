package org.power_systems_modelica.psm.ddr.dyd;

public class Component
{
	public Component(String id, String type)
	{
		this.id = id;
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public String getType()
	{
		return type;
	}

	public void setParameterSet(ParameterSet set)
	{
		this.parameterSet = set;
	}

	public ParameterSet getParameterSet()
	{
		return this.parameterSet;
	}

	// For components defined using templates we will not have a parameter reference
	// The parameter reference will go in the template instantiation
	public void setParameterSetReference(ParameterSetReference parameterSetReference)
	{
		this.parameterSetReference = parameterSetReference;
	}

	public ParameterSetReference getParameterSetReference()
	{
		return parameterSetReference;
	}

	private final String			id;
	private final String			type;
	private ParameterSetReference	parameterSetReference;
	private ParameterSet			parameterSet;
}
