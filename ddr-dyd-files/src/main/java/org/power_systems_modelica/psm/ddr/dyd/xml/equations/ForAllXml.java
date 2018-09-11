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
import org.power_systems_modelica.psm.dd.equations.ForAll;
import org.power_systems_modelica.psm.dd.equations.Selector;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ForAllXml
{
	public static final String	ELEMENT_NAME	= "forAll";

	public static ForAll read(XMLStreamReader reader) throws XMLStreamException
	{
		Selector[] s = new Selector[1];
		ExpressionTemplate[] t = new ExpressionTemplate[1];
		XmlUtil.readUntilEndElement(ELEMENT_NAME, reader, () -> {
			switch (reader.getLocalName())
			{
			case TemplateXml.ELEMENT_NAME:
				t[0] = TemplateXml.read(reader);
				break;
			// FIXME Must be a selector ... should we check it ???
			// FIXME We have already read the start element
			default:
				s[0] = SelectorXml.read(reader, reader.getLocalName());
				break;
			}
		});
		ForAll forAll = new ForAll(s[0], t[0]);
		return forAll;
	}

	public static void write(ForAll f, XMLStreamWriter w) throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		SelectorXml.write(f.getSelector(), w);
		TemplateXml.write(f.getTemplate(), w);
		w.writeEndElement();
	}
}