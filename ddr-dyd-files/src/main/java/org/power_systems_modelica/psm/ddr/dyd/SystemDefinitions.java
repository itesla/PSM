package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.List;

import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;

public class SystemDefinitions implements DydContent
{
	public SystemDefinitions()
	{
		declarations = new ArrayList<>();
		equations = new ArrayList<>();
	}

	public void add(SystemDefinitions sd)
	{
		declarations.addAll(sd.getDeclarations());
		equations.addAll(sd.getEquations());
	}

	public List<ModelicaDeclaration> getDeclarations()
	{
		return declarations;
	}

	public List<Equation> getEquations()
	{
		return equations;
	}

	List<ModelicaDeclaration>	declarations;
	List<Equation>				equations;
}
