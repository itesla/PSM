package org.power_systems_modelica.psm.gui.model;

import java.util.Map;

public class BusData {

	public BusData(String id, String name, Map<String, float[]> data) {
		this.id = id;
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public Map<String, float[]> getData() {
		return data;
	}

	public float getData(String variable, int id) {
		return data.get(variable)[id];
	}

	public void setError(float err) {
		this.err = err;
	}

	public float getError() {
		return err;
	}

	private String id;
	private String name;
	private Map<String, float[]> data;
	private float err;

}
