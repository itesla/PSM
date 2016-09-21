package org.power_systems_modelica.psm.ddr.dyd;

public class ParameterValue extends Parameter
{
	public ParameterValue(String type, String name, Object value)
	{
		super(name);
		this.type = type;
		this.value = value;
	}

	public String getType()
	{
		return type;
	}

	public Object getValue()
	{
		return value;
	}

	private final String	type;
	private final Object	value;
}
