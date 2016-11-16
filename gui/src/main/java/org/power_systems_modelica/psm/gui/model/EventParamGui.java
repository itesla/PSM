package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventParamGui implements Serializable  {

	public EventParamGui() {
		this.name = new SimpleStringProperty();
		this.value = new SimpleStringProperty();
	}

	public String getName() {
		return name.get();
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getValue() {
		return value.get();
	}

	public StringProperty valueProperty() {
		return value;
	}

	public void setValue(String description) {
		this.value.set(description);
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		
		String t = name.get().replace(" (" + unit + ")", "") +
				"=" +
				value.get();
		
		return t;
	}
	
	private StringProperty name;
	private String unit;
	private StringProperty value;
}
