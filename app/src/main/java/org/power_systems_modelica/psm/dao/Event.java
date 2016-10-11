package org.power_systems_modelica.psm.dao;

import java.io.Serializable;

public class Event implements Serializable  {

	public static int OPEN = 0; 
	public static int CLOSE = 1; 
	public static int MODIFY = 2;
	
	private int action;
	private String element;

	public int getAction() {
		return action;
	}
	
	public void setAction(int action) {
		this.action = action;
	}
	
	public String getElement() {
		return element;
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	
	@Override
	public String toString() {
		String txt = "Action: ";
		
		if (action == Event.OPEN)
			txt += "OPEN";
		else if (action == Event.CLOSE)
			txt += "CLOSE";
		else if (action == Event.MODIFY)
			txt += "MODIFY";
		txt += ", ";
		txt += "Element: " + element;
		
		return txt;
	}
	
}
