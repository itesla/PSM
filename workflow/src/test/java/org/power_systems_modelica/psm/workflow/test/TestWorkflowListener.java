package org.power_systems_modelica.psm.workflow.test;

import java.util.ArrayList;
import java.util.List;

import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.WorkflowListener;

class TestWorkflowListener implements WorkflowListener
{
	@Override
	public void onStateChange(List<TaskStatePair> states)
	{
		recorded.add(states);
	}

	public List<List<TaskStatePair>> getRecordedStates()
	{
		return recorded;
	}

	private List<List<TaskStatePair>> recorded = new ArrayList<>();
}