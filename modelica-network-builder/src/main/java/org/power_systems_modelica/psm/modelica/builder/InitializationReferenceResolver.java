package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

public class InitializationReferenceResolver implements ReferenceResolver
{
	public InitializationReferenceResolver(InitializationResults results)
	{
		this.results = results;
	}

	@Override
	public Object resolveReference(String name, ModelicaModel m, ModelicaDeclaration d)
	{
		Object value = results.get(m.getStaticId(), d.getId(), name);
		if (value == null) throw new RuntimeException("unresolved INIT reference key = " +
				m.getStaticId() + "---" +
				d.getId() + "---" +
				name);
		else return value;
	}

	private final InitializationResults results;
}
