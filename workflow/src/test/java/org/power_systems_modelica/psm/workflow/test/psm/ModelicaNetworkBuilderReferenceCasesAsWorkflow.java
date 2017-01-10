package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.test.ModelicaNetworkBuilderReferenceCases;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class ModelicaNetworkBuilderReferenceCasesAsWorkflow
		extends ModelicaNetworkBuilderReferenceCases
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Override
	public void build(
			String foldername,
			String casename,
			String ddrname,
			String expectedmoname,
			int expectedNumBuses,
			int expectedNumGenerators)
			throws Exception
	{
		// TODO Use ShrinkWrap file system for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		String cim = folder.resolve(casename).toString();
		String ddr = TEST_SAMPLES.resolve(ddrname).toString();
		String fakeInit = TEST_SAMPLES.resolve(ddrname).resolve("fake_init.csv").toString();
		String name = foldername;
		String outname = DATA_TMP
				.resolve("workflow_modelica_builder_output_" + name + ".mo").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("workflow_modelica_builder_init" + name);
		Files.createDirectories(modelicaEngineWorkingDir);

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr,
								"modelicaEngine", "Fake",
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"fakeModelicaEngineResults", fakeInit)),
				TD(ModelicaExporterTask.class, "exporter0",
						TC("target", outname)));
		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);
		assertEquals(SUCCESS, wf.getState());

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(expectedNumBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);

		Path expected = folder.resolve(expectedmoname);
		Path actual = Paths.get(outname);
		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}
}
