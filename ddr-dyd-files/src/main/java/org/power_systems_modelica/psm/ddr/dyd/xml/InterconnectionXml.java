package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Interconnection;

public class InterconnectionXml
{
	public static final String ROOT_ELEMENT_NAME = "interconnection";

	public static Interconnection read(XMLStreamReader r)
	{
		String name = r.getAttributeValue(null, "name");

		String componentId = r.getAttributeValue(null, "componentId");
		String componentVar = r.getAttributeValue(null, "componentVar");

		String targetModel = r.getAttributeValue(null, "targetModel");
		String targetName = r.getAttributeValue(null, "targetName");

		Interconnection ic;
		// TODO These are in fact two different types of objects!!!
		if (targetModel != null && targetName != null)
			ic = new Interconnection(componentId, componentVar, targetModel, targetName);
		else
			ic = new Interconnection(name, componentId, componentVar);
		return ic;
	}

	public static void write(XMLStreamWriter w, Interconnection ic)
			throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		if (ic.getName() != null) w.writeAttribute("name", ic.getName());
		if (ic.getComponentId() != null) w.writeAttribute("componentId", ic.getComponentId());
		w.writeAttribute("componentVar", ic.getComponentVar());
		if (ic.getTargetModel() != null) w.writeAttribute("targetModel", ic.getTargetModel());
		if (ic.getTargetName() != null)
			w.writeAttribute("targetName", ic.getTargetName());
	}
}
