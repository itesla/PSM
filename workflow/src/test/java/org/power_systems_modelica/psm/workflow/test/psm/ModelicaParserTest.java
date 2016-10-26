package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil;

public class ModelicaParserTest
{
	@Test
	public void parseIeee14() throws WorkflowCreationException, IOException
	{
		testParseModelica("ieee14/itesla", "ieee14.mo");
	}

	private void testParseModelica(
			String foldername,
			String moname)
			throws WorkflowCreationException, IOException
	{
		Path folder = TEST_SAMPLES.resolve(foldername);
		String moInput = folder.resolve(moname).toString();
		String moOutput = DATA_TMP.resolve("kk_" + moname).toString();

		Workflow wf = WF(
				TD(ModelicaParserTask.class, "moparser0",
						TC("source", moInput)),
				TD(ModelicaExporterTask.class, "exporter0",
						TC("target", moOutput)));
		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getState());

		Path expected = folder.resolve(moInput);
		Path actual = Paths.get(moOutput);

		WorkflowTestUtil.assertEqualsModelicaText(
				Files.newInputStream(expected),
				Files.newInputStream(actual));
	}
}
