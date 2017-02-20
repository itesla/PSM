package org.power_systems_modelica.psm.workflow.test.psm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;
import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.StateManager;

public class LoadFlowTest
{
	@Test
	public void hadesIeee14() throws WorkflowCreationException
	{
		hades("ieee14", "ieee14bus_EQ.xml");
	}

	@Test
	public void hadesIeee30() throws WorkflowCreationException
	{
		hades("ieee30", "ieee30bus_EQ.xml");
	}

	@Test
	public void hadesIeee57() throws WorkflowCreationException
	{
		hades("ieee57", "ieee57bus_EQ.xml");
	}

	@Test
	public void hadesIeee118() throws WorkflowCreationException
	{
		hades("ieee118", "ieee118bus_EQ.xml");
	}

	@Test
	public void hadesNordic32() throws WorkflowCreationException
	{
		hades("Nordic32", "Nordic32_EQ.xml");
	}

	@Test
	public void hades7buses() throws WorkflowCreationException
	{
		hades("7buses", "CIM_7buses_EQ.xml");
	}

	@Test
	public void hadesSmallcase1() throws WorkflowCreationException
	{
		hades("smallcase1", "case1_EQ.xml");
	}

	@Test
	public void hadesSmallcase2() throws WorkflowCreationException
	{
		hades("smallcase2", "case2_EQ.xml");
	}

	@Test
	public void hadesSmallcase3() throws WorkflowCreationException
	{
		hades("smallcase3", "case3_EQ.xml");
	}

	@Test
	public void helmflowIeee14() throws WorkflowCreationException
	{
		helmflow("ieee14", "ieee14bus_EQ.xml");
	}

	@Test
	public void helmflowIeee30() throws WorkflowCreationException
	{
		helmflow("ieee30", "ieee30bus_EQ.xml");
	}

	@Test
	public void helmflowIeee57() throws WorkflowCreationException
	{
		helmflow("ieee57", "ieee57bus_EQ.xml");
	}

	@Test
	public void helmflowIeee118() throws WorkflowCreationException
	{
		helmflow("ieee118", "ieee118bus_EQ.xml");
	}

	@Test
	public void helmflowNordic32() throws WorkflowCreationException
	{
		helmflow("Nordic32", "Nordic32_EQ.xml");
	}

	@Test
	public void helmflow7buses() throws WorkflowCreationException
	{
		helmflow("7buses", "M7buses_EQ.xml");
	}

	@Test
	public void helmflowSmallcase1() throws WorkflowCreationException
	{
		helmflow("smallcase1", "case1_EQ.xml");
	}

	@Test
	public void helmflowSmallcase2() throws WorkflowCreationException
	{
		helmflow("smallcase2", "case2_EQ.xml");
	}

	@Test
	public void helmflowSmallcase3() throws WorkflowCreationException
	{
		helmflow("smallcase3", "case3_EQ.xml");
	}

	public void helmflow(String caseFolder, String caseName) throws WorkflowCreationException
	{
		String case_ = TEST_SAMPLES.resolve(caseFolder).resolve(caseName).toString();
		String targetCsvFolder = DATA_TMP.resolve("helmflow").resolve(caseFolder).toString();
		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", case_)),
				TD(LoadFlowTask.class, "loadflow0",
						TC("loadFlowFactoryClass", HELMFLOW_FACTORY,
								"targetCsvFolder", targetCsvFolder)));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
	}

	public void hades(String caseFolder, String caseName) throws WorkflowCreationException
	{
		if (!isHades2Available()) return;

		String case_ = TEST_SAMPLES.resolve(caseFolder).resolve(caseName).toString();
		String targetCsvFolder = DATA_TMP.resolve("hades").resolve(caseFolder).toString();
		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", case_)),
				TD(LoadFlowTask.class, "loadflow0",
						TC("loadFlowFactoryClass", HADES2_FACTORY,
								"targetCsvFolder", targetCsvFolder)));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
	}

	@Test
	public void compareHelmflowHades2Ieee14() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("ieee14", "ieee14bus_EQ.xml");
	}

	@Test
	public void compareHelmflowHades2Ieee30() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("ieee30", "ieee30bus_EQ.xml");
	}

	@Test
	public void compareHelmflowHades2Ieee57() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("ieee57", "ieee57bus_EQ.xml");
	}

	@Test
	public void compareHelmflowHades2Ieee118() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("ieee118", "ieee118bus_EQ.xml");
	}

	@Test
	public void compareHelmflowHades27Buses() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("7buses", "M7buses_EQ.xml");
	}

	@Test
	public void compareHelmflowHades2Nordic32() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("Nordic32", "Nordic32_EQ.xml");
	}

	@Test
	public void compareHelmflowHades2smallcase1() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("smallcase1", "case1_EQ.xml");
	}

	@Test
	public void compareHelmflowHades2smallcase2() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("smallcase2", "case2_EQ.xml");
	}

	@Test
	public void compareHelmflowHades2smallcase3() throws WorkflowCreationException, IOException
	{
		compareHelmflowHades2("smallcase3", "case3_EQ.xml");
	}

	void compareHelmflowHades2(String caseFolder, String caseName)
			throws WorkflowCreationException, IOException
	{
		if (!isHades2Available()) return;
		String caseFilename = TEST_SAMPLES.resolve(caseFolder).resolve(caseName).toString();
		String targetCsvFolderName = System.getProperty("loadFlowTest.targetCsvFolder");
		String targetCsvFolderNameHades = null;
		String targetCsvFolderNameHelm = null;
		if (targetCsvFolderName != null)
		{
			Path targetCsvFolder = Paths.get(targetCsvFolderName);
			Path targetCsvFolderHades = targetCsvFolder.resolve("hades").resolve(caseFolder);
			Path targetCsvFolderHelm = targetCsvFolder.resolve("helmflow").resolve(caseFolder);
			Files.createDirectories(targetCsvFolder);
			Files.createDirectories(targetCsvFolderHades);
			Files.createDirectories(targetCsvFolderHelm);
			targetCsvFolderNameHades = targetCsvFolderHades.toAbsolutePath().toString();
			targetCsvFolderNameHelm = targetCsvFolderHelm.toAbsolutePath().toString();
		}
		String helmSourceStateId = System.getProperty("loadFlowTest.helmSourceStateId");
		LOG.info("compareHelmFlowHades2 " + caseFolder);
		LOG.info("    targetCvsFolderName = " + targetCsvFolderName);
		LOG.info("    helmSourceStateId   = " + helmSourceStateId);

		Workflow wf = WF(
				TD(StaticNetworkImporterTask.class, "importer0",
						TC("source", caseFilename)),
				TD(LoadFlowTask.class, "loadflowHades2",
						TC("loadFlowFactoryClass", HADES2_FACTORY,
								"targetStateId", "resultsHades2",
								"targetCsvFolder", targetCsvFolderNameHades)),
				TD(LoadFlowTask.class, "loadflowHelmflow",
						TC("loadFlowFactoryClass", HELMFLOW_FACTORY,
								"sourceStateId", helmSourceStateId,
								"targetStateId", "resultsHelmflow",
								"targetCsvFolder", targetCsvFolderNameHelm)));
		wf.start();
		assertEquals(SUCCESS, wf.getTaskStates().get(0).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(1).state);
		assertEquals(SUCCESS, wf.getTaskStates().get(2).state);

		Network n = (Network) wf.getResults("network");
		assertNotNull(n);

		n.getStateManager().allowStateMultiThreadAccess(false);

		// Compare HELM Flow results with inputs but do not assert
		Map<String, Map<String, float[]>> allBusesValuesHF0 = gatherBusesValues(
				n,
				"resultsHelmflow",
				StateManager.INITIAL_STATE_ID);
		checkResults(caseFolder, "HF-0 ", allBusesValuesHF0, "P");
		checkResults(caseFolder, "HF-0 ", allBusesValuesHF0, "Q");
		checkResults(caseFolder, "HF-0 ", allBusesValuesHF0, "A");
		checkResults(caseFolder, "HF-0 ", allBusesValuesHF0, "V");

		// Compare Hades2 results with inputs but do not assert
		Map<String, Map<String, float[]>> allBusesValuesHD20 = gatherBusesValues(
				n,
				"resultsHades2",
				StateManager.INITIAL_STATE_ID);
		checkResults(caseFolder, "HD2-0", allBusesValuesHD20, "P");
		checkResults(caseFolder, "HD2-0", allBusesValuesHD20, "Q");
		checkResults(caseFolder, "HD2-0", allBusesValuesHD20, "A");
		checkResults(caseFolder, "HD2-0", allBusesValuesHD20, "V");

		// Compare between HELM Flow and Hades2 and assert differences should be lower than ...
		Map<String, Map<String, float[]>> allBusesValues = gatherBusesValues(
				n,
				"resultsHelmflow",
				"resultsHades2");
		checkResults(caseFolder, "HF-HD", allBusesValues, "P");
		checkResults(caseFolder, "HF-HD", allBusesValues, "Q");
		checkResults(caseFolder, "HF-HD", allBusesValues, "A");
		DoubleSummaryStatistics stats = checkResults(caseFolder, "HF-HD", allBusesValues, "V");
		if (stats != null)
		{
			assertTrue(stats.getAverage() < getMaxRelativeErrorAverage("V"));
			assertTrue(stats.getMax() < getMaxRelativeErrorMax("V"));
		}
	}

	private Map<String, Map<String, float[]>> gatherBusesValues(
			Network n,
			String caseId0,
			String caseId1)
	{
		Map<String, Map<String, float[]>> allBusesValues = new HashMap<>();
		n.getBusBreakerView().getBuses().forEach(b -> {
			Map<String, float[]> bvalues = new HashMap<>();
			float[] Vs = new float[2];
			float[] As = new float[2];
			float[] Ps = new float[2];
			float[] Qs = new float[2];
			n.getStateManager().setWorkingState(caseId0);
			Vs[0] = b.getV();
			As[0] = b.getAngle();
			Ps[0] = b.getP();
			Qs[0] = b.getQ();
			n.getStateManager().setWorkingState(caseId1);
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
		return allBusesValues;
	}

	private DoubleSummaryStatistics checkResults(
			String case_,
			String label,
			Map<String, Map<String, float[]>> allBusesValues,
			String variable)
	{
		float maxRelativeErrorAverage = getMaxRelativeErrorAverage(variable);
		float maxRelativeErrorMax = getMaxRelativeErrorMax(variable);

		List<Float> relativeErrors = calcRelativeErrors(allBusesValues, variable);
		DoubleSummaryStatistics stats = relativeErrors.stream()
				.collect(Collectors.summarizingDouble(Float::doubleValue));

		debugValues(case_, allBusesValues, variable, relativeErrors);
		System.err.printf("LF_DIFF_STATS_AVG\t%s\t%s\t%s\t%f\t%f\t%s%n",
				case_,
				label,
				variable,
				stats.getAverage(),
				maxRelativeErrorAverage,
				stats.getAverage() < maxRelativeErrorAverage ? "PASS" : "FAIL");
		System.err.printf("LF_DIFF_STATS_MAX\t%s\t%s\t%s\t%f\t%f\t%s%n",
				case_,
				label,
				variable,
				stats.getMax(),
				maxRelativeErrorMax,
				stats.getMax() < maxRelativeErrorMax ? "PASS" : "FAIL");
		return stats;
	}

	private void debugValues(
			String case_,
			Map<String, Map<String, float[]>> allBusesValues,
			String variable,
			List<Float> relativeErrors)
	{
		List<Float> values0 = allBusesValues.values().stream()
				.map(bv -> bv.get(variable)[0])
				.collect(Collectors.toList());
		List<Float> values1 = allBusesValues.values().stream()
				.map(bv -> bv.get(variable)[1])
				.collect(Collectors.toList());

		System.err.printf("LF_DIFF_VALUES\t%s\t%s\tHELM Flow\tHades2\trelative Error%n", case_,
				variable);
		for (int k = 0; k < values0.size(); k++)
		{
			System.err.printf("LF_DIFF_VALUES\t%s\t%s\t%f\t%f\t%f%n",
					case_,
					variable,
					values0.get(k),
					values1.get(k),
					relativeErrors.get(k));
		}
	}

	private List<Float> calcRelativeErrors(
			Map<String, Map<String, float[]>> allBusesValues,
			String variable)
	{
		return allBusesValues.values().stream().map(bv -> {
			float[] values = bv.get(variable);
			float err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			err = Math.abs(err);
			return err;
		}).collect(Collectors.toList());
	}

	private boolean isHades2Available()
	{
		// Hades is only available for Linux
		return System.getProperty("os.name").startsWith("Linux");
	}

	private float getMaxRelativeErrorAverage(String variable)
	{
		switch (variable)
		{
		case "A":
		case "V":
		case "P":
		case "Q":
		default:
			return LOADFLOW_MAX_RELATIVE_ERROR_AVG;
		}
	}

	private float getMaxRelativeErrorMax(String variable)
	{
		switch (variable)
		{
		case "A":
		case "V":
		case "P":
		case "Q":
		default:
			return LOADFLOW_MAX_RELATIVE_ERROR_MAX;
		}
	}

	private static final String	HELMFLOW_FACTORY	= "com.elequant.helmflow.ipst.HelmFlowFactory";
	private static final String	HADES2_FACTORY		= "com.rte_france.itesla.hades2.Hades2Factory";
	private static final float	LOADFLOW_MAX_RELATIVE_ERROR_AVG;
	private static final float	LOADFLOW_MAX_RELATIVE_ERROR_MAX;
	static
	{
		String savg = System.getProperty("loadFlowTest.maxRelativeErrorAvg");
		if (savg == null) LOADFLOW_MAX_RELATIVE_ERROR_AVG = 1e-3f;
		else LOADFLOW_MAX_RELATIVE_ERROR_AVG = Float.valueOf(savg);
		String smax = System.getProperty("loadFlowTest.maxRelativeErrorMax");
		if (smax == null) LOADFLOW_MAX_RELATIVE_ERROR_MAX = 1e-3f;
		else LOADFLOW_MAX_RELATIVE_ERROR_MAX = Float.valueOf(smax);
	}

	private static final Logger LOG = LoggerFactory.getLogger(LoadFlowTest.class);
}
