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

import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
