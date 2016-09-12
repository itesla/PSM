package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterSetContainerXml
{
	public static final String ROOT_ELEMENT_NAME = "parameters";

	public static ParameterSetContainer read(Path file) throws XMLStreamException, IOException
	{
		ParameterSetContainer container = null;
		LOG.debug("read PAR file {}", file);
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
				case ParameterSetXml.ROOT_ELEMENT_NAME:
					set = ParameterSetXml.read(r);
					container.add(set);
					break;
				case ParameterValueXml.ROOT_ELEMENT_NAME:
					set.add(ParameterValueXml.read(r));
					break;
				case ParameterReferenceXml.ROOT_ELEMENT_NAME:
					set.add(ParameterReferenceXml.read(r));
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
	}

	public static void write(XMLStreamWriter w, ParameterSetContainer container)
			throws XMLStreamException
	{
		w.writeStartDocument();
		w.writeStartElement(ROOT_ELEMENT_NAME);
		for (ParameterSet set : container.getSets())
			ParameterSetXml.write(w, set);
		w.writeEndElement();
		w.writeEndDocument();
		w.flush();
	}

	private static final Logger LOG = LoggerFactory.getLogger(ParameterSetContainerXml.class);
}
