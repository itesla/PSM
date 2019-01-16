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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.power_systems_modelica.psm.dd.Association;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.ModelForElement;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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

	public void setDynamo(boolean dynamo)
	{
		this.dynamo = dynamo;
	}

	public boolean isDynamo()
	{
		return dynamo;
	}

	public List<Model> getModels()
	{
		return this.models;
	}

	public Model getModel(String id, Stage stage)
	{
		if (ModelicaUtil.containsOmegaRef(id)) id = "DM__SYSTEM_";
		for (Model m : getModels())
		{
			if (m.getId().equals(id) && m.getStage().equals(stage))
				return m;
		}
		return null;
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
	private boolean					dynamo;
	private final List<Model>		models			= new ArrayList<>();
	private final List<Association>	associations	= new ArrayList<>();
}
