package org.power_systems_modelica.psm.gui.service;

import java.util.List;

import org.power_systems_modelica.psm.gui.MainApp.InitCompletionHandler;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.concurrent.Task;
import javafx.concurrent.Worker;

public class TaskService {

	public static Task createTask(Workflow w, InitCompletionHandler initCompletionHandler) {

		Task task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				w.addListener(new WorkflowListener() {

					@Override
					public void onStateChange(List<TaskStatePair> states) {
						int totalTasks = states.size();

						int i = 0;
						int currentTask = 0;
						for (TaskStatePair state : states) {
														
							System.out.println(states.get(i).taskId + " is " + states.get(i).state.toString());
							if (!state.state.equals(ProcessState.SUCCESS) && 
									!state.state.equals(ProcessState.FAILED) &&
									currentTask <= 0)
								currentTask = i;

							i++;
						}
						updateMessage(states.get(currentTask).taskId + " is " + states.get(currentTask).state.toString());
						updateProgress(currentTask+1, totalTasks);
					}

				});
				w.start();
				updateProgress(0, w.getTaskStates().size());

				return null;
			}
		};
		
		task.stateProperty().addListener((observableValue, oldState, newState) -> {
			System.out.println("oldState: " + oldState.toString() + " to newState: " + newState.toString());
			if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED) 
				initCompletionHandler.complete();
		});
		
		return task;
	}

	public static void startTask(Task task) {

		LOG.info("startWorkflow Thread start");
		new Thread(task).start();
	}

	private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
}
