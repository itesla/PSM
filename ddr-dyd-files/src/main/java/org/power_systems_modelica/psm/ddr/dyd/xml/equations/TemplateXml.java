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

import org.power_systems_modelica.psm.dd.equations.ExpressionTemplate;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class TemplateXml
{
	public static final String	ELEMENT_NAME	= "template";

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