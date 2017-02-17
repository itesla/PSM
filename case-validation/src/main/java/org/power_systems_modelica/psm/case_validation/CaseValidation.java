package org.power_systems_modelica.psm.case_validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.case_validation.model.VariableValidation;

public class CaseValidation
{

	public CaseValidation()
	{
		rr = new CaseValidationLoader(result);
	}

	public void read(double stepSize, String pathRefData, String pathModelData,
			String pathNamesMapping) throws IOException
	{

		rr.loadNamesMapping(stepSize, pathNamesMapping);
		rr.loadModelicaData(stepSize, pathModelData);
		rr.loadRefData(stepSize, pathRefData);
		rr.calculateDiff();
	}

	public void validation(double absThreshold, double relThreshold, List<String> valuesTest)
			throws IOException
	{

		StringBuilder relRmesw = new StringBuilder();
		StringBuilder absRmesw = new StringBuilder();

		CompareData compare = new CompareData(absThreshold, relThreshold, result);
		compare.compareValues();

		valuesTest.forEach(v -> {
			VariableValidation vv = new VariableValidation();
			vv.setName(v);
			result.addVariable(vv);
		});

		int nElements = result.getElements().size();
		int nValuesElement = ((Element) result.getElements().values().iterator().next()).getValues()
				.size();
		int totalValues = nElements * nValuesElement;

		List<String> elements = new ArrayList<>();
		elements.addAll(result.getElements().keySet());

		result.getVariables().forEach(vv -> {

			elements.forEach(ke -> {

				Element e = result.getElements().get(ke);
				if (e.getRefName().contains(vv.getName()))
				{
					vv.addSteps(e.getValues().size());
					vv.addAbsOffset(e.getAbsOffset());
					vv.addRelOffset(e.getRelOffset());

					if (e.getAbsRmes() > absThreshold)
						vv.incRmesKo();
				}
			});

			double offset = vv.getAbsOffset();
			vv.setAbsOffset(offset / nValuesElement);
			vv.incAbsTotalOffset(offset / totalValues);

			offset = vv.getRelOffset();
			vv.setRelOffset(offset / nValuesElement);
			vv.incRelTotalOffset(offset / totalValues);

			vv.setRmesAbove(vv.getRmesKo() / totalValues);
		});

		rr.getOutputNames().forEach(ke -> {

			Element e = result.getElements().get(ke);

			absRmesw.append(e.getRefName() + "	" + e.getAbsRmes() + "\n");
			relRmesw.append(e.getRefName() + "	" + e.getRelRmes() + "\n");
		});

		if (rmseTest)
		{
			System.out.println("Test OK in RMSE");
		}

		result.getVariables().forEach(vv -> {

			System.out.println(vv.getName() + " Points Above Thresold Error " + ": "
					+ vv.getAbsOffset() * 100.0f + " % Total " + vv.getName() + " points: "
					+ vv.getSteps());
		});

		System.out.println("**************** Validation absolute *******************");

		result.getVariables().forEach(vv -> {

			System.out.println("RMES Above Threshold " + ": " + vv.getRmesAbove() + " % ");
			System.out.println(vv.getName() + " Points Above Thresold Error " + ": "
					+ vv.getAbsTotalOffset() * 100.0f + " % " + "points: " + totalValues);
		});

		System.out.println("**************** Validation relative *******************");
		result.getVariables().forEach(vv -> {

			System.out.println(vv.getName() + " Points Above Thresold Error " + ": "
					+ vv.getRelTotalOffset() * 100.0f + " % " + "points: " + totalValues);
		});

		if (writeFile)
		{
			BufferedWriter rmescsv = new BufferedWriter(new FileWriter("RMES1.csv"));
			rmescsv.write(absRmesw.toString());
			rmescsv.close();
			rmescsv = new BufferedWriter(new FileWriter("RMES0.csv"));
			rmescsv.write(relRmesw.toString());
			rmescsv.close();
		}
	}

	public CaseValidationLoader getCaseValidationLoader()
	{
		return rr;
	}

	public ValidationResult getResult()
	{
		return result;
	}

	private CaseValidationLoader	rr;
	private ValidationResult		result		= new ValidationResult();
	private boolean					writeFile	= true;
	private boolean					rmseTest	= true;
}
