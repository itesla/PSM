package org.power_systems_modelica.psm.server.message;

import javax.json.stream.JsonGenerator;

import com.google.gson.Gson;

public class CatalogMessage extends Message<CatalogSynthesis>
{
	public CatalogMessage(CatalogSynthesis catalogSynthesis)
	{
		super(catalogSynthesis);
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

	private static final String TYPE = "catalog";
}
