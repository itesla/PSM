package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;

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

	public void setMainApp(MainApp mainApp, Workflow w, boolean isWorkflowDetail) {
		this.mainApp = mainApp;

		this.isWorkflowDetail = isWorkflowDetail;
		if (isWorkflowDetail) 
			panel.setText("Workflow detail");
		else 
			panel.setText("Compare loadflows detail");
		createdLabel.setText("" + w.getId());
		statusLabel.setText(w.getState().name());
		//statusBar.setProgress(w.getProgress());
		//statusBar.setTooltip(new Tooltip(String.format("%.2f",w.getProgress()*100) + "%"));
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
