package org.power_systems_modelica.psm.server.message;

import javax.json.stream.JsonGenerator;

import com.google.gson.Gson;

public class ConnectionMessage extends Message<Boolean>
{
	public ConnectionMessage(Boolean body)
	{
		super(body);
	}

	@Override
	protected String getType()
	{
		return type;
	}

	@Override
	public String toJson()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	@Override
	protected void toJson(JsonGenerator generator)
	{
	}

	private final String type = "connection";
}
