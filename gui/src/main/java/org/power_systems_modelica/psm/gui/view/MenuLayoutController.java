package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MenuLayoutController
{

	@FXML
	private void initialize()
	{
		compareLoadflows.setVisible(!DISABLECOMPARELOADFLOWS);
		compareLoadflows.setDisable(DISABLECOMPARELOADFLOWS);
	}

	@FXML
	private void handleCasesOverview()
	{
		cases.getStyleClass().add("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");

		mainService.showCasesOverview();
	}

	@FXML
	private void handleDdrsOverview()
	{
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().add("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");

		mainService.showDdrsOverview();
	}

	@FXML
	private void handleConversionOverview()
	{
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().add("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");

		Workflow w = mainService.getConversion();
		mainService.showConversionView(w);
	}

	@FXML
	private void handleSimulationOverview()
	{
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().add("active");
		compareLoadflows.getStyleClass().remove("active");

		Workflow w = mainService.getSimulation();
		mainService.showSimulationView(w);
	}

	@FXML
	private void handleCompareLoadflowsOverview()
	{
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().add("active");

		Workflow w = mainService.getCompareLoadflows();
		mainService.showCompareLoadflowsView(w);
	}

	private void selectOption(Button b)
	{
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");

		b.getStyleClass().add("active");
	}

	public void selectConversionOption()
	{
		selectOption(conversion);
	}

	public void selectSimulationOption()
	{
		selectOption(simulation);
	}

	public void selectCompareLoadflowsOption()
	{
		selectOption(compareLoadflows);
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}

	@FXML
	private HBox					buttonBar;
	@FXML
	private Button					cases;
	@FXML
	private Button					ddrs;
	@FXML
	private Button					conversion;
	@FXML
	private Button					simulation;
	@FXML
	private Button					compareLoadflows;

	private MainService				mainService;

	private static final Boolean	DISABLECOMPARELOADFLOWS	= new Boolean(false);
}
