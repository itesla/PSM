package org.psm.openmodelica.integration;

/*
 * #%L
 * Dynamic simulation using OpenModelica
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineFactory;

import com.google.auto.service.AutoService;

/**
 * @author Silvia Machado <machados at aia.es>
 */
@AutoService(ModelicaEngineFactory.class)
public class OpenModelicaEngineFactory implements ModelicaEngineFactory {

	@Override
	public String getEngine() {
		return "OpenModelica";
	}

	@Override
	public ModelicaEngine create() {
		return new OpenModelicaEngine();
	}

}
