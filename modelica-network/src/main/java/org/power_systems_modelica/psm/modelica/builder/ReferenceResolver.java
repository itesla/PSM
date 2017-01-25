package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaInterconnection;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

public interface ReferenceResolver
{
	// Return the value of the referred property in the context of the given Modelica model and declaration
	Object resolveReference(ModelicaArgumentReference a, ModelicaModel m, ModelicaDeclaration d)
			throws ModelicaArgumentReferenceException;

	// Return the connection point for the target item and pin given the source model
	default ModelicaInterconnection resolveConnectionTarget(
			String targetItem,
			String targetPin,
			ModelicaModel sourceModel)
	{
		throw new RuntimeException("Unable to solve connection targets");
	}
}
