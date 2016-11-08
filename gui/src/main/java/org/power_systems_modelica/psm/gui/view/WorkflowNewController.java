package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.Event.ActionEvent;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class WorkflowNewController {

	@FXML
	private void initialize() {

		addEventPane.setVisible(false);
		catalogSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				caseSource.setItems(mainApp.getCases(newValue.getName()));
				ddrSource.setItems(mainApp.getDdrs(newValue.getName()));
			}

		});
	}

	@FXML
	private void handleOpenAddEvent() {
		LOG.debug("handleAddEvent");
		addEventPane.setVisible(true);
	}

	@FXML
	private void handleAddEvent() {
		LOG.debug("handleAddEvent");
		Event e = new Event();
		e.setElement(elementEvent.getText());
		e.setAction((ActionEvent) actionEvent.getSelectionModel().getSelectedItem());
		addedEvents.getItems().add(e);
		addEventPane.setVisible(false);
	}

	@FXML
	private void handleCancelEvent() {
		LOG.debug("handleAddEvent");
		addEventPane.setVisible(false);
	}

	@FXML
	private void handleRemoveEvent() {
		LOG.debug("handleRemoveEvent");
		ObservableList<Event> list = addedEvents.getSelectionModel().getSelectedItems();
		addedEvents.getItems().removeAll(list);
	}

	@FXML
	private void handleStartWorkflow() {
		LOG.debug("handleStartWorkflow");

		Catalog ctlg = (Catalog) catalogSource.getSelectionModel().getSelectedItem();
		Case cs = (Case) caseSource.getSelectionModel().getSelectedItem();
		Ddr ddr = (Ddr) ddrSource.getSelectionModel().getSelectedItem();

		LoadflowEngine le = (LoadflowEngine) loadflowEngine.getSelectionModel().getSelectedItem();

		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();

		DsEngine dse = (DsEngine) dsEngine.getSelectionModel().getSelectedItem();

		ObservableList<Event> events = addedEvents.getItems();

		mainApp.startWorkflow(ctlg, cs, ddr, le, onlyMainConnectedComponent, events, dse);
	}

	public void setCase(Case c) {
		
		ObservableList<Catalog> catalogs = mainApp.getCatalogs("cases");
		
		FilteredList<Catalog> filteredCatalogs = new FilteredList<Catalog> (catalogs, catalog -> c.getLocation().contains(catalog.getLocation())); 
		
		filteredCatalogs.forEach(catalog -> {
			catalogSource.getSelectionModel().select(catalog);
		});
		
		caseSource.getSelectionModel().select(c);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		catalogSource.setItems(mainApp.getCatalogs("cases"));

		loadflowEngine.setItems(mainApp.getLoadflowEngines());
		dsEngine.setItems(mainApp.getDsEngines());
		actionEvent.setItems(mainApp.getActionEvents());
	}

	@FXML
	private ComboBox catalogSource;
	@FXML
	private ComboBox caseSource;
	@FXML
	private ComboBox ddrSource;

	@FXML
	private ComboBox loadflowEngine;

	@FXML
	private CheckBox mainConnectedComponent;

	@FXML
	private ListView<Event> addedEvents;

	@FXML
	private TitledPane addEventPane;
	@FXML
	private TextField elementEvent;
	@FXML
	private ComboBox actionEvent;

	@FXML
	private ComboBox dsEngine;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowNewController.class);
}
