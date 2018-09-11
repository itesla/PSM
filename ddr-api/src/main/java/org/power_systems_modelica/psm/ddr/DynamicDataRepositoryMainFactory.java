package org.power_systems_modelica.psm.ddr;

/*
 * #%L
 * Dynamic Data Repository API
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.commons.util.ServiceLoaderCache;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DynamicDataRepositoryMainFactory
{
	public static DynamicDataRepository create(String type, String location)
	{
		try
		{
			Optional<DynamicDataRepositoryFactory> f;
			f = DDR_FACTORIES
					.getServices()
					.stream()
					.filter(d -> d.getType().equals(type))
					.findFirst();
			if (!f.isPresent()) throw new Exception("No DDR factory for type " + type);
			DynamicDataRepository ddr = f.get().create();
			ddr.setLocation(location);
			return ddr;
		}
		catch (Exception x)
		{
			LOG.error(
					"Could not create Dynamic Data Repository of type {} at location {}, reason is '{}'",
					type,
					location,
					x.getMessage());
			throw new RuntimeException(x);
		}
	}

	private static final ServiceLoaderCache<DynamicDataRepositoryFactory>	DDR_FACTORIES	= new ServiceLoaderCache<>(
			DynamicDataRepositoryFactory.class);
	private static final Logger												LOG				= LoggerFactory
			.getLogger(DynamicDataRepositoryMainFactory.class);
}
