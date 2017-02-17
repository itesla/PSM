package org.power_systems_modelica.psm.case_validation.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Element
{
	public String getRefName()
	{
		return refName;
	}

	public void setRefName(String refName)
	{
		this.refName = refName;
	}

	public String getModelicaName()
	{
		return modelicaName;
	}

	public void setModelicaName(String modelicaName)
	{
		this.modelicaName = modelicaName;
	}

	public void addModelicaValue(double time, double value)
	{
		ComparisionData data = new ComparisionData();

		data.setTime(time);
		data.setModelicaData(value);
		values.put(time, data);
	}

	public void addRefValue(double time, double value)
	{
		ComparisionData data = values.get(time);
		data.setRefData(value);
		
	}

	public Map<Double,ComparisionData> getValues()
	{
		return values;
	}

	public double getAbsRmes()
	{
		return absRmes;
	}

	public void setAbsRmes(double rmes)
	{
		this.absRmes = rmes;
	}

	public void addAbsRmes(double rmes)
	{
		this.absRmes += rmes;
	}

	public double getRelRmes()
	{
		return relRmes;
	}

	public void setRelRmes(double rmes)
	{
		this.relRmes = rmes;
	}

	public void addRelRmes(double rmes)
	{
		this.relRmes += rmes;
	}

	public int getAbsOffset()
	{
		return absOffset;
	}

	public void setAbsOffset(int absOffset)
	{
		this.absOffset = absOffset;
	}

	public void incAbsOffset()
	{
		this.absOffset++;
	}

	public int getRelOffset()
	{
		return relOffset;
	}

	public void setRelOffset(int relOffset)
	{
		this.relOffset = relOffset;
	}

	public void incRelOffset()
	{
		this.relOffset++;
	}

	public double getStepSize()
	{
		return stepSize;
	}

	public void setStepSize(double stepSize)
	{
		this.stepSize = stepSize;
	}

	private String							refName;
	private String							modelicaName;
	private Map<Double, ComparisionData>	values		= new HashMap<>();
	private double							absRmes		= 0.0;
	private double							relRmes		= 0.0;
	private int								absOffset	= 0;
	private int								relOffset	= 0;

	private double							stepSize;
}
