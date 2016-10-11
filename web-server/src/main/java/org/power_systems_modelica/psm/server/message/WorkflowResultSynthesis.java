package org.power_systems_modelica.psm.server.message;

import java.io.Serializable;
import java.util.List;

import org.power_systems_modelica.psm.workflow.WorkflowResult;

public class WorkflowResultSynthesis implements Serializable
{
	public WorkflowResultSynthesis(List<WorkflowResult> results)
	{
		this.results = results;
	}

	public List<WorkflowResult> getWorkflowResult()
	{
		return results;
	}

	public void setWorkflowResult(List<WorkflowResult> results)
	{
		this.results = results;
	}

	private List<WorkflowResult> results;
}
