package org.power_systems_modelica.psm.ddr.dyd;

public class Connector
{
	public Connector(String id, String pin, boolean reusable)
	{
		this.id = id;
		this.pin = pin;
		this.reusable = reusable;
	}

	public String getId()
	{
		return id;
	}

	public String getPin()
	{
		return pin;
	}

	public boolean isReusable()
	{
		return reusable;
	}

	private final String	id;
	private final String	pin;
	private final boolean	reusable;

}
