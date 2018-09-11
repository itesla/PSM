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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.dd.Component;
import org.power_systems_modelica.psm.dd.Connection;
import org.power_systems_modelica.psm.dd.Interconnection;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.ModelForAssociation;
import org.power_systems_modelica.psm.dd.ModelForElement;
import org.power_systems_modelica.psm.dd.ModelForEvent;
import org.power_systems_modelica.psm.dd.ModelForEvent.Injection;
import org.power_systems_modelica.psm.dd.ModelForType;
import org.power_systems_modelica.psm.dd.StaticType;
import org.power_systems_modelica.psm.dd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.xml.equations.EquationXml;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelXml
{
	public static final String	ROOT_ELEMENT_NAME	= "model";

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
