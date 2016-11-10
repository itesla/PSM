package org.psm.openmodelica.integration;

import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineFactory;

import com.google.auto.service.AutoService;

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
