package org.power_systems_modelica.psm.workflow.test.psm;

/*
 * #%L
 * Power systems on Modelica workflow
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaSimulatorTest
{

	@Test
	public void testIeee14() throws WorkflowCreationException, IOException
	{
		if (!isOpenModelicaAvailable()) return;
		String varResults = "bus[a-zA-Z0-9_]*.(V|angle)";

		testBuild("test",
				"ieee14",
				"itesla/ieee14bus_no_lf.mo",
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

		testBuild("test",
				"ieee30",
				"itesla/ieee30bus_no_lf.mo",
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

		testBuild("test",
				"ieee57",
				"itesla/ieee57bus_no_lf.mo",
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

		testBuild("test",
				"ieee118",
				"itesla/ieee118bus_no_lf.mo",
				"library",
				"OpenModelica",
				varResults,
				null);
	}

	public void testBuild(
			String catalog,
			String folderName,
			String caseName,
			String libFolderName,
			String modelicaEngine,
			String resultVariables,
			String webServicem) throws WorkflowCreationException, IOException
	{
		Path folder = DATA.resolve(catalog).resolve(folderName);
		Path modelicaEngineWorkingDir = PathUtils.DATA_TMP;
		Files.createDirectories(modelicaEngineWorkingDir);
		Path libraryDir = DATA.resolve(libFolderName);
		String moInput = folder.resolve(caseName).toString();

		Workflow wf = WF(
				TD(ModelicaParserTask.class, "moparser0",
						TC("source", moInput, "modelicaDocument", "mo")),
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
