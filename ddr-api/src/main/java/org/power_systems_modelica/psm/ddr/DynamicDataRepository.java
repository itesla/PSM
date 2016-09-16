package org.power_systems_modelica.psm.ddr;

import java.util.List;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

import eu.itesla_project.iidm.network.Identifiable;

public interface DynamicDataRepository
{
	public String getType();

	public void setLocation(String location);

	public void connect() throws ConnectionException;

	public ModelicaModel getModelicaModel(Identifiable<?> e);

	List<ModelicaDeclaration> getSystemDeclarations();

	// TODO We need a way to define additional equations not directly obtainable from elements
	// As an example: obtain the additional equations related to omegaRef
}