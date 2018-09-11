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

import org.power_systems_modelica.psm.dd.equations.PrefixSelector;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class PrefixSelectorXml
{
	public static final String	ELEMENT_NAME	= "startsWith";

	public static void write(PrefixSelector s, XMLStreamWriter w) throws XMLStreamException
	{
		w.writeEmptyElement(ELEMENT_NAME);
		w.writeAttribute("prefix", s.getPrefix());
	}

	public static PrefixSelector read(XMLStreamReader reader)
	{
		String prefix = reader.getAttributeValue(null, "prefix");
		PrefixSelector s = new PrefixSelector(prefix);
		return s;
	}
}