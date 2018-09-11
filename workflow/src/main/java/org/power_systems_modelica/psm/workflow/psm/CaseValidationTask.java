package org.power_systems_modelica.psm.workflow.psm;

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

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import org.power_systems_modelica.psm.case_validation.CaseValidation;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class CaseValidationTask extends WorkflowTask
{

	public CaseValidationTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Sw-to-Sw validation";
	}

	@Override
	public void configure(Configuration config)
	{
		this.config = config;
	}

	@Override
	public void run()
	{
		running();
		try
		{
			CaseValidation compare = new CaseValidation(config);
			compare.calculate();
			compare.validation();
			publish(SCOPE_GLOBAL,
					"casevalidation",
					compare.getResult());
			compare.clear();
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}
	
	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}

	private Configuration					config;
}
