package org.power_systems_modelica.psm.ddr.dyd;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	public ParameterSet get(ParameterSetReference ref)
	{
		String container = ref.getContainer();
		ParameterSetContainer c = parameterSetContainers.get(container);
		if (c == null) return null;

		String set = ref.getSet();
		return c.get(set);
	}

	private final Map<String, ParameterSetContainer> parameterSetContainers = new HashMap<>();

}
