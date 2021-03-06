package org.power_systems_modelica.psm.test.gui.service;

/*
 * #%L
 * Power Systems on Modelica GUI
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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class WorkflowConfigurationTest
{

	@Test
	public void configurationTest() throws WorkflowCreationException
	{
		List<LoadflowEngine> le = WorkflowServiceConfiguration.getLoadflowEngines();
		int numLoadflowEngines = 2;
		if (Utils.isHades2Available()) numLoadflowEngines++;
		assertEquals(numLoadflowEngines, le.size());

		List<DsEngine> dse = WorkflowServiceConfiguration.getDsEngines();
		assertTrue(dse.size() >= 2);

		ConvertedCase cs = new ConvertedCase();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
		cs.setDdrLocation(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString());
		List<String> actions = WorkflowServiceConfiguration.getAvailableEvents(cs);
		assertTrue(actions.size() > 0);

		List<String> elements = WorkflowServiceConfiguration.getNetworkElements(cs, actions.get(1));
		assertTrue(elements.size() > 0);

		List<EventParamGui> events = WorkflowServiceConfiguration.getEventParams(actions.get(0));
		assertTrue(events.size() > 0);
	}

	@Test
	public void createConversionTest() throws WorkflowCreationException
	{

		Case cs = new Case();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());

		Ddr ddr = new Ddr();
		ddr.setLocation(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString());

		Workflow w = WorkflowServiceConfiguration.createConversion(cs, ddr, LoadflowEngine.HELMFLOW,
				true, DsEngine.OPENMODELICA, false);

		assertNotNull(w);
		assertEquals(5, w.getWorkflowTasks().size());
		assertEquals("importer0", w.getWorkflowTasks().get(0).getId());
		assertEquals("loadflowHelmflow", w.getWorkflowTasks().get(1).getId());
		assertEquals("modelicaCheck0", w.getWorkflowTasks().get(2).getId());
		assertEquals("modelica0", w.getWorkflowTasks().get(3).getId());
		assertEquals("exporter0", w.getWorkflowTasks().get(4).getId());
	}

	@Test
	public void createSimulationTest() throws WorkflowCreationException
	{

		ConvertedCase cs = new ConvertedCase();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
		cs.setDdrLocation(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString());

		Event event = new Event();
		event.setElement("_BUS___10_TN");
		event.setAction("BusFault");
		List<EventParamGui> params = new ArrayList<>();
		EventParamGui param = new EventParamGui();
		param.setName("R");
		param.setUnit("pu");
		param.setValue("0.5");
		params.add(param);
		param = new EventParamGui();
		param.setName("X");
		param.setUnit("pu");
		param.setValue("0.5");
		params.add(param);
		param = new EventParamGui();
		param.setName("t1");
		param.setUnit("s");
		param.setValue("0.3");
		params.add(param);
		param = new EventParamGui();
		param.setName("t2");
		param.setUnit("x");
		param.setValue("0.5");
		params.add(param);
		event.setParams(params);

		ObservableList<Event> events = FXCollections.observableArrayList();
		events.add(event);

		Workflow w = WorkflowServiceConfiguration.createSimulation(cs, events,
				DsEngine.OPENMODELICA, "5", "100", false, false, false);

		assertNotNull(w);
		assertEquals(6, w.getWorkflowTasks().size());
		assertEquals("importer0", w.getWorkflowTasks().get(0).getId());
		assertEquals("moparser0", w.getWorkflowTasks().get(1).getId());
		assertEquals("eventAdder0", w.getWorkflowTasks().get(2).getId());
		assertEquals("exporter0", w.getWorkflowTasks().get(3).getId());
		assertEquals("modelica0", w.getWorkflowTasks().get(4).getId());
		assertEquals("results0", w.getWorkflowTasks().get(5).getId());
	}

	@Test
	public void createCompareLoadflowsTest() throws WorkflowCreationException
	{

		Case cs = new Case();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());

		Workflow w = WorkflowServiceConfiguration.createCompareLoadflows(cs, LoadflowEngine.COMPARISON, true, true);

		assertNotNull(w);
		assertEquals(3, w.getWorkflowTasks().size());
		assertEquals("importer0", w.getWorkflowTasks().get(0).getId());
		assertEquals("loadflowHades2", w.getWorkflowTasks().get(1).getId());
		assertEquals("loadflowHelmflow", w.getWorkflowTasks().get(2).getId());
	}
}
