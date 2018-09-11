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
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.test.ModelicaNetworkBuilderReferenceCases;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaNetworkBuilderReferenceCasesAsWorkflow
		extends ModelicaNetworkBuilderReferenceCases
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Override
	public void build(
			String catalog,
			String foldername,
			String casename,
			String ddrname,
			String expectedmoname,
			int expectedNumBuses,
			int expectedNumGenerators)
			throws Exception
	{
		// TODO Use ShrinkWrap file system for temporal files used in tests
		Path folder = DATA.resolve(catalog).resolve(foldername);
		String cim = folder.resolve(casename).toString();
		String ddr = DATA.resolve(catalog).resolve(ddrname).toString();
		String fakeInit = DATA.resolve(catalog).resolve(ddrname).resolve("fake_init.csv").toString();
		String name = foldername;
		String outname = DATA_TMP
				.resolve("workflow_modelica_builder_output_" + name + ".mo").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("workflow_modelica_builder_init" + name);
		Files.createDirectories(modelicaEngineWorkingDir);

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr,
								"modelicaEngine", "Fake",
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"fakeModelicaEngineResults", fakeInit)),
				TD(ModelicaExporterTask.class, "exporter0",
						TC("target", outname)));
		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);
		assertEquals(SUCCESS, wf.getState());

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(expectedNumBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);

		Path expected = folder.resolve(expectedmoname);
		
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
