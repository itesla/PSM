package org.power_systems_modelica.psm.modelica.engine.logs;

import java.util.ArrayList;
import java.util.List;

public class CallLog
{
	CallLog(String call)
	{
		this.call = call;
	}

	void status(String message)
	{
		this.statuses.add(message);
	}

	void reply(String reply)
	{
		this.replies.add(reply);
	}

	void callError(String callError)
	{
		this.callError = callError;
	}

	final String		call;
	final List<String>	statuses	= new ArrayList<>();
	final List<String>	replies		= new ArrayList<>();
	String				callError;
}
