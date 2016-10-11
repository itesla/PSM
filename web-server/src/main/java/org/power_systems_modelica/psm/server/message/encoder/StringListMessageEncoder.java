package org.power_systems_modelica.psm.server.message.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.power_systems_modelica.psm.server.message.StringListMessage;

public class StringListMessageEncoder implements Encoder.Text<StringListMessage>
{
	@Override
	public String encode(StringListMessage message) throws EncodeException
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
