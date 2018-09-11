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

import org.power_systems_modelica.psm.dd.Parameter;
import org.power_systems_modelica.psm.dd.ParameterSet;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParameterSetXml
{
	public static final String	ROOT_ELEMENT_NAME	= "set";

	public static ParameterSet read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		ParameterSet set = new ParameterSet(id);
		return set;
	}

	public static void write(XMLStreamWriter w, ParameterSet set) throws XMLStreamException
	{
		w.writeStartElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("id", set.getId());
		for (Parameter p : set.getParameters())
			ParameterXml.write(w, p);
		w.writeEndElement();
	}
}
