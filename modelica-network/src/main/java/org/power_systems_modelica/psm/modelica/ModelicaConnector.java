package org.power_systems_modelica.psm.modelica;

import java.util.Optional;

public class ModelicaConnector
{
	public ModelicaConnector(String id, String pin, String target)
	{
		this.id = id;
		this.pin = pin;
		this.target = Optional.ofNullable(target);
		ref = id + "." + pin;
	}

	public String getId()
	{
		return id;
	}

	public String getPin()
	{
		return pin;
	}

	public String getRef()
	{
		return ref;
	}

	public Optional<String> getTarget()
	{
		return target;
	}

	private final String			id;
	private final String			pin;
	private final String			ref;
	private final Optional<String>	target;
}
