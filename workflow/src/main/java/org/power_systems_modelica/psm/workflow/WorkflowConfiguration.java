package org.power_systems_modelica.psm.workflow;

import java.util.List;

public class WorkflowConfiguration
{
	public void setTaskDefinitions(List<TaskDefinition> taskDefinitions)
	{
		this.taskDefinitions = taskDefinitions;
	}

	public List<TaskDefinition> getTaskDefinitions()
	{
		return taskDefinitions;
	}

	private List<TaskDefinition> taskDefinitions;
}
