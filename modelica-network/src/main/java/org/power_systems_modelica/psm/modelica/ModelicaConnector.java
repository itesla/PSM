package org.power_systems_modelica.psm.modelica;

public class ModelicaConnector
{
	public ModelicaConnector(String id, String pin, boolean reusable)
	{
		ref = id + "." + pin;
		this.reusable = reusable;
	}

	public String getRef()
	{
		return ref;
	}

	public boolean isReusable()
	{
		return reusable;
	}

	private final String	ref;
	private final boolean	reusable;
}
