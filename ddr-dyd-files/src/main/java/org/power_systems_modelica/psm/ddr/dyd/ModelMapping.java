package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapping
{
	public ModelMapping(String modelKey)
	{
		this.modelKey = modelKey;
	}

	public String getKey()
	{
		return modelKey;
	}

	public void add(Model model, ModelContainer mc)
	{
		models.add(model);
		mcs.add(mc);
	}

	public List<Model> getModels()
	{
		return models;
	}

	public List<ModelContainer> getModelContainers()
	{
		return mcs;
	}

	public boolean isDuplicated()
	{
		return mcs.size() > 1 || models.size() > 1;
	}

	@Override
	public String toString()
	{
		return (models.get(0) != null
				? models.get(0).toString() + " for " + models.get(0).getStage().name() : mcs.get(0).toString() + " for " + mcs.get(0).getStage().name()) +
				" at " +
				mcs.stream().map(mc -> "'" + mc.getName() + "'").collect(Collectors.joining(", "));
	}

	private String					modelKey;
	private List<ModelContainer>	mcs		= new ArrayList<>();
	private List<Model>				models	= new ArrayList<>();
}
