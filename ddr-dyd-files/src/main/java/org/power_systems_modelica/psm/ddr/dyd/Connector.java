package org.power_systems_modelica.psm.ddr.dyd;

import java.util.Objects;

public class Connector
{
	public Connector(String id, String pin, String target)
	{
		Objects.requireNonNull(pin);
		this.id = id;
		this.pin = pin;
		this.target = target;
	}

	public String getId()
	{
		return id;
	}

	public String getPin()
	{
		return pin;
	}

	public String getTarget()
	{
		return target;
	}

	private final String	id;
	private final String	pin;
	private final String	target;

}
