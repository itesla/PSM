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

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class EmptyTask extends WorkflowTask
{
	public EmptyTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Empty task";
	}

	@Override
	public void configure(Configuration config)
	{
	}

	@Override
	public void run()
	{
		succeded();
	}
	
	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}
}