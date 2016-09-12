package org.power_systems_modelica.psm.workflow.test;

import java.util.Arrays;

import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskConfiguration;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.TaskFactory;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class WorkflowTestMacros
{
	public static TaskStatePair TS(String taskId, ProcessState state)
	{
		return new TaskStatePair(taskId, state);
	}

	public static TaskDefinition TD(Class<? extends WorkflowTask> taskClass, String taskId)
	{
		return new TaskDefinition(taskClass, taskId);
	}

	public static TaskDefinition TD(Class<? extends WorkflowTask> taskClass, String taskId,
			TaskConfiguration config)
	{
		TaskDefinition td = new TaskDefinition(taskClass, taskId);
		td.setTaskConfiguration(config);
		return td;
	}

	public static TaskConfiguration TC(String... params)
	{
		TaskConfiguration tc = new TaskConfiguration();
		for (int k = 0; k < params.length; k += 2)
			tc.setParameter(params[k], params[k + 1]);
		return tc;
	}

	public static Workflow WF(TaskDefinition... td) throws WorkflowCreationException
	{
		WorkflowConfiguration config = new WorkflowConfiguration();
		config.setTaskDefinitions(Arrays.asList(td));
		TaskFactory tf = new TaskFactory();
		Workflow wf = Workflow.create(config, tf);
		return wf;
	}
}
