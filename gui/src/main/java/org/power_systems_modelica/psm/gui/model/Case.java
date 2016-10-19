package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Case implements Serializable {

	public static int FORMAT_CIM1 = 0;
	
	public Case() {
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
		this.format = new SimpleIntegerProperty();
		this.size = new SimpleIntegerProperty();
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

	public String getDescription() {
		return description.get();
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public void setDescription(String description) {
		this.description.set(description);
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

	public int getFormat() {
		return format.get();
	}

	public IntegerProperty formatProperty() {
		return format;
	}

	public void setFormat(int format) {
		this.format.set(format);
	}

	public int getSize() {
		return size.get();
	}

	public IntegerProperty sizeProperty() {
		return size;
	}

	public void setSize(int size) {
		this.size.set(size);
	}

	public String toString() {
		return name.get();
	}

	private StringProperty name;
	private StringProperty description;
	private StringProperty location;
	private IntegerProperty format;
	private IntegerProperty size;
}
