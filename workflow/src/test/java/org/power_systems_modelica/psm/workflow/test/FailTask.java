package org.power_systems_modelica.psm.workflow.test;

public class FailTask extends EmptyTask
{
	public FailTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Fail task";
	}

	@Override
	public void run()
	{
		failed();
	}
}