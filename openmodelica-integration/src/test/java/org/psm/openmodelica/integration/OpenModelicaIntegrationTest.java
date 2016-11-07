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

public class OpenModelicaIntegrationTest {

	@Test
	public void testSinglegen() throws FileNotFoundException, IOException {
//		ModelicaParser moParser = new ModelicaParser();
//		ModelicaDocument mo = moParser.parse(TEST_SAMPLES.resolve("test").resolve("singlegen").resolve("itesla").resolve("singlegen.mo"));
		
		Configuration config = setSinglegenConfiguration();
		OpenModelicaEngine omEngine = new OpenModelicaEngine();
		omEngine.configure(config);
		Path omSimPath = omEngine.simulateFake(TEST_SAMPLES
									.resolve("test")
									.resolve("singlegen")
									.resolve("itesla")
									.resolve("singlegen.mo"));

		assertTrue(Files.exists(omSimPath.resolve("singlegen_res.csv")));
		assertTrue(Files.exists(omSimPath.resolve("singlegen_res.mat")));
		assertEquals(4, Files.walk(omSimPath).parallel().filter(p -> p.toFile().getName().endsWith(".mo")).count());
	}
	
	@Test
	public void testIEEE14() throws FileNotFoundException, IOException {
//		ModelicaParser moParser = new ModelicaParser();
//		ModelicaDocument mo = moParser.parse(TEST_SAMPLES.resolve("test").resolve("ieee14").resolve("itesla").resolve("ieee14bus.mo"));
		
		Configuration config = setTestIEEE14Configuration();
		OpenModelicaEngine omEngine = new OpenModelicaEngine();
		omEngine.configure(config);
		Path omSimPath = omEngine.simulateFake(TEST_SAMPLES
					.resolve("test")
					.resolve("ieee14")
					.resolve("itesla")
					.resolve("ieee14bus.mo"));	
		
		assertTrue(Files.exists(omSimPath.resolve("ieee14bus_res.csv")));
		assertTrue(Files.exists(omSimPath.resolve("ieee14bus_res.mat")));
		assertEquals(6, Files.walk(omSimPath).parallel().filter(p -> p.toFile().getName().endsWith(".mo")).count());
	}
	
	
	private Configuration setSinglegenConfiguration() {
		String varResults = "gen_pwGeneratorM2S__GEN____1_SM.pin_EFD," + 
							"gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA," + 
							"gen_pwGeneratorM2S__GEN____1_SM.pin_CM," +  
							"gen_pwGeneratorM2S__GEN____1_SM.omegaRef";

		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", TEST_SAMPLES.resolve("tmp").toString());
		config.setParameter("method", "dassl");
		config.setParameter("numberOfIntervals", "500");
		config.setParameter("outputFixedStepSize", "0.002");
		config.setParameter("outputInterval", "0.002");
		config.setParameter("startTime", "0.0");
		config.setParameter("stopTime", "1.0");
		config.setParameter("tolerance", "0.000001");
		config.setParameter("libraryDirectory", TEST_SAMPLES.resolve("test").resolve("singlegen").resolve("library").toString());
		config.setParameter("resultVariables", varResults);

		return config;
	}

	private Configuration setTestIEEE14Configuration() {
		String varResults = "bus__BUS___10_TN.V," + "bus__BUS___10_TN.angle," +
							"bus__BUS___11_TN.V," + "bus__BUS___11_TN.angle," +
							"bus__BUS___12_TN.V," + "bus__BUS___12_TN.angle," +
							"bus__BUS___13_TN.V," + "bus__BUS___13_TN.angle," +
							"bus__BUS___14_TN.V," + "bus__BUS___14_TN.angle," +
							"bus__BUS____1_TN.V," + "bus__BUS____1_TN.angle," +
							"bus__BUS____2_TN.V," + "bus__BUS____2_TN.angle," + 
							"bus__BUS____3_TN.V," + "bus__BUS____3_TN.angle," +
							"bus__BUS____4_TN.V," + "bus__BUS____4_TN.angle," + 
							"bus__BUS____5_TN.V," + "bus__BUS____5_TN.angle," + 
							"bus__BUS____6_TN.V," + "bus__BUS____6_TN.angle," + 
							"bus__BUS____7_TN.V," + "bus__BUS____7_TN.angle," + 
							"bus__BUS____8_TN.V," + "bus__BUS____8_TN.angle," +
							"bus__BUS____9_TN.V," + "bus__BUS____9_TN.angle";

		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", TEST_SAMPLES.resolve("tmp").toString());
		config.setParameter("method", "dassl");
		config.setParameter("numberOfIntervals", "500");
		config.setParameter("outputFixedStepSize", "0.002");
		config.setParameter("outputInterval", "0.002");
		config.setParameter("startTime", "0");
		config.setParameter("stopTime", "1");
		config.setParameter("tolerance", "0.000001");
		config.setParameter("libraryDirectory", TEST_SAMPLES.resolve("test").resolve("library").toString());
		config.setParameter("resultVariables", varResults);
		
		return config;
	}

	private static final Path TEST_SAMPLES = Paths.get(System.getenv("PSM_DATA"));
}
