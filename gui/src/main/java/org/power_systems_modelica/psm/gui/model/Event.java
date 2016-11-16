package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Event implements Serializable  {

	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getElement() {
		return element;
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	
	public List<EventParamGui> getParams() {
		return params;
	}

	public void setParams(List<EventParamGui> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		
		String t = action +
				"," +
				element +
				"," +
				params.stream().map(Object::toString).collect(Collectors.joining(","));
		
		return t;
	}
	
	private String action;
	private String element;
	private List<EventParamGui> params;
}
