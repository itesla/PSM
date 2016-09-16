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
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
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
		Model model = null;
		Component component = null;
		ParameterSet set = null;
		while (r.hasNext())
		{
			switch (r.next())
			{
			case XMLEvent.START_ELEMENT:
				switch (r.getLocalName())
				{
				case ModelXml.ROOT_ELEMENT_NAME:
					model = ModelXml.read(r);
					dyd.add(model);
					break;
				case ComponentXml.ROOT_ELEMENT_NAME:
					component = ComponentXml.read(r);
					set = null;
					model.addComponent(component);
					break;
				case ParameterValueXml.ROOT_ELEMENT_NAME:
					if (set == null)
					{
						// TODO Reorganize XML reading/writing using inner elements and adders
						set = new ParameterSet("inline");
						component.setParameterSet(set);
					}
					set.add(ParameterValueXml.read(r));
					break;
				case ParameterReferenceXml.ROOT_ELEMENT_NAME:
					if (set == null)
					{
						// TODO Reorganize XML reading/writing using inner elements and adders
						set = new ParameterSet("inline");
						component.setParameterSet(set);
					}
					set.add(ParameterReferenceXml.read(r));
					break;
				case ConnectorXml.ROOT_ELEMENT_NAME:
					Connector connector = ConnectorXml.read(r);
					model.addConnector(connector);
					break;
				case ConnectionXml.ROOT_ELEMENT_NAME:
					Connection connection = ConnectionXml.read(r);
					model.addConnection(connection);
					break;
				}
				break;
			case XMLEvent.END_ELEMENT:
				switch (r.getLocalName())
				{
				case ModelXml.ROOT_ELEMENT_NAME:
					model = null;
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
		w.writeDefaultNamespace("http://www.power_systems_on_modelica.org/schema/dyd/1_0");
		for (Model m : dyd.getModelDefinitions())
			ModelXml.write(w, m);
		w.writeEndElement();
		w.writeEndDocument();
		w.flush();
	}

	private static void validate(Path file)
	{
		// FIXME Parameter definition is duplicated between dyd and par xsd files
		XmlUtil.validate(file, "dyd.xsd");
	}

	private static final Logger LOG = LoggerFactory.getLogger(ModelContainerXml.class);
}
