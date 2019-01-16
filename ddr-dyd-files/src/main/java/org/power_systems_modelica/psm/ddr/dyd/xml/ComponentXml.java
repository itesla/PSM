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
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.ModelForElement;
import org.power_systems_modelica.psm.dd.Parameter;
import org.power_systems_modelica.psm.dd.ParameterReference;
import org.power_systems_modelica.psm.dd.ParameterSet;
import org.power_systems_modelica.psm.dd.ParameterSetReference;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ComponentXml
{
	public static final String	ROOT_ELEMENT_NAME_AIA	= "component";
	public static final String	ROOT_ELEMENT_NAME_DYN	= "unitDynamicModel";
	public static final String	BLACKBOX_MODEL_NAME		= "blackBoxModel";
	public static final String	MACRO_CONNECTOR_NAME	= "macroConnector";

	public static Component readAia(XMLStreamReader r)
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

	public static Component readDynamo(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		String type = r.getAttributeValue(null, "name");
		Component c = new Component(id, type);

		String initName = r.getAttributeValue(null, "initName");
		if (initName != null) c.setInitName(initName);
		
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

	public static Component readDynamoTemplate(XMLStreamReader r) {
		String id = r.getAttributeValue(null, "templateId");
		String type = id + ".so";
		Component c = new Component(id, type);

		String parFile = r.getAttributeValue(null, "parFile");
		String parId = r.getAttributeValue(null, "parId");
		ParameterSetReference pref = null;
		if (parFile != null && parId != null) pref = new ParameterSetReference(parFile, parId);
		if (pref != null) c.setParameterSetReference(pref);

		return c;
	}

	public static Component readBlackBoxModel(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		if (!ModelicaUtil.containsOmegaRef(id) && !id.contains("SysData")) return null;
		
		String type = r.getAttributeValue(null, "lib");
		Component c = new Component(id, type);
		
		String parFile = r.getAttributeValue(null, "parFile");
		String parId = r.getAttributeValue(null, "parId");
		ParameterSetReference pref = null;
		if (parFile != null && parId != null) pref = new ParameterSetReference(parFile, parId);
		if (pref != null) c.setParameterSetReference(pref);

		return c;
	}

	public static void write(XMLStreamWriter w, Component c, boolean dynamo) throws XMLStreamException
	{
		ParameterSet set = c.getParameterSet();
		boolean isEmptyElement = set == null ||
				set.getParameters() == null ||
				set.getParameters().isEmpty();

		if (isEmptyElement) 
		{
			if (dynamo)
			{
				if (c.getLib() != null) w.writeEmptyElement(BLACKBOX_MODEL_NAME);
				else w.writeEmptyElement(ROOT_ELEMENT_NAME_DYN);
			}
			else w.writeEmptyElement(ROOT_ELEMENT_NAME_AIA);
		}
		else 
		{
			if (dynamo)
			{
				if (c.getLib() != null) w.writeStartElement(BLACKBOX_MODEL_NAME);
				else w.writeStartElement(ROOT_ELEMENT_NAME_DYN);
			}
			else w.writeStartElement(ROOT_ELEMENT_NAME_AIA);
		}
		if (dynamo) w.writeAttribute("name", c.getType());
		else w.writeAttribute("type", c.getType());
		if (c.getId() != null) w.writeAttribute("id", c.getId());
		if (c.getLib() != null) w.writeAttribute("lib", c.getLib());
		if (set != null)
		{
			for (Parameter p : set.getParameters())
				ParameterXml.write(w, p, dynamo);
		}
		else
		{
			ParameterSetReference pref = c.getParameterSetReference();
			String initName = c.getInitName();
			if (initName != null) w.writeAttribute("initName", initName);
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
