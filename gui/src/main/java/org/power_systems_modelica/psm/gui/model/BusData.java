package org.power_systems_modelica.psm.gui.model;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BusData
{
	public BusData(String id, String name, Map<String, float[]> data)
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

	public Map<String, float[]> getData()
	{
		return data;
	}

	public FloatProperty dataProperty(String variable, int id) {
		
		return new SimpleFloatProperty(data.get(variable)[id]);
	}

	public float getData(String variable, int id)
	{
		return data.get(variable)[id];
	}

	public void setError(String variable, float err)
	{
		float[] input = new float[1];
		input[0] = err;

		this.err.put(variable, input);
	}

	public float getError(String variable)
	{
		return err.get(variable)[0];
	}

	public FloatProperty errorProperty(String variable)
	{
		return new SimpleFloatProperty(err.get(variable)[0]);
	}

	public float getAbsError(String variable)
	{
		return Math.abs(err.get(variable)[0]);
	}

	private String					id;
	private String					name;
	private Map<String, float[]>	data;
	private Map<String, float[]>	err;
}
