package org.power_systems_modelica.psm.server.message;

import javax.json.stream.JsonGenerator;

import com.google.gson.Gson;

public class StringListMessage extends Message<StringListSynthesis>
{
	public StringListMessage(StringListSynthesis stringListSynthesis)
	{
		super(stringListSynthesis);
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
	protected void toJson(JsonGenerator generator)
	{
	}

	private static final String TYPE = "stringList";
}
