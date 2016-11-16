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

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	private double	x;
	private double	y;
}
