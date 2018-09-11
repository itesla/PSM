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

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class FailTask extends EmptyTask
{
	public FailTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Fail task";
	}

	@Override
	public void run()
	{
		failed();
	}
}