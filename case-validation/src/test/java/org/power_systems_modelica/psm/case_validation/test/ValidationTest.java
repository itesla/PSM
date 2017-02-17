package org.power_systems_modelica.psm.case_validation.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.power_systems_modelica.psm.case_validation.CaseValidation;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;

public class ValidationTest
{

	private static double			stepSize			= 0.0001;
	private static final String		pathNamesMapping	= "C:\\Users\\demiguelm\\psm\\data\\test\\smallcase1\\validation\\NamesMapping.csv";
	private static final String		pathRefData			= "C:\\Users\\demiguelm\\psm\\data\\test\\smallcase1\\validation\\expected\\EurostagData.csv";
	private static final String		pathModelicaData	= "C:\\Users\\demiguelm\\psm\\data\\test\\smallcase1\\validation\\actual\\case1_res.csv";
	private static final String[]	valuesTest			= { "V", "angle", "P", "Q", "efd", "cm",
			"lambdad", "lambdaq1", "lambdaf", "lambdaq2" };

	@Test
	public void caseValidation() throws IOException
	{
		CaseValidation compare = new CaseValidation();
		compare.read(stepSize, pathRefData, pathModelicaData, pathNamesMapping);
		compare.validation(0.001, 0.011, Arrays.asList(valuesTest));
		ValidationResult r = compare.getResult();

		assertNotNull(r);
	}

	@Test
	public void loadingNamesMapping() throws IOException
	{
		CaseValidation compare = new CaseValidation();
		compare.getCaseValidationLoader().loadNamesMapping(stepSize, pathNamesMapping);
	}
	
	@Test
	public void loadingModelicaData() throws IOException
	{
		CaseValidation compare = new CaseValidation();
		compare.getCaseValidationLoader().loadNamesMapping(stepSize, pathNamesMapping);
		compare.getCaseValidationLoader().loadModelicaData(stepSize, pathModelicaData);
	}
	
	@Test
	public void loadingRefData() throws IOException
	{
		CaseValidation compare = new CaseValidation();
		compare.getCaseValidationLoader().loadNamesMapping(stepSize, pathNamesMapping);
		compare.getCaseValidationLoader().loadModelicaData(stepSize, pathModelicaData);
		compare.getCaseValidationLoader().loadRefData(stepSize, pathRefData);
	}
}
