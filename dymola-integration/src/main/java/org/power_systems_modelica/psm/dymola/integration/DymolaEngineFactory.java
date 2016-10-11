package org.power_systems_modelica.psm.dymola.integration;

import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineFactory;

public class DymolaEngineFactory implements ModelicaEngineFactory {

	@Override
	public String getEngine() {
		return "DYMOLA";
	}

	@Override
	public ModelicaEngine create() {
		return new DymolaEngine();
	}

}
