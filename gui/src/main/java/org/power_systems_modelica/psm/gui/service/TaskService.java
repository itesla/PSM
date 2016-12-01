package org.power_systems_modelica.psm.gui.service;

import org.power_systems_modelica.psm.gui.utils.InitCompletionHandler;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.concurrent.Task;
import javafx.concurrent.Worker;

public class TaskService {

	public static Task createTask(Workflow w, InitCompletionHandler initCompletionHandler) {

		WorkflowService task = new WorkflowService(w);

		task.stateProperty().addListener((observableValue, oldState, newState) -> {

			if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED)
				initCompletionHandler.complete();
		});

		return task;
	}

	public static void startTask(Task task) {

		Thread t = new Thread(task);
		t.start();
	}

	private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
}
