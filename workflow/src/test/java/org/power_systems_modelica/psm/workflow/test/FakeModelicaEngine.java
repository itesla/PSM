package org.power_systems_modelica.psm.workflow.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.power_systems_modelica.psm.modelica.engine.io.ModelicaSimulationResultsCsv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeModelicaEngine implements ModelicaEngine
{
	@Override
	public void configure(Configuration config)
	{
		try
		{
			Path fakef = Paths.get(config.getParameter("fakeModelicaEngineResults"));
			this.results = ModelicaSimulationResultsCsv.read(fakef);
		}
		catch (Exception e)
		{
			LOG.warn("Fake Modelica engine configured without fake simulation results");
		}
	}

	@Override
	public void simulate(ModelicaDocument mo)
	{
	}

	@Override
	public void simulate(Collection<ModelicaDocument> mos)
	{
	}

	@Override
	public ModelicaSimulationResults getSimulationResults()
	{
		return results;
	}

	ModelicaSimulationResults	results;

	private static final Logger	LOG	= LoggerFactory.getLogger(FakeModelicaEngine.class);
}
