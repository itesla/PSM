package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.ParameterValue;
import org.power_systems_modelica.psm.ddr.dyd.Parameter;
import org.power_systems_modelica.psm.ddr.dyd.ParameterReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;

public class ParameterSetXml
{
	public static final String ROOT_ELEMENT_NAME = "set";

	public static ParameterSet read(XMLStreamReader r)
	{
		String id = r.getAttributeValue(null, "id");
		ParameterSet set = new ParameterSet(id);
		return set;
	}

	public static void write(XMLStreamWriter w, ParameterSet set) throws XMLStreamException
	{
		w.writeStartElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("id", set.getId());
		for (Parameter p : set.getParameters())
		{
			if (p instanceof ParameterValue)
				ParameterValueXml.write(w, (ParameterValue) p);
			else if (p instanceof ParameterReference)
				ParameterReferenceXml.write(w, (ParameterReference) p);
		}
		w.writeEndElement();
	}
}
