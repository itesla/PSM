package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.DynamicTreeView;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.ProgressData;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;

public class SimulationCheckVerifyDetailController implements MainChildrenController
{

	@Override
	public void handleMainAction()
	{

		handleSimulateWorkflow(false);
	}

	@Override
	public void handleMenuAction(String action)
	{

		switch (action)
		{
		case "New":
			handleNewWorkflow();
			break;
		case "Verify":
			handleSimulateWorkflow(true);
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
		actions.add("New");
		if (isCheckDetail)
			actions.add("Verify");
		return actions;
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{

		List<SummaryLabel> labels = new ArrayList();
		labels.add(new SummaryLabel("Case:", caseLabel, false, true));
		labels.add(new SummaryLabel("Created:", createdLabel, true, true));
		if (isCheckDetail)
			labels.add(new SummaryLabel("Check:", checkLabel, false, false));
		else
			labels.add(new SummaryLabel("Verify:", checkLabel, false, false));
		return labels;
	}

	@FXML
	private void initialize()
	{
	}

	private void handleNewWorkflow()
	{

		mainService.showSimulationNewView(mainService.getSimulation());
	}

	private void handleSimulateWorkflow(boolean isVerify)
	{

		try
		{
			ConvertedCase cs = mainService.getConvertedCase(catalogName, casePath);
			mainService.startSimulation(cs, events, dse, stopTime, false, isVerify,
					Boolean.getBoolean(createFilteredMat));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private void showModelicaFileContent(CodeEditor codeEditor, String path, String file)
	{

		StringBuilder fileContent = new StringBuilder();
		try
		{
			fileContent = PathUtils.loadFile(path, file);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		codeEditor.setEditingFile(path, file);
		codeEditor.setCode(fileContent);
		codeEditor.setVisible(true);
	}

	public void setMainService(MainService mainService, Workflow w, boolean isCheckDetail)
	{

		this.mainService = mainService;
		this.isCheckDetail = isCheckDetail;

		if (isCheckDetail)
		{
			panel.setText("Simulation check detail");
		}
		else
		{
			panel.setText("Simulation verify detail");
		}

		checkLabel = "Label";
		String moInput = null;
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{

			if (td.getTaskClass().equals(ModelicaParserTask.class))
			{
				moInput = td.getTaskConfiguration().getParameter("source");

				try
				{
					BasicFileAttributes attr = Files.readAttributes(Paths.get(moInput),
							BasicFileAttributes.class);
					DateTime date = new DateTime(attr.lastModifiedTime().toMillis());

					createdLabel = date.toString("yyyy/MM/dd HH:mm:ss");
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (td.getTaskClass().equals(StaticNetworkImporterTask.class))
			{

				String uri = td.getTaskConfiguration().getParameter("source");

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
					catalogName = catalog.getName();

					Case c = mainService.getCase(catalog.getName(), casePath);
					caseLabel = catalogName + "\t" + c.getName();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (td.getTaskClass().equals(ModelicaEventAdderTask.class))
			{

				events.clear();
				String[] evs = td.getTaskConfiguration().getParameter("events").split("\n");
				for (String event : evs)
				{

					Event e = new Event();
					e.fromString(event);
					events.add(e);
				}
			}

			if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
			{
				stopTime = td.getTaskConfiguration().getParameter("stopTime");
				createFilteredMat = td.getTaskConfiguration().getParameter("stopTime");

				String simulationEngine = td.getTaskConfiguration().getParameter("modelicaEngine");
				dse = Utils.getDsEngine(simulationEngine);
			}
		}

		moTab.setDisable(true);
		moweTab.setDisable(true);
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
	}

	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@FXML
	private TitledPane						panel;

	@FXML
	private Tab								moTab;
	@FXML
	private Tab								moweTab;

	@FXML
	private CodeEditor						moEditor;
	@FXML
	private CodeEditor						moweEditor;

	@FXML
	private DynamicTreeView<ProgressData>	treeView;

	private String							caseLabel;
	private String							createdLabel;
	private String							checkLabel;

	private String							catalogName;
	private Path							casePath;
	private ObservableList<Event>			events	= FXCollections.observableArrayList();
	private String							stopTime;
	private DsEngine						dse;
	private String							createFilteredMat;

	private boolean							isCheckDetail;
	private GuiFileChooser					fileChooser;

	private MainService						mainService;

	private static final Logger				LOG		= LoggerFactory
			.getLogger(SimulationCheckVerifyDetailController.class);
}
