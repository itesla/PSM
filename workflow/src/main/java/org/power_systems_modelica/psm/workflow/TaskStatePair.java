package org.power_systems_modelica.psm.workflow;

public class TaskStatePair
{
	public final String			taskId;
	public final ProcessState	state;

	public TaskStatePair(String taskId, ProcessState state)
	{
		this.taskId = taskId;
		this.state = state;
	}

	@Override
	public int hashCode()
	{
		return this.taskId.hashCode() + 37 * this.state.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof TaskStatePair)) return false;
		if (obj == this) return true;

		TaskStatePair other = (TaskStatePair) obj;
		return this.taskId.equals(other.taskId) && this.state.equals(other.state);
	}

	@Override
	public String toString()
	{
		return taskId + ":" + state;
	}
}
