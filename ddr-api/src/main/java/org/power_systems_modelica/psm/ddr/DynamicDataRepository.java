package org.power_systems_modelica.psm.ddr;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;

import eu.itesla_project.iidm.network.Identifiable;

public interface DynamicDataRepository
{
	String getType();

	void setLocation(String location);

	String getLocation();

	void connect() throws ConnectionException;

	ModelicaModel getModelicaModel(Identifiable<?> e, Stage stage);

	Optional<ModelicaModel> getSystemModel(Stage stage);

	List<ModelicaEquation> getSystemOtherEquationsInContext(
			ModelicaSystemModel m,
			Stage stage);

	ModelicaModel getModelicaModelForEvent(String id, Identifiable<?> e);

	enum Injection
	{
		ADD, REPLACE
	}

	Collection<String> getEvents();

	Injection getEventInjection(String event);

	List<EventParameter> getEventParameters(String event);

	StaticType getEventAppliesToStaticType(String event);

	public static final String EVENT_PARAMS_DATA_SOURCE = "EVENT";
}