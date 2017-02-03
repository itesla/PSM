package org.power_systems_modelica.psm.gui.service;

import org.power_systems_modelica.psm.gui.utils.InitCompletionHandler;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.concurrent.Task;

public class TaskService
{
	public static Task<?> createTask(Workflow w, InitCompletionHandler initCompletionHandler)
	{
		WorkflowService task = new WorkflowService(w);
		task.stateProperty().addListener((observableValue, oldState, newState) -> {
			if (w.getState().equals(ProcessState.SUCCESS) || w.getState().equals(ProcessState.FAILED)) {
				initCompletionHandler.complete();
			}
		});
		return task;
	}

	public static void startTask(Task<?> task)
	{
		Thread t = new Thread(task);
		t.start();
	}
}
