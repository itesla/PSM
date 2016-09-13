package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetReference;

public class ComponentXml
{
	public static final String ROOT_ELEMENT_NAME = "component";

	public static Component read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		String name = r.getAttributeValue(null, "name");
		Component c = new Component(id, name);

		String parFile = r.getAttributeValue(null, "parFile");
		String parId = r.getAttributeValue(null, "parId");
		ParameterSetReference pref = null;
		if (parFile != null && parId != null) pref = new ParameterSetReference(parFile, parId);
		if (pref != null) c.setParameterSetReference(pref);

		return c;
	}

	public static void write(XMLStreamWriter w, Component c) throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("name", c.getName());
		if (c.getId() != null) w.writeAttribute("id", c.getId());

		ParameterSetReference pref = c.getParameterSetReference();
		if (c != null)
		{
			w.writeAttribute("parFile", pref.getContainer());
			w.writeAttribute("parId", pref.getSet());
		}
	}
}
