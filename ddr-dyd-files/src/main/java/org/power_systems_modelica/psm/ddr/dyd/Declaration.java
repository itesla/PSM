package org.power_systems_modelica.psm.ddr.dyd;

import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;

public class Declaration
{
	public Declaration(
			ModelicaDeclaration d,
			Stage stage)
	{
		this.stage = stage;
		this.modelicaDeclaration = d;
	}

	public Stage getStage()
	{
		return stage;
	}

	public ModelicaDeclaration getModelicaDeclaration()
	{
		return modelicaDeclaration;
	}

	private final Stage					stage;
	private final ModelicaDeclaration	modelicaDeclaration;
}