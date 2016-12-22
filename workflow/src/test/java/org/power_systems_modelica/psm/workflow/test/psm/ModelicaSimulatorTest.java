package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;

public class ModelicaSimulatorTest
{

	@Test
	public void testSinglegen() throws WorkflowCreationException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		String varResults = "[a-zA-Z0-9_]*.(pin_EFD|pin_OMEGA|pin_CM|omegaRef)";

		testBuild(
				"singlegen",
				"itesla/singlegen.mo",
				"singlegen/library",
				"OpenModelica",
				varResults,
				null);
	}

	@Test
	public void testIeee14() throws WorkflowCreationException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";

		testBuild(
				"ieee14",
				"itesla/ieee14bus.mo",
				"library",
				"OpenModelica",
				varResults,
				null);

	}

	@Test
	public void testIeee30() throws WorkflowCreationException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";

		testBuild(
				"ieee30",
				"itesla/ieee30bus.mo",
				"library",
				"OpenModelica",
				varResults,
				null);
	}

	// @Test
	public void testIeee57() throws WorkflowCreationException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";

		testBuild(
				"ieee57",
				"itesla/ieee57bus.mo",
				"library",
				"OpenModelica",
				varResults,
				null);
	}

	// @Test
	public void testIeee118() throws WorkflowCreationException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";

		testBuild(
				"ieee118",
				"itesla/ieee118bus.mo",
				"library",
				"OpenModelica",
				varResults,
				null);
	}

	public void testBuild(
			String folderName,
			String caseName,
			String libFolderName,
			String modelicaEngine,
			String resultVariables,
			String webServicem) throws WorkflowCreationException, IOException
	{
		Path folder = TEST_SAMPLES.resolve(folderName);
		Path modelicaEngineWorkingDir = Paths.get(System.getenv("PSM_DATA")).resolve("tmp");
		Files.createDirectories(modelicaEngineWorkingDir);
		Path libraryDir = TEST_SAMPLES.resolve(libFolderName);
		String moInput = folder.resolve(caseName).toString();

		Workflow wf = WF(
				TD(ModelicaParserTask.class, "moparser0",
						TC("source", moInput)),
				TD(ModelicaSimulatorTask.class, "dynamicsim0",
						TC("source", "mo",
								"modelicaEngine", modelicaEngine,
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"libraryDir", libraryDir.toString(),
								"resultVariables", resultVariables)));

		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getState());

		Path simPath = (Path) wf.getResults("simres");
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		String moName = mo.getSystemModel().getId();
		assertTrue(Files.exists(simPath.resolve(moName + "_res_filtered.csv")));
		assertTrue(Files.exists(simPath.resolve(moName + "_res.mat")));
	}

	private boolean isOpenModelicaAvailable()
	{
		return Boolean.valueOf(System.getProperty("OpenModelicaAvailable"));
	}

}
