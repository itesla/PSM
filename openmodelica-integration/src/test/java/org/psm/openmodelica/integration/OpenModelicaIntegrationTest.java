package org.psm.openmodelica.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class OpenModelicaIntegrationTest {

	@Test
	public void testSinglegen() throws FileNotFoundException, IOException {
		if(!isOpenModelicaAvailable()) return;
		
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST
													.resolve("singlegen")
													.resolve("itesla")
													.resolve("singlegen.mo"));
		
		String filterResVariables = "[a-zA-Z0-9_]*.(pin_EFD|pin_OMEGA|pin_CM|omegaRef)";
		
		Configuration config = setConfiguration(
												DATA_TMP.toString(),
												DATA_TEST.resolve("singlegen").resolve("library").toString(),
												filterResVariables,
												"dassl",
												"0.0",
												"1.0",
												"0.000001",
												"500");
		
		config.setParameter("simFlags", "-lv LOG_STATS");
		config.setParameter("createFilteredMat", "false");
		
		OpenModelicaEngine omEngine = new OpenModelicaEngine();
		omEngine.configure(config);
		omEngine.simulate(mo);
		
		assertEquals("singlegen", mo.getSystemModel().getId());
		assertEquals(3, mo.getSystemModel().getDeclarations().size());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		assertEquals("gen_pwGeneratorM2S__GEN____1_SM", mo.getSystemModel().getDeclarations().get(1).getId());
		assertEquals(4, mo.getSystemModel().getEquations().size());
		
		Path omSimPath = (Path) omEngine.getSimulationResults().getValue(mo.getSystemModel().getId(), "simulation", "path");
		assertTrue(Files.exists(omSimPath.resolve("singlegen_res.mat")));
		assertTrue(Files.exists(omSimPath.resolve("singlegen_res_filtered.csv")));
		if(config.getBoolean("createFilteredMat")) assertTrue(Files.exists(omSimPath.resolve("singlegen_res_filtered.mat")));
		assertEquals(4, Files.walk(omSimPath).parallel().filter(p -> p.toFile().getName().endsWith(MO_EXTENSION)).count());
	}
	
	@Test
	public void testIEEE14() throws FileNotFoundException, IOException {
		if(!isOpenModelicaAvailable()) return;
		
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST.resolve("ieee14").resolve("itesla").resolve("ieee14bus.mo"));
		
		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		
		Configuration config = setConfiguration(
												DATA_TMP.toString(),
												DATA_TEST.resolve("library").toString(),
												filterResVariables,
												"dassl",
												"0.0",
												"1.0",
												"0.000001",
												"500");
	
		config.setParameter("createFilteredMat", "true");
		
		OpenModelicaEngine omEngine = new OpenModelicaEngine();
		omEngine.configure(config);
		omEngine.simulate(mo);
		
		assertEquals("ieee14bus", mo.getSystemModel().getId());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		
		Path omSimPath = (Path) omEngine.getSimulationResults().getValue(mo.getSystemModel().getId(), "simulation", "path");
		assertTrue(Files.exists(omSimPath.resolve("ieee14bus_res.mat")));
		assertTrue(Files.exists(omSimPath.resolve("ieee14bus_res_filtered.csv")));
		if(config.getBoolean("createFilteredMat")) assertTrue(Files.exists(omSimPath.resolve("ieee14bus_res_filtered.mat")));
		assertEquals(2, Files.walk(omSimPath).parallel().filter(p -> p.toFile().getName().endsWith(MO_EXTENSION)).count());
	}
	
	@Test
	public void testIEEE30() throws FileNotFoundException, IOException {
		if(!isOpenModelicaAvailable()) return;
		
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST.resolve("ieee30").resolve("itesla").resolve("ieee30bus.mo"));
		
		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		
		Configuration config = setConfiguration(
												DATA_TMP.toString(),
												DATA_TEST.resolve("library").toString(),
												filterResVariables,
												"dassl",
												"0.0",
												"1.0",
												"0.000001",
												"500");
		
		config.setParameter("createFilteredMat", "false");
		
		OpenModelicaEngine omEngine = new OpenModelicaEngine();
		omEngine.configure(config);
		omEngine.simulate(mo);
		
		assertEquals("ieee30bus", mo.getSystemModel().getId());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		
		Path omSimPath = (Path) omEngine.getSimulationResults().getValue(mo.getSystemModel().getId(), "simulation", "path");
		assertTrue(Files.exists(omSimPath.resolve("ieee30bus_res.mat")));
		assertTrue(Files.exists(omSimPath.resolve("ieee30bus_res_filtered.csv")));
		if(config.getBoolean("createFilteredMat")) assertTrue(Files.exists(omSimPath.resolve("ieee30bus_res_filtered.mat")));
		assertEquals(2, Files.walk(omSimPath).parallel().filter(p -> p.toFile().getName().endsWith(MO_EXTENSION)).count());
	}
	
//	@Test 
	public void testIEEE57() throws FileNotFoundException, IOException {
		if(!isOpenModelicaAvailable()) return;
		
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST.resolve("ieee57").resolve("itesla").resolve("ieee57bus.mo"));
		
		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		
		Configuration config = setConfiguration(
												DATA_TMP.toString(),
												DATA_TEST.resolve("library").toString(),
												filterResVariables,
												"dassl",
												"0.0",
												"1.0",
												"0.000001",
												"500");
		
		config.setParameter("createFilteredMat", "false");
	
		OpenModelicaEngine omEngine = new OpenModelicaEngine();
		omEngine.configure(config);
		omEngine.simulate(mo);
		
		assertEquals("ieee57bus", mo.getSystemModel().getId());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		
		Path omSimPath = (Path) omEngine.getSimulationResults().getValue(mo.getSystemModel().getId(), "simulation", "path");
		assertTrue(Files.exists(omSimPath.resolve("ieee57bus_res.mat")));
		assertTrue(Files.exists(omSimPath.resolve("ieee57bus_res_filtered.csv")));
		if(config.getBoolean("createFilteredMat")) assertTrue(Files.exists(omSimPath.resolve("ieee57bus_res_filtered.mat")));
		assertEquals(2, Files.walk(omSimPath).parallel().filter(p -> p.toFile().getName().endsWith(MO_EXTENSION)).count());
	}
	
//	@Test //TODO PENDING
	public void testIEEE118() throws FileNotFoundException, IOException {
		if(!isOpenModelicaAvailable()) return;
		
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST.resolve("ieee118").resolve("itesla").resolve("ieee118bus.mo"));
		
		String filterResVariables = "bus[a-zA-Z0-9_]*.(V|angle)";
		
		Configuration config = setConfiguration(
				DATA_TMP.toString(),
				DATA_TEST.resolve("library").toString(),
				filterResVariables,
				"dassl",
				"0.0",
				"1.0",
				"0.000001",
				"500");
		
		config.setParameter("createFilteredMat", "false");
	
		OpenModelicaEngine omEngine = new OpenModelicaEngine();
		omEngine.configure(config);
		omEngine.simulate(mo);
		
		assertEquals("ieee118bus", mo.getSystemModel().getId());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		
		Path omSimPath = (Path) omEngine.getSimulationResults().getValue(mo.getSystemModel().getId(), "simulation", "path");
		assertTrue(Files.exists(omSimPath.resolve("ieee118bus_res.mat")));
		assertTrue(Files.exists(omSimPath.resolve("ieee118bus_res_filtered.csv")));
		if(config.getBoolean("createFilteredMat")) assertTrue(Files.exists(omSimPath.resolve("ieee118bus_res_filtered.mat")));
		assertEquals(9, Files.walk(omSimPath).parallel().filter(p -> p.toFile().getName().endsWith(MO_EXTENSION)).count());
	}
	
	
	private Configuration setConfiguration(String modelicaEngineWorkingDir,
											String libraryDir,
											String resultVariables,
											String method,
											String startTime,
											String stopTime,
											String tolerance,
											String numOfIntervalsPerSecond)
	{
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir);
		config.setParameter("libraryDir", libraryDir);
		config.setParameter("resultVariables", resultVariables);
		
		config.setParameter("method", method);
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
	
	private static final Path	DATA_TEST		= Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path	DATA_TMP		= Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
	private static final String	MO_EXTENSION	= ".mo"; 
}
