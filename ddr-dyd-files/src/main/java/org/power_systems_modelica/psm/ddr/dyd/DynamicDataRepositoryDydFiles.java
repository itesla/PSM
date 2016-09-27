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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.dyd.equations.Context;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equal;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.equations.Expression;
import org.power_systems_modelica.psm.ddr.dyd.equations.ExpressionTemplate;
import org.power_systems_modelica.psm.ddr.dyd.equations.Factors;
import org.power_systems_modelica.psm.ddr.dyd.equations.Folding.Sum;
import org.power_systems_modelica.psm.ddr.dyd.equations.ForAll;
import org.power_systems_modelica.psm.ddr.dyd.equations.Literal;
import org.power_systems_modelica.psm.ddr.dyd.equations.PrefixSelector;
import org.power_systems_modelica.psm.ddr.dyd.equations.Quotient;
import org.power_systems_modelica.psm.ddr.dyd.equations.Selector;
import org.power_systems_modelica.psm.ddr.dyd.xml.ModelContainerXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParameterSetContainerXml;
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
		// FIXME read from dyd files
		boolean param = true;
		boolean notParam = false;
		String omegaRefType = "Modelica.Blocks.Interfaces.RealOutput";
		return Arrays.asList(
				new ModelicaDeclaration("Real", "SNREF", "100.0", param),
				new ModelicaDeclaration(omegaRefType, "omegaRef", null, notParam));
	}

	@Override
	public List<ModelicaEquation> getSystemEquations(ModelicaSystemModel m)
	{
		// FIXME read equation definitions from dyd files
		Selector selector = new PrefixSelector("gen_pwGeneratorM2S_");
		ExpressionTemplate t1 = new ExpressionTemplate(
				"_g",
				"_g.omega*_g.SN*_g.HIn");
		ExpressionTemplate t2 = new ExpressionTemplate(
				"_g",
				"_g.SN*_g.HIn");
		Factors f1 = new ForAll(selector, t1);
		Factors f2 = new ForAll(selector, t2);
		Sum s1 = new Sum(f1);
		Sum s2 = new Sum(f2);
		Quotient q = new Quotient(s1, s2);
		Expression weightedAverageOfGeneratorOmegas = q;
		Equation eqw = new Equal(
				new Literal("omegaRef"),
				weightedAverageOfGeneratorOmegas);

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
		String eqwText = eqw.writeIn(contextModelica);
		ModelicaEquation eqwm = new ModelicaEquation(eqwText);
		eqwm.setAnnotation(null);
		return Arrays.asList(eqwm);
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

	private ModelicaModel buildModelicaModelFromDynamicModelDefinition(Model mdef,
			Identifiable<?> element)
	{
		ModelicaModel m = new ModelicaModel(buildModelicaModelId(mdef, element));
		m.setStaticId(buildModelicaModelStaticId(mdef, element));

		List<ModelicaDeclaration> ds = new ArrayList<>();
		for (Component mc : mdef.getComponents())
		{
			String type = mc.getName();
			String id = buildModelicaDeclarationId(mdef, mc, element);
			List<ModelicaArgument> arguments = buildModelicaArguments(mc);

			// FIXME everything is a variable, no parameters; isParameter should be defined with the model
			boolean isParameter = false;
			ds.add(new ModelicaDeclaration(type, id, arguments, isParameter));
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
						c.isReusable()))
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
		return m.getId();
	}

	private String buildModelicaModelStaticId(Model mdef, Identifiable<?> element)
	{
		// If model definition does not provide a static identifier, return element identifier
		if (mdef.getStaticId() == null || mdef.getStaticId().equals("")) return element.getId();
		return mdef.getStaticId();
	}

	private String buildModelicaDeclarationId(
			Model m,
			Component c,
			Identifiable<?> element)
	{
		// If the model definition refers to a type use a combination of component name and element id
		if (m instanceof ModelForType)
			return ModelicaUtil.dynamicDeclarationIdFromStaticId(
					((ModelForType) m).getType(),
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
		reset();
		try
		{
			read();
		}
		catch (IOException e)
		{
			throw new ConnectionException(e);
		}
	}

	private void reset()
	{
		dynamicModels = new ModelProvider(false);
		initializationModels = new ModelProvider(true);
		parameters = new ParameterSetProvider();
		mappings.clear();
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
				if (isDyd(file))
				{
					ModelContainer dyd = readDyd(file);
					if (dyd != null)
					{
						if (dyd.isInitialization())
							initializationModels.add(dyd);
						else
							dynamicModels.add(dyd);
						resolveParameters(dyd);
					}
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private ModelContainer readDyd(Path file)
	{
		ModelContainer dyd = null;
		try
		{
			dyd = ModelContainerXml.read(file);
		}
		catch (XMLStreamException | IOException e)
		{
			LOG.warn("ignored DYD file {} because of error {}", file, e);
		}
		return dyd;
	}

	private void resolveParameters(ModelContainer dyd)
	{
		for (Model m : dyd.getModelDefinitions())
			for (Component mc : m.getComponents())
				if (mc.getParameterSetReference() != null)
					resolveParameters(mc.getParameterSetReference().getContainer());
	}

	private void resolveParameters(String filename)
	{
		if (parameters.contains(filename)) return;
		ParameterSetContainer c = readParameters(filename);
		if (c != null)
		{
			c.setFilename(filename);
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
			container = ParameterSetContainerXml.read(f);
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

	private Path						location;
	private ModelProvider				dynamicModels;
	private ModelProvider				initializationModels;
	private ParameterSetProvider		parameters;
	private Map<String, ModelicaModel>	mappings	= new HashMap<>();

	private static final PathMatcher	DYD_MATCHER	= FileSystems
			.getDefault().getPathMatcher("glob:*.dyd");
	private static final Logger			LOG			= LoggerFactory
			.getLogger(DynamicDataRepositoryDydFiles.class);
}
