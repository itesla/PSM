package org.power_systems_modelica.psm.ddr;

import java.util.List;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;

import eu.itesla_project.iidm.network.Identifiable;

public interface DynamicDataRepository
{
	String getType();

	void setLocation(String location);

	void connect() throws ConnectionException;

	ModelicaModel getModelicaModel(Identifiable<?> e);

	ModelicaModel getModelicaInitializationModel(Identifiable<?> e);

	List<ModelicaDeclaration> getSystemDeclarations();

	List<ModelicaEquation> getSystemEquations(ModelicaSystemModel m);

	ModelicaModel getModelicaModelForEvent(String id, Identifiable<?> e);

	enum Injection
	{
		ADD, REPLACE
	}

	Injection getInjectionForEvent(String id);

	List<String> getEvents();
	
	List<EventParameter> getEventParameters(String event);
}