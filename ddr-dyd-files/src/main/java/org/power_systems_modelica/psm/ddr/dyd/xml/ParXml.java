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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import org.power_systems_modelica.psm.dd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParXml
{
	public static final String ROOT_ELEMENT_NAME_AIA = "parameters";
	public static final String ROOT_ELEMENT_NAME_DYN = "parametersSet";

	public static ParameterSetContainer read(Path file) throws XMLStreamException, IOException
	{
		LOG.debug("read PAR file {}", file);
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);

		ParameterSetContainer container = null;
		XMLStreamReader r = XmlUtil.reader(file);
		try
		{
			container = read(r);
		}
		finally
		{
			r.close();
		}
		return container;
	}

	public static ParameterSetContainer read(XMLStreamReader r) throws XMLStreamException
	{
		ParameterSetContainer container = new ParameterSetContainer();
		ParameterSet set = null;
		while (r.hasNext())
		{
			switch (r.next())
			{
			case XMLEvent.START_ELEMENT:
				switch (r.getLocalName())
				{
				case ROOT_ELEMENT_NAME_AIA:
					container.setDynamo(false);
					break;
				case ROOT_ELEMENT_NAME_DYN:
					container.setDynamo(true);
					break;
				case ParameterSetXml.ROOT_ELEMENT_NAME:
					set = ParameterSetXml.read(r);
					container.add(set);
					break;
				case ParameterValueXml.ROOT_ELEMENT_NAME:
					set.add(ParameterValueXml.read(r));
					break;
				case ParameterReferenceXml.ROOT_ELEMENT_NAME_AIA:
					set.add(ParameterReferenceXml.readAia(r));
					break;
				case ParameterReferenceXml.ROOT_ELEMENT_NAME_DYN:
					set.add(ParameterReferenceXml.readDynamo(r));
					break;
				}
				break;
			case XMLEvent.END_ELEMENT:
				switch (r.getLocalName())
				{
				case ParameterSetXml.ROOT_ELEMENT_NAME:
					set = null;
					break;
				}
				break;
			}
		}
		return container;
	}

	public static void write(Path file, ParameterSetContainer container)
			throws XMLStreamException, IOException
	{
		LOG.debug("write PAR file {}", file);
		XMLStreamWriter w = XmlUtil.writer(file);
		try
		{
			write(w, container);
		}
		finally
		{
			w.close();
		}
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);
	}

	public static void write(XMLStreamWriter w, ParameterSetContainer container)
			throws XMLStreamException
	{
		w.writeStartDocument();
		if (container.isDynamo()) 
			w.writeStartElement(ROOT_ELEMENT_NAME_DYN);
		else
			w.writeStartElement(ROOT_ELEMENT_NAME_AIA);
		w.writeDefaultNamespace(XmlUtil.NAMESPACE);
		for (ParameterSet set : sortedSets(container.getSets()))
			ParameterSetXml.write(w, set, container.isDynamo());
		w.writeEndElement();
		w.writeEndDocument();
		w.flush();
	}

	private static Collection<ParameterSet> sortedSets(Collection<ParameterSet> sets)
	{
		Comparator<ParameterSet> byId = Comparator.comparing(ParameterSet::getId);
		return sets.stream().sorted(byId).collect(Collectors.toList());
	}

	private static final Logger LOG = LoggerFactory.getLogger(ParXml.class);
}
