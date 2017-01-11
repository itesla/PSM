package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConvertedCase implements Serializable {

	public enum Format {
		CIM1(0), IIDM(1);

		private int value;

		private Format(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public ConvertedCase() {
		this.name = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
		this.ddrLocation = new SimpleStringProperty();
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

	public String getLocation() {
		return location.get();
	}

	public StringProperty locationProperty() {
		return location;
	}

	public void setLocation(String location) {
		this.location.set(location);
	}

	public String getDdrLocation() {
		return ddrLocation.get();
	}

	public StringProperty ddrLocationProperty() {
		return ddrLocation;
	}

	public void setDdrLocation(String location) {
		this.ddrLocation.set(location);
	}

	public String toString() {
		return name.get();
	}

	private StringProperty name;
	private StringProperty location;
	private StringProperty ddrLocation;
}