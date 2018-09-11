package org.power_systems_modelica.psm.gui.view;

/*
 * #%L
 * Power Systems on Modelica GUI
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class CompareLoadflowsDetailController extends MainChildrenController
{

	@Override
	public void handleMainAction()
	{
		handleNewWorkflow();
	}

	@Override
	public String getMainAction()
	{
		return "New loadflow";
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{

		List<SummaryLabel> labels = new ArrayList<SummaryLabel>();
		labels.add(new SummaryLabel("Case:", caseLabel, false, false));
		if (!loadflowLabel.equals("Comparision"))
			labels.add(new SummaryLabel("Loadflow:", loadflowLabel, false, false));
		return labels;
	}

	public void addDiffSeries(WorkflowResult workflowResult)
	{
		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> voltageSeries = new XYChart.Series<>();

		ObservableList<XYChart.Series<String, Number>> displayedPhaseSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> phaseSeries = new XYChart.Series<>();

		ObservableList<XYChart.Series<String, Number>> displayedActiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> activeSeries = new XYChart.Series<>();

		ObservableList<XYChart.Series<String, Number>> displayedReactiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> reactiveSeries = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues())
		{
			if (bus.getData("V", 0) == null ||
					bus.getData("A", 0) == null ||
					bus.getData("P", 0) == null ||
					bus.getData("Q", 0) == null)
				continue;

			if (bus.getData("V", 1) == null ||
					bus.getData("A", 1) == null ||
					bus.getData("P", 1) == null ||
					bus.getData("Q", 1) == null)
				continue;

			voltageSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("V")));
			phaseSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("A")));
			activeSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("P")));
			reactiveSeries.getData().add(new XYChart.Data<>(bus.getName(), bus.getError("Q")));
		}

		voltageSeries.setName(workflowResult.getId());
		displayedVoltageSeries.add(voltageSeries);

		voltageDiffChart.getData().addAll(displayedVoltageSeries);

		phaseSeries.setName(workflowResult.getId());
		displayedPhaseSeries.add(phaseSeries);

		phaseDiffChart.getData().addAll(displayedPhaseSeries);

		activeSeries.setName(workflowResult.getId());
		displayedActiveSeries.add(activeSeries);

		activeDiffChart.getData().addAll(displayedActiveSeries);

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

		ObservableList<XYChart.Series<String, Number>> displayedPhaseSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> phaseHelmflowSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> phaseHades2Series = new XYChart.Series<>();

		ObservableList<XYChart.Series<String, Number>> displayedActiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> activeHelmflowSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> activeHades2Series = new XYChart.Series<>();

		ObservableList<XYChart.Series<String, Number>> displayedReactiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> reactiveHelmflowSeries = new XYChart.Series<>();
		XYChart.Series<String, Number> reactiveHades2Series = new XYChart.Series<>();

		for (BusData bus : workflowResult.getAllBusesValues())
		{
			if (bus.getData("V", 0) == null ||
					bus.getData("A", 0) == null ||
					bus.getData("P", 0) == null ||
					bus.getData("Q", 0) == null)
				continue;

			if (bus.getData("V", 1) == null ||
					bus.getData("A", 1) == null ||
					bus.getData("P", 1) == null ||
					bus.getData("Q", 1) == null)
				continue;

			voltageHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("V", 0)));
			voltageHades2Series.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("V", 1)));

			phaseHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("A", 0)));
			phaseHades2Series.getData().add(new XYChart.Data<>(bus.getName(), bus.getData("A", 1)));

			activeHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("P", 0)));
			activeHades2Series.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("P", 1)));

			reactiveHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("Q", 0)));
			reactiveHades2Series.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("Q", 1)));
		}

		voltageHelmflowSeries.setName("HELM-Flow");
		voltageHades2Series.setName("Hades2");
		if (!le.equals(LoadflowEngine.HADES2))
			displayedVoltageSeries.add(voltageHelmflowSeries);
		if (!le.equals(LoadflowEngine.HELMFLOW))
			displayedVoltageSeries.add(voltageHades2Series);

		voltageCurvesChart.getData().addAll(displayedVoltageSeries);

		phaseHelmflowSeries.setName("HELM-Flow");
		phaseHades2Series.setName("Hades2");
		if (!le.equals(LoadflowEngine.HADES2))
			displayedPhaseSeries.add(phaseHelmflowSeries);
		if (!le.equals(LoadflowEngine.HELMFLOW))
			displayedPhaseSeries.add(phaseHades2Series);

		phaseCurvesChart.getData().addAll(displayedPhaseSeries);

		activeHelmflowSeries.setName("HELM-Flow");
		activeHades2Series.setName("Hades2");
		if (!le.equals(LoadflowEngine.HADES2))
			displayedActiveSeries.add(activeHelmflowSeries);
		if (!le.equals(LoadflowEngine.HELMFLOW))
			displayedActiveSeries.add(activeHades2Series);

		activeCurvesChart.getData().addAll(displayedActiveSeries);

		reactiveHelmflowSeries.setName("HELM-Flow");
		reactiveHades2Series.setName("Hades2");
		if (!le.equals(LoadflowEngine.HADES2))
			displayedReactiveSeries.add(reactiveHelmflowSeries);
		if (!le.equals(LoadflowEngine.HELMFLOW))
			displayedReactiveSeries.add(reactiveHades2Series);

		reactiveCurvesChart.getData().addAll(displayedReactiveSeries);
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		loadflowLabel = null;

		voltageTab.setDisable(true);
		phaseTab.setDisable(true);
		activeTab.setDisable(true);
		reactiveTab.setDisable(true);
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(StaticNetworkImporterTask.class))
			{

				String uri = td.getTaskConfiguration().getParameter("source");

				Path casePath;
				if (uri.endsWith(".xml"))
				{
					Path path = Paths.get(uri);
					casePath = path.getParent();
				}
				else
					casePath = Paths.get(uri);

				Path catalogPath = casePath.getParent();

				try
				{
					Catalog catalog = CatalogService.getCatalog("cases", catalogPath);
					Case c = CaseService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + " - " + c.getName();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			if (td.getTaskClass().equals(LoadFlowTask.class))
			{
				if (loadflowLabel == null)
				{
					le = Utils.getLoadflowEngine(td.getTaskId());
					loadflowLabel = le.toString();
				}
				else
				{
					le = LoadflowEngine.COMPARISON;
					loadflowLabel = "Comparison";
				}
			}
		}

		WorkflowResult wr = WorkflowServiceConfiguration.getCompareLoadflowsResult("" + w.getId(),
				loadflowLabel.equals("Comparison"));
		if (w.getState().equals(ProcessState.SUCCESS))
		{
			voltageTab.setDisable(false);
			phaseTab.setDisable(false);
			activeTab.setDisable(false);
			reactiveTab.setDisable(false);
			if (loadflowLabel.equals("Comparison"))
			{
				loadflowPane.setText("Loadflows comparison");
				voltageCurvesChart.setLegendVisible(true);
				phaseCurvesChart.setLegendVisible(true);
				activeCurvesChart.setLegendVisible(true);
				reactiveCurvesChart.setLegendVisible(true);

				DoubleSummaryStatistics voltageStats = wr.getAllBusesValues().stream()
						.map(bus -> bus.getAbsError("V")).filter(value -> !Float.isNaN(value))
						.collect(Collectors.summarizingDouble(Float::doubleValue));

				DecimalFormat df = new DecimalFormat("0.0###");
				if (voltageStats.getAverage() < 0.0001)
					df = new DecimalFormat("0.0###E0");

				avgVoltageDiffLabel.setText(df.format(voltageStats.getAverage()));
				maxVoltageDiffLabel.setText(df.format(voltageStats.getMax()));

				DoubleSummaryStatistics phaseStats = wr.getAllBusesValues().stream()
						.map(bus -> bus.getAbsError("A")).filter(value -> !Float.isNaN(value))
						.collect(Collectors.summarizingDouble(Float::doubleValue));

				df = new DecimalFormat("0.0###");
				if (phaseStats.getAverage() < 0.0001)
					df = new DecimalFormat("0.0###E0");

				avgPhaseDiffLabel.setText(df.format(phaseStats.getAverage()));
				maxPhaseDiffLabel.setText(df.format(phaseStats.getMax()));

				DoubleSummaryStatistics activeStats = wr.getAllBusesValues().stream()
						.map(bus -> bus.getAbsError("P")).filter(value -> !Float.isNaN(value))
						.collect(Collectors.summarizingDouble(Float::doubleValue));

				df = new DecimalFormat("0.0###");
				if (activeStats.getAverage() < 0.0001)
					df = new DecimalFormat("0.0###E0");

				avgActiveDiffLabel.setText(df.format(activeStats.getAverage()));
				maxActiveDiffLabel.setText(df.format(activeStats.getMax()));

				DoubleSummaryStatistics reactiveStats = wr.getAllBusesValues().stream()
						.map(bus -> bus.getAbsError("Q")).filter(value -> !Float.isNaN(value))
						.collect(Collectors.summarizingDouble(Float::doubleValue));

				df = new DecimalFormat("0.0###");
				if (reactiveStats.getAverage() < 0.0001)
					df = new DecimalFormat("0.0###E0");

				avgReactiveDiffLabel
						.setText(df.format(reactiveStats.getAverage()));
				maxReactiveDiffLabel.setText(df.format(reactiveStats.getMax()));

				addDiffSeries(wr);
			}
			else
			{
				loadflowPane.setText("Loadflow results");
				avgVoltageDiffTitle.setVisible(false);
				maxVoltageDiffTitle.setVisible(false);
				avgPhaseDiffTitle.setVisible(false);
				maxPhaseDiffTitle.setVisible(false);
				avgActiveDiffTitle.setVisible(false);
				maxActiveDiffTitle.setVisible(false);
				avgReactiveDiffTitle.setVisible(false);
				maxReactiveDiffTitle.setVisible(false);

				avgVoltageDiffLabel.setVisible(false);
				maxVoltageDiffLabel.setVisible(false);
				avgPhaseDiffLabel.setVisible(false);
				maxPhaseDiffLabel.setVisible(false);
				avgActiveDiffLabel.setVisible(false);
				maxActiveDiffLabel.setVisible(false);
				avgReactiveDiffLabel.setVisible(false);
				maxReactiveDiffLabel.setVisible(false);

				voltageDiffChart.setVisible(false);
				phaseDiffChart.setVisible(false);
				activeDiffChart.setVisible(false);
				reactiveDiffChart.setVisible(false);

				voltageCurvesChart.setLayoutY(14.0);
				phaseCurvesChart.setLayoutY(14.0);
				activeCurvesChart.setLayoutY(14.0);
				reactiveCurvesChart.setLayoutY(14.0);

				voltageTable.setLayoutY(414.0);
				phaseTable.setLayoutY(414.0);
				activeTable.setLayoutY(414.0);
				reactiveTable.setLayoutY(414.0);
			}

			voltageTable.setItems(wr.getAllBusesValues());
			phaseTable.setItems(wr.getAllBusesValues());
			activeTable.setItems(wr.getAllBusesValues());
			reactiveTable.setItems(wr.getAllBusesValues());
			addCurvesSeries(wr);

			boolean helmflowColumnVisible = false;
			boolean hadesColumnVisible = false;
			if (le.equals(LoadflowEngine.HADES2))
			{
				hadesColumnVisible = true;
			}
			else if (le.equals(LoadflowEngine.HELMFLOW))
			{
				helmflowColumnVisible = true;
			}
			else
			{
				hadesColumnVisible = true;
				helmflowColumnVisible = true;
			}
			hadesVoltageColumn.setVisible(hadesColumnVisible);
			hadesPhaseColumn.setVisible(hadesColumnVisible);
			hadesActiveColumn.setVisible(hadesColumnVisible);
			hadesReactiveColumn.setVisible(hadesColumnVisible);

			helmflowVoltageColumn.setVisible(helmflowColumnVisible);
			helmflowPhaseColumn.setVisible(helmflowColumnVisible);
			helmflowActiveColumn.setVisible(helmflowColumnVisible);
			helmflowReactiveColumn.setVisible(helmflowColumnVisible);

			differenceVoltageColumn.setVisible(hadesColumnVisible && helmflowColumnVisible);
			differencePhaseColumn.setVisible(hadesColumnVisible && helmflowColumnVisible);
			differenceActiveColumn.setVisible(hadesColumnVisible && helmflowColumnVisible);
			differenceReactiveColumn.setVisible(hadesColumnVisible && helmflowColumnVisible);

			yVoltageDiffAxis.setLabel("pu");
			yPhaseDiffAxis.setLabel("deg");
			yActiveDiffAxis.setLabel("MW");
			yReactiveDiffAxis.setLabel("MVar");
			yVoltageCurvesAxis.setLabel("pu");
			yPhaseCurvesAxis.setLabel("deg");
			yActiveCurvesAxis.setLabel("MW");
			yReactiveCurvesAxis.setLabel("MVar");

			UtilsFX.addTooltipComparisonChart(voltageDiffChart, wr, "V", "pu");
			UtilsFX.addTooltipComparisonChart(phaseDiffChart, wr, "A", "deg");
			UtilsFX.addTooltipComparisonChart(activeDiffChart, wr, "P", "MW");
			UtilsFX.addTooltipComparisonChart(reactiveDiffChart, wr, "Q", "MVar");
			UtilsFX.addTooltipScatterChart(voltageCurvesChart, "pu");
			UtilsFX.addTooltipScatterChart(phaseCurvesChart, "deg");
			UtilsFX.addTooltipScatterChart(activeCurvesChart, "MW");
			UtilsFX.addTooltipScatterChart(reactiveCurvesChart, "MVar");
		}
		else
		{
			tabPane.getSelectionModel().select(logTab);
		}

		StringBuilder sb = new StringBuilder();
		for (Exception e : wr.getExceptions())
		{
			sb.append(Utils.getStackTrace(e));
			sb.append("\n\n");
		}

		logArea.setText(sb.toString());
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
						DecimalFormat df = new DecimalFormat("0.0###");
						if (object.floatValue() < 0.0001 && object.floatValue() != 0.0)
							df = new DecimalFormat("0.0###E0");

						return df.format(object.floatValue());
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
						DecimalFormat df = new DecimalFormat("0.0###");
						if (object.floatValue() < 0.0001 && object.floatValue() != 0.0)
							df = new DecimalFormat("0.0###E0");

						return df.format(object.floatValue());
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
						DecimalFormat df = new DecimalFormat("0.0###");
						if (object.floatValue() < 0.0001 && object.floatValue() != 0.0)
							df = new DecimalFormat("0.0###E0");

						return df.format(object.floatValue());
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
						DecimalFormat df = new DecimalFormat("0.0###");
						if (object.floatValue() < 0.0001 && object.floatValue() != 0.0)
							df = new DecimalFormat("0.0###E0");

						return df.format(object.floatValue());
					}
				});

		elementVoltageColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		hadesVoltageColumn
				.setCellValueFactory(cellData -> cellData.getValue().dataProperty("V", 1));
		helmflowVoltageColumn
				.setCellValueFactory(cellData -> cellData.getValue().dataProperty("V", 0));
		differenceVoltageColumn
				.setCellValueFactory(cellData -> cellData.getValue().errorProperty("V"));

		elementPhaseColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		hadesPhaseColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty("A", 1));
		helmflowPhaseColumn
				.setCellValueFactory(cellData -> cellData.getValue().dataProperty("A", 0));
		differencePhaseColumn
				.setCellValueFactory(cellData -> cellData.getValue().errorProperty("A"));

		elementActiveColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		hadesActiveColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty("P", 1));
		helmflowActiveColumn
				.setCellValueFactory(cellData -> cellData.getValue().dataProperty("P", 0));
		differenceActiveColumn
				.setCellValueFactory(cellData -> cellData.getValue().errorProperty("P"));

		elementReactiveColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		hadesReactiveColumn
				.setCellValueFactory(cellData -> cellData.getValue().dataProperty("Q", 1));
		helmflowReactiveColumn
				.setCellValueFactory(cellData -> cellData.getValue().dataProperty("Q", 0));
		differenceReactiveColumn
				.setCellValueFactory(cellData -> cellData.getValue().errorProperty("Q"));
	}

	private void handleNewWorkflow()
	{
		LOG.debug("handleNewWorkflow");
		mainService.showCompareLoadflowsView(null);
	}

	@FXML
	private TitledPane						loadflowPane;

	@FXML
	private TabPane							tabPane;
	@FXML
	private Tab								voltageTab;
	@FXML
	private Tab								phaseTab;
	@FXML
	private Tab								activeTab;
	@FXML
	private Tab								reactiveTab;
	@FXML
	private Tab								logTab;

	@FXML
	private Label							avgVoltageDiffTitle;
	@FXML
	private Label							maxVoltageDiffTitle;
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
	private Label							avgPhaseDiffTitle;
	@FXML
	private Label							maxPhaseDiffTitle;
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
	private Label							avgActiveDiffTitle;
	@FXML
	private Label							maxActiveDiffTitle;
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
	private Label							avgReactiveDiffTitle;
	@FXML
	private Label							maxReactiveDiffTitle;
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

	@FXML
	private TableView<BusData>				voltageTable;
	@FXML
	private TableColumn<BusData, String>	elementVoltageColumn;
	@FXML
	private TableColumn<BusData, Number>	hadesVoltageColumn;
	@FXML
	private TableColumn<BusData, Number>	helmflowVoltageColumn;
	@FXML
	private TableColumn<BusData, Number>	differenceVoltageColumn;

	@FXML
	private TableView<BusData>				phaseTable;
	@FXML
	private TableColumn<BusData, String>	elementPhaseColumn;
	@FXML
	private TableColumn<BusData, Number>	hadesPhaseColumn;
	@FXML
	private TableColumn<BusData, Number>	helmflowPhaseColumn;
	@FXML
	private TableColumn<BusData, Number>	differencePhaseColumn;

	@FXML
	private TableView<BusData>				activeTable;
	@FXML
	private TableColumn<BusData, String>	elementActiveColumn;
	@FXML
	private TableColumn<BusData, Number>	hadesActiveColumn;
	@FXML
	private TableColumn<BusData, Number>	helmflowActiveColumn;
	@FXML
	private TableColumn<BusData, Number>	differenceActiveColumn;

	@FXML
	private TableView<BusData>				reactiveTable;
	@FXML
	private TableColumn<BusData, String>	elementReactiveColumn;
	@FXML
	private TableColumn<BusData, Number>	hadesReactiveColumn;
	@FXML
	private TableColumn<BusData, Number>	helmflowReactiveColumn;
	@FXML
	private TableColumn<BusData, Number>	differenceReactiveColumn;

	@FXML
	private TextArea						logArea;

	private String							caseLabel;
	private LoadflowEngine					le;
	private String							loadflowLabel;

	private static final Logger				LOG	= LoggerFactory
			.getLogger(CompareLoadflowsDetailController.class);

}
