package org.power_systems_modelica.psm.case_validation.model;

public class VariableValidation
{

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSteps()
	{
		return steps;
	}

	public void setSteps(int steps)
	{
		this.steps = steps;
	}

	public void addSteps(int steps)
	{
		this.steps += steps;
	}

	public double getAbsOffset()
	{
		return absOffset;
	}

	public void setAbsOffset(double offset)
	{
		this.absOffset = offset;
	}

	public void addAbsOffset(double offset)
	{
		this.absOffset += offset;
	}

	public double getRelOffset()
	{
		return relOffset;
	}

	public void setRelOffset(double offset)
	{
		this.relOffset = offset;
	}

	public void addRelOffset(double offset)
	{
		this.relOffset += offset;
	}

	public double getAbsTotalOffset()
	{
		return absTotalOffset;
	}

	public void setAbsTotalOffset(double absOffset)
	{
		this.absTotalOffset = absOffset;
	}

	public void incAbsTotalOffset(double absOffset)
	{
		this.absTotalOffset += absOffset;
	}

	public double getRelTotalOffset()
	{
		return relTotalOffset;
	}

	public void setRelTotalOffset(double relOffset)
	{
		this.relTotalOffset = relOffset;
	}

	public void incRelTotalOffset(double relOffset)
	{
		this.relTotalOffset += relOffset;
	}

	public int getRmesKo()
	{
		return rmesKo;
	}

	public void setRmesKo(int rmesKo)
	{
		this.rmesKo = rmesKo;
	}

	public void incRmesKo()
	{
		this.rmesKo++;
	}

	public int getRmesElements()
	{
		return rmesElements;
	}

	public void incRmesElements()
	{
		this.rmesElements++;
	}

	public double getRmesAbove()
	{
		return rmesAbove;
	}

	public void setRmesAbove(double rmesAbove)
	{
		this.rmesAbove = rmesAbove;
	}

	private String	name;
	private int		steps			= 0;
	private double	absOffset		= 0;
	private double	relOffset		= 0;
	private double	absTotalOffset	= 0;
	private double	relTotalOffset	= 0;

	private int		rmesKo			= 0;
	private int		rmesElements	= 0;
	private double	rmesAbove		= 0;
}
