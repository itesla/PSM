package org.power_systems_modelica.psm.case_validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.case_validation.model.ComparisionData;
import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;

public class CompareData
{
	public CompareData(double thresold, double relThreshold, ValidationResult r)
	{
		this.errorAbsTol = thresold;
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
				if ((Math.abs(v.getRefData()) < errorAbsTol * 1.5)
						&& !e.getRefName().equalsIgnoreCase("time"))
				{
					dif = v.getAbsDif();
				}
				e.addRelRmes(dif * dif);
				if (dif > errorRelTol)
					e.incRelOffset();
				
				dif = v.getAbsDif();
				e.addAbsRmes(dif * dif);
				if (dif > errorAbsTol)
					e.incAbsOffset();
			});

			double steps = e.getValues().size();
			double rmes = Math.sqrt(e.getRelRmes() / steps);
			e.setRelRmes(rmes);

			rmes = Math.sqrt(e.getAbsRmes() / steps);
			e.setAbsRmes(rmes);
		});
	}

	private double				errorAbsTol;
	private double				errorRelTol;
	private ValidationResult	result;
}
