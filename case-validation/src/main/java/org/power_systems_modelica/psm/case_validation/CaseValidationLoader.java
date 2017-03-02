package org.power_systems_modelica.psm.case_validation;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.ICsvListReader;

public class CaseValidationLoader
{

	public static final Path DATA_TMP = Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");

	public CaseValidationLoader(ValidationResult result, boolean writeFile)
	{
		this.r = result;
		this.writeFile = writeFile;
		this.postName = "";
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

	public void prepareOutputNames(String pathRefData) throws IOException
	{
		BufferedReader bufferValues;
		try
		{
			bufferValues = new BufferedReader(new FileReader(pathRefData));
			String dataRow = bufferValues.readLine();
			outputNames = Arrays.asList(dataRow.replaceAll("\"", "").split(","));
			bufferValues.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, List<TimeValue>> loadCvsData(String pathData, boolean refData)
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
			Map<String, List<TimeValue>> modelicaData) throws IOException
	{
		List<String> elements = new ArrayList<>();
		elements.addAll(r.getElements().keySet());
		elements.forEach(k -> {

			if (k.equalsIgnoreCase("Time")) return;

			Element e = r.getElements().get(k);
			if (e == null) return;

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
				if (modIdx == modelicaTimes.length - 1 || refIdx == refTimes.length - 1)
					nextTime = false;

				TimeValue modelicaValue = modelicaValues.get(modIdx);
				TimeValue refValue = refValues.get(refIdx);

				ComparisionData data = new ComparisionData();

				data.setTime(time);
				data.setModelicaData(modelicaValue.getValue());
				data.setRefData(refValue.getValue());

				e.addValue(time, data);
				time += stepSize;
			}
		});

		calculateDiff();
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
					v.setAbsDif(
							Math.abs(v.getRefData() - (v.getModelicaData() * (Math.PI / 180.0))));

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
					new FileWriter(DATA_TMP.resolve("DataDif" + postName + ".csv").toString()));
			BufferedWriter brRel = new BufferedWriter(
					new FileWriter(DATA_TMP.resolve("DataDifRel" + postName + ".csv").toString()));
			BufferedWriter brMod = new BufferedWriter(
					new FileWriter(
							DATA_TMP.resolve("DataModelica" + postName + ".csv").toString()));
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

	private ValidationResult	r;
	private List<String>		outputNames	= new ArrayList<>();;

	private String				postName;
	private boolean				writeFile;

	private static final Logger	LOG			= LoggerFactory
			.getLogger(CaseValidationLoader.class);

}
