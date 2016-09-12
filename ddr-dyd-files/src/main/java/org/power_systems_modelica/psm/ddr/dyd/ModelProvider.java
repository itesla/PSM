package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelProvider
{
	public Model getDynamicModelForId(String id)
	{
		return dynamicModelsById.get(id);
	}

	public void add(ModelContainer c)
	{
		containers.add(c);
		index(c);
	}

	private void index(ModelContainer c)
	{
		for (Model m : c.getModelDefinitions())
			dynamicModelsById.put(m.getStaticId(), m);
	}

	private final List<ModelContainer>	containers			= new ArrayList<>();
	private final Map<String, Model>	dynamicModelsById	= new HashMap<>();
}
