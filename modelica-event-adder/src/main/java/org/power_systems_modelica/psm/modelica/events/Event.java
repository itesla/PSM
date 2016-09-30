package org.power_systems_modelica.psm.modelica.events;

import java.util.Map;

import org.power_systems_modelica.psm.modelica.ModelicaEventType;

import eu.itesla_project.iidm.network.Identifiable;

public class Event
{
	public Event(ModelicaEventType type, Identifiable<?> element, Map<String, Object> parameters)
	{
		this.type = type;
		this.element = element;
		this.parameters = parameters;
	}

	public ModelicaEventType getType()
	{
		return type;
	}

	public Identifiable<?> getElement()
	{
		return element;
	}

	public Map<String, Object> getParameters()
	{
		return parameters;
	}

	private final ModelicaEventType		type;
	private final Identifiable<?>		element;
	private final Map<String, Object>	parameters;
}