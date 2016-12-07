package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.DydFilesFromModelicaTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class Mo2Dyd2MoTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void rebuildIeee14() throws WorkflowCreationException, IOException
	{
		testRebuildModelica(
				"ieee14",
				"itesla/ieee14bus_no_lf.mo",
				"itesla/init",
				"ieee14bus_EQ.xml",
				"ieee14_ddr",
				14,
				5);
	}

	@Test
	public void rebuildIeee30() throws WorkflowCreationException, IOException
	{
		testRebuildModelica(
				"ieee30",
				"itesla/ieee30bus_no_lf.mo",
				"itesla/init",
				"ieee30bus_EQ.xml",
				"ieee30_ddr",
				30,
				6);
	}

	@Test
	public void rebuildIeee57() throws WorkflowCreationException, IOException
	{
		testRebuildModelica(
				"ieee57",
				"itesla/ieee57bus_no_lf.mo",
				"itesla/init",
				"ieee57bus_EQ.xml",
				"ieee57_ddr",
				57,
				7);
	}

	@Test
	public void rebuildIeee118() throws WorkflowCreationException, IOException
	{
		testRebuildModelica(
				"ieee118",
				"itesla/ieee118bus_no_lf.mo",
				"itesla/init",
				"ieee118bus_EQ.xml",
				"ieee118_ddr",
				118,
				54);
	}

	private void testRebuildModelica(
			String foldername,
			String moName,
			String moInitPath,
			String caseName,
			String ddrLocation,
			int numBuses,
			int numGenerators)
			throws WorkflowCreationException, IOException
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		// ddr is created in tmp folder
		ddrLocation = DATA_TMP.resolve(ddrLocation).toAbsolutePath().toString();
		Files.createDirectories(Paths.get(ddrLocation));
		String moInput = folder.resolve(moName).toString();
		String moInputInit = folder.resolve(moInitPath).toString();
		String cim = folder.resolve(caseName).toString();
		String fakeInit = folder.resolve(ddrLocation).resolve("fake_init.csv").toString();
		String moOutput = DATA_TMP.resolve("mo2dyd2mo.mo").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("mo2dyd2mo");
		Files.createDirectories(modelicaEngineWorkingDir);

		TaskDefinition mo2dyd;
		if (EXPLICIT_INIT_FILES)
		{
			mo2dyd = TD(DydFilesFromModelicaTask.class, "mo2dyd0",
					TC("ddrLocation", ddrLocation,
							"modelicaFile", moInput,
							"modelicaInitPath", moInputInit));
		}
		else
		{
			mo2dyd = TD(DydFilesFromModelicaTask.class, "mo2dyd0",
					TC("ddrLocation", ddrLocation,
							"modelicaFile", moInput));
		}

		Workflow wf = WF(
				mo2dyd,
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddrLocation,
								"modelicaEngine", "Fake",
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"fakeModelicaEngineResults", fakeInit)),
				TD(ModelicaExporterTask.class, "exporter0",
						TC("target", moOutput)));

		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(3).state);
		assertEquals(SUCCESS, wf.getState());

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(numBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(numGenerators, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);

		Path expected = folder.resolve(moInput);
		Path actual = Paths.get(moOutput);

		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}

	private static final boolean EXPLICIT_INIT_FILES = true;
}
