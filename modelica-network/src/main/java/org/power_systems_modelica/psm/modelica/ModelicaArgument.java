package org.power_systems_modelica.psm.modelica;

public class ModelicaArgument
{
	public ModelicaArgument(String name, Object value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public Object getValue()
	{
		return value;
	}

	private final String	name;
	private final Object	value;
}
