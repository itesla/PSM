package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.Equal;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class EquationXml
{
	public static final String ELEMENT_NAME = "equation";

	public static Equation read(XMLStreamReader reader) throws XMLStreamException
	{
		final Equation[] eq = new Equation[1];
		XmlUtil.readUntilEndElement(
				EquationXml.ELEMENT_NAME,
				reader,
				() -> {
					switch (reader.getLocalName())
					{
					case EqualXml.ELEMENT_NAME:
						eq[0] = EqualXml.read(reader);
						break;
					}
				});
		return eq[0];
	}

	public static void write(XMLStreamWriter w, Equation eq)
			throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		if (eq instanceof Equal)
			EqualXml.write((Equal) eq, w);
		w.writeEndElement();
	}
}
