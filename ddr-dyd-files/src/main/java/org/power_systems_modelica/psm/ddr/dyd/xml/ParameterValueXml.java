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

import org.power_systems_modelica.psm.dd.ParameterValue;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParameterValueXml
{
	public static final String	ROOT_ELEMENT_NAME	= "par";

	public static ParameterValue read(XMLStreamReader r)
	{
		String type = r.getAttributeValue(null, "type");
		String name = r.getAttributeValue(null, "name");
		String value = r.getAttributeValue(null, "value");
		String unit = r.getAttributeValue(null, "unit");
		String sisGeneric = r.getAttributeValue(null, "isGeneric");
		boolean isGeneric = IS_GENERIC_DEFAULT;
		if (sisGeneric != null) isGeneric = Boolean.valueOf(sisGeneric);
		ParameterValue p = new ParameterValue(type, unit, name, value);
		p.setGeneric(isGeneric);
		return p;
	}

	public static void write(XMLStreamWriter w, ParameterValue p) throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		if (p.getType() != null) w.writeAttribute("type", p.getType());
		if (p.getUnit() != null) w.writeAttribute("unit", p.getUnit());
		w.writeAttribute("name", p.getName());
		// TODO Proper serialization of p.getValue()?
		w.writeAttribute("value", p.getValue().toString());
		if (p.isGeneric() != IS_GENERIC_DEFAULT) w.writeAttribute("isGeneric",
				Boolean.toString(p.isGeneric()));
	}

	private static final boolean	IS_GENERIC_DEFAULT	= false;
}
