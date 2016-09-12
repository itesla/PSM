package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.ParameterValue;

public class ParameterValueXml
{
	public static final String ROOT_ELEMENT_NAME = "par";

	public static ParameterValue read(XMLStreamReader r)
	{
		String type = r.getAttributeValue(null, "type");
		String name = r.getAttributeValue(null, "name");
		String value = r.getAttributeValue(null, "value");
		return new ParameterValue(type, name, value);
	}

	public static void write(XMLStreamWriter w, ParameterValue p) throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("type", p.getType());
		w.writeAttribute("name", p.getName());
		w.writeAttribute("value", p.getValue());
	}
}
