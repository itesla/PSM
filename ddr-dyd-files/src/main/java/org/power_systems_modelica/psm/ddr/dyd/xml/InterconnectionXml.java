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
		String targetModel2 = r.getAttributeValue(null, "targetModel2");
		String targetName2 = r.getAttributeValue(null, "targetName2");

		Interconnection ic = new Interconnection(
				name, componentId, componentVar,
				targetModel, targetName,
				targetModel2, targetName2);

		return ic;
	}

	public static void write(XMLStreamWriter w, Interconnection ic)
			throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);

		if (ic.getName() != null) w.writeAttribute("name", ic.getName());

		if (ic.getComponentId() != null) w.writeAttribute("componentId", ic.getComponentId());
		if (ic.getComponentVar() != null) w.writeAttribute("componentVar", ic.getComponentVar());

		if (ic.getTargetModel() != null) w.writeAttribute("targetModel", ic.getTargetModel());
		if (ic.getTargetName() != null) w.writeAttribute("targetName", ic.getTargetName());

		if (ic.getTargetModel2() != null) w.writeAttribute("targetModel2", ic.getTargetModel2());
		if (ic.getTargetName2() != null) w.writeAttribute("targetName2", ic.getTargetName2());
	}
}
