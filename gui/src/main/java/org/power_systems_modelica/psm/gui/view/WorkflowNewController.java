package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
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
		Utils.setDragablePane(addEventPane);
		
		catalogCaseSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				if (newValue != null)
					caseSource.setItems(mainApp.getCases(newValue.getName()));
			}

		});

		catalogDdrSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				if (newValue != null)
					ddrSource.setItems(mainApp.getDdrs(newValue.getName()));
			}

		});

		ddrSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ddr>() {

			@Override
			public void changed(ObservableValue<? extends Ddr> observable, Ddr oldValue, Ddr newValue) {
				if (newValue != null)
					actionEvent.setItems(mainApp.getActionEvents(newValue));
			}

		});

		parametersView.setEditable(true);
		nameParamColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueParamColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
		valueParamColumn.setCellValueFactory(new PropertyValueFactory<EventParamGui, String>("value"));
		valueParamColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		valueParamColumn.setOnEditCommit(
		    new EventHandler<CellEditEvent<EventParamGui, String>>() {
		        @Override
		        public void handle(CellEditEvent<EventParamGui, String> t) {
		            ((EventParamGui) t.getTableView().getItems().get(
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
	private void handleCleanWorkflow() {
		caseSource.getSelectionModel().clearSelection();
		catalogCaseSource.getSelectionModel().clearSelection();
		ddrSource.getSelectionModel().clearSelection();
		catalogDdrSource.getSelectionModel().clearSelection();

		loadflowEngine.getSelectionModel().select(LoadflowEngine.NONE);
		dsEngine.getSelectionModel().select(DsEngine.OPENMODELICA);
		addedEvents.getItems().clear();
		
		stopTimeText.setText("1");
		
		mainConnectedComponent.setSelected(MAINCONNECTEDCOMPONENTDEFAULT);
	}
	
	@FXML
	private void handleStartWorkflow() {
		LOG.debug("handleStartWorkflow");

		Case cs = caseSource.getSelectionModel().getSelectedItem();
		if (cs == null) {
			Utils.showWarning("Warning", "Select a case");
			return;
		}
		Ddr ddr = ddrSource.getSelectionModel().getSelectedItem();
		if (ddr == null) {
			Utils.showWarning("Warning", "Select a DDR");
			return;
		}

		LoadflowEngine le = loadflowEngine.getSelectionModel().getSelectedItem();
		if (le == null) {
			Utils.showWarning("Warning", "Select a Loadflow engine");
			return;
		}

		boolean onlyMainConnectedComponent = mainConnectedComponent.isSelected();

		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();
		if (dse == null) {
			Utils.showWarning("Warning", "Select a Dynamic simulation engine");
			return;
		}
		
		String stopTime = stopTimeText.getText();

		ObservableList<Event> events = addedEvents.getItems();

		mainApp.startWorkflow(cs, ddr, le, onlyMainConnectedComponent, events, dse, stopTime);
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
	
	public void setWorkflow(Workflow w) {
		
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {
			
			if (td.getTaskClass().equals(StaticNetworkImporterTask.class)) {
				String casePath = td.getTaskConfiguration().getParameter("source");
				Utils.resolveCasePath(casePath, catalogCaseSource, caseSource);
			}
			
			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class)) {
				String ddrPath = td.getTaskConfiguration().getParameter("ddrLocation");
				Utils.resolveDdrPath(ddrPath, catalogDdrSource, ddrSource);
				
				Boolean onlyMainConnectedComponent = td.getTaskConfiguration().getBoolean("onlyMainConnectedComponent");
				mainConnectedComponent.setSelected(onlyMainConnectedComponent);
			}
			
			if (td.getTaskClass().equals(ModelicaEventAdderTask.class)) {
				String[] events = td.getTaskConfiguration().getParameter("events").split("\n");
				for (String event: events) {
					
					Event e = new Event();
					e.fromString(event);
					addedEvents.getItems().add(e);
				}
			}
			

			if (td.getTaskClass().equals(LoadFlowTask.class))
				loadflowEngine.getSelectionModel().select(Utils.getLoadflowEngine(td.getTaskId()));
			
			if (td.getTaskClass().equals(ModelicaSimulatorTask.class)) {
				String stopTime = td.getTaskConfiguration().getParameter("stopTime");
				stopTimeText.setText(stopTime);
				
				dsEngine.getSelectionModel().select(Utils.getDsEngine(td.getTaskId()));
			}
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		catalogCaseSource.setItems(mainApp.getCatalogs("cases"));
		catalogDdrSource.setItems(mainApp.getCatalogs("ddrs"));

		loadflowEngine.setItems(mainApp.getLoadflowEngines());
		loadflowEngine.getSelectionModel().select(LoadflowEngine.NONE);
		
		dsEngine.setItems(mainApp.getDsEngines());
		dsEngine.getSelectionModel().select(DsEngine.OPENMODELICA);
		stopTimeText.setText("1");

		
		mainConnectedComponent.setSelected(MAINCONNECTEDCOMPONENTDEFAULT);
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
	private TableView<EventParamGui> parametersView;
    @FXML
    private TableColumn<EventParamGui, String> nameParamColumn;
    @FXML
    private TableColumn<EventParamGui, String> valueParamColumn;

	@FXML
	private ComboBox<DsEngine> dsEngine;
	@FXML
	private TextField stopTimeText;

	private MainApp mainApp;

	private static final Boolean MAINCONNECTEDCOMPONENTDEFAULT = new Boolean(true);
	private static final Logger LOG = LoggerFactory.getLogger(WorkflowNewController.class);

}
