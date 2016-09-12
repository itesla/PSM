package org.power_systems_modelica.psm.workflow.test;

import org.power_systems_modelica.psm.workflow.TaskConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class EmptyTask extends WorkflowTask
{
	public EmptyTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Empty task";
	}

	@Override
	public void configure(TaskConfiguration config)
	{
	}

	@Override
	public void run()
	{
		succeded();
	}
}