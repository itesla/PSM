package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import org.power_systems_modelica.psm.ddr.dyd.DydContent;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.power_systems_modelica.psm.ddr.dyd.SystemDefinitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DydXml
{
	public static DydContent read(Path file)
			throws XMLStreamException, IOException
	{
		LOG.debug("read DYD file {}", file);
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);

		DydContent dyd = null;
		XMLStreamReader r = XmlUtil.reader(file);
		try
		{
			dyd = readDyd(r);
		}
		finally
		{
			r.close();
		}
		return dyd;
	}

	private static DydContent readDyd(XMLStreamReader r) throws XMLStreamException
	{
		while (r.hasNext())
		{
			switch (r.next())
			{
			case XMLEvent.START_ELEMENT:
				switch (r.getLocalName())
				{
				case ModelContainerXml.MODEL_CONTAINER_ELEMENT_NAME:
					return ModelContainerXml.read(r);
				case SystemDefinitionsXml.SYSTEM_DEFINITIONS_ELEMENT_NAME:
					return SystemDefinitionsXml.read(r);
				}
			}
		}
		return null;
	}

	public static void write(Path file, DydContent dyd) throws XMLStreamException, IOException
	{
		LOG.debug("write DYD file {}", file);
		XMLStreamWriter w = XmlUtil.writer(file);
		try
		{
			write(w, dyd);
		}
		finally
		{
			w.close();
		}
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);
	}

	public static void write(XMLStreamWriter w, DydContent dyd)
			throws XMLStreamException
	{
		w.writeStartDocument();

		if (dyd instanceof ModelContainer)
			ModelContainerXml.write(w, (ModelContainer) dyd);
		else if (dyd instanceof SystemDefinitions)
			SystemDefinitionsXml.write(w, (SystemDefinitions) dyd);

		w.writeEndDocument();
		w.flush();
	}

	private static final Logger LOG = LoggerFactory.getLogger(DydXml.class);
}
