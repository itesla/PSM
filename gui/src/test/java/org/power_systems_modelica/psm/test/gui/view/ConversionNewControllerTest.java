package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.view.ConversionNewController;
import org.power_systems_modelica.psm.test.gui.GuiFileChooserFake;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.testfx.framework.junit.ApplicationTest;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConversionNewControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = null;
		try {

			MainService mainService = new MainService(null);
			mainService.setStage(stage);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConversionNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooserFake("test.properties"));

			Scene scene = new Scene(workflowsOverview);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testSetCase() {
		
		Case cs = new Case();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());

		interact(new Runnable() {

			@Override
			public void run() {
				ComboBox<Catalog> catalogCaseSource = lookup("#catalogCaseSource").query();
				ComboBox<Case> caseSource = lookup("#caseSource").query();
				ComboBox<Catalog> catalogDdrSource = lookup("#catalogDdrSource").query();
				ComboBox<Ddr> ddrSource = lookup("#ddrSource").query();

				controller.setCase(cs);
				
				assertEquals("Reference cases", catalogCaseSource.getSelectionModel().getSelectedItem().getName());
				assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());

				assertEquals("Reference cases", catalogDdrSource.getSelectionModel().getSelectedItem().getName());
				assertEquals("ieee14_ddr", ddrSource.getSelectionModel().getSelectedItem().getName());
			}

		});
	}
	
	@Test
	public void testSource() {

		ComboBox<Catalog> catalogCaseSource = lookup("#catalogCaseSource").query();
		ComboBox<Case> caseSource = lookup("#caseSource").query();
		ComboBox<Catalog> catalogDdrSource = lookup("#catalogDdrSource").query();
		ComboBox<Ddr> ddrSource = lookup("#ddrSource").query();

		List<Catalog> catalogs = CatalogService.getCatalogs("cases");
		assertEquals(catalogs.size(), catalogCaseSource.getItems().size());
		Catalog catalog = catalogs.stream().filter(c -> c.getName().equals("Reference cases")).findFirst().get();
		clickOn("#catalogCaseSource").clickOn("Reference cases");
		assertEquals("Reference cases", catalogCaseSource.getSelectionModel().getSelectedItem().getName());

		List<Case> cases = CaseService.getCases(catalog);
		assertEquals(cases.size(), caseSource.getItems().size());
		clickOn("#caseSource").clickOn("ieee14");
		assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());

		assertEquals("Reference cases", catalogDdrSource.getSelectionModel().getSelectedItem().getName());
		assertEquals("ieee14_ddr", ddrSource.getSelectionModel().getSelectedItem().getName());
	}

	@Test
	public void testSetWorkflow() throws WorkflowCreationException {
		
		Case cs = new Case();
		cs.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());

		Ddr ddr = new Ddr();
		ddr.setLocation(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString());

		Workflow w = WorkflowServiceConfiguration.createConversion(cs, ddr, LoadflowEngine.HELMFLOW, true, DsEngine.OPENMODELICA, false);

		interact(new Runnable() {

			@Override
			public void run() {
				ComboBox<Catalog> catalogCaseSource = lookup("#catalogCaseSource").query();
				ComboBox<Case> caseSource = lookup("#caseSource").query();
				ComboBox<Catalog> catalogDdrSource = lookup("#catalogDdrSource").query();
				ComboBox<Ddr> ddrSource = lookup("#ddrSource").query();
				
				controller.setWorkflow(w);

				assertEquals("Reference cases", catalogCaseSource.getSelectionModel().getSelectedItem().getName());
				assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());

				assertEquals("Reference cases", catalogDdrSource.getSelectionModel().getSelectedItem().getName());
				assertEquals("ieee14_ddr", ddrSource.getSelectionModel().getSelectedItem().getName());
			}
		});
	}

	private ConversionNewController controller;
}
