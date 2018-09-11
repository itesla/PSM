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

import org.power_systems_modelica.psm.dd.equations.LogicalSelector;
import org.power_systems_modelica.psm.dd.equations.PrefixSelector;
import org.power_systems_modelica.psm.dd.equations.Selector;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class SelectorXml
{

	public static void write(Selector s, XMLStreamWriter w) throws XMLStreamException
	{
		if (s instanceof LogicalSelector) LogicalSelectorXml.write((LogicalSelector) s, w);
		else if (s instanceof PrefixSelector) PrefixSelectorXml.write((PrefixSelector) s, w);
	}

	public static Selector read(XMLStreamReader reader, String selectorName)
			throws XMLStreamException
	{
		Selector[] s = new Selector[1];
		switch (selectorName)
		{
		case LogicalSelectorXml.AND_ELEMENT_NAME:
		case LogicalSelectorXml.OR_ELEMENT_NAME:
			s[0] = LogicalSelectorXml.read(reader, selectorName);
			break;
		case PrefixSelectorXml.ELEMENT_NAME:
			s[0] = PrefixSelectorXml.read(reader);
		}
		return s[0];
	}
}