package org.power_systems_modelica.psm.workflow;
public class TaskProgress {

	public final String			taskId;
	public final String			info;

	public TaskProgress(String taskId, String info) {

		this.taskId = taskId;
		this.info = info;
	}
	
	@Override
	public int hashCode()
	{
		return this.taskId.hashCode() + 37 * this.info.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof TaskProgress)) return false;
		if (obj == this) return true;

		TaskProgress other = (TaskProgress) obj;
		return this.taskId.equals(other.taskId) && this.info.equals(other.info);
	}

	@Override
	public String toString()
	{
		return taskId + ":" + info;
	}
}
