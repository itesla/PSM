package org.power_systems_modelica.psm.gui.service;

import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.EventParameter;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.TaskFactory;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Network;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkflowService {

	public static final Path	DATA_TMP		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
	
	public static final Path	LIBRARY		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test").resolve("library");
	
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

	public static ObservableList<String> getActionEvents(Ddr intput) {

		ObservableList<String> actions = FXCollections.observableArrayList();
		ddr = DynamicDataRepositoryMainFactory.create("DYD", intput.getLocation());
		try {
			ddr.connect();
			
			actions.addAll(ddr.getEvents());
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return actions;
	}

	public static ObservableList<EventParamGui> getEventParams(String event) {
		
		ObservableList<EventParamGui> eventParams = FXCollections.observableArrayList();
		List<EventParameter> parameters = ddr.getEventParameters(event);
		
		parameters.forEach(p -> {
			EventParamGui ep = new EventParamGui();
			ep.setName(p.getName() + " (" + p.getUnit() + ")");
			ep.setUnit(p.getUnit());
			ep.setValue("");
			eventParams.add(ep);
		});
		
		return eventParams;
	}

	public static Workflow createWorkflow(Case cs, Ddr ddr0, LoadflowEngine le,
			boolean onlyMainConnectedComponent, ObservableList events, DsEngine dse) throws WorkflowCreationException {

		String fakeInit = Paths.get(ddr0.getLocation()).resolve("fake_init.csv").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("moBuilder");
		new File(modelicaEngineWorkingDir.toString()).mkdir();
		String outname = DATA_TMP.resolve("eventAdder_initial.mo").toString();
		String outnameev = DATA_TMP.resolve("eventAdder_events.mo").toString();
		
		try {
			Path casePath = Utils.findCasePath(Paths.get(cs.getLocation()));

			String loadflowId = le.equals(LoadflowEngine.HADES2) ? "loadflowHades2" : "loadflowHelmflow";
			String loadflowClass = le.equals(LoadflowEngine.HADES2) ? "com.rte_france.itesla.hades2.Hades2Factory"
					: "com.elequant.helmflow.ipst.HelmFlowFactory";
			String simulationEngine = dse.equals(DsEngine.OPENMODELICA) ? "OpenModelica" : "Dymola";
			String simulationSource = "mo";
			String resultVariables = "V,angle";
			
			List<TaskDefinition> tasks = new ArrayList<TaskDefinition>();
			
			tasks.add(TD(StaticNetworkImporterTask.class, "importer0", 
					TC("source", casePath.toString())));
			tasks.add(TD(LoadFlowTask.class, loadflowId,
					TC("loadFlowFactoryClass", loadflowClass, 
							"targetStateId", "resultsLoadflow")));
			tasks.add(TD(ModelicaNetworkBuilderTask.class, "modelica0",
					TC("ddrType", "DYD",
							"ddrLocation", ddr0.getLocation(),
							"onlyMainConnectedComponent", Boolean.toString(onlyMainConnectedComponent),
							"modelicaEngine", "Fake",
							"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
							"fakeModelicaEngineResults", fakeInit)));
			tasks.add(TD(ModelicaExporterTask.class, "exporter0",
					TC("source", "mo",
							"target", outname,
							"includePsmAnnotations", "true")));
			if (!events.isEmpty()) {
				tasks.add(TD(ModelicaEventAdderTask.class, "eventAdder0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr0.getLocation(),
								"events", (String) events.stream().map(Object::toString).collect(Collectors.joining("\n")))));
				tasks.add(TD(ModelicaExporterTask.class, "exporter1",
						TC("source", "moWithEvents",
								"target", outnameev,
								"includePsmAnnotations", "true")));
				
				simulationSource = "moWithEvents";
			}
			
			tasks.add(TD(ModelicaSimulatorTask.class, "dynamicsim0",
					TC("source", simulationSource,
						"modelicaEngine", simulationEngine,
						"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
						"libraryDir", LIBRARY.toString(),
						"resultVariables", resultVariables)));

			WorkflowConfiguration config = new WorkflowConfiguration();
			config.setTaskDefinitions(tasks);
			TaskFactory tf = new TaskFactory();
			w = Workflow.create(config, tf);

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
		
		try {
			Map<String, List<DsData>> values = Utils.readVariableColumnsWithCsvListReader(w.getResults("simres").toString(), ".csv");
			results.setDsValues(values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	public static Workflow createCompareLoadflows(Case cs, boolean generatorsReactiveLimits)
			throws WorkflowCreationException {

		try {
			Path casePath = Utils.findCasePath(Paths.get(cs.getLocation()));

			cl = WF(TD(StaticNetworkImporterTask.class, "importer0", TC("source", casePath.toString())),
					TD(LoadFlowTask.class, "loadflowHelmflow",
							TC("loadFlowFactoryClass", "com.elequant.helmflow.ipst.HelmFlowFactory", 
									"targetStateId", "resultsHelmflow")),
					TD(LoadFlowTask.class, "loadflowHades2", 
							TC("loadFlowFactoryClass", "com.rte_france.itesla.hades2.Hades2Factory", 
									"targetStateId", "resultsHades2")));
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
			bv.setError("V", err);
			values = bv.getData().get("A");
			err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError("A", err);
			values = bv.getData().get("P");
			err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError("P", err);
			values = bv.getData().get("Q");
			err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError("Q", err);
		});
		
		results.setId(id);
		results.setAllBusesValues(allBusesValues);

		return results;
	}

	private static Random rnd = new Random();
	private static Workflow w = null;
	private static Workflow cl = null;
	private static DynamicDataRepository ddr = null;

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowService.class);
}
