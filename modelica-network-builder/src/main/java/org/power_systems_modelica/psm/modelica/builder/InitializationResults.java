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

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class InitializationResults
{
	public InitializationResults(ModelicaSimulationFinalResults results)
	{
		this.results = results;
	}

	public Object get(String staticId, String name) throws Exception
	{
		if (results == null)
		{
			String msg = String.format(
					"Initialization results not available. staticId = %s, name = %s",
					staticId,
					name);
			LOG.error(msg);
			throw new Exception(msg);
		}
		return results.getValue(staticId, name);
	}

	private final ModelicaSimulationFinalResults	results;

	private static final Logger						LOG	= LoggerFactory
																.getLogger(InitializationResults.class);
}
