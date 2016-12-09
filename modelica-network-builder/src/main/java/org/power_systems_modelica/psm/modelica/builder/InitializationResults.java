package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;

public class InitializationResults
{
	public InitializationResults(ModelicaSimulationResults results)
	{
		this.results = results;
	}

	public Object get(String staticId, String id, String name) 
	{
		if (results == null)
		{
			String msg = String.format(
					"Initialization results not available. staticId = %s, id = %s, name = %s",
					staticId,
					id,
					name);
			throw new RuntimeException(msg);
		}
		return results.getValue(staticId, id, name);
	}

	private final ModelicaSimulationResults results;
}
