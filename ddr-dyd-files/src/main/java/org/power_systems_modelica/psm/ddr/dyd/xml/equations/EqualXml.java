package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.Equal;
import org.power_systems_modelica.psm.ddr.dyd.equations.Expression;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class EqualXml
{
	static final String			ELEMENT_NAME		= "equal";

	private static final String	LEFT_ELEMENT_NAME	= "left";
	private static final String	RIGHT_ELEMENT_NAME	= "right";

	public static Equal read(XMLStreamReader reader) throws XMLStreamException
	{
		final Expression[] exs = new Expression[2];
		XmlUtil.readUntilEndElement(
				ELEMENT_NAME,
				reader,
				() -> {
					switch (reader.getLocalName())
					{
					case EqualXml.LEFT_ELEMENT_NAME:
						exs[0] = ExpressionXml.read(reader, EqualXml.LEFT_ELEMENT_NAME);
						break;
					case EqualXml.RIGHT_ELEMENT_NAME:
						exs[1] = ExpressionXml.read(reader, EqualXml.RIGHT_ELEMENT_NAME);
						break;
					}
				});
		Expression left = exs[0];
		Expression right = exs[1];
		Equal eq = new Equal(left, right);
		return eq;
	}

	public static void write(Equal eq, XMLStreamWriter w) throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		w.writeStartElement(LEFT_ELEMENT_NAME);
		ExpressionXml.write(eq.getLeft(), w);
		w.writeEndElement();
		w.writeStartElement(RIGHT_ELEMENT_NAME);
		ExpressionXml.write(eq.getRight(), w);
		w.writeEndElement();
		w.writeEndElement();
	}
}