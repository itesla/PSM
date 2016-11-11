package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParam;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class WorkflowNewController {

	@FXML
	private void initialize() {

		addEventPane.setVisible(false);
		catalogCaseSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				caseSource.setItems(mainApp.getCases(newValue.getName()));
			}

		});

		catalogDdrSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				ddrSource.setItems(mainApp.getDdrs(newValue.getName()));
			}

		});

		ddrSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ddr>() {

			@Override
			public void changed(ObservableValue<? extends Ddr> observable, Ddr oldValue, Ddr newValue) {
				actionEvent.setItems(mainApp.getActionEvents(newValue));
			}

		});

		parametersView.setEditable(true);
		nameParamColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueParamColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
		valueParamColumn.setCellValueFactory(new PropertyValueFactory<EventParam, String>("value"));
		valueParamColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		valueParamColumn.setOnEditCommit(
		    new EventHandler<CellEditEvent<EventParam, String>>() {
		        @Override
		        public void handle(CellEditEvent<EventParam, String> t) {
		            ((EventParam) t.getTableView().getItems().get(
		                t.getTablePosition().getRow())
		                ).setValue(t.getNewValue());
		        }
		    }
		);
	}

	@FXML
	private void handleOpenAddEvent() {
		LOG.debug("handleAddEvent");
		actionEvent.getSelectionModel().clearSelection();
		elementEvent.clear();
		parametersView.setItems(null);
		addEventPane.setVisible(true);
	}
	
	@FXML
	private void handleActionSelectedEvent() {
		
		String event = actionEvent.getSelectionModel().getSelectedItem();
		if (event != null)
			parametersView.setItems(mainApp.getEventParams(event));
	}

	@FXML
	private void handleAddEvent() {
		LOG.debug("handleAddEvent");
		
		Event e = new Event();
		e.setElement(elementEvent.getText());
		e.setAction(actionEvent.getSelectionModel().getSelectedItem());
		e.setParams(parametersView.getItems());
		
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

		Catalog ctlg = catalogCaseSource.getSelectionModel().getSelectedItem();
		Case cs = caseSource.getSelectionModel().getSelectedItem();
		Ddr ddr = ddrSource.getSelectionModel().getSelectedItem();

		LoadflowEngine le = loadflowEngine.getSelectionModel().getSelectedItem();

		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();

		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();

		ObservableList<Event> events = addedEvents.getItems();

		mainApp.startWorkflow(ctlg, cs, ddr, le, onlyMainConnectedComponent, events, dse);
	}

	@FXML
	private void handleEditCommitEvent() {
		
	}
	
	public void setCase(Case c) {
		
		ObservableList<Catalog> catalogs = mainApp.getCatalogs("cases");
		
		FilteredList<Catalog> filteredCatalogs = new FilteredList<Catalog> (catalogs, catalog -> c.getLocation().contains(catalog.getLocation())); 
		
		filteredCatalogs.forEach(catalog -> {
			catalogCaseSource.getSelectionModel().select(catalog);
		});
		
		caseSource.getSelectionModel().select(c);
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		catalogCaseSource.setItems(mainApp.getCatalogs("cases"));
		catalogDdrSource.setItems(mainApp.getCatalogs("ddrs"));

		loadflowEngine.setItems(mainApp.getLoadflowEngines());
		dsEngine.setItems(mainApp.getDsEngines());
	}

	@FXML
	private ComboBox<Catalog> catalogCaseSource;
	@FXML
	private ComboBox<Case> caseSource;
	@FXML
	private ComboBox<Catalog> catalogDdrSource;
	@FXML
	private ComboBox<Ddr> ddrSource;

	@FXML
	private ComboBox<LoadflowEngine> loadflowEngine;

	@FXML
	private CheckBox mainConnectedComponent;

	@FXML
	private ListView<Event> addedEvents;

	@FXML
	private TitledPane addEventPane;
	@FXML
	private TextField elementEvent;
	@FXML
	private ComboBox<String> actionEvent;
	@FXML
	private TableView<EventParam> parametersView;
    @FXML
    private TableColumn<EventParam, String> nameParamColumn;
    @FXML
    private TableColumn<EventParam, String> valueParamColumn;

	@FXML
	private ComboBox<DsEngine> dsEngine;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowNewController.class);
}
