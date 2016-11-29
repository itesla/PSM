package org.power_systems_modelica.psm.ddr;

import java.util.Collection;
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

	List<ModelicaEquation> getSystemEquationsInContext(ModelicaSystemModel m);

	ModelicaModel getModelicaModelForEvent(String id, Identifiable<?> e);

	enum Injection
	{
		ADD, REPLACE
	}

	Injection getInjectionForEvent(String id);

	Collection<String> getEvents();

	List<EventParameter> getEventParameters(String event);

	public static final String EVENT_PARAMS_DATA_SOURCE = "EVENT";
}