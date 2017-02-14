package org.power_systems_modelica.psm.ddr.dyd;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository.Injection;

public class ModelForEvent extends Model
{
	public ModelForEvent(String event, Injection injection, String baseId)
	{
		super(baseId);
		this.event = event;
		this.injection = injection;
	}

	public String getEvent()
	{
		return event;
	}

	public Injection getInjection()
	{
		return injection;
	}

	@Override
	public String toString()
	{
		return "Event " + event;
	}

	private final String	event;
	private final Injection	injection;
}
