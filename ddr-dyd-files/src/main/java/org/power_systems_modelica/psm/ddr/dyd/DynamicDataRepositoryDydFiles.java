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
import org.power_systems_modelica.psm.ddr.dyd.equations.Context;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.equations.PrefixSelector;
import org.power_systems_modelica.psm.ddr.dyd.equations.Selector;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParXml;
import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Identifiable;

public class DynamicDataRepositoryDydFiles implements DynamicDataRepository
{
	public DynamicDataRepositoryDydFiles()
	{
		modelContainers = new HashMap<>();
		associations = new AssociationProvider();
		dynamicModels = new ModelProvider(associations);
		initializationModels = new ModelProvider(associations);
		systemDefinitions = new SystemDefinitions();
		parameters = new ParameterSetProvider();
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
	public List<ModelicaDeclaration> getSystemDeclarations()
	{
		return systemDefinitions.getDeclarations();
	}

	@Override
	public List<ModelicaEquation> getSystemEquations(ModelicaSystemModel m)
	{
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
				return m.getDeclarations().stream();
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

		return systemDefinitions.getEquations()
				.stream()
				.map(eq -> new ModelicaEquation(eq.writeIn(contextModelica)))
				.collect(Collectors.toList());
	}

	@Override
	public ModelicaModel getModelicaModel(Identifiable<?> e)
	{
		Model mdef = dynamicModels.getModel(e);
		if (mdef != null) return buildModelicaModelFromDynamicModelDefinition(mdef, e);
		return null;
	}

	@Override
	public ModelicaModel getModelicaInitializationModel(Identifiable<?> e)
	{
		Model mdef = initializationModels.getModel(e);
		if (mdef != null) return buildModelicaModelFromDynamicModelDefinition(mdef, e);
		return null;
	}

	@Override
	public ModelicaModel getModelicaModelForEvent(String ev, Identifiable<?> e)
	{
		ModelForEvent mdef = dynamicModels.getModelForEvent(ev);
		if (mdef != null) return buildModelicaModelFromDynamicModelDefinition(mdef, e);
		return null;
	}

	@Override
	public Injection getInjectionForEvent(String ev)
	{
		ModelForEvent mdef = dynamicModels.getModelForEvent(ev);
		return mdef.getInjection();
	}

	@Override
	public Collection<String> getEvents()
	{
		// Return a sorted set of events found in the model provider
		Set<String> events = new TreeSet<>();
		events.addAll(dynamicModels.getEvents());
		return events;
	}

	@Override
	public List<EventParameter> getEventParameters(String event)
	{
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
			String cn = c.getName();
			ParameterSet pset = c.getParameterSet();
			// Lets say that parameters for events should be define in-line, no external references are allowed
			if (pset == null) return;
			pset.getParameters().stream()
					// Consider only the parameters of the model components that are references to EVENT data
					.filter(p -> p instanceof ParameterReference)
					.filter(p -> ((ParameterReference) p).getDataSource()
							.equals(EVENT_PARAMS_DATA_SOURCE))
					.forEach(p -> {
						String pn = p.getName();
						String name = qualifyParameterNames ? cn.concat(pn) : pn;
						String unit = p.getUnit() != null ? p.getUnit() : "";
						eventParams.add(new EventParameter(name, unit));
					});
		});
		return eventParams;
	}

	public void setSystemDefinitionsName(String systemName)
	{
		systemDefinitions.setName(systemName);
	}

	public static String dynamicId(String baseId, Identifiable<?> element)
	{
		String staticId = element.getId();
		String dynamicId = baseId.replace("{staticId}",
				ModelicaUtil.normalizedIdentifier(staticId));
		return dynamicId;
	}

	private ModelicaModel buildModelicaModelFromDynamicModelDefinition(
			Model mdef,
			Identifiable<?> element)
	{
		ModelicaModel m = new ModelicaModel(dynamicId(mdef.getId(), element));
		m.setStaticId(element.getId());

		List<ModelicaDeclaration> ds = new ArrayList<>();
		for (Component c : mdef.getComponents())
		{
			String type = c.getName();
			String did = dynamicId(c.getId(), element);
			List<ModelicaArgument> arguments = buildModelicaArguments(c, element);
			Annotation annotation = null;

			// TODO Every Modelica declaration built from a model component definition is a variable, not a parameter
			boolean isParameter = false;
			ds.add(new ModelicaDeclaration(type, did, arguments, isParameter, annotation));
		}
		m.addDeclarations(ds);

		m.setConnectors(mdef.getConnectors()
				.stream()
				.map(c -> new ModelicaConnector(
						// FIXME If the connector identifier is empty, assign it the id of the first declaration
						// This is right because we only have generic connectors on models with only one component
						// The general solution should be: the connector has a relative id to the components
						// And the exact id can be resolved after all components have been instantiated
						c.getId() == null || c.getId().isEmpty()
								? ds.get(0).getId()
								: dynamicId(c.getId(), element),
						c.getPin(),
						c.getTarget()))
				.collect(Collectors.toList()));

		List<ModelicaEquation> meqs = new ArrayList<>(mdef.getConnections().size());
		for (Connection c : mdef.getConnections())
		{
			String ref1 = ModelicaUtil.idvar2ref(dynamicId(c.getId1(), element), c.getVar1());
			String ref2 = ModelicaUtil.idvar2ref(dynamicId(c.getId2(), element), c.getVar2());
			meqs.add(new ModelicaConnect(ref1, ref2));
		}
		m.addEquations(meqs);

		return m;
	}

	private List<ModelicaArgument> buildModelicaArguments(Component c, Identifiable<?> element)
	{
		ParameterSet set = getParameterSet(c, element);
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

	private ParameterSet getParameterSet(Component c, Identifiable<?> element)
	{
		ParameterSet set = c.getParameterSet();
		if (set == null)
		{
			ParameterSetReference pref = c.getParameterSetReference();
			if (pref != null) set = parameters.get(pref, element);
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

	private void readDyd(Path file)
	{
		try
		{
			DydContent dyd = DydXml.read(file);
			if (dyd == null) return;
			String name = file.getFileName().toString().replace(".dyd", "");
			dyd.setName(name);

			if (dyd instanceof ModelContainer)
			{
				ModelContainer mc = (ModelContainer) dyd;
				mc.getAssociations().forEach(a -> addAssociation(name, a));
				mc.getModels().forEach(m -> addModel(name, m));
				resolveParameters(mc);
			}
			else if (dyd instanceof SystemDefinitions)
			{
				SystemDefinitions sd = (SystemDefinitions) dyd;
				systemDefinitions.add(sd);
			}
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
		f = fileForDydContent(systemDefinitions);
		DydXml.write(f, systemDefinitions);
		for (ModelContainer mc : modelContainers.values())
		{
			f = fileForDydContent(mc);
			DydXml.write(f, mc);
		}
		for (ParameterSetContainer pc : parameters.getContainers())
			ParXml.write(location.resolve(pc.getName()), pc);
	}

	public void addSystemDeclarations(List<ModelicaDeclaration> declarations)
	{
		systemDefinitions.addDeclarations(declarations);
	}

	public void addSystemEquations(List<Equation> equations)
	{
		systemDefinitions.addEquations(equations);
	}

	public void addModel(String containerName, Model mdef)
	{
		ModelContainer mc = getContainer(containerName);
		mc.add(mdef);
		if (mdef.isInitialization()) initializationModels.add(mdef);
		else dynamicModels.add(mdef);
	}

	public void addAssociation(String containerName, Association a)
	{
		ModelContainer mc = getContainer(containerName);
		mc.add(a);
		associations.add(a);
	}

	public Model getDynamicModelForAssociation(String associationId)
	{
		return dynamicModels.getDynamicModelForAssociation(associationId);
	}

	public Model getDynamicModelForStaticType(String type)
	{
		return dynamicModels.getDynamicModelForStaticType(type).orElse(null);
	}

	private ModelContainer getContainer(String name)
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

	private Path						location;
	private Map<String, ModelContainer>	modelContainers;
	private ModelProvider				dynamicModels;
	private ModelProvider				initializationModels;
	private SystemDefinitions			systemDefinitions;
	private ParameterSetProvider		parameters;
	private AssociationProvider			associations;

	private static final PathMatcher	DYD_MATCHER	= FileSystems
			.getDefault().getPathMatcher("glob:*.dyd");
	private static final Logger			LOG			= LoggerFactory
			.getLogger(DynamicDataRepositoryDydFiles.class);
}
