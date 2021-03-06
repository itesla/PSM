package org.power_systems_modelica.psm.case_validation.test;

/*
 * #%L
 * Case Validation
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;
import org.power_systems_modelica.psm.case_validation.CaseValidation;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.PathUtils;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ValidationTest
{
	@Test
	public void validateSmallCase1() throws IOException
	{
		double stepSize = 0.0001;
		validateCase(stepSize, "smallcase1", "case1_withEvents_res.csv");
	}
	
	@Test
	public void validateSmallCase2() throws IOException
	{
		double stepSize = 0.0001;
		validateCase(stepSize, "smallcase2", "case2_withEvents_res.csv");
	}
	
	@Test
	public void validateSmallCase3() throws IOException
	{
		double stepSize = 0.00001;
		validateCase(stepSize, "smallcase3", "case3_withEvents_res.csv");
	}
	
	@Test
	public void validateSmallCase4() throws IOException
	{
		double stepSize = 0.0001;
		validateCase(stepSize, "smallcase4", "case4_withEvents_res.csv");
	}
	
	@Test
	public void validate7buses() throws IOException
	{
		double stepSize = 0.0001;
		validateCase(stepSize, "7buses", "M7buses_withEvents_res.csv");
	}
	
	
	@Test
	public void validateIeee14() throws IOException
	{
		double stepSize = 0.0001;
		validateCase(stepSize, "ieee14", "ieee14bus_withEvents_res.csv");
	}
	
	
	@Test
	public void validateIeee30() throws IOException
	{
		double stepSize = 0.0001;
		validateCase(stepSize, "ieee30", "ieee30bus_withEvents_res.csv");
	}
	
	
	@Test
	public void validateIeee57() throws IOException
	{
		double stepSize = 0.001;
		validateCase(stepSize, "ieee57", "ieee57bus_withEvents_res.csv");
	}
	
	private void validateCase(
			double stepSize,
			String caseFolderName,
			String caseCsvName
			) 
	{
		System.out.println("---------------------TESTING CASE " + caseFolderName);
		Path validationCasePath = PathUtils.DATA_TEST.resolve(caseFolderName).resolve("validation");
		String namesMappingPath = validationCasePath.resolve("NamesMapping.csv").toString();
		String referenceDataPath = validationCasePath.resolve("expected").resolve("ReferenceData.csv").toString();
		String modelicaDataPath = validationCasePath.resolve("actual").resolve(caseCsvName).toString();
		
		Configuration tc = new Configuration();
		tc.setParameter("stepSize", "" + stepSize);
		tc.setParameter("pathNamesMapping", namesMappingPath);
		tc.setParameter("pathRefData", referenceDataPath);
		tc.setParameter("pathModelicaData", modelicaDataPath);
		
		CaseValidation compare = new CaseValidation(tc);
		try
		{
			compare.calculate();
			compare.validation();
			ValidationResult r = compare.getResult();

			assertNotNull(r);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
