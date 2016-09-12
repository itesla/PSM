package org.power_systems_modelica.psm.workflow;

import java.lang.reflect.InvocationTargetException;

public class TaskFactory
{
	public WorkflowTask create(TaskDefinition td) throws WorkflowCreationException
	{
		try
		{
			String taskId = td.getTaskId();
			WorkflowTask t = td
					.getTaskClass()
					.getDeclaredConstructor(String.class)
					.newInstance(taskId);
			t.configure(td.getTaskConfiguration());
			return t;
		}
		catch (InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException
				| NoSuchMethodException
				| SecurityException e)
		{
			throw new WorkflowCreationException(e);
		}
	}
}