package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository.Injection;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Connector;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForAssociation;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.ModelForEvent;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;

public class ModelXml
{
	public static final String ROOT_ELEMENT_NAME = "model";

	public static Model read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");

		// The model applies either to a staticId, an association, a type or an event
		String staticId = r.getAttributeValue(null, "staticId");
		String association = r.getAttributeValue(null, "association");
		String type = r.getAttributeValue(null, "type");
		String event = r.getAttributeValue(null, "event");
		String eventInjection = r.getAttributeValue(null, "injection");

		Model m = null;
		if (event != null) m = new ModelForEvent(event, Injection.valueOf(eventInjection), id);
		else if (type != null) m = new ModelForType(type, id);
		else if (association != null) m = new ModelForAssociation(association, id);
		else m = new ModelForElement(staticId, id);

		m.setStage(DydXml.readAttributeStage(r));

		return m;
	}

	public static void write(XMLStreamWriter w, Model m) throws XMLStreamException
	{
		w.writeStartElement(ROOT_ELEMENT_NAME);
		DydXml.writeAttributeStage(w, m.getStage());
		w.writeAttribute("id", m.getId());

		if (m instanceof ModelForElement)
		{
			w.writeAttribute("staticId", ((ModelForElement) m).getStaticId());
		}
		else if (m instanceof ModelForAssociation)
		{
			w.writeAttribute("association", ((ModelForAssociation) m).getAssociation());
		}
		else if (m instanceof ModelForType)
		{
			w.writeAttribute("type", ((ModelForType) m).getType());
		}
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
