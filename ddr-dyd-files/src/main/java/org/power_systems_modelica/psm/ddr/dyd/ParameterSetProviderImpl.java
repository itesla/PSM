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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.power_systems_modelica.psm.dd.DD;
import org.power_systems_modelica.psm.dd.ParameterSet;
import org.power_systems_modelica.psm.dd.ParameterSetProvider;
import org.power_systems_modelica.psm.dd.ParameterSetReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParameterSetProviderImpl implements ParameterSetProvider
{
	public Collection<ParameterSetContainer> getContainers()
	{
		return parameterSetContainers.values();
	}

	public boolean contains(String filename)
	{
		return parameterSetContainers.containsKey(filename);
	}

	public void addContainer(ParameterSetContainer c)
	{
		String container = c.getName();
		parameterSetContainers.put(container, c);
	}

	public ParameterSetContainer getContainer(String name)
	{
		return parameterSetContainers.get(name);
	}

	public ParameterSet get(ParameterSetReference ref, String staticId)
	{
		String container = ref.getContainer();
		ParameterSetContainer c = parameterSetContainers.get(container);
		if (c == null)
		{
			LOG.warn("Parameter set container not found: {}", container);
			return EMPTY_PARAMETER_SET;
		}

		// The set identifier of the parameter set reference may contain a variable that must be expanded
		// parId = "{staticId}_params_for_component_a"
		String setId = DD.dynamicId(ref.getSet(), staticId);
		ParameterSet set = c.get(setId);
		if (set == null)
		{
			LOG.warn("Parameter set not found. Container = {}, set = {}",
					container,
					setId);
			return EMPTY_PARAMETER_SET;
		}
		return set;
	}

	private final Map<String, ParameterSetContainer>	parameterSetContainers	= new HashMap<>();
	private static final ParameterSet					EMPTY_PARAMETER_SET		= new ParameterSet(
			"empty");
	private static final Logger							LOG						= LoggerFactory
			.getLogger(ParameterSetProviderImpl.class);
}
