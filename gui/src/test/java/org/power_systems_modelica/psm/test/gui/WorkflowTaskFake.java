package org.power_systems_modelica.psm.test.gui;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class WorkflowTaskFake extends WorkflowTask{

	public WorkflowTaskFake(String id) {
		super(id);
	}

	@Override
	public String getName()
	{
		return "Workflow Task Test";
	}
	
	@Override
	public void configure(Configuration config)
	{
	}

	@Override
	public void run()
	{
		progress("Start test workflow");
		running();
		progress("Succeded test workflow");
		succeded();
		progress("End test workflow");
	}
}
