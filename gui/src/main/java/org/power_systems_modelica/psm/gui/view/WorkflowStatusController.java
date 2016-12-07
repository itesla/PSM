package org.power_systems_modelica.psm.gui.view;

import java.util.List;

import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.service.WorkflowService;
import org.power_systems_modelica.psm.gui.utils.DynamicTreeView;
import org.power_systems_modelica.psm.gui.utils.ProgressData;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;

public class WorkflowStatusController {

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		if (isWorkflowDetail)
			mainService.showWorkflowView(null);
		else
			mainService.showCompareLoadflowsView(null);
	}
	
	public void setTask(Workflow w, Task task) {
		
		if (task != null) {
			statusLabel.textProperty().bind(task.messageProperty());
			statusBar.progressProperty().bind(task.progressProperty());
		}
		
		List<TaskDefinition> tasks = w.getConfiguration().getTaskDefinitions();
		
		TreeItem<ProgressData> root = new TreeItem<>();
		root.setExpanded(true);
		treeView.getStyleClass().add("treeViewItem");
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		treeView.setItems(((WorkflowService)task).getWorkflowInfo());
	}

	public void setMainService(MainService mainService, Workflow w, boolean isWorkflowDetail) {
		this.mainService = mainService;

		this.isWorkflowDetail = isWorkflowDetail;
		if (isWorkflowDetail) 
			panel.setText("Workflow detail");
		else 
			panel.setText("Compare loadflows detail");

		createdLabel.setText("" + w.getId());
		
	}

	@FXML
	private TitledPane panel;

	@FXML
	private Label createdLabel;

	@FXML
	private Label statusLabel;
	
	@FXML
	private ProgressBar statusBar;
	
	@FXML
	private DynamicTreeView<ProgressData> treeView;

	private MainService mainService;
	private boolean isWorkflowDetail;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowStatusController.class);
}
