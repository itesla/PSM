package org.power_systems_modelica.psm.gui.view;

import java.util.List;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.service.WorkflowTask;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class WorkflowStatusController {

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		if (isWorkflowDetail)
			mainApp.showWorkflowView(null);
		else
			mainApp.showCompareLoadflowsView(null);
	}
	
	public void setTask(Workflow w, Task task) {
		
		if (task != null) {
			statusLabel.textProperty().bind(task.messageProperty());
			statusBar.progressProperty().bind(task.progressProperty());
		}
		
		List<TaskDefinition> tasks = w.getConfiguration().getTaskDefinitions();
		int i = 0;
		for (TaskDefinition t : tasks) {
			Label label = new Label(t.getTaskId());
			gridPane.add(label, 0, i);
			Label status = new Label();
			status.textProperty().bind(((WorkflowTask)task).workflowStateProperty(t.getTaskId()));
			gridPane.add(status, 1, i);
			RowConstraints constraints = new RowConstraints();
			constraints.setMinHeight(30);
			constraints.setPrefHeight(30);
			gridPane.getRowConstraints().add(constraints);
			i++;
		}
	}

	public void setMainApp(MainApp mainApp, Workflow w, boolean isWorkflowDetail) {
		this.mainApp = mainApp;

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
	private GridPane gridPane;

	private MainApp mainApp;
	private boolean isWorkflowDetail;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowStatusController.class);
}
