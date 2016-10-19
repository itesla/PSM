package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.service.Workflow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuLayoutController {

	@FXML
	private void handleCasesOverview() {
		cases.getStyleClass().add("active");
		ddr.getStyleClass().remove("active");
		workflows.getStyleClass().remove("active");
		
		mainApp.showCasesOverview();
	}
	
	@FXML
	private void handleDdrOverview() {
		cases.getStyleClass().remove("active");
		ddr.getStyleClass().add("active");
		workflows.getStyleClass().remove("active");
		
		mainApp.showDdrsOverview();
	}

	@FXML
	private void handleWorkflowsOverview() {
		cases.getStyleClass().remove("active");
		ddr.getStyleClass().remove("active");
		workflows.getStyleClass().add("active");
		
		Workflow w = mainApp.getWorkflow();
		mainApp.showWorkflowView(w);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		
	}

	@FXML
	private Button cases;
	@FXML
	private Button ddr;
	@FXML
	private Button workflows;
	
	private MainApp mainApp;
}
