package org.power_systems_modelica.psm.modelica.builder;

/*
 * #%L
 * Modelica network model
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaInterconnection;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public interface ReferenceResolver
{
	// Return the value of the referred property in the context of the given Modelica model and declaration
	Object resolveReference(ModelicaArgumentReference a, ModelicaModel m, BaseModelicaDeclaration d)
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
