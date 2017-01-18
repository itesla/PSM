package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;

public class SimulationDetailController implements MainChildrenController
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
		labels.add("Created:");
		labels.add(date.toString("yyyy/MM/dd HH:mm:ss"));
		labels.add("Dynamic simulator:");
		labels.add(dsLabel);
		return labels;
	}
	
	@FXML
	private void initialize()
	{

		dsChart.setCreateSymbols(false);
		yDsAxis.setForceZeroInRange(false);
		yDsAxis.setAutoRanging(true);
	}

	private void handleNewWorkflow()
	{
		LOG.debug("handleNewWorkflow");
		mainService.showSimulationNewView(mainService.getSimulation());
	}

	@FXML
	private void handleSaveMoFileContentEvent()
	{
		saveFileContentEvent(moEditor);
	}
	
	@FXML
	private void handleSaveMoweFileContentEvent()
	{
		saveFileContentEvent(moweEditor);
	}
	
	private void saveFileContentEvent(CodeEditor codeEditor)
	{
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		try
		{
			PathUtils.saveFile(location, file, ddrContent);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void handleSaveAsMoFileContentEvent()
	{
		saveAsFileContentEvent(moEditor);
	}
	
	@FXML
	private void handleSaveAsMoweFileContentEvent()
	{
		saveAsFileContentEvent(moweEditor);
	}
	
	private void saveAsFileContentEvent(CodeEditor codeEditor)
	{
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		boolean close = true;
		try
		{
			close = PathUtils.saveAsMoFile(fileChooser, mainService.getPrimaryStage(), location,
					file, ddrContent);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void handleRevertMoFileContentEvent()
	{
		moEditor.revertEdits();
	}

	@FXML
	private void handleRevertMoweFileContentEvent()
	{
		moweEditor.revertEdits();
	}

	private void showModelicaFileContent(CodeEditor codeEditor, String file)
	{

		StringBuilder fileContent = new StringBuilder();
		try
		{
			fileContent = PathUtils.loadFile(PathUtils.DATA_TMP.toString(), file);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		codeEditor.setEditingFile(PathUtils.DATA_TMP.toString(), file);
		codeEditor.setCode(fileContent);
		codeEditor.setVisible(true);
	}

	private <S1, S2, T extends List<XYChart.Series<S1, S2>>> void highlightSeriesOnHover(
			T seriesList)
	{
		for (XYChart.Series<S1, S2> series : seriesList)
		{
			Node seriesNode = series.getNode();
			// seriesNode will be null if this method is called before the scene
			// CSS has been applied
			if (seriesNode != null && seriesNode instanceof Path)
			{
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

	private <S1, S2, T extends List<XYChart.Series<S1, S2>>> void highlightSerie(T seriesList,
			Path seriesPath)
	{
		for (XYChart.Series<?, ?> series : seriesList)
		{
			Node seriesNode = series.getNode();
			// seriesNode will be null if this method is called before the scene
			// CSS has been applied
			if (seriesNode != null && seriesNode instanceof Path)
			{
				Path sPath = (Path) seriesNode;
				Paint color = colors.get(series.getName());
				if (color == null)
				{
					color = sPath.getStroke();
					colors.put(series.getName(), color);
				}
				int strokeWidth = 2;
				double opacity = 1;
				if (seriesPath != null)
				{
					if (sPath == seriesPath)
					{
						color = ((Color) color).darker();
						strokeWidth = 4;
					}
					else
					{
						color = Color.GRAY;
						strokeWidth = 1;
						opacity = 0.5;
					}
				}

				sPath.setStroke(color);
				sPath.setStrokeWidth(strokeWidth);
				sPath.setOpacity(opacity);
			}
		}
	}

	public void addSeries(WorkflowResult results)
	{

		ObservableList<XYChart.Series<Number, Number>> displayedDsSeries = FXCollections
				.observableArrayList();
		for (String key : results.getDsValues().keySet())
		{
			if (!key.endsWith(".V"))
				continue;

			XYChart.Series<Number, Number> valuesDS = new XYChart.Series<>();
			valuesDS.setName(key);

			for (DsData xyValue : results.getDsValues().get(key))
			{
				valuesDS.getData().add(new XYChart.Data<>(xyValue.getX(), xyValue.getY()));
			}
			displayedDsSeries.add(valuesDS);
		}

		dsChart.getData().addAll(displayedDsSeries);
		highlightSeriesOnHover(displayedDsSeries);
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		moTab.setDisable(true);
		if (Files.exists(PathUtils.DATA_TMP.resolve("gui_workflow_event_adder_initial.mo"),
				LinkOption.NOFOLLOW_LINKS))
		{
			moTab.setDisable(false);
			showModelicaFileContent(moEditor, "gui_workflow_event_adder_initial.mo");
		}

		moweTab.setDisable(true);
		if (Files.exists(PathUtils.DATA_TMP.resolve("gui_workflow_event_adder_events.mo"),
				LinkOption.NOFOLLOW_LINKS))
		{
			moweTab.setDisable(false);
			showModelicaFileContent(moweEditor, "gui_workflow_event_adder_events.mo");
		}
	}

	public void setWorkflow(Workflow w)
	{
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(ModelicaParserTask.class)) {
				String moInput = td.getTaskConfiguration().getParameter("source");
				
				try {
					BasicFileAttributes attr = Files.readAttributes(Paths.get(moInput), BasicFileAttributes.class);
					date = new DateTime(attr.lastModifiedTime().toMillis());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (td.getTaskClass().equals(StaticNetworkImporterTask.class)) {
				
				String uri = td.getTaskConfiguration().getParameter("source");
				
				java.nio.file.Path casePath;
				if (uri.endsWith(".xml")) {
					java.nio.file.Path path = Paths.get(uri);
					casePath = path.getParent();
				} else
					casePath = Paths.get(uri);
				
				java.nio.file.Path catalogPath = casePath.getParent();

				try {
					Catalog catalog = mainService.getCatalog("cases", catalogPath);
					Case c = mainService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + "\t" + c.getName();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class)) {
				String simulationEngine = td.getTaskConfiguration().getParameter("modelicaEngine");
				dsLabel = Utils.getDsEngine(simulationEngine).name();
			}
		}
		
		if (w.getState().equals(ProcessState.SUCCESS))
		{
			addSeries(mainService.getSimulationResult("" + w.getId()));
			Utils.addTooltipLineChartPosition(dsChart, "Time", "s", "Voltage", "pu");
		}

	}

	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@FXML
	private Tab								moTab;
	@FXML
	private Tab								moweTab;

	@FXML
	private CodeEditor						moEditor;
	@FXML
	private CodeEditor						moweEditor;

	@FXML
	private LineChart<Number, Number>		dsChart;
	@FXML
	private NumberAxis						xDsAxis;
	@FXML
	private NumberAxis						yDsAxis;

	private String							caseLabel;
	private DateTime						date;
	private String							dsLabel;

	private Map<String, Paint>				colors	= new HashMap<String, Paint>();
	private GuiFileChooser					fileChooser;
	
	private MainService						mainService;

	private static final Logger				LOG		= LoggerFactory
			.getLogger(SimulationDetailController.class);
}
