package org.power_systems_modelica.psm.modelica;

public class ModelicaParameter
{
	public ModelicaParameter(ModelicaType type, String name, String value)
	{
		this.type = type;
		this.name = name;
		this.value = value;
	}

	public ModelicaType getType()
	{
		return type;
	}

	public String getName()
	{
		return name;
	}

	public String getValue()
	{
		return value;
	}

	private ModelicaType	type;
	private String			name;
	private String			value;
}
