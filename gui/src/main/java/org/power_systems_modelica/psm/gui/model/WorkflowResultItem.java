package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;
import java.util.HashMap;

public class WorkflowResultItem implements Serializable {

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	private int x;
	private double y;
}
