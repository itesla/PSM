package org.power_systems_modelica.psm.ddr.dyd.equations;

// FIXME PrefixSelector is a simple selector, build a selector based on type of Modelica element
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

	@Override
	public String toString()
	{
		return "startsWith(" + prefix + ")";
	}

	private final String prefix;
}