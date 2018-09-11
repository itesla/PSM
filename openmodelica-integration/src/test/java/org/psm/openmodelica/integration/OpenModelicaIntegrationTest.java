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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class OpenModelicaIntegrationTest
{

	@Test
	public void testSmallTestCase1() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "smallcase1", "case1_no_lf.mo", 8, false);
	}

	@Test
	public void testSmallTestCase2() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "smallcase2", "case2_no_lf.mo", 8, false);
	}

	@Test
	public void testSmallTestCase3() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "smallcase3", "case3_no_lf.mo", 8, false);
	}

	@Test
	public void testSmallTestCase4() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "smallcase4", "case4_no_lf.mo", 8, false);
	}

	@Test
	public void testAllSmallTestCases() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		List<ModelicaDocument> moDocsList = new ArrayList<ModelicaDocument>();
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("smallcase1").resolve("itesla")
						.resolve("case1_no_lf.mo")));
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("smallcase2").resolve("itesla")
						.resolve("case2_no_lf.mo")));
//		 moDocsList.add(ModelicaParser
//		 .parse(PathUtils.DATA_TEST.resolve("smallcase3").resolve("itesla").resolve("case3_no_lf.mo")));
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("smallcase4").resolve("itesla")
						.resolve("case4_no_lf.mo")));

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			omEngine.configure(config);
			omEngine.simulate(moDocsList);

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			assertTrue(results.getValue("case1", "simulation_path") != null);
			assertTrue(results.getValue("case2", "simulation_path") != null);
			// assertTrue(results.getValue("case3", "simulation_path") != null);
			assertTrue(results.getValue("case4", "simulation_path") != null);
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}

	}

	@Test
	public void test7buses() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "7buses", "M7buses_no_lf.mo", 16, false);
	}

	@Test
	public void testNordic32() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "Nordic32", "Nordic32_no_lf.mo", 106, false);
	}

	@Test
	public void testAllIEEE() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		List<ModelicaDocument> moDocsList = new ArrayList<ModelicaDocument>();
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("ieee14").resolve("itesla")
						.resolve("ieee14bus_no_lf.mo")));
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("ieee30").resolve("itesla")
						.resolve("ieee30bus_no_lf.mo")));
		// moDocsList.add(ModelicaParser
		// .parse(PathUtils.DATA_TEST.resolve("ieee57").resolve("itesla").resolve("ieee57bus_no_lf.mo")));

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			omEngine.configure(config);
			omEngine.simulate(moDocsList);

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			assertTrue(results.getValue("ieee14bus", "simulation_path") != null);
			assertTrue(results.getValue("ieee30bus", "simulation_path") != null);
			// assertTrue(results.getValue("ieee57bus", "simulation_path") != null);
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}

	}

	@Test
	public void testIEEE14() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		// Regular expression for the software-to-software validation
		// String filterResVariables = "[a-zA-Z0-9_]*((TN.(V|angle))|(EC.(P|Q))|(SM.(efd|cm|lambdad|lambdaf|lambdaq1|lambdaq2)))";
		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		config.setParameter("stopTime", "1.0");

		testBuild(config, "test", "ieee14", "ieee14bus_no_lf.mo", 30, false);
	}

	@Test
	public void testIEEE30() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "ieee30", "ieee30bus_no_lf.mo", 62, false);
	}

	@Test
	public void testIEEE57() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		testBuild(config, "test", "ieee57", "ieee57bus_no_lf.mo", 116, false);
	}

	@Test // TODO PENDING
	public void testIEEE118() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable())
			return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.DATA_TEST.resolve("ieee118").resolve("library").toString(),
				"false");

		testBuild(config, "test", "ieee118", "ieee118bus_no_lf.mo", 238, true);
	}

	private void testBuild(Configuration config,
			String catalog,
			String folderName,
			String moFileName,
			int numOfResults,
			boolean failsSimulation) throws FileNotFoundException, IOException
	{

		ModelicaDocument mo = ModelicaParser
				.parse(PathUtils.DATA.resolve(catalog).resolve(folderName).resolve("itesla").resolve(moFileName));
		String moName = mo.getSystemModel().getId();

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "0");
			config.setParameter("stopTime", "0.0001");
			omEngine.configure(config);
			omEngine.simulate(mo);

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			Path omSimPath = (Path) omEngine.getSimulationResults()
					.getValue(moName, "simulation_path");

			if (!failsSimulation)
			{
				assertTrue(results.getEntries().size() > 1);
				assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
				assertTrue(Files.exists(omSimPath.resolve(moName + "_res.csv")));
				if (config.getBoolean("createFilteredMat"))
					assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.mat")));
			}
			else
			{
				assertFalse(Files.exists(omSimPath.resolve(moName + "_res.csv")));
			}

		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	private Configuration setConfiguration(
			String modelicaEngineWorkingDir,
			String libraryDir,
			String createFilteredMat)
	{
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir);
		config.setParameter("libraryDir", libraryDir);
		config.setParameter("createFilteredMat", createFilteredMat);

		return config;
	}

	private boolean isOpenModelicaAvailable()
	{
		return Boolean.valueOf(System.getProperty("OpenModelicaAvailable"));
	}
}
