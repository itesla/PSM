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

import org.power_systems_modelica.psm.dd.Component;
import org.power_systems_modelica.psm.dd.Parameter;
import org.power_systems_modelica.psm.dd.ParameterSet;
import org.power_systems_modelica.psm.dd.ParameterSetReference;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ComponentXml
{
	public static final String	ROOT_ELEMENT_NAME	= "component";

	public static Component read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		String type = r.getAttributeValue(null, "type");
		Component c = new Component(id, type);

		String parFile = r.getAttributeValue(null, "parFile");
		String parId = r.getAttributeValue(null, "parId");
		ParameterSetReference pref = null;
		if (parFile != null && parId != null) pref = new ParameterSetReference(parFile, parId);
		if (pref != null) c.setParameterSetReference(pref);

		String svalue = r.getAttributeValue(null, "value");
		if (svalue != null) c.setValue(svalue);
		boolean isParameter = IS_PARAMETER_DEFAULT;
		String sIsParameter = r.getAttributeValue(null, "isParameter");
		if (sIsParameter != null) isParameter = Boolean.valueOf(sIsParameter);
		c.setParameter(isParameter);

		return c;
	}

	public static void write(XMLStreamWriter w, Component c) throws XMLStreamException
	{
		ParameterSet set = c.getParameterSet();
		boolean isEmptyElement = set == null ||
				set.getParameters() == null ||
				set.getParameters().isEmpty();

		if (isEmptyElement) w.writeEmptyElement(ROOT_ELEMENT_NAME);
		else w.writeStartElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("type", c.getType());
		if (c.getId() != null) w.writeAttribute("id", c.getId());
		if (set != null)
		{
			for (Parameter p : set.getParameters())
				ParameterXml.write(w, p);
		}
		else
		{
			ParameterSetReference pref = c.getParameterSetReference();
			if (pref != null)
			{
				w.writeAttribute("parFile", pref.getContainer());
				w.writeAttribute("parId", pref.getSet());
			}

			Object value = c.getValue();
			if (value != null) w.writeAttribute("value", value.toString());
			if (c.isParameter() != IS_PARAMETER_DEFAULT)
				w.writeAttribute("isParameter", Boolean.toString(c.isParameter()));
		}

		if (!isEmptyElement) w.writeEndElement();
	}

	private static final boolean	IS_PARAMETER_DEFAULT	= false;
}
