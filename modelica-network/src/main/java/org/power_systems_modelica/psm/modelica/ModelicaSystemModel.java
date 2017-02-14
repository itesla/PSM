package org.power_systems_modelica.psm.modelica;

public class ModelicaSystemModel extends ModelicaModel
{
	public ModelicaSystemModel(String id)
	{
		super(id);
	}

	public ModelicaSystemModel copy(String id)
	{
		ModelicaSystemModel m = new ModelicaSystemModel(id);
		copy(this, m);
		return m;
	}
}
