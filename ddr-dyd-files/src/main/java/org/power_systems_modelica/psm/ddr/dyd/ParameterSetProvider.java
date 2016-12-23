package org.power_systems_modelica.psm.ddr.dyd;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterSetProvider
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
		String setId = DynamicDataRepositoryDydFiles.dynamicId(ref.getSet(), staticId);
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
			.getLogger(ParameterSetProvider.class);
}
