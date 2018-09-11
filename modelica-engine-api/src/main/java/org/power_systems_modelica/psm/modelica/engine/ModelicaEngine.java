package org.power_systems_modelica.psm.modelica.engine;

/*
 * #%L
 * Modelica Engine API
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Collection;
import java.util.Properties;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public interface ModelicaEngine extends AutoCloseable
{
	Properties loadDefaultProperties();
	
	void configure(Configuration config);

	void simulate(ModelicaDocument mo) throws Exception;

	void simulate(Collection<ModelicaDocument> mos) throws Exception;

	ModelicaSimulationFinalResults getSimulationResults();
	
	ModelicaEngineProgress getModelicaEngineProgress();
	
	void progress(String message);
	
	Logs getLogs();
}
