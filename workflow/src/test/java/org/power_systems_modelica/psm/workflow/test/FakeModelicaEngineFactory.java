package org.power_systems_modelica.psm.workflow.test;

import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineFactory;

import com.google.auto.service.AutoService;

@AutoService(ModelicaEngineFactory.class)
public class FakeModelicaEngineFactory implements ModelicaEngineFactory
{
	@Override
	public String getEngine()
	{
		return "Fake";
	}

	@Override
	public ModelicaEngine create()
	{
		return new FakeModelicaEngine();
	}
}
