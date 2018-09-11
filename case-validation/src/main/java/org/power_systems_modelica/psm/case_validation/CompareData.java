package org.power_systems_modelica.psm.case_validation;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.case_validation.model.ComparisionData;
import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class CompareData
{
	public CompareData(double threshold, double relThreshold, double tol, ValidationResult r)
	{
		this.errorAbsTol = threshold;
		this.errorRelTol = relThreshold;
		this.result = r;
	}

	public void compareValues()
	{
		List<String> elements = new ArrayList<>();
		elements.addAll(result.getElements().keySet());
		
		elements.forEach(ke -> {
			
			Element e = result.getElements().get(ke);
			Map<Double, ComparisionData> values = e.getValues();
			values.keySet().forEach(k -> {
				
				ComparisionData v = values.get(k);
				
				double dif = v.getRelDif();
				e.addRelRmes(dif * dif);
				if (dif > errorRelTol)
					e.incRelOffset();
				
				dif = v.getAbsDif();
				e.addAbsRmes(dif * dif);
				if (dif > errorAbsTol)
					e.incAbsOffset();
			});

			double steps = e.getValues().size();
			double rmes = Math.sqrt(e.getRelRmes() / (double)steps);
			e.setRelRmes(rmes);

			rmes = Math.sqrt(e.getAbsRmes() / (double)steps);
			e.setAbsRmes(rmes);
		});
	}

	private double				errorAbsTol;
	private double				errorRelTol;
	private ValidationResult	result;
}
