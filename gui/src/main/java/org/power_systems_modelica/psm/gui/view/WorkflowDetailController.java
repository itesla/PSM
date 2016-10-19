package org.power_systems_modelica.psm.gui.view;

import java.util.List;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.model.WorkflowResultItem;
import org.power_systems_modelica.psm.gui.service.Workflow;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public class WorkflowDetailController {

	@FXML
	private void initialize() {

		lineChart.setCreateSymbols(false);
		lineChart.setLegendVisible(false);

		xAxis.setLowerBound(1);
		xAxis.setUpperBound(25);

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
				Path sPath = (Path) seriesNode;
				if (seriesPath == null) {
					sPath.setStroke(Color.RED);
					sPath.setStrokeWidth(2);
					sPath.setOpacity(1);
				} else if (sPath == seriesPath) {
					sPath.setStroke(Color.RED.darker());
					sPath.setStrokeWidth(4);
					sPath.setOpacity(1);
				} else {
					sPath.setStroke(Color.GRAY);
					sPath.setStrokeWidth(1);
					sPath.setOpacity(0.5);
				}
			}
		}
	}

	private void addSeries(ObservableList<WorkflowResult> results) {

		ObservableList<XYChart.Series> displayedSeries = FXCollections.observableArrayList();
		for (WorkflowResult result : results) {

			XYChart.Series<Integer, Double> series = new XYChart.Series<>();

			for (WorkflowResultItem item : result.getResult()) {
				series.getData().add(new XYChart.Data<>(item.getX(), item.getY()));
			}

			displayedSeries.add(series);
		}

		lineChart.getData().addAll(displayedSeries);
		highlightSerie(displayedSeries, null);
		highlightSeriesOnHover(displayedSeries); 
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		Workflow w = mainApp.getWorkflow();
		
		createdLabel.setText(w.getName());
		statusLabel.setText(w.getStatus().name());
		if (w.isSuccess())
			addSeries(mainApp.getWorkflowResult(w.getName()));
	}

	@FXML
	private Label createdLabel;

	@FXML
	private Label statusLabel;

	@FXML
	private LineChart lineChart;
	@FXML
	private NumberAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowDetailController.class);
}
