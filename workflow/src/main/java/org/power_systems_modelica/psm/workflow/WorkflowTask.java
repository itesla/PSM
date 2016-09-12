package org.power_systems_modelica.psm.workflow;

import static org.power_systems_modelica.psm.workflow.ProcessState.FAILED;
import static org.power_systems_modelica.psm.workflow.ProcessState.IDLE;
import static org.power_systems_modelica.psm.workflow.ProcessState.RUNNING;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;

/*
An individual task can not be interrupted, 
Status is IDLE until run is called
is RUNNING while run is being executed
and is either SUCCESS or FAILED after run has finished
*/

public abstract class WorkflowTask
{
	public WorkflowTask(String id)
	{
		this.id = id;
	}

	public abstract String getName();

	public abstract void configure(TaskConfiguration config);

	public String getId()
	{
		return id;
	}

	public void setWorkflow(Workflow workflow)
	{
		this.workflow = workflow;
	}

	public ProcessState getState()
	{
		return state;
	}

	public void run()
	{
		running();
	}

	protected void running()
	{
		state = RUNNING;
	}

	protected void succeded()
	{
		state = SUCCESS;
	}

	protected void failed()
	{
		state = FAILED;
	}

	protected void publish(Workflow.ResultsScope scope, String resultsId, Object results)
	{
		workflow.publish(this, scope, resultsId, results);
	}

	private final String	id;
	protected Workflow		workflow;
	private ProcessState	state	= IDLE;
}
