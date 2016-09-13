package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;

public class ModelXml
{
	public static final String ROOT_ELEMENT_NAME = "model";

	public static Model read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		String staticId = r.getAttributeValue(null, "staticId");
		String type = r.getAttributeValue(null, "type");

		Model m = null;
		if (type != null) m = new ModelForType(type);
		else m = new Model(id, staticId);

		return m;
	}

	public static void write(XMLStreamWriter w, Model m) throws XMLStreamException
	{
		w.writeStartElement(ROOT_ELEMENT_NAME);
		if (m.getId() != null) w.writeAttribute("id", m.getId());
		if (m.getStaticId() != null) w.writeAttribute("staticId", m.getStaticId());
		if (m instanceof ModelForType) w.writeAttribute("type", ((ModelForType) m).getType());

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
