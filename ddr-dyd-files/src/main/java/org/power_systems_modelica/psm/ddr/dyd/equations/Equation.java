package org.power_systems_modelica.psm.ddr.dyd.equations;

import org.power_systems_modelica.psm.ddr.Stage;

public abstract class Equation
{
	public abstract String writeIn(Context<?> context);

	public Equation()
	{
		this.stage = Stage.SIMULATION;
	}

	public Equation(Stage stage)
	{
		this.stage = stage;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public Stage getStage()
	{
		return stage;
	}

	private Stage stage;
}
