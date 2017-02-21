package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

public class ModelContainer implements DydContent
{
	public ModelContainer()
	{
		this.name = null;
	}

	public ModelContainer(String name)
	{
		this.name = name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public List<Model> getModels()
	{
		return this.models;
	}

	public void add(Collection<Model> ms)
	{
		this.models.addAll(ms);
	}

	public void add(Model m)
	{
		this.models.add(m);
	}

	public void add(Association a)
	{
		this.associations.add(a);
	}

	public List<Association> getAssociations()
	{
		return associations;
	}

	public boolean isForSystemDefinitions()
	{
		if (models.size() > 0)
		{
			Model m = models.get(0);
			if (m instanceof ModelForElement)
			{
				ModelForElement ms = (ModelForElement) m;
				if (ModelicaUtil.getSystemStaticId().equals(ms.getStaticId()))
					return true;
			}
		}
		return false;
	}
	
	public Stage getStage()
	{
		if (models.size() > 0)
		{
			Model m = models.get(0);
			if (m instanceof ModelForElement)
				return m.getStage();
		}
		return null;
	}

	@Override
	public String toString()
	{
		if (models.size() > 0)
		{
			Model m = models.get(0);
			if (m instanceof ModelForElement)
			{
				ModelForElement ms = (ModelForElement) m;
				if (ModelicaUtil.getSystemStaticId().equals(ms.getStaticId()))
					return ms.toString();
			}
		}
		
		return "";
	}
	

	private String					name;
	private final List<Model>		models			= new ArrayList<>();
	private final List<Association>	associations	= new ArrayList<>();
}
