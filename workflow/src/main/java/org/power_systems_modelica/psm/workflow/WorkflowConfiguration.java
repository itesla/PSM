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

import java.util.List;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class WorkflowConfiguration
{
	public void setTaskDefinitions(List<TaskDefinition> taskDefinitions)
	{
		this.taskDefinitions = taskDefinitions;
	}

	public List<TaskDefinition> getTaskDefinitions()
	{
		return taskDefinitions;
	}

	private List<TaskDefinition> taskDefinitions;
}
