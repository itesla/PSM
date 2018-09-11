package org.power_systems_modelica.psm.gui.service.fx;

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

import org.power_systems_modelica.psm.gui.utils.InitCompletionHandler;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.concurrent.Task;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class TaskService
{
	public static Task<?> createTask(Workflow w, InitCompletionHandler initCompletionHandler)
	{
		WorkflowService task = new WorkflowService(w);
		task.stateProperty().addListener((observableValue, oldState, newState) -> {
			if (w.getState().equals(ProcessState.SUCCESS) || w.getState().equals(ProcessState.FAILED)) {
				initCompletionHandler.complete();
			}
		});
		return task;
	}

	public static void startTask(Task<?> task)
	{
		Thread t = new Thread(task);
		t.start();
	}
}
