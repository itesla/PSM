package org.power_systems_modelica.psm.ddr.dyd;

public class ModelForType extends Model
{
	public ModelForType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	private final String type;
}
