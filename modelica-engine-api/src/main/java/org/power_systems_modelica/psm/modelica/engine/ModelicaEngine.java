package org.power_systems_modelica.psm.modelica.engine;

import java.util.Collection;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;

public interface ModelicaEngine extends AutoCloseable
{
	void configure(Configuration config);

	void simulate(ModelicaDocument mo) throws Exception;

	void simulate(Collection<ModelicaDocument> mos) throws Exception;

	ModelicaSimulationFinalResults getSimulationResults();
	
	ModelicaEngineProgress getModelicaEngineProgress();
	
	void progress(String message);
}
