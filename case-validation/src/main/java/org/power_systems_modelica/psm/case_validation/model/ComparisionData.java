package org.power_systems_modelica.psm.case_validation.model;

public class ComparisionData
{
	public double getTime()
	{
		return time;
	}

	public void setTime(double time)
	{
		this.time = time;
	}

	public double getModelicaData()
	{
		return modelicaData;
	}

	public void setModelicaData(double modelicaData)
	{
		this.modelicaData = modelicaData;
	}

	public double getRefData()
	{
		return refData;
	}

	public void setRefData(double refData)
	{
		this.refData = refData;
	}

	public double getAbsDif()
	{
		return absDif;
	}

	public void setAbsDif(double absDif)
	{
		this.absDif = absDif;
	}

	public double getRelDif()
	{
		return relDif;
	}

	public void setRelDif(double relDif)
	{
		this.relDif = relDif;
	}

	private double	time;
	private double	modelicaData;
	private double	refData;
	private double	absDif;
	private double	relDif;
}
