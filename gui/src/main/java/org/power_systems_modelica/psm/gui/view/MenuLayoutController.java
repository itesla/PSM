package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.service.MainService;
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
		
		mainService.showCasesOverview();
	}
	
	@FXML
	private void handleDdrsOverview() {
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().add("active");
		workflow.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");
		
		mainService.showDdrsOverview();
	}

	@FXML
	private void handleWorkflowOverview() {
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		workflow.getStyleClass().add("active");
		compareLoadflows.getStyleClass().remove("active");
		
		Workflow w = mainService.getWorkflow();
		mainService.showWorkflowView(w);
	}

	@FXML
	private void handleCompareLoadflowsOverview() {
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		workflow.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().add("active");
		
		Workflow w = mainService.getCompareLoadflows();
		mainService.showCompareLoadflowsView(w);
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

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	@FXML
	private Button cases;
	@FXML
	private Button ddrs;
	@FXML
	private Button workflow;
	@FXML
	private Button compareLoadflows;
	
	private MainService mainService;

	private static final Boolean DISABLECOMPARELOADFLOWS = new Boolean(false); 
}
