package org.power_systems_modelica.psm.ddr.dyd;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.EventParameter;
import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.ddr.dyd.equations.Context;
import org.power_systems_modelica.psm.ddr.dyd.equations.PrefixSelector;
import org.power_systems_modelica.psm.ddr.dyd.equations.Selector;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaInterconnection;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.ConnectableType;
import eu.itesla_project.iidm.network.Identifiable;

public class DynamicDataRepositoryDydFiles implements DynamicDataRepository
{
	public DynamicDataRepositoryDydFiles()
	{
		modelContainers = new HashMap<>();
		associations = new AssociationProvider();
		modelsByStage = new HashMap<>();
		for (Stage stage : Stage.values())
			modelsByStage.put(stage, new ModelProvider(associations));
		systemDefinitions = null;
		parameters = new ParameterSetProvider();
	}

	public List<Model> getAllModelDefinitions()
	{
		List<Model> models = new ArrayList<>();
		models.addAll(modelContainers.values().stream()
				.flatMap(mc -> mc.getModels().stream())
				.collect(Collectors.toList()));
		return models;
	}

	@Override
	public String getType()
	{
		return "DYD";
	}

	@Override
	public void setLocation(String location)
	{
		this.location = Paths.get(location);
	}

	@Override
	public String getLocation()
	{
		return location.toAbsolutePath().toString();
	}

	@Override
	public List<ModelicaEquation> getSystemOtherEquationsInContext(
			ModelicaSystemModel moSystem,
			Stage stage)
	{
		if (systemDefinitions == null) return null;

		Context<ModelicaDeclaration> contextModelica = new Context<ModelicaDeclaration>()
		{
			@Override
			public String write(ModelicaDeclaration d)
			{
				return d.getId();
			}

			@Override
			public Stream<ModelicaDeclaration> getDomainStream()
			{
				return moSystem.getDeclarations().stream();
			}

			@Override
			public Predicate<ModelicaDeclaration> getPredicate(Selector selector)
			{
				Predicate<ModelicaDeclaration> p = super.getPredicate(selector);
				if (p != null) return p;
				if (selector instanceof PrefixSelector)
					return dm -> dm.getId().startsWith(((PrefixSelector) selector).getPrefix());
				return null;
			}
		};

		List<Model> mdefs = systemDefinitions.getModels().stream().collect(Collectors.toList());
		List<Model> mdefsstage = mdefs.stream()
				.filter(m -> m.getStage().equals(stage))
				.collect(Collectors.toList());
		List<ModelicaEquation> specialEquations = mdefsstage.stream()
				.map(mdef -> mdef.getOtherEquations())
				.flatMap(List::stream)
				.map(eq -> new ModelicaEquation(eq.writeIn(contextModelica)))
				.collect(Collectors.toList());
		return specialEquations;
	}

	@Override
	public Optional<ModelicaModel> getSystemModel(Stage stage)
	{
		if (systemDefinitions == null) return Optional.empty();
		String systemId = ModelicaUtil.getSystemStaticId();
		Optional<ModelicaModel> mo = systemDefinitions.getModels().stream()
				.filter(m -> m.getStage().equals(stage))
				.findFirst()
				.map(mdef -> buildModelicaModelFromDynamicModelDefinition(mdef, systemId))
				.map(mo0 -> {
					mo0.setStaticId(systemId);
					return mo0;
				});
		return mo;
	}

	@Override
	public ModelicaModel getModelicaModel(Identifiable<?> e, Stage stage)
	{
		Model mdef = modelsByStage.get(stage).getModel(e);
		if (mdef != null) return buildModelicaModelFromDynamicModelDefinition(mdef, e.getId());
		return null;
	}

	@Override
	public ModelicaModel getModelicaModelForEvent(String ev, Identifiable<?> e)
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		ModelForEvent mdef = dynamicModels.getModelForEvent(ev);
		if (mdef != null) return buildModelicaModelFromDynamicModelDefinition(mdef, e.getId());
		return null;
	}

	@Override
	public Collection<String> getEvents()
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		// Return a sorted set of events found in the model provider
		Set<String> events = new TreeSet<>();
		events.addAll(dynamicModels.getEvents());
		return events;
	}

	@Override
	public Injection getEventInjection(String ev)
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		ModelForEvent mdef = dynamicModels.getModelForEvent(ev);
		return mdef.getInjection();
	}

	@Override
	public List<EventParameter> getEventParameters(String event)
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		ModelForEvent m = dynamicModels.getModelForEvent(event);
		if (m == null)
		{
			LOG.warn("Event definition not found {}", event);
			return Collections.emptyList();
		}

		// If the model contains more than one component, qualify the names of the parameters
		boolean qualifyParameterNames = m.getComponents().size() > 1;
		List<EventParameter> eventParams = new ArrayList<>(m.getComponents().size() * 5);
		m.getComponents().forEach(c -> {
			String ct = c.getType();
			ParameterSet pset = c.getParameterSet();
			// Lets say that parameters for events should be define in-line, no external references are allowed
			if (pset == null) return;
			pset.getParameters().stream()
					// Consider only the parameters of the model components that are references to EVENT data
					.filter(p -> p instanceof ParameterReference)
					.filter(p -> ((ParameterReference) p).getDataSource()
							.equals(EVENT_PARAMS_DATA_SOURCE))
					.forEach(p -> {
						String pn = ((ParameterReference) p).getSourceName();
						String name = qualifyParameterNames ? ct.concat(pn) : pn;
						String unit = p.getUnit() != null ? p.getUnit() : "";
						eventParams.add(new EventParameter(name, unit));
					});
		});
		return eventParams;
	}

	@Override
	public ConnectableType getEventAppliesToConnectableType(String event)
	{
		// FIXME move this to the xml files
		switch (event)
		{
		case "BusFault":
			return ConnectableType.BUSBAR_SECTION;
		case "LineFault":
			return ConnectableType.LINE;
		case "LineOpenSendingSide":
			return ConnectableType.LINE;
		case "LineOpenReceiverSide":
			return ConnectableType.LINE;
		case "LineOpenBothSides":
			return ConnectableType.LINE;
		case "BankModification":
			return ConnectableType.SHUNT_COMPENSATOR;
		case "LoadVariation":
			return ConnectableType.LOAD;
		case "GeneratorVSetpointModification":
			return ConnectableType.GENERATOR;
		}
		return null;
	}

	public void createSystemDefinitions(String name)
	{
		systemDefinitions = new ModelContainer();
		systemDefinitions.setName(name);
	}

	public static String dynamicId(String baseId, String staticId)
	{
		if (baseId == null) return null;
		String nstaticId = ModelicaUtil.normalizedIdentifier(staticId);
		String dynamicId = baseId.replace("{staticId}", nstaticId);
		return dynamicId;
	}

	private ModelicaModel buildModelicaModelFromDynamicModelDefinition(
			Model mdef,
			String staticId)
	{
		ModelicaModel m = new ModelicaModel(dynamicId(mdef.getId(), staticId));
		m.setStaticId(staticId);

		List<ModelicaDeclaration> ds = new ArrayList<>();
		for (Component c : mdef.getComponents())
		{
			String type = c.getType();
			String did = dynamicId(c.getId(), staticId);
			List<ModelicaArgument> arguments = buildModelicaArguments(c, staticId);
			Annotation annotation = null;

			boolean isParameter = c.isParameter();
			if (arguments != null)
				ds.add(new ModelicaDeclaration(type, did, arguments, isParameter, annotation));
			else
				ds.add(new ModelicaDeclaration(type, did, c.getValue(), isParameter, annotation));
		}
		m.addDeclarations(ds);

		m.setInterconnections(mdef.getInterconnections().stream()
				.map(ic -> new ModelicaInterconnection(
						ic.getName(),
						dynamicId(ic.getComponentId(), staticId),
						ic.getComponentVar(),
						ic.getTargetModel(),
						ic.getTargetName(),
						ic.getTargetModel2(),
						ic.getTargetName2()))
				.collect(Collectors.toList()));

		List<ModelicaEquation> meqs = new ArrayList<>(mdef.getConnections().size());
		for (Connection c : mdef.getConnections())
		{
			String ref1 = ModelicaUtil.idvar2ref(dynamicId(c.getId1(), staticId), c.getVar1());
			String ref2 = ModelicaUtil.idvar2ref(dynamicId(c.getId2(), staticId), c.getVar2());
			meqs.add(new ModelicaConnect(ref1, ref2));
		}
		m.addEquations(meqs);

		return m;
	}

	private List<ModelicaArgument> buildModelicaArguments(Component c, String staticId)
	{
		ParameterSet set = getParameterSet(c, staticId);
		if (set == null) return null;

		List<ModelicaArgument> arguments = new ArrayList<>(set.getParameters().size());
		for (Parameter p : set.getParameters())
		{
			ModelicaArgument a = null;
			if (p instanceof ParameterValue) a = new ModelicaArgument(
					p.getName(),
					((ParameterValue) p).getValue());
			else if (p instanceof ParameterReference) a = new ModelicaArgumentReference(
					p.getName(),
					((ParameterReference) p).getDataSource(),
					((ParameterReference) p).getSourceName());
			if (a != null) arguments.add(a);
		}
		return arguments;
	}

	private ParameterSet getParameterSet(Component c, String staticId)
	{
		ParameterSet set = c.getParameterSet();
		if (set == null)
		{
			ParameterSetReference psetRef = c.getParameterSetReference();
			if (psetRef != null) set = parameters.get(psetRef, staticId);
		}
		return set;
	}

	@Override
	public void connect() throws ConnectionException
	{
		try
		{
			read();
		}
		catch (IOException e)
		{
			throw new ConnectionException(e);
		}
	}

	private void read() throws IOException
	{
		// Read all DYD and PAR files in the given location
		Path start = location;
		Files.walkFileTree(start, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			{
				if (isDyd(file)) readDyd(file);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	public Map<String, String> checkXmls() throws IOException
	{
		boolean isValidationActive = XmlUtil.isValidationActive;
		XmlUtil.isValidationActive = true;

		Map<String, String> xmlErrors = new HashMap<>();

		// Read all DYD files in the given location
		Path start = location;
		Files.walkFileTree(start, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			{
				if (isDyd(file)) checkXmlDyd(file, xmlErrors);
				return FileVisitResult.CONTINUE;
			}
		});

		XmlUtil.isValidationActive = isValidationActive;
		return xmlErrors;
	}

	public Map<String, ModelMapping> checkDuplicates() throws IOException
	{
		Map<String, ModelMapping> modelMapping = new HashMap<>();

		// Read all DYD files in the given location
		Path start = location;
		Files.walkFileTree(start, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			{
				if (isDyd(file)) checkDuplicatesDyd(file, modelMapping);
				return FileVisitResult.CONTINUE;
			}
		});

		return modelMapping;
	}

	private void readDyd(Path file)
	{
		try
		{
			DydContent dyd = DydXml.read(file);
			if (dyd == null) return;
			Path rfile = location.relativize(file);
			String name = rfile.toString().replace(".dyd", "");
			dyd.setName(name);

			if (dyd instanceof ModelContainer)
			{
				ModelContainer mc = (ModelContainer) dyd;
				if (mc.isForSystemDefinitions())
				{
					if (systemDefinitions != null)
					{
						LOG.warn(
								"Existing system definitions read from '{}' will be overwritten with new ones read from '{}'",
								systemDefinitions.getName(),
								dyd.getName());
					}
					systemDefinitions = (ModelContainer) dyd;
				}
				else
				{
					mc.getAssociations().forEach(a -> addAssociation(name, a));
					mc.getModels().forEach(m -> addModel(name, m));
					resolveParameters(mc);
				}
			}
		}
		catch (XMLStreamException | IOException | RuntimeException e)
		{
			LOG.warn("ignored DYD file {} because of error {}", file, e);
		}
	}

	private void checkXmlDyd(Path file, Map<String, String> xmlErrors)
	{
		try
		{
			DydXml.read(file);
		}
		catch (Exception e)
		{
			Path rfile = location.relativize(file);
			String name = rfile.toString().replace(".dyd", "");

			xmlErrors.put(name, e.getMessage());
		}
	}

	private void checkDuplicatesDyd(Path file, Map<String, ModelMapping> modelMapping)
	{
		try
		{
			DydContent dyd = DydXml.read(file);
			if (dyd == null) return;
			Path rfile = location.relativize(file);
			String name = rfile.toString().replace(".dyd", "");
			dyd.setName(name);

			if (!(dyd instanceof ModelContainer)) return;
			ModelContainer mc = (ModelContainer) dyd;

			// TODO Check also system definitions are not duplicated
			if (mc.isForSystemDefinitions()) return;

			// TODO Check also the associations (do not allow duplicated names)
			// mc.getAssociations().forEach(a -> addAssociation(name, a));
			mc.getModels().forEach(m -> checkDuplicatedModel(mc, m, modelMapping));
		}
		catch (XMLStreamException | IOException e)
		{
			LOG.warn("ignored DYD file {} because of error {}", file, e);
		}
	}

	private void resolveParameters(ModelContainer dyd)
	{
		for (Model m : dyd.getModels())
			for (Component mc : m.getComponents())
				if (mc.getParameterSetReference() != null)
					resolveParameters(mc.getParameterSetReference().getContainer());
	}

	private void resolveParameters(String name)
	{
		if (parameters.contains(name)) return;
		ParameterSetContainer c = readParameters(name);
		if (c != null)
		{
			c.setName(name);
			parameters.addContainer(c);
		}
	}

	private ParameterSetContainer readParameters(String filename)
	{
		// Filename is assumed to be relative to repository location
		Path f = location.resolve(filename);
		if (!Files.exists(f))
		{
			LOG.warn("ignored PAR file {} does not exist", filename);
			return null;
		}
		ParameterSetContainer container = null;
		try
		{
			container = ParXml.read(f);
		}
		catch (XMLStreamException | IOException e)
		{
			LOG.warn("ignored PAR file {} because of error {}", filename, e.toString());
		}
		return container;
	}

	private boolean isDyd(Path file)
	{
		Path name = file.getFileName();
		return (name != null && DYD_MATCHER.matches(name));
	}

	public void write() throws XMLStreamException, IOException
	{
		Path f;

		// Write all model containers, all model initialization containers and all parameter set containers
		if (systemDefinitions != null)
		{
			f = ensureFile(fileForDydContent(systemDefinitions));
			DydXml.write(f, systemDefinitions);
		}
		for (ModelContainer mc : modelContainers.values())
		{
			f = ensureFile(fileForDydContent(mc));
			DydXml.write(f, mc);
		}
		for (ParameterSetContainer pc : parameters.getContainers())
		{
			f = ensureFile(location.resolve(pc.getName()));
			ParXml.write(f, pc);
		}
	}

	public void addSystemModel(Model m)
	{
		systemDefinitions.add(m);
	}

	public void addModel(String containerName, Model mdef)
	{
		ModelContainer mc = getCreateModelContainer(containerName);
		mc.add(mdef);
		modelsByStage.get(mdef.getStage()).add(mdef);
	}

	public void checkDuplicatedModel(ModelContainer mc, Model mdef,
			Map<String, ModelMapping> modelMapping)
	{
		String mdefKey = DynamicDataRepositoryDydFiles.getModelKey(mdef);
		if (!modelMapping.containsKey(mdefKey))
			modelMapping.put(mdefKey, new ModelMapping(mdefKey));

		modelMapping.get(mdefKey).add(mdef, mc);
	}

	public void addAssociation(String containerName, Association a)
	{
		ModelContainer mc = getCreateModelContainer(containerName);
		mc.add(a);
		associations.add(a);
	}

	public Model getDynamicModelForAssociation(String associationId, Stage stage)
	{
		ModelProvider dynamicModels = modelsByStage.get(stage);
		return dynamicModels.getDynamicModelForAssociation(associationId);
	}

	public Model getDynamicModelForStaticType(String type, Stage stage)
	{
		ModelProvider dynamicModels = modelsByStage.get(stage);
		return dynamicModels.getDynamicModelForStaticType(type).orElse(null);
	}

	public Model getDynamicModelForSystem(Stage stage)
	{
		Optional<Model> ms = systemDefinitions.getModels().stream()
				.filter(m -> m.getStage().equals(stage))
				.findFirst();
		if (ms.isPresent()) return ms.get();
		return null;
	}

	private ModelContainer getCreateModelContainer(String name)
	{
		ModelContainer mc = Optional
				.ofNullable(modelContainers.get(name))
				.orElse(new ModelContainer(name));
		modelContainers.put(name, mc);
		return mc;
	}

	public ParameterSetContainer getParameterSetContainer(String name)
	{
		return parameters.getContainer(name);
	}

	public void addParameterSetContainer(String name)
	{
		ParameterSetContainer pc = new ParameterSetContainer();
		pc.setName(name);
		parameters.addContainer(pc);
	}

	private Path fileForDydContent(DydContent dyd)
	{
		String name = dyd.getName();
		Objects.requireNonNull(name);
		return location.resolve(name + ".dyd");
	}

	private Path ensureFile(Path f)
	{
		f.toFile().getParentFile().mkdirs();
		return f;
	}

	private static String getModelKey(Model mdef)
	{
		String stage = mdef.getStage().name();
		String className = mdef.getClass().getName();

		String id = "";
		if (mdef instanceof ModelForElement)
			id = ((ModelForElement) mdef).getStaticId();
		else if (mdef instanceof ModelForAssociation)
			id = ((ModelForAssociation) mdef).getAssociation();
		else if (mdef instanceof ModelForType)
			id = ((ModelForType) mdef).getType();
		else if (mdef instanceof ModelForEvent)
			id = ((ModelForEvent) mdef).getEvent();

		return stage + className + id;
	}

	private Path						location;
	private Map<String, ModelContainer>	modelContainers;
	private Map<Stage, ModelProvider>	modelsByStage;
	private ModelContainer				systemDefinitions;
	private ParameterSetProvider		parameters;
	private AssociationProvider			associations;

	private static final PathMatcher	DYD_MATCHER	= FileSystems
			.getDefault().getPathMatcher("glob:*.dyd");
	private static final Logger			LOG			= LoggerFactory
			.getLogger(DynamicDataRepositoryDydFiles.class);
}
