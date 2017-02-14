package org.power_systems_modelica.psm.ddr.dyd;

public class ModelForElement extends Model
{
	public ModelForElement(String staticId, String id)
	{
		super(id);
		this.staticId = staticId;
	}

	public String getStaticId()
	{
		return staticId;
	}

	@Override
	public String toString()
	{
		return "Element " + staticId;
	}

	private final String staticId;
}
