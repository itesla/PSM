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
import org.power_systems_modelica.psm.dymola.integration.utils.Utils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class DymolaIntegrationTest {

	@Test
	public void testSinglegen() throws FileNotFoundException, IOException {
//		ModelicaParser moParser = new ModelicaParser();
//		ModelicaDocument mo = moParser.parse(TEST_SAMPLES.resolve("test").resolve("singlegen").resolve("itesla").resolve("singlegen.mo"));

		Configuration config = setSinglegenConfiguration();
		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		Path dymSimPath = dymEngine.simulateFake(TEST_SAMPLES
														.resolve("test")
														.resolve("singlegen")
														.resolve("itesla")
														.resolve("singlegen.mo"));
		
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.csv")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_in.zip")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_out.zip")));
		assertEquals(4, Files.walk(dymSimPath).parallel().filter(p -> p.toFile().getName().endsWith(".mo")).count());
	}
	
	//TODO Only with Dymola License because it's a too complex system for trial version and the iPSL is too big
	public void testIEEE14() throws FileNotFoundException, IOException {
//		ModelicaParser moParser = new ModelicaParser();
//		ModelicaDocument mo = moParser.parse(TEST_SAMPLES.resolve("test").resolve("ieee14").resolve("itesla").resolve("ieee14bus.mo"));
		
		Configuration config = setTestIEEE14Configuration();
		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		Path dymSimPath = dymEngine.simulateFake(TEST_SAMPLES
													.resolve("test")
													.resolve("ieee14")
													.resolve("itesla")
													.resolve("ieee14.mo"));
											
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.csv")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_in.zip")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_out.zip")));
		assertEquals(4, Files.walk(dymSimPath).parallel().filter(p -> p.toFile().getName().endsWith(".mo")).count());
	}
	
	
	private Configuration setSinglegenConfiguration() {
		String varResults = "gen_pwGeneratorM2S__GEN____1_SM.pin_EFD," + 
							"gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA," + 
							"gen_pwGeneratorM2S__GEN____1_SM.pin_CM," +  
							"gen_pwGeneratorM2S__GEN____1_SM.omegaRef";

		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", TEST_SAMPLES.resolve("tmp").toString());
		config.setParameter("method", "Dassl");
		config.setParameter("webService", "http://localhost:8888/dymservice?wsdl");
		config.setParameter("numberOfIntervals", "500");
		config.setParameter("outputFixedStepSize", "0.002");
		config.setParameter("outputInterval", "0.002");
		config.setParameter("startTime", "0.0");
		config.setParameter("stopTime", "1.0");
		config.setParameter("tolerance", "0.0001");
		config.setParameter("libraryDirectory", TEST_SAMPLES.resolve("test").resolve("singlegen").resolve("library").toString());
		config.setParameter("resultVariables", varResults);

		return config;
	}

	private Configuration setTestIEEE14Configuration() {
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", TEST_SAMPLES.resolve("tmp").toString());
		config.setParameter("method", "Dassl");
		config.setParameter("webService", "http://localhost:8888/dymservice?wsdl");
		config.setParameter("numberOfIntervals", "500");
		config.setParameter("outputFixedStepSize", "0.002");
		config.setParameter("outputInterval", "0.002");
		config.setParameter("startTime", "0");
		config.setParameter("stopTime", "1");
		config.setParameter("tolerance", "0.0001");
		config.setParameter("libraryDirectory", TEST_SAMPLES.resolve("test").resolve("library").toString());
				
		return config;
	}

	private static final Path TEST_SAMPLES = Paths.get(System.getenv("PSM_DATA"));
}
