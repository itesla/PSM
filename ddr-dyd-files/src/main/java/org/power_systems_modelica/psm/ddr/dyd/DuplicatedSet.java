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

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DuplicatedSet
{

	public DuplicatedSet(List<String> names, String file)
	{
		super();
		this.names.addAll(names);
		this.file = file;
	}

	@Override
	public String toString()
	{
		return "Set " + String.join(",", names) + " at '" + file + "'";
	}

	private List<String>	names	= new ArrayList<>();
	private String			file;
}
