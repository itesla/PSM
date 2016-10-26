package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.DydFilesFromModelicaTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil;

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
				"itesla/ieee14bus_no_loadflow.mo",
				"ieee14bus_EQ.xml",
				"kk_ddr");
	}

	public void testRebuildModelica(
			String foldername,
			String moname,
			String casename,
			String ddrLocation)
			throws WorkflowCreationException, IOException
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		// ddr relative to folder
		ddrLocation = folder.resolve(ddrLocation).toAbsolutePath().toString();
		Files.createDirectories(Paths.get(ddrLocation));
		String moInput = folder.resolve(moname).toString();
		String cim = folder.resolve(casename).toString();
		String fakeInit = folder.resolve(ddrLocation).resolve("fake_init.csv").toString();
		String moOutput = "./kk.mo";
		String modelicaEngineWorkingDir = "./kk";
		Files.createDirectories(Paths.get(modelicaEngineWorkingDir));

		Workflow wf = WF(
				TD(DydFilesFromModelicaTask.class, "mo2dyd0",
						TC("ddrLocation", ddrLocation,
								"modelicaFile", moInput)),
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddrLocation,
								"modelicaEngine", "Fake",
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir,
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
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);

		Path expected = folder.resolve(moInput);
		Path actual = Paths.get(moOutput);

		WorkflowTestUtil.assertEqualsText(
				Files.newInputStream(expected),
				Files.newInputStream(actual));
	}
}
