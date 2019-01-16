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
	public static final String	ROOT_ELEMENT_NAME_AIA	= "ref";
	public static final String	ROOT_ELEMENT_NAME_DYN	= "reference";
	public static final String	STATIC_REF_NAME			= "staticRef";

	public static ParameterReference readAia(XMLStreamReader r)
	{
		String name = r.getAttributeValue(null, "name");
		String dataSource = r.getAttributeValue(null, "dataSource");
		String sourceName = r.getAttributeValue(null, "sourceName");
		String unit = r.getAttributeValue(null, "unit");
		return new ParameterReference(null, name, unit, dataSource, sourceName);
	}

	public static ParameterReference readDynamo(XMLStreamReader r)
	{
		String type = r.getAttributeValue(null, "type");
		String name = r.getAttributeValue(null, "name");
		String dataSource = r.getAttributeValue(null, "origData");
		String sourceName = r.getAttributeValue(null, "origName");
		String unit = r.getAttributeValue(null, "unit");
		return new ParameterReference(type, name, unit, dataSource, sourceName);
	}

	public static ParameterReference readStaticRef(XMLStreamReader r)
	{
		String var = r.getAttributeValue(null, "var");
		String staticVar = r.getAttributeValue(null, "staticVar");
		return new ParameterReference(null, var, null, "IIDM", staticVar);
	}

	public static ParameterReference blackBoxParameter(XMLStreamReader r)
	{
		String name = r.getAttributeValue(null, "id");
		String lib = r.getAttributeValue(null, "lib");
		String dataSource = r.getAttributeValue(null, "parFile");
		String sourceName = r.getAttributeValue(null, "parId");

		return new ParameterReference(null, name, lib, dataSource, sourceName);
	}

	public static void write(XMLStreamWriter w, ParameterReference p, boolean dynamo) throws XMLStreamException
	{
		if (dynamo) writeDynamo(w,p);
		else writeAia(w,p);
	}
	
	public static void writeAia(XMLStreamWriter w, ParameterReference p) throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME_AIA);
		w.writeAttribute("name", p.getName());
		if (p.getUnit() != null) w.writeAttribute("unit", p.getUnit());
		w.writeAttribute("dataSource", p.getDataSource());
		w.writeAttribute("sourceName", p.getSourceName());
	}

	public static void writeDynamo(XMLStreamWriter w, ParameterReference p) throws XMLStreamException
	{
		if (p.getType() == null && p.getDataSource() == null && p.getUnit() == null)
		{
			w.writeEmptyElement(STATIC_REF_NAME);
			w.writeAttribute("var", p.getName());
			w.writeAttribute("staticVar", p.getSourceName());
		}
		else
		{
			w.writeEmptyElement(ROOT_ELEMENT_NAME_DYN);
			w.writeAttribute("type", p.getType());
			w.writeAttribute("name", p.getName());
			if (p.getUnit() != null) w.writeAttribute("unit", p.getUnit());
			w.writeAttribute("origData", p.getDataSource());
			w.writeAttribute("origName", p.getSourceName());
		}
	}
}
