package org.power_systems_modelica.psm.server.message.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import org.power_systems_modelica.psm.server.message.CatalogMessage;
import org.power_systems_modelica.psm.server.message.ConnectionMessage;

public class CatalogMessageEncoder implements Encoder.Text<CatalogMessage>
{
	@Override
	public String encode(CatalogMessage message) throws EncodeException
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
