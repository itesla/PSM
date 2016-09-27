package org.power_systems_modelica.psm.ddr.dyd.equations;

// FIXME A simple example selector
public class PrefixSelector implements Selector
{
	public PrefixSelector(String prefix)
	{
		this.prefix = prefix;
	}

	public String getPrefix()
	{
		return prefix;
	}

	private final String prefix;
}