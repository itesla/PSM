package org.power_systems_modelica.psm.modelica;

/*
 * #%L
 * Modelica network model
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
public class ModelicaArgument
{
	public ModelicaArgument(String name, Object value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public Object getValue()
	{
		return value;
	}

	private final String	name;
	private final Object	value;
}
