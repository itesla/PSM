package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.service.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;

public class WorkflowStatusController {

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleNewWorkflow() {
		LOG.debug("handleNewWorkflow");
		mainApp.showWorkflowView(null);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		Workflow w = mainApp.getWorkflow();
		
		createdLabel.setText(w.getName());
		statusLabel.setText(w.getStatus().name());
		statusBar.setProgress(w.getProgress());
		statusBar.setTooltip(new Tooltip(String.format("%.2f",w.getProgress()*100) + "%"));
	}

	@FXML
	private Label createdLabel;

	@FXML
	private Label statusLabel;
	
	@FXML
	private ProgressBar statusBar;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowStatusController.class);
}
