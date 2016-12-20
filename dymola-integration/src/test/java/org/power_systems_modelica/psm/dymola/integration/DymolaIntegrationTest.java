package org.power_systems_modelica.psm.dymola.integration;

import static org.junit.Assert.assertEquals;
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

public class DymolaIntegrationTest
{

	@Test
	public void testSinglegen() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "[a-zA-Z0-9_]*.(pin_EFD|pin_OMEGA|pin_CM|omegaRef)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(),
				DATA_TEST.resolve("singlegen").resolve("library").toString(),
				varResults, "0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "singlegen", "singlegen.mo", 6);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testSmallTestCase1() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				varResults, "0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "smallcase1", "case1.mo", 8);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testSmallTestCase2() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				varResults, "0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "smallcase1", "case1.mo", 8);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testSmallTestCase3() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				varResults, "0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "smallcase1", "case1.mo", 8);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void test7buses() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				varResults, "0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "7buses", "CIM_7buses.mo", 8);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testNordic32() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				varResults, "0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "Nordic32", "Nordic32.mo", 8); // TODO PENDING MODIFY NUMBER OF RESULTS
	}

	// @Test //TODO Pending for now because these systems do not simulate with Dymola Trial Version
	public void testAllIEEE() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		List<ModelicaDocument> moDocsList = new ArrayList<ModelicaDocument>();
		moDocsList.add(ModelicaParser
				.parse(DATA_TEST.resolve("ieee14").resolve("itesla").resolve("ieee14bus.mo")));
		moDocsList.add(ModelicaParser
				.parse(DATA_TEST.resolve("ieee30").resolve("itesla").resolve("ieee30bus.mo")));
		moDocsList.add(ModelicaParser
				.parse(DATA_TEST.resolve("ieee57").resolve("itesla").resolve("ieee57bus.mo")));

		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";

		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				filterResVariables,
				"0.0",
				"1.0",
				"0.000001",
				"500");

		config.setParameter("createFilteredMat", "false");

		DymolaEngine omEngine = new DymolaEngine();
		omEngine.configure(config);
		omEngine.simulate(moDocsList);

		ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
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

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testIEEE14() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(), DATA_TEST.resolve("library").toString(), varResults, "0.0",
				"1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "ieee14", "ieee14bus.mo", 30);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testIEEE30() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(), DATA_TEST.resolve("library").toString(), varResults, "0.0",
				"1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "ieee30", "ieee30bus.mo", 60);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testIEEE57() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(), DATA_TEST.resolve("library").toString(), varResults, "0.0",
				"1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "ieee57", "ieee57bus.mo", 116);
	}

	// @Test //TODO Pending for now because this system does not simulate with Dymola Trial Version
	public void testIEEE118() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		Configuration config = setConfiguration(
				"http://localhost:8888/dymservice?wsdl",
				DATA_TMP.toString(), DATA_TEST.resolve("ieee118bus").resolve("library").toString(),
				varResults, "0.0", "1.0", "0.000001", "500");
		config.setParameter("createFilteredMat", "true");

		testBuild(config, "ieee118", "ieee118bus.mo", 238);
	}

	private void testBuild(
			Configuration config,
			String folderName,
			String moFileName,
			int numOfResults) throws FileNotFoundException, IOException
	{
		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulate(mo);
		dymEngine.close();

		ModelicaSimulationFinalResults results = dymEngine.getSimulationResults();
		assertTrue(results.getEntries().size() == numOfResults);

		assertEquals(moName, mo.getSystemModel().getId());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

		Path dymSimPath = (Path) dymEngine.getSimulationResults()
				.getValue(mo.getSystemModel().getId(), "simulation_path");
		assertTrue(Files.exists(dymSimPath.resolve(moName + "_res.mat")));
		assertTrue(Files.exists(dymSimPath.resolve(moName + "_res_filtered.csv")));
		if (config.getBoolean("createFilteredMat"))
			assertTrue(Files.exists(dymSimPath.resolve(moName + "_res_filtered.mat")));
		assertTrue(Files.exists(dymSimPath.resolve(moName + "_in.zip")));
		assertTrue(Files.exists(dymSimPath.resolve(moName + "_out.zip")));
	}

	private Configuration setConfiguration(
			String webService,
			String modelicaEngineWorkingDir,
			String libraryDir,
			String resultVariables,
			String startTime,
			String stopTime,
			String tolerance,
			String numOfIntervalsPerSecond)
	{
		Configuration config = new Configuration();
		config.setParameter("webService", webService);
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir);
		config.setParameter("libraryDir", libraryDir);
		config.setParameter("resultVariables", resultVariables);

		config.setParameter("startTime", startTime);
		config.setParameter("stopTime", stopTime);
		config.setParameter("tolerance", tolerance);

		config.setParameter("numOfIntervalsPerSecond", numOfIntervalsPerSecond);

		return config;
	}

	private boolean isDymolaAvailable()
	{
		return Boolean.valueOf(System.getProperty("DymolaAvailable"));
	}

	private static final Path	DATA_TEST	= Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path	DATA_TMP	= Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
}
