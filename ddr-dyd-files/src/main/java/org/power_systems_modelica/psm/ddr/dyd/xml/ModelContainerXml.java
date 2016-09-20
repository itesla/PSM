package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
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
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);

		ModelContainer dyd = null;
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

	public static ModelContainer read(XMLStreamReader r)
	{
		String sinit = r.getAttributeValue(null, "isInitialization");
		boolean isInitialization = sinit == null ? false : Boolean.valueOf(sinit);
		ModelContainer m = new ModelContainer(isInitialization);
		return m;
	}

	private static ModelContainer readDyd(XMLStreamReader r) throws XMLStreamException
	{
		ModelContainer dyd = null;
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
				case ModelContainerXml.ROOT_ELEMENT_NAME:
					dyd = ModelContainerXml.read(r);
					break;
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
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);
	}

	public static void write(XMLStreamWriter w, ModelContainer dyd)
			throws XMLStreamException
	{
		w.writeStartDocument();
		w.writeStartElement(ROOT_ELEMENT_NAME);
		w.writeDefaultNamespace("http://www.power_systems_on_modelica.org/schema/dyd/1_0");
		w.writeAttribute("isInitialization", Boolean.toString(dyd.isInitialization()));

		// Sort models before writing
		for (Model m : sortedModels(dyd.getModelDefinitions()))
			ModelXml.write(w, m);

		w.writeEndElement();
		w.writeEndDocument();
		w.flush();
	}

	private static Collection<Model> sortedModels(Collection<Model> models)
	{
		Comparator<Model> byType, byId;
		byType = Comparator.comparing(ModelContainerXml::getType);
		byId = Comparator.comparing(Model::getId);
		return models.stream().sorted(byType.thenComparing(byId)).collect(Collectors.toList());
	}

	private static String getType(Model m)
	{
		if (m instanceof ModelForType) return ((ModelForType) m).getType();
		return "~";
	}

	private static final Logger LOG = LoggerFactory.getLogger(ModelContainerXml.class);
}
