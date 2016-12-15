package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.view.CasesOverviewController;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CasesOverviewControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = null;
		try {

			MainService mainService = new MainService(null);
			mainService.setStage(stage);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CasesOverview.fxml"));
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
	public void testContentCases() {
		
		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());
		
		clickOn("#catalogs").clickOn("Reference cases");
		
		TableView<Catalog> catalogs = lookup("#catalogs").query();
		assertEquals(CatalogService.getCatalogs("cases").size(), catalogs.getItems().size());
		
		TableView<Case> cases = lookup("#cases").query();
		assertEquals(CaseService.getCases(catalog).size(), cases.getItems().size());
	}
	
	private CasesOverviewController controller;
}
