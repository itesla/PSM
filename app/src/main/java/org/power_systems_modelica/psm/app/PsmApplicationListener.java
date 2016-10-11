package org.power_systems_modelica.psm.app;

import org.power_systems_modelica.psm.workflow.StatusSynthesis;

public interface PsmApplicationListener
{
	void onDisconnection();

	void onConnection();

	void onWorkflowUpdate(StatusSynthesis status);
}
