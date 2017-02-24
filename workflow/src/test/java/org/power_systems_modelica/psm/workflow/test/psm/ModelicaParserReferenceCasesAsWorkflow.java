package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.modelica.parser.test.ModelicaParserReferenceCases;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;

public class ModelicaParserReferenceCasesAsWorkflow extends ModelicaParserReferenceCases
{
	@Override
	protected void parse(String catalog, String foldername, String moname) throws Exception
	{
		Path folder = DATA.resolve(catalog).resolve(foldername);
		String moInput = folder.resolve(moname).toString();
		String moOutput = DATA_TMP.resolve("parsed_" + moname).toString();

		Workflow wf = WF(
				TD(ModelicaParserTask.class, "moparser0",
						TC("source", moInput, "modelicaDocument", "mo")),
				TD(ModelicaExporterTask.class, "exporter0",
						TC("target", moOutput)));
		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getState());

		Path expected = folder.resolve(moInput);
		Path actual = Paths.get(moOutput);

		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}
}
