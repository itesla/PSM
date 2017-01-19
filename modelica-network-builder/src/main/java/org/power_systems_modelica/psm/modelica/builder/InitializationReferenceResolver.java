package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
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
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			ModelicaDeclaration d)
			throws UnresolvedReferenceException
	{
		// Solve the reference to initialization data in the context of given model and declaration
		Object value;
		try
		{
			value = results.get(m.getStaticId(), a.getSourceName());
			return value;
		}
		catch (Exception e)
		{
			String msg = String.format(
					"Unresolved initialization results reference. staticId = %s, name = %s; inside declaration d = %s",
					m.getStaticId(),
					a.getSourceName(),
					d.getId());
			LOG.error(msg);
			throw new UnresolvedReferenceException(a);
		}
	}

	private final InitializationResults	results;

	private static final Logger			LOG	= LoggerFactory
			.getLogger(InitializationReferenceResolver.class);
}
