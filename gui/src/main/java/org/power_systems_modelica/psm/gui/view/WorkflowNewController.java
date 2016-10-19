package org.power_systems_modelica.psm.gui.view;

import java.util.List;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.model.WorkflowResultItem;
import org.power_systems_modelica.psm.gui.service.Workflow;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public class WorkflowNewController {

	@FXML
	private void initialize() {

		catalogSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				caseSource.setItems(mainApp.getCases(newValue.getName()));
				ddrSource.setItems(mainApp.getDdrs(newValue.getName()));
			}

		});
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

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		catalogSource.setItems(mainApp.getCatalogs());

		loadflowEngine.setItems(mainApp.getLoadflowEngines());
		dsEngine.setItems(mainApp.getDsEngines());
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
	private ListView addedEvents;

	@FXML
	private ComboBox dsEngine;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowNewController.class);
}
