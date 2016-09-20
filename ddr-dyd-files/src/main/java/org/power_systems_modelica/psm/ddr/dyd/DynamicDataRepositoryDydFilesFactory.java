package org.power_systems_modelica.psm.ddr.dyd;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryFactory;

import com.google.auto.service.AutoService;

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
