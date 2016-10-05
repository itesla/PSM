package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;

public class ModelicaDeclarationXml
{
	public static final String ELEMENT_NAME = "declaration";

	public static void write(XMLStreamWriter w, ModelicaDeclaration d) throws XMLStreamException
	{
		w.writeEmptyElement(ELEMENT_NAME);
		w.writeAttribute("type", d.getType());
		w.writeAttribute("id", d.getId());
		if (d.getValue() != null) w.writeAttribute("value", d.getValue().toString());
		w.writeAttribute("isParameter", Boolean.toString(d.isParameter()));
	}

	public static ModelicaDeclaration read(XMLStreamReader r)
	{
		String type = r.getAttributeValue(null, "type");
		String id = r.getAttributeValue(null, "id");
		Object value = r.getAttributeValue(null, "value");
		boolean isParameter = Boolean.valueOf(r.getAttributeValue(null, "isParameter"));
		Annotation annotation = null;
		ModelicaDeclaration d = new ModelicaDeclaration(type, id, value, isParameter, annotation);
		return d;
	}
}
