package org.power_systems_modelica.psm.workflow.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.TaskFactory;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import com.google.common.io.ByteStreams;

public class WorkflowTestUtil
{
	public static final Path TEST_SAMPLES = Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");

	public static TaskStatePair TS(String taskId, ProcessState state)
	{
		return new TaskStatePair(taskId, state);
	}

	public static TaskDefinition TD(Class<? extends WorkflowTask> taskClass, String taskId)
	{
		return new TaskDefinition(taskClass, taskId);
	}

	public static TaskDefinition TD(
			Class<? extends WorkflowTask> taskClass,
			String taskId,
			Configuration config)
	{
		TaskDefinition td = new TaskDefinition(taskClass, taskId);
		td.setTaskConfiguration(config);
		return td;
	}

	public static Configuration TC(String... params)
	{
		Configuration tc = new Configuration();
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

	public static void assertEqualsText(InputStream expected, InputStream actual)
			throws IOException
	{
		assertEquals(
				new String(ByteStreams.toByteArray(expected), StandardCharsets.UTF_8),
				new String(ByteStreams.toByteArray(actual), StandardCharsets.UTF_8));
	}
}
