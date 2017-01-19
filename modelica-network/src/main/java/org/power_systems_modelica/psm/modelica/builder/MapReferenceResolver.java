package org.power_systems_modelica.psm.modelica.builder;

import java.util.Map;

import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

public class MapReferenceResolver implements ReferenceResolver
{
	public MapReferenceResolver(Map<String, Object> map)
	{
		this.map = map;
	}

	@Override
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			ModelicaDeclaration d) throws UnresolvedReferenceException
	{
		if (!map.containsKey(a.getSourceName())) throw new UnresolvedReferenceException(a);
		return map.get(a.getSourceName());
	}

	private final Map<String, Object> map;
}
