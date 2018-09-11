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

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.dd.equations.LogicalSelector;
import org.power_systems_modelica.psm.dd.equations.Selector;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class LogicalSelectorXml
{
	public static final String	AND_ELEMENT_NAME	= "and";
	public static final String	OR_ELEMENT_NAME		= "or";

	public static void write(LogicalSelector l, XMLStreamWriter w)
			throws XMLStreamException
	{
		String root = null;
		if (l instanceof LogicalSelector.And) root = AND_ELEMENT_NAME;
		else if (l instanceof LogicalSelector.Or) root = OR_ELEMENT_NAME;
		w.writeStartElement(root);
		for (Selector s : l.getSelectors())
			SelectorXml.write(s, w);
		w.writeEndElement();
	}

	public static LogicalSelector read(XMLStreamReader reader, String elementName)
			throws XMLStreamException
	{
		List<Selector> selectors = readTerms(reader, elementName);
		LogicalSelector s = null;
		switch (elementName)
		{
		case AND_ELEMENT_NAME:
			s = new LogicalSelector.And(selectors);
			break;
		case OR_ELEMENT_NAME:
			s = new LogicalSelector.Or(selectors);
			break;
		}
		return s;
	}

	static List<Selector> readTerms(XMLStreamReader reader, String elementName)
			throws XMLStreamException
	{
		List<Selector> selectors = new ArrayList<>();
		XmlUtil.readUntilEndElement(elementName, reader, () -> {
			String elementName1 = reader.getLocalName();
			Selector s1 = SelectorXml.read(reader, elementName1);
			selectors.add(s1);
		});
		return selectors;
	}
}