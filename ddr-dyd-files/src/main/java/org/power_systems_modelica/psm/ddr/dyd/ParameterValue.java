package org.power_systems_modelica.psm.ddr.dyd;

public class ParameterValue extends Parameter
{
	public ParameterValue(String type, String unit, String name, Object value)
	{
		super(name, unit);
		this.type = type;
		this.value = value;
		this.isGeneric = false;
	}

	public String getType()
	{
		return type;
	}

	public Object getValue()
	{
		return value;
	}

	@Override
	public boolean isGeneric()
	{
		return this.isGeneric;
	}

	public void setGeneric(boolean isGeneric)
	{
		this.isGeneric = isGeneric;
	}

	private final String	type;
	private final Object	value;
	private boolean			isGeneric;
}
