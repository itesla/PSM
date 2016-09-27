package org.power_systems_modelica.psm.ddr.dyd.equations;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList implements Factors
{
	public ExpressionList()
	{
		this.expressions = new ArrayList<>();
	}

	public void add(Expression e)
	{
		expressions.add(e);
	}

	public List<Expression> get()
	{
		return expressions;
	}

	@Override
	public <T> List<Expression> getFrom(Context<T> context)
	{
		return expressions;
	}

	private final List<Expression> expressions;
}
