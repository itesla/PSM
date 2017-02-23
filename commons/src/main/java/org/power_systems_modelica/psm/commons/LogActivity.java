package org.power_systems_modelica.psm.commons;

import java.util.ArrayList;
import java.util.List;

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
