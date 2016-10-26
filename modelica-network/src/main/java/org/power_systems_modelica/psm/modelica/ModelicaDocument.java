package org.power_systems_modelica.psm.modelica;

import java.util.Optional;

public class ModelicaDocument
{
	public void setWithin(String within)
	{
		this.within = Optional.ofNullable(within).orElse("");
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
