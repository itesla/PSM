package org.power_systems_modelica.psm.server.message;

import java.util.HashMap;

import javax.json.stream.JsonGenerator;

import org.power_systems_modelica.psm.server.WorkflowInfo;

import com.google.gson.Gson;

public class WorkflowListMessage extends Message<HashMap<String, WorkflowInfo>>
{
	public WorkflowListMessage(HashMap<String, WorkflowInfo> workflows)
	{
		super(workflows);
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

	private final String type = "workflows";
}
