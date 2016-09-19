package org.power_systems_modelica.psm.modelica.engine;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.commons.util.ServiceLoaderCache;

public class ModelicaEngineMainFactory
{
	public static ModelicaEngine create(String engine)
	{
		try
		{
			Optional<ModelicaEngineFactory> mf;
			mf = MODELICA_ENGINE_FACTORIES
					.getServices()
					.stream()
					.filter(f -> f.getEngine().equals(engine))
					.findFirst();
			if (!mf.isPresent())
				throw new Exception("No Modelica engine factory found for engine: " + engine);
			ModelicaEngine e = mf.get().newInstance();
			return e;
		}
		catch (Exception x)
		{
			LOG.error(
					"Could not create Modelica engine {}, reason is '{}'",
					engine,
					x.getMessage());
			throw new RuntimeException(x);
		}
	}

	private static final ServiceLoaderCache<ModelicaEngineFactory>	MODELICA_ENGINE_FACTORIES	= new ServiceLoaderCache<>(
			ModelicaEngineFactory.class);
	private static final Logger										LOG							= LoggerFactory
			.getLogger(ModelicaEngineMainFactory.class);
}
