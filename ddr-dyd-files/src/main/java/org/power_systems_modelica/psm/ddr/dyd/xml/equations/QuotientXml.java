package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.Expression;
import org.power_systems_modelica.psm.ddr.dyd.equations.Quotient;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class QuotientXml
{
	public static final String	ELEMENT_NAME			= "quotient";

	private static final String	DIVIDEND_ELEMENT_NAME	= "dividend";
	private static final String	DIVISOR_ELEMENT_NAME	= "divisor";

	public static Quotient read(XMLStreamReader reader) throws XMLStreamException
	{
		final Expression[] exs = new Expression[2];
		XmlUtil.readUntilEndElement(
				ELEMENT_NAME,
				reader,
				() -> {
					switch (reader.getLocalName())
					{
					case QuotientXml.DIVIDEND_ELEMENT_NAME:
						exs[0] = ExpressionXml.read(reader, QuotientXml.DIVIDEND_ELEMENT_NAME);
						break;
					case QuotientXml.DIVISOR_ELEMENT_NAME:
						exs[1] = ExpressionXml.read(reader, QuotientXml.DIVISOR_ELEMENT_NAME);
						break;
					}
				});
		Expression dividend = exs[0];
		Expression divisor = exs[1];
		return new Quotient(dividend, divisor);
	}

	public static void write(Quotient q, XMLStreamWriter w) throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		w.writeStartElement(DIVIDEND_ELEMENT_NAME);
		ExpressionXml.write(q.getDividend(), w);
		w.writeEndElement();
		w.writeStartElement(DIVISOR_ELEMENT_NAME);
		ExpressionXml.write(q.getDivisor(), w);
		w.writeEndElement();
		w.writeEndElement();
	}
}