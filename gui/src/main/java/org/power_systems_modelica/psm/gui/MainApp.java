package org.power_systems_modelica.psm.gui;

import java.io.IOException;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.view.CasesOverviewController;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsDetailController;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsNewController;
import org.power_systems_modelica.psm.gui.view.DdrsOverviewController;
import org.power_systems_modelica.psm.gui.view.MenuLayoutController;
import org.power_systems_modelica.psm.gui.view.WorkflowDetailController;
import org.power_systems_modelica.psm.gui.view.WorkflowNewController;
import org.power_systems_modelica.psm.gui.view.WorkflowStatusController;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.emory.mathcs.backport.java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		MainService mainService = new MainService(this);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Power Systems on Modelica");

		initRootLayout();
		showMenuLayout(mainService);
		showWorkflowView(mainService, null);
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(MainApp.class.getResource("/css/bootstrap3.css").toExternalForm());
			scene.getStylesheets().add(MainApp.class.getResource("/css/MainApp.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FXMLLoader showMenuLayout(MainService mainService) {

		FXMLLoader loader = null;
		try {
			// Load menu layout.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MenuLayout.fxml"));
			AnchorPane menuLayout = (AnchorPane) loader.load();

			// Set menu layout into the top of the root layout.
			rootLayout.setTop(menuLayout);

			MenuLayoutController controller = loader.getController();
			controller.setMainService(mainService);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showCasesOverview(MainService mainService) {

		FXMLLoader loader = null;
		try {
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CasesOverview.fxml"));
			AnchorPane casesOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(casesOverview);

			CasesOverviewController controller = loader.getController();
			controller.setMainService(mainService);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showDdrsOverview(MainService mainService) {

		FXMLLoader loader = null;
		try {
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DdrsOverview.fxml"));
			AnchorPane ddrsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(ddrsOverview);

			DdrsOverviewController controller = loader.getController();
			controller.setMainService(mainService);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showWorkflowView(MainService mainService, Workflow w) {

		if (w == null)
			return showWorkflowNewView(mainService, w);
		else {
			if (!w.getState().equals(ProcessState.SUCCESS) && !w.getState().equals(ProcessState.FAILED))
				return showWorkflowStatusView(mainService, w, true);
			else
				return showWorkflowDetailView(mainService);
		}
	}

	public FXMLLoader showWorkflowNewView(MainService mainService, Workflow w) {

		FXMLLoader loader = null;
		try {
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			WorkflowNewController controller = loader.getController();
			controller.setMainService(mainService);
			controller.setDefaultInit();
			if (w != null)
				controller.setWorkflow(w);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return loader;
	}

	public FXMLLoader showWorkflowDetailView(MainService mainService) {

		FXMLLoader loader = null;
		try {
			if (mainService.getWorkflowTask() != null)
				mainService.resetWorkflowTask();

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			WorkflowDetailController controller = loader.getController();
			controller.setMainService(mainService);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showWorkflowStatusView(MainService mainService, Workflow w, boolean isWorkflowDetail) {

		FXMLLoader loader = null;
		try {
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowStatus.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			WorkflowStatusController controller = loader.getController();
			controller.setMainService(mainService, w, isWorkflowDetail);

			if (isWorkflowDetail)
				controller.setTask(w, mainService.getWorkflowTask());
			else
				controller.setTask(w, mainService.getCompareLoadflowTask());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}

	public FXMLLoader showCompareLoadflowsView(MainService mainService, Workflow w) {
		if (w == null)
			return showCompareLoadflowsNewView(mainService);
		else {
			if (!w.getState().equals(ProcessState.SUCCESS) && !w.getState().equals(ProcessState.FAILED))
				return showWorkflowStatusView(mainService, w, false);
			else
				return showCompareLoadflowsDetailView(mainService);
		}
	}

	public FXMLLoader showCompareLoadflowsDetailView(MainService mainService) {

		FXMLLoader loader = null;
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}

	private FXMLLoader showCompareLoadflowsNewView(MainService mainService) {

		FXMLLoader loader = null;
		try {
			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CompareLoadflowsNew.fxml"));
			AnchorPane compareLoadflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(compareLoadflowsOverview);

			CompareLoadflowsNewController controller = loader.getController();
			controller.setMainService(mainService);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}

	public void showWorkflowWithCase(MainService mainService, Case c) {

		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectWorkflowOption();

		FXMLLoader loader = showWorkflowNewView(mainService, null);
		WorkflowNewController controller = loader.getController();
		controller.setCase(c);
	}

	public void showCompareLoadflowsWithCase(MainService mainService, Case c) {
		FXMLLoader menuLoader = showMenuLayout(mainService);
		((MenuLayoutController) menuLoader.getController()).selectCompareLoadflowsOption();

		FXMLLoader loader = showCompareLoadflowsNewView(mainService);
		CompareLoadflowsNewController controller = loader.getController();
		controller.setCase(c);
	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);
}
