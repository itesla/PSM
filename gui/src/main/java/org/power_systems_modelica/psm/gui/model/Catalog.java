package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Catalog implements Serializable
{

	public Catalog()
	{
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
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

	public String toString()
	{
		return name.get();
	}

	private StringProperty	name;
	private StringProperty	description;
	private StringProperty	location;
}
