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
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class CompareLoadflowsDetailController implements MainChildrenController
{

	@Override
	public void handleMainAction()
	{

		handleNewWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{

	}

	@Override
	public String getMainAction()
	{

		return "New comparison";
	}

	@Override
	public List<String> getMenuActions()
	{

		return null;
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{

		List<SummaryLabel> labels = new ArrayList();
		labels.add(new SummaryLabel("Case:", caseLabel, false, false));
		return labels;
	}

	@Override
	public ObservableValue<? extends Boolean> disableBackground()
	{
		return new SimpleBooleanProperty(false);
	}

	@Override
	public Button getDefaultEnterButton()
	{
		return null;
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

		for (BusData bus : workflowResult.getAllBusesValues())
		{
			voltageHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("V", 0)));
			voltageHades2Series.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("V", 1)));
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

		for (BusData bus : workflowResult.getAllBusesValues())
		{
			phaseHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("A", 0)));
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

		for (BusData bus : workflowResult.getAllBusesValues())
		{
			activeHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("P", 0)));
			activeHades2Series.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("P", 1)));
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

		for (BusData bus : workflowResult.getAllBusesValues())
		{
			reactiveHelmflowSeries.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("Q", 0)));
			reactiveHades2Series.getData()
					.add(new XYChart.Data<>(bus.getName(), bus.getData("Q", 1)));
		}

		reactiveHelmflowSeries.setName(workflowResult.getId());
		reactiveHades2Series.setName(workflowResult.getId());
		displayedReactiveSeries.add(reactiveHelmflowSeries);
		displayedReactiveSeries.add(reactiveHades2Series);

		reactiveCurvesChart.getData().addAll(displayedReactiveSeries);
	}

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
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
					Catalog catalog = mainService.getCatalog("cases", catalogPath);
					Case c = mainService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + "\t" + c.getName();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		WorkflowResult wr = mainService.getCompareLoadflowsResult("" + w.getId());
		if (w.getState().equals(ProcessState.SUCCESS))
		{
			voltageTab.setDisable(false);
			phaseTab.setDisable(false);
			activeTab.setDisable(false);
			reactiveTab.setDisable(false);

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

			voltageTable.setItems(wr.getAllBusesValues());
			phaseTable.setItems(wr.getAllBusesValues());
			activeTable.setItems(wr.getAllBusesValues());
			reactiveTable.setItems(wr.getAllBusesValues());
			addDiffSeries(wr);
			addCurvesSeries(wr);
			UtilsFX.addTooltipComparisonChart(voltageDiffChart, wr, "V", "pu");
			UtilsFX.addTooltipComparisonChart(phaseDiffChart, wr, "A", "º");
			UtilsFX.addTooltipComparisonChart(activeDiffChart, wr, "P", "MW");
			UtilsFX.addTooltipComparisonChart(reactiveDiffChart, wr, "Q", "MVar");
			UtilsFX.addTooltipScatterChart(voltageCurvesChart, "pu");
			UtilsFX.addTooltipScatterChart(phaseCurvesChart, "º");
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

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
	}

	@Override
	public void setDefaultInit()
	{
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

	private MainService						mainService;

	private static final Logger				LOG	= LoggerFactory
			.getLogger(CompareLoadflowsDetailController.class);

}
