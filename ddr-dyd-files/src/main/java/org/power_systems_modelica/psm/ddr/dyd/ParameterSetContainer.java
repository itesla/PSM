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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.dd.ParameterSet;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParameterSetContainer implements DydContent
{
	public ParameterSetContainer()
	{
		this.setCounter = 0;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public ParameterSet newParameterSet()
	{
		int id = ++setCounter;
		return new ParameterSet("" + id);
	}

	public void add(ParameterSet parameterSet)
	{
		if (parameterSets.containsKey(parameterSet.getId()))
		{
			duplicatedParameterSets.add(parameterSet);
		}
		parameterSets.put(parameterSet.getId(), parameterSet);
	}

	public ParameterSet get(String setId)
	{
		return parameterSets.get(setId);
	}

	public Collection<ParameterSet> getSets()
	{
		return Collections.unmodifiableCollection(parameterSets.values());
	}

	public List<ParameterSet> getDuplicateds()
	{
		return duplicatedParameterSets;
	}

	private String							name;
	private int								setCounter;

	private final Map<String, ParameterSet>	parameterSets			= new HashMap<>();
	private final List<ParameterSet>		duplicatedParameterSets	= new ArrayList<>();
}
