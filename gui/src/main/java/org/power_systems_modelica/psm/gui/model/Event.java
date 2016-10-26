package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;

public class Event implements Serializable  {

	public enum ActionEvent {
		OPEN(0), CLOSE(1), MODIFY(2);

		private int value;

		private ActionEvent(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public ActionEvent getAction() {
		return action;
	}
	
	public void setAction(ActionEvent action) {
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
		
		if (action == ActionEvent.OPEN)
			txt += "OPEN";
		else if (action == ActionEvent.CLOSE)
			txt += "CLOSE";
		else if (action == ActionEvent.MODIFY)
			txt += "MODIFY";
		txt += ", ";
		txt += "Element: " + element;
		
		return txt;
	}
	
	private ActionEvent action;
	private String element;
}
