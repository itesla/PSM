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
public class ParameterValue extends Parameter
{
	public ParameterValue(String type, String unit, String name, Object value)
	{
		super(name, unit);
		this.type = type;
		this.value = value;
		this.isGeneric = false;
	}

	public String getType()
	{
		return type;
	}

	public Object getValue()
	{
		return value;
	}

	@Override
	public boolean isGeneric()
	{
		return this.isGeneric;
	}

	public void setGeneric(boolean isGeneric)
	{
		this.isGeneric = isGeneric;
	}

	@Override
    public boolean equals(Object other) {
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof ParameterValue))return false;
	    
	    ParameterValue p = (ParameterValue) other;
        return (this.getName().equals(p.getName()));
    }

	private final String	type;
	private final Object	value;
	private boolean			isGeneric;
}
