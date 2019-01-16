package org.power_systems_modelica.psm.ddr.dyd;

/*
 * #%L
 * Dynamic Data Repository on DYD files
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

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
import java.util.Iterator;
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

import org.power_systems_modelica.psm.dd.Association;
import org.power_systems_modelica.psm.dd.AssociationProvider;
import org.power_systems_modelica.psm.dd.Component;
import org.power_systems_modelica.psm.dd.DD;
import org.power_systems_modelica.psm.dd.Event;
import org.power_systems_modelica.psm.dd.Interconnection;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.ModelForAssociation;
import org.power_systems_modelica.psm.dd.ModelForElement;
import org.power_systems_modelica.psm.dd.ModelForEvent;
import org.power_systems_modelica.psm.dd.ModelForEvent.Injection;
import org.power_systems_modelica.psm.dd.ModelForType;
import org.power_systems_modelica.psm.dd.ModelProvider;
import org.power_systems_modelica.psm.dd.Parameter;
import org.power_systems_modelica.psm.dd.ParameterReference;
import org.power_systems_modelica.psm.dd.ParameterSet;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.dd.StaticType;
import org.power_systems_modelica.psm.dd.equations.Context;
import org.power_systems_modelica.psm.dd.equations.Equation;
import org.power_systems_modelica.psm.dd.equations.PrefixSelector;
import org.power_systems_modelica.psm.dd.equations.Selector;
import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.EventParameter;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.ParXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.net.SyslogAppender;
import com.powsybl.iidm.network.Identifiable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DynamicDataRepositoryDydFiles implements DynamicDataRepository
{
	public DynamicDataRepositoryDydFiles()
	{
		modelContainers = new HashMap<>();
		associations = new AssociationProvider();
		modelsByStage = new HashMap<>();
		for (Stage stage : Stage.values())
			modelsByStage.put(stage, new ModelProvider(associations));
		parameters = new ParameterSetProviderImpl();
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
		Context<BaseModelicaDeclaration> contextModelica = new Context<BaseModelicaDeclaration>()
		{
			@Override
			public String write(BaseModelicaDeclaration d)
			{
				return d.getId();
			}

			@Override
			public Stream<BaseModelicaDeclaration> getDomainStream()
			{
				return moSystem.getDeclarations().stream();
			}

			@Override
			public Predicate<BaseModelicaDeclaration> getPredicate(Selector selector)
			{
				Predicate<BaseModelicaDeclaration> p = super.getPredicate(selector);
				if (p != null) return p;
				if (selector instanceof PrefixSelector)
					return dm -> dm.getId().startsWith(((PrefixSelector) selector).getPrefix());
				return null;
			}
		};

		Model mdef = getDynamicModelForSystem(stage);
		if (mdef == null) return null;
		List<Equation> otherEquations = mdef.getOtherEquations();
		if (otherEquations == null) return null;
		List<ModelicaEquation> specialEquations = otherEquations.stream()
				.map(eq -> new ModelicaEquation(eq.writeIn(contextModelica)))
				.collect(Collectors.toList());
		return specialEquations;
	}

	@Override
	public Optional<ModelicaModel> getSystemModel(Stage stage)
	{
		String systemId = ModelicaUtil.getSystemStaticId();
		ModelProvider dynamicModels = modelsByStage.get(stage);
		Optional<ModelicaModel> mo = Optional.ofNullable(getDynamicModelForSystem(stage))
				.map(mdef -> DD.buildModelicaModelFromDynamicModelDefinition(
						mdef,
						systemId,
						parameters,
						dynamicModels))
				.map(mo0 -> {
					mo0.setStaticId(systemId);
					return mo0;
				});
		return mo;
	}

	@Override
	public List<Model> getModels(Identifiable<?> e, Stage stage)
	{
		return modelsByStage.get(stage).getModels(e);
	}
	
	@Override
	public ModelicaModel getModelicaModel(Identifiable<?> e, Stage stage)
	{
		Model mdef = modelsByStage.get(stage).getModel(e);
		ModelProvider dynamicModels = modelsByStage.get(stage);
		if (mdef != null)
			return DD.buildModelicaModelFromDynamicModelDefinition(mdef, e.getId(), parameters, dynamicModels);
		return null;
	}

	@Override
	public ModelicaModel getModelicaModelForEvent(Event event)
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		ModelForEvent mdef = dynamicModels.getModelForEvent(event.getEvent());
		if (mdef != null)
		{
			ModelForEvent mdef1 = mdef;
			// Rebuild a model definition that takes into account the instance attribute of the received Event 
			if (event.getInstance() != null)
			{
				String eventType = mdef.getEvent();
				Injection injection = mdef.getInjection();
				StaticType appliesTo = mdef.getAppliesTo();
				String baseId1 = mdef.getId() + "_" + event.getInstance();//event.getElement().getId() + "_" + event.getInstance();
				mdef1 = new ModelForEvent(eventType, injection, baseId1);
				
				//Clone the ModelForEvent components to take into account the instance attribute of the received Event.
				for(Component c : mdef.getComponents()) {
					Component comp = new Component(c.getId() + "_" + event.getInstance(), c.getType());
					comp.setParameter(c.isParameter());
					comp.setParameterSet(c.getParameterSet());
					comp.setParameterSetReference(c.getParameterSetReference());
					comp.setValue(c.getValue());
					mdef1.addComponent(comp);
				}
				//Clone the ModelForEvent interconnections to take into account the instance attribute of the received Event.
				for(Interconnection i : mdef.getInterconnections()) {
					Interconnection interc = new Interconnection(i.getName(), 
																i.getComponentId() + "_" + event.getInstance(), 
																i.getComponentVar(), 
																i.getTargetModel(), 
																i.getTargetName(), 
																i.getTargetModel2(), 
																i.getTargetName2());
					mdef1.addInterconnection(interc);
				}
				
				//FIXME clone also connections? others?
				mdef1.setAppliesTo(appliesTo);
			}
			return DD.buildModelicaModelFromDynamicModelDefinition(mdef1, event.getElement().getId(), parameters, dynamicModels);
		}
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
	public Injection getEventInjection(String event)
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		ModelForEvent m = dynamicModels.getModelForEvent(event);
		if (m == null)
		{
			LOG.warn("Event definition not found looking for injection {}", event);
			return null;
		}
		return m.getInjection();
	}

	@Override
	public List<EventParameter> getEventParameters(String event)
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		ModelForEvent m = dynamicModels.getModelForEvent(event);
		if (m == null)
		{
			LOG.warn("Event definition not found looking for parameters {}", event);
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
	public StaticType getEventAppliesToStaticType(String event)
	{
		ModelProvider dynamicModels = modelsByStage.get(Stage.SIMULATION);
		ModelForEvent m = dynamicModels.getModelForEvent(event);
		if (m == null)
		{
			LOG.warn("Event definition not found looking for static type {}", event);
			return null;
		}
		StaticType stype = m.getAppliesTo();
		if (stype != null) return stype;
		return null;
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

	public Diagnostics check() throws IOException
	{
		Diagnostics diagnostics = new Diagnostics();
		checkXmls(diagnostics);
		checkDuplicates(diagnostics);

		return diagnostics;
	}

	public void checkXmls(Diagnostics diagnostics) throws IOException
	{
		boolean isValidationActive = XmlUtil.isValidationActive;
		XmlUtil.isValidationActive = true;

		// Read all DYD files in the given location
		Path start = location;
		Files.walkFileTree(start, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			{
				if (isDyd(file)) checkXmlDyd(file, diagnostics);
				return FileVisitResult.CONTINUE;
			}
		});

		XmlUtil.isValidationActive = isValidationActive;
	}

	public void checkDuplicates(Diagnostics diagnostics) throws IOException
	{
		// Read all DYD files in the given location
		Path start = location;
		Files.walkFileTree(start, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			{
				if (isDyd(file)) checkDuplicatesDyd(file, diagnostics);
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
			Path rfile = location.relativize(file);
			String name = rfile.toString().replace(".dyd", "");
			dyd.setName(name);

			if (dyd instanceof ModelContainer)
			{
				ModelContainer mc = (ModelContainer) dyd;
				mc.getAssociations().forEach(a -> addAssociation(name, a));
				mc.getModels().forEach(m -> addModel(name, m));
				resolveParameters(mc);
			}
		}
		catch (XMLStreamException | IOException | RuntimeException e)
		{
			LOG.warn("ignored DYD file {} because of error {}", file, e);
		}
	}

	private void checkXmlDyd(Path file, Diagnostics diagnostics)
	{
		try
		{
			DydXml.read(file);
		}
		catch (Exception e)
		{
			Path rfile = location.relativize(file);
			String name = rfile.toString().replace(".dyd", "");

			diagnostics.addXmlError(name, e.getMessage());
		}
	}

	private void checkDuplicatesDyd(Path file, Diagnostics diagnostics)
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

			mc.getAssociations().forEach(a -> checkDuplicatedAssociation(mc, a, diagnostics));
			mc.getModels().forEach(m -> checkDuplicatedModel(mc, m, diagnostics));
			resolveParameters(mc);
			checkParameters(diagnostics);
		}
		catch (XMLStreamException | IOException e)
		{
			LOG.warn("ignored DYD file {} because of error {}", file, e);
		}
	}

	private void checkParameters(Diagnostics diagnostics)
	{
		Collection<ParameterSetContainer> containers = parameters.getContainers();
		Iterator<ParameterSetContainer> it = containers.iterator();
		while (it.hasNext())
		{
			ParameterSetContainer container = it.next();
			String name = container.getName().replace(".par", "");

			List<ParameterSet> duplicatedSets = container.getDuplicateds();
			if (!duplicatedSets.isEmpty())
			{
				List<String> sets = duplicatedSets.stream().map(ds -> ds.getId())
						.collect(Collectors.toList());
				diagnostics.addDuplicatedSet(sets, name);
			}
			for (ParameterSet set : container.getSets())
			{
				List<Parameter> duplicatedParameters = set.getDuplicateds();
				if (!duplicatedParameters.isEmpty())
				{
					List<String> parameters = duplicatedParameters.stream().map(dp -> dp.getName())
							.collect(Collectors.toList());
					diagnostics.addDuplicatedParameter(parameters, set.getId(), name);
				}
			}
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

	public void addModel(String containerName, Model mdef)
	{
		ModelContainer mc = getCreateModelContainer(containerName);
		mc.add(mdef);
		modelsByStage.get(mdef.getStage()).add(mdef);
	}

	public void checkDuplicatedModel(
			ModelContainer mc,
			Model mdef,
			Diagnostics diagnostics)
	{
		String mdefKey = DynamicDataRepositoryDydFiles.getModelKey(mdef);
		diagnostics.addModelMapping(mdefKey, mdef, mc);
	}
	
	public void checkDuplicatedAssociation(
			ModelContainer mc,
			Association adef,
			Diagnostics diagnostics
			)
	{
		String adefKey = DynamicDataRepositoryDydFiles.getAssociationKey(adef);
		diagnostics.addAssociationMapping(adefKey, adef, mc);
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

	public Model getDynamicModelForStaticType(StaticType type, Stage stage)
	{
		ModelProvider dynamicModels = modelsByStage.get(stage);
		return dynamicModels.getDynamicModelForStaticType(type).orElse(null);
	}

	public Model getDynamicModelForSystem(Stage stage)
	{
		ModelProvider dynamicModels = modelsByStage.get(stage);
		String systemId = ModelicaUtil.getSystemStaticId();
		Optional<Model> m = dynamicModels.getDynamicModelForId(systemId);
		if (m.isPresent()) return m.get();
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
			id = ((ModelForType) mdef).getType().name();
		else if (mdef instanceof ModelForEvent)
			id = ((ModelForEvent) mdef).getEvent();

		return stage + className + id;
	}
	
	private static String getAssociationKey(Association adef)
	{
		String id = adef.getId();
		String className = adef.getClass().getName();

		return className + id;
	}

	private Path						location;
	private Map<String, ModelContainer>	modelContainers;
	private Map<Stage, ModelProvider>	modelsByStage;
	private ParameterSetProviderImpl	parameters;
	private AssociationProvider			associations;

	private static final PathMatcher	DYD_MATCHER	= FileSystems
			.getDefault().getPathMatcher("glob:*.dyd");
	private static final Logger			LOG			= LoggerFactory
			.getLogger(DynamicDataRepositoryDydFiles.class);
}
