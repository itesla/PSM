package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class ConversionDetailController implements MainChildrenController {

	@Override
	public void handleMainAction() {

		handleNewConversionEvent();
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
		labels.add("Path:");
		labels.add(pathLabel);
		labels.add("Created:");
		labels.add(date.toString("yyyy/MM/dd HH:mm:ss"));
		labels.add("Case:");
		labels.add(caseLabel);
		labels.add("Ddr:");
		labels.add(ddrLabel);
		return labels;
	}

	@FXML
	private void initialize() {

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

	private void handleNewConversionEvent() {
		mainService.showConversionNewView(mainService.getConversion());
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
		
	}

	public void addSeries(WorkflowResult results)
	{
		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = FXCollections
			.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedPhaseSeries = FXCollections
				.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedActiveSeries = FXCollections
				.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedReactiveSeries = FXCollections
				.observableArrayList();
		XYChart.Series<String, Number> valuesV = new XYChart.Series<>();
		valuesV.setName("V");
		XYChart.Series<String, Number> valuesA = new XYChart.Series<>();
		valuesA.setName("A");
		XYChart.Series<String, Number> valuesP = new XYChart.Series<>();
		valuesP.setName("P");
		XYChart.Series<String, Number> valuesQ = new XYChart.Series<>();
		valuesQ.setName("Q");

		for (BusData bus : results.getAllBusesValues())
		{
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
	}

	public void setWorkflow(Workflow w) {
		
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions()) {

			if (td.getTaskClass().equals(ModelicaExporterTask.class)) {
				
				String uri = td.getTaskConfiguration().getParameter("target");
				Path moFile = Paths.get(uri);
				pathLabel = uri;
				
				StringBuilder moContent = new StringBuilder();
				try {
					BasicFileAttributes attr = Files.readAttributes(Paths.get(pathLabel), BasicFileAttributes.class);
					date = new DateTime(attr.lastModifiedTime().toMillis());

					moContent = PathUtils.loadFile(moFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				codeEditor.setCode(moContent);
			}
			
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

			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class)) {
				
				String uri = td.getTaskConfiguration().getParameter("ddrLocation");
				Path ddrPath = Paths.get(uri).normalize();
				Path catalogPath = ddrPath.getParent().getParent();
				
				try {
					Catalog catalog = mainService.getCatalog("ddrs", catalogPath);
					Ddr ddr = mainService.getDdr(catalog.getName(), ddrPath);
					ddrLabel = catalog.getName() + "\t" + ddr.getName();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (w.getState().equals(ProcessState.SUCCESS)) {
			addSeries(mainService.getConversionResult("" + w.getId()));
			Utils.addTooltipScatterChart(voltageChart, "pu");
			Utils.addTooltipScatterChart(phaseChart, "º");
			Utils.addTooltipScatterChart(activeChart, "MW");
			Utils.addTooltipScatterChart(reactiveChart, "MVar");
		}
	}
	
	public void setConversionResult(Case c) {
		
		Path casePath = Paths.get(c.getLocation());
		Path catalogPath = casePath.getParent();
		Path convertedPath = casePath.resolve(c.getName() + ".mo");

		Catalog catalog;
		try {
			catalog = mainService.getCatalog("cases", catalogPath);
			ConvertedCase cc = mainService.getConvertedCase(catalog.getName(), casePath);
			
			Path ddrPath = Paths.get(cc.getDdrLocation());
			Ddr ddr = mainService.getDdr(catalog.getName(), ddrPath);
			
			pathLabel = convertedPath.toString();
			try {
				BasicFileAttributes attr = Files.readAttributes(Paths.get(pathLabel), BasicFileAttributes.class);
				date = new DateTime(attr.lastModifiedTime().toMillis());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			caseLabel = catalog.getName() + "\t" + c.getName();
			ddrLabel = catalog.getName() + "\t" + ddr.getName();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringBuilder moContent = new StringBuilder();
		try {
			moContent = PathUtils.loadFile(convertedPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codeEditor.setCode(moContent);
	}
	

	@FXML
	private CodeEditor codeEditor;

	@FXML
	private ScatterChart<String, Number>	voltageChart;
	@FXML
	private CategoryAxis					xVoltageAxis;
	@FXML
	private NumberAxis						yVoltageAxis;

	@FXML
	private ScatterChart<String, Number>	phaseChart;
	@FXML
	private CategoryAxis					xPhaseAxis;
	@FXML
	private NumberAxis						yPhaseAxis;

	@FXML
	private ScatterChart<String, Number>	activeChart;
	@FXML
	private CategoryAxis					xActiveAxis;
	@FXML
	private NumberAxis						yActiveAxis;

	@FXML
	private ScatterChart<String, Number>	reactiveChart;
	@FXML
	private CategoryAxis					xReactiveAxis;
	@FXML
	private NumberAxis						yReactiveAxis;

	private DateTime date;
	private String pathLabel;
	private String caseLabel;
	private String ddrLabel;
	
	private MainService mainService;

}
