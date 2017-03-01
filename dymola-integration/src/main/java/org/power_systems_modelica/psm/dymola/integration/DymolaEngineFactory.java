package org.power_systems_modelica.psm.dymola.integration;

import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineFactory;

import com.google.auto.service.AutoService;

@AutoService(ModelicaEngineFactory.class)
public class DymolaEngineFactory implements ModelicaEngineFactory {

	@Override
	public String getEngine() {
		return "Dymola";
	}

	@Override
	public ModelicaEngine create() {
		return new DymolaEngine();
	}

}
