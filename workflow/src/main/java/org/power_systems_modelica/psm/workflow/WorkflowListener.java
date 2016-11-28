package org.power_systems_modelica.psm.workflow;

import java.util.List;

public interface WorkflowListener
{
	public void onStateChange(List<TaskStatePair> states);

	public void onProgress(TaskProgress info);
}
