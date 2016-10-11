package org.power_systems_modelica.psm.server.message.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.power_systems_modelica.psm.server.message.WorkflowResultMessage;

public class WorkflowResultMessageEncoder implements Encoder.Text<WorkflowResultMessage>
{
	@Override
	public String encode(WorkflowResultMessage message) throws EncodeException
	{
		return message.toJson();
	}

	@Override
	public void init(EndpointConfig config)
	{
	}

	@Override
	public void destroy()
	{
	}
}
