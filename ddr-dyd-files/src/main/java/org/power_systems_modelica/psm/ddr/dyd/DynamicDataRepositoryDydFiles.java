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

import javax.xml.stream.XMLStreamException;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.dyd.xml.ModelContainerXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParameterSetContainerXml;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaModelInstantiation;
import org.power_systems_modelica.psm.modelica.ModelicaParameter;
import org.power_systems_modelica.psm.modelica.ModelicaType;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Line;
import eu.itesla_project.iidm.network.Load;
import eu.itesla_project.iidm.network.ShuntCompensator;
import eu.itesla_project.iidm.network.TwoWindingsTransformer;

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
	public List<ModelicaParameter> getSystemParameters()
	{
		// FIXME read from dyd files
		return Arrays.asList(new ModelicaParameter(ModelicaType.Real, "SNREF", "100.0"));
		// TODO add omegaRef as a parameter
		// m.addParameters(Arrays.asList(new ModelicaParameter(ModelicaType.Real, "omegaRef", "0.0")));
	}

	@Override
	public ModelicaModel getModelicaModel(Identifiable<?> e)
	{
		Model mdef = dynamicModels.getDynamicModelForId(validDynamicId(e.getId()));
		if (mdef == null) mdef = dynamicModels.getDynamicModelForStaticType(getType(e));
		if (mdef != null) return buildModelicaModelFromDynamicModelDefinition(mdef, e);
		return null;
	}

	private String getType(Identifiable<?> e)
	{
		if (e instanceof Bus) return "Bus";
		else if (e instanceof Line) return "Line";
		else if (e instanceof TwoWindingsTransformer) return "Transformer";
		else if (e instanceof Load) return "Load";
		else if (e instanceof ShuntCompensator) return "Shunt";
		return null;
	}

	@Override
	public ModelicaConnector[] getModelicaConnectors(ModelicaModel m)
	{
		// FIXME connector references should be defined in the DYD for every model:

		String name = m.getModelInstantiations().get(0).getName();
		boolean isBranch = name.startsWith("line_") || name.startsWith("trafo_");

		ModelicaConnector[] connectors = new ModelicaConnector[isBranch ? 2 : 1];

		String pin;
		boolean reusable;

		// Connector 1
		pin = "p";
		if (name.startsWith("gen_"))
		{
			pin = "sortie";
		}
		reusable = false;
		if (name.startsWith("bus_")) reusable = true;
		connectors[0] = new ModelicaConnector(name, pin, reusable);

		// Connector 2, only branches have more than one connector
		if (isBranch)
		{
			pin = "n";
			reusable = false;
			connectors[1] = new ModelicaConnector(name, pin, reusable);
		}

		return connectors;
	}

	private String validDynamicId(String id)
	{
		// Some characters are not allowed in dynamic model identifiers
		return id.replace('-', '_');
	}

	private ModelicaModel buildModelicaModelFromDynamicModelDefinition(Model mdef,
			Identifiable<?> element)
	{
		ModelicaModel m = new ModelicaModel(buildModelicaModelId(mdef, element));
		m.setStaticId(buildModelicaModelStaticId(mdef, element));

		List<ModelicaModelInstantiation> mis = new ArrayList<>();
		for (Component mc : mdef.getComponents())
		{
			String type = mc.getName();
			String name = buildModelicaModelInstantiationName(mdef, mc, element);
			ParameterSetReference p = mc.getParameterSetReference();
			List<ModelicaArgument> arguments = buildModelicaInstatiationArguments(p);
			mis.add(new ModelicaModelInstantiation(type, name, arguments));
		}
		m.addModelInstantiations(mis);

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

	private String buildModelicaModelInstantiationName(
			Model m,
			Component c,
			Identifiable<?> element)
	{
		// If the model definition refers to a type use a combination of component name and element id
		if (m instanceof ModelForType) return ModelicaUtil.dynamicInstantiationIdFromStaticId(c.getName(), element.getId());
		return c.getId();
	}

	private List<ModelicaArgument> buildModelicaInstatiationArguments(
			ParameterSetReference pref)
	{
		if (pref == null) return null;

		ParameterSet set = parameters.get(pref);
		if (set == null) return null;

		List<ModelicaArgument> arguments = new ArrayList<>(set.getParameters().size());
		for (Parameter p : set.getParameters())
		{
			// FIXME should Parameter and ModelicaArgument really be different classes ???
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
		dynamicModels = new ModelProvider();
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
	private ParameterSetProvider		parameters;
	private Map<String, ModelicaModel>	mappings	= new HashMap<>();

	private static final PathMatcher	DYD_MATCHER	= FileSystems
			.getDefault().getPathMatcher("glob:*.dyd");
	private static final Logger			LOG			= LoggerFactory
			.getLogger(DynamicDataRepositoryDydFiles.class);
}
