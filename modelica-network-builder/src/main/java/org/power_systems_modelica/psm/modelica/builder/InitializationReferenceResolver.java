package org.power_systems_modelica.psm.modelica.builder;

/*
 * #%L
 * Modelica network builder
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class InitializationReferenceResolver implements ReferenceResolver
{
	public InitializationReferenceResolver(InitializationResults results)
	{
		this.results = results;
	}

	@Override
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			BaseModelicaDeclaration d)
			throws UnresolvedReferenceException
	{
		// Solve the reference to initialization data in the context of given model and declaration
		Object value;
		try
		{
			value = results.get(ModelicaUtil.normalizedIdentifier(m.getStaticId()), a.getSourceName());
			return value;
		}
		catch (Exception e)
		{
			String msg = String.format(
					"Unresolved initialization results reference. staticId = %s, name = %s; inside declaration d = %s",
					m.getStaticId(),
					a.getSourceName(),
					d.getId());
			LOG.error(msg);
			throw new UnresolvedReferenceException(a);
		}
	}

	private final InitializationResults	results;

	private static final Logger			LOG	= LoggerFactory
			.getLogger(InitializationReferenceResolver.class);
}
