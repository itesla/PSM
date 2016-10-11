package org.power_systems_modelica.psm.dao;

import java.io.Serializable;

public class Ddr implements Serializable {
	
	public static int TYPE_DYD = 0;
	public static int TYPE_DDB = 1;
	
	private String name;
	private String description;
	private int type;
	private String source;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
