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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.EventParameter;
import org.power_systems_modelica.psm.ddr.StaticType;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.model.Validation;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.utils.CsvReader;
import org.power_systems_modelica.psm.gui.utils.CsvReaderPopulator;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
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
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask.ElementModel;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTaskResults;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.power_systems_modelica.psm.workflow.psm.SwtoswValidationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.ICsvListReader;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

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

		@Override
		public String toString()
		{
			switch (value)
			{
			case 0:
				return "Hades";
			case 1:
				return "HELM Flow";
			case 2:
				return "None";
			}
			return null;
		}
	}

	public enum DsEngine
	{
		DYMOLA(0), OPENMODELICA(1), FAKE(2);

		private int value;

		private DsEngine(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}

		@Override
		public String toString()
		{
			switch (value)
			{
			case 0:
				return "Dymola";
			case 1:
				return "Open Modelica";
			case 2:
				return "Fake";
			}
			return null;
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

	public static List<LoadflowEngine> getLoadflowEngines()
	{

		List<LoadflowEngine> engines = new ArrayList<>();
		engines.add(LoadflowEngine.HADES2);
		engines.add(LoadflowEngine.HELMFLOW);
		engines.add(LoadflowEngine.NONE);

		return engines;
	}

	public static List<DsEngine> getDsEngines()
	{

		List<DsEngine> engines = new ArrayList<>();
		engines.add(DsEngine.FAKE);
		engines.add(DsEngine.DYMOLA);
		engines.add(DsEngine.OPENMODELICA);

		return engines;
	}

	public static List<String> getAvailableEvents(ConvertedCase newValue)
	{
		// TODO do not connect to the DDR each time, store the connected DDR reference inside ConvertedCase ?
		List<String> actions = new ArrayList<>();
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

	public static Map<String, StaticType> getStaticTypesForEvents(ConvertedCase newValue)
	{
		// TODO do not connect to the DDR each time, store the connected DDR reference inside ConvertedCase ?
		ddr = DynamicDataRepositoryMainFactory.create("DYD", newValue.getDdrLocation());
		try
		{
			ddr.connect();
			Map<String, StaticType> eventAppliesTo = ddr.getEvents().stream()
					.collect(Collectors.toMap(
							ev -> ev,
							ev -> ddr.getEventAppliesToStaticType(ev)));
			return eventAppliesTo;
		}
		catch (ConnectionException e)
		{
			LOG.error(e.getMessage());
			return Collections.emptyMap();
		}
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

		// Group elements by StaticType
		Map<StaticType, List<String>> elementsByStaticType = n.getIdentifiables().stream()
				.filter(e -> e instanceof Connectable)
				.collect(Collectors.groupingBy(StaticType::from,
						Collectors.mapping(Identifiable::getId, Collectors.toList())));
		// Expand the elements located under "Bus" static type with buses resulting from bus breaker view
		List<String> busIds = elementsByStaticType.get(StaticType.Bus);
		if (busIds == null)
		{
			busIds = new ArrayList<>();
			elementsByStaticType.put(StaticType.Bus, busIds);
		}
		for (Bus b : n.getBusBreakerView().getBuses())
			busIds.add(b.getId());

		// Now for each event type, set the list of applicable elements based on the static type defined for the event
		Map<String, Collection<String>> elementsByEventType = new HashMap<>();
		getStaticTypesForEvents(case_).entrySet().stream()
				.forEach(e -> elementsByEventType.put(
						e.getKey(),
						elementsByStaticType.get(e.getValue())));
		return elementsByEventType;
	}

	public static List<String> getNetworkElements(ConvertedCase case_, String eventType)
	{
		List<String> elements = new ArrayList<>();

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

	public static List<EventParamGui> getEventParams(String event)
	{

		List<EventParamGui> eventParams = new ArrayList<>();
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

	public static Workflow createSimulation(ConvertedCase cs, List<Event> events,
			DsEngine dse,
			String stopTime, String stepBySecond, boolean onlyCheck, boolean onlyVerify,
			boolean createFilteredMat)
			throws WorkflowCreationException
	{
		String moInput = Paths.get(cs.getLocation()).resolve(cs.getName() + ".mo").toString();
		Path modelicaEngineWorkingDir = PathUtils.DATA_TMP.resolve("gui_workflow_moengine_working");
		Path output_with_events = Paths.get(cs.getLocation()).resolve(cs.getName() + "_events.mo");
		Path output_simulation = Paths.get(cs.getLocation());

		try
		{
			Files.deleteIfExists(output_with_events);
			Files.createDirectories(modelicaEngineWorkingDir);

			String simulationEngine = dse.equals(DsEngine.OPENMODELICA) ? "OpenModelica" : "Dymola";
			String simulationSource = "mo";
			String resultVariables = "[a-zA-Z0-9_]*((TN.(V|angle))|(EC.(P|Q))|(SM.(efd|cm|lambdad|lambdaf|lambdaq1|lambdaq2)))";

			List<TaskDefinition> tasks = new ArrayList<TaskDefinition>();
			Path casePath = PathUtils.findCasePath(Paths.get(cs.getLocation()));

			tasks.add(TD(StaticNetworkImporterTask.class, "importer0",
					TC("source", casePath.toString())));
			tasks.add(TD(ModelicaParserTask.class, "moparser0",
					TC("source", moInput, "modelicaDocument", "mo")));

			if (!events.isEmpty())
			{
				tasks.add(TD(ModelicaEventAdderTask.class, "eventAdder0",
						TC("network", "network",
								"modelicaDocument", "mo",
								"ddrType", "DYD",
								"ddrLocation", cs.getDdrLocation(),
								"events", (String) events.stream().map(Object::toString)
										.collect(Collectors.joining("\n")))));
				tasks.add(TD(ModelicaExporterTask.class, "exporter0",
						TC("source", "moWithEvents",
								"target", output_with_events.toString(),
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

			tasks.add(TD(ModelicaSimulatorTask.class, "modelica0",
					TC("source", simulationSource,
							"modelicaEngine", simulationEngine,
							"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
							"stopTime", stopTime,
							"numOfIntervalsPerSecond", stepBySecond,
							"libraryDir", PathUtils.LIBRARY.toString(),
							"resultVariables", resultVariables,
							"depth", depth,
							"createFilteredMat", Boolean.toString(createFilteredMat))));
			tasks.add(TD(ModelicaSimulatorTaskResults.class, "results0",
					TC("source", "simres",
							"target", output_simulation.toString(),
							"createFilteredMat", Boolean.toString(createFilteredMat))));

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
			boolean onlyMainConnectedComponent, DsEngine dse)
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
						TC("loadFlowFactoryClass", loadflowClass,
								"targetStateId", "resultsLoadFlow")));
			}

			String simulationEngine = dse.equals(DsEngine.OPENMODELICA) ? "OpenModelica"
					: dse.equals(DsEngine.DYMOLA) ? "Dymola" : "Fake";

			if (dse.equals(DsEngine.FAKE))
			{
				tasks.add(TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr0.getLocation(),
								"onlyMainConnectedComponent",
								Boolean.toString(onlyMainConnectedComponent),
								"modelicaEngine", simulationEngine,
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"fakeModelicaEngineResults", fakeInit)));
			}
			else
			{
				String simulationSource = "mo";

				tasks.add(TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("source", simulationSource,
								"ddrType", "DYD",
								"ddrLocation", ddr0.getLocation(),
								"onlyMainConnectedComponent",
								Boolean.toString(onlyMainConnectedComponent),
								"modelicaEngine", simulationEngine,
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"libraryDir", PathUtils.LIBRARY.toString())));
			}
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

		try
		{
			Map<String, List<DsData>> values = CsvReader.readVariableColumnsWithCsvListReader(
					sim.getResults("simres_output").toString(),
					new CsvReaderPopulator<DsData>()
					{

						@Override
						public void prepare(ICsvListReader listReader,
								Map<String, List<DsData>> values)
						{
							columns = listReader.length();
							columnNames = new String[columns];
							for (int i = 2; i <= columns; i++)
							{
								List<DsData> dsData = new ArrayList<DsData>();
								columnNames[i - 1] = listReader.get(i);
								values.put(columnNames[i - 1], dsData);
							}
						}

						@Override
						public void populate(List<Object> columnValues,
								Map<String, List<DsData>> values)
						{
							Double time = (Double) columnValues.get(0);
							for (int i = 1; i < columns; i++)
							{
								List<DsData> dsData = values.get(columnNames[i]);
								dsData.add(new DsData(time, (Double) columnValues.get(i)));
							}
						}

						private int	columns;
						private String[]	columnNames;
					});
			results.setDsValues(values);
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}

		results.setExceptions(sim.getExceptions());

		return results;
	}

	public static WorkflowResult getConversionResult(String id)
	{
		WorkflowResult results = new WorkflowResult();

		Network n = (Network) conv.getResults("network");
		fillLoadflowResults(id, results, n);

		@SuppressWarnings("unchecked")
		List<ElementModel> models = (List<ElementModel>) conv.getResults("models");
		results.setModels(models);

		results.setExceptions(conv.getExceptions());

		return results;
	}

	private static void fillLoadflowResults(String id, WorkflowResult results, Network n)
	{
		if (n == null) return;

		// Ensure the right current working state is set
		if (n.getStateManager().getStateIds().contains("resultsLoadFlow"))
			n.getStateManager().setWorkingState("resultsLoadFlow");

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
	}

	public static Workflow createCompareLoadflows(Case cs, boolean generatorsReactiveLimits,
			boolean helmflowFromHadesResults)
			throws WorkflowCreationException
	{
		try
		{
			Path casePath = PathUtils.findCasePath(Paths.get(cs.getLocation()));

			// TODO Allow the user change this from the user interface, flag similar to reactive limits
			String helmSourceStateId = null;
			if (helmflowFromHadesResults) helmSourceStateId = "resultsHades2";

			List<TaskDefinition> tasks = new ArrayList<TaskDefinition>();

			tasks.add(TD(StaticNetworkImporterTask.class, "importer0",
					TC("source", casePath.toString())));
			tasks.add(TD(LoadFlowTask.class, "loadflowHades2",
					TC("loadFlowFactoryClass", "com.rte_france.itesla.hades2.Hades2Factory",
							"targetStateId", "resultsHades2")));
			tasks.add(TD(LoadFlowTask.class, "loadflowHelmflow",
					TC("loadFlowFactoryClass", "com.elequant.helmflow.ipst.HelmFlowFactory",
							"sourceStateId", helmSourceStateId,
							"targetStateId", "resultsHelmflow")));

			WorkflowConfiguration config = new WorkflowConfiguration();
			config.setTaskDefinitions(tasks);
			TaskFactory tf = new TaskFactory();
			cl = Workflow.create(config, tf);
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

		List<BusData> allBusesValues = new ArrayList<>();
		try
		{
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
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}

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

		results.setExceptions(cl.getExceptions());

		return results;
	}

	public static Workflow createSwtoswValidation(String expectedPath, String casePath,
			String stepSize) throws WorkflowCreationException
	{
		sts = WF(TD(SwtoswValidationTask.class, "validator0",
				TC()));

		return sts;
	}

	public static WorkflowResult getSwtoswValidationResult(String name, String... variables)
	{
		WorkflowResult results = new WorkflowResult();

		List<Validation> list = new ArrayList<>();

		Validation v;

		if (variables.length == 0)
		{
			String[] summaries = { "V", "A", "P", "Q" };

			for (String summary : summaries)
			{
				v = new Validation();
				v.setName(summary);
				v.setRmse(Utils.randomDouble(0, 0.0015));
				v.setRd(Utils.randomDouble(0, 0.06));
				v.setAd(Utils.randomDouble(0, 0.06));

				list.add(v);
			}
		}
		else
		{

			for (String variable : variables)
			{
				String[] buses = { "bus_BUS____1_TN." + variable, "bus_BUS____2_TN." + variable,
						"bus_BUS____3_TN." + variable, "bus_BUS____4_TN." + variable,
						"bus_BUS____5_TN." + variable, "bus_BUS____6_TN." + variable,
						"bus_BUS____7_TN." + variable, "bus_BUS____8_TN." + variable,
						"bus_BUS____9_TN." + variable, "bus_BUS___10_TN." + variable,
						"bus_BUS___11_TN." + variable, "bus_BUS___12_TN." + variable,
						"bus_BUS___13_TN." + variable, "bus_BUS___14_TN.V" };

				for (String bus : buses)
				{
					v = new Validation();
					v.setName(bus);
					v.setRmse(Utils.randomDouble(0, 0.0015));
					v.setRd(Utils.randomDouble(0, 0.06));
					v.setAd(Utils.randomDouble(0, 0.06));

					list.add(v);
				}
			}
		}

		results.setValidation(list);
		results.setExceptions(sts.getExceptions());

		return results;
	}

	private static Workflow					conv	= null;
	private static Workflow					sim		= null;
	private static Workflow					cl		= null;
	private static Workflow					sts		= null;
	private static DynamicDataRepository	ddr		= null;

	private static final Logger				LOG		= LoggerFactory
			.getLogger(WorkflowServiceConfiguration.class);

}
