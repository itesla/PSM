package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.workflow.ProcessState.FAILED;
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
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class ImporterTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void failsIfSourceDoesNotExist()
			throws WorkflowCreationException
	{
		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0", TC("source", "does not exist")));
		wf.start();
		assertEquals(FAILED, wf.getState());
	}

	@Test
	public void iee14() throws WorkflowCreationException
	{

		String filename = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml").toString();

		Workflow wf = WF(TD(StaticNetworkImporterTask.class, "importer0", TC("source", filename)));
		wf.start();
		assertEquals(SUCCESS, wf.getState());
		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}
}
