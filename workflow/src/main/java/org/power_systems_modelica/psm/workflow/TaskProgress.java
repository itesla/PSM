package org.power_systems_modelica.psm.workflow;

/*
 * #%L
 * Power systems on Modelica workflow
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */
/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class TaskProgress {

	public final String			taskId;
	public final String			info;

	public TaskProgress(String taskId, String info) {

		this.taskId = taskId;
		this.info = info;
	}
	
	@Override
	public int hashCode()
	{
		return this.taskId.hashCode() + 37 * this.info.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof TaskProgress)) return false;
		if (obj == this) return true;

		TaskProgress other = (TaskProgress) obj;
		return this.taskId.equals(other.taskId) && this.info.equals(other.info);
	}

	@Override
	public String toString()
	{
		return taskId + ":" + info;
	}
}
