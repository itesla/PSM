package org.power_systems_modelica.psm.ddr.dyd;

public class ModelForAssociation extends Model
{
	public ModelForAssociation(String association, String baseId)
	{
		super(baseId);
		this.association = association;
	}

	public String getAssociation()
	{
		return association;
	}

	private final String association;
}
