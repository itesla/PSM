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
		this.stepSize = Double
				.valueOf(Optional.ofNullable(config.getParameter("stepSize")).orElse("0.0001"));
		pathNamesMapping = config.getParameter("pathNamesMapping");
		pathRefData = config.getParameter("pathRefData");
		pathModelicaData = config.getParameter("pathModelicaData");
	}

	@Override
	public void run()
	{
		running();
		try
		{
			CaseValidation compare = new CaseValidation();
			compare.read(stepSize, pathRefData, pathModelicaData, pathNamesMapping);
			compare.validation(0.001, 0.011, Arrays.asList(valuesTest));
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

	private double		stepSize		= 0.0001;
	private double		threshold		= 0.001;
	private double		relThreshold	= 0.011;
	private String		pathNamesMapping;
	private String		pathRefData;
	private String		pathModelicaData;
	private String[]	valuesTest		= { "V", "angle", "P", "Q", "efd", "cm", "lambdad",
			"lambdaq1",
			"lambdaf", "lambdaq2" };
}
