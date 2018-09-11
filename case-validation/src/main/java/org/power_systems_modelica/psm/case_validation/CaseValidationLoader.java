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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.case_validation.model.ComparisionData;
import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.TimeValue;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.commons.CsvReader;
import org.power_systems_modelica.psm.commons.CsvReaderPopulator;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.ICsvListReader;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class CaseValidationLoader
{
	public CaseValidationLoader(ValidationResult result, boolean debugFile, boolean writeFile, String pathOutput)
	{
		this.r = result;
		this.debugFile = debugFile;
		this.writeFile = writeFile;
		this.postName = "";
		this.pathOutput = pathOutput;
	}

	public void setPostName(String postName)
	{
		this.postName = postName;
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
				if (names.length < 2) continue;

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
			e.printStackTrace();
		}

	}

	public void prepareOutputNames(String pathRefData) throws IOException
	{
		BufferedReader bufferValues;
		try
		{
			bufferValues = new BufferedReader(new FileReader(pathRefData));
			String dataRow = bufferValues.readLine();
			List<String> refNames = Arrays.asList(dataRow.replaceAll("\"", "").split(","));
			outputNames = refNames.stream().filter(name -> r.getElements().get(name) != null)
					.collect(Collectors.toList());
			bufferValues.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public Map<String, List<TimeValue>> loadCvsData(String pathData)
			throws IOException
	{
		Map<String, List<TimeValue>> values = null;
		try
		{
			Optional<Path> path = Files
					.walk(Paths.get(pathData), 1,
							FileVisitOption.FOLLOW_LINKS)
					.filter((p) -> !p.toFile().isDirectory()
							&& p.toFile().getAbsolutePath().endsWith(".csv"))
					.findFirst();
			if (path.isPresent())
			{

				values = CsvReader.readVariableColumnsWithCsvListReader(
						path.get().toFile(),
						new CsvReaderPopulator<TimeValue>()
						{

							@Override
							public void prepare(ICsvListReader listReader,
									Map<String, List<TimeValue>> values)
							{
								columns = listReader.length();
								columnNames = new String[columns];
								for (int i = 1; i <= columns; i++)
								{
									String column = listReader.get(i);
									if (column.equalsIgnoreCase("Time"))
									{
										timeColumn = i - 1;
										continue;
									}
									List<TimeValue> timeValue = new ArrayList<TimeValue>();
									columnNames[i - 1] = column;
									values.put(column, timeValue);
								}
							}

							@Override
							public void populate(List<Object> columnValues,
									Map<String, List<TimeValue>> values)
							{
								Double time = (Double) columnValues.get(timeColumn);
								for (int i = 0; i < columns; i++)
								{
									if (i == timeColumn)
									{
										continue;
									}
									List<TimeValue> timeValue = values.get(columnNames[i]);
									timeValue
											.add(new TimeValue(time, (Double) columnValues.get(i)));

								}
							}

							private int	timeColumn;
							private int	columns;
							private String[]	columnNames;
						});
			}
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage());
		}

		return values;
	}

	public void calculateDiff(double stepSize, Map<String, List<TimeValue>> refData,
			Map<String, List<TimeValue>> modelicaData,
			double threshold, double tol) throws IOException
	{
		List<String> elements = new ArrayList<>();
		elements.addAll(r.getElements().keySet());
		elements.forEach(k -> {

			if (k.equalsIgnoreCase("Time")) return;

			Element e = r.getElements().get(k);
			if (e == null) return;

			Map<Double, ComparisionData> values = calculateElementDiff(e, stepSize, refData,
					modelicaData, threshold, tol);
			e.setValues(values);
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
			keys.addAll(
					((Element) r.getElements().values().iterator().next()).getValues().keySet());
			Collections.sort(keys);
			keys.forEach(k -> {

				final List<String> absDif = new ArrayList<>();
				final List<String> relDif = new ArrayList<>();
				final List<String> modData = new ArrayList<>();

				final double id = k;
				outputNames.forEach(ke -> {

					if (ke.equalsIgnoreCase("Time"))
					{
						absDif.add("" + id);
						relDif.add("" + id);
						modData.add("" + id);
					}
					else
					{
						Element e = r.getElements().get(ke);
						absDif.add("" + e.getValues().get(id).getAbsDif());
						relDif.add("" + e.getValues().get(id).getRelDif());
						modData.add("" + e.getValues().get(id).getModelicaData());
					}
				});
				sb.append(absDif.stream().collect(Collectors.joining(",")) + "\n");
				sbRel.append(relDif.stream().collect(Collectors.joining(",")) + "\n");
				sbMod.append(modData.stream().collect(Collectors.joining(",")) + "\n");
			});

			BufferedWriter br = new BufferedWriter(
					new FileWriter(Paths.get(pathOutput).resolve("DataDifAbs" + postName + ".csv").toString()));
			BufferedWriter brRel = new BufferedWriter(
					new FileWriter(Paths.get(pathOutput).resolve("DataDifRel" + postName + ".csv").toString()));
			br.write(sb.toString());
			brRel.write(sbRel.toString());
			brRel.close();
			br.close();
			
			if (debugFile)
			{
				BufferedWriter brMod = new BufferedWriter(
						new FileWriter(
								Paths.get(pathOutput).resolve("DataModelica" + postName + ".csv").toString()));
				brMod.write(sbMod.toString());
				brMod.close();
			}
		}
	}

	public Map<Double, ComparisionData> calculateElementDiff(Element e, double stepSize,
			Map<String, List<TimeValue>> refData,
			Map<String, List<TimeValue>> modelicaData,
			double threshold, double tol)
	{
		Map<Double, ComparisionData> values = new HashMap<>();

		List<TimeValue> refValues = refData.get(e.getRefName());
		refValues.sort(Comparator.comparingDouble(TimeValue::getTime));
		Double[] refTimes = refValues.stream().map(TimeValue::getTime).toArray(Double[]::new);

		List<TimeValue> modelicaValues = modelicaData.get(e.getModelicaName());
		modelicaValues.sort(Comparator.comparingDouble(TimeValue::getTime));
		Double[] modelicaTimes = modelicaValues.stream().map(TimeValue::getTime)
				.toArray(Double[]::new);

		boolean nextTime = true;
		double time = refValues.get(0).getTime();
		while (nextTime)
		{
			int modIdx = getClosestTime(stepSize, modelicaTimes, time);
			int refIdx = getClosestTime(stepSize, refTimes, time);
			if (modIdx == -1 || refIdx == -1)
			{
				nextTime = false;
				continue;
			}
			if (modIdx == modelicaTimes.length - 1 || refIdx == refTimes.length - 1)
				nextTime = false;

			TimeValue modelicaValue = modelicaValues.get(modIdx);
			TimeValue refValue = refValues.get(refIdx);

			ComparisionData data = new ComparisionData();

			data.setTime(time);
			double v = modelicaValue.getValue();
			if (e.getRefName().contains("angle"))
				v = modelicaValue.getValue() * (Math.PI / 180.0);

			data.setModelicaData(v);
			data.setRefData(refValue.getValue());

			if (e.getRefName().equalsIgnoreCase("time"))
			{
				data.setAbsDif(data.getTime());

			}
			else
			{
				data.setAbsDif(Math.abs(data.getRefData() - data.getModelicaData()));

			}

			if ((Math.abs(data.getRefData()) < threshold * tol)
					&& !e.getRefName().equalsIgnoreCase("time"))
			{
				data.setRelDif(data.getAbsDif());
			}
			else data.setRelDif(data.getAbsDif() / Math.abs(data.getRefData()));

			values.put(time, data);
			time += stepSize;
		}

		return values;
	}

	private int getClosestTime(double stepSize, Double[] times, double time)
	{
		int id = Arrays.binarySearch(times, time);
		if (id < 0)
		{
			id *= -1;
			if (id >= times.length)
			{
				double time1 = times[times.length - 1];
				if (time - time1 > stepSize)
					return -1;
				else
					return times.length - 1;
			}
			else
			{
				double time1 = times[id - 2];
				double time2 = times[id - 1];
				if (time - time1 < time2 - time)
					return id - 2;
				else
					return id - 1;
			}
		}
		return id;
	}

	public List<String> getOutputNames()
	{
		return outputNames;
	}

	private ValidationResult	r;
	private List<String>		outputNames	= new ArrayList<>();;

	private String				postName;
	private boolean				debugFile;
	private boolean				writeFile;
	private String				pathOutput;

	private static final Logger	LOG			= LoggerFactory
			.getLogger(CaseValidationLoader.class);

}
