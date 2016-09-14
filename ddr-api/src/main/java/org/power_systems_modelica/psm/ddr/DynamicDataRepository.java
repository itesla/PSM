package org.power_systems_modelica.psm.ddr;

import java.util.List;

import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaParameter;

import eu.itesla_project.iidm.network.Identifiable;

public interface DynamicDataRepository
{
	public String getType();

	public void setLocation(String location);

	public void connect() throws ConnectionException;

	public List<ModelicaParameter> getSystemParameters();

	public ModelicaModel getModelicaModel(Identifiable<?> e);

	// TODO We need a way to define additional equations not directly obtainable from elements
	// As an example: obtain the additional equations related to omegaRef
}