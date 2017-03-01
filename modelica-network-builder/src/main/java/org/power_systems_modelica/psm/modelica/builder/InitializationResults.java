package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitializationResults
{
	public InitializationResults(ModelicaSimulationFinalResults results)
	{
		this.results = results;
	}

	public Object get(String staticId, String name) throws Exception
	{
		if (results == null)
		{
			String msg = String.format(
					"Initialization results not available. staticId = %s, name = %s",
					staticId,
					name);
			LOG.error(msg);
			throw new Exception(msg);
		}
		return results.getValue(staticId, name);
	}

	private final ModelicaSimulationFinalResults	results;

	private static final Logger						LOG	= LoggerFactory
																.getLogger(InitializationResults.class);
}
