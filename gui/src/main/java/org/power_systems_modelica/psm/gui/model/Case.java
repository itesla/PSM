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


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class Case implements Serializable {
	private static final long serialVersionUID = 1L;

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

	public Case() {
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
		this.format = new SimpleStringProperty();
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

	public String getFormat() {
		return format.get();
	}

	public StringProperty formatProperty() {
		return format;
	}

	public void setFormat(String format) {
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
	private StringProperty format;
	private IntegerProperty size;
}
