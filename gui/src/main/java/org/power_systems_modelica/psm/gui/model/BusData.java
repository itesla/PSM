package org.power_systems_modelica.psm.gui.model;

import java.util.HashMap;
import java.util.Map;

public class BusData {

	public BusData(String id, String name, Map<String, float[]> data) {
		this.id = id;
		this.name = name;
		this.data = data;
		this.err = new HashMap<>();
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

	public void setError(String variable, float err) {
		
		float[] input = new float[1];
		input[0] = err;
		
		if (variable == null) System.out.println("variable null");
		if (input == null) System.out.println("input null");
		if (this.err == null) System.out.println("this.err null");
		this.err.put(variable, input);
	}

	public float getError(String variable) {
		return err.get(variable)[0];
	}

	public float getAbsError(String variable) {
		return Math.abs(err.get(variable)[0]);
	}

	private String id;
	private String name;
	private Map<String, float[]> data;
	private Map<String, float[]> err;

}
