package org.power_systems_modelica.psm.modelica.builder;

/*
 * #%L
 * Modelica network builder
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Collection;

import com.powsybl.iidm.network.Identifiable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
@SuppressWarnings("serial")
public class MissingDynamicModelException extends Exception
{
	public MissingDynamicModelException(Collection<Identifiable<?>> elements)
	{
		this.elements = elements;
	}

	public Collection<Identifiable<?>> getElements()
	{
		return elements;
	}

	private final Collection<Identifiable<?>> elements;
}
