package org.power_systems_modelica.psm.commons;

import java.util.List;
import java.util.Map;

import org.supercsv.io.ICsvListReader;

public interface CsvReaderPopulator<T>
{
	public void prepare(ICsvListReader listReader, Map<String, List<T>> values);
	public void populate(List<Object> columnValues, Map<String, List<T>> values);
}
