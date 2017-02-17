package org.power_systems_modelica.psm.case_validation.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;
import org.power_systems_modelica.psm.case_validation.CaseValidation;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.commons.Configuration;

public class ValidationTest
{

	public static final Path DATA_TEST = Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");

	@Test
	public void caseValidation() throws IOException
	{
		double stepSize = 0.0001;
		Path validationPath = DATA_TEST.resolve("smallcase1").resolve("validation");
		String pathNamesMapping = validationPath.resolve("NamesMapping.csv").toString();
		String pathRefData = validationPath.resolve("expected").resolve("EurostagData.csv").toString();
		String pathModelicaData = validationPath.resolve("actual").resolve("case1_res.csv").toString();
		
		Configuration tc = new Configuration();
		tc.setParameter("stepSize", "" + stepSize);
		tc.setParameter("pathNamesMapping", pathNamesMapping);
		tc.setParameter("pathRefData", pathRefData);
		tc.setParameter("pathModelicaData", pathModelicaData);
		
		CaseValidation compare = new CaseValidation();
		compare.configure(tc);
		compare.calculate();
		compare.validation();
		ValidationResult r = compare.getResult();

		assertNotNull(r);
	}
}
