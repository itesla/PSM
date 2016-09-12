package org.power_systems_modelica.psm.workflow;

public interface TaskResultsListener
{
	public void onResults(String taskId, String resultsId, Object results);
}
