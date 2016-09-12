package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaModel;

public interface ReferenceResolver
{
	// Return the value of the referred property in the context of the given ModelicaModel
	public Object resolveReference(String name, ModelicaModel m);
}
