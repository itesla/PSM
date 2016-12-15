package org.power_systems_modelica.psm.gui.view;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.Utils;
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
		
		voltageChart.setLegendVisible(false);

		yVoltageAxis.setLowerBound(0);
		yVoltageAxis.setUpperBound(2.25);
		yVoltageAxis.setTickUnit(0.25);
		yVoltageAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25)) { 
			@Override public String toString(Number object) { 
				return String.format("%,.4f%%", object.floatValue()*100); 
			} 
		});

		phaseChart.setLegendVisible(false);

		yPhaseAxis.setLowerBound(0);
		yPhaseAxis.setUpperBound(2.25);
		yPhaseAxis.setTickUnit(0.25);
		yPhaseAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25)) { 
			@Override public String toString(Number object) { 
				return String.format("%,.4f%%", object.floatValue()*100); 
			} 
		});

		activeChart.setLegendVisible(false);

		yActiveAxis.setLowerBound(0);
		yActiveAxis.setUpperBound(2.25);
		yActiveAxis.setTickUnit(0.25);
		yActiveAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25)) { 
			@Override public String toString(Number object) { 
				return String.format("%,.4f%%", object.floatValue()*100); 
			} 
		});

		reactiveChart.setLegendVisible(false);

		yReactiveAxis.setLowerBound(0);
		yReactiveAxis.setUpperBound(2.25);
		yReactiveAxis.setTickUnit(0.25);
		yReactiveAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25)) { 
			@Override public String toString(Number object) { 
				return String.format("%,.4f%%", object.floatValue()*100); 
			} 
		});
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainService.showCompareLoadflowsView(null);
	}

	public void addSeries(WorkflowResult workflowResult) {

		ObservableList<XYChart.Series> displayedVoltageSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Float> voltageSeries = new XYChart.Series<>();

		int i = 1;
		for (BusData bus : workflowResult.getAllBusesValues()) {
			voltageSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("V")));
			i++;
		}

		voltageSeries.setName(workflowResult.getId());
		displayedVoltageSeries.add(voltageSeries);

		voltageChart.getData().addAll(displayedVoltageSeries);

		ObservableList<XYChart.Series> displayedPhaseSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Float> phaseSeries = new XYChart.Series<>();

		i = 1;
		for (BusData bus : workflowResult.getAllBusesValues()) {
			phaseSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("A")));
			i++;
		}

		phaseSeries.setName(workflowResult.getId());
		displayedPhaseSeries.add(phaseSeries);

		phaseChart.getData().addAll(displayedPhaseSeries);

		ObservableList<XYChart.Series> displayedActiveSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Float> activeSeries = new XYChart.Series<>();

		i = 1;
		for (BusData bus : workflowResult.getAllBusesValues()) {
			activeSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("P")));
			i++;
		}

		activeSeries.setName(workflowResult.getId());
		displayedActiveSeries.add(activeSeries);

		activeChart.getData().addAll(displayedActiveSeries);

		ObservableList<XYChart.Series> displayedReactiveSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Float> reactiveSeries = new XYChart.Series<>();

		i = 1;
		for (BusData bus : workflowResult.getAllBusesValues()) {
			reactiveSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("Q")));
			i++;
		}

		reactiveSeries.setName(workflowResult.getId());
		displayedReactiveSeries.add(reactiveSeries);

		reactiveChart.getData().addAll(displayedReactiveSeries);
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;

	}
	
	public void setWorkflow(Workflow w) {
		
		createdLabel.setText("" + w.getId());
		statusLabel.setText(w.getState().name());
		if (w.getState().equals(ProcessState.SUCCESS)) {
			WorkflowResult wr = mainService.getCompareLoadflowsResult("" + w.getId());
			
			DoubleSummaryStatistics voltageStats = wr.getAllBusesValues().stream().map(bus -> bus.getAbsError("V"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));
			
		    avgVoltageErrorLabel.setText(String.format("%,.4f%%", voltageStats.getAverage()*100));	
			maxVoltageErrorLabel.setText(String.format("%,.4f%%", voltageStats.getMax()*100));	

			DoubleSummaryStatistics phaseStats = wr.getAllBusesValues().stream().map(bus -> bus.getAbsError("A"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));
			
		    avgPhaseErrorLabel.setText(String.format("%,.4f%%", phaseStats.getAverage()*100));	
			maxPhaseErrorLabel.setText(String.format("%,.4f%%", phaseStats.getMax()*100));	

			DoubleSummaryStatistics activeStats = wr.getAllBusesValues().stream().map(bus -> bus.getAbsError("P"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));
			
		    avgActiveErrorLabel.setText(String.format("%,.4f%%", activeStats.getAverage()*100));	
			maxActiveErrorLabel.setText(String.format("%,.4f%%", activeStats.getMax()*100));	

			DoubleSummaryStatistics reactiveStats = wr.getAllBusesValues().stream().map(bus -> bus.getAbsError("Q"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));
			
		    avgReactiveErrorLabel.setText(String.format("%,.4f%%", reactiveStats.getAverage()*100));	
			maxReactiveErrorLabel.setText(String.format("%,.4f%%", reactiveStats.getMax()*100));	

			addSeries(wr);
			Utils.addTooltipComparisonChart(voltageChart, wr, "V", "pu");
			Utils.addTooltipComparisonChart(phaseChart, wr, "A", "º");
			Utils.addTooltipComparisonChart(activeChart, wr, "P", "MW");
			Utils.addTooltipComparisonChart(reactiveChart, wr, "Q", "MVar");
		}
	}

	@FXML
	private Label createdLabel;
	@FXML
	private Label statusLabel;

	@FXML
	private Label avgVoltageErrorLabel;
	@FXML
	private Label maxVoltageErrorLabel;
	@FXML
	private ScatterChart voltageChart;
	@FXML
	private CategoryAxis xVoltageAxis;
	@FXML
	private NumberAxis yVoltageAxis;

	@FXML
	private Label avgPhaseErrorLabel;
	@FXML
	private Label maxPhaseErrorLabel;
	@FXML
	private ScatterChart phaseChart;
	@FXML
	private CategoryAxis xPhaseAxis;
	@FXML
	private NumberAxis yPhaseAxis;

	@FXML
	private Label avgActiveErrorLabel;
	@FXML
	private Label maxActiveErrorLabel;
	@FXML
	private ScatterChart activeChart;
	@FXML
	private CategoryAxis xActiveAxis;
	@FXML
	private NumberAxis yActiveAxis;

	@FXML
	private Label avgReactiveErrorLabel;
	@FXML
	private Label maxReactiveErrorLabel;
	@FXML
	private ScatterChart reactiveChart;
	@FXML
	private CategoryAxis xReactiveAxis;
	@FXML
	private NumberAxis yReactiveAxis;

	private MainService mainService;

	private static final Logger LOG = LoggerFactory.getLogger(CompareLoadflowsDetailController.class);
}
