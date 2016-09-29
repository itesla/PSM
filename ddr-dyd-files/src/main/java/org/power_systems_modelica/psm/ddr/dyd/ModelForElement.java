package org.power_systems_modelica.psm.ddr.dyd;

public class ModelForElement extends Model
{
	public ModelForElement(String id, String staticId)
	{
		this.id = id;
		this.staticId = staticId;
	}

	public String getId()
	{
		return id;
	}

	public String getStaticId()
	{
		return staticId;
	}

	private final String	id;
	private final String	staticId;
}
