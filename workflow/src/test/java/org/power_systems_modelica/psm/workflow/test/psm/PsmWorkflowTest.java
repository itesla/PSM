package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.workflow.ProcessState.FAILED;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TC;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TD;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.WF;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteStreams;

import eu.itesla_project.iidm.network.Network;

public class PsmWorkflowTest
{
	private static final Path TEST_SAMPLES = Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");

	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void testWorfkflowStaticNetworkImportFailsIfSourceDoesNotExist()
			throws WorkflowCreationException
	{
		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0", TC("source", "does not exist")));
		wf.start();
		assertEquals(FAILED, wf.getState());
	}

	@Test
	public void testWorfkflowStaticNetworkImportIeee14() throws WorkflowCreationException
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

	@Test
	public void testWorfkflowBuildIeee14() throws WorkflowCreationException, IOException
	{
		testWorkflowBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_everything_explicit",
				"expected/ieee14bus_without_omegaRef.mo");
	}

	@Test
	public void testWorfkflowBuildIeee14DdrWithAReference()
			throws WorkflowCreationException, IOException
	{
		testWorkflowBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_single_manual_reference",
				"expected/ieee14bus_without_omegaRef_ddr_single_manual_reference.mo");
	}

	@Test
	public void testWorfkflowBuildIeee14DdrWithIidmReferences()
			throws WorkflowCreationException, IOException
	{
		testWorkflowBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references",
				"expected/ieee14bus_without_omegaRef_ddr_references.mo");
	}

	@Test
	public void testWorfkflowBuildIeee14DdrWithIidmReferencesInGenerators()
			throws WorkflowCreationException, IOException
	{
		testWorkflowBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references_gen",
				"expected/ieee14bus_without_omegaRef_ddr_references_gen.mo");
	}

	@Test
	public void testWorfkflowBuildIeee14DdrWithIidmReferencesAndInlineParameters()
			throws WorkflowCreationException, IOException
	{
		testWorkflowBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references_gen_inline",
				"expected/ieee14bus_without_omegaRef_ddr_references_gen.mo");
	}

	@Test
	public void testWorfkflowBuildIeee14WithIidmAndInitReferences()
			throws WorkflowCreationException, IOException
	{
		testWorkflowBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr_references_to_init_source_data",
				"expected/ieee14bus_without_omegaRef_ddr_references_gen.mo");
	}

	public void testWorkflowBuild(
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
		String outname = "/tmp/test.mo";

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", cim)),
				TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr,
								"modelicaEngine", "Fake",
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

		assertEqualsText(Files.newInputStream(expected), Files.newInputStream(actual));
	}

	@Test
	public void testLoadFlowHades2Runs() throws WorkflowCreationException
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

	protected static void assertEqualsText(InputStream expected, InputStream actual)
			throws IOException
	{
		assertEquals(
				new String(ByteStreams.toByteArray(expected), StandardCharsets.UTF_8),
				new String(ByteStreams.toByteArray(actual), StandardCharsets.UTF_8));
	}
}
