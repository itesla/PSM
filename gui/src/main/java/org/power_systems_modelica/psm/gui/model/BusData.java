package org.power_systems_modelica.psm.gui.model;

/*
 * #%L
 * Power Systems on Modelica GUI
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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class BusData
{
	public BusData(String id, String name, Map<String, double[]> data)
	{
		this.id = id;
		this.name = name;
		this.data = data;
		this.err = new HashMap<>();
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public StringProperty nameProperty()
	{
		return new SimpleStringProperty(name);
	}

	public Map<String, double[]> getData()
	{
		return data;
	}

	public DoubleProperty dataProperty(String variable, int id) {
		
		return new SimpleDoubleProperty(data.get(variable)[id]);
	}

	public Double getData(String variable, int id)
	{
		if (Double.isNaN(data.get(variable)[id])) return null;
		return data.get(variable)[id];
	}

	public void setError(String variable, double err)
	{
		double[] input = new double[1];
		input[0] = err;

		this.err.put(variable, input);
	}

	public double getError(String variable)
	{
		return err.get(variable)[0];
	}

	public DoubleProperty errorProperty(String variable)
	{
		return new SimpleDoubleProperty(err.get(variable)[0]);
	}

	public double getAbsError(String variable)
	{
		return Math.abs(err.get(variable)[0]);
	}

	private String					id;
	private String					name;
	private Map<String, double[]>	data;
	private Map<String, double[]>	err;
}
