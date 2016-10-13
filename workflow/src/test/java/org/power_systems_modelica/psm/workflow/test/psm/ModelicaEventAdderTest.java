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

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
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
	public void addEventsIeee14() throws WorkflowCreationException, IOException
	{
		addEvents(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr");
	}

	public void addEvents(
			String foldername,
			String casename,
			String ddrname)
			throws WorkflowCreationException, IOException
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		String cim = folder.resolve(casename).toString();
		String ddr = folder.resolve(ddrname).toString();
		String fakeInit = folder.resolve(ddrname).resolve("fake_init.csv").toString();
		String outname = "./kk.mo";
		String outnameev = "./kk_events.mo";
		String modelicaEngineWorkingDir = "./kk";
		Files.createDirectories(Paths.get(modelicaEngineWorkingDir));

		StringBuilder events = new StringBuilder();
		events.append("BusFault");
		events.append(",");
		events.append("_BUS___10_TN");
		events.append(",");
		events.append("R=0.5");
		events.append(",");
		events.append("X=0.5");
		events.append(",");
		events.append("t1=20.0");
		events.append(",");
		events.append("t2=20.2");
		events.append("\n");

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
						TC("source", "mo",
								"target", outname,
								"includeSystemModelAnnotations", "true")),
				TD(ModelicaEventAdderTask.class, "eventAdder0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr,
								"events", events.toString())),
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
		assertEquals(ndecls + 1, ndecls2);
		int neqs = mo.getSystemModel().getEquations().size();
		int neqs2 = mo2.getSystemModel().getEquations().size();
		assertEquals(neqs + 1, neqs2);
	}
}
