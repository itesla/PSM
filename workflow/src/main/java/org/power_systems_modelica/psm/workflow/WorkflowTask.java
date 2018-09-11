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

import static org.power_systems_modelica.psm.workflow.ProcessState.FAILED;
import static org.power_systems_modelica.psm.workflow.ProcessState.IDLE;
import static org.power_systems_modelica.psm.workflow.ProcessState.RUNNING;
import static org.power_systems_modelica.psm.workflow.ProcessState.SUCCESS;

import org.power_systems_modelica.psm.commons.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
An individual task can not be interrupted, 
Status is IDLE until run is called
is RUNNING while run is being executed
and is either SUCCESS or FAILED after run has finished
*/

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public abstract class WorkflowTask
{
	public WorkflowTask(String id)
	{
		this.id = id;
	}

	public abstract String getName();

	public abstract void configure(Configuration config);
	public abstract void cancel();

	public String getId()
	{
		return id;
	}

	public void setWorkflow(Workflow workflow)
	{
		this.workflow = workflow;
	}

	public ProcessState getState()
	{
		return state;
	}

	public void run()
	{
		running();
	}

	protected void running()
	{
		state = RUNNING;
		workflow.updateState(this.id, state);
	}

	protected void succeded()
	{
		state = SUCCESS;
		workflow.updateState(this.id, state);
	}

	protected void failed()
	{
		state = FAILED;
		workflow.updateState(this.id, state);
	}

	protected void failed(Exception x)
	{
		LOG.error("Workflow task {} failed", this.id, x);
		state = FAILED;
		workflow.updateState(this.id, state, x);
	}
	
	protected void progress(String info)
	{
		LOG.debug("Workflow task {} info", this.id, info);
		workflow.updateProgress(this.id, info);
	}

	protected void publish(Workflow.ResultsScope scope, String resultsId, Object results)
	{
		workflow.publish(this, scope, resultsId, results);
	}

	private final String		id;
	protected Workflow			workflow;
	private ProcessState		state	= IDLE;

	private static final Logger	LOG		= LoggerFactory.getLogger(WorkflowTask.class);
}
