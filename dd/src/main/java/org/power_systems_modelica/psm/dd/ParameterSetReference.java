package org.power_systems_modelica.psm.dd;

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
public class ParameterSetReference
{
	public ParameterSetReference(String container, String set)
	{
		this.container = container;
		this.set = set;
	}

	public String getContainer()
	{
		return container;
	}

	public String getSet()
	{
		return set;
	}

	private final String	container;
	private final String	set;
}
