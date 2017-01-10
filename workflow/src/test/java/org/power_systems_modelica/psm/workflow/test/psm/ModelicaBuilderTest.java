package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
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
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

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
				"ieee14/ddr",
				"itesla/ieee14bus_no_lf.mo",
				14,
				5);
	}

	@Test
	public void buildIeee30() throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee30",
				"ieee30bus_EQ.xml",
				"ieee30/ddr",
				"itesla/ieee30bus_no_lf.mo",
				30,
				6);
	}

	@Test
	public void buildIeee57() throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee57",
				"ieee57bus_EQ.xml",
				"ieee57/ddr",
				"itesla/ieee57bus_no_lf.mo",
				57,
				7);
	}

	@Test
	public void buildIeee118() throws WorkflowCreationException, IOException
	{
		testBuild(
				"ieee118",
				"ieee118bus_EQ.xml",
				"ieee118/ddr",
				"itesla/ieee118bus_no_lf.mo",
				118,
				54);
	}

	@Test // FIXME Fixed injections are not exported to the Modelica file.
	public void buildSmallCase1() throws WorkflowCreationException, IOException
	{
		testBuild(
				"smallcase1",
				"case1_EQ.xml",
				"smallcase1/ddr",
				"itesla/case1_no_lf.mo",
				3,
				2); // There are one generator and one fixed injection but in IIDM both are Generators.
	}

	@Test
	public void buildSmallCase2() throws WorkflowCreationException, IOException
	{
		testBuild(
				"smallcase2",
				"case2_EQ.xml",
				"smallcase2/ddr",
				"itesla/case2_no_lf.mo",
				3,
				1);
	}

	@Test 
	public void buildSmallCase3() throws WorkflowCreationException, IOException
	{
		testBuild(
				"smallcase3",
				"case3_EQ.xml",
				"smallcase3/ddr",
				"itesla/case3_no_lf.mo",
				3,
				2); // There are one generator and one fixed injection but in IIDM both are Generators.
	}

	@Test // FIXME Extend grammar to accept array parameters and fix differences in ratios of transformers.
	public void build7buses() throws WorkflowCreationException, IOException
	{
		testBuild(
				"7buses",
				"CIM_7buses_EQ.xml",
				"7buses/ddr",
				"itesla/CIM_7buses_no_lf.mo",
				7,
				3);
	}

	@Test
	public void buildNordic32() throws WorkflowCreationException, IOException
	{
		testBuild(
				"Nordic32",
				"Nordic32_EQ.xml",
				"Nordic32/ddr",
				"itesla/Nordic32_no_lf.mo",
				52,
				20);
	}

	public void testBuild(
			String foldername,
			String casename,
			String ddrname,
			String expectedmoname,
			int expectedNumBuses,
			int expectedNumGenerators)
			throws WorkflowCreationException, IOException
	{
		// TODO Use ShrinkWrap file system for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		String cim = folder.resolve(casename).toString();
		String ddr = TEST_SAMPLES.resolve(ddrname).toString();
		String fakeInit = TEST_SAMPLES.resolve(ddrname).resolve("fake_init.csv").toString();

		String name = foldername;
		String outname = DATA_TMP.resolve("moBuilder_output_" + name + ".mo").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("moBuilder_init" + name);
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
