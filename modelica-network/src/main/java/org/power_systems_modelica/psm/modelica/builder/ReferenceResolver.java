package org.power_systems_modelica.psm.modelica.builder;

import java.util.Optional;

import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

public interface ReferenceResolver
{
	// Return the value of the referred property in the context of the given Modelica model and declaration
	Object resolveReference(String name, ModelicaModel m, ModelicaDeclaration d);

	// Return the connection point for the target item and pin given the source model
	default Optional<ModelicaConnector> resolveConnectionTarget(
			String targetItem,
			String targetPin,
			ModelicaModel sourceModel)
	{
		throw new RuntimeException("Unable to solve connection targets");
	}
}
