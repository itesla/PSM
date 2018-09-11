package org.power_systems_modelica.psm.case_validation.model;

/*
 * #%L
 * Case Validation
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
	
	public void setVariables(List<VariableValidation> variables)
	{
		this.variables = variables;
	}

	public double getOriginalStepSize()
	{
		return originalStepSize;
	}

	public void setOriginalStepSize(double originalStepSize)
	{
		this.originalStepSize = originalStepSize;
	}

	public double getFinalStepSize()
	{
		return finalStepSize;
	}

	public void setFinalStepSize(double finalStepSize)
	{
		this.finalStepSize = finalStepSize;
	}



	private int							steps;
	private Map<String, Element>		elements = new HashMap<>();
	private List<VariableValidation>	variables = new ArrayList<>();
	private double						originalStepSize;
	private double						finalStepSize;
}
