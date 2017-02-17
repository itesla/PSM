package org.psm.openmodelica.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class OpenModelicaIntegrationTest
{

	@Test
	public void testSinglegen() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "[a-zA-Z0-9_]*.(pin_EFD|pin_OMEGA|pin_CM|omegaRef)";
		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("singlegen").resolve("library").toString(), 
				"false");
		config.setParameter("simFlags", "-lv LOG_STATS");
		config.setParameter("resultVariables", filterResVariables);
		
		testBuild(config, "singlegen", "singlegen.mo", 6, false);
	}

	@Test
	public void testSmallTestCase1() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), 
				"false");

		testBuild(config, "smallcase1", "case1_no_lf.mo", 8, false);
	}

//	@Test // TODO This system does not check
	public void testSmallTestCase2() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), 
				"false");

		testBuild(config, "smallcase2", "case2_no_lf.mo", 8, true);
	}

	@Test
	public void testSmallTestCase3() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				"false");

		testBuild(config, "smallcase3", "case3_no_lf.mo", 8, false);
	}

	@Test // FIXME This system does not simulate
	public void test7buses() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				"false");

		testBuild(config, "7buses", "M7buses_no_lf.mo", 16, false);	
	}

	 @Test
	public void testNordic32() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				"false");

		testBuild(config, "Nordic32", "Nordic32_no_lf.mo", 106, false);
	}

	@Test
	public void testAllIEEE() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		List<ModelicaDocument> moDocsList = new ArrayList<ModelicaDocument>();
		moDocsList.add(ModelicaParser
				.parse(DATA_TEST.resolve("ieee14").resolve("itesla").resolve("ieee14bus_no_lf.mo")));
		moDocsList.add(ModelicaParser
				.parse(DATA_TEST.resolve("ieee30").resolve("itesla").resolve("ieee30bus_no_lf.mo")));
		 moDocsList.add(ModelicaParser
		 .parse(DATA_TEST.resolve("ieee57").resolve("itesla").resolve("ieee57bus_no_lf.mo")));

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), 
				"false");

		try(OpenModelicaEngine omEngine = new OpenModelicaEngine()) {
			omEngine.configure(config);
			omEngine.simulate(moDocsList);

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			assertTrue(results.getValue("ieee14bus", "simulation_path") != null);
			assertTrue(results.getValue("ieee30bus", "simulation_path") != null);
			 assertTrue(results.getValue("ieee57bus", "simulation_path") != null);
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
	}

	@Test
	public void testIEEE14() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		
		//Regular expression for the software-to-software validation
//		String filterResVariables = "[a-zA-Z0-9_]*((TN.(V|angle))|(EC.(P|Q))|(SM.(efd|cm|lambdad|lambdaf|lambdaq1|lambdaq2)))";
		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				"true");

		config.setParameter("stopTime", "1.5");
		
		testBuild(config, "ieee14", "ieee14bus_no_lf.mo", 30, false);
	}

	@Test
	public void testIEEE30() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), 
				"false");

		testBuild(config, "ieee30", "ieee30bus_no_lf.mo", 62, false);
	}

	 @Test
	public void testIEEE57() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), 
				"false");

		testBuild(config, "ieee57", "ieee57bus_no_lf.mo", 116, false);
	}

	 @Test //TODO PENDING
	public void testIEEE118() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable())
			return;

		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("ieee118").resolve("library").toString(),
				"false");

		testBuild(config, "ieee118", "ieee118bus_no_lf.mo", 238, true);
	}

	private void testBuild(Configuration config,
			String folderName,
			String moFileName,
			int numOfResults,
			boolean failsSimulation) throws FileNotFoundException, IOException
	{

		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));
		String moName = mo.getSystemModel().getId();
		
		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "0");
			omEngine.configure(config);
			omEngine.simulate(mo);

			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

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

	private static final Path	DATA_TEST	= Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path	DATA_TMP	= Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
}
