package org.power_systems_modelica.psm.modelica.events;

/*
 * #%L
 * Add events to existing Modelica network models
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.ReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedReferenceException;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class PreviousDynamicModelReferenceResolver implements ReferenceResolver
{
	public PreviousDynamicModelReferenceResolver(ModelicaModel m)
	{
		this.model = m;
	}

	@Override
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			BaseModelicaDeclaration d) throws UnresolvedReferenceException
	{
		String referred = a.getSourceName();

		// TODO We are looking in all declarations the first argument that has the sourceName
		// We could assume that the referred name has a declaration id and an argument name,
		// to be more explicit in our references
		
		for (BaseModelicaDeclaration d0 : model.getDeclarations())
		{
			for (ModelicaArgument a0 : d0.getArguments())
			{
				if (a0.getName().equals(referred))
					return a0.getValue();
			}
		}

		throw new UnresolvedReferenceException(a);
	}

	private final ModelicaModel model;

}
