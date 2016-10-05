package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

public interface ReferenceResolver
{
	// Return the value of the referred property in the context of the given Modelica model and declaration
	Object resolveReference(String name, ModelicaModel m, ModelicaDeclaration d);
}
