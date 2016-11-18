package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
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
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;

public class WorkflowDetailController {

	@FXML
	private void initialize() {

		fileContentPane.setVisible(false);

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
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainApp.showWorkflowNewView(mainApp.getWorkflow());
	}

	@FXML
	private void handleFileEvent() {
		LOG.debug("handleFileEvent");
		showModelicaFileContent("eventAdder_initial.mo");
	}

	@FXML
	private void handleFileWithEventsEvent() {
		LOG.debug("handleFileWithEventsEvent");
		showModelicaFileContent("eventAdder_events.mo");
	}

	@FXML
	private void handleSaveFileContentEvent() {
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		try {
			Utils.saveFile(location, file, ddrContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileContentPane.setVisible(false);
	}

	@FXML
	private void handleRevertFileContentEvent() {
		codeEditor.revertEdits();
	}

	@FXML
	private void handleCloseFileContentEvent() {
		fileContentPane.setVisible(false);
	}

	private void showModelicaFileContent(String file) {

		StringBuilder fileContent = new StringBuilder();
		try {
			fileContent = Utils.loadFile(Utils.DATA_TMP.toString(), file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		codeEditor.setEditingFile(Utils.DATA_TMP.toString(), file);
		codeEditor.setCode(fileContent);
		codeEditor.setVisible(true);
		fileContentPane.setVisible(true);
	}

	private void highlightSeriesOnHover(List<XYChart.Series> seriesList) {

		for (XYChart.Series series : seriesList) {
			Node seriesNode = series.getNode();
			// seriesNode will be null if this method is called before the scene
			// CSS has been applied
			if (seriesNode != null && seriesNode instanceof Path) {
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

	private void highlightSerie(List<XYChart.Series> seriesList, Path seriesPath) {

		for (XYChart.Series series : seriesList) {
			Node seriesNode = series.getNode();
			// seriesNode will be null if this method is called before the scene
			// CSS has been applied
			if (seriesNode != null && seriesNode instanceof Path) {

				Path sPath = (Path) seriesNode;
				Paint color = colors.get(series.getName());
				if (color == null) {
					System.out.println("serie: " + series.getName() + " color: " + sPath.getStroke().toString());
					color = sPath.getStroke();
					colors.put(series.getName(), color);
				}
				int strokeWidth = 2;
				double opacity = 1;
				if (seriesPath != null) {
					if (sPath == seriesPath) {
						color = ((Color) color).darker();
						strokeWidth = 4;
					} else {
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

	private void addSeries(WorkflowResult results) {

		ObservableList<XYChart.Series> displayedVoltageSeries = FXCollections.observableArrayList();
		ObservableList<XYChart.Series> displayedPhaseSeries = FXCollections.observableArrayList();
		ObservableList<XYChart.Series> displayedActiveSeries = FXCollections.observableArrayList();
		ObservableList<XYChart.Series> displayedReactiveSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Float> valuesV = new XYChart.Series<>();
		valuesV.setName("V");
		XYChart.Series<String, Float> valuesA = new XYChart.Series<>();
		valuesA.setName("A");
		XYChart.Series<String, Float> valuesP = new XYChart.Series<>();
		valuesP.setName("P");
		XYChart.Series<String, Float> valuesQ = new XYChart.Series<>();
		valuesQ.setName("Q");

		for (BusData bus : results.getAllBusesValues()) {

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

		ObservableList<XYChart.Series> displayedDsSeries = FXCollections.observableArrayList();

		for (String key : results.getDsValues().keySet()) {

			if (!key.endsWith(".V"))
				continue;

			XYChart.Series<Double, Double> valuesDS = new XYChart.Series<>();
			valuesDS.setName(key);

			for (DsData xyValue : results.getDsValues().get(key)) {
				valuesDS.getData().add(new XYChart.Data<>(xyValue.getX(), xyValue.getY()));
			}
			displayedDsSeries.add(valuesDS);
		}

		dsChart.getData().addAll(displayedDsSeries);
		highlightSeriesOnHover(displayedDsSeries);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		Workflow w = mainApp.getWorkflow();

		createdLabel.setText("" + w.getId());
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {
			if (td.getTaskClass().equals(LoadFlowTask.class))
				loadflowLabel.setText(Utils.getLoadflowEngine(td.getTaskId()).name());

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
				dsLabel.setText(Utils.getDsEngine(td.getTaskId()).name());
		}
		statusLabel.setText(w.getState().name());
		if (w.getState().equals(ProcessState.SUCCESS)) {
			addSeries(mainApp.getWorkflowResult("" + w.getId()));
			Utils.addTooltipScatterChart(voltageChart, false);
			Utils.addTooltipScatterChart(phaseChart, false);
			Utils.addTooltipScatterChart(activeChart, false);
			Utils.addTooltipScatterChart(reactiveChart, false);
			Utils.addTooltipLineChart(dsChart);
		}

		if (Files.exists(Utils.DATA_TMP.resolve("eventAdder_initial.mo"), LinkOption.NOFOLLOW_LINKS)) {
			modelicaFileButton.setDisable(false);
		}

		if (Files.exists(Utils.DATA_TMP.resolve("eventAdder_events.mo"), LinkOption.NOFOLLOW_LINKS)) {
			modelicaEventsFileButton.setDisable(false);
		}
	}

	@FXML
	private TitledPane fileContentPane;
	@FXML
	private CodeEditor codeEditor;

	@FXML
	private Button modelicaFileButton;
	@FXML
	private Button modelicaEventsFileButton;

	@FXML
	private Label createdLabel;
	@FXML
	private Label statusLabel;
	@FXML
	private Label loadflowLabel;
	@FXML
	private Label dsLabel;

	@FXML
	private ScatterChart voltageChart;
	@FXML
	private CategoryAxis xVoltageAxis;
	@FXML
	private NumberAxis yVoltageAxis;

	@FXML
	private ScatterChart phaseChart;
	@FXML
	private CategoryAxis xPhaseAxis;
	@FXML
	private NumberAxis yPhaseAxis;

	@FXML
	private ScatterChart activeChart;
	@FXML
	private CategoryAxis xActiveAxis;
	@FXML
	private NumberAxis yActiveAxis;

	@FXML
	private ScatterChart reactiveChart;
	@FXML
	private CategoryAxis xReactiveAxis;
	@FXML
	private NumberAxis yReactiveAxis;

	@FXML
	private LineChart dsChart;
	@FXML
	private NumberAxis xDsAxis;
	@FXML
	private NumberAxis yDsAxis;

	private Map<String, Paint> colors = new HashMap<String, Paint>();
	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowDetailController.class);
}
