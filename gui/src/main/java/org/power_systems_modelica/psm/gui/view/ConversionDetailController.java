package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask.ElementModel;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import eu.itesla_project.iidm.network.Identifiable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConversionDetailController implements MainChildrenController
{

	@Override
	public void handleMainAction()
	{

		mainService.showSimulationWithCase(c);
	}

	@Override
	public void handleMenuAction(String action)
	{
		switch (action)
		{
		case "New conversion":
			handleNewConversionEvent();
			break;
		}

	}

	@Override
	public String getMainAction()
	{

		return "Simulate";
	}

	@Override
	public List<String> getMenuActions()
	{

		List<String> actions = new ArrayList();
		actions.add("New conversion");
		return actions;
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{
		String dateLabel = "";
		if (date != null)
			dateLabel = date.toString("yyyy/MM/dd HH:mm:ss");

		List<SummaryLabel> labels = new ArrayList();
		if (!isCheckDetail)
			labels.add(new SummaryLabel("Path:", pathLabel, false, false));
		labels.add(new SummaryLabel("Case:", caseLabel, false, true));
		labels.add(new SummaryLabel("DDR:", ddrLabel, true, true));
		if (!isCheckDetail)
		{
			labels.add(new SummaryLabel("Created:", dateLabel, false, true));
			labels.add(new SummaryLabel("Loadflow:", loadflowLabel, true, true));
			labels.add(new SummaryLabel("Modelica network:", onlyMainComponentLabel, false, true));
			labels.add(
					new SummaryLabel("Full model initialization:", fullModelInitializationLabel,
							true,
							true));
		}
		else
			labels.add(new SummaryLabel("Modelica network:", onlyMainComponentLabel, false, false));
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

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		curvesTab.setDisable(false);
		modelsTab.setDisable(false);
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

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{

		if (objects.length > 0)
		{
			isCheckDetail = (boolean) objects[0];
		}

		loadflowLabel = "None";
		moTab.setDisable(true);
		curvesTab.setDisable(true);
		modelsTab.setDisable(true);
		staticTable.getItems().clear();
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{

			if (td.getTaskClass().equals(ModelicaExporterTask.class))
			{

				String uri = td.getTaskConfiguration().getParameter("target");
				Path moFile = Paths.get(uri);
				pathLabel = uri;

				StringBuilder moContent = new StringBuilder();
				try
				{
					BasicFileAttributes attr = Files.readAttributes(Paths.get(pathLabel),
							BasicFileAttributes.class);
					date = new DateTime(attr.lastModifiedTime().toMillis());

					moContent = PathUtils.loadFile(moFile);
				}
				catch (IOException e)
				{
					date = null;
				}
				codeEditor.setCode(moContent);
			}

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
					c = CaseService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + " - " + c.getName();
				}
				catch (IOException e)
				{
					caseLabel = "";
				}
			}

			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class))
			{

				String uri = td.getTaskConfiguration().getParameter("ddrLocation");
				Path ddrPath = Paths.get(uri).normalize();
				Path catalogPath = ddrPath.getParent().getParent();

				try
				{
					Catalog catalog = CatalogService.getCatalog("ddrs", catalogPath);
					Ddr ddr = DdrService.getDdr(catalog.getName(), ddrPath);
					ddrLabel = catalog.getName() + "\t" + ddr.getName();
				}
				catch (IOException e)
				{
					ddrLabel = "";
				}

				onlyMainComponentLabel = td.getTaskConfiguration()
						.getParameter("onlyMainConnectedComponent")
						.equals("true") ? "Only main connected component"
								: "All connected components";
				fullModelInitializationLabel = Utils.getDsEngine(td.getTaskConfiguration()
						.getParameter("modelicaEngine")).toString();
			}

			if (td.getTaskClass().equals(LoadFlowTask.class))
			{
				loadflowLabel = Utils.getLoadflowEngine(td.getTaskId()).toString();
			}
		}

		WorkflowResult r = WorkflowServiceConfiguration.getConversionResult("" + w.getId());
		Collection<Identifiable<?>> unmapped = WorkflowServiceConfiguration
				.getElementsMissingDynamicModel("" + w.getId());
		if (w.getState().equals(ProcessState.SUCCESS))
		{
			boolean successful = unmapped == null || unmapped.isEmpty();
			if (!isCheckDetail)
			{
				moTab.setDisable(false);
				if (successful)
				{
					resultIcon.setImage(okImage);
					resultText.setText(caseLabel + " converted successfully");
				}
				else
				{
					resultIcon.setImage(wnImage);
					resultText.setText("Conversion model with errors.");
				}
				curvesTab.setDisable(false);
				addSeries(r);
				UtilsFX.addTooltipScatterChart(voltageChart, "pu");
				UtilsFX.addTooltipScatterChart(phaseChart, "º");
				UtilsFX.addTooltipScatterChart(activeChart, "MW");
				UtilsFX.addTooltipScatterChart(reactiveChart, "MVar");
			}
			else
			{
				if (successful)
				{
					resultIcon.setImage(okImage);
					resultText.setText("Check of " + caseLabel + " completed successfully");
				}
				else
				{
					resultIcon.setImage(wnImage);
					resultText.setText("Check of " + caseLabel + " with errors.");
				}
			}
		}
		else
		{
			if (!isCheckDetail)
			{
				moTab.setDisable(false);
				resultText.setText("Conversion model failed. See logs tab for more details.");
			}
			else
				resultText.setText("Check model failed.");
			resultIcon.setImage(koImage);
		}

		TreeItem<ElementModel> root = modelsTable.getRoot();
		List<ElementModel> models = r.getModels();

		if (models != null || (unmapped != null && !unmapped.isEmpty()))
			modelsTab.setDisable(false);
		
		if (unmapped != null && !unmapped.isEmpty()) staticTable.getItems().addAll(unmapped);
		if (models != null) 
		{
			models.stream().forEach((model) -> {
				Optional<TreeItem<ElementModel>> item = root.getChildren().stream().filter(t -> {
					return ((ElementModel) ((TreeItem<ElementModel>) t).getValue()).getStaticId()
							.equals(((ElementModel) model).getStaticId());
				}).findFirst();
	
				TreeItem<ElementModel> treeItem = root;
				if (item.isPresent())
					treeItem = item.get();
	
				treeItem.getChildren().add(new TreeItem<ElementModel>((ElementModel) model));
				treeItem.setExpanded(true);
			});
			root.getChildren().sort(Comparator
					.comparing(t -> ((TreeItem<ElementModel>) t).getValue().getStaticId()));
		}

		StringBuilder sb = new StringBuilder();
		for (Exception e : r.getExceptions())
		{
			sb.append(Utils.getStackTrace(e));
			sb.append("\n\n");
		}

		Logs l = WorkflowServiceConfiguration.getConversionLogs("" + w.getId());
		if (l != null)
		{
			l.dump(sb);
			sb.append("\n\n");
		}

		logArea.setText(sb.toString());
	}

	public void setCase(Case c)
	{
		this.c = c;

		curvesTab.setDisable(true);
		modelsTab.setDisable(true);

		Path casePath = Paths.get(c.getLocation());
		Path catalogPath = casePath.getParent();
		Path convertedPath = casePath.resolve(c.getName() + ".mo");

		Catalog catalog;
		try
		{
			catalog = CatalogService.getCatalog("cases", catalogPath);
			ConvertedCase cc = CaseService.getConvertedCase(catalog.getName(), casePath);

			Path ddrPath = Paths.get(cc.getDdrLocation());
			Ddr ddr = DdrService.getDdr(catalog.getName(), ddrPath);

			pathLabel = convertedPath.toString();
			try
			{
				BasicFileAttributes attr = Files.readAttributes(Paths.get(pathLabel),
						BasicFileAttributes.class);
				date = new DateTime(attr.lastModifiedTime().toMillis());
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			caseLabel = catalog.getName() + "\t" + c.getName();
			ddrLabel = catalog.getName() + "\t" + ddr.getName();

			loadflowLabel = cc.getLoadflowEngine();
			onlyMainComponentLabel = cc.getOnlyMainConnectedComponent();
			fullModelInitializationLabel = cc.getFullModelInitializationEgine();
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuilder moContent = new StringBuilder();
		try
		{
			moContent = PathUtils.loadFile(convertedPath);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codeEditor.setCode(moContent);
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

		staticIdColumn.setPrefWidth(220.0);
		staticIdColumn.setCellValueFactory((param) -> {
			TreeItem<ElementModel> root = param.getTreeTableView().getRoot();
			if (param.getValue().getParent().equals(root))
				return new ReadOnlyStringWrapper(param.getValue().getValue().getStaticId());
			else
				return new ReadOnlyStringWrapper("");
		});
		staticIdColumn.widthProperty().addListener(new ChangeListener<Number>()
		{

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue,
					Number newValue)
			{
				double dynamicIdColumnWidth = modelsTable.getWidth() - ((double) newValue) - 15;
				dynamicIdColumn.setPrefWidth(dynamicIdColumnWidth);
			}

		});
		originColumn.setPrefWidth(220.0);
		originColumn.setCellValueFactory((param) -> {
			TreeItem<ElementModel> root = param.getTreeTableView().getRoot();
			if (param.getValue().getParent().equals(root))
				return new ReadOnlyStringWrapper(param.getValue().getValue().getOrigin());
			else
				return new ReadOnlyStringWrapper("");
		});
		dynamicIdColumn.setPrefWidth(680.0);
		dynamicIdColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<ElementModel, String> param) -> new ReadOnlyStringWrapper(
						param.getValue().getValue().getDynamicType()));

		TreeItem<ElementModel> root = new TreeItem<>();
		root.setExpanded(true);
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
		modelsTable.widthProperty().addListener(new ChangeListener<Number>()
		{

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue,
					Number newValue)
			{
				if (((double) oldValue) == 0.0 || ((double) newValue) == 0.0) return;

				double ratio = ((double) newValue) / ((double) oldValue);
				double staticIdColumnWidth = staticIdColumn.getWidth();
				staticIdColumn.setPrefWidth(staticIdColumnWidth * ratio);
			}

		});
	}

	@FXML
	private void handleFindContentEvent()
	{
		codeEditor.find();
	}

	private void handleNewConversionEvent()
	{
		mainService.showConversionNewView(WorkflowServiceConfiguration.getConversion());
	}

	@FXML
	private TabPane									tabPane;

	@FXML
	private Tab										resultTab;
	@FXML
	private ImageView								resultIcon;
	@FXML
	private Label									resultText;

	@FXML
	private Tab										moTab;
	@FXML
	private CodeEditor								codeEditor;

	@FXML
	private Tab										modelsTab;
	@FXML
	private ListView<Identifiable<?>>				staticTable;
	@FXML
	private TreeTableView<ElementModel>				modelsTable;
	@FXML
	private TreeTableColumn<ElementModel, String>	staticIdColumn;
	@FXML
	private TreeTableColumn<ElementModel, String>	originColumn;
	@FXML
	private TreeTableColumn<ElementModel, String>	dynamicIdColumn;

	@FXML
	private Tab										curvesTab;

	@FXML
	private ScatterChart<String, Number>			voltageChart;
	@FXML
	private CategoryAxis							xVoltageAxis;
	@FXML
	private NumberAxis								yVoltageAxis;

	@FXML
	private ScatterChart<String, Number>			phaseChart;
	@FXML
	private CategoryAxis							xPhaseAxis;
	@FXML
	private NumberAxis								yPhaseAxis;

	@FXML
	private ScatterChart<String, Number>			activeChart;
	@FXML
	private CategoryAxis							xActiveAxis;
	@FXML
	private NumberAxis								yActiveAxis;

	@FXML
	private ScatterChart<String, Number>			reactiveChart;
	@FXML
	private CategoryAxis							xReactiveAxis;
	@FXML
	private NumberAxis								yReactiveAxis;

	@FXML
	private Tab										logTab;
	@FXML
	private TextArea								logArea;

	private DateTime								date;
	private String									pathLabel;
	private Case									c;
	private String									caseLabel;
	private String									ddrLabel;
	private String									loadflowLabel;
	private String									onlyMainComponentLabel;
	private String									fullModelInitializationLabel;
	private boolean									isCheckDetail	= false;

	private Image									okImage			= new Image(
			getClass().getResourceAsStream("/img/hook.png"));
	private Image									wnImage			= new Image(
			getClass().getResourceAsStream("/img/warning.png"));
	private Image									koImage			= new Image(
			getClass().getResourceAsStream("/img/false.png"));

	private MainService								mainService;

}
