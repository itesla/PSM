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

	// Can the parameter be included in a template?
	// That is, the parameter contains enough information so it can be included in a template
	// Either because it is a reference to an external data source, a constant value for all instances, ...
	public abstract boolean isGeneric();

	private final String	name;
	private final String	unit;
}
