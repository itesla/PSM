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

	public void addDeclarations(List<ModelicaDeclaration> declarations)
	{
		Objects.requireNonNull(declarations);
		this.declarations.addAll(declarations);
	}

	public void addDeclaration(ModelicaDeclaration declaration)
	{
		Objects.requireNonNull(declaration);
		this.declarations.add(declaration);
	}

	public List<ModelicaDeclaration> getDeclarations()
	{
		return Collections.unmodifiableList(declarations);
	}

	public void addEquations(List<ModelicaEquation> equations)
	{
		Objects.requireNonNull(equations);
		this.equations.addAll(equations);
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

	public boolean isReplacement()
	{
		return isReplacement;
	}

	public void setReplacement(boolean isReplacement)
	{
		this.isReplacement = isReplacement;
	}

	private String						name;
	private String						staticId;
	private List<ModelicaDeclaration>	declarations	= new ArrayList<>();
	private List<ModelicaEquation>		equations		= new ArrayList<>();
	private ModelicaConnector[]			connectors;

	private boolean						isReplacement	= false;
}
