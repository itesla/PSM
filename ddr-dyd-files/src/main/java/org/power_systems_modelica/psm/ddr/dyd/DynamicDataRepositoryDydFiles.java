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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
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
		dynamicModels = new ModelProvider(false);
		initializationModels = new ModelProvider(true);
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

	private ModelicaModel buildModelicaModelFromDynamicModelDefinition(Model mdef,
			Identifiable<?> element)
	{
		ModelicaModel m = new ModelicaModel(buildModelicaModelId(mdef, element));
		m.setStaticId(element.getId());

		List<ModelicaDeclaration> ds = new ArrayList<>();
		for (Component mc : mdef.getComponents())
		{
			String type = mc.getName();
			String id = buildModelicaDeclarationId(mdef, mc, element);
			List<ModelicaArgument> arguments = buildModelicaArguments(mc);
			Annotation annotation = null;

			// TODO Every Modelica declaration built from a model component definition is a variable, not a parameter
			boolean isParameter = false;
			ds.add(new ModelicaDeclaration(type, id, arguments, isParameter, annotation));
		}
		m.addDeclarations(ds);

		m.setConnectors(mdef.getConnectors()
				.stream()
				.map(c -> new ModelicaConnector(
						// FIXME If the connector identifier is empty, assign it the id of the first declaration
						// This is right because we only have generic connectors on models with only one component
						// The general solution should be: the connector has a relative id to the components
						// And the exact id can be resolved after all components have been instantiated
						c.getId() == null || c.getId().isEmpty() ? ds.get(0).getId() : c.getId(),
						c.getPin(),
						c.getTarget()))
				.collect(Collectors.toList()));

		List<ModelicaEquation> meqs = new ArrayList<>(mdef.getConnections().size());
		for (Connection c : mdef.getConnections())
		{
			String ref1 = ModelicaUtil.idvar2ref(c.getId1(), c.getVar1());
			String ref2 = ModelicaUtil.idvar2ref(c.getId2(), c.getVar2());
			meqs.add(new ModelicaConnect(ref1, ref2));
		}
		m.addEquations(meqs);

		return m;
	}

	private String buildModelicaModelId(Model m, Identifiable<?> element)
	{
		// If the model definition refers to a type build the identifier from the element identifier
		if (m instanceof ModelForType) return ModelicaUtil.dynamicIdFromStaticId(element.getId());
		else if (m instanceof ModelForElement) return ((ModelForElement) m).getId();
		// TODO If many events on same element we should assign different identifiers
		else if (m instanceof ModelForEvent) return "EVT".concat(element.getId());

		throw new RuntimeException(
				"Don't know how to build Modelica model id for element " +
						element.getId() +
						" on model class " +
						m.getClass().getName());
	}

	private String buildModelicaDeclarationId(
			Model m,
			Component c,
			Identifiable<?> element)
	{
		// If the model definition refers to a type use a combination of component name and element id
		if (m instanceof ModelForType)
			return ModelicaUtil.dynamicDeclarationIdFromModelForType(
					((ModelForType) m).getType(),
					c.getName(),
					element.getId());
		else if (m instanceof ModelForEvent)
			return ModelicaUtil.dynamicDeclarationIdFromModelForEvent(
					((ModelForEvent) m).getEvent(),
					c.getName(),
					element.getId());
		return c.getId();
	}

	private List<ModelicaArgument> buildModelicaArguments(Component mc)
	{
		ParameterSet set = getParameterSet(mc);
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

	private ParameterSet getParameterSet(Component mc)
	{
		ParameterSet set = mc.getParameterSet();
		if (set == null)
		{
			ParameterSetReference pref = mc.getParameterSetReference();
			if (pref != null) set = parameters.get(pref);
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
				if (mc.isInitialization())
					initializationModels.add(mc);
				else
					dynamicModels.add(mc);
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
		for (Model m : dyd.getModelDefinitions())
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

	public void write(
			String systemDefinitionsDefaultName,
			String dynamicModelsDefaultName,
			String initializationModelsDefaultName) throws XMLStreamException, IOException
	{
		Path f;

		// Write all model containers, all model initialization containers and all parameter set containers
		f = fileForDydContent(systemDefinitions, systemDefinitionsDefaultName);
		DydXml.write(f, systemDefinitions);
		for (ModelContainer mc : dynamicModels.getContainers())
		{
			f = fileForDydContent(mc, dynamicModelsDefaultName);
			DydXml.write(f, mc);
		}
		for (ModelContainer ic : initializationModels.getContainers())
		{
			f = fileForDydContent(ic, initializationModelsDefaultName);
			DydXml.write(f, ic);
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

	public void addModel(Model mdef)
	{
		dynamicModels.add(mdef);
	}

	public void addInitializationModel(Model mdefi)
	{
		initializationModels.add(mdefi);
	}

	public Model getDynamicModelForStaticType(String type)
	{
		return dynamicModels.getDynamicModelForStaticType(type);
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

	private Path fileForDydContent(DydContent dyd, String defaultName)
	{
		String name = dyd.getName();
		if (name == null) name = defaultName;
		return location.resolve(name + ".dyd");
	}

	private Path						location;
	private ModelProvider				dynamicModels;
	private ModelProvider				initializationModels;
	private SystemDefinitions			systemDefinitions;
	private ParameterSetProvider		parameters;

	private static final PathMatcher	DYD_MATCHER	= FileSystems
			.getDefault().getPathMatcher("glob:*.dyd");
	private static final Logger			LOG			= LoggerFactory
			.getLogger(DynamicDataRepositoryDydFiles.class);
}
