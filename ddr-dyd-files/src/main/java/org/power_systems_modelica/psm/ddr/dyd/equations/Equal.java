package org.power_systems_modelica.psm.ddr.dyd.equations;

public class Equal implements Equation
{
	public Equal(Expression left, Expression right)
	{
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

	private final Expression	left;
	private final Expression	right;
}