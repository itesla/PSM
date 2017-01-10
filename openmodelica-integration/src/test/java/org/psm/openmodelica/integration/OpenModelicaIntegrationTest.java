package org.psm.openmodelica.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("singlegen").resolve("library").toString(), filterResVariables,
				"0.0", "1.0", "0.000001", "500");
		config.setParameter("simFlags", "-lv LOG_STATS");
		
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "singlegen", "singlegen.mo", 6, false);
	}

//	@Test
	public void testSmallTestCase1() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "smallcase1", "case1.mo", 8, false);
	}

//	@Test // FIXME This system does not simulate
	public void testSmallTestCase2() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		// FIXME This system does not simulate because the InfiniteBus Modelica model is on progress.
		testBuild(config, "smallcase2", "case2.mo", 8, true);
	}

	@Test // FIXME This system does not simulate due to a division by zero
	public void testSmallTestCase3() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "smallcase3", "case3.mo", 8, true);
	}

	@Test // FIXME This system does not simulate
	public void test7buses() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "7buses", "CIM_7buses.mo", 16, true);
	}

	// @Test //It takes long time to simulate 1s
	public void testNordic32() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "Nordic32", "Nordic32.mo", 106, false);
	}

	@Test
	public void testAllIEEE() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		List<ModelicaDocument> moDocsList = new ArrayList<ModelicaDocument>();
		moDocsList.add(ModelicaParser
				.parse(DATA_TEST.resolve("ieee14").resolve("itesla").resolve("ieee14bus.mo")));
		moDocsList.add(ModelicaParser
				.parse(DATA_TEST.resolve("ieee30").resolve("itesla").resolve("ieee30bus.mo")));
		// moDocsList.add(ModelicaParser
		// .parse(DATA_TEST.resolve("ieee57").resolve("itesla").resolve("ieee57bus.mo")));

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		try(OpenModelicaEngine omEngine = new OpenModelicaEngine()) {
			omEngine.configure(config);
			omEngine.simulate(moDocsList);

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			assertTrue(results.getValue("ieee14bus", "simulation_path") != null);
			assertTrue(results.getValue("ieee30bus", "simulation_path") != null);
			// assertTrue(results.getValue("ieee57bus", "simulation_path") != null);
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
		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "ieee14", "ieee14bus.mo", 30, false);
	}

	@Test
	public void testIEEE30() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "ieee30", "ieee30bus.mo", 62, false);
	}

	// @Test
	public void testIEEE57() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(), filterResVariables, "0.0", "1.0",
				"0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "ieee57", "ieee57bus.mo", 116, false);
	}

	// @Test //TODO PENDING
	public void testIEEE118() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable())
			return;

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(DATA_TMP.toString(),
				DATA_TEST.resolve("ieee118").resolve("library").toString(), filterResVariables,
				"0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "false");

		testBuild(config, "ieee118", "ieee118bus.mo", 238, false);
	}

	private void testBuild(Configuration config,
			String folderName,
			String moFileName,
			int numOfResults,
			boolean failsSimulation) throws FileNotFoundException, IOException
	{

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			omEngine.configure(config);
			boolean validated = omEngine.validate(mo, 2);
			if (validated) omEngine.simulate(mo);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			Path omSimPath = (Path) omEngine.getSimulationResults()
					.getValue(mo.getSystemModel().getId(), "simulation_path");
			if (!failsSimulation)
			{
				//If a validation is performed before the dynamic simulation the "omvalidation_" directory is saved in the results (numOfResults+1).
				assertTrue(results.getEntries().size() == numOfResults+1);
				assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
				assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.csv")));
				if (config.getBoolean("createFilteredMat"))
					assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.mat")));
			}
			else
			{
				assertNull(omSimPath);
			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	private Configuration setConfiguration(String modelicaEngineWorkingDir, String libraryDir,
			String resultVariables,
			String startTime, String stopTime, String tolerance, String numOfIntervalsPerSecond)
	{
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir);
		config.setParameter("libraryDir", libraryDir);
		config.setParameter("resultVariables", resultVariables);

		config.setParameter("startTime", startTime);
		config.setParameter("stopTime", stopTime);
		config.setParameter("tolerance", tolerance);

		config.setParameter("numOfIntervalsPerSecond", numOfIntervalsPerSecond);

		return config;
	}

	private boolean isOpenModelicaAvailable()
	{
		return Boolean.valueOf(System.getProperty("OpenModelicaAvailable"));
	}

	private static final Path	DATA_TEST	= Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path	DATA_TMP	= Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
}
