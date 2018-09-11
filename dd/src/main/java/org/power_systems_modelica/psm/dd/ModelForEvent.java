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

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelForEvent extends Model
{
	public enum Injection
	{
		ADD, REPLACE
	}

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
