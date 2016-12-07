package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.equations.UnparsedEquation;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;

public class SystemDefinitions implements DydContent
{
	public SystemDefinitions()
	{
		declarations = new ArrayList<>();
		equations = new ArrayList<>();
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void add(SystemDefinitions sd)
	{
		declarations.addAll(sd.getDeclarations());
		equations.addAll(sd.getEquations());
	}

	public void add(Declaration d)
	{
		declarations.add(d);
	}

	public void add(Equation eq)
	{
		equations.add(eq);
	}

	public void add(ModelicaDeclaration d, Stage stage)
	{
		declarations.add(new Declaration(d, stage));
	}

	public void addDeclarations(List<ModelicaDeclaration> declarations, Stage stage)
	{
		this.declarations.addAll(declarations.stream()
				.map(md -> new Declaration(md, stage))
				.collect(Collectors.toList()));
	}

	public void add(ModelicaEquation meq, Stage stage)
	{
		Equation eq = new UnparsedEquation(meq.getText());
		eq.setStage(stage);
		equations.add(eq);
	}

	public void addEquations(List<ModelicaEquation> equations, Stage stage)
	{
		this.equations.addAll(equations.stream()
				.map(meq -> {
					Equation eq = new UnparsedEquation(meq.getText());
					eq.setStage(stage);
					return eq;
				})
				.collect(Collectors.toList()));
	}

	public List<Declaration> getDeclarations()
	{
		return declarations;
	}

	public List<Equation> getEquations()
	{
		return equations;
	}

	public boolean isEmpty()
	{
		return declarations.isEmpty() && equations.isEmpty();
	}

	String				name;
	List<Declaration>	declarations;
	List<Equation>		equations;
}
