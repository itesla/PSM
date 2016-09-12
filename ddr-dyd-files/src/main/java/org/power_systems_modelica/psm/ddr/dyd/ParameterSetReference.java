package org.power_systems_modelica.psm.ddr.dyd;

public class ParameterSetReference
{
	public ParameterSetReference(String container, String set)
	{
		this.container = container;
		this.set = set;
	}

	public String getContainer()
	{
		return container;
	}

	public String getSet()
	{
		return set;
	}

	private final String	container;
	private final String	set;
}
