package org.power_systems_modelica.psm.gui.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowListener;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;

public class WorkflowTask extends Task<Void> {

	public WorkflowTask(Workflow w) {
		super();
		this.w = w;

		for (TaskDefinition task : w.getConfiguration().getTaskDefinitions()) {

			observableStates.put(task.getTaskId(),
					new SimpleStringProperty(this, task.getTaskId(), ProcessState.IDLE.toString()));
		}
	}

	public void updateWorkflowStates(List<TaskStatePair> states) {

		if (Platform.isFxApplicationThread()) {
			for (TaskStatePair state : states) {

				StringProperty value = observableStates.get(state.taskId);

				value.set(state.state.toString());
			}
		} else {
			if (statesUpdate.getAndSet(states) == null) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						final List<TaskStatePair> states = statesUpdate.getAndSet(null);
						for (TaskStatePair state : states) {

							StringProperty value = WorkflowTask.this.observableStates.get(state.taskId);

							value.set(state.state.toString());
						}
					}
				});
			}
		}
	}

	public StringProperty workflowStateProperty(String stateId) {

		return observableStates.get(stateId);
	}

	@Override
	protected Void call() throws Exception {

		w.addListener(new WorkflowListener() {

			@Override
			public void onStateChange(List<TaskStatePair> states) {
				int totalTasks = states.size();

				updateWorkflowStates(states);
				int i = 0;
				int currentTask = 0;
				for (TaskStatePair state : states) {

					if (!state.state.equals(ProcessState.SUCCESS) && !state.state.equals(ProcessState.FAILED)
							&& currentTask <= 0)
						currentTask = i;

					i++;
				}
				updateMessage(states.get(currentTask).taskId + " is " + states.get(currentTask).state.toString());
				updateProgress(currentTask + 1, totalTasks);
			}

		});
		w.start();
		updateProgress(0, w.getTaskStates().size());
		updateWorkflowStates(w.getTaskStates());

		return null;
	}

	private AtomicReference<List<TaskStatePair>> statesUpdate = new AtomicReference<>();
	private ObservableMap<String, StringProperty> observableStates = FXCollections.observableHashMap();
	private Workflow w = null;
}
