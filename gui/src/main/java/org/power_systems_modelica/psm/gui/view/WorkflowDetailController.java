package org.power_systems_modelica.psm.gui.view;

import java.util.List;
import java.util.Set;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public class WorkflowDetailController {

	@FXML
	private void initialize() {

		//lineChart.setCreateSymbols(false);
		//lineChart.setLegendVisible(false);

		yAxis.setLowerBound(0);
		yAxis.setUpperBound(2.25);
		yAxis.setTickUnit(0.25);
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainApp.showWorkflowView(null);
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
				Color color = Color.RED;
				String dColor = ".default-color0";
				if (series.getName().equals("V")) {
					color = Color.RED;
					dColor = ".default-color0";
				}
				else if (series.getName().equals("A")) {
					color = Color.GREEN;
					dColor = ".default-color1";
				}
				else if (series.getName().equals("P")) {
					color = Color.YELLOW;
					dColor = ".default-color2";
				}
				else if (series.getName().equals("Q")) {
					color = Color.BLUE;
					dColor = ".default-color3";
				}
				int strokeWidth = 2;
				double opacity = 1;
				Path sPath = (Path) seriesNode;
				if (seriesPath != null) {
					if (sPath == seriesPath) {
						color = color.darker();
						strokeWidth = 4;
					} else {
						color = Color.GRAY;
						strokeWidth = 1;
						opacity = 0.5;
					}
				}
				
				Set<Node> nodes = lineChart.lookupAll(dColor + ".chart-line-symbol");
				for (final Node node : nodes) {
					String colorName = color.toString();
					node.setStyle("-fx-background-color: #" + colorName.substring(2,colorName.length()-2) + ";");
				}
				sPath.setStroke(color);
				sPath.setStrokeWidth(strokeWidth);
				sPath.setOpacity(opacity);
			}
		}
	}

	private void addSeries(WorkflowResult results) {

		ObservableList<XYChart.Series> displayedSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Float> valuesV = new XYChart.Series<>();
		valuesV.setName("V");
		XYChart.Series<String, Float> valuesA = new XYChart.Series<>();
		valuesA.setName("A");
		XYChart.Series<String, Float> valuesP = new XYChart.Series<>();
		valuesP.setName("P");
		XYChart.Series<String, Float> valuesQ = new XYChart.Series<>();
		valuesQ.setName("Q");

		for (BusData bus : results.getAllBusesValues()) {
			
			valuesV.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("V",0)));
			valuesA.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("A",0)));
			valuesP.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("P",0)));
			valuesQ.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("Q",0)));
		}
		
		displayedSeries.add(valuesV);
		displayedSeries.add(valuesA);
		displayedSeries.add(valuesP);
		displayedSeries.add(valuesQ);
		
		lineChart.getData().addAll(displayedSeries);
		highlightSerie(displayedSeries, null);
		highlightSeriesOnHover(displayedSeries); 
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		Workflow w = mainApp.getWorkflow();
		
		createdLabel.setText("" + w.getId());
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {
			if (!td.getTaskClass().equals(LoadFlowTask.class))
				continue;
			
			loadflowLabel.setText(td.getTaskId().equals("loadflowHades2")?LoadflowEngine.HADES2.name():LoadflowEngine.HELMFLOW.name());
		}
		statusLabel.setText(w.getState().name());
		if (w.getState().equals(ProcessState.SUCCESS)) {
			addSeries(mainApp.getWorkflowResult("" + w.getId()));
		}
	}

	@FXML
	private Label createdLabel;
	@FXML
	private Label statusLabel;
	@FXML
	private Label loadflowLabel;

	@FXML
	private LineChart lineChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowDetailController.class);
}
