package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;

@SuppressWarnings("serial")
public class IncompleteReferenceException extends ModelicaArgumentReferenceException
{
	public IncompleteReferenceException(ModelicaArgumentReference a)
	{
		super(a);
	}
}
