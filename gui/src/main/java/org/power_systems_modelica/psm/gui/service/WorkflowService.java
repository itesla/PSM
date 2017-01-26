package org.power_systems_modelica.psm.gui.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.power_systems_modelica.psm.gui.utils.ProgressData;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskProgress;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowListener;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class WorkflowService extends Task<Void>
{
	public WorkflowService(Workflow w)
	{
		super();
		this.w = w;
		for (WorkflowTask task : w.getWorkflowTasks())
		{
			ProgressData data = new ProgressData(task.getId(), task.getName(), task.getState());
			observableProgress.add(data);
		}
	}
	
	public Workflow getWorkflow()
	{
		return w;
	}

	public void updateWorkflowStates(List<TaskStatePair> states)
	{
		if (Platform.isFxApplicationThread())
		{
			for (TaskStatePair state : states)
			{
				ProgressData taskData = (ProgressData) observableProgress
						.filtered(d -> ((ProgressData) d).getTaskId().equals(state.taskId)).get(0);
				int taskIndex = observableProgress.indexOf(taskData);
				taskData.updateState(state.state);
				observableProgress.set(taskIndex, taskData);
			}
		}
		else
		{
			if (statesUpdate.getAndSet(states) == null)
			{
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						final List<TaskStatePair> states = statesUpdate.getAndSet(null);
						for (TaskStatePair state : states)
						{
							ProgressData taskData = (ProgressData) WorkflowService.this.observableProgress
									.filtered(d -> ((ProgressData) d).getTaskId()
											.equals(state.taskId))
									.get(0);
							int taskIndex = WorkflowService.this.observableProgress
									.indexOf(taskData);
							taskData.updateState(state.state);
							WorkflowService.this.observableProgress.set(taskIndex, taskData);
						}
					}
				});
			}
		}
	}

	public void updateWorkflowInfo(TaskProgress info)
	{
		if (Platform.isFxApplicationThread())
		{
			ProgressData taskData = (ProgressData) observableProgress
					.filtered(d -> ((ProgressData) d).getTaskId().equals(info.taskId)).get(0);
			int taskIndex = observableProgress.indexOf(taskData);
			ProgressData infoData = new ProgressData(info.info);
			if (!taskData.getChildren().contains(infoData))
				taskData.getChildren().add(infoData);
			observableProgress.set(taskIndex, taskData);
		}
		else
		{
			List<TaskProgress> list = new ArrayList<>();
			list.add(info);
			if (infosUpdate.getAndAccumulate(list,
					(List<TaskProgress> l1, List<TaskProgress> l2) -> {
						if (l1 == null)
							return l2;
						else
						{
							l1.addAll(l2);
							return l1;
						}
					}) == null)
			{
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						final List<TaskProgress> infos = infosUpdate.getAndSet(null);
						for (TaskProgress info : infos)
						{
							ProgressData taskData = (ProgressData) WorkflowService.this.observableProgress
									.filtered(
											d -> ((ProgressData) d).getTaskId().equals(info.taskId))
									.get(0);
							int taskIndex = WorkflowService.this.observableProgress
									.indexOf(taskData);
							ProgressData infoData = new ProgressData(info.info);
							if (!taskData.getChildren().contains(infoData))
								taskData.getChildren().add(infoData);
							WorkflowService.this.observableProgress.set(taskIndex, taskData);
						}
					}
				});
			}
		}
	}

	public ObservableList<ProgressData> getWorkflowInfo()
	{
		return observableProgress;
	}

	@Override
	protected Void call() throws Exception
	{
		w.addListener(new WorkflowListener()
		{
			@Override
			public void onStateChange(List<TaskStatePair> states)
			{
				int totalTasks = states.size();

				updateWorkflowStates(states);
				int i = 0;
				int currentTask = 0;
				for (TaskStatePair state : states)
				{
					if (!state.state.equals(ProcessState.SUCCESS)
							&& !state.state.equals(ProcessState.FAILED)
							&& currentTask <= 0)
						currentTask = i;
					i++;
				}
				updateMessage(states.get(currentTask).taskId + " is "
						+ states.get(currentTask).state.toString());
				updateProgress(currentTask, totalTasks);
			}

			@Override
			public void onProgress(TaskProgress info)
			{
				updateWorkflowInfo(info);
			}
		});
		w.start();
		updateProgress(0, w.getTaskStates().size());
		updateWorkflowStates(w.getTaskStates());

		return null;
	}

	private AtomicReference<List<TaskStatePair>>	statesUpdate		= new AtomicReference<>();
	private AtomicReference<List<TaskProgress>>		infosUpdate			= new AtomicReference<>();
	private ObservableList<ProgressData>			observableProgress	= FXCollections
			.observableArrayList();
	private Workflow								w					= null;
}
