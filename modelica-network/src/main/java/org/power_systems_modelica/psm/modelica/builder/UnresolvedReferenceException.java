package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;

@SuppressWarnings("serial")
public class UnresolvedReferenceException extends ModelicaArgumentReferenceException
{
	public UnresolvedReferenceException(ModelicaArgumentReference a)
	{
		super(a);
	}
}
