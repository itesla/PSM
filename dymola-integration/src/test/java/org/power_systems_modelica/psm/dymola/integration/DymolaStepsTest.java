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
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

public class DymolaStepsTest
{

	private String			folderName			= "singlegen";
	private String			moFileName			= "singlegen.mo";
	private int				numOfResults;

	private String			filterResVariables	= "[a-zA-Z0-9_]*.(pin_EFD|pin_OMEGA|pin_CM|omegaRef)";
	private Configuration	config				= setConfiguration(
			"http://localhost:8888/dymservice?wsdl",
			DATA_TMP.toString(),
			DATA_TEST.resolve("singlegen").resolve("library").toString(),
			filterResVariables, "0.0", "1.0", "0.000001", "500");

	@Test
	public void testCheck() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (DymolaEngine dymEngine = new DymolaEngine())
		{
			config.setParameter("depth", "1");
			dymEngine.configure(config);
			dymEngine.simulate(mo);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(!dymEngine.getSimulationResults().getEntries().isEmpty());
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testVerify() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (DymolaEngine dymEngine = new DymolaEngine())
		{
			config.setParameter("depth", "2");
			dymEngine.configure(config);
			dymEngine.simulate(mo);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());
			assertTrue(!dymEngine.getSimulationResults().getEntries().isEmpty());
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	@Test
	public void testSimulation() throws FileNotFoundException, IOException
	{
		if (!isDymolaAvailable()) return;
		numOfResults = 6;

		String moName = moFileName.substring(0, moFileName.indexOf("."));
		ModelicaDocument mo = ModelicaParser
				.parse(DATA_TEST.resolve(folderName).resolve("itesla").resolve(moFileName));

		try (DymolaEngine dymEngine = new DymolaEngine())
		{
			config.setParameter("depth", "0");
			dymEngine.configure(config);
			dymEngine.simulate(mo);

			ModelicaSimulationFinalResults results = dymEngine.getSimulationResults();
			if (results.getEntries().size() > 1)
				assertTrue(results.getEntries().size() == numOfResults);

			assertEquals(moName, mo.getSystemModel().getId());
			assertEquals("SNREF", mo.getSystemModel().getDeclarations().get(0).getId());

			Path dymSimPath = (Path) dymEngine.getSimulationResults()
					.getValue(mo.getSystemModel().getId(), "simulation_path");
			assertTrue(Files.exists(dymSimPath.resolve(moName + "_res_filtered.csv")));
			if (config.getBoolean("createFilteredMat"))
				assertTrue(Files.exists(dymSimPath.resolve(moName + "_res_filtered.mat")));
			assertTrue(Files.exists(dymSimPath.resolve(moName + "_in.zip")));
			assertTrue(Files.exists(dymSimPath.resolve(moName + "_out.zip")));
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
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
		config.setParameter("createFilteredMat", "true");

		return config;
	}

	private boolean isDymolaAvailable()
	{
		return Boolean.valueOf(System.getProperty("DymolaAvailable"));
	}

	private static final Path	DATA_TEST	= Paths.get(System.getenv("PSM_DATA")).resolve("test");
	private static final Path	DATA_TMP	= Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
}
