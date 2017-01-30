package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Event implements Serializable
{

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public String getElement()
	{
		return element;
	}

	public void setElement(String element)
	{
		this.element = element;
	}

	public List<EventParamGui> getParams()
	{
		return params;
	}

	public void setParams(List<EventParamGui> params)
	{
		this.params = params;
	}

	public EventParamGui getParam(String key)
	{
		Optional<EventParamGui> param = params.stream()
				.filter(p -> p.getNameWithoutUnit().equals(key)).findFirst();
		if (param.isPresent())
			return param.get();

		return null;
	}

	public void fromString(String event)
	{

		String[] eventVar = event.split(",");

		action = eventVar[0];
		element = eventVar[1];
		params = new ArrayList<EventParamGui>();

		int i = 2;
		while (i < eventVar.length)
		{

			EventParamGui p = new EventParamGui();
			p.fromString(eventVar[i]);
			i++;

			params.add(p);
		}
	}

	@Override
	public String toString()
	{

		String t = action +
				"," +
				element;
		if (params != null)
		{
			t += "," + params.stream().map(Object::toString).collect(Collectors.joining(","));
		}

		return t;
	}

	private String				action;
	private String				element;
	private List<EventParamGui>	params;
}
