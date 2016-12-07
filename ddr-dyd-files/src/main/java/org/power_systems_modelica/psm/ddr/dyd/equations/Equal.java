package org.power_systems_modelica.psm.ddr.dyd.equations;

public class Equal extends Equation
{
	public Equal(Expression left, Expression right)
	{
		super();
		this.left = left;
		this.right = right;
	}

	public Expression getLeft()
	{
		return left;
	}

	public Expression getRight()
	{
		return right;
	}

	@Override
	public String writeIn(Context<?> domain)
	{
		return left.writeIn(domain) + " = " + right.writeIn(domain);
	}

	@Override
	public String toString()
	{
		return "( " + left + " = " + right + " )";
	}

	private final Expression	left;
	private final Expression	right;
}