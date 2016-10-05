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
		String target = r.getAttributeValue(null, "target");
		Connector c = new Connector(id, pin, target);
		return c;
	}

	public static void write(XMLStreamWriter w, Connector c)
			throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		if (c.getId() != null) w.writeAttribute("id", c.getId());
		w.writeAttribute("pin", c.getPin());
		if (c.getTarget() != null) w.writeAttribute("target", c.getTarget());
	}
}
