package org.power_systems_modelica.psm.ddr;

public interface DynamicDataRepositoryFactory
{
	public String getType();

	public DynamicDataRepository create();
}
