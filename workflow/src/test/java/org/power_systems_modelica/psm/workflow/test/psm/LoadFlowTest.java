package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TC;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TD;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.WF;

import org.junit.Before;
import org.junit.Test;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

public class LoadFlowTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void hades2Runs() throws WorkflowCreationException
	{
		String case_ = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml").toString();
		String cim = case_.toString();

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(LoadFlowTask.class, "loadflow0",
						TC("loadFlowFactoryClass", "com.rte_france.itesla.hades2.Hades2Factory")));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
	}
}
