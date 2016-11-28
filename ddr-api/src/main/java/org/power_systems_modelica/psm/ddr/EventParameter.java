package org.power_systems_modelica.psm.ddr;

public class EventParameter
{
	public EventParameter(String name, String unit)
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
