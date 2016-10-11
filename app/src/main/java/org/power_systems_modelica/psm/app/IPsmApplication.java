package org.power_systems_modelica.psm.app;

import java.util.List;

import org.power_systems_modelica.psm.dao.Case;
import org.power_systems_modelica.psm.dao.Catalog;
import org.power_systems_modelica.psm.dao.Ddr;
import org.power_systems_modelica.psm.workflow.WorkflowParameters;
import org.power_systems_modelica.psm.workflow.WorkflowResult;
import org.power_systems_modelica.psm.workflow.Workflow;

public interface IPsmApplication extends AutoCloseable
{
	void addListener(PsmApplicationListener l);

	void removeListener(PsmApplicationListener l);

	public String create();

	public WorkflowParameters getWorkflow(String id);

	public void removeWorkflow(String id);

	void startWorkflow(WorkflowParameters conf);

	void stopWorkflow();

	public List<Workflow> getWorkflowStates();

	public List<Catalog> getCatalogs();

	public List<Case> getCases(String catalogName);

	public List<Ddr> getDdrs(String catalogName);

	List<WorkflowResult> getWorkflowResult(String workflowId);
}
