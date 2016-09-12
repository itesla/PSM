package org.power_systems_modelica.psm.workflow.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.power_systems_modelica.psm.workflow.ProcessState.FAILED;
import static org.power_systems_modelica.psm.workflow.ProcessState.IDLE;
import static org.power_systems_modelica.psm.workflow.ProcessState.SCHEDULED;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestMacros.TD;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestMacros.TS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestMacros.WF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

public class SimpleWorkflowTest
{
	@Test
	public void testWorfkflowSingleEmptyTask() throws WorkflowCreationException
	{
		Workflow wf = WF(TD(EmptyTask.class, "task0"));
		wf.start();
		assertEquals(SUCCESS, wf.getState());
	}

	@Test
	public void testWorfkflowSingleFailTask() throws WorkflowCreationException
	{
		Workflow wf = WF(TD(FailTask.class, "task0"));
		wf.start();
		assertEquals(FAILED, wf.getState());
	}

	@Test
	public void testWorfkflowSecondTaskMustNotBeRun() throws WorkflowCreationException
	{
		Workflow wf = WF(TD(FailTask.class, "task0"), TD(EmptyTask.class, "task1"));
		wf.start();
		assertEquals(FAILED, wf.getState());
		List<TaskStatePair> ts = wf.getTaskStates();
		assertEquals(FAILED, ts.get(0).state);
		assertEquals(IDLE, ts.get(1).state);
	}

	@Test
	public void testWorfkflowListenerTwoSuccessfulTasks() throws WorkflowCreationException
	{
		Workflow wf = WF(TD(EmptyTask.class, "task0"), TD(EmptyTask.class, "task1"));
		
		TestWorkflowListener wfl = new TestWorkflowListener();
		wf.addListener(wfl);
		wf.start();

		assertEquals(SUCCESS, wf.getState());
		List<TaskStatePair> ts = wf.getTaskStates();
		assertEquals(SUCCESS, ts.get(0).state);
		assertEquals(SUCCESS, ts.get(1).state);

		if (wfl.getRecordedStates().isEmpty()) fail();

		List<List<TaskStatePair>> expected = new ArrayList<>();
		expected.add(Arrays.asList(TS("task0", IDLE), TS("task1", IDLE)));
		expected.add(Arrays.asList(TS("task0", SCHEDULED), TS("task1", IDLE)));
		expected.add(Arrays.asList(TS("task0", SUCCESS), TS("task1", IDLE)));
		expected.add(Arrays.asList(TS("task0", SUCCESS), TS("task1", SCHEDULED)));
		expected.add(Arrays.asList(TS("task0", SUCCESS), TS("task1", SUCCESS)));
		assertThat(expected, is(equalTo(wfl.getRecordedStates())));

		int record = 0;
		for (List<TaskStatePair> states : wfl.getRecordedStates())
		{
			System.out.printf("record %d%n", record++);
			for (TaskStatePair state : states)
				System.out.printf("    %-8s %-10s%n", state.taskId, state.state);
		}
	}

	@Test
	public void testTaskStatePairs()
	{
		TaskStatePair t0 = TS("task", IDLE);
		TaskStatePair t1 = TS("task", IDLE);
		assertEquals(t0, t1);
	}
}
