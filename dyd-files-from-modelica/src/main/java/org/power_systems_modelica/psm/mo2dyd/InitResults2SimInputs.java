package org.power_systems_modelica.psm.mo2dyd;

/*
 * #%L
 * DYD files from Modelica
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class InitResults2SimInputs
{
	public InitResults2SimInputs()
	{
		initResultsFromSimInputs = new HashMap<>();
	}
	
	public InitResult getInitResultFromSimInput(SimInput simInput)
	{
		InitResult r = initResultsFromSimInputs.get(simInput);
		if (r == null) r = getDefaultInitResultFromSimInput(simInput);
		return r;
	}

	private InitResult getDefaultInitResultFromSimInput(SimInput simInput)
	{
		// Default behavior for mapping initialization results to simulation inputs:
		// if variable name starts with "init_"
		// it is considered as referring to an initialization result value of same name without "init_" prefix
		if (simInput.var.startsWith("init_"))
		{
			String ivar = simInput.var.replace("init_", "");
			return new InitResult(simInput.component, ivar);
		}
		return null;
	}

	public void read(Path f) throws FileNotFoundException, IOException
	{
		ICsvListReader csvListReader = null;
		try (BufferedReader in = Files.newBufferedReader(f);)
		{	
			csvListReader = new CsvListReader(in, CsvPreference.STANDARD_PREFERENCE);
			String[] header = csvListReader.getHeader(true);

			assert (header[0].equals("sim_component"));
			assert (header[1].equals("sim_var"));
			assert (header[2].equals("init_component"));
			assert (header[3].equals("init_var"));
			List<String> csvRow;
			while((csvRow = csvListReader.read()) != null) 
			{
				InitResult initResult = new InitResult(csvRow.get(2), csvRow.get(3));
				SimInput simInput = new SimInput(csvRow.get(0), csvRow.get(1));
				initResultsFromSimInputs.put(simInput, initResult);
			}
			csvListReader.close();
		}
	}

	static class ComponentVar
	{
		public ComponentVar(String component, String var)
		{
			Objects.requireNonNull(component);
			Objects.requireNonNull(var);
			this.component = component;
			this.var = var;
		}

		@Override
		public boolean equals(Object other)
		{
			if (other == null) return false;
			if (!(other instanceof ComponentVar)) return false;
			ComponentVar o = (ComponentVar) other;
			return this.component.equals(o.component) &&
					this.var.equals(o.var);
		}

		@Override
		public int hashCode()
		{
			return this.component.hashCode() + this.var.hashCode();
		}

		final String	component;
		final String	var;
	}

	static class InitResult extends ComponentVar
	{
		public InitResult(String component, String var)
		{
			super(component, var);
		}
	}

	static class SimInput extends ComponentVar
	{
		public SimInput(String component, String var)
		{
			super(component, var);
		}
	}

	private Map<SimInput, InitResult> initResultsFromSimInputs;
}
