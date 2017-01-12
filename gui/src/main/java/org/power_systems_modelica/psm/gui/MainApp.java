package org.power_systems_modelica.psm.gui;

import java.io.IOException;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.view.CasesOverviewController;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsDetailController;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsNewController;
import org.power_systems_modelica.psm.gui.view.ConversionDetailController;
import org.power_systems_modelica.psm.gui.view.ConversionNewController;
import org.power_systems_modelica.psm.gui.view.DdrsOverviewController;
import org.power_systems_modelica.psm.gui.view.MenuLayoutController;
import org.power_systems_modelica.psm.gui.view.SimulationCheckVerifyDetailController;
import org.power_systems_modelica.psm.gui.view.SimulationDetailController;
import org.power_systems_modelica.psm.gui.view.SimulationNewController;
import org.power_systems_modelica.psm.gui.view.WorkflowStatusController;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application
{
	public enum WorkflowType
	{
		CONVERSION(0), SIMULATION(1), COMPARELOADFLOW(2);

		private int value;

		private WorkflowType(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	@Override
	public void start(Stage primaryStage)
	{
		MainService mainService = new MainService(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Power Systems on Modelica");

		initRootLayout();
		showMenuLayout(mainService);
		showConversionView(mainService, null);
	}

	public void initRootLayout()
	{
		try
		{
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets()
					.add(MainApp.class.getResource("/css/bootstrap3.css").toExternalForm());
			scene.getStylesheets()
					.add(MainApp.class.getResource("/css/MainApp.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public FXMLLoader showMenuLayout(MainService mainService)
	{
		FXMLLoader loader = null;
		try
		{
			// Load menu layout.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MenuLayout.fxml"));
			AnchorPane menuLayout = (AnchorPane) loader.load();

			// Set menu layout into the top of the root layout.
			rootLayout.setTop(menuLayout);

			MenuLayoutController controller = loader.getController();
			controller.setMainService(mainService);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showCasesOverview(MainService mainService)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CasesOverview.fxml"));
			AnchorPane casesOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(casesOverview);

			CasesOverviewController controller = loader.getController();
			controller.setMainService(mainService);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showDdrsOverview(MainService mainService)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DdrsOverview.fxml"));
			AnchorPane ddrsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(ddrsOverview);

			DdrsOverviewController controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooser());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showConversionView(MainService mainService, Workflow w)
	{
		if (w == null)
			return showConversionNewView(mainService, w);
		else
		{
			if (!w.getState().equals(ProcessState.SUCCESS)
					&& !w.getState().equals(ProcessState.FAILED))
				return showWorkflowStatusView(mainService, w, WorkflowType.CONVERSION);
			else
				return showConversionDetailView(mainService, true);
		}
	}

	public FXMLLoader showConversionNewView(MainService mainService, Workflow w)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConversionNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			ConversionNewController controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooser());
			controller.setDefaultInit();
			if (w != null)
				controller.setWorkflow(w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showConversionDetailView(MainService mainService, boolean loadWorkflow)
	{
		FXMLLoader loader = null;
		try
		{
			if (mainService.getConversionTask() != null)
				mainService.resetConversionTask();

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ConversionDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			ConversionDetailController controller = loader.getController();
			controller.setMainService(mainService);
			if (loadWorkflow)
				controller.setWorkflow(mainService.getConversion());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showSimulationView(MainService mainService, Workflow w)
	{
		if (w == null)
			return showSimulationNewView(mainService, w);
		else
		{
			if (!w.getState().equals(ProcessState.SUCCESS)
					&& !w.getState().equals(ProcessState.FAILED))
				return showWorkflowStatusView(mainService, w, WorkflowType.SIMULATION);
			else
			{

				boolean onlyCheck = false, onlyVerify = false;
				for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
				{

					if (td.getTaskClass().equals(ModelicaSimulatorTask.class))
					{
						int depth = Integer
								.parseInt(td.getTaskConfiguration().getParameter("depth"));
						if (depth == 1)
							onlyCheck = true;
						else if (depth == 2)
							onlyVerify = true;
					}

				}
				return showSimulationDetailView(mainService, onlyCheck, onlyVerify);
			}
		}
	}

	public FXMLLoader showSimulationNewView(MainService mainService, Workflow w)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SimulationNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			SimulationNewController controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooser());
			controller.setDefaultInit();
			if (w != null)
				controller.setWorkflow(w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showSimulationDetailView(MainService mainService, boolean onlyCheck,
			boolean onlyVerify)
	{
		if (onlyCheck || onlyVerify)
			return showSimulationCheckDetailView(mainService, onlyCheck);

		return showSimulationDetailView(mainService);
	}

	public FXMLLoader showSimulationCheckDetailView(MainService mainService, boolean isCheckDetail)
	{
		FXMLLoader loader = null;
		try
		{
			if (mainService.getSimulationTask() != null)
				mainService.resetSimulationTask();

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SimulationCheckVerifyDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			SimulationCheckVerifyDetailController controller = loader.getController();
			controller.setMainService(mainService, mainService.getSimulation(), isCheckDetail);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showSimulationDetailView(MainService mainService)
	{
		FXMLLoader loader = null;
		try
		{
			if (mainService.getSimulationTask() != null)
				mainService.resetSimulationTask();

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SimulationDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			SimulationDetailController controller = loader.getController();
			controller.setMainService(mainService);
			controller.setWorkflow(mainService.getSimulation());
			controller.setFileChooser(new GuiFileChooser());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showWorkflowStatusView(MainService mainService, Workflow w,
			WorkflowType compareloadflow)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowStatus.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			WorkflowStatusController controller = loader.getController();
			controller.setMainService(mainService, w, compareloadflow);

			if (compareloadflow.equals(WorkflowType.CONVERSION))
				controller.setTask(w, mainService.getConversionTask());
			else if (compareloadflow.equals(WorkflowType.SIMULATION))
				controller.setTask(w, mainService.getSimulationTask());
			else
				controller.setTask(w, mainService.getCompareLoadflowTask());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showCompareLoadflowsView(MainService mainService, Workflow w)
	{
		if (w == null)
			return showCompareLoadflowsNewView(mainService);
		else
		{
			if (!w.getState().equals(ProcessState.SUCCESS)
					&& !w.getState().equals(ProcessState.FAILED))
				return showWorkflowStatusView(mainService, w, WorkflowType.COMPARELOADFLOW);
			else
				return showCompareLoadflowsDetailView(mainService);
		}
	}

	public FXMLLoader showCompareLoadflowsDetailView(MainService mainService)
	{
		FXMLLoader loader = null;
		try
		{
			if (mainService.getCompareLoadflowTask() != null)
				mainService.resetCompareLoadflowTask();

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CompareLoadflowsDetail.fxml"));
			AnchorPane compareLoadflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(compareLoadflowsOverview);

			CompareLoadflowsDetailController controller = loader.getController();
			controller.setMainService(mainService);
			controller.setWorkflow(mainService.getCompareLoadflows());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	private FXMLLoader showCompareLoadflowsNewView(MainService mainService)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CompareLoadflowsNew.fxml"));
			AnchorPane compareLoadflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(compareLoadflowsOverview);

			CompareLoadflowsNewController controller = loader.getController();
			controller.setMainService(mainService);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public void showConversionWithCase(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectConversionOption();

		FXMLLoader loader = showConversionNewView(mainService, null);
		ConversionNewController controller = loader.getController();
		controller.setCase(c);
	}

	public void showConversionResult(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectConversionOption();

		FXMLLoader loader = showConversionDetailView(mainService, false);
		ConversionDetailController controller = loader.getController();
		controller.setConversionResult(c);
	}

	public void showSimulationWithCase(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectSimulationOption();

		FXMLLoader loader = showSimulationNewView(mainService, null);
		SimulationNewController controller = loader.getController();
		controller.setCase(c);
	}

	public void showCompareLoadflowsWithCase(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectCompareLoadflowsOption();

		FXMLLoader loader = showCompareLoadflowsNewView(mainService);
		CompareLoadflowsNewController controller = loader.getController();
		controller.setCase(c);
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	private Stage		primaryStage;
	private BorderPane	rootLayout;
}
