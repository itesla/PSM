package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitializationReferenceResolver implements ReferenceResolver
{
	public InitializationReferenceResolver(InitializationResults results)
	{
		this.results = results;
	}

	@Override
	public Object resolveReference(String name, ModelicaModel m, ModelicaDeclaration d)
	{
		// Solve the reference to initialization data "name" in the context of given model and declaration
		Object value = results.get(m.getStaticId(), name);
		if (value == null)
		{
			String msg = String.format(
					"Unresolved initialization results reference. staticId = %s, name = %s; inside declaration d = %s",
					m.getStaticId(),
					name,
					d.getId());
			LOG.error(msg);
			throw new RuntimeException(msg);
		}
		else return value;
	}

	private final InitializationResults	results;

	private static final Logger			LOG	= LoggerFactory
			.getLogger(InitializationReferenceResolver.class);
}
