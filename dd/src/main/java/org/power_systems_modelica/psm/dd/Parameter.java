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
public abstract class Parameter
{
	public Parameter(String name)
	{
		this.name = name;
		this.unit = null;
	}

	public Parameter(String name, String unit)
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

	// Can the parameter be included in a template?
	// That is, the parameter contains enough information so it can be included in a template
	// Either because it is a reference to an external data source, a constant value for all instances, ...
	public abstract boolean isGeneric();

	private final String	name;
	private final String	unit;
}
