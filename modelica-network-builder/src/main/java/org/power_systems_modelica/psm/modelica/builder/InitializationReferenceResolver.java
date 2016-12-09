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
		if (value == null)
		{
			String msg = String.format(
					"Unresolved initialization results reference. staticId = %s, id = %s, name = %s",
					m.getStaticId(),
					d.getId(),
					name);
			throw new RuntimeException(msg);
		}
		else return value;
	}

	private final InitializationResults results;
}
