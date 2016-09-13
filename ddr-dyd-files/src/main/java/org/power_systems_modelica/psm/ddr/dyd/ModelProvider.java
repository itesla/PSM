package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelProvider
{
	public ModelProvider()
	{
		containers.add(new ModelContainer());
	}

	public ModelContainer getDefaultContainer()
	{
		return containers.get(0);
	}

	public Model getDynamicModelForId(String id)
	{
		return dynamicModelsById.get(id);
	}

	public Model getDynamicModelForStaticType(String type)
	{
		return dynamicModelsByType.get(type);
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
		if (m.getStaticId() != null) dynamicModelsById.put(m.getStaticId(), m);
		if (m instanceof ModelForType) dynamicModelsByType.put(((ModelForType) m).getType(), m);
	}

	private final List<ModelContainer>	containers			= new ArrayList<>();
	private final Map<String, Model>	dynamicModelsById	= new HashMap<>();
	private final Map<String, Model>	dynamicModelsByType	= new HashMap<>();
}
