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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class OpenModelicaStepsTest
{

	private String			folderName			= "ieee14";
	private String			moFileName			= "ieee14bus_no_lf.mo";
	private String			filterResVariables	= "bus[a-zA-Z0-9_]*.(V|angle)";
	private Configuration	config				= setConfiguration(PathUtils.DATA_TMP.toString(),
			PathUtils.LIBRARY.toString(), filterResVariables,
			"0.0", "1.0", "0.000001", "500");
	
	@Test
	public void testCheck() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		ModelicaDocument mo = ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));
		
		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "1");
			omEngine.configure(config);
			omEngine.simulate(mo);

			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testVerify() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		ModelicaDocument mo = ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));
		String moName = mo.getSystemModel().getId();

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "2");
			omEngine.configure(config);
			omEngine.simulate(mo);

			Path omSimPath = (Path) omEngine.getSimulationResults()
					.getValue(mo.getSystemModel().getId(), "simulation_path");

			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
			assertTrue(Files.exists(omSimPath.resolve(moName + ".log")));
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.csv")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testSimulation() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		ModelicaDocument mo = ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));
		String moName = mo.getSystemModel().getId();
		
		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "0");
			omEngine.configure(config);
			omEngine.simulate(mo);

			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());
			assertTrue(results.getEntries().size() > 1);

			Path omSimPath = (Path) omEngine.getSimulationResults()
					.getValue(mo.getSystemModel().getId(), "simulation_path");
			assertTrue(Files.exists(omSimPath.resolve(moName + ".log")));
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.csv")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	private Configuration setConfiguration(String modelicaEngineWorkingDir, String libraryDir,
			String resultVariables, String startTime, String stopTime, String tolerance,
			String numOfIntervalsPerSecond)
	{
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir);
		config.setParameter("libraryDir", libraryDir);
		config.setParameter("resultVariables", resultVariables);
		config.setParameter("startTime", startTime);
		config.setParameter("stopTime", stopTime);
		config.setParameter("tolerance", tolerance);
		config.setParameter("numOfIntervalsPerSecond", numOfIntervalsPerSecond);
		config.setParameter("simFlags", "-lv LOG_STATS");
		config.setParameter("createFilteredMat", "false");

		return config;
	}

	private boolean isOpenModelicaAvailable()
	{
		return Boolean.valueOf(System.getProperty("OpenModelicaAvailable"));
	}
}
