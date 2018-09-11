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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.dd.Association;
import org.power_systems_modelica.psm.dd.Model;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class Diagnostics
{

	public void addXmlError(String name, String message)
	{
		xmlErrors.put(name, message);
	}

	public void addModelMapping(String name, Model model, ModelContainer mc)
	{
		if (!modelMapping.containsKey(name))
			modelMapping.put(name, new ModelMapping(name));
			
		modelMapping.get(name).add(model, mc);
	}
	
	public void addAssociationMapping(String name, Association association, ModelContainer mc)
	{
		if (!associationMapping.containsKey(name))
			associationMapping.put(name, new AssociationMapping(name));

		associationMapping.get(name).add(association, mc);
	}

	public void addDuplicatedSet(List<String> names, String file)
	{
		DuplicatedSet set = new DuplicatedSet(names, file);
		sets.add(set);
	}

	public void addDuplicatedParameter(List<String> names, String set, String file)
	{
		DuplicatedParameter p = new DuplicatedParameter(names, set, file);
		parameters.add(p);
	}

	public Map<String, String> getXmlErrors()
	{
		return xmlErrors;
	}

	public Map<String, ModelMapping> getModelMapping()
	{
		return modelMapping;
	}

	public Map<String, AssociationMapping> getAssociationMapping()
	{
		return associationMapping;
	}

	public boolean isSetDuplicated()
	{
		return sets.size() > 0;
	}

	public List<DuplicatedSet> getSets()
	{
		return sets;
	}

	public boolean isParameterDuplicated()
	{
		return parameters.size() > 0;
	}

	public List<DuplicatedParameter> getParameters()
	{
		return parameters;
	}

	private Map<String, String>				xmlErrors			= new HashMap<>();
	private Map<String, ModelMapping>		modelMapping		= new HashMap<>();
	private Map<String, AssociationMapping>	associationMapping	= new HashMap<>();
	private List<DuplicatedSet>				sets				= new ArrayList<>();
	private List<DuplicatedParameter>		parameters			= new ArrayList<>();
}
