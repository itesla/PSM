package org.power_systems_modelica.psm.ddr.dyd;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository.Injection;
import org.power_systems_modelica.psm.ddr.StaticType;

public class ModelForEvent extends Model
{
	public ModelForEvent(String event, Injection injection, String baseId)
	{
		super(baseId);
		this.event = event;
		this.injection = injection;
		this.appliesTo = null;
	}

	public String getEvent()
	{
		return event;
	}

	public Injection getInjection()
	{
		return injection;
	}

	public void setAppliesTo(StaticType appliesTo)
	{
		this.appliesTo = appliesTo;
	}

	public StaticType getAppliesTo()
	{
		return appliesTo;
	}

	@Override
	public String toString()
	{
		return "Event " + event;
	}

	private final String	event;
	private final Injection	injection;
	private StaticType		appliesTo;
}
