package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.power_systems_modelica.psm.case_validation.CaseValidation;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

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
			CaseValidation compare = new CaseValidation();
			compare.configure(config);
			compare.calculate();
			compare.validation();
			publish(SCOPE_GLOBAL,
					"casevalidation",
					compare.getResult());
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
