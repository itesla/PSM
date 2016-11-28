package org.power_systems_modelica.psm.workflow.test;

import java.util.ArrayList;
import java.util.List;

import org.power_systems_modelica.psm.workflow.TaskProgress;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.WorkflowListener;

class TestWorkflowListener implements WorkflowListener
{
	@Override
	public void onStateChange(List<TaskStatePair> states)
	{
		recordedStates.add(states);
	}

	@Override
	public void onProgress(TaskProgress infos) 
	{
		recordedInfos.add(infos);
	}

	public List<List<TaskStatePair>> getRecordedStates()
	{
		return recordedStates;
	}

	public List<TaskProgress> getRecordedInfos()
	{
		return recordedInfos;
	}

	private List<TaskProgress> recordedInfos = new ArrayList<>();
	private List<List<TaskStatePair>> recordedStates = new ArrayList<>();

}