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

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ComparisionData
{
	public ComparisionData()
	{
	}

	public ComparisionData(ComparisionData c)
	{
		this.time = c.getTime();
		this.modelicaData = c.getModelicaData();
		this.refData = c.getRefData();
		this.absDif = c.getAbsDif();
		this.relDif = c.getRelDif();
	}

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
