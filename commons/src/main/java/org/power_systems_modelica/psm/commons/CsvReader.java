package org.power_systems_modelica.psm.commons;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.StrReplace;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class CsvReader
{

	public static <T> Map<String, List<T>> readVariableColumnsWithCsvListReader(
			File file, CsvReaderPopulator<T> csvReaderPopulator) throws Exception
	{

		Map<String, List<T>> values = new HashMap<String, List<T>>();
		ICsvListReader listReader = null;
		try
		{
			listReader = new CsvListReader(
					new FileReader(file),
					CsvPreference.STANDARD_PREFERENCE);

			// Read and process the header
			// https://super-csv.github.io/super-csv/apidocs/org/supercsv/io/ICsvReader.html#get-int-
			// column indexes begin at 1
			listReader.getHeader(true);
			csvReaderPopulator.prepare(listReader, values);

			final CellProcessor[] processors = getProcessors(listReader.length());

			while ((listReader.read()) != null)
			{
				final List<Object> columnValues = listReader.executeProcessors(processors);
				csvReaderPopulator.populate(columnValues, values);
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
