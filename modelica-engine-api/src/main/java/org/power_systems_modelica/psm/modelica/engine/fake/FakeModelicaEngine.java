package org.power_systems_modelica.psm.modelica.engine.fake;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineProgress;
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
			Path fakef = Paths
					.get(Optional.ofNullable(config.getParameter("fakeModelicaEngineResults"))
							.orElse(this.properties.getProperty("fakeModelicaEngineResults")));
			this.results = ModelicaSimulationResultsCsv.read(fakef);
			workingDir = Paths.get(Optional.of(config.getParameter("modelicaEngineWorkingDir"))
					.orElse(this.properties.getProperty("modelicaEngineWorkingDir")));
		}
		catch (Exception e)
		{
			LOG.warn("Fake Modelica engine configured without fake simulation results");
		}
	}

	@Override
	public void simulate(ModelicaDocument mo)
	{
		String moFilename = mo.getSystemModel().getId() + ".mo";
		Path mof = workingDir.resolve(moFilename);
		try
		{
			ModelicaTextPrinter.print(mo, mof, false);
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

	@Override
	public void progress(String message)
	{
	}

	@Override
	public ModelicaEngineProgress getModelicaEngineProgress()
	{
		return engineProgress;
	}

	@Override
	public Properties loadDefaultProperties()
	{
		Properties properties = new Properties();
		try (InputStream inputStream = Files.newInputStream(DEF_PROPERTIES))
		{
			properties.load(inputStream);

			Path fakef = Paths.get(properties.getProperty("fakeModelicaEngineResults"));
			this.results = ModelicaSimulationResultsCsv.read(fakef);
			this.workingDir = Paths.get(properties.getProperty("modelicaEngineWorkingDir"));
		}
		catch (IOException e)
		{
			LOG.error("", e.getMessage());
		}
		return properties;
	}

	private Properties				properties		= loadDefaultProperties();
	private ModelicaEngineProgress	engineProgress;
	ModelicaSimulationFinalResults	results;
	Path							workingDir;

	private static final Path		DEF_PROPERTIES	= Paths.get(System.getenv("PSM_DATA"))
			.resolve("test").resolve("cfg").resolve("modelicaengine.properties");
	private static final Logger		LOG				= LoggerFactory
			.getLogger(FakeModelicaEngine.class);
}
