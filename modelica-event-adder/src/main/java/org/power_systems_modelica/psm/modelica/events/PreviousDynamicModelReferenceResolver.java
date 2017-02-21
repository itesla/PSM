package org.power_systems_modelica.psm.modelica.events;

import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.ReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedReferenceException;

public class PreviousDynamicModelReferenceResolver implements ReferenceResolver
{
	public PreviousDynamicModelReferenceResolver(ModelicaModel m)
	{
		this.model = m;
	}

	@Override
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			ModelicaDeclaration d) throws UnresolvedReferenceException
	{
		String referred = a.getSourceName();

		// TODO We are looking in all declarations the first argument that has the sourceName
		// We could assume that the referred name has a declaration id and an argument name,
		// to be more explicit in our references
		
		for (ModelicaDeclaration d0 : model.getDeclarations())
		{
			for (ModelicaArgument a0 : d0.getArguments())
			{
				if (a0.getName().equals(referred))
					return a0.getValue();
			}
		}

		throw new UnresolvedReferenceException(a);
	}

	private final ModelicaModel model;

}
