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

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaArgumentReference extends ModelicaArgument
{
	public ModelicaArgumentReference(String name, String dataSource, String sourceName)
	{
		super(name, null);
		this.dataSource = dataSource;
		this.sourceName = sourceName;
	}

	public String getDataSource()
	{
		return dataSource;
	}
	
	public String getSourceName()
	{
		return sourceName;
	}
	
	@Override
	public String getValue()
	{
		throw new UnsupportedOperationException("it is not valid to call getValue for an argument reference");
	}

	private final String	dataSource;
	private final String	sourceName;
}
