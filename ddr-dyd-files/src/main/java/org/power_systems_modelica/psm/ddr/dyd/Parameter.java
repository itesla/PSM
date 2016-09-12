package org.power_systems_modelica.psm.ddr.dyd;

public abstract class Parameter
{
	public Parameter(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	private final String name;
}
