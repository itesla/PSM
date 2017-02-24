package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.MainApp.WorkflowType;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.service.fx.TaskService;
import org.power_systems_modelica.psm.gui.service.fx.WorkflowService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.AutoFillTextBox;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.PathUtilsFX;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	public void handleMainAction()
	{

		handleStartWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{

		switch (action)
		{
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
	public String getMainAction()
	{

		return "Simulate";
	}

	@Override
	public List<String> getMenuActions()
	{

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
	public List<SummaryLabel> getSummaryLabels()
	{

		return null;
	}

	@Override
	public ObservableValue<? extends Boolean> disableBackground()
	{
		return addEventPane.visibleProperty();
	}

	@Override
	public Button getDefaultEnterButton()
	{
		if (addEventPane.isVisible())
			return add;

		return null;
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		handleCleanWorkflow();
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{

			if (td.getTaskClass().equals(ModelicaParserTask.class))
			{
				String casePath = td.getTaskConfiguration().getParameter("source");
				System.out.println("Simulation new" + casePath);
				UtilsFX.resolveConvertedCasePath(casePath, catalogCaseSource, caseSource);
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
				addedEvents.getItems().sort(
						Comparator.comparing(t -> ((Event) t).getParam("startTime").getValue()));
			}

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
			{
				String stopTime = td.getTaskConfiguration().getParameter("stopTime");
				stopTimeText.setText(stopTime);

				String stepBySecond = td.getTaskConfiguration()
						.getParameter("numOfIntervalsPerSecond");
				stepBySecondText.setText(stepBySecond);

				String simulationEngine = td.getTaskConfiguration().getParameter("modelicaEngine");
				dsEngine.getSelectionModel().select(Utils.getDsEngine(simulationEngine));

				Boolean createFilteredMat = td.getTaskConfiguration()
						.getBoolean("createFilteredMat");
				createFilteredMatCheck.setSelected(createFilteredMat);
			}
		}
	}

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		catalogCaseSource
				.setItems(FXCollections.observableArrayList(CatalogService.getCatalogs("cases")));

		dsEngine.setItems(
				FXCollections.observableArrayList(WorkflowServiceConfiguration.getDsEngines()));
		dsEngine.getSelectionModel().select(DsEngine.OPENMODELICA);
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@Override
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
	private void initialize()
	{

		Properties p = PathUtils.getGUIProperties();
		DSE = Optional.ofNullable(p.getProperty("simulation.dynamicSimulation.engine"))
				.orElse("OpenModelica");
		STOPTIME = Optional.ofNullable(p.getProperty("simulation.dynamicSimulation.stopTime"))
				.orElse("1.0");
		STEPSECOND = Optional
				.ofNullable(p.getProperty("simulation.dynamicSimulation.stepsPerSecond"))
				.orElse("100");
		FILTMAT = Optional
				.ofNullable(p.getProperty("simulation.dynamicSimulation.createFilteredMAT"))
				.orElse("false");

		editingEvent = null;

		addEventPane.setVisible(false);
		removeEvent.setDisable(true);
		editEvent.setDisable(true);

		UtilsFX.setDragablePane(addEventPane);

		elementEvent.setFilterMode(true);

		catalogCaseSource.disableProperty().bind(addEventPane.visibleProperty());
		catalogCaseSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Catalog>()
				{
					@Override
					public void changed(ObservableValue<? extends Catalog> observable,
							Catalog oldValue, Catalog newValue)
					{
						if (newValue != null)
						{
							caseSource.setItems(FXCollections.observableArrayList(
									CaseService.getConvertedCases(newValue.getName())));
							caseSource.getItems().sort(new Comparator<ConvertedCase>()
							{

								@Override
								public int compare(ConvertedCase d1, ConvertedCase d2)
								{
									return d1.getName().compareToIgnoreCase(d2.getName());
								}

							});
						}
					}

				});
		caseSource.disableProperty().bind(addEventPane.visibleProperty());
		caseSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ConvertedCase>()
				{
					@Override
					public void changed(ObservableValue<? extends ConvertedCase> observable,
							ConvertedCase oldValue,
							ConvertedCase newValue)
					{
						if (newValue != null)
							actionEvent.setItems(FXCollections
									.observableArrayList(WorkflowServiceConfiguration
											.getAvailableEvents(newValue)));
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
							elementEvent.setData(FXCollections
									.observableArrayList(
											WorkflowServiceConfiguration.getNetworkElements(
													caseSource.getSelectionModel()
															.getSelectedItem(),
													newValue)));
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

		addedEvents.disableProperty().bind(addEventPane.visibleProperty());
		addedEvents.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Event>()
				{

					@Override
					public void changed(ObservableValue<? extends Event> observable, Event oldValue,
							Event newValue)
					{
						if (newValue != null)
						{
							removeEvent.setDisable(false);
							editEvent.setDisable(false);
						}
						else
						{
							removeEvent.setDisable(true);
							editEvent.setDisable(true);
						}
					}
				});

		dsEngine.disableProperty().bind(addEventPane.visibleProperty());
		stopTimeText.disableProperty().bind(addEventPane.visibleProperty());
		stepBySecondText.disableProperty().bind(addEventPane.visibleProperty());
		createFilteredMatCheck.disableProperty().bind(addEventPane.visibleProperty());

		// single cell selection mode
		// parametersView.getSelectionModel().setCellSelectionEnabled(true);
	}

	@FXML
	private void handleOpenAddEvent()
	{
		LOG.debug("handleAddEvent");
		editingEvent = null;
		add.setText("Add");
		elementEvent.clear();
		actionEvent.getSelectionModel().clearSelection();
		parametersView.setItems(null);
		addEventPane.setVisible(true);
	}

	@FXML
	private void handleRemoveEvent()
	{
		LOG.debug("handleRemoveEvent");
		ObservableList<Event> list = addedEvents.getSelectionModel().getSelectedItems();
		addedEvents.getItems().removeAll(list);
	}

	@FXML
	private void handleEditEvent()
	{
		LOG.debug("handleEditEvent");
		ObservableList<Event> list = addedEvents.getSelectionModel().getSelectedItems();
		editingEvent = list.get(0);

		add.setText("Edit");
		actionEvent.getSelectionModel().clearSelection();
		actionEvent.getSelectionModel().select(editingEvent.getAction());
		elementEvent.getTextbox().setText(editingEvent.getElement());
		parametersView.getItems().clear();
		parametersView.getItems().addAll(editingEvent.getParams());
		addEventPane.setVisible(true);
	}

	@FXML
	private void handleActionSelectedEvent()
	{
		String event = actionEvent.getSelectionModel().getSelectedItem();
		if (event != null)
			parametersView
					.setItems(FXCollections.observableArrayList(
							WorkflowServiceConfiguration.getEventParams(event)));
	}

	@FXML
	private void handleAddEvent()
	{
		LOG.debug("handleAddEvent");

		if (actionEvent.getSelectionModel().getSelectedItem() == null)
		{
			UtilsFX.showWarning("Warning", "Select a type");
			return;
		}
		if (elementEvent.getText().equals(""))
		{
			UtilsFX.showWarning("Warning", "Select an element");
			return;
		}
		if (parametersView.getItems().stream().filter(i -> i.getValue().equals("")).findAny()
				.isPresent())
		{
			UtilsFX.showWarning("Warning", "Complete all parameters");
			return;
		}
		if (actionEvent.getSelectionModel().getSelectedItem().equals("BusFault"))
		{
			if (parametersView.getItems().stream().filter(i -> {
				return i.getNameWithoutUnit().equals("X")
						&& Double.parseDouble(i.getValue()) < 1e-3;
			}).findAny().isPresent())
			{
				UtilsFX.showWarning("Warning",
						"Reactance parameter is too low.\nPlease a different value for X.");
				return;
			}
		}

		Event e = new Event();
		if (editingEvent != null)
			e = editingEvent;
		else
			addedEvents.getItems().add(e);

		e.setAction(actionEvent.getSelectionModel().getSelectedItem());
		e.setElement(elementEvent.getText());
		e.setParams(parametersView.getItems());

		addedEvents.getItems().sort(new Comparator<Event>()
		{

			@Override
			public int compare(Event e1, Event e2)
			{
				Double startTime1 = Double.parseDouble(e1.getParam("startTime").getValue());
				Double startTime2 = Double.parseDouble(e2.getParam("startTime").getValue());

				int c = startTime1.compareTo(startTime2);
				if (c == 0)
				{
					Double endTime1 = Double.parseDouble(e1.getParam("endTime").getValue());
					Double endTime2 = Double.parseDouble(e2.getParam("endTime").getValue());

					c = endTime1.compareTo(endTime2);
				}

				return c;
			}

		});

		addEventPane.setVisible(false);
	}

	@FXML
	private void handleCancelEvent()
	{
		LOG.debug("handleAddEvent");
		addEventPane.setVisible(false);
	}

	private void handleLoadWorkflow()
	{
		handleCleanWorkflow();
		try
		{
			Properties workflowProperties = PathUtilsFX.loadSimulationFile(fileChooser,
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
			UtilsFX.resolveConvertedCasePath(casePath, catalogCaseSource, caseSource);
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

		if (workflowProperties.containsKey("dsStepBySecond"))
		{
			String stepBySecond = workflowProperties.getProperty("dsStepBySecond");
			stepBySecondText.setText(stepBySecond);
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

		if (workflowProperties.containsKey("createFilteredMat"))
		{
			Boolean createFilteredMat = Boolean
					.valueOf(workflowProperties.getProperty("createFilteredMat"));
			createFilteredMatCheck.setSelected(createFilteredMat);
		}
	}

	private void handleSaveWorkflow()
	{
		ConvertedCase cs = caseSource.getSelectionModel().getSelectedItem();
		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();
		String stopTime = stopTimeText.getText();
		String stepBySecond = stepBySecondText.getText();

		boolean createFilteredMat = createFilteredMatCheck.isSelected();

		ObservableList<Event> events = addedEvents.getItems();

		Properties workflowProperties;
		try
		{
			workflowProperties = Utils.getSimulationProperties(cs, events, dse, stopTime,
					stepBySecond, createFilteredMat);
			PathUtilsFX.saveSimulationFile(fileChooser, mainService.getPrimaryStage(),
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

		dsEngine.getSelectionModel().select(Utils.getDsEngine(DSE));
		addedEvents.getItems().clear();

		stopTimeText.setText(STOPTIME);
		stepBySecondText.setText(STEPSECOND);

		createFilteredMatCheck.setSelected(Boolean.parseBoolean(FILTMAT));
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
			UtilsFX.showWarning("Warning", "Select a case");
			return;
		}

		DsEngine dse = dsEngine.getSelectionModel().getSelectedItem();
		if (dse == null)
		{
			UtilsFX.showWarning("Warning", "Select a Dynamic simulation engine");
			return;
		}
		String stopTime = stopTimeText.getText();
		String stepBySecond = stepBySecondText.getText();
		ObservableList<Event> events = addedEvents.getItems();

		boolean createFilteredMat = createFilteredMatCheck.isSelected();
		startSimulation(cs, events, dse, stopTime, stepBySecond, onlyCheck, onlyVerify,
				createFilteredMat);
	}

	private void startSimulation(ConvertedCase cs, ObservableList<Event> events, DsEngine dse,
			String stopTime, String stepBySecond, boolean onlyCheck, boolean onlyVerify,
			boolean createFilteredMat)
	{

		try
		{
			Workflow w = WorkflowServiceConfiguration.createSimulation(cs, events, dse, stopTime,
					stepBySecond, onlyCheck, onlyVerify, createFilteredMat);
			Task<?> task = TaskService.createTask(w,
					() -> simulationFinish(w, onlyCheck, onlyVerify));
			mainService.setSimulationTask(task);
			mainService.getMainApp().showWorkflowStatusView(mainService, w,
					WorkflowType.SIMULATION);
			TaskService.startTask(task);
		}
		catch (WorkflowCreationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void simulationFinish(Workflow w, boolean onlyCheck, boolean onlyVerify)
	{
		if (((WorkflowService) mainService.getSimulationTask()).isCancelled())
			mainService.getMainApp().showSimulationNewView(w);
		else
			mainService.getMainApp().showSimulationDetailView(mainService, w, onlyCheck,
					onlyVerify);
	}

	@FXML
	private void handleEditCommitEvent()
	{
	}

	public void setCase(Case c)
	{
		UtilsFX.resolveConvertedCasePath(c.getLocation(), catalogCaseSource, caseSource);
	}

	@FXML
	private ComboBox<Catalog>					catalogCaseSource;
	@FXML
	private ComboBox<ConvertedCase>				caseSource;

	@FXML
	private ListView<Event>						addedEvents;
	@FXML
	private Button								removeEvent;
	@FXML
	private Button								editEvent;

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
	private Button								add;

	private Event								editingEvent;

	@FXML
	private ComboBox<DsEngine>					dsEngine;
	@FXML
	private TextField							stopTimeText;
	@FXML
	private TextField							stepBySecondText;
	@FXML
	private CheckBox							createFilteredMatCheck;

	private GuiFileChooser						fileChooser;
	private MainService							mainService;

	private String								DSE;
	private String								STOPTIME;
	private String								STEPSECOND;
	private String								FILTMAT;

	private static final Logger					LOG			= LoggerFactory
			.getLogger(SimulationNewController.class);
}
