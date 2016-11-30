package org.power_systems_modelica.psm.ddr.dyd.equations;

public class Quotient implements Expression
{
	public Quotient(Expression dividend, Expression divisor)
	{
		this.dividend = dividend;
		this.divisor = divisor;
	}

	public Expression getDividend()
	{
		return dividend;
	}

	public Expression getDivisor()
	{
		return divisor;
	}

	@Override
	public String writeIn(Context<?> context)
	{
		return dividend.writeIn(context)
				.concat(" / ")
				.concat(divisor.writeIn(context));
	}

	@Override
	public String toString()
	{
		return "( " + dividend + " / " + divisor + " )";
	}

	private final Expression	dividend;
	private final Expression	divisor;
}