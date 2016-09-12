package org.power_systems_modelica.psm.modelica;

public class ModelicaArgumentReference extends ModelicaArgument
{
	public ModelicaArgumentReference(String name, String dataSource, String sourceName)
	{
		super(name, null);
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
	public String getValue()
	{
		throw new UnsupportedOperationException("it is not valid to call getValue for an argument reference");
	}

	private final String	dataSource;
	private final String	sourceName;
}
