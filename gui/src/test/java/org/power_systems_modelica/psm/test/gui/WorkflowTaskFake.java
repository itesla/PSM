package org.power_systems_modelica.psm.test.gui;

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

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public class WorkflowTaskFake extends WorkflowTask {

	public WorkflowTaskFake(String id) {
		super(id);
	}

	@Override
	public String getName() {
		return "Workflow Task Test";
	}

	@Override
	public void configure(Configuration config) {
	}

	@Override
	public void run() {
		progress("Start test workflow");
		running();
		progress("running1");
		progress("running2");
		progress("running3");
		progress("running4");
		progress("running5");
		succeded();
		progress("End test workflow");
	}
	
	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}
}
