package org.power_systems_modelica.psm.gui.service;

import java.io.IOException;
import java.nio.file.Path;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.MainApp.WorkflowType;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import eu.itesla_project.iidm.network.Network;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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

	public ObservableList<Catalog> getCatalogs(String name) {
		return CatalogService.getCatalogs(name);
	}

	public Catalog getCatalog(String name, Path path) throws IOException {
		return CatalogService.getCatalog(name, path);
	}

	public ObservableList<Case> getCases(String catalogName) {
		return CaseService.getCases(CatalogService.getCatalogByName("cases", catalogName));
	}

	public Case getCase(String catalogName, Path casePath) throws IOException {
		return CaseService.getCase(CatalogService.getCatalogByName("cases", catalogName), casePath);
	}

	public ObservableList<ConvertedCase> getConvertedCases(String catalogName) {
		return CaseService.getConvertedCases(CatalogService.getCatalogByName("cases", catalogName));
	}

	public ConvertedCase getConvertedCase(String catalogName, Path path) throws IOException {
		return CaseService.getConvertedCase(CatalogService.getCatalogByName("cases", catalogName), path);
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

	public ObservableList<Ddr> getDdrs(String catalogName) {
		return DdrService.getDdrs(CatalogService.getCatalogByName("ddrs", catalogName));
	}

	public Ddr getDdr(String catalogName, Path path) throws IOException {
		return DdrService.getDdr(CatalogService.getCatalogByName("ddrs", catalogName), path);
	}

	public boolean duplicateDdr(Ddr ddrIn, Ddr ddrOut) {
		return DdrService.duplicateDdr(ddrIn, ddrOut);
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

	public ObservableList<LoadflowEngine> getLoadflowEngines() {
		return WorkflowServiceConfiguration.getLoadflowEngines();
	}

	public ObservableList<DsEngine> getDsEngines() {
		return WorkflowServiceConfiguration.getDsEngines();
	}

	public ObservableList<String> getNetworkElements(ConvertedCase c, String action) {
		return WorkflowServiceConfiguration.getNetworkElements(c, action);
	}

	public ObservableList<String> getActionEvents(ConvertedCase c) {
		return WorkflowServiceConfiguration.getActionEvents(c);
	}

	public void startConversion(Case cs, Ddr ddr, LoadflowEngine le, boolean onlyMainConnectedComponent, DsEngine dse) {

		try {
			Workflow w = WorkflowServiceConfiguration.createConversion(cs, ddr, le, onlyMainConnectedComponent, dse);
			cTask = TaskService.createTask(w, () -> getMainApp().showConversionDetailView(this, true, null));
			getMainApp().showWorkflowStatusView(this, w, WorkflowType.CONVERSION);
			TaskService.startTask(cTask);
			CaseService.saveConvertedCaseProperties(cs.getLocation(), ddr.getLocation(), le.name(), onlyMainConnectedComponent, dse.name());
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startSimulation(ConvertedCase cs, ObservableList<Event> events, DsEngine dse, String stopTime, String stepBySecond, boolean onlyCheck, boolean onlyVerify, boolean createFilteredMat) {

		try {
			Workflow w = WorkflowServiceConfiguration.createSimulation(cs, events, dse, stopTime, stepBySecond, onlyCheck, onlyVerify, createFilteredMat);
			sTask = TaskService.createTask(w, () -> getMainApp().showSimulationDetailView(this, onlyCheck, onlyVerify));
			getMainApp().showWorkflowStatusView(this, w, WorkflowType.SIMULATION);
			TaskService.startTask(sTask);
		} catch (WorkflowCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WorkflowResult getConversionResult(String name) {
		return WorkflowServiceConfiguration.getConversionResult(name);
	}

	public WorkflowResult getSimulationResult(String name) {
		return WorkflowServiceConfiguration.getSimulationResult(name);
	}

	public Workflow getCompareLoadflows() {
		return WorkflowServiceConfiguration.getCompareLoadflow();
	}

	public void startCompareLoadflows(Case cs, boolean generatorsReactiveLimits, boolean helmflowFromHadesResults) {

		try {
			Workflow w = WorkflowServiceConfiguration.createCompareLoadflows(cs, generatorsReactiveLimits, helmflowFromHadesResults);
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

	public void stopConversion(Workflow w)
	{
		((WorkflowService) cTask).cancelTask();	
		resetConversionTask();
		showConversionNewView(w);
	}

	public void stopSimulation(Workflow w)
	{
		((WorkflowService) sTask).cancelTask();
		resetSimulationTask();
		showSimulationNewView(w);
	}

	public void stopCompareLoadflows()
	{
		((WorkflowService) clTask).cancelTask();
		resetCompareLoadflowTask();
		showCompareLoadflowsView(null);
	}

	public Task<?> getConversionTask() {
		return cTask;
	}

	public Task<?> getSimulationTask() {
		return sTask;
	}

	public Task<?> getCompareLoadflowTask() {
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

	private Task<?> cTask = null;
	private Task<?> sTask = null;
	private Task<?> clTask = null;
	
	private Application mainApp;
	private Stage stage;
}
