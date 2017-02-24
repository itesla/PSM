package org.power_systems_modelica.psm.gui.view;

import java.util.Properties;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.workflow.Workflow;

import org.power_systems_modelica.psm.commons.Version;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MenuLayoutController
{

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

	public void selectSwtoswValidationOption()
	{
		selectOption(swtoswValidation);
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
	}

	@FXML
	private void initialize()
	{
		try
		{
			String projectVersion = Version.VERSION.getProjectVersion();
			long buildTimeStamp = Version.VERSION.getBuildTimestamp();
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

			versionLabel.setText(
					"Version " + projectVersion + " build " + formatter.print(buildTimeStamp));
		}
		catch (Throwable e)
		{
		}

		Properties p = PathUtils.getGUIProperties();
		DISABLECOMPARELOADFLOWS = Boolean.valueOf(p.getProperty("menu.disableCompareLoadflows"));
		DISABLESWTOSWVALIDATION = Boolean.valueOf(p.getProperty("menu.disableSwtoswValidation"));

		if (DISABLECOMPARELOADFLOWS)
			buttonBar.getChildren().remove(compareLoadflowsBox);
		if (DISABLESWTOSWVALIDATION)
			buttonBar.getChildren().remove(swtoswValidationBox);
	}

	@FXML
	private void handleCasesOverview()
	{
		cases.getStyleClass().add("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");
		swtoswValidation.getStyleClass().remove("active");

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
		swtoswValidation.getStyleClass().remove("active");

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
		swtoswValidation.getStyleClass().remove("active");

		Workflow w = WorkflowServiceConfiguration.getConversion();
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
		swtoswValidation.getStyleClass().remove("active");

		Workflow w = WorkflowServiceConfiguration.getSimulation();
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
		swtoswValidation.getStyleClass().remove("active");

		Workflow w = WorkflowServiceConfiguration.getCompareLoadflow();
		mainService.showCompareLoadflowsView(w);
	}

	@FXML
	private void handleSwtoswValidationOverview()
	{
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");
		swtoswValidation.getStyleClass().add("active");

		mainService.showSwtoswValidationView();
	}

	private void selectOption(Button b)
	{
		cases.getStyleClass().remove("active");
		ddrs.getStyleClass().remove("active");
		conversion.getStyleClass().remove("active");
		simulation.getStyleClass().remove("active");
		compareLoadflows.getStyleClass().remove("active");
		swtoswValidation.getStyleClass().remove("active");

		b.getStyleClass().add("active");
	}

	@FXML
	private Label		versionLabel;

	@FXML
	private HBox		buttonBar;
	@FXML
	private AnchorPane	compareLoadflowsBox;
	@FXML
	private AnchorPane	swtoswValidationBox;

	@FXML
	private Button		cases;
	@FXML
	private Button		ddrs;
	@FXML
	private Button		conversion;
	@FXML
	private Button		simulation;
	@FXML
	private Button		compareLoadflows;
	@FXML
	private Button		swtoswValidation;

	private MainService	mainService;

	private Boolean		DISABLECOMPARELOADFLOWS	= new Boolean(false);
	private Boolean		DISABLESWTOSWVALIDATION	= new Boolean(false);
}
