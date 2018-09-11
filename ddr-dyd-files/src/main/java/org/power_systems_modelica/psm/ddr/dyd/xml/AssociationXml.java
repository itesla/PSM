package org.power_systems_modelica.psm.ddr.dyd.xml;

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

import org.power_systems_modelica.psm.dd.Association;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class AssociationXml
{
	public static final String	ROOT_ELEMENT_NAME	= "association";

	public static Association read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		Association a = new Association(id);

		String pattern = r.getAttributeValue(null, "pattern");
		if (pattern != null) a.setPattern(pattern);

		return a;
	}

	public static void write(XMLStreamWriter w, Association a) throws XMLStreamException
	{
		boolean isEmptyElement = true;

		if (isEmptyElement) w.writeEmptyElement(ROOT_ELEMENT_NAME);
		else w.writeStartElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("id", a.getId());
		if (a.getPattern() != null) w.writeAttribute("pattern", a.getPattern());
		if (!isEmptyElement) w.writeEndElement();
	}

}
