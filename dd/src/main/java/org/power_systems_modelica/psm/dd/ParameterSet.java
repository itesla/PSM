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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParameterSet
{
	public ParameterSet(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void add(List<Parameter> ps)
	{
		params.addAll(ps);
	}

	public void add(Parameter p)
	{
		if (params.contains(p))
		{
			duplicatedParams.add(p);
		}
		params.add(p);
	}

	public List<Parameter> getParameters()
	{
		return params;
	}

	public List<Parameter> getDuplicateds()
	{
		return duplicatedParams;
	}

	private final String			id;
	private final List<Parameter>	params				= new ArrayList<>();
	private final List<Parameter>	duplicatedParams	= new ArrayList<>();
}
