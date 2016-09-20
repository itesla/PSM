package org.power_systems_modelica.psm.ddr;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.commons.util.ServiceLoaderCache;

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
