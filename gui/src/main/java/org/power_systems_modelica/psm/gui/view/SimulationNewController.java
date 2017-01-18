package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.utils.AutoFillTextBox;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SimulationNewController implements MainChildrenController
{
	@Override
	public void handleMainAction() {
		
		handleStartWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{
		
		switch(action) {
		case "Load":
			handleLoadWorkflow();
			break;
		case "Save":
			handleSaveWorkflow();
			break;
		case "Clean":
			handleCleanWorkflow();
			break;
		case "Check":
			handleCheckWorkflow();
			break;
		case "Verify":
			handleVerifyWorkflow();
			break;
		}
	}

	@Override
	public String getMainAction() {

		return "Simulate";
	}

	@Override
	public List<String> getMenuActions() {

		List<String> actions = new ArrayList();
		actions.add("Load");
		actions.add("Save");
		actions.add("Clean");
		actions.add("separator");
		actions.add("Check");
		actions.add("Verify");
		return actions;
	}

	@Override
	public List<String> getSummaryLabels() {
		
		return null;
	}

	@FXML
	private void initialize()
	{
		addEventPane.setVisible(false);
		Utils.setDragablePane(addEventPane);

		elementEvent.setFilterMode(true);

		catalogCaseSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Catalog>()
				{
					@Override
					public void changed(ObservableValue<? extends Catalog> observable,
							Catalog oldValue, Catalog newValue)
					{
						if (newValue != null)
							caseSource.setItems(mainService.getConvertedCases(newValue.getName()));
					}

				});
		caseSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ConvertedCase>()
				{
					@Override
					public void changed(ObservableValue<? extends ConvertedCase> observable,
							ConvertedCase oldValue,
							ConvertedCase newValue)
					{
						if (newValue != null)
							actionEvent.setItems(mainService.getActionEvents(newValue));
						if (newValue != oldValue)
							addedEvents.getItems().clear();
					}
				});
		actionEvent.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>()
				{
					@Override
					public void changed(ObservableValue<? extends String> observable,
							String oldValue, String newValue)
					{
						if (newValue != null)
						{
							elementEvent.setData(mainService.getNetworkElements(
									caseSource.getSelectionModel().getSelectedItem(), newValue));
						}
					}
				});
		parametersView.setEditable(true);
		nameParamColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueParamColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
		valueParamColumn
				.setCellValueFactory(new PropertyValueFactory<EventParamGui, String>("value"));
		valueParamColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		valueParamColumn.setOnEditCommit(new EventHandler<CellEditEvent<EventParamGui, String>>()
		{
			@Override
			public void handle(CellEditEvent<EventParamGui, String> t)
			{
				// update value
				((EventParamGui) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setValue(t.getNewValue());

				// move focus & selection
				// we need to clear the current selection first or else the selection would be added to the current selection since we are in multi selection mode
				@SuppressWarnings("unchecked")
				TablePosition<EventParamGui, String> pos = parametersView.getFocusModel()
						.getFocusedCell();
				if (pos.getRow() == -1)
				{
					parametersView.getSelectionModel().select(0);
				}
				else if (pos.getRow() == parametersView.getItems().size() - 1)
				{
					return;
				} // select next row, but same column as the current selection
				else if (pos.getRow() < parametersView.getItems().size() - 1)
				{
					parametersView.getSelectionModel().clearAndSelect(pos.getRow() + 1,
							valueParamColumn);
				}
				parametersView.requestFocus();
			}
		});

		// switch to edit mode on keypress
		// this must be KeyEvent.KEY_PRESSED so that the key gets forwarded to the editing cell; it wouldn't be forwarded on KEY_RELEASED
		parametersView.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{

				if (event.getCode() == KeyCode.ENTER)
				{
					return;
				}

				// switch to edit mode on keypress, but only if we aren't already in edit mode
				if (parametersView.getEditingCell() == null)
				{
					if (event.getCode().isLetterKey() || event.getCode().isDigitKey())
					{
						@SuppressWarnings("unchecked")
						TablePosition<EventParamGui, String> focusedCellPosition = parametersView
								.getFocusModel()
								.getFocusedCell();
						parametersView.edit(focusedCellPosition.getRow(), valueParamColumn);
					}
				}
			}
		});

		// single cell selection mode
		// parametersView.getSelectionModel().setCellSelectionEnabled(true);
	}

	@FXML
	private void handleOpenAddEvent()
	{
		LOG.debug("handleAddEvent");
		actionEvent.getSelectionModel().clearSelection();
		elementEvent.getTextbox().clear();
		parametersView.setItems(null);
		addEventPane.setVisible(true);
	}

	@FXML
	private void handleActionSelectedEvent()
	{
		String event = actionEvent.getSelectionModel().getSelectedItem();
		if (event != null)
			parametersView.setItems(mainService.getEventParams(event));
	}

	@FXML
	private void handleAddEvent()
	{
		LOG.debug("handleAddEvent");

		Event e = new Event();
		e.setElement(elementEvent.getText());
		e.setAction(actionEvent.getSelectionModel().getSelectedItem());
		e.setParams(parametersView.getItems());

		addedEvents.getItems().add(e);
		addEventPane.setVisible(false);
	}

	@FXML
	private void handleCancelEvent()
	{
		LOG.debug("handleAddEvent");
		addEventPane.setVisible(false);
	}

	@FXML
	private void handleRemoveEvent()
	{
		LOG.debug("handleRemoveEvent");
		ObservableList<Event> list = addedEvents.getSelectionModel().getSelectedItems();
		addedEvents.getItems().removeAll(list);
	}

	private void handleLoadWorkflow()
	{
		handleCleanWorkflow();
		try
		{
			Properties workflowProperties = PathUtils.loadSimulationFile(fileChooser,
					mainService.getPrimaryStage(),
					System.getProperty("user.home"));
			loadWorkflow(workflowProperties);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadWorkflow(Properties workflowProperties)
	{
		if (workflowProperties.containsKey("casePath"))
		{
			String casePath = workflowProperties.getProperty("casePath");
			Utils.resolveConvertedCasePath(casePath, catalogCaseSource, caseSource);
		}

		if (workflowProperties.containsKey("dsEngine"))
		{
			String dse = workflowProperties.getProperty("dsEngine");
			dsEngine.getSelectionModel().select(Utils.getDsEngine(dse));
		}

		if (workflowProperties.containsKey("dsStopTime"))
		{
			String stopTime = workflowProperties.getProperty("dsStopTime");
			stopTimeText.setText(stopTime);
		}

		if (workflowProperties.containsKey("events"))
		{
			String[] events = workflowProperties.getProperty("events").split("\n");
			for (String event : events)
			{

				Event e = new Event();
				e.fromString(event);
				addedEvents.getItems().add(e);
			}
		}
	}

	private void handleSaveWorkflow()
	{
		ConvertedCase cs = caseSource.getSelectionModel().getSelectedItem();
		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();
		String stopTime = stopTimeText.getText();

		ObservableList<Event> events = addedEvents.getItems();

		Properties workflowProperties;
		try
		{
			workflowProperties = Utils.getSimulationProperties(cs, events, dse, stopTime);
			PathUtils.saveSimulationFile(fileChooser, mainService.getPrimaryStage(),
					System.getProperty("user.home"),
					workflowProperties);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleCleanWorkflow()
	{
		caseSource.getSelectionModel().clearSelection();
		catalogCaseSource.getSelectionModel().clearSelection();

		dsEngine.getSelectionModel().select(DsEngine.OPENMODELICA);
		addedEvents.getItems().clear();

		stopTimeText.setText("1");
	}

	private void handleCheckWorkflow()
	{
		LOG.debug("handleCheckWorkflow");

		startWorkflow(true, false);
	}

	private void handleVerifyWorkflow()
	{
		LOG.debug("handleCheckWorkflow");

		startWorkflow(false, true);
	}

	private void handleStartWorkflow()
	{
		LOG.debug("handleStartWorkflow");

		startWorkflow(false, false);
	}

	private void startWorkflow(boolean onlyCheck, boolean onlyVerify)
	{
		ConvertedCase cs = caseSource.getSelectionModel().getSelectedItem();
		if (cs == null)
		{
			Utils.showWarning("Warning", "Select a case");
			return;
		}

		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();
		if (dse == null)
		{
			Utils.showWarning("Warning", "Select a Dynamic simulation engine");
			return;
		}
		String stopTime = stopTimeText.getText();
		ObservableList<Event> events = addedEvents.getItems();
		mainService.startSimulation(cs, events, dse, stopTime, onlyCheck, onlyVerify);
	}

	@FXML
	private void handleEditCommitEvent()
	{
	}

	public void setCase(Case c)
	{
		Utils.resolveConvertedCasePath(c.getLocation(), catalogCaseSource, caseSource);
	}

	public void setWorkflow(Workflow w)
	{
		handleCleanWorkflow();
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{

			if (td.getTaskClass().equals(StaticNetworkImporterTask.class))
			{
				String casePath = td.getTaskConfiguration().getParameter("source");
				System.out.println("Simulation new" + casePath);
				Utils.resolveConvertedCasePath(casePath, catalogCaseSource, caseSource);
			}

			if (td.getTaskClass().equals(ModelicaEventAdderTask.class))
			{
				String[] events = td.getTaskConfiguration().getParameter("events").split("\n");
				for (String event : events)
				{

					Event e = new Event();
					e.fromString(event);
					addedEvents.getItems().add(e);
				}
			}
			if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
			{
				String stopTime = td.getTaskConfiguration().getParameter("stopTime");
				stopTimeText.setText(stopTime);

				String simulationEngine = td.getTaskConfiguration().getParameter("modelicaEngine");
				dsEngine.getSelectionModel().select(Utils.getDsEngine(simulationEngine));
			}
		}
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		catalogCaseSource.setItems(mainService.getCatalogs("cases"));

		dsEngine.setItems(mainService.getDsEngines());
		dsEngine.getSelectionModel().select(DsEngine.OPENMODELICA);
	}

	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	public void setDefaultInit()
	{
		handleCleanWorkflow();
		try
		{
			Properties workflowProperties = PathUtils.loadDefaultSimulationFile();
			loadWorkflow(workflowProperties);
		}
		catch (IOException e)
		{
		}
	}

	@FXML
	private ComboBox<Catalog>					catalogCaseSource;
	@FXML
	private ComboBox<ConvertedCase>				caseSource;

	@FXML
	private ListView<Event>						addedEvents;

	@FXML
	private TitledPane							addEventPane;
	@FXML
	private AutoFillTextBox<String>				elementEvent;
	@FXML
	private ComboBox<String>					actionEvent;
	@FXML
	private TableView<EventParamGui>			parametersView;
	@FXML
	private TableColumn<EventParamGui, String>	nameParamColumn;
	@FXML
	private TableColumn<EventParamGui, String>	valueParamColumn;

	@FXML
	private ComboBox<DsEngine>					dsEngine;
	@FXML
	private TextField							stopTimeText;

	private GuiFileChooser						fileChooser;
	private MainService							mainService;

	private static final Logger					LOG	= LoggerFactory
			.getLogger(SimulationNewController.class);
}
