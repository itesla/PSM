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

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryFactory;

import com.google.auto.service.AutoService;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
@AutoService(DynamicDataRepositoryFactory.class)
public class DynamicDataRepositoryDydFilesFactory implements DynamicDataRepositoryFactory
{
	@Override
	public String getType()
	{
		return "DYD";
	}

	@Override
	public DynamicDataRepository create()
	{
		return new DynamicDataRepositoryDydFiles();
	}
}
