package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.AutoFillTextBox;
import org.power_systems_modelica.psm.gui.utils.fx.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.PathUtilsFX;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.SingleTerminalConnectable;
import eu.itesla_project.iidm.network.TwoTerminalsConnectable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;

public class SimulationDetailController implements MainChildrenController
{
	@Override
	public void handleMainAction()
	{

		handleNewWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{

	}

	@Override
	public String getMainAction()
	{

		return "New simulation";
	}

	@Override
	public List<String> getMenuActions()
	{

		return null;
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{
		String dateLabel = "";
		if (date != null)
			dateLabel = date.toString("yyyy/MM/dd HH:mm:ss");

		List<SummaryLabel> labels = new ArrayList<>();
		labels.add(new SummaryLabel("Case:", caseLabel, false, true));
		labels.add(new SummaryLabel("Created:", dateLabel, true, true));
		labels.add(new SummaryLabel("Dynamic simulator:", dsLabel, false, false));
		return labels;
	}

	@Override
	public ObservableValue<? extends Boolean> disableBackground()
	{
		return new SimpleBooleanProperty(false);
	}

	@Override
	public Button getDefaultEnterButton()
	{
		return null;
	}

	public void addSeries(WorkflowResult results)
	{
		element.clear();
		ObservableList<String> buses = FXCollections.observableArrayList();
		List<String> keys = results.getDsValues().keySet().stream().filter(k -> k.endsWith(".V"))
				.filter(k -> !selectedBuses.contains(k)).collect(Collectors.toList());
		buses.addAll(keys);
		element.setData(buses);

		ObservableList<XYChart.Series<Number, Number>> displayedDsSeries = FXCollections
				.observableArrayList();
		for (String key : results.getDsValues().keySet())
		{
			if (!selectedBuses.contains(key))
				continue;

			XYChart.Series<Number, Number> valuesDS = new XYChart.Series<>();
			valuesDS.setName(key);

			for (DsData xyValue : results.getDsValues().get(key))
			{
				valuesDS.getData().add(new XYChart.Data<>(xyValue.getX(), xyValue.getY()));
			}
			displayedDsSeries.add(valuesDS);
		}

		dsChart.getData().addAll(displayedDsSeries);
		highlightSeriesOnHover(displayedDsSeries);
	}

	public void addDefaultBuses(Workflow w, WorkflowResult results)
	{
		List<String> keys = results.getDsValues().keySet().stream().filter(k -> k.endsWith(".V"))
				.collect(Collectors.toList());

		Network n = (Network) w.getResults("network");

		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(ModelicaEventAdderTask.class))
			{
				String[] events = td.getTaskConfiguration().getParameter("events").split("\n");
				for (String event : events)
				{

					Event e = new Event();
					e.fromString(event);
					keys.stream().filter(k -> containsRelatedBuses(n, e, k)).forEach(key -> {
						selectedBuses.add(key);
					});
				}
			}
		}

		if (selectedBuses.isEmpty())
		{
			int max = Math.min(5, keys.size());
			selectedBuses.addAll(keys.subList(0, max));
		}
	}

	private boolean containsRelatedBuses(Network n, Event e, String key)
	{
		Identifiable<?> i = n.getIdentifiable(e.getElement());
		System.out.println("ID: " + i.getId() + " key: " + key);
		if (i instanceof Bus)
		{
			return key.contains(i.getId());
		}
		else if (i instanceof SingleTerminalConnectable)
		{
			return key.contains(((SingleTerminalConnectable<?>) i).getTerminal().getBusBreakerView().getBus()
					.getId());
		}
		else if (i instanceof TwoTerminalsConnectable)
		{
			return key.contains(((TwoTerminalsConnectable<?>) i).getTerminal1().getBusBreakerView().getBus()
					.getId())
					|| key.contains(((TwoTerminalsConnectable<?>) i).getTerminal2().getBusBreakerView().getBus()
							.getId());
		}

		return false;
	}

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		String moInput = null;
		curvesTab.setDisable(true);
		moTab.setDisable(true);
		moweTab.setDisable(true);
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(ModelicaParserTask.class))
			{
				moInput = td.getTaskConfiguration().getParameter("source");

				java.nio.file.Path casePath;
				if (moInput.endsWith(".mo"))
				{
					java.nio.file.Path path = Paths.get(moInput);
					casePath = path.getParent();
				}
				else
					casePath = Paths.get(moInput);

				java.nio.file.Path catalogPath = casePath.getParent();

				try
				{
					Catalog catalog = CatalogService.getCatalog("cases", catalogPath);
					Case c = CaseService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + "\t" + c.getName();

					BasicFileAttributes attr = Files.readAttributes(Paths.get(moInput),
							BasicFileAttributes.class);
					date = new DateTime(attr.lastModifiedTime().toMillis());
				}
				catch (IOException e)
				{
					caseLabel = "";
					date = null;
				}
			}

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
			{
				String simulationEngine = td.getTaskConfiguration().getParameter("modelicaEngine");
				dsLabel = Utils.getDsEngine(simulationEngine).toString();
			}
		}

		if (moInput != null)
		{

			java.nio.file.Path moInputPath = Paths.get(moInput);
			String path = moInputPath.toFile().getParent();
			if (Files.exists(moInputPath, LinkOption.NOFOLLOW_LINKS))
			{
				moTab.setDisable(false);
				String file = moInputPath.toFile().getName();
				showModelicaFileContent(moEditor, path, file);
			}

			String moweInput = Utils.replaceLast(moInput, ".mo", "_events.mo");
			java.nio.file.Path moweInputPath = Paths.get(moweInput);
			if (Files.exists(moweInputPath, LinkOption.NOFOLLOW_LINKS))
			{
				moweTab.setDisable(false);
				String file = moweInputPath.toFile().getName();
				showModelicaFileContent(moweEditor, path, file);
			}
		}

		results = WorkflowServiceConfiguration.getSimulationResult("" + w.getId());
		if (w.getState().equals(ProcessState.SUCCESS))
		{
			curvesTab.setDisable(false);
			addDefaultBuses(w, results);
			addSeries(results);
			UtilsFX.addTooltipLineChartPosition(dsChart, "Time", "s", "Voltage", "pu");
		}
		else
		{
			tabPane.getSelectionModel().select(logTab);
		}

		StringBuilder sb = new StringBuilder();
		for (Exception e : results.getExceptions())
		{
			sb.append(Utils.getStackTrace(e));
			sb.append("\n\n");
		}

		logArea.setText(sb.toString());
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@Override
	public void setDefaultInit()
	{
	}

	@FXML
	private void initialize()
	{
		element.setFilterMode(true);

		dsChart.setCreateSymbols(false);
		yDsAxis.setForceZeroInRange(false);
		yDsAxis.setAutoRanging(true);
	}

	private void handleNewWorkflow()
	{
		LOG.debug("handleNewWorkflow");
		mainService.showSimulationNewView(WorkflowServiceConfiguration.getSimulation());
	}

	@FXML
	private void handleAddElement()
	{
		LOG.debug("handleAddElement");
		String bus = element.getText();
		element.removeData(bus);
		element.resetTextbox();
		selectedBuses.add(bus);

		XYChart.Series<Number, Number> valuesDS = new XYChart.Series<>();
		valuesDS.setName(bus);
		for (DsData xyValue : results.getDsValues().get(bus))
		{
			valuesDS.getData().add(new XYChart.Data<>(xyValue.getX(), xyValue.getY()));
		}

		dsChart.getData().add(valuesDS);
		highlightSeriesOnHover(dsChart.getData());
	}

	private void handleRemoveElement(String bus)
	{
		LOG.debug("handleRemoveElement");
		element.addData(bus);
		selectedBuses.remove(bus);
		FilteredList<XYChart.Series<Number, Number>> series = dsChart.getData()
				.filtered(s -> s.getName().equals(bus));
		if (series.isEmpty()) return;

		dsChart.getData().removeAll(series);
	}

	@FXML
	private void handleFindMoContentEvent()
	{
		moEditor.find();
	}

	@FXML
	private void handleFindMoweContentEvent()
	{
		moweEditor.find();
	}

	@FXML
	private void handleSaveMoFileContentEvent()
	{
		saveFileContentEvent(moEditor);
	}

	@FXML
	private void handleSaveMoweFileContentEvent()
	{
		saveFileContentEvent(moweEditor);
	}

	private void saveFileContentEvent(CodeEditor codeEditor)
	{
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		try
		{
			PathUtils.saveFile(location, file, ddrContent);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void handleSaveAsMoFileContentEvent()
	{
		saveAsFileContentEvent(moEditor);
	}

	@FXML
	private void handleSaveAsMoweFileContentEvent()
	{
		saveAsFileContentEvent(moweEditor);
	}

	private void saveAsFileContentEvent(CodeEditor codeEditor)
	{
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		try
		{
			PathUtilsFX.saveAsMoFile(fileChooser, mainService.getPrimaryStage(), location,
					file, ddrContent);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	private void handleRevertMoFileContentEvent()
	{
		moEditor.revertEdits();
	}

	@FXML
	private void handleRevertMoweFileContentEvent()
	{
		moweEditor.revertEdits();
	}

	private void showModelicaFileContent(CodeEditor codeEditor, String path, String file)
	{

		StringBuilder fileContent = new StringBuilder();
		try
		{
			fileContent = PathUtils.loadFile(path, file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		codeEditor.setEditingFile(path, file);
		codeEditor.setCode(fileContent);
		codeEditor.setVisible(true);
	}

	private <S1, S2, T extends List<XYChart.Series<S1, S2>>> void highlightSeriesOnHover(
			T seriesList)
	{
		for (XYChart.Series<S1, S2> series : seriesList)
		{
			Node seriesNode = series.getNode();
			// seriesNode will be null if this method is called before the scene
			// CSS has been applied
			if (seriesNode != null && seriesNode instanceof Path)
			{
				Path seriesPath = (Path) seriesNode;

				seriesPath.setOnMouseEntered(event -> {
					highlightSerie(seriesList, seriesPath);
				});
				seriesPath.setOnMouseExited(event -> {
					// Reset
					highlightSerie(seriesList, null);
				});

				seriesPath.setOnMouseClicked(new EventHandler<MouseEvent>()
				{

					@Override
					public void handle(MouseEvent event)
					{

						if (MouseButton.SECONDARY.equals(event.getButton()))
						{
							MenuItem removeItem = new MenuItem("Remove " + series.getName());
							removeItem.setOnAction(new EventHandler<ActionEvent>()
							{
								@Override
								public void handle(ActionEvent event)
								{
									handleRemoveElement(series.getName());
								}
							});
							menu.getItems().clear();
							menu.getItems().add(removeItem);
							menu.show(mainService.getPrimaryStage(), event.getScreenX(),
									event.getScreenY());
						}
					}
				});
			}
		}
	}

	private <S1, S2, T extends List<XYChart.Series<S1, S2>>> void highlightSerie(T seriesList,
			Path seriesPath)
	{
		for (XYChart.Series<?, ?> series : seriesList)
		{
			Node seriesNode = series.getNode();
			// seriesNode will be null if this method is called before the scene
			// CSS has been applied
			if (seriesNode != null && seriesNode instanceof Path)
			{
				Path sPath = (Path) seriesNode;
				Paint color = colors.get(series.getName());
				if (color == null)
				{
					color = sPath.getStroke();
					colors.put(series.getName(), color);
				}
				int strokeWidth = 2;
				double opacity = 1;
				if (seriesPath != null)
				{
					if (sPath == seriesPath)
					{
						color = ((Color) color).darker();
						strokeWidth = 4;
					}
					else
					{
						color = Color.GRAY;
						strokeWidth = 1;
						opacity = 0.5;
					}
				}

				sPath.setStroke(color);
				sPath.setStrokeWidth(strokeWidth);
				sPath.setOpacity(opacity);
			}
		}
	}

	@FXML
	private TabPane						tabPane;

	@FXML
	private Tab							curvesTab;
	@FXML
	private Tab							moTab;
	@FXML
	private Tab							moweTab;
	@FXML
	private Tab							logTab;

	@FXML
	private CodeEditor					moEditor;
	@FXML
	private CodeEditor					moweEditor;

	@FXML
	private AutoFillTextBox<String>		element;
	@FXML
	private LineChart<Number, Number>	dsChart;
	@FXML
	private NumberAxis					xDsAxis;
	@FXML
	private NumberAxis					yDsAxis;

	@FXML
	private TextArea					logArea;

	private String						caseLabel;
	private DateTime					date;
	private String						dsLabel;

	private Map<String, Paint>			colors			= new HashMap<String, Paint>();
	private GuiFileChooser				fileChooser;

	ObservableList<String>				selectedBuses	= FXCollections.observableArrayList();
	private ContextMenu					menu			= new ContextMenu();

	private WorkflowResult				results;
	private MainService					mainService;

	private static final Logger			LOG				= LoggerFactory
			.getLogger(SimulationDetailController.class);

}
