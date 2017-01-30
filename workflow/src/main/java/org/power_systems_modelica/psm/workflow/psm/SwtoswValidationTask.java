package org.power_systems_modelica.psm.workflow.psm;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class SwtoswValidationTask extends WorkflowTask
{

	public SwtoswValidationTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Sw-to-Sw validation";
	}

	@Override
	public void configure(Configuration config)
	{
	}

	@Override
	public void run()
	{
		running();
		succeded();
	}
}
