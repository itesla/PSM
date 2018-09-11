package org.power_systems_modelica.psm.ddr.dyd;

/*
 * #%L
 * Dynamic Data Repository on DYD files
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
import java.util.Objects;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.dd.Association;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class AssociationMapping
{
	public AssociationMapping(String associationKey)
	{
		this.associationKey = associationKey;
	}

	public String getKey()
	{
		return associationKey;
	}

	public void add(Association association, ModelContainer mc)
	{
		Objects.requireNonNull(association);
		Objects.requireNonNull(mc);
		associations.add(association);
		mcs.add(mc);
	}

	public List<Association> getAssociations()
	{
		return associations;
	}

	public List<ModelContainer> getModelContainers()
	{
		return mcs;
	}

	public boolean isDuplicated()
	{
		return mcs.size() > 1 || associations.size() > 1;
	}

	@Override
	public String toString()
	{
		return associations.get(0).toString() + 
				" definition at " + mcs.stream().map(mc -> "'" + mc.getName() + "'")
						.collect(Collectors.joining(", "));
	}

	private String					associationKey;
	private List<ModelContainer>	mcs				= new ArrayList<>();
	private List<Association>		associations	= new ArrayList<>();
}