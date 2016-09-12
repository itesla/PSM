package org.power_systems_modelica.psm.ddr;

import eu.itesla_project.commons.util.ServiceLoaderCache;

public class DynamicDataRepositoryMainFactory
{
	public static DynamicDataRepository create(String type, String location)
	{
		DynamicDataRepositoryFactory f = DDR_FACTORIES
				.getServices()
				.stream()
				.filter(d -> d.getType().equals(type))
				.findFirst()
				.get();
		DynamicDataRepository ddr = f.newInstance();
		ddr.setLocation(location);
		return ddr;
	}

	private static final ServiceLoaderCache<DynamicDataRepositoryFactory> DDR_FACTORIES = new ServiceLoaderCache<>(
			DynamicDataRepositoryFactory.class);
}
