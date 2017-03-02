package org.power_systems_modelica.psm.gui.service;

import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.case_validation.model.VariableValidation;
import org.power_systems_modelica.psm.commons.CsvReader;
import org.power_systems_modelica.psm.commons.CsvReaderPopulator;
import org.power_systems_modelica.psm.commons.FileUtils;
import org.power_systems_modelica.psm.commons.Logs;
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
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.TaskFactory;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.CaseValidationTask;
import org.power_systems_modelica.psm.workflow.psm.LoadFlowTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaEventAdderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaExporterTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask.ElementModel;
import org.power_systems_modelica.psm.workflow.psm.ModelicaParserTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTask;
import org.power_systems_modelica.psm.workflow.psm.ModelicaSimulatorTaskResults;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
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

	public static void resetConversion()
	{
		conv = null;
	}

	public static Workflow getSimulation()
	{
		return sim;
	}

	public static void resetSimulation()
	{
		sim = null;
	}

	public static Workflow getCompareLoadflow()
	{
		return cl;
	}

	public static void resetCompareLoadflow()
	{
		cl = null;
	}

	public static List<LoadflowEngine> getLoadflowEngines()
	{
		List<LoadflowEngine> engines = new ArrayList<>();
		if (Utils.isHades2Available()) engines.add(LoadflowEngine.HADES2);
		engines.add(LoadflowEngine.HELMFLOW);
		engines.add(LoadflowEngine.NONE);

		return engines;
	}

	public static List<DsEngine> getDsEngines()
	{
		Properties p = PathUtils.getGUIProperties();
		String senableFake = p.getProperty("enableFakeModelicaEngine");
		boolean enableFake = senableFake != null && Boolean.valueOf(senableFake) == true;

		List<DsEngine> engines = new ArrayList<>();
		if (enableFake) engines.add(DsEngine.FAKE);
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
			Optional<Path> path = Files
					.walk(output_simulation, 1, FileVisitOption.FOLLOW_LINKS)
					.filter((p) -> !p.toFile().isDirectory()
							&& p.toFile().getAbsolutePath().endsWith(".csv"))
					.findFirst();
			if (path.isPresent())
				Files.deleteIfExists(path.get());
			Files.createDirectories(modelicaEngineWorkingDir);

			String simulationEngine = dse.equals(DsEngine.OPENMODELICA) ? "OpenModelica" : "Dymola";
			String simulationSource = "mo";

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
			boolean onlyMainConnectedComponent, DsEngine dse, boolean checkOnly)
			throws WorkflowCreationException
	{

		String fakeInit = Paths.get(ddr0.getLocation()).resolve("fake_init.csv").toString();
		Path modelicaEngineWorkingDir = PathUtils.DATA_TMP.resolve("gui_workflow_moengine_working");
		String outname = Paths.get(cs.getLocation()).resolve(cs.getName() + ".mo").toString();

		try
		{
			FileUtils.deleteDirectory(modelicaEngineWorkingDir);
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

			String simulationEngine = dse != null && dse.equals(DsEngine.OPENMODELICA) ? "OpenModelica"
					: dse != null && dse.equals(DsEngine.DYMOLA) ? "Dymola" : "Fake";

			if (dse == null)
			{
				tasks.add(TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr0.getLocation(),
								"onlyMainConnectedComponent",
								Boolean.toString(onlyMainConnectedComponent),
								"checkOnly",
								Boolean.toString(checkOnly))));
				
			}
			else if (dse.equals(DsEngine.FAKE))
			{
				tasks.add(TD(ModelicaNetworkBuilderTask.class, "modelica0",
						TC("ddrType", "DYD",
								"ddrLocation", ddr0.getLocation(),
								"onlyMainConnectedComponent",
								Boolean.toString(onlyMainConnectedComponent),
								"checkOnly",
								Boolean.toString(checkOnly),
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
								"checkOnly",
								Boolean.toString(checkOnly),
								"modelicaEngine", simulationEngine,
								"modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString(),
								"libraryDir", PathUtils.LIBRARY.toString(),
								"resultVariables", "")));
			}
			if (!checkOnly)
			{
				tasks.add(TD(ModelicaExporterTask.class, "exporter0",
						TC("source", "mo",
								"target", outname,
								"includePsmAnnotations", "true")));
			}

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
			Optional<Path> path = Files
					.walk(Paths.get(sim.getResults("simres_output").toString()), 1,
							FileVisitOption.FOLLOW_LINKS)
					.filter((p) -> !p.toFile().isDirectory()
							&& p.toFile().getAbsolutePath().endsWith(".csv"))
					.findFirst();
			if (path.isPresent())
			{

				Map<String, List<DsData>> values = CsvReader.readVariableColumnsWithCsvListReader(
						path.get().toFile(),
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
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}

		results.setExceptions(sim.getExceptions());

		return results;
	}

	public static Logs getSimulationLogs(String id)
	{
		return (Logs) sim.getResults("logs");
	}

	public static Collection<Identifiable<?>> getElementsMissingDynamicModel(String string)
	{
		return (Collection<Identifiable<?>>) conv.getResults("elementsMissingDynamicModel");
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

	public static Logs getConversionLogs(String id)
	{
		return (Logs) conv.getResults("logs");
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
			Ps[0] = zeroIfNaN(b.getP());
			Qs[0] = zeroIfNaN(b.getQ());
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);
			allBusesValues.add(new BusData(b.getId(), b.getName(), bvalues));
		});
		results.setId(id);
		results.setAllBusesValues(allBusesValues);
	}

	private static float zeroIfNaN(float value)
	{
		return Float.isNaN(value) ? 0.0f : value;
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
			Map<String, Map<String, float[]>> busValues = LoadFlowTask.gatherBusesValues(
					n,
					"resultsHelmflow",
					"resultsHades2");
			n.getBusBreakerView().getBuses().forEach(b -> {
				allBusesValues.add(
						new BusData(
								b.getId(),
								b.getName(),
								busValues.get(b.getId())));
			});
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}

		allBusesValues.forEach(bv -> {
			float[] values = bv.getData().get("V");
			bv.setError("V", LoadFlowTask.calcDifference(values[0], values[1]));
			values = bv.getData().get("A");
			bv.setError("A", LoadFlowTask.calcDifference(values[0], values[1]));
			values = bv.getData().get("P");
			bv.setError("P", LoadFlowTask.calcDifference(values[0], values[1]));
			values = bv.getData().get("Q");
			bv.setError("Q", LoadFlowTask.calcDifference(values[0], values[1]));
		});

		results.setId(id);
		results.setAllBusesValues(allBusesValues);

		results.setExceptions(cl.getExceptions());

		return results;
	}

	public static Workflow createSwtoswValidation(String mappingPath, String expectedPath,
			String casePath,
			String stepSize) throws WorkflowCreationException
	{
		sts = WF(TD(CaseValidationTask.class, "validator0",
				TC("stepSize", stepSize,
						"pathNamesMapping", mappingPath,
						"pathRefData", expectedPath,
						"pathModelicaData", casePath)));

		return sts;
	}

	public static WorkflowResult getSwtoswValidationResult(String thRmse, String thRd,
			String thAd, String... variables)
	{
		WorkflowResult results = new WorkflowResult();

		List<Validation> list = new ArrayList<>();
		if (variables.length == 0)
		{
			Validation val = new Validation();
			val.setSelectable(false);
			val.setName("Tolerance");
			val.setRmse(thRmse);
			val.setAd(thRd);
			val.setRd(thAd);

			list.add(val);
		}

		if (sts != null && sts.getState().equals(ProcessState.SUCCESS))
		{
			ValidationResult r = (ValidationResult) sts.getResults("casevalidation");

			List<Validation> sublist = new ArrayList<>();
			if (variables.length == 0)
			{
				double rmes = 0;
				double rd = 0;
				double ad = 0;
				for (VariableValidation v : r.getVariables())
				{
					Validation val = new Validation();
					val.setName(v.getName());
					val.setRmse("" + v.getRmesAbove());
					rmes += v.getRmesAbove();
					val.setAd("" + v.getAbsTotalOffset());
					ad += v.getAbsTotalOffset();
					val.setRd("" + v.getRelTotalOffset());
					rd += v.getRelTotalOffset();

					sublist.add(val);
				}

				Validation total = new Validation();
				total.setSelectable(false);
				total.setName("Total");
				total.setRmse("" + rmes);
				total.setAd("" + ad);
				total.setRd("" + rd);

				list.add(total);
				Validation space = new Validation();
				space.setName("");
				space.setSelectable(false);
				list.add(space);
				list.addAll(sublist);
			}
			else
			{
				List<String> elements = new ArrayList<>();
				elements.addAll(r.getElements().keySet());

				for (String variable : variables)
				{
					VariableValidation v = r.getVariable(variable);
					if (v == null) continue;
					
					elements.forEach(ke -> {

						Element e = r.getElements().get(ke);
						if (e.getRefName().endsWith(variable))
						{
							Validation val = new Validation();
							val.setName(e.getModelicaName());
							val.setRmse("" + e.getAbsRmes());
							val.setAd("" + (1.0*e.getAbsOffset() / v.getSteps()));
							val.setRd("" + (1.0*e.getRelOffset() / v.getSteps()));

							list.add(val);
						}
					});
				}
			}

		}
		results.setValidation(list);
		if (sts != null)
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
