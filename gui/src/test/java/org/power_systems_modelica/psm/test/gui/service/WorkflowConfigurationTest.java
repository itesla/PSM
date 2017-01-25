package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkflowConfigurationTest
{

	@Test
	public void createConversionTest() throws WorkflowCreationException
	{

		Case cs = new Case();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());

		Ddr ddr = new Ddr();
		ddr.setLocation(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString());

		Workflow w = WorkflowServiceConfiguration.createConversion(cs, ddr, LoadflowEngine.HELMFLOW,
				true, DsEngine.OPENMODELICA);

		assertNotNull(w);
		assertEquals(4, w.getWorkflowTasks().size());
		assertEquals("importer0", w.getWorkflowTasks().get(0).getId());
		assertEquals("loadflowHelmflow", w.getWorkflowTasks().get(1).getId());
		assertEquals("modelica0", w.getWorkflowTasks().get(2).getId());
		assertEquals("exporter0", w.getWorkflowTasks().get(3).getId());
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

	// FIXME Allow loadflow engine in task integration server
	// @Test
	// public void createCompareLoadflowsTest() throws WorkflowCreationException {
	//
	// Case cs = new Case();
	// cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
	//
	// Workflow w = WorkflowServiceConfiguration.createCompareLoadflows(cs, true);
	//
	// assertNotNull(w);
	// assertEquals(3,w.getWorkflowTasks().size());
	// assertEquals("importer0",w.getWorkflowTasks().get(0).getId());
	// assertEquals("loadflowHelmflow",w.getWorkflowTasks().get(1).getId());
	// assertEquals("loadflowHades2",w.getWorkflowTasks().get(2).getId());
	//
	// }
}
