package org.power_systems_modelica.psm.dd.equations;

/*
 * #%L
 * Dynamic Data
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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