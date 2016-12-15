package org.power_systems_modelica.psm.gui.model;

public class DsData
{
	public DsData(Double x, Double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	private double	x;
	private double	y;
}
