package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.StaticType;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository.Injection;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Interconnection;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForAssociation;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.ModelForEvent;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.xml.equations.EquationXml;

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
		String eventAppliesTo = r.getAttributeValue(null, "appliesTo");

		Model m = null;
		if (event != null)
		{
			ModelForEvent mev = new ModelForEvent(event, Injection.valueOf(eventInjection), id);
			if (eventAppliesTo != null) mev.setAppliesTo(StaticType.valueOf(eventAppliesTo));
			m = mev;
		}
		else if (type != null) m = new ModelForType(StaticType.valueOf(type), id);
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
			w.writeAttribute("type", ((ModelForType) m).getType().name());
		}
		else if (m instanceof ModelForEvent)
		{
			ModelForEvent mev = (ModelForEvent) m;
			w.writeAttribute("event", mev.getEvent());
			w.writeAttribute("injection", mev.getInjection().name());
			if (mev.getAppliesTo() != null)
				w.writeAttribute("appliesTo", mev.getAppliesTo().name());
		}

		for (Component mc : m.getComponents())
			ComponentXml.write(w, mc);
		for (Connection mcn : m.getConnections())
			ConnectionXml.write(w, mcn);
		for (Interconnection mcr : m.getInterconnections())
			InterconnectionXml.write(w, mcr);
		for (Equation meq : m.getOtherEquations())
			EquationXml.write(w, meq);

		w.writeEndElement();
	}
}
