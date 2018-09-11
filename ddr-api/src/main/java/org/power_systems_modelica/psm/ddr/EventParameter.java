package org.power_systems_modelica.psm.ddr;

/*
 * #%L
 * Dynamic Data Repository API
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
public class EventParameter
{
	public EventParameter(String name, String unit)
	{
		this.name = name;
		this.unit = unit;
	}

	public String getName()
	{
		return name;
	}

	public String getUnit()
	{
		return unit;
	}

	private final String	name;
	private final String	unit;
}
