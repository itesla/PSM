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
		systemModel = m;
	}

	public ModelicaSystemModel getSystemModel()
	{
		return systemModel;
	}

	public ModelicaDocument copy()
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin(within);
		mo.setSystemModel(systemModel.copy());
		return mo;
	}

	private String				within;
	private ModelicaSystemModel	systemModel;
}
