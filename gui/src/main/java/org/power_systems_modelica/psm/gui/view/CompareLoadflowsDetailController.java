package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.model.WorkflowResultItem;
import org.power_systems_modelica.psm.gui.service.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class CompareLoadflowsDetailController {

	@FXML
	private void initialize() {

		xAxis.setLowerBound(1);
		xAxis.setUpperBound(25);

		yAxis.setLowerBound(0);
		yAxis.setUpperBound(2.25);
		yAxis.setTickUnit(0.25);
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainApp.showCompareLoadflowsView(null);
	}

	private void addSeries(ObservableList<WorkflowResult> results) {

		ObservableList<XYChart.Series> displayedSeries = FXCollections.observableArrayList();
		for (WorkflowResult result : results) {

			XYChart.Series<Integer, Double> series = new XYChart.Series<>();

			for (WorkflowResultItem item : result.getResult()) {
				series.getData().add(new XYChart.Data<>(item.getX(), item.getY()));
			}

			series.setName(result.getId());
			displayedSeries.add(series);
		}

		scatterChart.getData().addAll(displayedSeries);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		Workflow w = mainApp.getCompareLoadflows();
		
		createdLabel.setText(w.getName());
		statusLabel.setText(w.getStatus().name());
		if (w.isSuccess())
			addSeries(mainApp.getCompareLoadflowsResult(w.getName()));
	}

	@FXML
	private Label createdLabel;

	@FXML
	private Label statusLabel;

	@FXML
	private ScatterChart scatterChart;
	@FXML
	private NumberAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(CompareLoadflowsDetailController.class);
}
