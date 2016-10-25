package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModelContainer implements DydContent
{
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public List<Model> getModelDefinitions()
	{
		return this.dynamicModelDefinitions;
	}

	public void add(Collection<Model> mdefs)
	{
		this.dynamicModelDefinitions.addAll(mdefs);
	}

	public void add(Model mdef)
	{
		this.dynamicModelDefinitions.add(mdef);
	}

	private String				name;
	private final List<Model>	dynamicModelDefinitions	= new ArrayList<>();
}
