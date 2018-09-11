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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.junit.Before;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.mo2dyd.test.Mo2Dyd2MoTest;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.DydFilesFromModelicaTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class Mo2Dyd2MoAsWorkflowTest extends Mo2Dyd2MoTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Override
	protected void rebuild(
			String catalog,
			String foldername,
			String moName,
			String moInitPath,
			String caseName,
			String ddrName,
			int numBuses,
			int numGenerators)
			throws Exception
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = DATA.resolve(catalog).resolve(foldername);
		// ddr is created in tmp folder
		Path ddrLocation = DATA_TMP.resolve(ddrName);
		Files.createDirectories(ddrLocation);
		String moInput = folder.resolve(moName).toString();
		String moInputInit = folder.resolve(moInitPath).toString();
		String cim = folder.resolve(caseName).toString();
		String fakeInit = ddrLocation.resolve("fake_init.csv").toString();
		String moOutput = DATA_TMP.resolve("mo2dyd2mo.mo").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("mo2dyd2mo");
		Files.createDirectories(modelicaEngineWorkingDir);

		TaskDefinition mo2dyd;
		if (EXPLICIT_INIT_FILES)
		{
			mo2dyd = TD(DydFilesFromModelicaTask.class, "mo2dyd0",
					TC("ddrLocation", ddrLocation.toAbsolutePath().toString(),
							"modelicaFile", moInput,
							"modelicaInitPath", moInputInit));
		}
		else
		{
			mo2dyd = TD(DydFilesFromModelicaTask.class, "mo2dyd0",
					TC("ddrLocation", ddrLocation.toAbsolutePath().toString(),
							"modelicaFile", moInput));
		}

		Workflow wf = WF(
				mo2dyd,
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddrLocation.toAbsolutePath().toString(),
								"modelicaEngine", "Fake",
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"fakeModelicaEngineResults", fakeInit)),
				TD(ModelicaExporterTask.class, "exporter0",
						TC("target", moOutput)));

		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(3).state);
		assertEquals(SUCCESS, wf.getState());

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(numBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(numGenerators, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);

		Path expected = folder.resolve(moInput);

		//Check if genberated .mo and expected .mo are equals checking declarations and equations
		ModelicaDocument moExpected = ModelicaParser.parse(expected);
		assertTrue(mo.getSystemModel().getDeclarations().stream().map(dec -> dec.getType())
				.collect(Collectors.toList())
				.containsAll(moExpected.getSystemModel().getDeclarations().stream()
						.map(dec -> dec.getType()).collect(Collectors.toList())));
		assertTrue(mo.getSystemModel().getDeclarations().stream().map(dec -> dec.getId())
				.collect(Collectors.toList())
				.containsAll(moExpected.getSystemModel().getDeclarations().stream()
						.map(dec -> dec.getId()).collect(Collectors.toList())));
		assertTrue(mo.getSystemModel().getEquations().stream().map(eq -> eq.getText())
				.collect(Collectors.toList())
				.containsAll(moExpected.getSystemModel().getEquations().stream()
						.map(eq -> eq.getText()).collect(Collectors.toList())));
	}
}
