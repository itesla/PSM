package org.power_systems_modelica.psm.modelica;

public class ModelicaSystemModel extends ModelicaModel
{
	public ModelicaSystemModel(String name)
	{
		super(name);
	}

	public ModelicaSystemModel copy()
	{
		ModelicaSystemModel m = new ModelicaSystemModel(getName());
		copy(this, m);
		return m;
	}
}
