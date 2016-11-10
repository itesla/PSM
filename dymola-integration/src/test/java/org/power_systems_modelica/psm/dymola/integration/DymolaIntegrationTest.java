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
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve("singlegen").resolve("itesla").resolve("singlegen.mo"));

		String varResults = "gen_pwGeneratorM2S__GEN____1_SM.pin_EFD," + 
							"gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA," + 
							"gen_pwGeneratorM2S__GEN____1_SM.pin_CM," + 
							"gen_pwGeneratorM2S__GEN____1_SM.omegaRef";
		Configuration config = setConfiguration(
												"http://localhost:8888/dymservice?wsdl", 
												DATA_TMP.toString(),
												DATA_TEST.resolve("singlegen").resolve("library").toString(), 
												varResults, 
												"Dassl", 
												"0.0", 
												"1.0",
												"0.000001", 
												"500", 
												"0.002", 
												"0.002");

		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulate(mo);

		assertEquals("singlegen", mo.getSystemModel().getName());
		assertEquals(3, mo.getSystemModel().getDeclarations().size());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
		assertEquals("gen_pwGeneratorM2S__GEN____1_SM", mo.getSystemModel().getDeclarations().get(1).getId());
		assertEquals(4, mo.getSystemModel().getEquations().size());

		Path dymSimPath = (Path) dymEngine.getSimulationResults().getValue(mo.getSystemModel().getName(), "simulation",
				"path");
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.csv")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_in.zip")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_out.zip")));
		assertEquals(4, Files.walk(dymSimPath).parallel().filter(p -> p.toFile().getName().endsWith(".mo")).count());
	}

	// TODO Only with Dymola License because it's a too complex system for trial version and the iPSL is too big
	// @Test
	public void testIEEE14() throws FileNotFoundException, IOException {
		ModelicaDocument mo = ModelicaParser.parse(DATA_TEST.resolve("ieee14").resolve("itesla").resolve("ieee14bus.mo"));

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
		Configuration config = setConfiguration(
												"http://localhost:8888/dymservice?wsdl", 
												DATA_TMP.toString(),
												DATA_TEST.resolve("library").toString(), 
												varResults, 
												"Dassl", 
												"0.0", 
												"1.0",
												"0.000001", 
												"500", 
												"0.002", 
												"0.002");

		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulate(mo);

		assertEquals("ieee14", mo.getSystemModel().getName());
		assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

		Path dymSimPath = (Path) dymEngine.getSimulationResults().getValue(mo.getSystemModel().getName(), "simulation", "path");
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.csv")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_res.mat")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_in.zip")));
		assertTrue(Files.exists(dymSimPath.resolve("singlegen_out.zip")));
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
											String numOfIntervals, 
											String stepSize,
											String intervalLength)
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

		config.setParameter("numOfIntervals", numOfIntervals);
		config.setParameter("stepSize", stepSize);
		config.setParameter("intervalLength", intervalLength);

		return config;
	}

	private static final Path DATA_TEST = Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path DATA_TMP = Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
}
