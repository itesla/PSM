package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ModelicaModel
{
	public ModelicaModel(String name)
	{
		this.name = Objects.requireNonNull(name);
	}

	public String getName()
	{
		return name;
	}

	public void setStaticId(String staticId)
	{
		this.staticId = staticId;
	}

	public String getStaticId()
	{
		return this.staticId;
	}

	public void setConnectors(List<ModelicaConnector> connectors)
	{
		this.connectors = new ModelicaConnector[connectors.size()];
		this.connectors = connectors.toArray(this.connectors);
	}

	public void addModelInstantiations(List<ModelicaModelInstantiation> modelInstantiations)
	{
		if (modelInstantiations != null) this.modelInstantiations.addAll(modelInstantiations);
	}

	public List<ModelicaModelInstantiation> getModelInstantiations()
	{
		return Collections.unmodifiableList(modelInstantiations);
	}

	public void addEquations(List<ModelicaEquation> equations)
	{
		if (equations != null) this.equations.addAll(equations);
	}

	public void addEquation(ModelicaEquation equation)
	{
		if (equation != null) this.equations.add(equation);
	}

	public List<ModelicaEquation> getEquations()
	{
		return Collections.unmodifiableList(equations);
	}

	public ModelicaConnector[] getConnectors()
	{
		return connectors;
	}

	private String								name;
	private String								staticId;
	private List<ModelicaModelInstantiation>	modelInstantiations	= new ArrayList<>();
	private List<ModelicaEquation>				equations			= new ArrayList<>();
	private ModelicaConnector[]					connectors;
}
