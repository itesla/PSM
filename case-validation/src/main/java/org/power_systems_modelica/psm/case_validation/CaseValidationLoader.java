package org.power_systems_modelica.psm.case_validation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.case_validation.model.ComparisionData;
import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;

public class CaseValidationLoader
{

	public CaseValidationLoader(ValidationResult result)
	{
		this.r = result;
	}

	public ValidationResult read(double stepSize, String pathRef, String pathModelData,
			String pathNamesMapping) throws IOException
	{
		loadNamesMapping(stepSize, pathNamesMapping);
		loadModelicaData(stepSize, pathModelData);
		loadRefData(stepSize, pathRef);
		calculateDiff();

		return r;
	}

	public void loadNamesMapping(double stepSize, String pathNamesMapping) throws IOException
	{
		BufferedReader bufferNames;
		try
		{
			bufferNames = new BufferedReader(new FileReader(pathNamesMapping));
			String dataRow = bufferNames.readLine();
			if (dataRow == null)
			{
				bufferNames.close();
				return;
			}

			boolean firstExpected = dataRow.split(",")[0].equals("expected");
			while ((dataRow = bufferNames.readLine()) != null)
			{
				Element e = new Element();
				String[] names = dataRow.split(",");
				if (firstExpected)
				{
					e.setRefName(names[0]);
					e.setModelicaName(names[1]);
				}
				else
				{
					e.setRefName(names[1]);
					e.setModelicaName(names[0]);
				}
				e.setStepSize(stepSize);

				r.addElement(e.getRefName(), e);
			}

			bufferNames.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadModelicaData(double stepSize, String pathModelData) throws IOException
	{
		BufferedReader bufferValues;
		try
		{
			bufferValues = new BufferedReader(new FileReader(pathModelData));
			String dataRow = bufferValues.readLine();
			List<String> names = Arrays.asList(dataRow.replaceAll("\"", "").split(","));
			final int timeCol = getTimeIndex(names);

			List<String> elements = new ArrayList<>();
			elements.addAll(r.getElements().keySet());

			double lastTime = Double.NEGATIVE_INFINITY;

			while ((dataRow = bufferValues.readLine()) != null)
			{
				List<String> values = Arrays.asList(dataRow.split(","));
				final double time = Double.parseDouble(values.get(timeCol)); 

				if (Math.abs(Math.rint(10*time/stepSize) - Math.rint(10*lastTime/stepSize)) >= 9)
				{
					elements.forEach(k -> {
						
						Element e = r.getElements().get(k);
						double value = Double
								.parseDouble(values.get(names.indexOf(e.getModelicaName())));

						e.addModelicaValue(time, value);
					});
					lastTime = time;
				}
			}

			bufferValues.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadRefData(double stepSize, String pathRefData) throws IOException
	{
		BufferedReader bufferValues;
		try
		{
			bufferValues = new BufferedReader(new FileReader(pathRefData));
			String dataRow = bufferValues.readLine();
			outputNames = Arrays.asList(dataRow.replaceAll("\"", "").split(","));
			final int timeCol = getTimeIndex(outputNames);
			Set<Double> times = ((Element) r.getElements().values().iterator().next()).getValues().keySet();

			List<String> elements = new ArrayList<>();
			elements.addAll(r.getElements().keySet());

			while ((dataRow = bufferValues.readLine()) != null)
			{
				final List<String> values = Arrays.asList(dataRow.split(","));
				final double time = getTimeKey(stepSize, times, Double.parseDouble(values.get(timeCol)));
				if (time < 0.0)
					continue;
				
				elements.forEach(k -> {
					
					Element e = r.getElements().get(k);
					double value = Double.parseDouble(values.get(outputNames.indexOf(e.getRefName())));

					e.addRefValue(time, value);
				});
			}

			bufferValues.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private double getTimeKey(double stepSize, Set<Double> times, double time)
	{
		Iterator<Double> it = times.iterator();
		while (it.hasNext())
		{
			double candidate = it.next();
			if (Math.abs(candidate - time) < stepSize/10)
				return candidate;
		}
		return -1.0;
	}

	private int getTimeIndex(List<String> names)
	{
		for (int i = 0; i <= names.size(); i++)
		{
			String name = names.get(i);
			if (name.equalsIgnoreCase("time"))
				return i;
		}
		return -1;
	}

	public void calculateDiff() throws IOException
	{
		List<String> elements = new ArrayList<>();
		elements.addAll(r.getElements().keySet());
		
		elements.forEach(ke -> {
			
			Element e = r.getElements().get(ke);
			Map<Double, ComparisionData> values = e.getValues();
			values.keySet().forEach(k -> {
				
				ComparisionData v = values.get(k);
				if (e.getRefName().equalsIgnoreCase("time"))
				{
					v.setAbsDif(v.getTime());

				}
				else if (e.getRefName().contains("angle"))
				{
					v.setAbsDif(Math.abs(v.getRefData() - (v.getModelicaData() * (Math.PI / 180.0))));

				}
				else
				{
					v.setAbsDif(Math.abs(v.getRefData() - v.getModelicaData()));

				}

				v.setRelDif(v.getAbsDif() / Math.abs(v.getRefData()));
			});

		});

		if (writeFile)
		{
			StringBuilder sb = new StringBuilder();
			StringBuilder sbRel = new StringBuilder();
			StringBuilder sbMod = new StringBuilder();
			sb.append(outputNames.stream().collect(Collectors.joining(",")) + "\n");
			sbRel.append(outputNames.stream().collect(Collectors.joining(",")) + "\n");
			sbMod.append(outputNames.stream().collect(Collectors.joining(",")) + "\n");

			List<Double> keys = new ArrayList<>();
			keys.addAll(((Element) r.getElements().values().iterator().next()).getValues().keySet());
			Collections.sort(keys);
			keys.forEach( k -> {
				
				final List<String> absDif = new ArrayList<>();
				final List<String> relDif = new ArrayList<>();
				final List<String> modData = new ArrayList<>();
				
				final double id = k;
				outputNames.forEach(ke -> {
					
					Element e = r.getElements().get(ke);
					absDif.add("" + e.getValues().get(id).getAbsDif());
					relDif.add("" + e.getValues().get(id).getRelDif());
					modData.add("" + e.getValues().get(id).getModelicaData());
				});
				sb.append(absDif.stream().collect(Collectors.joining(",")) + "\n");
				sbRel.append(relDif.stream().collect(Collectors.joining(",")) + "\n");
				sbMod.append(modData.stream().collect(Collectors.joining(",")) + "\n");
			});
			
			BufferedWriter br = new BufferedWriter(new FileWriter("DataDif.csv"));
			BufferedWriter brRel = new BufferedWriter(new FileWriter("DataDifRel.csv"));
			BufferedWriter brMod = new BufferedWriter(new FileWriter("DataModelica.csv"));
			br.write(sb.toString());
			brRel.write(sbRel.toString());
			brMod.write(sbMod.toString());
			brRel.close();
			br.close();
			brMod.close();
		}
	}
	
	public List<String> getOutputNames()
	{
		return outputNames;
	}

	private ValidationResult	r ;
	private boolean				writeFile	= true;
	private List<String> 		outputNames;
}
