package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;

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
	
	public void setTask(Task task) {
		
		if (task != null) {
			statusLabel.textProperty().bind(task.messageProperty());
			statusBar.progressProperty().bind(task.progressProperty());
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

	private MainApp mainApp;
	private boolean isWorkflowDetail;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowStatusController.class);
}
