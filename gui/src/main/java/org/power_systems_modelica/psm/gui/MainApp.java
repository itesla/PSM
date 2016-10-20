package org.power_systems_modelica.psm.gui;

import java.io.IOException;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.service.Workflow;
import org.power_systems_modelica.psm.gui.service.WorkflowService;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.power_systems_modelica.psm.gui.view.CasesOverviewController;
import org.power_systems_modelica.psm.gui.view.DdrsOverviewController;
import org.power_systems_modelica.psm.gui.view.MenuLayoutController;
import org.power_systems_modelica.psm.gui.view.WorkflowDetailController;
import org.power_systems_modelica.psm.gui.view.WorkflowNewController;
import org.power_systems_modelica.psm.gui.view.WorkflowStatusController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Power Systems on Modelica");

		initRootLayout();

		showMenuLayout();
		Workflow w = getWorkflow();
		showWorkflowView(w);
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

	public void showMenuLayout() {
		try {
			// Load menu layout.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MenuLayout.fxml"));
			AnchorPane menuLayout = (AnchorPane) loader.load();

			// Set menu layout into the top of the root layout.
			rootLayout.setTop(menuLayout);

			MenuLayoutController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showCasesOverview() {
		try {
			// Load cases overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CasesOverview.fxml"));
			AnchorPane casesOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(casesOverview);

			CasesOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDdrsOverview() {
		try {
			// Load cases overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DdrsOverview.fxml"));
			AnchorPane ddrsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(ddrsOverview);

			DdrsOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showWorkflowView(Workflow w) {
		if (w == null || w.getName() == null)
			showWorkflowNew();
		else {
			if (w.isRunning())
				showWorkflowStatus();
			else
				showWorkflowDetail();
		}
	}

	public void showWorkflowNew() {
		try {
			// Load cases overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowNew.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			WorkflowNewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showWorkflowDetail() {
		try {
			// Load cases overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			WorkflowDetailController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showWorkflowStatus() {
		try {
			// Load cases overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowStatus.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			// Set cases overview into the center of the root layout.
			rootLayout.setCenter(workflowsOverview);

			WorkflowStatusController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList getCatalogs() {
		return CatalogService.getCatalogs();
	}

	public ObservableList getCases(String catalogName) {
		return CaseService.getCases(catalogName);
	}

	public ObservableList getDdrs(String catalogName) {
		return DdrService.getDdrs(catalogName);
	}

	public Workflow getWorkflow() {
		return WorkflowService.getWorkflow();
	}

	public ObservableList getLoadflowEngines() {
		return WorkflowService.getLoadflowEngines();
	}

	public ObservableList getDsEngines() {
		return WorkflowService.getDsEngines();
	}

	public void startWorkflow(Catalog ctlg, Case cs, Ddr ddr, LoadflowEngine le, boolean onlyMainConnectedComponent,
			ObservableList events, DsEngine dse) {
		Workflow w = WorkflowService.startWorkflow(ctlg, cs, ddr, le, onlyMainConnectedComponent, events, dse);
		showWorkflowView(w);
	}

	public ObservableList<WorkflowResult> getWorkflowResult(String name) {
		// TODO Auto-generated method stub
		return WorkflowService.getWorkflowResult(name);
	}

	public static void main(String[] args) {
		launch(args);
	}

	private Stage primaryStage;
	private BorderPane rootLayout;

	private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

}