package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
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

public class CompareLoadflowsDetailController implements MainChildrenController
{

	@Override
	public void handleMainAction() {

		handleNewWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{
		
	}

	@Override
	public String getMainAction() {

		return "New";
	}

	@Override
	public List<String> getMenuActions() {

		return null;
	}

	@Override
	public List<String> getSummaryLabels() {
		
		List<String> labels = new ArrayList();
		labels.add("Case:");
		labels.add(caseLabel);
		return labels;
	}

	@FXML
	private void initialize()
	{

		voltageDiffChart.setLegendVisible(false);
		voltageCurvesChart.setLegendVisible(false);

		yVoltageDiffAxis.setLowerBound(0);
		yVoltageDiffAxis.setUpperBound(2.25);
		yVoltageDiffAxis.setTickUnit(0.25);
		yVoltageDiffAxis.setTickLabelFormatter(
				new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25))
				{
					@Override
					public String toString(Number object)
					{
						if (Math.abs(object.floatValue() * 100) > 1000)
							return String.format("%,.0f%%", object.floatValue() * 100);
						
						return String.format("%,.4f%%", object.floatValue() * 100);
					}
				});

		phaseDiffChart.setLegendVisible(false);
		phaseCurvesChart.setLegendVisible(false);

		yPhaseDiffAxis.setLowerBound(0);
		yPhaseDiffAxis.setUpperBound(2.25);
		yPhaseDiffAxis.setTickUnit(0.25);
		yPhaseDiffAxis.setTickLabelFormatter(
				new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25))
				{
					@Override
					public String toString(Number object)
					{
						if (Math.abs(object.floatValue() * 100) > 1000)
							return String.format("%,.0f%%", object.floatValue() * 100);
						
						return String.format("%,.4f%%", object.floatValue() * 100);
					}
				});

		activeDiffChart.setLegendVisible(false);
		activeCurvesChart.setLegendVisible(false);

		yActiveDiffAxis.setLowerBound(0);
		yActiveDiffAxis.setUpperBound(2.25);
		yActiveDiffAxis.setTickUnit(0.25);
		yActiveDiffAxis.setTickLabelFormatter(
				new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25))
				{
					@Override
					public String toString(Number object)
					{
						if (Math.abs(object.floatValue() * 100) > 1000)
							return String.format("%,.0f%%", object.floatValue() * 100);
						
						return String.format("%,.4f%%", object.floatValue() * 100);
					}
				});

		reactiveDiffChart.setLegendVisible(false);
		reactiveCurvesChart.setLegendVisible(false);

		yReactiveDiffAxis.setLowerBound(0);
		yReactiveDiffAxis.setUpperBound(2.25);
		yReactiveDiffAxis.setTickUnit(0.25);
		yReactiveDiffAxis.setTickLabelFormatter(
				new NumberAxis.DefaultFormatter(new NumberAxis(0, 2.25, 0.25))
				{
					@Override
					public String toString(Number object)
					{
						if (Math.abs(object.floatValue() * 100) > 1000)
							return String.format("%,.0f%%", object.floatValue() * 100);
						
						return String.format("%,.4f%%", object.floatValue() * 100);
					}
				});
	}

	private void handleNewWorkflow()
	{
		LOG.debug("handleNewWorkflow");
		mainService.showCompareLoadflowsView(null);
	}

	public void addDiffSeries(WorkflowResult workflowResult)
	{
		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> voltageSeries = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues())
			voltageSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("V")));

		voltageSeries.setName(workflowResult.getId());
		displayedVoltageSeries.add(voltageSeries);

		voltageDiffChart.getData().addAll(displayedVoltageSeries);

		ObservableList<XYChart.Series<String, Number>> displayedPhaseSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> phaseSeries = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues())
			phaseSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("A")));

		phaseSeries.setName(workflowResult.getId());
		displayedPhaseSeries.add(phaseSeries);

		phaseDiffChart.getData().addAll(displayedPhaseSeries);

		ObservableList<XYChart.Series<String, Number>> displayedActiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> activeSeries = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues())
			activeSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("P")));

		activeSeries.setName(workflowResult.getId());
		displayedActiveSeries.add(activeSeries);

		activeDiffChart.getData().addAll(displayedActiveSeries);

		ObservableList<XYChart.Series<String, Number>> displayedReactiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> reactiveSeries = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues())
			reactiveSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("Q")));

		reactiveSeries.setName(workflowResult.getId());
		displayedReactiveSeries.add(reactiveSeries);

		reactiveDiffChart.getData().addAll(displayedReactiveSeries);
	}

	public void addCurvesSeries(WorkflowResult workflowResult)
	{
		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> voltageHelmflowSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> voltageHades2Series = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues()) {
			voltageHelmflowSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("V", 0)));
			voltageHades2Series.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("V", 1)));
		}

		voltageHelmflowSeries.setName(workflowResult.getId());
		voltageHades2Series.setName(workflowResult.getId());
		displayedVoltageSeries.add(voltageHelmflowSeries);
		displayedVoltageSeries.add(voltageHades2Series);

		voltageCurvesChart.getData().addAll(displayedVoltageSeries);

		ObservableList<XYChart.Series<String, Number>> displayedPhaseSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> phaseHelmflowSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> phaseHades2Series = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues()) {
			phaseHelmflowSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("A", 0)));
			phaseHades2Series.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("A", 1)));
		}

		phaseHelmflowSeries.setName(workflowResult.getId());
		phaseHades2Series.setName(workflowResult.getId());
		displayedPhaseSeries.add(phaseHelmflowSeries);
		displayedPhaseSeries.add(phaseHades2Series);

		phaseCurvesChart.getData().addAll(displayedPhaseSeries);

		ObservableList<XYChart.Series<String, Number>> displayedActiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> activeHelmflowSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> activeHades2Series = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues()) {
			activeHelmflowSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("P", 0)));
			activeHades2Series.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("P", 1)));
		}

		activeHelmflowSeries.setName(workflowResult.getId());
		activeHades2Series.setName(workflowResult.getId());
		displayedActiveSeries.add(activeHelmflowSeries);
		displayedActiveSeries.add(activeHades2Series);

		activeCurvesChart.getData().addAll(displayedActiveSeries);

		ObservableList<XYChart.Series<String, Number>> displayedReactiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> reactiveHelmflowSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> reactiveHades2Series = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues()) {
			reactiveHelmflowSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("Q", 0)));
			reactiveHades2Series.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("Q", 1)));
		}

		reactiveHelmflowSeries.setName(workflowResult.getId());
		reactiveHades2Series.setName(workflowResult.getId());
		displayedReactiveSeries.add(reactiveHelmflowSeries);
		displayedReactiveSeries.add(reactiveHades2Series);

		reactiveCurvesChart.getData().addAll(displayedReactiveSeries);
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

	}

	public void setWorkflow(Workflow w)
	{
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {
			if (td.getTaskClass().equals(StaticNetworkImporterTask.class)) {
				
				String uri = td.getTaskConfiguration().getParameter("source");
				
				Path casePath;
				if (uri.endsWith(".xml")) {
					Path path = Paths.get(uri);
					casePath = path.getParent();
				} else
					casePath = Paths.get(uri);
				
				Path catalogPath = casePath.getParent();
	
				try {
					Catalog catalog = mainService.getCatalog("cases", catalogPath);
					Case c = mainService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + "\t" + c.getName();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (w.getState().equals(ProcessState.SUCCESS))
		{
			WorkflowResult wr = mainService.getCompareLoadflowsResult("" + w.getId());

			DoubleSummaryStatistics voltageStats = wr.getAllBusesValues().stream()
					.map(bus -> bus.getAbsError("V"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));

			avgVoltageDiffLabel.setText(String.format("%,.4f%%", voltageStats.getAverage() * 100));
			maxVoltageDiffLabel.setText(String.format("%,.4f%%", voltageStats.getMax() * 100));

			DoubleSummaryStatistics phaseStats = wr.getAllBusesValues().stream()
					.map(bus -> bus.getAbsError("A"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));

			avgPhaseDiffLabel.setText(String.format("%,.4f%%", phaseStats.getAverage() * 100));
			maxPhaseDiffLabel.setText(String.format("%,.4f%%", phaseStats.getMax() * 100));

			DoubleSummaryStatistics activeStats = wr.getAllBusesValues().stream()
					.map(bus -> bus.getAbsError("P"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));

			avgActiveDiffLabel.setText(String.format("%,.4f%%", activeStats.getAverage() * 100));
			maxActiveDiffLabel.setText(String.format("%,.4f%%", activeStats.getMax() * 100));

			DoubleSummaryStatistics reactiveStats = wr.getAllBusesValues().stream()
					.map(bus -> bus.getAbsError("Q"))
					.collect(Collectors.summarizingDouble(Float::doubleValue));

			avgReactiveDiffLabel
					.setText(String.format("%,.4f%%", reactiveStats.getAverage() * 100));
			maxReactiveDiffLabel.setText(String.format("%,.4f%%", reactiveStats.getMax() * 100));

			addDiffSeries(wr);
			addCurvesSeries(wr);
			Utils.addTooltipComparisonChart(voltageDiffChart, wr, "V", "pu");
			Utils.addTooltipComparisonChart(phaseDiffChart, wr, "A", "º");
			Utils.addTooltipComparisonChart(activeDiffChart, wr, "P", "MW");
			Utils.addTooltipComparisonChart(reactiveDiffChart, wr, "Q", "MVar");
			Utils.addTooltipScatterChart(voltageCurvesChart, "pu");
			Utils.addTooltipScatterChart(phaseCurvesChart, "º");
			Utils.addTooltipScatterChart(activeCurvesChart, "MW");
			Utils.addTooltipScatterChart(reactiveCurvesChart, "MVar");
		}
	}

	@FXML
	private Label							avgVoltageDiffLabel;
	@FXML
	private Label							maxVoltageDiffLabel;
	@FXML
	private ScatterChart<String, Number>	voltageDiffChart;
	@FXML
	private CategoryAxis					xVoltageDiffAxis;
	@FXML
	private NumberAxis						yVoltageDiffAxis;

	@FXML
	private Label							avgPhaseDiffLabel;
	@FXML
	private Label							maxPhaseDiffLabel;
	@FXML
	private ScatterChart<String, Number>	phaseDiffChart;
	@FXML
	private CategoryAxis					xPhaseDiffAxis;
	@FXML
	private NumberAxis						yPhaseDiffAxis;

	@FXML
	private Label							avgActiveDiffLabel;
	@FXML
	private Label							maxActiveDiffLabel;
	@FXML
	private ScatterChart<String, Number>	activeDiffChart;
	@FXML
	private CategoryAxis					xActiveDiffDiffAxis;
	@FXML
	private NumberAxis						yActiveDiffAxis;

	@FXML
	private Label							avgReactiveDiffLabel;
	@FXML
	private Label							maxReactiveDiffLabel;
	@FXML
	private ScatterChart<String, Number>	reactiveDiffChart;
	@FXML
	private CategoryAxis					xReactiveDiffAxis;
	@FXML
	private NumberAxis						yReactiveDiffAxis;

	@FXML
	private ScatterChart<String, Number>	voltageCurvesChart;
	@FXML
	private CategoryAxis					xVoltageCurvesAxis;
	@FXML
	private NumberAxis						yVoltageCurvesAxis;

	@FXML
	private ScatterChart<String, Number>	phaseCurvesChart;
	@FXML
	private CategoryAxis					xPhaseCurvesAxis;
	@FXML
	private NumberAxis						yPhaseCurvesAxis;

	@FXML
	private ScatterChart<String, Number>	activeCurvesChart;
	@FXML
	private CategoryAxis					xActiveCurvesCurvesAxis;
	@FXML
	private NumberAxis						yActiveCurvesAxis;

	@FXML
	private ScatterChart<String, Number>	reactiveCurvesChart;
	@FXML
	private CategoryAxis					xReactiveCurvesAxis;
	@FXML
	private NumberAxis						yReactiveCurvesAxis;

	private String							caseLabel;

	private MainService						mainService;

	private static final Logger				LOG	= LoggerFactory
			.getLogger(CompareLoadflowsDetailController.class);
}
