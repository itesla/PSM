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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class Ddr implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public enum DdrType
	{
		DYD(0), DDB(1);

		private int value;

		private DdrType(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	public Ddr()
	{
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
		this.type = new SimpleObjectProperty<DdrType>(DdrType.DYD);
	}

	public String getName()
	{
		return name.get();
	}

	public StringProperty nameProperty()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public String getLocation()
	{
		return location.get();
	}

	public StringProperty locationProperty()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location.set(location);
	}

	public String getDescription()
	{
		return description.get();
	}

	public StringProperty descriptionProperty()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description.set(description);
	}

	public DdrType getType()
	{
		return (DdrType) type.get();
	}

	public ObjectProperty<DdrType> typeProperty()
	{
		return type;
	}

	public void setType(DdrType type)
	{
		this.type.set(type);
	}

	public String toString()
	{
		return name.get();
	}

	private StringProperty			name;
	private StringProperty			description;
	private StringProperty			location;
	private ObjectProperty<DdrType>	type;
}
