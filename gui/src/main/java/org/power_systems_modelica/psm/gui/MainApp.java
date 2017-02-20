package org.power_systems_modelica.psm.gui;

import java.io.IOException;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsNewController;
import org.power_systems_modelica.psm.gui.view.ConversionDetailController;
import org.power_systems_modelica.psm.gui.view.ConversionNewController;
import org.power_systems_modelica.psm.gui.view.MainLayoutController;
import org.power_systems_modelica.psm.gui.view.MenuLayoutController;
import org.power_systems_modelica.psm.gui.view.SimulationDetailController;
import org.power_systems_modelica.psm.gui.view.SimulationNewController;
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
		initMainLayout(mainService);
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

	public void initMainLayout(MainService mainService)
	{
		try
		{
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainLayout.fxml"));
			mainLayout = (AnchorPane) loader.load();

			rootLayout.setCenter(mainLayout);

			mainLayoutController = loader.getController();
			mainLayoutController.setMainService(mainService);
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

	public FXMLLoader showCasesOverview()
	{
		return this.getFXMLLoader("view/CasesOverview.fxml", null);
	}

	public FXMLLoader showDdrsOverview()
	{
		return this.getFXMLLoader("view/DdrsOverview.fxml", null);
	}

	public FXMLLoader showConversionView(MainService mainService, Workflow w)
	{
		if (w == null)
			return showConversionNewView(w);
		else
		{
			if (!w.getState().equals(ProcessState.SUCCESS)
					&& !w.getState().equals(ProcessState.FAILED))
				return showWorkflowStatusView(mainService, w, WorkflowType.CONVERSION);
			else
				return showConversionDetailView(mainService, true, null);
		}
	}

	public FXMLLoader showConversionNewView(Workflow w)
	{
		return this.getFXMLLoader("view/ConversionNew.fxml", w);
	}

	public FXMLLoader showConversionDetailView(MainService mainService, boolean loadWorkflow,
			Case c)
	{
		if (mainService.getConversionTask() != null)
			mainService.resetConversionTask();

		Workflow w = null;
		if (loadWorkflow)
			w = WorkflowServiceConfiguration.getConversion();

		FXMLLoader loader = this.getFXMLLoader("view/ConversionDetail.fxml", w);
		if (c != null)
		{
			ConversionDetailController controller = loader.getController();
			controller.setCase(c);
		}
		return loader;
	}

	public FXMLLoader showSimulationView(MainService mainService, Workflow w)
	{
		if (w == null)
			return showSimulationNewView(w);
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
				return showSimulationDetailView(mainService, w, onlyCheck, onlyVerify);
			}
		}
	}

	public FXMLLoader showSimulationNewView(Workflow w)
	{
		return this.getFXMLLoader("view/SimulationNew.fxml", w);
	}

	public FXMLLoader showSimulationDetailView(MainService mainService, Workflow w, boolean onlyCheck,
			boolean onlyVerify)
	{
		FXMLLoader subLoader = null;
		if (onlyCheck || onlyVerify || w.getState().equals(ProcessState.FAILED))
			subLoader = showSimulationCheckDetailView(mainService);
		else
			subLoader = showSimulationCurveDetailView(mainService);

		return showSimulationDetailView(mainService, subLoader, onlyCheck);
	}

	public FXMLLoader showSimulationCheckDetailView(MainService mainService)
	{
		return this.prepareFXMLLoader("view/SimulationCheckVerifyDetail.fxml",null);
	}

	public FXMLLoader showSimulationCurveDetailView(MainService mainService)
	{
		return this.prepareFXMLLoader("view/SimulationCurveDetail.fxml", null);
	}

	public FXMLLoader showSimulationDetailView(MainService mainService, FXMLLoader subLoader, boolean onlyCheck)
	{
		if (mainService.getSimulationTask() != null)
			mainService.resetSimulationTask();

		FXMLLoader loader = this.getFXMLLoader("view/SimulationDetail.fxml", WorkflowServiceConfiguration.getSimulation());
		
		SimulationDetailController controller = loader.getController();
		controller.addController(subLoader.getController());
		controller.addNode(subLoader.getRoot());
		controller.setWorkflow(WorkflowServiceConfiguration.getSimulation(), onlyCheck);
		
		return loader;
	}

	public FXMLLoader showWorkflowStatusView(MainService mainService, Workflow w,
			WorkflowType compareloadflow)
	{
		return this.getFXMLLoader("view/WorkflowStatus.fxml", w, compareloadflow);
	}

	public FXMLLoader showCompareLoadflowsView(MainService mainService, Workflow w)
	{
		if (w == null)
			return showCompareLoadflowsNewView();
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
		if (mainService.getCompareLoadflowTask() != null)
			mainService.resetCompareLoadflowTask();

		return this.getFXMLLoader("view/CompareLoadflowsDetail.fxml",
				WorkflowServiceConfiguration.getCompareLoadflow());
	}

	private FXMLLoader showCompareLoadflowsNewView()
	{
		return this.getFXMLLoader("view/CompareLoadflowsNew.fxml", null);
	}

	public FXMLLoader showSwtoswValidationView()
	{
		return this.getFXMLLoader("view/SwtoswValidation.fxml", null);
	}

	public void showSwtoswValidationResults(Workflow w)
	{
		mainLayoutController.setWorkflow(w);
	}

	public void showConversionWithCase(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectConversionOption();

		FXMLLoader loader = showConversionNewView(null);
		ConversionNewController controller = loader.getController();
		controller.setCase(c);
	}

	public void showConversionResult(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectConversionOption();

		showConversionDetailView(mainService, false, c);
	}

	public void showSimulationWithCase(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectSimulationOption();

		FXMLLoader loader = showSimulationNewView(null);
		SimulationNewController controller = loader.getController();
		controller.setCase(c);
	}

	public void showCompareLoadflowsWithCase(MainService mainService, Case c)
	{
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectCompareLoadflowsOption();

		FXMLLoader loader = showCompareLoadflowsNewView();
		CompareLoadflowsNewController controller = loader.getController();
		controller.setCase(c);
	}

	public FXMLLoader prepareFXMLLoader(String viewResource, Workflow w, Object... objects)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(viewResource));
			AnchorPane view = (AnchorPane) loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader getFXMLLoader(String viewResource, Workflow w, Object... objects)
	{
		FXMLLoader loader = null;
		try
		{
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(viewResource));
			AnchorPane view = (AnchorPane) loader.load();

			mainLayoutController.setController(loader.getController());
			mainLayoutController.setFileChooser(fileChooser);
			mainLayoutController.setDefaultInit();

			if (w != null)
				mainLayoutController.setWorkflow(w, objects);

			mainLayoutController.setLayout(view);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return loader;
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	private Stage					primaryStage;
	private AnchorPane				mainLayout;
	private MainLayoutController	mainLayoutController;
	private BorderPane				rootLayout;

	private final GuiFileChooser	fileChooser	= new GuiFileChooser();
}
