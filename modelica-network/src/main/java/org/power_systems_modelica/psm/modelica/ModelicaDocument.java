package org.power_systems_modelica.psm.modelica;

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

import java.util.Optional;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaDocument
{
	public void setWithin(String within)
	{
		this.within = Optional.ofNullable(within).orElse("");
	}

	public String getWithin()
	{
		return within;
	}

	public void setSystemModel(ModelicaSystemModel m)
	{
		systemModel = m;
	}

	public ModelicaSystemModel getSystemModel()
	{
		return systemModel;
	}

	public ModelicaDocument copy(String systemId)
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin(within);
		mo.setSystemModel(systemModel.copy(systemId));
		return mo;
	}

	private String				within;
	private ModelicaSystemModel	systemModel;
}
