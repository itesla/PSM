package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsNewController;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CompareLoadflowsNewControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = null;
		try {

			MainService mainService = new MainService(null);
			mainService.setStage(stage);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CompareLoadflowsNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setMainService(mainService);

			Scene scene = new Scene(workflowsOverview);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetCase() {
		
		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());
		List<Case> cases = CaseService.getCases(catalog);

		Case cs = cases.stream().filter(c -> {
			return c.getName().equals("ieee14");
		}).findFirst().get();

		interact(new Runnable() {

			@Override
			public void run() {
				ComboBox<Catalog> catalogSource = lookup("#catalogSource").query();
				ComboBox<Case> caseSource = lookup("#caseSource").query();

				controller.setCase(cs);
				
				assertEquals("Reference cases", catalogSource.getSelectionModel().getSelectedItem().getName());
				assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());
			}

		});
	}

	@Test
	public void testSource() {
		
		ComboBox<Catalog> catalogSource = lookup("#catalogSource").query();
		ComboBox<Case> caseSource = lookup("#caseSource").query();

		List<Catalog> catalogs = CatalogService.getCatalogs("cases");
		assertEquals(catalogs.size(), catalogSource.getItems().size());
		Catalog catalog = catalogs.stream().filter(c -> c.getName().equals("Reference cases")).findFirst().get();
		clickOn("#catalogSource").clickOn("Reference cases");
		assertEquals("Reference cases", catalogSource.getSelectionModel().getSelectedItem().getName());

		List<Case> cases = CaseService.getCases(catalog);
		assertEquals(cases.size(), caseSource.getItems().size());
		clickOn("#caseSource").clickOn("ieee14");
		assertEquals("ieee14", caseSource.getSelectionModel().getSelectedItem().getName());
	}

	private CompareLoadflowsNewController controller;
}
