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
public class Component
{
	public Component(String id, String type)
	{
		this.id = id;
		this.type = type;
	}

	public String getId()
	{
		return id;
	}

	public String getType()
	{
		return type;
	}

	public void setLib(String lib) 
	{
		this.lib = lib;
	}

	public String getLib()
	{
		return lib;
	}

	public void setInitName(String initName)
	{
		this.initName = initName;
	}

	public String getInitName()
	{
		return this.initName;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public Object getValue()
	{
		return this.value;
	}

	public void setParameter(boolean isParameter)
	{
		this.isParameter = isParameter;
	}

	public boolean isParameter()
	{
		return this.isParameter;
	}

	public void setParameterSet(ParameterSet set)
	{
		this.parameterSet = set;
	}

	public ParameterSet getParameterSet()
	{
		return this.parameterSet;
	}

	// For components defined using templates we will not have a parameter reference
	// The parameter reference will go in the template instantiation
	public void setParameterSetReference(ParameterSetReference parameterSetReference)
	{
		this.parameterSetReference = parameterSetReference;
	}

	public ParameterSetReference getParameterSetReference()
	{
		return parameterSetReference;
	}

	private final String			id;
	private final String			type;
	private String					lib;
	private String					initName;
	private Object					value;
	private boolean					isParameter;
	private ParameterSetReference	parameterSetReference;
	private ParameterSet			parameterSet;
}
