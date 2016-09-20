package org.power_systems_modelica.psm.modelica.engine;

public interface ModelicaEngineFactory
{
	public String getEngine();

	public ModelicaEngine create();
}
