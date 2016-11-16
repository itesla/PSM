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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public class WorkflowDetailController {

	@FXML
	private void initialize() {

		voltageChart.setLegendVisible(false);
		phaseChart.setLegendVisible(false);
		activeChart.setLegendVisible(false);
		reactiveChart.setLegendVisible(false);

		yVoltageAxis.setLowerBound(0);
		yVoltageAxis.setUpperBound(2.25);
		yVoltageAxis.setTickUnit(0.25);

		yPhaseAxis.setLowerBound(0);
		yPhaseAxis.setUpperBound(2.25);
		yPhaseAxis.setTickUnit(0.25);

		yActiveAxis.setLowerBound(0);
		yActiveAxis.setUpperBound(2.25);
		yActiveAxis.setTickUnit(0.25);

		yReactiveAxis.setLowerBound(0);
		yReactiveAxis.setUpperBound(2.25);
		yReactiveAxis.setTickUnit(0.25);
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainApp.showWorkflowView(null);
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
			
			valuesV.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("V",0)));
			valuesA.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("A",0)));
			valuesP.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("P",0)));
			valuesQ.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("Q",0)));
		}
		
		displayedVoltageSeries.add(valuesV);
		displayedPhaseSeries.add(valuesA);
		displayedActiveSeries.add(valuesP);
		displayedReactiveSeries.add(valuesQ);
		
		voltageChart.getData().addAll(displayedVoltageSeries);
		phaseChart.getData().addAll(displayedPhaseSeries);
		activeChart.getData().addAll(displayedActiveSeries);
		reactiveChart.getData().addAll(displayedReactiveSeries);
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

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowDetailController.class);
}
