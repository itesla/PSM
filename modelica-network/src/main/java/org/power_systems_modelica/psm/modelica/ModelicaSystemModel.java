package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelicaSystemModel extends ModelicaModel
{
	public ModelicaSystemModel(String name)
	{
		super(name);
	}

	public void addParameters(List<ModelicaParameter> params)
	{
		parameters.addAll(params);
	}

	public List<ModelicaParameter> getParameters()
	{
		return Collections.unmodifiableList(parameters);
	}

	private List<ModelicaParameter> parameters = new ArrayList<>();
}
