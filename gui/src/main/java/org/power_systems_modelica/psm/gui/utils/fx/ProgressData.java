package org.power_systems_modelica.psm.gui.utils.fx;

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

import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.ProcessState;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
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
	public Node getGraphic() {
		
		if (taskState == null)
			return null;

		if (taskState.equals(ProcessState.RUNNING))
			return new ImageView(runningImage);

		if (taskState.equals(ProcessState.FAILED))
			return new ImageView(failedImage);

		if (taskState.equals(ProcessState.SUCCESS))
			return new ImageView(successImage);

		if (taskState.equals(ProcessState.IDLE))
			return new ImageView(idleImage);

		if (taskState.equals(ProcessState.SCHEDULED))
			return new ImageView(scheduledImage);

		return null;
	}

	@Override
	public String toString() {
		
		if (info != null)
			return "\t" + info;
		
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

	private Image runningImage = new Image(getClass().getResourceAsStream("/img/running.gif"));
	private Image failedImage = new Image(getClass().getResourceAsStream("/img/failed.gif"));
	private Image successImage = new Image(getClass().getResourceAsStream("/img/success.gif"));
	private Image idleImage = new Image(getClass().getResourceAsStream("/img/idle.gif"));
	private Image scheduledImage = new Image(getClass().getResourceAsStream("/img/scheduled.gif"));

}