package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.view.WorkflowNewController;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.ListViewMatchers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorkflowNewControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader loader = null;
		try {
			
			MainService mainService = new MainService(null);
			
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			WorkflowNewController controller = loader.getController();
			controller.setMainService(mainService);
			
			Scene scene = new Scene(workflowsOverview);
	        stage.setScene(scene);
	        stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSource() {

		ComboBox<Catalog> catalogCaseSource = lookup("#catalogCaseSource").query();
		ComboBox<Case> caseSource = lookup("#caseSource").query();
		ComboBox<Catalog> catalogDdrSource = lookup("#catalogDdrSource").query();
		ComboBox<Ddr> ddrSource = lookup("#ddrSource").query();

		ObservableList<Catalog> catalogs = CatalogService.getCatalogs("cases");
		assertEquals(catalogs.size(), catalogCaseSource.getItems().size());
		Catalog catalog = catalogs.stream().filter(c -> c.getName().equals("Reference cases")).findFirst().get();
		clickOn("#catalogCaseSource").clickOn("Reference cases");
		//catalogCaseSource.getSelectionModel().select(catalog);
		assertEquals("Reference cases",catalogCaseSource.getSelectionModel().getSelectedItem().getName());
		
		ObservableList<Case> cases = CaseService.getCases(catalog);
		assertEquals(cases.size(), caseSource.getItems().size());
		clickOn("#caseSource").clickOn("ieee14");
		assertEquals("ieee14",caseSource.getSelectionModel().getSelectedItem().getName());
		
		assertEquals("Reference cases",catalogDdrSource.getSelectionModel().getSelectedItem().getName());
		assertEquals("ieee14_ddr",ddrSource.getSelectionModel().getSelectedItem().getName());
	}
	
	@Test
	public void testAddEvent() {
		
		Event event = new Event();
		event.setElement("_BUS___10_TN");
		event.setAction("BusFault");
		List<EventParamGui> params = new ArrayList();
		EventParamGui param = new EventParamGui();
		param.setName("R");
		param.setUnit("pu");
		param.setValue("0.5");
		params.add(param);
		param = new EventParamGui();
		param.setName("X");
		param.setUnit("pu");
		param.setValue("0.5");
		params.add(param);param = new EventParamGui();
		param.setName("t1");
		param.setUnit("s");
		param.setValue("0.3");
		params.add(param);param = new EventParamGui();
		param.setName("t2");
		param.setUnit("x");
		param.setValue("0.5");
		params.add(param);
		event.setParams(params);

		clickOn("#catalogCaseSource").clickOn("Reference cases");
		clickOn("#caseSource").clickOn("ieee14");
		clickOn("#addEvents");
		
		TextField element = lookup("#elementEvent").query();
		element.setText("_BUS___10_TN");
		clickOn("#actionEvent").clickOn("BusFault");
		
		
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
		assertEquals(event.toString(),listView.getItems().get(0).toString());
	}
}
