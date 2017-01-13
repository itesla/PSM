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
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class OpenModelicaStepsTest
{

	private String			folderName			= "ieee14";
	private String			moFileName			= "ieee14bus.mo";
	private int				numOfResults; 
	private String			filterResVariables	= "bus[a-zA-Z0-9_]*.(V|angle)";
	private Configuration	config				= setConfiguration(DATA_TMP.toString(),
			DATA_TEST.resolve("library").toString(), filterResVariables,
			"0.0", "1.0", "0.000001", "500");
	
	@Test
	public void testCheck() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "1");
			omEngine.configure(config);
			omEngine.simulate(mo);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testVerify() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "2");
			omEngine.configure(config);
			omEngine.simulate(mo);

			Path omSimPath = (Path) omEngine.getSimulationResults()
					.getValue(mo.getSystemModel().getId(), "simulation_path");

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
			assertTrue(Files.exists(omSimPath.resolve(moName + ".log")));
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.csv")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testSimulation() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		numOfResults = 30;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			config.setParameter("depth", "0");
			omEngine.configure(config);
			omEngine.simulate(mo);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());
			assertTrue(results.getEntries().size() == numOfResults);

			Path omSimPath = (Path) omEngine.getSimulationResults()
					.getValue(mo.getSystemModel().getId(), "simulation_path");
			assertTrue(Files.exists(omSimPath.resolve(moName + ".log")));
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
			assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.csv")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	private Configuration setConfiguration(String modelicaEngineWorkingDir, String libraryDir,
			String resultVariables, String startTime, String stopTime, String tolerance,
			String numOfIntervalsPerSecond)
	{
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir);
		config.setParameter("libraryDir", libraryDir);
		config.setParameter("resultVariables", resultVariables);
		config.setParameter("startTime", startTime);
		config.setParameter("stopTime", stopTime);
		config.setParameter("tolerance", tolerance);
		config.setParameter("numOfIntervalsPerSecond", numOfIntervalsPerSecond);
		config.setParameter("simFlags", "-lv LOG_STATS");
		config.setParameter("createFilteredMat", "false");

		return config;
	}

	private boolean isOpenModelicaAvailable()
	{
		return Boolean.valueOf(System.getProperty("OpenModelicaAvailable"));
	}

	private static final Path	DATA_TEST	= Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path	DATA_TMP	= Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
}
