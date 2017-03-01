package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Parameter;
import org.power_systems_modelica.psm.ddr.dyd.ParameterReference;
import org.power_systems_modelica.psm.ddr.dyd.ParameterValue;

public class ParameterXml
{
	public static void write(XMLStreamWriter w, Parameter p) throws XMLStreamException
	{
		if (p instanceof ParameterValue) ParameterValueXml.write(w, (ParameterValue) p);
		else if (p instanceof ParameterReference) ParameterReferenceXml.write(w,
				(ParameterReference) p);
	}
}
