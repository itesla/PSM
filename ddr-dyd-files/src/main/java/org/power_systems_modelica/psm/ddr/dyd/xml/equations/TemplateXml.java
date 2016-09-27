package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.ExpressionTemplate;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class TemplateXml
{
	public static final String ELEMENT_NAME = "template";

	public static ExpressionTemplate read(XMLStreamReader reader)
			throws XMLStreamException
	{
		String variable = reader.getAttributeValue(null, "variable");
		String text = XmlUtil.readUntilEndElement(ELEMENT_NAME, reader, null);
		ExpressionTemplate t = new ExpressionTemplate(variable, text);
		return t;
	}

	public static void write(ExpressionTemplate t, XMLStreamWriter w)
			throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		w.writeAttribute("variable", t.getVariable());
		w.writeCharacters(t.getTemplate());
		w.writeEndElement();
	}
}