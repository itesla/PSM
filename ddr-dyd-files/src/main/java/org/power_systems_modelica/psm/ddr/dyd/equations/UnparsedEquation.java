package org.power_systems_modelica.psm.ddr.dyd.equations;

public class UnparsedEquation extends Equation
{
	public UnparsedEquation(String text)
	{
		super();
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

	@Override
	public String toString()
	{
		return text;
	}

	private final String text;
}
