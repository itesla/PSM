package org.power_systems_modelica.psm.workflow.test;

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

import java.util.ArrayList;
import java.util.List;

import org.power_systems_modelica.psm.workflow.TaskProgress;
import org.power_systems_modelica.psm.workflow.TaskStatePair;
import org.power_systems_modelica.psm.workflow.WorkflowListener;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
class TestWorkflowListener implements WorkflowListener
{
	@Override
	public void onStateChange(List<TaskStatePair> states)
	{
		recordedStates.add(states);
	}

	@Override
	public void onProgress(TaskProgress infos) 
	{
		recordedInfos.add(infos);
	}

	public List<List<TaskStatePair>> getRecordedStates()
	{
		return recordedStates;
	}

	public List<TaskProgress> getRecordedInfos()
	{
		return recordedInfos;
	}

	private List<TaskProgress> recordedInfos = new ArrayList<>();
	private List<List<TaskStatePair>> recordedStates = new ArrayList<>();

}