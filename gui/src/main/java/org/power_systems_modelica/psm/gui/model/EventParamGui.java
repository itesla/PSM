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

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class EventParamGui implements Serializable
{
	private static final long serialVersionUID = 1L;

	public EventParamGui()
	{
		this.name = new SimpleStringProperty();
		this.value = new SimpleStringProperty();
	}

	public String getName()
	{
		return name.get();
	}

	public String getNameWithoutUnit()
	{
		return name.get().replace(" (" + unit + ")", "");
	}

	public StringProperty nameProperty()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public String getValue()
	{
		return value.get();
	}

	public StringProperty valueProperty()
	{
		return value;
	}

	public void setValue(String description)
	{
		this.value.set(description);
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public void fromString(String param)
	{

		String[] paramVar = param.split("=");

		name.set(paramVar[0]);
		value.set(paramVar[1]);
	}

	@Override
	public String toString()
	{

		String t = getNameWithoutUnit() +
				"=" +
				value.get();

		return t;
	}

	private StringProperty	name;
	private String			unit;
	private StringProperty	value;
}
