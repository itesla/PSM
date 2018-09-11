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

import org.power_systems_modelica.psm.dd.ParameterReference;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParameterReferenceXml
{
	public static final String	ROOT_ELEMENT_NAME	= "ref";

	public static ParameterReference read(XMLStreamReader r)
	{
		String name = r.getAttributeValue(null, "name");
		String dataSource = r.getAttributeValue(null, "dataSource");
		String sourceName = r.getAttributeValue(null, "sourceName");
		String unit = r.getAttributeValue(null, "unit");
		return new ParameterReference(name, unit, dataSource, sourceName);
	}

	public static void write(XMLStreamWriter w, ParameterReference p) throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("name", p.getName());
		if (p.getUnit() != null) w.writeAttribute("unit", p.getUnit());
		w.writeAttribute("dataSource", p.getDataSource());
		w.writeAttribute("sourceName", p.getSourceName());
	}
}
