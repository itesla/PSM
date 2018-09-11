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

import org.power_systems_modelica.psm.dd.Model;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelMapping
{
	public ModelMapping(String modelKey)
	{
		this.modelKey = modelKey;
	}

	public String getKey()
	{
		return modelKey;
	}

	public void add(Model model, ModelContainer mc)
	{
		Objects.requireNonNull(model);
		Objects.requireNonNull(mc);
		models.add(model);
		mcs.add(mc);
	}

	public List<Model> getModels()
	{
		return models;
	}

	public List<ModelContainer> getModelContainers()
	{
		return mcs;
	}

	public boolean isDuplicated()
	{
		return mcs.size() > 1 || models.size() > 1;
	}

	@Override
	public String toString()
	{
		return models.get(0).toString() +
				" for " + models.get(0).getStage().name() +
				" at " + mcs.stream().map(mc -> "'" + mc.getName() + "'")
						.collect(Collectors.joining(", "));
	}

	private String					modelKey;
	private List<ModelContainer>	mcs		= new ArrayList<>();
	private List<Model>				models	= new ArrayList<>();
}
