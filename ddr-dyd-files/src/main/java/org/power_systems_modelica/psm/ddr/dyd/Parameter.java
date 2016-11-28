package org.power_systems_modelica.psm.ddr.dyd;

public abstract class Parameter
{
	public Parameter(String name)
	{
		this.name = name;
		this.unit = null;
	}

	public Parameter(String name, String unit)
	{
		this.name = name;
		this.unit = unit;
	}

	public String getName()
	{
		return name;
	}

	public String getUnit()
	{
		return unit;
	}

	private final String	name;
	private final String	unit;
}
