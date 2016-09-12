package org.power_systems_modelica.psm.ddr.dyd;

public class Component
{
	public Component(String id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	// Reference to parameters is optional,
	// Dynamic model components used in template definitions will not have parameter references,
	// the parameter reference will go in the template instantiation
	public void setParameterSetReference(ParameterSetReference parameterSetReference)
	{
		this.parameterSetReference = parameterSetReference;
	}

	public ParameterSetReference getParameterSetReference()
	{
		return parameterSetReference;
	}

	private final String			id;
	private final String			name;
	private ParameterSetReference	parameterSetReference;
}
