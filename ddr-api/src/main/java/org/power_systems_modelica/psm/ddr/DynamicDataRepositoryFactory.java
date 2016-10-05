package org.power_systems_modelica.psm.ddr;

public interface DynamicDataRepositoryFactory
{
	String getType();

	DynamicDataRepository create();
}
