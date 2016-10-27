package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class ModelicaEventAdderTest
{
	@Test
	public void addEventsIeee14BusFault() throws WorkflowCreationException, IOException
	{
		String events = new StringBuilder(100)
				.append("BusFault")
				.append(",")
				.append("_BUS___10_TN")
				.append(",")
				.append("R=0.5")
				.append(",")
				.append("X=0.5")
				.append(",")
				.append("t1=20.0")
				.append(",")
				.append("t2=20.2")
				.append("\n")
				.toString();
		ModelicaDocument mo = addEvents(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr",
				events,
				1,
				1);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		assertTrue(models.get("_BUS___10_TN").getDeclarations().stream()
				.filter(d -> d.getType().endsWith("PwFault"))
				.findFirst()
				.isPresent());
	}

	@Test
	public void addEventsIeee14LineFault() throws WorkflowCreationException, IOException
	{
		String events = new StringBuilder(100)
				.append("LineFault")
				.append(",")
				.append("_BUS___10-BUS___11-1_AC")
				.append(",")
				.append("k=0.7")
				.append(",")
				.append("Rfault=0.01")
				.append(",")
				.append("Xfault=0.001")
				.append(",")
				.append("startTime=200")
				.append(",")
				.append("endTime=400")
				.append("\n")
				.toString();
		ModelicaDocument mo = addEvents(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr",
				events,
				0,
				0);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		assertTrue(models.get("_BUS___10_BUS___11_1_AC").getDeclarations()
				.get(0)
				.getType()
				.endsWith("LineFault"));
	}

	public ModelicaDocument addEvents(
			String foldername,
			String casename,
			String ddrname,
			String events,
			int expectedAdditionalDeclarations,
			int expectedAdditionalEquations)
			throws WorkflowCreationException, IOException
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		String cim = folder.resolve(casename).toString();
		String ddr = folder.resolve(ddrname).toString();
		String fakeInit = folder.resolve(ddrname).resolve("fake_init.csv").toString();
		String outname = DATA_TMP.resolve("eventAdder_initial.mo").toString();
		String outnameev = DATA_TMP.resolve("eventAdder_events.mo").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("eventAdder");
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
						TC("source", "mo",
								"target", outname,
								"includePsmAnnotations", "true")),
				TD(ModelicaEventAdderTask.class, "eventAdder0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr,
								"events", events)),
				TD(ModelicaExporterTask.class, "exporter1",
						TC("source", "moWithEvents",
								"target", outnameev)));
		wf.start();

		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(3).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(4).state);
		assertEquals(SUCCESS, wf.getState());

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
		ModelicaDocument mo = (ModelicaDocument) wf.getResults("mo");
		assertNotNull(mo);
		ModelicaDocument mo2 = (ModelicaDocument) wf.getResults("moWithEvents");
		assertNotNull(mo2);
		int ndecls = mo.getSystemModel().getDeclarations().size();
		int ndecls2 = mo2.getSystemModel().getDeclarations().size();
		assertEquals(ndecls + expectedAdditionalDeclarations, ndecls2);
		int neqs = mo.getSystemModel().getEquations().size();
		int neqs2 = mo2.getSystemModel().getEquations().size();
		assertEquals(neqs + expectedAdditionalEquations, neqs2);
		return mo2;
	}
}
