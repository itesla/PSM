package org.power_systems_modelica.psm.workflow;

import org.power_systems_modelica.psm.commons.Configuration;

public final class TaskDefinition
{
	public TaskDefinition(Class<? extends WorkflowTask> taskClass, String taskId)
	{
		this.taskClass = taskClass;
		this.taskId = taskId;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public Configuration getTaskConfiguration()
	{
		return config;
	}

	public void setTaskConfiguration(Configuration config)
	{
		this.config = config;
	}

	public Class<? extends WorkflowTask> getTaskClass()
	{
		return taskClass;
	}

	private final Class<? extends WorkflowTask>	taskClass;
	private final String						taskId;
	private Configuration						config;
}
