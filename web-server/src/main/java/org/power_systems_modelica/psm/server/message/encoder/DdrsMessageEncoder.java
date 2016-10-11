package org.power_systems_modelica.psm.server.message.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.power_systems_modelica.psm.server.message.DdrsMessage;

public class DdrsMessageEncoder implements Encoder.Text<DdrsMessage>
{
	@Override
	public String encode(DdrsMessage message) throws EncodeException
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
