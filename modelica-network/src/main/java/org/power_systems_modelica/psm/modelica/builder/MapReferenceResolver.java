package org.power_systems_modelica.psm.modelica.builder;

import java.util.Map;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

public class MapReferenceResolver implements ReferenceResolver
{
	public MapReferenceResolver(Map<String, Object> map)
	{
		this.map = map;
	}

	@Override
	public Object resolveReference(String name, ModelicaModel m, ModelicaDeclaration d)
	{
		return map.get(name);
	}

	private final Map<String, Object> map;
}
