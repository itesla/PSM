package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TC;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TD;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.WF;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

public class LoadFlowTest
{
	@Test
	public void hades2Runs() throws WorkflowCreationException
	{
		// Hades is only available for Linux
		if (!System.getProperty("os.name").startsWith("Linux")) return;

		String case_ = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml").toString();
		String cim = case_.toString();

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(LoadFlowTask.class, "loadflow0",
						TC("loadFlowFactoryClass", "com.rte_france.itesla.hades2.Hades2Factory",
								"targetCsvFolder", DATA_TMP.resolve("hades").toString())));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
	}

	@Test
	public void helmflowRuns() throws WorkflowCreationException
	{
		String case_ = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml").toString();
		String cim = case_.toString();

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(LoadFlowTask.class, "loadflow0",
						TC("loadFlowFactoryClass", "com.elequant.helmflow.ipst.HelmFlowFactory",
								"targetCsvFolder", DATA_TMP.resolve("helmflow").toString())));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
	}

	private static final Path DATA_TMP = Paths.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
}
