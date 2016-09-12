package org.power_systems_modelica.psm.modelica;

public class ModelicaDocument
{
	public void setWithin(String within)
	{
		this.within = within;
	}

	public String getWithin()
	{
		return within;
	}

	public void setSystemModel(ModelicaSystemModel m)
	{
		model = m;
	}

	public ModelicaSystemModel getSystemModel()
	{
		return model;
	}

	private String				within;
	private ModelicaSystemModel	model;
}
