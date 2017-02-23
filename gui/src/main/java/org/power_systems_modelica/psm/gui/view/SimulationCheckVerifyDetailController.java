package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SimulationCheckVerifyDetailController implements SimulationResultDetailController
{

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		boolean isCheckDetail = false;
		boolean isVerifyDetail = false;
		if (objects.length > 0)
		{
			isCheckDetail = (boolean)objects[0];
			isVerifyDetail = (boolean)objects[1];
		}
		
		String caseLabel = "";
		for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
		{
			if (td.getTaskClass().equals(ModelicaParserTask.class))
			{
				String moInput = td.getTaskConfiguration().getParameter("source");

				java.nio.file.Path casePath;
				if (moInput.endsWith(".mo"))
				{
					java.nio.file.Path path = Paths.get(moInput);
					casePath = path.getParent();
				}
				else
					casePath = Paths.get(moInput);

				java.nio.file.Path catalogPath = casePath.getParent();

				try
				{
					Catalog catalog = CatalogService.getCatalog("cases", catalogPath);
					Case c = CaseService.getCase(catalog.getName(), casePath);
					caseLabel = catalog.getName() + " - " + c.getName();
				}
				catch (IOException e)
				{
					caseLabel = "";
				}
			}
		}
		if (w.getState().equals(ProcessState.FAILED))
		{
			resultIcon.setImage(koImage);
			if (isVerifyDetail)
				resultText.setText("Simulation verification failed. See logs tab for more details.");
			else if (isCheckDetail)
				resultText.setText("Check model failed. See logs tab for more details.");
			else
				resultText.setText("Simulation failed. See logs tab for more details.");
		}
		else
		{
			resultIcon.setImage(okImage);
			if (isCheckDetail)
				resultText.setText("Check of " + caseLabel + " completed successfully");
			else
				resultText.setText("Simulation verification of " + caseLabel + " completed successfully");
		}
	}

	@FXML
	private void initialize()
	{
	}

	@FXML
	private AnchorPane			resultView;
	@FXML
	private ImageView			resultIcon;
	@FXML
	private Label				resultText;

	private Image				okImage	= new Image(
			getClass().getResourceAsStream("/img/hook.png"));
	private Image				koImage	= new Image(
			getClass().getResourceAsStream("/img/false.png"));

	private static final Logger	LOG		= LoggerFactory
			.getLogger(SimulationCheckVerifyDetailController.class);

}
