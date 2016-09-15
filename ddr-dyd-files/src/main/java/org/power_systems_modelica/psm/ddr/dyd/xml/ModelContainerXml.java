package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelContainerXml
{
	public static final String ROOT_ELEMENT_NAME = "model_container";

	public static ModelContainer read(Path file)
			throws XMLStreamException, IOException
	{
		LOG.debug("read DYD file {}", file);
		if (XmlUtil.isValidationActive) validate(file);

		ModelContainer dyd = null;
		XMLStreamReader r = XmlUtil.reader(file);
		try
		{
			dyd = read(r);
		}
		finally
		{
			r.close();
		}
		return dyd;
	}

	public static ModelContainer read(XMLStreamReader r) throws XMLStreamException
	{
		ModelContainer dyd = new ModelContainer();
		Model m = null;
		while (r.hasNext())
		{
			switch (r.next())
			{
			case XMLEvent.START_ELEMENT:
				switch (r.getLocalName())
				{
				case ModelXml.ROOT_ELEMENT_NAME:
					m = ModelXml.read(r);
					dyd.add(m);
					break;
				case ComponentXml.ROOT_ELEMENT_NAME:
					Component component = ComponentXml.read(r);
					m.addComponent(component);
					break;
				case ConnectorXml.ROOT_ELEMENT_NAME:
					Connector connector = ConnectorXml.read(r);
					m.addConnector(connector);
					break;
				case ConnectionXml.ROOT_ELEMENT_NAME:
					Connection connection = ConnectionXml.read(r);
					m.addConnection(connection);
					break;
				}
				break;
			case XMLEvent.END_ELEMENT:
				switch (r.getLocalName())
				{
				case ModelXml.ROOT_ELEMENT_NAME:
					m = null;
					break;
				}
				break;
			}
		}
		return dyd;
	}

	public static void write(Path file, ModelContainer dyd) throws XMLStreamException, IOException
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
		if (XmlUtil.isValidationActive) validate(file);
	}

	public static void write(XMLStreamWriter w, ModelContainer dyd)
			throws XMLStreamException
	{
		w.writeStartDocument();
		w.writeStartElement(ROOT_ELEMENT_NAME);
		for (Model m : dyd.getModelDefinitions())
			ModelXml.write(w, m);
		w.writeEndElement();
		w.writeEndDocument();
		w.flush();
	}

	private static void validate(Path file)
	{
		XmlUtil.validate(file, "dyd.xsd");
	}

	private static final Logger LOG = LoggerFactory.getLogger(ModelContainerXml.class);
}
