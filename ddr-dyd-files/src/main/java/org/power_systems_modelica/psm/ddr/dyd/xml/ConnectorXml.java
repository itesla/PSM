package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Connector;

public class ConnectorXml
{
	public static final String ROOT_ELEMENT_NAME = "connector";

	public static Connector read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		String pin = r.getAttributeValue(null, "pin");
		String reusable = r.getAttributeValue(null, "reusable");
		Connector c = new Connector(id, pin, Boolean.valueOf(reusable));
		return c;
	}

	public static void write(XMLStreamWriter w, Connector c)
			throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("id", c.getId());
		w.writeAttribute("pin", c.getPin());
		w.writeAttribute("reusable", Boolean.toString(c.isReusable()));
	}
}
