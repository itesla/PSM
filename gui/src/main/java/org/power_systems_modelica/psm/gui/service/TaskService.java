package org.power_systems_modelica.psm.gui.service;

import java.util.List;

import org.power_systems_modelica.psm.gui.MainApp.InitCompletionHandler;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

public class TaskService {

	public static Task createTask(Workflow w, InitCompletionHandler initCompletionHandler) {

		WorkflowTask task = new WorkflowTask(w);

		task.stateProperty().addListener((observableValue, oldState, newState) -> {

			if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED)
				initCompletionHandler.complete();
		});

		return task;
	}

	public static void startTask(Task task) {

		new Thread(task).start();
	}

	private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
}
