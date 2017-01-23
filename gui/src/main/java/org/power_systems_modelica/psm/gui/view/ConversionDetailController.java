package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask.ElementModel;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class ConversionDetailController implements MainChildrenController {

	@Override
	public void handleMainAction() {

		handleNewConversionEvent();
	}

	@Override
	public void handleMenuAction(String action) {

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
		labels.add("DDR:");
		labels.add(ddrLabel);
		labels.add("Loadflow:");
		labels.add(loadflowLabel);
		labels.add("Modelica network:");
		labels.add(onlyMainComponentLabel);
		labels.add("Full model initialization:");
		labels.add(fullModelInitializationLabel);
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

	@FXML
	private void handleFindContentEvent() {
		codeEditor.find();
	}

	private void handleNewConversionEvent() {
		mainService.showConversionNewView(mainService.getConversion());
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;

		curvesTab.setDisable(false);
		modelsTab.setDisable(false);
	}

	public void addSeries(WorkflowResult results) {
		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = FXCollections.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedPhaseSeries = FXCollections.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedActiveSeries = FXCollections.observableArrayList();
		ObservableList<XYChart.Series<String, Number>> displayedReactiveSeries = FXCollections.observableArrayList();
		XYChart.Series<String, Number> valuesV = new XYChart.Series<>();
		valuesV.setName("V");
		XYChart.Series<String, Number> valuesA = new XYChart.Series<>();
		valuesA.setName("A");
		XYChart.Series<String, Number> valuesP = new XYChart.Series<>();
		valuesP.setName("P");
		XYChart.Series<String, Number> valuesQ = new XYChart.Series<>();
		valuesQ.setName("Q");

		for (BusData bus : results.getAllBusesValues()) {
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

		loadflowLabel = "None";
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

				onlyMainComponentLabel = td.getTaskConfiguration().getParameter("onlyMainConnectedComponent")
						.equals("true") ? "Only main connected component" : "All connected components";
				fullModelInitializationLabel = td.getTaskConfiguration().getParameter("modelicaEngine");
			}

			if (td.getTaskClass().equals(LoadFlowTask.class)) {
				loadflowLabel = td.getTaskId();
			}
		}

		if (w.getState().equals(ProcessState.SUCCESS)) {
			WorkflowResult r = mainService.getConversionResult("" + w.getId());
			addSeries(r);
			Utils.addTooltipScatterChart(voltageChart, "pu");
			Utils.addTooltipScatterChart(phaseChart, "º");
			Utils.addTooltipScatterChart(activeChart, "MW");
			Utils.addTooltipScatterChart(reactiveChart, "MVar");

			TreeItem<ElementModel> root = new TreeItem<>();
			root.setExpanded(true);
			List<ElementModel> models = r.getModels();
			models.stream().forEach((model) -> {
				Optional<TreeItem<ElementModel>> item = root.getChildren().stream().filter(t -> {
					return ((ElementModel)((TreeItem<ElementModel>) t).getValue()).getStaticId().equals(((ElementModel) model).getStaticId());
				}).findFirst();
				
				TreeItem<ElementModel> treeItem = root;
				if (item.isPresent()) 
					treeItem = item.get();
				
				treeItem.getChildren().add(new TreeItem<ElementModel>((ElementModel) model));
				treeItem.setExpanded(true);
			});
			
			root.getChildren().stream().forEach((item) -> {
				// Ensure all items are expanded
				item.expandedProperty().addListener(observable -> {
					if (!item.isExpanded())
						item.setExpanded(true);
				});
			});
			
			modelsTable.getStyleClass().add("treeViewItem");
			modelsTable.setRoot(root);
			modelsTable.setShowRoot(false);
			staticIdColumn.prefWidthProperty().bind(modelsTable.widthProperty().multiply(0.2));
			dynamicIdColumn.prefWidthProperty().bind(modelsTable.widthProperty().multiply(0.8));

			staticIdColumn.setCellValueFactory((param) -> {
				if (param.getValue().getParent().equals(root))
					return new ReadOnlyStringWrapper(param.getValue().getValue().getStaticId());
				else
					return new ReadOnlyStringWrapper("");
			});

			dynamicIdColumn.setCellValueFactory(
					(TreeTableColumn.CellDataFeatures<ElementModel, String> param) -> new ReadOnlyStringWrapper(
							param.getValue().getValue().getDynamicId()));
		}
	}

	public void setConversionResult(Case c) {

		curvesTab.setDisable(true);
		modelsTab.setDisable(true);

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

			loadflowLabel = cc.getLoadflowEngine();
			onlyMainComponentLabel = cc.getOnlyMainConnectedComponent();
			fullModelInitializationLabel = cc.getFullModelInitializationEgine();
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
	private Tab modelsTab;
	@FXML
	private TreeTableView<ElementModel> modelsTable;
	@FXML
	private TreeTableColumn<ElementModel, String> staticIdColumn;
	@FXML
	private TreeTableColumn<ElementModel, String> dynamicIdColumn;

	@FXML
	private Tab curvesTab;

	@FXML
	private ScatterChart<String, Number> voltageChart;
	@FXML
	private CategoryAxis xVoltageAxis;
	@FXML
	private NumberAxis yVoltageAxis;

	@FXML
	private ScatterChart<String, Number> phaseChart;
	@FXML
	private CategoryAxis xPhaseAxis;
	@FXML
	private NumberAxis yPhaseAxis;

	@FXML
	private ScatterChart<String, Number> activeChart;
	@FXML
	private CategoryAxis xActiveAxis;
	@FXML
	private NumberAxis yActiveAxis;

	@FXML
	private ScatterChart<String, Number> reactiveChart;
	@FXML
	private CategoryAxis xReactiveAxis;
	@FXML
	private NumberAxis yReactiveAxis;

	private DateTime date;
	private String pathLabel;
	private String caseLabel;
	private String ddrLabel;
	private String loadflowLabel;
	private String onlyMainComponentLabel;
	private String fullModelInitializationLabel;

	private MainService mainService;

}
