package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.ParameterReference;

public class ParameterReferenceXml
{
	public static final String ROOT_ELEMENT_NAME = "ref";

	public static ParameterReference read(XMLStreamReader r)
	{
		String name = r.getAttributeValue(null, "name");
		String dataSource = r.getAttributeValue(null, "dataSource");
		String sourceName = r.getAttributeValue(null, "sourceName");
		return new ParameterReference(name, dataSource, sourceName);
	}

	public static void write(XMLStreamWriter w, ParameterReference p) throws XMLStreamException
	{
		w.writeEmptyElement(ROOT_ELEMENT_NAME);
		w.writeAttribute("name", p.getName());
		w.writeAttribute("dataSource", p.getDataSource());
		w.writeAttribute("sourceName", p.getSourceName());
	}
}
