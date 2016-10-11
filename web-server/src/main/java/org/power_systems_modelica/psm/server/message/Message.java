package org.power_systems_modelica.psm.server.message;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

public abstract class Message<T>
{
	public String toJson()
	{
		StringWriter writer = new StringWriter();
		try (JsonGenerator generator = Json.createGenerator(writer))
		{
			generator.writeStartObject().write("type", getType()).writeStartObject("body");
			toJson(generator);
			generator.writeEnd().writeEnd();
		}
		return writer.toString();
	}

	public static <U extends Message<?>> String toJson(Iterable<U> messages)
	{
		StringWriter writer = new StringWriter();
		try (JsonGenerator generator = Json.createGenerator(writer))
		{
			generator.writeStartArray();
			for (U message : messages)
			{
				generator.writeStartObject();
				message.toJson(generator);
				generator.writeEnd();
			}
			generator.writeEnd();
		}
		return writer.toString();
	}

	protected final T body;

	protected Message(T body)
	{
		this.body = body;
	}

	protected abstract String getType();

	protected abstract void toJson(JsonGenerator generator);
}
