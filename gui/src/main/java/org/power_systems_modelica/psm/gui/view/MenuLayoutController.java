package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuLayoutController {

	@FXML
	private void initialize() {
		compareLoadflows.setVisible(!DISABLECOMPARELOADFLOWS);
		compareLoadflows.setDisable(DISABLECOMPARELOADFLOWS);
	}

	@FXML
	private void handleCasesOverview() {
		cases.getStyleClass().add("active");
		ddrs.getStyleClass().remove("active");
		workflow.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");
		
		mainApp.showCasesOverview();
	}
	
	@FXML
	private void handleDdrsOverview() {
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().add("active");
		workflow.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");
		
		mainApp.showDdrsOverview();
	}

	@FXML
	private void handleWorkflowOverview() {
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		workflow.getStyleClass().add("active");
		compareLoadflows.getStyleClass().remove("active");
		
		Workflow w = mainApp.getWorkflow();
		mainApp.showWorkflowView(w);
	}

	@FXML
	private void handleCompareLoadflowsOverview() {
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		workflow.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().add("active");
		
		Workflow w = mainApp.getCompareLoadflows();
		mainApp.showCompareLoadflowsView(w);
	}
	
	private void selectOption(Button b) {
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		workflow.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");
		
		b.getStyleClass().add("active");
	}
	
	public void selectWorkflowOption() {
		selectOption(workflow);
	}

	public void selectCompareLoadflowsOption() {
		selectOption(compareLoadflows);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private Button cases;
	@FXML
	private Button ddrs;
	@FXML
	private Button workflow;
	@FXML
	private Button compareLoadflows;
	
	private MainApp mainApp;

	private static final Boolean DISABLECOMPARELOADFLOWS = new Boolean(false); 
}
