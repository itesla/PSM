package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.Expression;
import org.power_systems_modelica.psm.ddr.dyd.equations.Literal;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class LiteralXml
{
	public static final String ELEMENT_NAME = "literal";

	public static void write(Literal e, XMLStreamWriter w) throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		w.writeCharacters(e.get());
		w.writeEndElement();
	}

	public static Expression read(XMLStreamReader r) throws XMLStreamException
	{
		String text = XmlUtil.readUntilEndElement(ELEMENT_NAME, r, null);
		return new Literal(text);
	}
}