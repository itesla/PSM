package org.power_systems_modelica.psm.dd;

/*
 * #%L
 * Dynamic Data
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Map;

import com.powsybl.iidm.network.Identifiable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class Event
{
	public Event(String event, Identifiable<?> element, Map<String, Object> parameters)
	{
		this.event = event;
		this.element = element;
		this.parameters = parameters;
	}

	public String getEvent()
	{
		return event;
	}

	public Identifiable<?> getElement()
	{
		return element;
	}

	public Map<String, Object> getParameters()
	{
		return parameters;
	}
	
	public Object getParameter(String key)
	{
		return parameters.get(key);
	}

	public void setInstance(String instance)
	{
		this.instance = instance;
	}

	public String getInstance()
	{
		return instance;
	}

	private final String				event;
	private final Identifiable<?>		element;
	private final Map<String, Object>	parameters;

	private String						instance;
}