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
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.power_systems_modelica.psm.dd.Event;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.events.test.ModelicaEventAdderTest;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaEventAdderAsWorkflowTest extends ModelicaEventAdderTest
{

	@Override
	public ModelicaDocument addEvents(
			Network network,
			String foldername,
			String casename,
			String ddrname,
			List<Event> events,
			int expectedNumBuses,
			int expectedNumGenerators,
			int expectedAdditionalDeclarations,
			int expectedAdditionalEquations,
			boolean reread)
			throws Exception
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		String cim = folder.resolve(casename).toString();
		String ddr = folder.resolve(ddrname).toString();
		String fakeInit = folder.resolve(ddrname).resolve("fake_init.csv").toString();
		String outname = DATA_TMP.resolve("event_adder_initial.mo").toString();
		String outnameev = DATA_TMP.resolve("event_adder_events.mo").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("event_adder");
		Files.createDirectories(modelicaEngineWorkingDir);
		
		String eventsList = ModelicaEventAdderTask.eventsToString(events);

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
						TC("source", "mo",
								"target", outname,
								"includePsmAnnotations", "true")),
				TD(ModelicaParserTask.class, "importerMo0",
						TC("source", outname,
								"modelicaDocument", "mo_re-read")),
				TD(ModelicaEventAdderTask.class, "eventAdder0",
						TC("network", "network",
								"modelicaDocument", "mo_re-read",
								"ddrType", "DYD",
								"ddrLocation", ddr,
								"events", eventsList)),
				TD(ModelicaExporterTask.class, "exporter1",
						TC("source", "moWithEvents",
								"target", outnameev)));
		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(3).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(4).state);
		assertEquals(SUCCESS, wf.getState());

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(expectedNumBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);
		ModelicaDocument mo2 = (ModelicaDocument) wf.getResults("moWithEvents");
		assertNotNull(mo2);
		int ndecls = mo.getSystemModel().getDeclarations().size();
		int ndecls2 = mo2.getSystemModel().getDeclarations().size();
		assertEquals(ndecls + expectedAdditionalDeclarations, ndecls2);
		int neqs = mo.getSystemModel().getEquations().size();
		int neqs2 = mo2.getSystemModel().getEquations().size();
		assertEquals(neqs + expectedAdditionalEquations, neqs2);
		return mo2;
	}
}
