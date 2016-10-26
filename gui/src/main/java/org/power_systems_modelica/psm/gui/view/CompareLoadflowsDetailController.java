package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class CompareLoadflowsDetailController {

	@FXML
	private void initialize() {
		
		scatterChart.setLegendVisible(false);

		yAxis.setLowerBound(0);
		yAxis.setUpperBound(2.25);
		yAxis.setTickUnit(0.25);
		NumberAxis axis = new NumberAxis(0, 2.25, 0.25); 
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(axis) { 
			@Override public String toString(Number object) { 
				return String.format("%,.2f%%", object.floatValue()*100); 
			} 
		});
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainApp.showCompareLoadflowsView(null);
	}

	private void addSeries(WorkflowResult workflowResult) {

		ObservableList<XYChart.Series> displayedSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Float> series = new XYChart.Series<>();

		int i = 1;
		for (BusData bus : workflowResult.getAllBusesValues()) {
			series.getData().add(new XYChart.Data<>(bus.getName(), bus.getError()));
			i++;
		}

		series.setName(workflowResult.getId());
		displayedSeries.add(series);

		scatterChart.getData().addAll(displayedSeries);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		Workflow w = mainApp.getCompareLoadflows();

		createdLabel.setText("" + w.getId());
		statusLabel.setText(w.getState().name());
		if (w.getState().equals(ProcessState.SUCCESS)) {
			WorkflowResult wr = mainApp.getCompareLoadflowsResult("" + w.getId());
					
		    avgErrorLabel.setText(String.format("%,.2f%%", wr.getStats().getAverage()*100));	
			maxErrorLabel.setText(String.format("%,.2f%%", wr.getStats().getMax()*100));	
			addSeries(wr);
		}
	}

	@FXML
	private Label createdLabel;
	@FXML
	private Label statusLabel;
	@FXML
	private Label avgErrorLabel;
	@FXML
	private Label maxErrorLabel;

	@FXML
	private ScatterChart scatterChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(CompareLoadflowsDetailController.class);
}
