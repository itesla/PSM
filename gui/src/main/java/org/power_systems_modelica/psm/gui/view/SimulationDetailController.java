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
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.PathUtilsFX;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;

public class SimulationDetailController implements MainChildrenController
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

		return "New simulation";
	}

	@Override
	public List<String> getMenuActions()
	{

		return null;
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{
		String dateLabel = "";
		if (date != null)
			dateLabel = date.toString("yyyy/MM/dd HH:mm:ss");

		List<SummaryLabel> labels = new ArrayList<>();
		labels.add(new SummaryLabel("Case:", caseLabel, false, true));
		labels.add(new SummaryLabel("Created:", dateLabel, true, true));
		labels.add(new SummaryLabel("Dynamic simulator:", dsLabel, false, false));
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
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		String moInput = null;
		moTab.setDisable(true);
		moweTab.setDisable(true);
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(ModelicaParserTask.class))
			{
				moInput = td.getTaskConfiguration().getParameter("source");

				java.nio.file.Path casePath;
				if (moInput.endsWith(".mo"))
				{
					java.nio.file.Path path = Paths.get(moInput);
					casePath = path.getParent();
				}
				else
					casePath = Paths.get(moInput);

				java.nio.file.Path catalogPath = casePath.getParent();

				try
				{
					Catalog catalog = CatalogService.getCatalog("cases", catalogPath);
					Case c = CaseService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + " - " + c.getName();

					BasicFileAttributes attr = Files.readAttributes(Paths.get(moInput),
							BasicFileAttributes.class);
					date = new DateTime(attr.lastModifiedTime().toMillis());
				}
				catch (IOException e)
				{
					caseLabel = "";
					date = null;
				}
			}

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
			{
				String simulationEngine = td.getTaskConfiguration().getParameter("modelicaEngine");
				dsLabel = Utils.getDsEngine(simulationEngine).toString();
			}
		}

		if (moInput != null)
		{

			java.nio.file.Path moInputPath = Paths.get(moInput);
			String path = moInputPath.toFile().getParent();
			if (Files.exists(moInputPath, LinkOption.NOFOLLOW_LINKS))
			{
				moTab.setDisable(false);
				String file = moInputPath.toFile().getName();
				showModelicaFileContent(moEditor, path, file);
			}

			String moweInput = Utils.replaceLast(moInput, ".mo", "_events.mo");
			java.nio.file.Path moweInputPath = Paths.get(moweInput);
			if (Files.exists(moweInputPath, LinkOption.NOFOLLOW_LINKS))
			{
				moweTab.setDisable(false);
				String file = moweInputPath.toFile().getName();
				showModelicaFileContent(moweEditor, path, file);
			}
		}

		results = WorkflowServiceConfiguration.getSimulationResult("" + w.getId());
		StringBuilder sb = new StringBuilder();
		for (Exception e : results.getExceptions())
		{
			sb.append(Utils.getStackTrace(e));
			sb.append("\n\n");
		}
		
		Logs l = WorkflowServiceConfiguration.getSimulationLogs("" + w.getId());
		if (l != null)
		{
			l.dump(sb);
			sb.append("\n\n");
		}
		
		logArea.setText(sb.toString());

		if (resultController != null)
		{
			resultController.setMainService(mainService);
			resultController.setWorkflow(w, objects);
		}
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@Override
	public void setDefaultInit()
	{
	}

	public void addController(SimulationResultDetailController controller)
	{
		resultController = controller;
	}

	public void addNode(Node node)
	{
		resultTab.setContent(node);
	}

	@FXML
	private void initialize()
	{
	}

	private void handleNewWorkflow()
	{
		LOG.debug("handleNewWorkflow");
		mainService.showSimulationNewView(WorkflowServiceConfiguration.getSimulation());
	}

	@FXML
	private void handleFindMoContentEvent()
	{
		moEditor.find();
	}

	@FXML
	private void handleFindMoweContentEvent()
	{
		moweEditor.find();
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

		try
		{
			PathUtilsFX.saveAsMoFile(fileChooser, mainService.getPrimaryStage(), location,
					file, ddrContent);
		}
		catch (IOException e)
		{
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

	private void showModelicaFileContent(CodeEditor codeEditor, String path, String file)
	{

		StringBuilder fileContent = new StringBuilder();
		try
		{
			fileContent = PathUtils.loadFile(path, file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		codeEditor.setEditingFile(path, file);
		codeEditor.setCode(fileContent);
		codeEditor.setVisible(true);
	}

	@FXML
	private TabPane								tabPane;

	@FXML
	private Tab									resultTab;
	@FXML
	private Tab									moTab;
	@FXML
	private Tab									moweTab;
	@FXML
	private Tab									logTab;

	@FXML
	private CodeEditor							moEditor;
	@FXML
	private CodeEditor							moweEditor;

	@FXML
	private TextArea							logArea;

	private String								caseLabel;
	private DateTime							date;
	private String								dsLabel;

	private Map<String, Paint>					colors				= new HashMap<String, Paint>();
	private GuiFileChooser						fileChooser;

	ObservableList<String>						selectedBuses		= FXCollections
			.observableArrayList();
	private ContextMenu							menu				= new ContextMenu();

	private WorkflowResult						results;
	private MainService							mainService;

	private SimulationResultDetailController	resultController	= null;

	private static final Logger					LOG					= LoggerFactory
			.getLogger(SimulationDetailController.class);
}
