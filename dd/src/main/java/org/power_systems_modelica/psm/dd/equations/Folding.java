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

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class Folding implements Expression
{
	protected Folding(Operation operation, Factors factors)
	{
		this.operation = operation;
		this.factors = factors;
	}

	public static class Product extends Folding
	{
		public Product(Factors factors)
		{
			super(Operation.PRODUCT, factors);
		}
	}

	public static class Sum extends Folding
	{
		public Sum(Factors factors)
		{
			super(Operation.SUM, factors);
		}
	}

	static enum Operation
	{
		SUM, PRODUCT;

		static final String[]	SYMBOLS		= { "+", "*" };
		static final String[]	DOC_SYMBOLS	= { "∑", "∏" };
	}

	public Operation getOperation()
	{
		return operation;
	}

	public Factors getFactors()
	{
		return factors;
	}

	public String writeIn(Context<?> context)
	{
		Objects.requireNonNull(factors);
		String symbol = Operation.SYMBOLS[operation.ordinal()];
		String joinWith = " ".concat(symbol).concat(" ");
		return "("
				.concat(factors.getFrom(context)
						.stream()
						.map(e -> e.writeIn(context))
						.collect(Collectors.joining(joinWith)))
				.concat(")");
	}

	@Override
	public String toString()
	{
		return Operation.DOC_SYMBOLS[operation.ordinal()] + " " + factors;
	}

	private final Operation	operation;
	private final Factors	factors;
}