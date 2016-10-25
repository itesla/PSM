package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.util.Optional;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository.Injection;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.ModelForEvent;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;

public class ModelXml
{
	public static final String ROOT_ELEMENT_NAME = "model";

	public static Model read(XMLStreamReader r)
	{
		boolean isInitialization = Boolean.valueOf(
				Optional.ofNullable(r.getAttributeValue(null, "isInitialization"))
						.orElse("false"));

		// The model applies either to a staticId, to a type or to an event

		String staticId = r.getAttributeValue(null, "staticId");
		String id = r.getAttributeValue(null, "id");

		String type = r.getAttributeValue(null, "type");

		String event = r.getAttributeValue(null, "event");
		String eventInjection = r.getAttributeValue(null, "injection");

		Model m = null;
		if (type != null) m = new ModelForType(type);
		else if (event != null) m = new ModelForEvent(event, Injection.valueOf(eventInjection));
		else m = new ModelForElement(id, staticId);
		m.setInitialization(isInitialization);

		return m;
	}

	public static void write(XMLStreamWriter w, Model m) throws XMLStreamException
	{
		w.writeStartElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("isInitialization", Boolean.toString(m.isInitialization()));
		
		if (m instanceof ModelForElement)
		{
			w.writeAttribute("staticId", ((ModelForElement) m).getStaticId());
			w.writeAttribute("id", ((ModelForElement) m).getId());
		}
		else if (m instanceof ModelForType)
			w.writeAttribute("type", ((ModelForType) m).getType());
		else if (m instanceof ModelForEvent)
		{
			w.writeAttribute("event", ((ModelForEvent) m).getEvent());
			w.writeAttribute("injection", ((ModelForEvent) m).getInjection().toString());
		}

		for (Component mc : m.getComponents())
			ComponentXml.write(w, mc);
		for (Connector mcr : m.getConnectors())
			ConnectorXml.write(w, mcr);
		for (Connection mcn : m.getConnections())
			ConnectionXml.write(w, mcn);

		w.writeEndElement();
	}
}
