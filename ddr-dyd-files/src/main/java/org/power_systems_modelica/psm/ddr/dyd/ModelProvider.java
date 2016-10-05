package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Line;
import eu.itesla_project.iidm.network.Load;
import eu.itesla_project.iidm.network.ShuntCompensator;
import eu.itesla_project.iidm.network.TwoWindingsTransformer;

public class ModelProvider
{
	public ModelProvider(boolean isInitialization)
	{
		containers.add(new ModelContainer(isInitialization));
	}

	public Collection<ModelContainer> getContainers()
	{
		return containers;
	}

	public Model getModel(Identifiable<?> e)
	{
		Model mdef = getDynamicModelForId(validDynamicId(e.getId()));
		if (mdef == null) mdef = getDynamicModelForStaticType(getType(e));
		return mdef;
	}

	public ModelForEvent getModelForEvent(String e)
	{
		return dynamicModelsForEvent.get(e);
	}

	public ModelContainer getContainer(String name)
	{
		return containers.stream().filter(c -> c.getName().equals(name)).findFirst().get();
	}

	public Model getDynamicModelForId(String id)
	{
		return dynamicModelsForElement.get(id);
	}

	public Model getDynamicModelForStaticType(String type)
	{
		return dynamicModelsForType.get(type);
	}

	public void add(ModelContainer c)
	{
		containers.add(c);
		// We assume the model container will not be further modified,
		// We will index only the models found right now inside it
		index(c);
	}

	public void add(Model m)
	{
		// We have always at least one container
		// We index each model added explicitly
		containers.get(0).add(m);
		index(m);
	}

	private void index(ModelContainer c)
	{
		for (Model m : c.getModelDefinitions())
			index(m);
	}

	private void index(Model m)
	{
		if (m instanceof ModelForElement)
			dynamicModelsForElement.put(((ModelForElement) m).getStaticId(), (ModelForElement) m);
		else if (m instanceof ModelForType)
			dynamicModelsForType.put(((ModelForType) m).getType(), (ModelForType) m);
		else if (m instanceof ModelForEvent)
			dynamicModelsForEvent.put(((ModelForEvent) m).getEvent(), (ModelForEvent) m);
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

	private String validDynamicId(String id)
	{
		// Some characters are not allowed in dynamic model identifiers
		return id.replace('-', '_');
	}

	private final List<ModelContainer>			containers				= new ArrayList<>();
	private final Map<String, ModelForElement>	dynamicModelsForElement	= new HashMap<>();
	private final Map<String, ModelForType>		dynamicModelsForType	= new HashMap<>();
	private final Map<String, ModelForEvent>	dynamicModelsForEvent	= new HashMap<>();
}
