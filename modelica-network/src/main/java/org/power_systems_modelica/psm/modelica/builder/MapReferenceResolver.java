package org.power_systems_modelica.psm.modelica.builder;

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

import java.util.Map;

import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class MapReferenceResolver implements ReferenceResolver
{
	public MapReferenceResolver(Map<String, Object> map)
	{
		this.map = map;
	}

	@Override
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			BaseModelicaDeclaration d) throws UnresolvedReferenceException
	{
		if (!map.containsKey(a.getSourceName())) throw new UnresolvedReferenceException(a);
		return map.get(a.getSourceName());
	}

	private final Map<String, Object> map;
}
