package org.power_systems_modelica.psm.modelica.engine.fake;

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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineProgress;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.engine.io.ModelicaSimulationResultsCsv;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
			LOG.info("Modelica output sent to " + mof.toAbsolutePath().toString());
		}
		catch (IOException e)
		{
			LOG.error("Error writing Modelica file " + mof.toAbsolutePath().toString());
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
		try (InputStream inputStream = Files.newInputStream(PathUtils.CONFIG
				.resolve("modelicaengine.properties")))
		{
			properties.load(inputStream);

			String fakeResultsName = properties.getProperty("fakeModelicaEngineResults");
			if (fakeResultsName != null)
			{
				Path fakef = Paths.get(fakeResultsName);
				this.results = ModelicaSimulationResultsCsv.read(fakef);
			}
			String workingDirName = properties.getProperty("modelicaEngineWorkingDir");
			if (workingDirName != null) this.workingDir = Paths.get(workingDirName);
		}
		catch (IOException e)
		{
			LOG.error("", e.getMessage());
		}
		return properties;
	}

	@Override
	public Logs getLogs()
	{
		return new Logs("Fake Modelica engine interaction");
	}

	private Properties				properties		= loadDefaultProperties();
	private ModelicaEngineProgress	engineProgress	= new ModelicaEngineProgress();
	ModelicaSimulationFinalResults	results;
	Path							workingDir;

	private static final Logger		LOG				= LoggerFactory
			.getLogger(FakeModelicaEngine.class);
}
