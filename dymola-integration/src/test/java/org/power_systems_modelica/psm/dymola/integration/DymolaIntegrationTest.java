package org.power_systems_modelica.psm.dymola.integration;

/*
 * #%L
 * Dynamic simulation using Dymola
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
public class DymolaIntegrationTest
{


	@Test // XXX Only with NO Trial Version of Dymola
	public void testSmallTestCase1() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable())
			return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "smallcase1", "case1_no_lf.mo", 8);
	}

	@Test // XXX Only with NO Trial Version of Dymola
	public void testSmallTestCase2() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable())
			return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "smallcase2", "case2.mo", 8);
	}

	@Test // XXX Only with NO Trial Version of Dymola
	public void testSmallTestCase3() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "smallcase3", "case3.mo", 8);
	}

	@Test // XXX Only with NO Trial Version of Dymola
	public void testSmallTestCase4() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "smallcase4", "case4.mo", 8);
	}

	@Test // XXX Only with NO Trial Version of Dymola
	public void test7buses() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "7buses", "M7buses.mo", 8);
	}

//	 @Test //XXX Only with NO Trial Version of Dymola
	public void testNordic32() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "Nordic32", "Nordic32.mo", 8);
	}

//	 @Test //XXX Only with NO Trial Version of Dymola
	public void testAllIEEE() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		List<ModelicaDocument> moDocsList = new ArrayList<ModelicaDocument>();
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("ieee14").resolve("itesla")
						.resolve("ieee14bus.mo")));
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("ieee30").resolve("itesla")
						.resolve("ieee30bus.mo")));
		moDocsList.add(ModelicaParser
				.parse(PathUtils.DATA_TEST.resolve("ieee57").resolve("itesla")
						.resolve("ieee57bus.mo")));

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"false");

		try (DymolaEngine dymEngine = new DymolaEngine())
		{
			dymEngine.configure(config);
			dymEngine.simulate(moDocsList);

			ModelicaSimulationFinalResults results = dymEngine.getSimulationResults();
			assertTrue(results.getValue("ieee14bus", "simulation_path") != null);
			System.out.println("IEEE14 simulation directory : "
					+ results.getValue("ieee14bus", "simulation_path"));
			assertTrue(results.getValue("ieee30bus", "simulation_path") != null);
			System.out.println("IEEE14 simulation directory : "
					+ results.getValue("ieee30bus", "simulation_path"));
			assertTrue(results.getValue("ieee57bus", "simulation_path") != null);
			System.out.println("IEEE14 simulation directory : "
					+ results.getValue("ieee57bus", "simulation_path"));
			assertTrue(results.getValue("ieee118bus", "simulation_path") != null);
			System.out.println("IEEE14 simulation directory : "
					+ results.getValue("ieee118bus", "simulation_path"));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	 @Test //XXX Only with NO Trial Version of Dymola
	public void testIEEE14() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "ieee14", "ieee14bus.mo", 30);
	}

	 @Test //XXX Only with NO Trial Version of Dymola
	public void testIEEE30() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "ieee30", "ieee30bus.mo", 60);
	}

//	 @Test //XXX Only with NO Trial Version of Dymola
	public void testIEEE57() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.LIBRARY.toString(),
				"true");

		testBuild("test", config, "ieee57", "ieee57bus.mo", 116);
	}

//	 @Test //XXX Only with NO Trial Version of Dymola
	public void testIEEE118() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		Configuration config = setConfiguration(
				PathUtils.DATA_TMP.toString(),
				PathUtils.DATA_TEST.resolve("ieee118bus").resolve("library").toString(),
				"true");

		testBuild("test", config, "ieee118", "ieee118bus.mo", 238);
	}

	private void testBuild(
			String catalog,
			Configuration config,
			String folderName,
			String moFileName,
			int numOfResults) throws FileNotFoundException, IOException
	{
		String moName = moFileName.contains("no_lf") ? moFileName.substring(0, moFileName.indexOf("_")) : moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(PathUtils.DATA.resolve(catalog).resolve(folderName).resolve("itesla")
						.resolve(moFileName));

		try (DymolaEngine dymEngine = new DymolaEngine())
		{
			config.setParameter("depth", "0");
			dymEngine.configure(config);
			dymEngine.simulate(mo);

			ModelicaSimulationFinalResults results = dymEngine.getSimulationResults();
			assertTrue(results.getEntries().size() > 1);

			assertEquals(moName, mo.getSystemModel().getId());

			Path dymSimPath = (Path) dymEngine.getSimulationResults()
					.getValue(mo.getSystemModel().getId(), "simulation_path");
			assertTrue(Files.exists(dymSimPath.resolve(moName + "_res.csv")));
			if (config.getBoolean("createFilteredMat"))
				assertTrue(Files.exists(dymSimPath.resolve(moName + "_res_filtered.mat")));
			assertTrue(Files.exists(dymSimPath.resolve(moName + ".zip")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	private Configuration setConfiguration(
			String modelicaEngineWorkingDir,
			String libraryDir,
			String createFilteredMath)
	{
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir);
		config.setParameter("libraryDir", libraryDir);
		config.setParameter("createFilteredMat", createFilteredMath);

		return config;
	}

	private boolean isDymolaAvailable()
	{
		return Boolean.valueOf(System.getProperty("DymolaAvailable"));
	}
}
