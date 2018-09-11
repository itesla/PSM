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

import org.power_systems_modelica.psm.dd.StaticType;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelForType extends Model
{
	public ModelForType(StaticType type, String baseId)
	{
		super(baseId);
		this.type = type;
	}

	public StaticType getType()
	{
		return type;
	}

	@Override
	public String toString()
	{
		return "Type " + type;
	}

	private final StaticType type;
}
