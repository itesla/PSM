package org.power_systems_modelica.psm.mo2dyd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InitResults2SimInputs
{
	public InitResults2SimInputs(Path refsCsv)
	{
		initResultsFromSimInputs = new HashMap<>();
		try
		{
			read(refsCsv);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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

	// TODO use a csv reader
	private void read(Path f) throws FileNotFoundException, IOException
	{
		try (BufferedReader in = Files.newBufferedReader(f);)
		{
			// Check the header
			String line = in.readLine();
			String[] header = line.split(",");
			assert (header[0].equals("sim_component"));
			assert (header[1].equals("sim_var"));
			assert (header[2].equals("init_component"));
			assert (header[3].equals("init_var"));
			while ((line = in.readLine()) != null)
			{
				String[] fields = line.split(",");
				String simComponent = fields[0];
				String simVar = fields[1];
				String initComponent = fields[2];
				String initVar = fields[3];

				InitResult initResult = new InitResult(initComponent, initVar);
				SimInput simInput = new SimInput(simComponent, simVar);
				initResultsFromSimInputs.put(simInput, initResult);
			}
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
