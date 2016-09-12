package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Model;

public class ModelXml
{
	public static final String ROOT_ELEMENT_NAME = "model";

	public static Model read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		String staticId = r.getAttributeValue(null, "staticId");
		Model m = new Model(id, staticId);
		return m;
	}

	public static void write(XMLStreamWriter w, Model m) throws XMLStreamException
	{
		w.writeStartElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("id", m.getId());
		w.writeAttribute("staticId", m.getStaticId());

		for (Component mc : m.getComponents())
		{
			ComponentXml.write(w, mc);
		}
		for (Connection mc : m.getConnections())
		{
			ConnectionXml.write(w, mc);
		}

		w.writeEndElement();
	}
}
