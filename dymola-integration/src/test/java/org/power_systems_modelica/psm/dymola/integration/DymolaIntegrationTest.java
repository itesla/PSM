package org.power_systems_modelica.psm.dymola.integration;

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

public class DymolaIntegrationTest {

	@Test
	public void testSinglegen() throws FileNotFoundException, IOException {
		if(!isDymolaAvailable()) return;
		
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST
											.resolve("singlegen")
											.resolve("itesla")
											.resolve("singlegen.mo"));

		
		String varResults = "[a-zA-Z0-9_]*.(pin_EFD|pin_OMEGA|pin_CM|omegaRef)";
		
		Configuration config = setConfiguration(
												"http://localhost:8888/dymservice?wsdl", 
												DATA_TMP.toString(),
												DATA_TEST.resolve("singlegen").resolve("library").toString(), 
												varResults, 
												"Dassl", 
												"0.0", 
												"1.0",
												"0.000001", 
												"500");
		
		config.setParameter("createFilteredMat", "true");

		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulate(mo);

		assertEquals("singlegen", mo.getSystemModel().getId());
		assertEquals(3, mo.getSystemModel().getDeclarations().size());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		assertEquals("gen_pwGeneratorM2S__GEN____1_SM", mo.getSystemModel().getDeclarations().get(1).getId());
		assertEquals(4, mo.getSystemModel().getEquations().size());

		Path dymSimPath = (Path) dymEngine.getSimulationResults().getValue(mo.getSystemModel().getId(), "simulation", "path");
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res_filtered.csv")));
		if(config.getBoolean("createFilteredMat")) assertTrue(Files.exists(dymSimPath.resolve("singlegen_res_filtered.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_in.zip")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_out.zip")));
		assertEquals(4, Files.walk(dymSimPath).parallel().filter(p -> p.toFile().getName().endsWith(".mo")).count());
	}

	//TODO Only with Dymola License because it's a too complex system for trial version and the iPSL is too big
	// @Test
	public void testIEEE14() throws FileNotFoundException, IOException {
		if(!isDymolaAvailable()) return;
		
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST
											.resolve("ieee14")
											.resolve("itesla")
											.resolve("ieee14bus.mo"));

		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";
		
		Configuration config = setConfiguration(
												"http://localhost:8888/dymservice?wsdl", 
												DATA_TMP.toString(),
												DATA_TEST.resolve("library").toString(), 
												varResults, 
												"Dassl", 
												"0.0", 
												"1.0",
												"0.000001", 
												"500"
												);
		
		config.setParameter("createFilteredMat", "true");

		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulate(mo);

		assertEquals("ieee14", mo.getSystemModel().getId());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

		Path dymSimPath = (Path) dymEngine.getSimulationResults().getValue(mo.getSystemModel().getId(), "simulation", "path");
		assertTrue(Files.exists(dymSimPath.resolve("ieee14_res.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("ieee14_res_filtered.csv")));
		if(config.getBoolean("createFilteredMat")) assertTrue(Files.exists(dymSimPath.resolve("ieee14_res_filtered.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("ieee14_in.zip")));
		assertTrue(Files.exists(dymSimPath.resolve("ieee14_out.zip")));
		assertEquals(4, Files.walk(dymSimPath).parallel().filter(p -> p.toFile().getName().endsWith(".mo")).count());
	}

	private Configuration setConfiguration(
											String webService,
											String modelicaEngineWorkingDir, 
											String libraryDir, 
											String resultVariables,
											String method, 
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

		config.setParameter("method", method);
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
	
	private static final Path DATA_TEST = Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path DATA_TMP = Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
}
