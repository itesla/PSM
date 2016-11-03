package org.power_systems_modelica.psm.gui.service;

import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event.ActionEvent;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Network;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkflowService {

	public enum LoadflowEngine {
		HADES2(0), HELMFLOW(1);

		private int value;

		private LoadflowEngine(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public enum DsEngine {
		DYMOLA(0), OPENMODELICA(1);

		private int value;

		private DsEngine(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public static Workflow getWorkflow() {

		return w;
	}

	public static Workflow getCompareLoadflow() {
		return cl;
	}

	public static ObservableList<LoadflowEngine> getLoadflowEngines() {

		ObservableList<LoadflowEngine> engines = FXCollections.observableArrayList();
		engines.add(LoadflowEngine.HADES2);
		engines.add(LoadflowEngine.HELMFLOW);

		return engines;
	}

	public static ObservableList<DsEngine> getDsEngines() {

		ObservableList<DsEngine> engines = FXCollections.observableArrayList();
		engines.add(DsEngine.DYMOLA);
		engines.add(DsEngine.OPENMODELICA);

		return engines;
	}

	public static ObservableList<ActionEvent> getActionEvents() {

		ObservableList<ActionEvent> actions = FXCollections.observableArrayList();
		actions.add(ActionEvent.OPEN);
		actions.add(ActionEvent.CLOSE);
		actions.add(ActionEvent.MODIFY);

		return actions;
	}

	public static Workflow createWorkflow(Catalog ctlg, Case cs, Ddr ddr, LoadflowEngine le,
			boolean onlyMainConnectedComponent, ObservableList events, DsEngine dse) throws WorkflowCreationException {

		try {
			Path casePath = findCasePath(Paths.get(cs.getLocation()));

			String loadflowId = le.equals(LoadflowEngine.HADES2) ? "loadflowHades2" : "loadflowHelmflow";
			String loadflowClass = le.equals(LoadflowEngine.HADES2) ? "com.rte_france.itesla.hades2.Hades2Factory"
					: "com.elequant.helmflow.ipst.HelmFlowFactory";

			w = WF(TD(StaticNetworkImporterTask.class, "importer0", TC("source", casePath.toString())),
					TD(LoadFlowTask.class, loadflowId,
							TC("loadFlowFactoryClass", loadflowClass, "targetStateId", "resultsLoadflow")));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return w;
	}

	public static WorkflowResult getWorkflowResult(String id) {

		WorkflowResult results = new WorkflowResult();

		Network n = (Network) w.getResults("network");
		
		// Fix temporal
		n.getStateManager().setWorkingState("resultsLoadflow");
		// Fin fix temporal
		
		n.getStateManager().allowStateMultiThreadAccess(false);
		n.getStateManager().setWorkingState("resultsLoadflow");
		List<BusData> allBusesValues = new ArrayList<>();
		n.getBusBreakerView().getBuses().forEach(b -> {
			Map<String, float[]> bvalues = new HashMap<>();
			float[] Vs = new float[1];
			float[] As = new float[1];
			float[] Ps = new float[1];
			float[] Qs = new float[1];

			Vs[0] = b.getV();
			As[0] = b.getAngle();
			Ps[0] = b.getP();
			Qs[0] = b.getQ();
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);
			allBusesValues.add(new BusData(b.getId(), b.getName(), bvalues));
		});

		results.setId(id);
		results.setAllBusesValues(allBusesValues);

		return results;
	}

	public static Workflow createCompareLoadflows(Catalog ctlg, Case cs, Ddr ddr, boolean generatorsReactiveLimits)
			throws WorkflowCreationException {

		try {
			Path casePath = findCasePath(Paths.get(cs.getLocation()));

			cl = WF(TD(StaticNetworkImporterTask.class, "importer0", TC("source", casePath.toString())),
					TD(LoadFlowTask.class, "loadflowHelmflow",
							TC("loadFlowFactoryClass", "com.elequant.helmflow.ipst.HelmFlowFactory", "targetStateId",
									"resultsHelmflow")),
					TD(LoadFlowTask.class, "loadflowHades2", TC("loadFlowFactoryClass",
							"com.rte_france.itesla.hades2.Hades2Factory", "targetStateId", "resultsHades2")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cl;
	}

	public static WorkflowResult getCompareLoadflowsResult(String id) {

		WorkflowResult results = new WorkflowResult();

		Network n = (Network) cl.getResults("network");
		
		// Fix temporal
		n.getStateManager().setWorkingState("resultsHelmflow");
		// Fin fix temporal
		
		n.getStateManager().allowStateMultiThreadAccess(false);

		List<BusData> allBusesValues = new ArrayList<>();
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
			allBusesValues.add(new BusData(b.getId(), b.getName(), bvalues));

		});

		allBusesValues.forEach(bv -> {
			float[] values = bv.getData().get("V");
			float err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError(err);
		});
		DoubleSummaryStatistics stats = allBusesValues.stream().map(BusData::getError)
				.collect(Collectors.summarizingDouble(Float::doubleValue));

		results.setId(id);
		results.setAllBusesValues(allBusesValues);
		results.setStats(stats);

		return results;
	}

	private static Path findCasePath(Path path) throws IOException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (entry.toString().endsWith("ME.xml"))
					return entry;
				else if (entry.toString().endsWith("EQ.xml"))
					return entry;
			}
		}

		return null;
	}

	private static Random rnd = new Random();
	private static Workflow w = null;
	private static Workflow cl = null;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowService.class);

}
