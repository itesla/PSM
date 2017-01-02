package org.power_systems_modelica.psm.workflow.psm.fake;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.engine.io.ModelicaSimulationResultsCsv;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
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
			workingDir = Paths.get(config.getParameter("modelicaEngineWorkingDir"));
		}
		catch (Exception e)
		{
			LOG.warn("Fake Modelica engine configured without fake simulation results");
		}
	}

	@Override
	public boolean validate(ModelicaDocument mo, int depth)
	{
		return true;
	}

	@Override
	public void simulate(ModelicaDocument mo)
	{
		String moFilename = mo.getSystemModel().getId() + ".mo";
		Path mof = workingDir.resolve(moFilename);
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		try (PrintWriter out = new PrintWriter(mof.toFile());)
		{
			mop.print(out);
			System.out.println("Modelica output sent to " + mof.toAbsolutePath().toString());
		}
		catch (IOException e)
		{
			System.err.println("Error writing Modelica file " + mof.toAbsolutePath().toString());
		}
	}

	@Override
	public void simulate(Collection<ModelicaDocument> mos)
	{
		// TODO Just as an exercise, do it in parallel
		// But be careful with using parallel (https://dzone.com/articles/think-twice-using-java-8)
		mos.forEach(mo -> simulate(mo));
	}

	@Override
	public ModelicaSimulationFinalResults getSimulationResults()
	{
		return results;
	}

	@Override
	public void close() throws Exception
	{
	}

	ModelicaSimulationFinalResults	results;
	Path							workingDir;

	private static final Logger		LOG	= LoggerFactory.getLogger(FakeModelicaEngine.class);
}
