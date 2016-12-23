package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;

public class InitializationResults
{
	public InitializationResults(ModelicaSimulationFinalResults results)
	{
		this.results = results;
	}

	public Object get(String staticId, String name)
	{
		if (results == null)
		{
			String msg = String.format(
					"Initialization results not available. staticId = %s, name = %s",
					staticId,
					name);
			throw new RuntimeException(msg);
		}
		return results.getValue(staticId, name);
	}

	private final ModelicaSimulationFinalResults results;
}
