package org.power_systems_modelica.psm.modelica;

public class ModelicaSystemModel extends ModelicaModel
{
	public ModelicaSystemModel(String id)
	{
		super(id);
	}

	public ModelicaSystemModel copy()
	{
		ModelicaSystemModel m = new ModelicaSystemModel(getId());
		copy(this, m);
		return m;
	}
}
