package org.power_systems_modelica.psm.case_validation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ValidationResult
{

	public int getSteps()
	{
		return steps;
	}

	public void setSteps(int steps)
	{
		this.steps = steps;
	}

	public void addElement(String name, Element e)
	{
		elements.put(name, e);
	}

	public Map<String, Element> getElements()
	{
		return elements;
	}

	public void addVariable(VariableValidation e)
	{
		variables.add(e);
	}
	
	public VariableValidation getVariable(String name)
	{
		Optional <VariableValidation> var = variables.stream().filter(v -> v.getName().equals(name)).findFirst();
		if (var.isPresent())
			return var.get();
		
		return null;
	}

	public List<VariableValidation> getVariables()
	{
		return variables;
	}

	private int							steps;
	private Map<String, Element>		elements = new HashMap<>();
	private List<VariableValidation>	variables = new ArrayList<>();
}
