package org.power_systems_modelica.psm.gui.utils;

import java.io.FileReader;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.power_systems_modelica.psm.gui.model.DsData;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.StrReplace;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class CsvReader {

	public static Map<String, List<DsData>> readVariableColumnsWithCsvListReader(
			String location,
			String extension) throws Exception
	{

		Map<String, List<DsData>> values = new HashMap<String, List<DsData>>();
		ICsvListReader listReader = null;
		try
		{
			Optional<Path> path = Files
					.walk(Paths.get(location), FileVisitOption.FOLLOW_LINKS)
					.filter((p) -> !p.toFile().isDirectory()
							&& p.toFile().getAbsolutePath().endsWith(extension))
					.findFirst();
			if (!path.isPresent()) return null;

			listReader = new CsvListReader(
					new FileReader(path.get().toFile()),
					CsvPreference.STANDARD_PREFERENCE);

			// Read and process the header
			// https://super-csv.github.io/super-csv/apidocs/org/supercsv/io/ICsvReader.html#get-int-
			// column indexes begin at 1
			listReader.getHeader(true);
			int columns = listReader.length();
			String[] columnNames = new String[columns];
			for (int i = 2; i <= columns; i++)
			{
				List<DsData> dsData = new ArrayList<DsData>();
				columnNames[i-1] = listReader.get(i);
				values.put(columnNames[i-1], dsData);
			}
			final CellProcessor[] processors = getProcessors(columns);

			while ((listReader.read()) != null)
			{
				final List<Object> columnValues = listReader.executeProcessors(processors);
				Double time = (Double) columnValues.get(0);
				for (int i = 1; i < columns; i++)
				{
					List<DsData> dsData = values.get(columnNames[i]);
					dsData.add(new DsData(time, (Double) columnValues.get(i)));
				}
			}
		}
		finally
		{
			if (listReader != null)
			{
				listReader.close();
			}
		}
		return values;
	}

	private static CellProcessor[] getProcessors(int num)
	{
		CellProcessor[] processors = new CellProcessor[num];
		for (int i = 0; i < num; i++)
			processors[i] = new StrReplace("null", STR_DOUBLE_NAN, new ParseDouble());
		return processors;
	}
	
	private static final String STR_DOUBLE_NAN = "" + Double.NaN;

}