package org.power_systems_modelica.psm.case_validation.model;

/*
 * #%L
 * Case Validation
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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

	public void setValues(Map<Double,ComparisionData> values)
	{
		this.values = values;
	}

	public Map<Double,ComparisionData> getValues()
	{
		return values;
	}

	public void resetValues()
	{
		values.clear();
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
