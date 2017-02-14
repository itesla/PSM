package org.power_systems_modelica.psm.gui.service.fx;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public class MainService
{

	public MainService(Application mainApp)
	{
		this.mainApp = mainApp;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public void showConversionWithCase(Case c)
	{
		getMainApp().showConversionWithCase(this, c);
	}

	public void showConversionResult(Case c)
	{
		getMainApp().showConversionResult(this, c);
	}

	public void showSimulationWithCase(Case c)
	{
		getMainApp().showSimulationWithCase(this, c);
	}

	public void showCompareLoadflowsWithCase(Case c)
	{
		getMainApp().showCompareLoadflowsWithCase(this, c);
	}

	public void showCompareLoadflowsView(Workflow w)
	{
		getMainApp().showCompareLoadflowsView(this, w);
	}

	public void showCasesOverview()
	{
		getMainApp().showCasesOverview();
	}

	public void showDdrsOverview()
	{
		getMainApp().showDdrsOverview();
	}

	public void showConversionView(Workflow w)
	{
		getMainApp().showConversionView(this, w);
	}

	public void showConversionNewView(Workflow w)
	{
		getMainApp().showConversionNewView(w);
	}

	public void showSimulationView(Workflow w)
	{
		getMainApp().showSimulationView(this, w);
	}

	public void showSimulationNewView(Workflow w)
	{
		getMainApp().showSimulationNewView(w);
	}

	public void showSwtoswValidationView()
	{
		getMainApp().showSwtoswValidationView();
	}

	public void setConversionTask(Task<?> task)
	{
		cTask = task;
	}

	public Task<?> getConversionTask()
	{
		return cTask;
	}

	public void setSimulationTask(Task<?> task)
	{
		sTask = task;
	}

	public Task<?> getSimulationTask()
	{
		return sTask;
	}

	public void setCompareLoadflowTask(Task<?> task)
	{
		clTask = task;
	}

	public Task<?> getCompareLoadflowTask()
	{
		return clTask;
	}

	public void setSwtoswValidationTask(Task<?> task)
	{
		stsTask = task;
	}

	public Task<?> getSwtoswValidationTask()
	{
		return stsTask;
	}

	public void resetConversionTask()
	{
		cTask = null;
	}

	public void resetSimulationTask()
	{
		sTask = null;
	}

	public void resetCompareLoadflowTask()
	{
		clTask = null;
	}

	public void resetSwtoswValidationTask()
	{
		stsTask = null;
	}

	public Stage getPrimaryStage()
	{
		if (mainApp != null)
			return getMainApp().getPrimaryStage();

		return stage;
	}

	public MainApp getMainApp()
	{
		return (MainApp) mainApp;
	}

	private Task<?>		cTask	= null;
	private Task<?>		sTask	= null;
	private Task<?>		clTask	= null;
	private Task<?>		stsTask	= null;

	private Application	mainApp;
	private Stage		stage;
}
