package org.power_systems_modelica.psm.modelica.engine;

import java.util.Collection;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;

public interface ModelicaEngine
{
	public void configure(Configuration config);

	public void simulate(ModelicaDocument mo);

	// FIXME run a collection of simulations (in parallel according to the configuration)
	public void simulate(Collection<ModelicaDocument> mos);

	public ModelicaSimulationResults getSimulationResults();
}
