package org.power_systems_modelica.psm.modelica.engine.io;

/*
 * #%L
 * Modelica Engine API
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaSimulationResultsCsv
{
	public static void write(Path f, ModelicaSimulationFinalResults results) throws FileNotFoundException, IOException
	{
		List<Object> rowData = new ArrayList<Object>();
		ICsvListWriter csvListWriter = null;
		try(BufferedWriter out = Files.newBufferedWriter(f);) {
			csvListWriter = new CsvListWriter(out, CsvPreference.STANDARD_PREFERENCE);
			//Set the header
			rowData.add("key");
			rowData.add("value");
			csvListWriter.write(rowData);
			
			for (Entry<String, Object> e : results.getEntries())
			{
				rowData = new ArrayList<Object>();
				rowData.add(e.getKey());
				rowData.add(e.getValue());
				csvListWriter.write(rowData);
			}
			csvListWriter.close();
		}
	}

	public static ModelicaSimulationFinalResults read(Path f) throws FileNotFoundException, IOException
	{
		
		ICsvListReader csvListReader = null;
		try (BufferedReader in = Files.newBufferedReader(f);)
		{	
			ModelicaSimulationFinalResults results = new ModelicaSimulationFinalResults();
			csvListReader = new CsvListReader(in, CsvPreference.STANDARD_PREFERENCE);
			csvListReader.getHeader(true); //Skip the header

			List<String> csvRow;
			while((csvRow = csvListReader.read()) != null) 
			{
				results.put(csvRow.get(0), csvRow.get(1));
			}
			csvListReader.close();
			return results;
		}
	}
}
