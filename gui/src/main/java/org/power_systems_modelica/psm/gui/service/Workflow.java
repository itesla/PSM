package org.power_systems_modelica.psm.gui.service;

import org.power_systems_modelica.psm.gui.model.WorkflowParameters;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Workflow {

	public enum Status {
		IDLE(0), SCHEDULED(1), RUNNING(2), SUCCESS(3), FAILED(4);

		private int value;

		Status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static Status get(int value) {
			for (Status s : Status.values()) {
				if (s.value == value)
					return s;
			}
			throw new IllegalArgumentException("Status not found");
		}
	}

	public Workflow() {
		this.name = new SimpleStringProperty();
		this.status = new SimpleObjectProperty();
		this.progress = new SimpleDoubleProperty(0.0);
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

	public Status getStatus() {
		return (Status) status.get();
	}

	public ObjectProperty statusProperty() {
		return status;
	}

	public void setStatus(Status status) {
		this.status.set(status);
	}
	
	public WorkflowParameters getParameters() {
		return wp;
	}

	public void setParameters(WorkflowParameters wp) {
		this.wp = wp;
	}

	public double getProgress() {
		return progress.get();
	}

	public DoubleProperty progressProperty() {
		return progress;
	}

	public void setProgress (double progress) {
		this.progress.set(progress);
	}

	public boolean isRunning() {
		return status.getValue().equals(Status.RUNNING);
	}

	public boolean isSuccess() {
		return status.getValue().equals(Status.SUCCESS);
	}

	/*
	 * public List<WorkflowResult> getResult() { return result; }
	 * 
	 * public void setResult(List<WorkflowResult> result) { this.result =
	 * result; }
	 */

	private StringProperty name;
	private ObjectProperty status;
	private WorkflowParameters wp;
	private DoubleProperty progress;
	// private List<WorkflowResult> result;
}
