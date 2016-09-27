package org.power_systems_modelica.psm.ddr.dyd.equations;

public class Literal implements Expression
{
	public Literal(String literal)
	{
		this.literal = literal;
	}

	public String get()
	{
		return literal;
	}

	@Override
	public String writeIn(Context<?> context)
	{
		return literal;
	}

	private final String literal;
}