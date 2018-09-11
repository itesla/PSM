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
import org.power_systems_modelica.psm.dd.equations.Literal;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class LiteralXml
{
	public static final String	ELEMENT_NAME	= "literal";

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