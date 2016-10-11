package org.power_systems_modelica.psm.server.message;

import javax.json.stream.JsonGenerator;

import org.power_systems_modelica.psm.workflow.StatusSynthesis;

import com.google.gson.Gson;

public class StatusMessage extends Message<StatusSynthesis>
{
	public StatusMessage(StatusSynthesis body)
	{
		super(body);
	}

	@Override
	protected String getType()
	{
		return TYPE;
	}

	@Override
	public String toJson()
	{
		Gson gson = new Gson();
		return gson.toJson(this);

	}

	@Override
	public void toJson(JsonGenerator generator)
	{
	}

	private static final String TYPE = "status";
}
