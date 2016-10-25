package org.psm.openmodelica.integration;

import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineFactory;

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
