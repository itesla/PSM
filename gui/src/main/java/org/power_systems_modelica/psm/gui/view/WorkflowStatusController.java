package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.MainApp.WorkflowType;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.service.WorkflowService;
import org.power_systems_modelica.psm.gui.utils.DynamicTreeView;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.ProgressData;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;

public class WorkflowStatusController implements MainChildrenController
{
	@Override
	public void handleMainAction()
	{

		handleStopWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{

	}

	@Override
	public String getMainAction()
	{

		return "Stop";
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
		labels.add(new SummaryLabel(firstLabelTitle, firstLabelValue, false,
				secondLabelTitle != null));
		if (secondLabelTitle != null)
		{
			labels.add(new SummaryLabel(secondLabelTitle, secondLabelValue, true, true));
		}
		return labels;
	}

	@FXML
	private void initialize()
	{
	}

	@FXML
	private void handleStopWorkflow()
	{
		LOG.debug("handleStopWorkflow");
		if (isWorkflowDetail.equals(WorkflowType.CONVERSION))
			mainService.stopConversion(w);
		else if (isWorkflowDetail.equals(WorkflowType.SIMULATION))
			mainService.stopSimulation(w);
		else
			mainService.stopCompareLoadflows();
	}

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		this.w = w;

		this.isWorkflowDetail = (WorkflowType) objects[0];
		if (isWorkflowDetail.equals(WorkflowType.CONVERSION))
		{
			panel.setText("Conversion detail");
			firstLabelTitle = "Case:";
			secondLabelTitle = "DDR:";
		}
		else if (isWorkflowDetail.equals(WorkflowType.SIMULATION))
		{
			panel.setText("Simulation detail");
			firstLabelTitle = "Case:";
			secondLabelTitle = "Created:";
		}
		else
		{
			panel.setText("Compare loadflows detail");
			firstLabelTitle = "Case:";
			secondLabelTitle = null;
			secondLabelValue = null;
		}

		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(ModelicaParserTask.class))
			{
				String moInput = td.getTaskConfiguration().getParameter("source");
				try
				{
					BasicFileAttributes attr = Files.readAttributes(Paths.get(moInput),
							BasicFileAttributes.class);
					DateTime date = new DateTime(attr.lastModifiedTime().toMillis());
					secondLabelValue = date.toString("yyyy/MM/dd HH:mm:ss");
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
					firstLabelValue = catalog.getName() + "\t" + c.getName();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (td.getTaskClass().equals(ModelicaNetworkBuilderTask.class))
			{
				String uri = td.getTaskConfiguration().getParameter("ddrLocation");
				Path ddrPath = Paths.get(uri).normalize();
				Path catalogPath = ddrPath.getParent().getParent();
				try
				{
					Catalog catalog = mainService.getCatalog("ddrs", catalogPath);
					Ddr ddr = mainService.getDdr(catalog.getName(), ddrPath);
					secondLabelValue = catalog.getName() + "\t" + ddr.getName();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		Task<?> task = null;
		if (isWorkflowDetail.equals(WorkflowType.CONVERSION))
			task = mainService.getConversionTask();
		else if (isWorkflowDetail.equals(WorkflowType.SIMULATION))
			task = mainService.getSimulationTask();
		else
			task = mainService.getCompareLoadflowTask();
		
		if (task != null)
		{
			statusLabel.textProperty().bind(task.messageProperty());
			statusBar.progressProperty().bind(task.progressProperty());
		}

		TreeItem<ProgressData> root = new TreeItem<>();
		root.setExpanded(true);
		treeView.getStyleClass().add("treeViewItem");
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		treeView.setItems(((WorkflowService) task).getWorkflowInfo());
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
	private TitledPane						panel;

	@FXML
	private Label							statusLabel;

	@FXML
	private ProgressBar						statusBar;

	@FXML
	private DynamicTreeView<ProgressData>	treeView;

	private String							firstLabelTitle;
	private String							firstLabelValue;
	private String							secondLabelTitle;
	private String							secondLabelValue;

	private Workflow						w;
	private MainService						mainService;
	private WorkflowType					isWorkflowDetail;

	private static final Logger				LOG	= LoggerFactory
			.getLogger(WorkflowStatusController.class);

}
