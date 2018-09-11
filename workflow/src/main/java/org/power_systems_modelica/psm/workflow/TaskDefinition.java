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

import org.power_systems_modelica.psm.commons.Configuration;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public final class TaskDefinition
{
	public TaskDefinition(Class<? extends WorkflowTask> taskClass, String taskId)
	{
		this.taskClass = taskClass;
		this.taskId = taskId;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public Configuration getTaskConfiguration()
	{
		return config;
	}

	public void setTaskConfiguration(Configuration config)
	{
		this.config = config;
	}

	public Class<? extends WorkflowTask> getTaskClass()
	{
		return taskClass;
	}

	private final Class<? extends WorkflowTask>	taskClass;
	private final String						taskId;
	private Configuration						config;
}
