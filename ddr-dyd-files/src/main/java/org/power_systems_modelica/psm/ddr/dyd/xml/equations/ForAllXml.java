package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.ExpressionTemplate;
import org.power_systems_modelica.psm.ddr.dyd.equations.ForAll;
import org.power_systems_modelica.psm.ddr.dyd.equations.Selector;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class ForAllXml
{
	public static final String	ELEMENT_NAME	= "forAll";

	public static ForAll read(XMLStreamReader reader) throws XMLStreamException
	{
		Selector[] s = new Selector[1];
		ExpressionTemplate[] t = new ExpressionTemplate[1];
		XmlUtil.readUntilEndElement(ELEMENT_NAME, reader, () -> {
			switch (reader.getLocalName())
			{
			case TemplateXml.ELEMENT_NAME:
				t[0] = TemplateXml.read(reader);
				break;
			// FIXME Must be a selector ... should we check it ???
			// FIXME We have already read the start element
			default:
				s[0] = SelectorXml.read(reader, reader.getLocalName());
				break;
			}
		});
		ForAll forAll = new ForAll(s[0], t[0]);
		return forAll;
	}

	public static void write(ForAll f, XMLStreamWriter w) throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		SelectorXml.write(f.getSelector(), w);
		TemplateXml.write(f.getTemplate(), w);
		w.writeEndElement();
	}
}