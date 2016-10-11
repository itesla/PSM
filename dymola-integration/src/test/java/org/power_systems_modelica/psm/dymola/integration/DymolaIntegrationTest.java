package org.power_systems_modelica.psm.dymola.integration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.dymola.integration.utils.Utils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class DymolaIntegrationTest {

//	@Test
	public void testTest() throws FileNotFoundException, IOException {
		if(Files.exists(TEST_SAMPLES.resolve("sampletest").resolve("work_dir").resolve("simulation"))) {
			Utils.deleteDirectoryRecursively(TEST_SAMPLES
					.resolve("sampletest")
					.resolve("work_dir")
					.resolve("simulation"));
		}
		
		
		ModelicaParser moParser = new ModelicaParser();
		ModelicaDocument mo = moParser.parse(TEST_SAMPLES
													.resolve("sampletest")
													.resolve("itesla")
													.resolve("sampletest.mo"));
		
		Configuration config = setTestConfiguration();
		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulate(mo);

		assertTrue(Files.exists(TEST_SAMPLES
				.resolve("sampletest")
				.resolve("simulation")
				.resolve("sampletest_out.zip")));
	}
	
	
	@Test
	public void testTestFake() throws FileNotFoundException, IOException {
		if(Files.exists(TEST_SAMPLES.resolve("sampletest").resolve("work_dir").resolve("simulation"))) {
			Utils.deleteDirectoryRecursively(TEST_SAMPLES
					.resolve("sampletest")
					.resolve("work_dir")
					.resolve("dymola_simulation"));
		}
		
		ModelicaParser moParser = new ModelicaParser();
		ModelicaDocument mo = moParser.parse(TEST_SAMPLES
												.resolve("sampletest")
												.resolve("itesla")
												.resolve("sampletest.mo"));
		
		Configuration config = setTestConfiguration();
		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulateFake(TEST_SAMPLES
				.resolve("sampletest")
				.resolve("itesla")
				.resolve("sampletest.mo"));

		assertTrue(Files.exists(TEST_SAMPLES
				.resolve("sampletest")
				.resolve("work_dir")
				.resolve("dymola_simulation")
				.resolve("sampletest_out.zip")));
	}
	
	//TODO Only with Dymola License because it's a too complex system for trial version and the iPSL is too big
	public void testIEEE14() throws FileNotFoundException, IOException {
		ModelicaParser moParser = new ModelicaParser();
		ModelicaDocument mo = moParser.parse(TEST_SAMPLES
												.resolve("ieee14")
												.resolve("itesla")
												.resolve("ieee14bus.mo"));
		
		Configuration config = setTestIEEE14Configuration();
		DymolaEngine dymEngine = new DymolaEngine();
		dymEngine.configure(config);
		dymEngine.simulate(mo);		
	}
	
	
	private Configuration setTestConfiguration() {
		Configuration config = new Configuration();
//		config.setParameter("modelicaEngineWorkingDir", TEST_SAMPLES.resolve("sampletest").resolve("simulation").toString());
		config.setParameter("modelicaEngineWorkingDir", TEST_SAMPLES.resolve("sampletest").resolve("work_dir").toString());
		config.setParameter("method", "Dassl");
//		config.setParameter("webService", "http://localhost:8888/dymservice?wsdl");
		config.setParameter("webService", "http://192.168.201.200:8888/dymservice?wsdl");
		config.setParameter("numberOfIntervals", "500");
		config.setParameter("outputFixedStepSize", "0.002");
		config.setParameter("outputInterval", "0.002");
		config.setParameter("startTime", "0");
		config.setParameter("stopTime", "1");
		config.setParameter("tolerance", "0.0001");
		config.setParameter("libraryDirectory", TEST_SAMPLES.resolve("sampletest").resolve("library").toString());
		
		return config;
	}

	private Configuration setTestIEEE14Configuration() {
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", TEST_SAMPLES.resolve("ieee14").resolve("simulation").toString());
		config.setParameter("method", "Dassl");
//		config.setParameter("webService", "http://localhost:8888/dymservice?wsdl");
		config.setParameter("numberOfIntervals", "500");
		config.setParameter("outputFixedStepSize", "0.002");
		config.setParameter("outputInterval", "0.002");
		config.setParameter("startTime", "0");
		config.setParameter("stopTime", "1");
		config.setParameter("tolerance", "0.0001");
		config.setParameter("libraryDirectory", TEST_SAMPLES.resolve("ieee14").resolve("library").toString());
		
		return config;
	}


	private static final Path TEST_SAMPLES = Paths.get(System.getenv("PSM_DATA")).resolve("test");
}
