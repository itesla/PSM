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

public class OpenModelicaValidationTest
{

	private String			folderName			= "singlegen";
	private String			moFileName			= "singlegen.mo";
	private int				numOfResults;

	private String			filterResVariables	= "[a-zA-Z0-9_]*.(pin_EFD|pin_OMEGA|pin_CM|omegaRef)";
	private Configuration	config				= setConfiguration(DATA_TMP.toString(),
			DATA_TEST.resolve("singlegen").resolve("library").toString(), filterResVariables,
			"0.0", "1.0", "0.000001", "500");

	@Test
	public void testOnlyValidationDepth1() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			omEngine.configure(config);
			boolean validated = omEngine.validate(mo, 1);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

			assertTrue(validated);
			assertTrue(omEngine.getSimulationResults().getEntries().isEmpty());
			// TODO assert if exists
			// assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
			// assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.csv")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testOnlyValidatioDepth2() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			omEngine.configure(config);
			boolean validated = omEngine.validate(mo, 2);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

			assertTrue(validated);
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());
			// TODO assert if exists
			// assertTrue(Files.exists(omSimPath.resolve(moName + "_res.mat")));
			// assertTrue(Files.exists(omSimPath.resolve(moName + ".log")));
			// assertTrue(Files.exists(omSimPath.resolve(moName + "_res_filtered.csv")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testOnlySimulation() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		numOfResults = 6;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
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

	@Test
	public void testValidationDepth1AndSimulation() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		numOfResults = 6;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			omEngine.configure(config);
			boolean validated = omEngine.validate(mo, 1);
			if (validated) omEngine.simulate(mo);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(validated);
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
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

	@Test
	public void testValidationDepth2AndSimulation() throws FileNotFoundException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		numOfResults = 6;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (OpenModelicaEngine omEngine = new OpenModelicaEngine())
		{
			omEngine.configure(config);
			boolean validated = omEngine.validate(mo, 1);
			if (validated) omEngine.simulate(mo);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(validated);
			assertTrue(!omEngine.getSimulationResults().getEntries().isEmpty());

			ModelicaSimulationFinalResults results = omEngine.getSimulationResults();
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
