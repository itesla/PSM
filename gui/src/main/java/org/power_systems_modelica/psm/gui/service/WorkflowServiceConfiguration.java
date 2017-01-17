package org.power_systems_modelica.psm.gui.service;

import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.EventParameter;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.utils.CsvReader;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.TaskFactory;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.ConnectableType;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkflowServiceConfiguration
{

	public enum LoadflowEngine
	{
		HADES2(0), HELMFLOW(1), NONE(2);

		private int value;

		private LoadflowEngine(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	public enum DsEngine
	{
		DYMOLA(0), OPENMODELICA(1);

		private int value;

		private DsEngine(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	public static Workflow getConversion()
	{
		return conv;
	}

	public static Workflow getSimulation()
	{
		return sim;
	}

	public static Workflow getCompareLoadflow()
	{
		return cl;
	}

	public static ObservableList<LoadflowEngine> getLoadflowEngines()
	{

		ObservableList<LoadflowEngine> engines = FXCollections.observableArrayList();
		engines.add(LoadflowEngine.HADES2);
		engines.add(LoadflowEngine.HELMFLOW);
		engines.add(LoadflowEngine.NONE);

		return engines;
	}

	public static ObservableList<DsEngine> getDsEngines()
	{

		ObservableList<DsEngine> engines = FXCollections.observableArrayList();
		engines.add(DsEngine.DYMOLA);
		engines.add(DsEngine.OPENMODELICA);

		return engines;
	}

	public static ObservableList<String> getActionEvents(ConvertedCase newValue)
	{
		ObservableList<String> actions = FXCollections.observableArrayList();
		ddr = DynamicDataRepositoryMainFactory.create("DYD", newValue.getDdrLocation());
		try
		{
			ddr.connect();

			actions.addAll(ddr.getEvents());
		}
		catch (ConnectionException e)
		{
			LOG.error(e.getMessage());
		}

		return actions;
	}

	// Elements of current case that can be used with current event

	// TODO This configuration should be read from events.dyd
	// Each event should name the type of static network elements it applies to
	private static final Map<String, ConnectableType> EVENT_APPLIES_TO = new HashMap<>();
	static
	{
		EVENT_APPLIES_TO.put("BusFault", ConnectableType.BUSBAR_SECTION);
		EVENT_APPLIES_TO.put("LineFault", ConnectableType.LINE);
		EVENT_APPLIES_TO.put("LineOpenReceiverSide", ConnectableType.LINE);
		EVENT_APPLIES_TO.put("LineOpenBothSides", ConnectableType.LINE);
		EVENT_APPLIES_TO.put("BankModification", ConnectableType.SHUNT_COMPENSATOR);
		EVENT_APPLIES_TO.put("LoadVariation", ConnectableType.LOAD);
		EVENT_APPLIES_TO.put("GeneratorSetpointModification", ConnectableType.GENERATOR);
	}

	private static ConnectableType connectableType(Identifiable<?> e)
	{
		return ((Connectable<?>) e).getType();
	}

	private static Map<String, Collection<String>> groupElementsByEventType(ConvertedCase case_)
	{
		// Import case
		Path caseSource;
		try
		{
			caseSource = PathUtils.findCasePath(Paths.get(case_.getLocation()));
		}
		catch (IOException e1)
		{
			return null;
		}
		Network n = StaticNetworkImporter.import_(caseSource);
		if (n == null) return null;

		// Group elements by ConnectableType
		Map<ConnectableType, List<String>> elementsByConnectableType = n.getIdentifiables().stream()
				.filter(e -> e instanceof Connectable)
				.collect(Collectors.groupingBy(WorkflowServiceConfiguration::connectableType,
						Collectors.mapping(Identifiable::getId, Collectors.toList())));
		// Expand the elements located under "Bus bar section" with buses resulting from bus breaker view
		List<String> busIds = elementsByConnectableType.get(ConnectableType.BUSBAR_SECTION);
		if (busIds == null)
		{
			busIds = new ArrayList<>();
			elementsByConnectableType.put(ConnectableType.BUSBAR_SECTION, busIds);
		}
		for (Bus b : n.getBusBreakerView().getBuses())
			busIds.add(b.getId());

		// Now for each event type, set the list of applicable elements based on the connectable type defined for the event
		Map<String, Collection<String>> elementsByEventType = new HashMap<>();
		EVENT_APPLIES_TO.entrySet().stream()
				.forEach(e -> elementsByEventType.put(
						e.getKey(),
						elementsByConnectableType.get(e.getValue())));
		return elementsByEventType;
	}

	public static ObservableList<String> getNetworkElements(ConvertedCase case_, String eventType)
	{
		ObservableList<String> elements = FXCollections.observableArrayList();

		// Cached list of element identifiers by event type is stored in ConvertedCase
		Map<String, Collection<String>> elementsByEventType = case_.getElementIdsByEventType();
		if (elementsByEventType == null)
		{
			case_.setElementIdentifiersByEventType(groupElementsByEventType(case_));
			elementsByEventType = case_.getElementIdsByEventType();
		}
		Collection<String> ids = elementsByEventType.get(eventType);
		if (ids != null)
		{
			elements.addAll(ids);
		}
		else
		{
			LOG.warn("The event type " + eventType
					+ " does not have elements to be applied to in current case");
		}
		return elements;
	}

	public static ObservableList<EventParamGui> getEventParams(String event)
	{

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

	public static Workflow createSimulation(ConvertedCase cs, ObservableList<Event> events,
			DsEngine dse,
			String stopTime, boolean onlyCheck, boolean onlyVerify)
			throws WorkflowCreationException
	{
		String moInput = Paths.get(cs.getLocation()).resolve(cs.getName() + ".mo").toString();
		Path modelicaEngineWorkingDir = PathUtils.DATA_TMP.resolve("gui_workflow_moengine_working");
		Path output = PathUtils.DATA_TMP.resolve("gui_workflow_event_adder_initial.mo");
		Path outputev = PathUtils.DATA_TMP.resolve("gui_workflow_event_adder_events.mo");

		try
		{
			Files.deleteIfExists(output);
			Files.deleteIfExists(outputev);
			Files.createDirectories(modelicaEngineWorkingDir);

			String simulationEngine = dse.equals(DsEngine.OPENMODELICA) ? "OpenModelica" : "Dymola";
			String simulationSource = "mo";
			String resultVariables = "bus[a-zA-Z0-9_]*.(V|angle)";

			List<TaskDefinition> tasks = new ArrayList<TaskDefinition>();
			Path casePath = PathUtils.findCasePath(Paths.get(cs.getLocation()));

			tasks.add(TD(StaticNetworkImporterTask.class, "importer0",
					TC("source", casePath.toString())));
			tasks.add(TD(ModelicaParserTask.class, "moparser0",
					TC("source", moInput, "modelicaDocument", "mo")));
			tasks.add(TD(ModelicaExporterTask.class, "exporter0",
					TC("source", "mo",
							"target", output.toString(),
							"includePsmAnnotations", "true")));

			if (!events.isEmpty())
			{
				tasks.add(TD(ModelicaEventAdderTask.class, "eventAdder0",
						TC("network", "network",
								"modelicaDocument", "mo",
								"ddrType", "DYD",
								"ddrLocation", cs.getDdrLocation(),
								"events", (String) events.stream().map(Object::toString)
										.collect(Collectors.joining("\n")))));
				tasks.add(TD(ModelicaExporterTask.class, "exporter1",
						TC("source", "moWithEvents",
								"target", outputev.toString(),
								"includePsmAnnotations", "true")));

				simulationSource = "moWithEvents";
			}

			String depth;
			if (onlyCheck)
				depth = "1";
			else if (onlyVerify)
				depth = "2";
			else
				depth = "0";

			tasks.add(TD(ModelicaSimulatorTask.class, simulationEngine,
					TC("source", simulationSource,
							"modelicaEngine", simulationEngine,
							"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
							"stopTime", stopTime,
							"libraryDir", PathUtils.LIBRARY.toString(),
							"resultVariables", resultVariables,
							"depth", depth)));

			WorkflowConfiguration config = new WorkflowConfiguration();
			config.setTaskDefinitions(tasks);
			TaskFactory tf = new TaskFactory();
			sim = Workflow.create(config, tf);
		}
		catch (IOException e)
		{
			LOG.error(e.getMessage());
		}
		return sim;
	}

	public static Workflow createConversion(Case cs, Ddr ddr0, LoadflowEngine le,
			boolean onlyMainConnectedComponent)
			throws WorkflowCreationException
	{

		String fakeInit = Paths.get(ddr0.getLocation()).resolve("fake_init.csv").toString();
		Path modelicaEngineWorkingDir = PathUtils.DATA_TMP.resolve("gui_workflow_moengine_working");
		String outname = Paths.get(cs.getLocation()).resolve(cs.getName() + ".mo").toString();

		try
		{
			if (Files.exists(modelicaEngineWorkingDir, LinkOption.NOFOLLOW_LINKS))
			{
				Files.walk(modelicaEngineWorkingDir, FileVisitOption.FOLLOW_LINKS)
						.sorted(Comparator.reverseOrder())
						.map(Path::toFile)
						.peek(System.out::println)
						.forEach(File::delete);
			}
			Files.createDirectories(modelicaEngineWorkingDir);
			Files.deleteIfExists(Paths.get(outname));

			Path casePath = PathUtils.findCasePath(Paths.get(cs.getLocation()));

			String loadflowId;
			String loadflowClass;
			switch (le)
			{
			case HADES2:
				loadflowId = "loadflowHades2";
				loadflowClass = "com.rte_france.itesla.hades2.Hades2Factory";
				break;
			case HELMFLOW:
				loadflowId = "loadflowHelmflow";
				loadflowClass = "com.elequant.helmflow.ipst.HelmFlowFactory";
				break;
			default:
				loadflowId = null;
				loadflowClass = null;
				break;
			}

			List<TaskDefinition> tasks = new ArrayList<TaskDefinition>();

			tasks.add(TD(StaticNetworkImporterTask.class, "importer0",
					TC("source", casePath.toString())));
			if (loadflowClass != null)
			{
				tasks.add(TD(LoadFlowTask.class, loadflowId,
						TC("loadFlowFactoryClass", loadflowClass)));
			}
			tasks.add(TD(ModelicaNetworkBuilderTask.class, "modelica0",
					TC("ddrType", "DYD",
							"ddrLocation", ddr0.getLocation(),
							"onlyMainConnectedComponent",
							Boolean.toString(onlyMainConnectedComponent),
							"modelicaEngine", "Fake",
							"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
							"fakeModelicaEngineResults", fakeInit)));
			tasks.add(TD(ModelicaExporterTask.class, "exporter0",
					TC("source", "mo",
							"target", outname,
							"includePsmAnnotations", "true")));

			WorkflowConfiguration config = new WorkflowConfiguration();
			config.setTaskDefinitions(tasks);
			TaskFactory tf = new TaskFactory();
			conv = Workflow.create(config, tf);

		}
		catch (IOException e)
		{
			LOG.error(e.getMessage());
		}

		return conv;
	}

	public static WorkflowResult getSimulationResult(String id)
	{

		WorkflowResult results = new WorkflowResult();

		Network n = (Network) sim.getResults("network");

		/*
		 * LUMA: no specific state for loadflow results, use current network state
		 * 
		 * // Fix temporal n.getStateManager().setWorkingState("resultsLoadflow"); // Fin fix temporal
		 * 
		 * n.getStateManager().allowStateMultiThreadAccess(false); n.getStateManager().setWorkingState("resultsLoadflow");
		 */
		List<BusData> allBusesValues = new ArrayList<>();
		n.getBusBreakerView().getBuses().forEach(b -> {
			Map<String, float[]> bvalues = new HashMap<>();
			float[] Vs = new float[1];
			float[] As = new float[1];
			float[] Ps = new float[1];
			float[] Qs = new float[1];

			Vs[0] = b.getV() / b.getVoltageLevel().getNominalV();
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

		try
		{
			Map<String, List<DsData>> values = CsvReader.readVariableColumnsWithCsvListReader(
					sim.getResults("simres").toString(), ".csv");
			results.setDsValues(values);
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}

		return results;
	}

	public static Workflow createCompareLoadflows(Case cs, boolean generatorsReactiveLimits)
			throws WorkflowCreationException
	{
		try
		{
			Path casePath = PathUtils.findCasePath(Paths.get(cs.getLocation()));

			// TODO Allow the user change this from the user interface, flag similar to reactive limits
			boolean helmflowFromHadesResults = Boolean.valueOf(Optional
					.ofNullable(System.getProperty("helmflowFromHadesResults"))
					.orElse("false"));
			String helmSourceStateId = null;
			if (helmflowFromHadesResults) helmSourceStateId = "resultsHades2";

			cl = WF(TD(StaticNetworkImporterTask.class, "importer0",
					TC("source", casePath.toString())),
					TD(LoadFlowTask.class, "loadflowHades2",
							TC("loadFlowFactoryClass", "com.rte_france.itesla.hades2.Hades2Factory",
									"targetStateId", "resultsHades2")),
					TD(LoadFlowTask.class, "loadflowHelmflow",
							TC("loadFlowFactoryClass", "com.elequant.helmflow.ipst.HelmFlowFactory",
									"sourceStateId", helmSourceStateId,
									"targetStateId", "resultsHelmflow")));
		}
		catch (IOException e)
		{
			LOG.error(e.getMessage());
		}

		return cl;
	}

	public static WorkflowResult getCompareLoadflowsResult(String id)
	{

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
			Vs[0] = b.getV() / b.getVoltageLevel().getNominalV();
			As[0] = b.getAngle();
			Ps[0] = b.getP();
			Qs[0] = b.getQ();
			n.getStateManager().setWorkingState("resultsHades2");
			Vs[1] = b.getV() / b.getVoltageLevel().getNominalV();
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

	private static Workflow					conv	= null;
	private static Workflow					sim		= null;
	private static Workflow					cl		= null;
	private static DynamicDataRepository	ddr		= null;

	private static final Logger				LOG		= LoggerFactory
			.getLogger(WorkflowServiceConfiguration.class);
}
