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

// FIXME PrefixSelector is a simple selector, build a selector based on type of Modelica element
/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class PrefixSelector implements Selector
{
	public PrefixSelector(String prefix)
	{
		this.prefix = prefix;
	}

	public String getPrefix()
	{
		return prefix;
	}

	@Override
	public String toString()
	{
		return "startsWith(" + prefix + ")";
	}

	private final String prefix;
}