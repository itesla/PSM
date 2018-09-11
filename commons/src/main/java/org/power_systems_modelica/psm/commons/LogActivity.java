package org.power_systems_modelica.psm.commons;

/*
 * #%L
 * Commons
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class LogActivity
{
	LogActivity(String call)
	{
		this.activity = call;
	}

	void detail(String message)
	{
		this.details.add(message);
	}

	void result(String reply)
	{
		this.results.add(reply);
	}

	void activityError(String callError)
	{
		this.callError = callError;
	}

	final String		activity;
	final List<String>	details	= new ArrayList<>();
	final List<String>	results		= new ArrayList<>();
	String				callError;
}
