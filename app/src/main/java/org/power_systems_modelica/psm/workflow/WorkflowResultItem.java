package org.power_systems_modelica.psm.workflow;

import java.io.Serializable;
import java.util.HashMap;

public class WorkflowResultItem implements Serializable {

	private String x;
	private double y;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
