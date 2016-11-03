package org.power_systems_modelica.psm.ddr.dyd;

public class Association
{
	public Association(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public String getPattern()
	{
		return pattern;
	}

	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}

	private final String	id;
	private String			pattern;
}
