package org.power_systems_modelica.psm.server.message;

import javax.json.stream.JsonGenerator;

import org.power_systems_modelica.psm.workflow.WorkflowParameters;

import com.google.gson.Gson;

public class WorkflowParametersMessage extends Message<WorkflowParameters>
{
	public WorkflowParametersMessage(WorkflowParameters parameters)
	{
		super(parameters);
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

	private static final String TYPE = "parameters";
}
