package org.power_systems_modelica.psm.dd;

/*
 * #%L
 * Dynamic Data
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
public class ParameterReference extends Parameter
{
	public ParameterReference(String name, String sourceName)
	{
		this(null, name, null, null, sourceName);
	}

	public ParameterReference(String type, String name, String unit, String dataSource, String sourceName)
	{
		super(name, unit);
		this.dataSource = dataSource;
		this.sourceName = sourceName;
		this.type = type;
	}

	public String getDataSource()
	{
		return dataSource;
	}

	public String getSourceName()
	{
		return sourceName;
	}

	public String getType()
	{
		return type;
	}
	
	@Override
	public boolean isGeneric()
	{
		return true;
	}
	
	@Override
    public boolean equals(Object other) {
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ParameterReference))return false;
	    
	    ParameterReference p = (ParameterReference) other;
        return (this.getName().equals(p.getName()));
    }

	private final String	type;
	private final String	dataSource;
	private final String	sourceName;
}
