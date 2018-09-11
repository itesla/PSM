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