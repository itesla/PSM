package org.power_systems_modelica.psm.app;

import java.util.List;

import org.power_systems_modelica.psm.dao.Case;
import org.power_systems_modelica.psm.dao.Catalog;
import org.power_systems_modelica.psm.dao.Ddr;
import org.power_systems_modelica.psm.workflow.WorkflowParameters;
import org.power_systems_modelica.psm.workflow.Workflow;

public interface PsmApplicationMBean
{
	public static final String BEAN_NAME = "org.power_systems_modelica.psm.app:type=PsmApplicationMBean";

	void ping();

	public String create();

	void startWorkflow(WorkflowParameters conf);

	void stopWorkflow();

	public List<Workflow> getWorkflowStates();

	public List<Catalog> getCatalogs();

	public List<Case> getCases(String catalogName);

	public List<Ddr> getDdrs(String catalogName);
}
