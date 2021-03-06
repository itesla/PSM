package org.power_systems_modelica.psm.test.gui.view;

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
import static org.testfx.api.FxAssert.verifyThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.view.SimulationNewController;
import org.power_systems_modelica.psm.test.gui.GuiFileChooserFake;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.ListViewMatchers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class SimulationNewControllerTest extends ApplicationTest
{

	@Override
	public void start(Stage stage) throws Exception
	{

		FXMLLoader loader = null;
		try
		{

			MainService mainService = new MainService(null);
			mainService.setStage(stage);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SimulationNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooserFake("test.properties"));

			Scene scene = new Scene(workflowsOverview);
			stage.setScene(scene);
			stage.show();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testSetCase()
	{

		Case cs = new Case();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());

		interact(new Runnable()
		{

			@Override
			public void run()
			{
				ComboBox<Catalog> catalogCaseSource = lookup("#catalogCaseSource").query();
				ComboBox<ConvertedCase> caseSource = lookup("#caseSource").query();

				controller.setCase(cs);

				assertEquals("Reference cases",
						catalogCaseSource.getSelectionModel().getSelectedItem().getName());
				assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());

			}

		});
	}

	@Test
	public void testSource()
	{

		ComboBox<Catalog> catalogCaseSource = lookup("#catalogCaseSource").query();
		ComboBox<ConvertedCase> caseSource = lookup("#caseSource").query();

		List<Catalog> catalogs = CatalogService.getCatalogs("cases");
		assertEquals(catalogs.size(), catalogCaseSource.getItems().size());
		Catalog catalog = catalogs.stream().filter(c -> c.getName().equals("Reference cases"))
				.findFirst().get();
		clickOn("#catalogCaseSource").clickOn("Reference cases");
		assertEquals("Reference cases",
				catalogCaseSource.getSelectionModel().getSelectedItem().getName());

		List<ConvertedCase> cases = CaseService.getConvertedCases(catalog);
		assertEquals(cases.size(), caseSource.getItems().size());
		clickOn("#caseSource").clickOn("ieee14");
		assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());

	}

	@Test
	public void testAddEvent()
	{

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
		param.setName("startTime");
		param.setUnit("s");
		param.setValue("0.3");
		params.add(param);
		param = new EventParamGui();
		param.setName("endTime");
		param.setUnit("x");
		param.setValue("0.5");
		params.add(param);
		event.setParams(params);

		clickOn("#catalogCaseSource").clickOn("Reference cases");
		clickOn("#caseSource").clickOn("ieee14");
		clickOn("#addEvents");

		clickOn("#actionEvent").clickOn("BusFault");
		clickOn("#elementEvent").clickOn("_BUS___10_TN");

		TableView<EventParamGui> tableView = lookup("#parametersView").query();
		param = tableView.getItems().get(0);
		param.setValue("0.5");
		param = tableView.getItems().get(1);
		param.setValue("0.5");
		param = tableView.getItems().get(2);
		param.setValue("0.3");
		param = tableView.getItems().get(3);
		param.setValue("0.5");
		clickOn("#add");

		verifyThat("#addedEvents", ListViewMatchers.hasItems(1));
		ListView<Event> listView = lookup("#addedEvents").query();
		assertEquals(event.toString(), listView.getItems().get(0).toString());

		clickOn("#addedEvents").clickOn(event.toString());
		clickOn("#removeEvent");
		verifyThat("#addedEvents", ListViewMatchers.isEmpty());
	}

	@Test
	public void testSetWorkflow() throws WorkflowCreationException
	{

		ConvertedCase cs = new ConvertedCase();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());

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

		Workflow w = WorkflowServiceConfiguration.createSimulation(cs, events, DsEngine.FAKE, "1.0",
				"100", false, false, false);

		interact(new Runnable()
		{

			@Override
			public void run()
			{
				ComboBox<Catalog> catalogCaseSource = lookup("#catalogCaseSource").query();
				ComboBox<ConvertedCase> caseSource = lookup("#caseSource").query();

				controller.setWorkflow(w);

				assertEquals("Reference cases",
						catalogCaseSource.getSelectionModel().getSelectedItem().getName());
				assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());

			}
		});
	}

	private SimulationNewController controller;
}
