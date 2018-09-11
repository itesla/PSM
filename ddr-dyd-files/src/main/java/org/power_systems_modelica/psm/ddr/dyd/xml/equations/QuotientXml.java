package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

/*
 * #%L
 * Dynamic Data Repository on DYD files
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.dd.equations.Expression;
import org.power_systems_modelica.psm.dd.equations.Quotient;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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