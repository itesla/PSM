package org.power_systems_modelica.psm.modelica.events;

import java.util.Map;

import eu.itesla_project.iidm.network.Identifiable;

public class Event
{
	public Event(String id, Identifiable<?> element, Map<String, Object> parameters)
	{
		this.id = id;
		this.element = element;
		this.parameters = parameters;
	}

	public String getId()
	{
		return id;
	}

	public Identifiable<?> getElement()
	{
		return element;
	}

	public Map<String, Object> getParameters()
	{
		return parameters;
	}

	private final String				id;
	private final Identifiable<?>		element;
	private final Map<String, Object>	parameters;
}