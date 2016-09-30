package org.power_systems_modelica.psm.ddr.dyd;

public class ModelForEvent extends Model
{
	public ModelForEvent(String event)
	{
		this.event = event;
	}

	public String getEvent()
	{
		return event;
	}

	private final String event;
}
