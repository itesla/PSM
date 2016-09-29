package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TC;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TD;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.WF;

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
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class ModelicaBuilderTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void buildIeee14() throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_everything_explicit",
				"expected/ieee14bus_wref.mo");
	}

	@Test
	public void buildIeee14DdrWithAReference()
			throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_single_manual_reference",
				"expected/ieee14bus_wref_ddr_single_manual_reference.mo");
	}

	@Test
	public void buildIeee14DdrWithIidmReferences()
			throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references",
				"expected/ieee14bus_wref_ddr_references.mo");
	}

	@Test
	public void buildIeee14DdrWithIidmReferencesInGenerators()
			throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references_gen",
				"expected/ieee14bus_wref_ddr_references_gen.mo");
	}

	@Test
	public void buildIeee14DdrWithIidmReferencesAndInlineParameters()
			throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references_gen_inline",
				"expected/ieee14bus_wref_ddr_references_gen.mo");
	}

	@Test
	public void buildIeee14WithIidmAndInitReferences()
			throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references_to_init_source_data",
				"expected/ieee14bus_wref_ddr_references_gen.mo");
	}

	@Test
	public void buildIeee14DdrUnparsedSystemEquations()
			throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_unparsed_system_equations",
				"expected/ieee14bus_wref_ddr_references_gen.mo");
	}

	public void testBuild(
			String foldername,
			String casename,
			String ddrname,
			String expectedmoname)
			throws WorkflowCreationException, IOException
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		String cim = folder.resolve(casename).toString();
		String ddr = folder.resolve(ddrname).toString();
		String fakeInit = folder.resolve(ddrname).resolve("fake_init.csv").toString();
		String outname = "./kk.mo";
		String modelicaEngineWorkingDir = "./kk";
		Files.createDirectories(Paths.get(modelicaEngineWorkingDir));

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr,
								"modelicaEngine", "Fake",
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir,
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
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);

		Path expected = folder.resolve(expectedmoname);
		Path actual = Paths.get(outname);

		WorkflowTestUtil.assertEqualsText(
				Files.newInputStream(expected),
				Files.newInputStream(actual));
	}
}
