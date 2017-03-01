package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.PrefixSelector;

public class PrefixSelectorXml
{
	public static final String	ELEMENT_NAME	= "startsWith";

	public static void write(PrefixSelector s, XMLStreamWriter w) throws XMLStreamException
	{
		w.writeEmptyElement(ELEMENT_NAME);
		w.writeAttribute("prefix", s.getPrefix());
	}

	public static PrefixSelector read(XMLStreamReader reader)
	{
		String prefix = reader.getAttributeValue(null, "prefix");
		PrefixSelector s = new PrefixSelector(prefix);
		return s;
	}
}