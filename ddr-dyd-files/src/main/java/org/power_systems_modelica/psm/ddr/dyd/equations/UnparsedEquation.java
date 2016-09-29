package org.power_systems_modelica.psm.ddr.dyd.equations;

public class UnparsedEquation implements Equation
{
	public UnparsedEquation(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	@Override
	public String writeIn(Context<?> context)
	{
		return text;
	}

	private final String text;
}
