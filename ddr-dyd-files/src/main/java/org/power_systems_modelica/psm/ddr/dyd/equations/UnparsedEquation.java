package org.power_systems_modelica.psm.ddr.dyd.equations;

public class UnparsedEquation implements Equation
{
	public UnparsedEquation(String text)
	{
		this.text = text;
		System.err.println("new unparsed Equation: " + this.text);
	}

	public String getText()
	{
		return text;
	}

	@Override
	public String writeIn(Context<?> context)
	{
		System.err.println("Unparsed equation written in context " + context + " : " + text);
		return text;
	}

	private final String text;

}
