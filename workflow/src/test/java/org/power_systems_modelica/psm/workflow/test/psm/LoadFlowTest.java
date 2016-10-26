package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.TEST_SAMPLES;
import static org.power_systems_modelica.psm.workflow.test.WorkflowTestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;

import com.google.common.collect.Iterables;

import edu.emory.mathcs.backport.java.util.Arrays;
import eu.itesla_project.iidm.network.Network;

public class LoadFlowTest
{
	@Test
	public void hades2Runs() throws WorkflowCreationException
	{
		if (!isHades2Available()) return;

		String case_ = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml").toString();
		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", case_)),
				TD(LoadFlowTask.class, "loadflow0",
						TC("loadFlowFactoryClass", "com.rte_france.itesla.hades2.Hades2Factory",
								"targetCsvFolder", DATA_TMP.resolve("hades").toString())));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
	}

	@Test
	public void helmflowRuns() throws WorkflowCreationException
	{
		String case_ = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml").toString();
		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", case_)),
				TD(LoadFlowTask.class, "loadflow0",
						TC("loadFlowFactoryClass", "com.elequant.helmflow.ipst.HelmFlowFactory",
								"targetCsvFolder", DATA_TMP.resolve("helmflow").toString())));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
	}

	@Test
	public void compareHelmflowHades2Ieee14() throws WorkflowCreationException
	{
		String case_ = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml").toString();
		compareHelmflowHades2(case_);
	}

	void compareHelmflowHades2(String case_) throws WorkflowCreationException
	{
		if (!isHades2Available()) return;

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", case_)),
				TD(LoadFlowTask.class, "loadflowHelmflow",
						TC("loadFlowFactoryClass", "com.elequant.helmflow.ipst.HelmFlowFactory",
								"targetStateId", "resultsHelmflow")),
				TD(LoadFlowTask.class, "loadflowHades2",
						TC("loadFlowFactoryClass",
								"com.rte_france.itesla.hades2.Hades2Factory",
								"targetStateId", "resultsHades2")));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());

		n.getStateManager().allowStateMultiThreadAccess(false);
		Map<String, Map<String, float[]>> allBusesValues = new HashMap<>();
		n.getBusBreakerView().getBuses().forEach(b -> {
			Map<String, float[]> bvalues = new HashMap<>();
			float[] Vs = new float[2];
			float[] As = new float[2];
			float[] Ps = new float[2];
			float[] Qs = new float[2];
			n.getStateManager().setWorkingState("resultsHelmflow");
			Vs[0] = b.getV();
			As[0] = b.getAngle();
			Ps[0] = b.getP();
			Qs[0] = b.getQ();
			n.getStateManager().setWorkingState("resultsHades2");
			Vs[1] = b.getV();
			As[1] = b.getAngle();
			Ps[1] = b.getP();
			Qs[1] = b.getQ();
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);
			allBusesValues.put(b.getId(), bvalues);
		});
		checkResults(allBusesValues, "V", 0.01f, 0.1f);
		// checkResults(allBusesValues, "A", 0.1f, 0.2f);
		checkResults(allBusesValues, "P", 0.001f, 0.02f);
		checkResults(allBusesValues, "Q", 0.01f, 0.1f);
	}

	private void checkResults(
			Map<String, Map<String, float[]>> allBusesValues,
			String variable,
			float maxRelativeErrorAverage,
			float maxRelativeError)
	{
		List<Float> relativeErrors = calcRelativeErrors(allBusesValues, variable);
		DoubleSummaryStatistics stats = relativeErrors.stream()
				.collect(Collectors.summarizingDouble(Float::doubleValue));

		List<Float> values0 = allBusesValues.values().stream()
				.map(bv -> bv.get(variable)[0])
				.collect(Collectors.toList());
		List<Float> values1 = allBusesValues.values().stream()
				.map(bv -> bv.get(variable)[1])
				.collect(Collectors.toList());

		System.err.printf(
				"variable %s relativeErrors%n    HELM Flow %s%n    Hades2    %s%n    errors    %s%n    stats     %s%n",
				variable,
				Arrays.toString(values0.toArray()),
				Arrays.toString(values1.toArray()),
				Arrays.toString(relativeErrors.toArray()),
				stats);

		assertTrue(stats.getAverage() < maxRelativeErrorAverage);
		assertTrue(stats.getMax() < maxRelativeError);
	}

	private List<Float> calcRelativeErrors(
			Map<String, Map<String, float[]>> allBusesValues,
			String variable)
	{
		return allBusesValues.values().stream().map(bv -> {
			float[] values = bv.get(variable);
			float err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			return err;
		}).collect(Collectors.toList());
	}

	private boolean isHades2Available()
	{
		// Hades is only available for Linux
		return System.getProperty("os.name").startsWith("Linux");
	}
}
