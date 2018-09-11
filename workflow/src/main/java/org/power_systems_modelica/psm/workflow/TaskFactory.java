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

import java.lang.reflect.InvocationTargetException;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class TaskFactory
{
	public WorkflowTask create(TaskDefinition td) throws WorkflowCreationException
	{
		try
		{
			String taskId = td.getTaskId();
			WorkflowTask t = td
					.getTaskClass()
					.getDeclaredConstructor(String.class)
					.newInstance(taskId);
			t.configure(td.getTaskConfiguration());
			return t;
		}
		catch (InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException
				| NoSuchMethodException
				| SecurityException e)
		{
			throw new WorkflowCreationException(e);
		}
	}
}