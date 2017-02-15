package org.power_systems_modelica.psm.ddr.dyd;

import org.power_systems_modelica.psm.ddr.StaticType;

public class ModelForType extends Model
{
	public ModelForType(StaticType type, String baseId)
	{
		super(baseId);
		this.type = type;
	}

	public StaticType getType()
	{
		return type;
	}

	@Override
	public String toString()
	{
		return "Type " + type;
	}

	private final StaticType type;
}
