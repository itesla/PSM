package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.Expression;
import org.power_systems_modelica.psm.ddr.dyd.equations.Folding;
import org.power_systems_modelica.psm.ddr.dyd.equations.Literal;
import org.power_systems_modelica.psm.ddr.dyd.equations.Quotient;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class ExpressionXml
{
	public static Expression read(XMLStreamReader reader, String elementName)
			throws XMLStreamException
	{
		final Expression[] ex = new Expression[1];
		XmlUtil.readUntilEndElement(
				elementName,
				reader,
				() -> {
					switch (reader.getLocalName())
					{
					case FoldingXml.SUM_ELEMENT_NAME:
						ex[0] = FoldingXml.read(reader,
								FoldingXml.SUM_ELEMENT_NAME);
						break;
					case FoldingXml.PRODUCT_ELEMENT_NAME:
						ex[0] = FoldingXml.read(reader,
								FoldingXml.PRODUCT_ELEMENT_NAME);
						break;
					case QuotientXml.ELEMENT_NAME:
						ex[0] = QuotientXml.read(reader);
						break;
					case LiteralXml.ELEMENT_NAME:
						ex[0] = LiteralXml.read(reader);
						break;
					}
				});
		return ex[0];
	}

	public static void write(Expression e, XMLStreamWriter w) throws XMLStreamException
	{
		if (e instanceof Folding)
			FoldingXml.write((Folding) e, w);
		else if (e instanceof Quotient)
			QuotientXml.write((Quotient) e, w);
		else if (e instanceof Literal)
			LiteralXml.write((Literal) e, w);
	}
}