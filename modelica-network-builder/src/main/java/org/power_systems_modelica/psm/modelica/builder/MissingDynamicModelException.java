package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;

import eu.itesla_project.iidm.network.Identifiable;

@SuppressWarnings("serial")
public class MissingDynamicModelException extends Exception
{
	public MissingDynamicModelException(Collection<Identifiable<?>> elements)
	{
		this.elements = elements;
	}

	public Collection<Identifiable<?>> getElements()
	{
		return elements;
	}

	private final Collection<Identifiable<?>> elements;
}
