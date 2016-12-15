package org.power_systems_modelica.psm.gui.service;

import java.io.IOException;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
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
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import eu.itesla_project.iidm.network.Network;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainService {

	public MainService(Application mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void showWorkflowWithCase(Case c) {
		getMainApp().showWorkflowWithCase(this, c);
	}

	public void showCompareLoadflowsWithCase(Case c) {
		getMainApp().showCompareLoadflowsWithCase(this, c);
	}

	public void showCompareLoadflowsView(Workflow w) {
		getMainApp().showCompareLoadflowsView(this, w);
	}

	public void showCasesOverview() {
		getMainApp().showCasesOverview(this);
	}

	public void showDdrsOverview() {
		getMainApp().showDdrsOverview(this);
	}

	public void showWorkflowView(Workflow w) {
		getMainApp().showWorkflowView(this, w);
	}

	public void showWorkflowNewView(Workflow w) {
		getMainApp().showWorkflowNewView(this, w);
	}

	public ObservableList getCatalogs(String name) {
		return CatalogService.getCatalogs(name);
	}

	public ObservableList getCases(String catalogName) {
		return CaseService.getCases(CatalogService.getCatalogByName("cases", catalogName));
	}

	public Network getCaseSummary(Case input) {

		Network n = null;
		try {
			n = CaseService.importCase(input);
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;
	}

	public String getDefaultDdrLocation(Case c) {
		return CaseService.getDefaultDdrLocation(c);
	}

	public ObservableList getDdrs(String catalogName) {
		return DdrService.getDdrs(CatalogService.getCatalogByName("ddrs", catalogName));
	}

	public ObservableList<EventParamGui> getEventParams(String event) {
		return WorkflowServiceConfiguration.getEventParams(event);
	}

	public Workflow getWorkflow() {
		return WorkflowServiceConfiguration.getWorkflow();
	}

	public ObservableList getLoadflowEngines() {
		return WorkflowServiceConfiguration.getLoadflowEngines();
	}

	public ObservableList getDsEngines() {
		return WorkflowServiceConfiguration.getDsEngines();
	}

	public ObservableList getActionEvents(Ddr ddr) {
		return WorkflowServiceConfiguration.getActionEvents(ddr);
	}

	public void startWorkflow(Case cs, Ddr ddr, LoadflowEngine le, boolean onlyMainConnectedComponent,
			ObservableList events, DsEngine dse, String stopTime) {

		try {
			Workflow w = WorkflowServiceConfiguration.createWorkflow(cs, ddr, le, onlyMainConnectedComponent, events, dse, stopTime);
			wTask = TaskService.createTask(w, () -> getMainApp().showWorkflowDetailView(this));
			getMainApp().showWorkflowStatusView(this, w, true);
			TaskService.startTask(wTask);
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WorkflowResult getWorkflowResult(String name) {
		return WorkflowServiceConfiguration.getWorkflowResult(name);
	}

	public Workflow getCompareLoadflows() {
		return WorkflowServiceConfiguration.getCompareLoadflow();
	}

	public void startCompareLoadflows(Case cs, boolean generatorsReactiveLimits) {

		try {
			Workflow w = WorkflowServiceConfiguration.createCompareLoadflows(cs, generatorsReactiveLimits);
			clTask = TaskService.createTask(w, () -> getMainApp().showCompareLoadflowsDetailView(this));
			getMainApp().showWorkflowStatusView(this, w, false);
			TaskService.startTask(clTask);
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WorkflowResult getCompareLoadflowsResult(String name) {
		return WorkflowServiceConfiguration.getCompareLoadflowsResult(name);
	}

	public Task getWorkflowTask() {
		return wTask;
	}

	public Task getCompareLoadflowTask() {
		return clTask;
	}

	public void resetWorkflowTask() {
		wTask = null;
	}

	public void resetCompareLoadflowTask() {
		clTask = null;
	}
	
	public Stage getPrimaryStage() {
		if (mainApp != null)
			return getMainApp().getPrimaryStage();
		
		return stage;
	}

	public MainApp getMainApp() {
		return (MainApp) mainApp;
	}

	private Task wTask = null;
	private Task clTask = null;
	
	private Application mainApp;
	private Stage stage;
}
