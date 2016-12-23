package org.power_systems_modelica.psm.ddr.dyd;

public class ParameterReference extends Parameter
{
	public ParameterReference(String name, String unit, String dataSource, String sourceName)
	{
		super(name, unit);
		this.dataSource = dataSource;
		this.sourceName = sourceName;
	}

	public String getDataSource()
	{
		return dataSource;
	}

	public String getSourceName()
	{
		return sourceName;
	}

	@Override
	public boolean isGeneric()
	{
		return true;
	}
	
	private final String	dataSource;
	private final String	sourceName;
}
