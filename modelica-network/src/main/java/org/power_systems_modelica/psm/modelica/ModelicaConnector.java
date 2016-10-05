package org.power_systems_modelica.psm.modelica;

public class ModelicaConnector
{
	public ModelicaConnector(String id, String pin, boolean reusable)
	{
		this.id = id;
		this.pin = pin;
		this.reusable = reusable;
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

	public boolean isReusable()
	{
		return reusable;
	}

	private final String	id;
	private final String	pin;
	private final String	ref;
	private final boolean	reusable;
}
