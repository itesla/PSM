package org.power_systems_modelica.psm.modelica.engine;

import java.util.Collection;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;

public interface ModelicaEngine extends AutoCloseable
{
	void configure(Configuration config);

	boolean validate(ModelicaDocument mo, int depth);

	void simulate(ModelicaDocument mo);

	void simulate(Collection<ModelicaDocument> mos);

	ModelicaSimulationFinalResults getSimulationResults();
}
