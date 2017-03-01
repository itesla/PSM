package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Connection;

public class ConnectionXml
{
	public static final String	ROOT_ELEMENT_NAME	= "connection";

	public static Connection read(XMLStreamReader r)
	{
		String id1 = r.getAttributeValue(null, "id1");
		String var1 = r.getAttributeValue(null, "var1");
		String id2 = r.getAttributeValue(null, "id2");
		String var2 = r.getAttributeValue(null, "var2");
		Connection c = new Connection(id1, var1, id2, var2);
		return c;
	}

	public static void write(XMLStreamWriter w, Connection c)
			throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("id1", c.getId1());
		w.writeAttribute("var1", c.getVar1());
		w.writeAttribute("id2", c.getId2());
		w.writeAttribute("var2", c.getVar2());
	}
}
