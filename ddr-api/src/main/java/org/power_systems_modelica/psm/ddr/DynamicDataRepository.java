package org.power_systems_modelica.psm.ddr;

import java.util.List;

import org.power_systems_modelica.psm.modelica.ModelicaEvent;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;

import eu.itesla_project.iidm.network.Identifiable;

public interface DynamicDataRepository
{
	public String getType();

	public void setLocation(String location);

	public void connect() throws ConnectionException;

	public ModelicaModel getModelicaModel(Identifiable<?> e);

	public ModelicaModel getModelicaInitializationModel(Identifiable<?> e);

	List<ModelicaDeclaration> getSystemDeclarations();

	public List<ModelicaEquation> getSystemEquations(ModelicaSystemModel m);

	public ModelicaModel getModelicaModelForEvent(ModelicaEvent event, Identifiable<?> e);
}