package org.power_systems_modelica.psm.gui.service;

import java.io.IOException;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.MainApp.WorkflowType;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
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
import org.power_systems_modelica.psm.gui.view.SimulationDetailController;
import org.power_systems_modelica.psm.gui.view.ConversionNewController;
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

	public void showConversionWithCase(Case c) {
		getMainApp().showConversionWithCase(this, c);
	}

	public void showConversionResult(Case c) {
		getMainApp().showConversionResult(this, c);
	}

	public void showSimulationWithCase(Case c) {
		getMainApp().showSimulationWithCase(this, c);
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

	public void showConversionView(Workflow w) {
		getMainApp().showConversionView(this, w);
	}

	public void showConversionNewView(Workflow w) {
		getMainApp().showConversionNewView(this, w);
	}

	public void showSimulationView(Workflow w) {
		getMainApp().showSimulationView(this, w);
	}

	public void showSimulationNewView(Workflow w) {
		getMainApp().showSimulationNewView(this, w);
	}

	public ObservableList getCatalogs(String name) {
		return CatalogService.getCatalogs(name);
	}

	public ObservableList getCases(String catalogName) {
		return CaseService.getCases(CatalogService.getCatalogByName("cases", catalogName));
	}

	public ObservableList<ConvertedCase> getConvertedCases(String catalogName) {
		return CaseService.getConvertedCases(CatalogService.getCatalogByName("cases", catalogName));
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

	public Workflow getConversion() {
		return WorkflowServiceConfiguration.getConversion();
	}

	public Workflow getSimulation() {
		return WorkflowServiceConfiguration.getSimulation();
	}

	public ObservableList getLoadflowEngines() {
		return WorkflowServiceConfiguration.getLoadflowEngines();
	}

	public ObservableList getDsEngines() {
		return WorkflowServiceConfiguration.getDsEngines();
	}

	public ObservableList getNetworkElements(ConvertedCase c, String action) {
		return WorkflowServiceConfiguration.getNetworkElements(c, action);
	}

	public ObservableList getActionEvents(ConvertedCase c) {
		return WorkflowServiceConfiguration.getActionEvents(c);
	}

	public void startConversion(Case cs, Ddr ddr, LoadflowEngine le, boolean onlyMainConnectedComponent) {

		try {
			Workflow w = WorkflowServiceConfiguration.createConversion(cs, ddr, le, onlyMainConnectedComponent);
			cTask = TaskService.createTask(w, () -> getMainApp().showConversionDetailView(this, true));
			getMainApp().showWorkflowStatusView(this, w, WorkflowType.CONVERSION);
			TaskService.startTask(cTask);
			CaseService.saveConvertedCaseProperties(cs.getLocation(), ddr.getLocation());
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startSimulation(ConvertedCase cs, ObservableList events, DsEngine dse, String stopTime) {

		try {
			Workflow w = WorkflowServiceConfiguration.createSimulation(cs, events, dse, stopTime);
			sTask = TaskService.createTask(w, () -> getMainApp().showSimulationDetailView(this));
			getMainApp().showWorkflowStatusView(this, w, WorkflowType.SIMULATION);
			TaskService.startTask(sTask);
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WorkflowResult getSimulationResult(String name) {
		return WorkflowServiceConfiguration.getSimulationResult(name);
	}

	public Workflow getCompareLoadflows() {
		return WorkflowServiceConfiguration.getCompareLoadflow();
	}

	public void startCompareLoadflows(Case cs, boolean generatorsReactiveLimits) {

		try {
			Workflow w = WorkflowServiceConfiguration.createCompareLoadflows(cs, generatorsReactiveLimits);
			clTask = TaskService.createTask(w, () -> getMainApp().showCompareLoadflowsDetailView(this));
			getMainApp().showWorkflowStatusView(this, w, WorkflowType.COMPARELOADFLOW);
			TaskService.startTask(clTask);
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WorkflowResult getCompareLoadflowsResult(String name) {
		return WorkflowServiceConfiguration.getCompareLoadflowsResult(name);
	}

	public Task getConversionTask() {
		return cTask;
	}

	public Task getSimulationTask() {
		return sTask;
	}

	public Task getCompareLoadflowTask() {
		return clTask;
	}

	public void resetConversionTask() {
		cTask = null;
	}

	public void resetSimulationTask() {
		sTask = null;
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

	private Task cTask = null;
	private Task sTask = null;
	private Task clTask = null;
	
	private Application mainApp;
	private Stage stage;
}
