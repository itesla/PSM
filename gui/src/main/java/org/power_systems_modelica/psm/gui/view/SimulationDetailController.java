package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;

public class SimulationDetailController
{
	@FXML
	private void initialize()
	{
		fileContentPane.setVisible(false);
		Utils.setDragablePane(fileContentPane);

		modelicaFileButton.setDisable(true);
		modelicaEventsFileButton.setDisable(true);

		dsChart.setCreateSymbols(false);
		yDsAxis.setForceZeroInRange(false);
		yDsAxis.setAutoRanging(true);

		voltageChart.setLegendVisible(false);
		phaseChart.setLegendVisible(false);
		activeChart.setLegendVisible(false);
		reactiveChart.setLegendVisible(false);

		yVoltageAxis.setForceZeroInRange(false);
		yVoltageAxis.setAutoRanging(true);

		yPhaseAxis.setForceZeroInRange(false);
		yPhaseAxis.setAutoRanging(true);

		yActiveAxis.setForceZeroInRange(false);
		yActiveAxis.setAutoRanging(true);

		yReactiveAxis.setForceZeroInRange(false);
		yReactiveAxis.setAutoRanging(true);
	}

	@FXML
	private void handleNewWorkflow()
	{
		LOG.debug("handleNewWorkflow");
		mainService.showSimulationNewView(mainService.getSimulation());
	}

	@FXML
	private void handleFileEvent()
	{
		LOG.debug("handleFileEvent");
		showModelicaFileContent("eventAdder_initial.mo");
	}

	@FXML
	private void handleFileWithEventsEvent()
	{
		LOG.debug("handleFileWithEventsEvent");
		showModelicaFileContent("eventAdder_events.mo");
	}

	@FXML
	private void handleSaveFileContentEvent()
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileContentPane.setVisible(false);
	}

	@FXML
	private void handleSaveAsFileContentEvent()
	{
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		boolean close = true;
		try
		{
			close = PathUtils.saveAsMoFile(fileChooser, mainService.getPrimaryStage(), location,
					file, ddrContent);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileContentPane.setVisible(!close);
	}

	@FXML
	private void handleRevertFileContentEvent()
	{
		codeEditor.revertEdits();
	}

	@FXML
	private void handleCloseFileContentEvent()
	{
		fileContentPane.setVisible(false);
	}

	private void showModelicaFileContent(String file)
	{

		StringBuilder fileContent = new StringBuilder();
		try
		{
			fileContent = PathUtils.loadFile(PathUtils.DATA_TMP.toString(), file);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		codeEditor.setEditingFile(PathUtils.DATA_TMP.toString(), file);
		codeEditor.setCode(fileContent);
		codeEditor.setVisible(true);
		fileContentPane.setVisible(true);
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

	public void addSeries(WorkflowResult results)
	{
		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = FXCollections
				.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedPhaseSeries = FXCollections
				.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedActiveSeries = FXCollections
				.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedReactiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> valuesV = new XYChart.Series<>();
		valuesV.setName("V");
		XYChart.Series<String, Number> valuesA = new XYChart.Series<>();
		valuesA.setName("A");
		XYChart.Series<String, Number> valuesP = new XYChart.Series<>();
		valuesP.setName("P");
		XYChart.Series<String, Number> valuesQ = new XYChart.Series<>();
		valuesQ.setName("Q");

		for (BusData bus : results.getAllBusesValues())
		{
			valuesV.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("V", 0)));
			valuesA.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("A", 0)));
			valuesP.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("P", 0)));
			valuesQ.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("Q", 0)));
		}

		displayedVoltageSeries.add(valuesV);
		displayedPhaseSeries.add(valuesA);
		displayedActiveSeries.add(valuesP);
		displayedReactiveSeries.add(valuesQ);

		voltageChart.getData().addAll(displayedVoltageSeries);
		phaseChart.getData().addAll(displayedPhaseSeries);
		activeChart.getData().addAll(displayedActiveSeries);
		reactiveChart.getData().addAll(displayedReactiveSeries);

		ObservableList<XYChart.Series<Number, Number>> displayedDsSeries = FXCollections
				.observableArrayList();
		for (String key : results.getDsValues().keySet())
		{
			if (!key.endsWith(".V"))
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

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		if (Files.exists(PathUtils.DATA_TMP.resolve("eventAdder_initial.mo"),
				LinkOption.NOFOLLOW_LINKS))
		{
			modelicaFileButton.setDisable(false);
		}

		if (Files.exists(PathUtils.DATA_TMP.resolve("eventAdder_events.mo"),
				LinkOption.NOFOLLOW_LINKS))
		{
			modelicaEventsFileButton.setDisable(false);
		}
	}

	public void setWorkflow(Workflow w)
	{

		createdLabel.setText("" + w.getId());
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(LoadFlowTask.class))
				loadflowLabel.setText(Utils.getLoadflowEngine(td.getTaskId()).name());

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
				dsLabel.setText(Utils.getDsEngine(td.getTaskId()).name());
		}
		statusLabel.setText(w.getState().name());
		if (w.getState().equals(ProcessState.SUCCESS))
		{
			addSeries(mainService.getSimulationResult("" + w.getId()));
			Utils.addTooltipScatterChart(voltageChart, "pu");
			Utils.addTooltipScatterChart(phaseChart, "º");
			Utils.addTooltipScatterChart(activeChart, "MW");
			Utils.addTooltipScatterChart(reactiveChart, "MVar");
			Utils.addTooltipLineChartPosition(dsChart, "Time", "s", "Voltage", "pu");
		}

	}

	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@FXML
	private TitledPane						fileContentPane;
	@FXML
	private CodeEditor						codeEditor;

	@FXML
	private Button							modelicaFileButton;
	@FXML
	private Button							modelicaEventsFileButton;

	@FXML
	private Label							createdLabel;
	@FXML
	private Label							statusLabel;
	@FXML
	private Label							loadflowLabel;
	@FXML
	private Label							dsLabel;

	@FXML
	private ScatterChart<String, Number>	voltageChart;
	@FXML
	private CategoryAxis					xVoltageAxis;
	@FXML
	private NumberAxis						yVoltageAxis;

	@FXML
	private ScatterChart<String, Number>	phaseChart;
	@FXML
	private CategoryAxis					xPhaseAxis;
	@FXML
	private NumberAxis						yPhaseAxis;

	@FXML
	private ScatterChart<String, Number>	activeChart;
	@FXML
	private CategoryAxis					xActiveAxis;
	@FXML
	private NumberAxis						yActiveAxis;

	@FXML
	private ScatterChart<String, Number>	reactiveChart;
	@FXML
	private CategoryAxis					xReactiveAxis;
	@FXML
	private NumberAxis						yReactiveAxis;

	@FXML
	private LineChart<Number, Number>		dsChart;
	@FXML
	private NumberAxis						xDsAxis;
	@FXML
	private NumberAxis						yDsAxis;

	private Map<String, Paint>				colors	= new HashMap<String, Paint>();
	private GuiFileChooser					fileChooser;
	private MainService						mainService;

	private static final Logger				LOG		= LoggerFactory
			.getLogger(SimulationDetailController.class);
}
