package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;

@SuppressWarnings("serial")
public class ModelicaArgumentReferenceException extends Exception
{
	public ModelicaArgumentReferenceException(ModelicaArgumentReference a)
	{
		this.argumentReference = a;
	}

	public ModelicaArgumentReference getArgumentReference()
	{
		return argumentReference;
	}

	private ModelicaArgumentReference argumentReference;
}
