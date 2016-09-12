package org.power_systems_modelica.psm.workflow;

import static org.power_systems_modelica.psm.workflow.ProcessState.FAILED;
import static org.power_systems_modelica.psm.workflow.ProcessState.IDLE;
import static org.power_systems_modelica.psm.workflow.ProcessState.RUNNING;
import static org.power_systems_modelica.psm.workflow.ProcessState.SCHEDULED;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Workflow implements Process
{
	public static Workflow create(WorkflowConfiguration config, TaskFactory TaskFactory)
			throws WorkflowCreationException
	{
		return new Workflow(config, TaskFactory, counter++);
	}

	private Workflow(WorkflowConfiguration config, TaskFactory TaskFactory, int id)
			throws WorkflowCreationException
	{
		this.id = id;
		this.config = config;

		state = IDLE;
		WorkflowTasks = new ArrayList<WorkflowTask>();
		for (TaskDefinition td : config.getTaskDefinitions())
		{
			WorkflowTask t = TaskFactory.create(td);
			t.setWorkflow(this);
			WorkflowTasks.add(t);
		}
	}

	@Override
	public ProcessState getState()
	{
		return state;
	}

	public WorkflowConfiguration getConfiguration()
	{
		return config;
	}

	public void start()
	{
		// Assume all WorkflowTasks must be run sequentially
		sequence(WorkflowTasks);
	}

	public List<TaskStatePair> getTaskStates()
	{
		return currentTaskStates;
	}

	public int getId()
	{
		return id;
	}

	public void addListener(WorkflowListener l)
	{
		listeners.add(l);
	}

	public void removeListener(WorkflowListener l)
	{
		listeners.remove(l);
	}

	private void sequence(List<WorkflowTask> WorkflowTasks)
	{
		updateState();
		Iterator<WorkflowTask> k = WorkflowTasks.iterator();
		while (k.hasNext())
		{
			WorkflowTask t = k.next();
			updateState(t.getId(), SCHEDULED);
			t.run();
			updateState();
			if (t.getState() == FAILED) break;
		}
	}

	static public enum ResultsScope
	{
		SCOPE_TASK, SCOPE_GLOBAL;
	}

	public void publish(WorkflowTask t, ResultsScope scope, String resultsId, Object results)
	{
		this.results.put(getQualifiedResultsId(scope, t.getId(), resultsId), results);
	}

	public Object getResults(String resultsId)
	{
		return results.get(getQualifiedResultsId(ResultsScope.SCOPE_GLOBAL, null, resultsId));
	}

	public Object getResults(String taskId, String resultsId)
	{
		return results.get(getQualifiedResultsId(ResultsScope.SCOPE_TASK, taskId, resultsId));
	}

	public static String getQualifiedResultsId(ResultsScope scope, String taskId, String resultsId)
	{
		String scopeId = null;
		switch (scope)
		{
		case SCOPE_GLOBAL:
			scopeId = "*";
			break;
		case SCOPE_TASK:
			scopeId = taskId;
			break;
		}
		return String.join(":", scopeId, resultsId);
	}

	private void updateState()
	{
		currentTaskStates = WorkflowTasks
				.stream()
				.map(t -> new TaskStatePair(t.getId(), t.getState()))
				.collect(Collectors.toList());
		updateWorkflowState();
		broadcast();
	}

	private void updateState(String taskId, ProcessState state)
	{
		// Only modify the state of the given taskId
		currentTaskStates = currentTaskStates
				.stream()
				.map(ts -> (ts.taskId.equals(taskId) ? new TaskStatePair(taskId, state) : ts))
				.collect(Collectors.toList());
		updateWorkflowState();
		broadcast();
	}

	private void updateWorkflowState()
	{
		currentTaskStates.stream()
				.filter(s -> s.state == RUNNING || s.state == SCHEDULED).findAny()
				.ifPresent(s -> this.state = RUNNING);
		currentTaskStates.stream()
				.filter(s -> s.state == FAILED).findAny()
				.ifPresent(s -> this.state = FAILED);
		if (currentTaskStates.stream().allMatch(s -> s.state == SUCCESS))
			this.state = SUCCESS;
	}

	private void broadcast()
	{
		for (WorkflowListener l : listeners)
			l.onStateChange(currentTaskStates);
	}

	private static int						counter		= 0;
	private final int						id;
	private final WorkflowConfiguration		config;
	private ProcessState					state;
	private List<WorkflowTask>				WorkflowTasks;
	private List<TaskStatePair>				currentTaskStates;
	private Map<String, Object>				results		= new HashMap<>();
	private final List<WorkflowListener>	listeners	= new ArrayList<>();
}
