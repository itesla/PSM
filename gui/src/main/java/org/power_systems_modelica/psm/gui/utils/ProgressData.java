package org.power_systems_modelica.psm.gui.utils;

import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.TaskStatePair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProgressData implements HierarchyData<ProgressData> {
	
	public ProgressData(String taskId, String taskName, ProcessState taskState) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskState = taskState;
	}
	
	public ProgressData(String info) {
		this.info = info;
	}

	public void updateState(ProcessState taskState) {
		this.taskState = taskState;
	}
	
	public String getTaskId() {
		return taskId;
	}

	@Override
	public ObservableList<ProgressData> getChildren() {
		return children;
	}
	
	@Override
	public Boolean isExpanded() {
		return taskState==null?false:taskState.equals(ProcessState.RUNNING);
	}

	@Override
	public String toString() {
		
		if (info != null)
			return info;
		
		return Utils.padString(taskId, 20) + Utils.padString(taskName, 60) + taskState.toString();
	}
	
	@Override
	public int hashCode()
	{
		if (this.info != null)
			return this.info.hashCode();

		return this.taskId.hashCode() + this.taskName.hashCode() + 57 * this.taskState.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof ProgressData)) return false;
		if (obj == this) return true;

		ProgressData other = (ProgressData) obj;
		if (this.info != null) {
			if (other.info == null)
				return false;
			
			return this.info.equals(other.info);
		}
			
		return this.taskId.equals(other.taskId) && this.taskName.equals(other.taskName) && this.taskState.equals(other.taskState);
	}

	private String taskId;
	private String taskName;
	private ProcessState taskState;
	
	private String info = null;
	
	private ObservableList<ProgressData> children = FXCollections.observableArrayList();
}