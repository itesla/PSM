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
