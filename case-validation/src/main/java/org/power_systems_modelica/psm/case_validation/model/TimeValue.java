package org.power_systems_modelica.psm.case_validation.model;

public class TimeValue
{

	public TimeValue(Double time, Double value)
	{
		this.time = time;
		this.value = value;
	}

	public double getTime()
	{
		return time;
	}

	public void setTime(double time)
	{
		this.time = time;
	}

	public double getValue()
	{
		return value;
	}

	public void setValue(double value)
	{
		this.value = value;
	}

	private double	time;
	private double	value;
}
