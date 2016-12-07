package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equal;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.equations.UnparsedEquation;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class EquationXml
{
	public static final String	ELEMENT_NAME					= "equation";
	public static final String	UNPARSED_EQUATION_ELEMENT_NAME	= "unparsed";

	public static Equation read(XMLStreamReader reader) throws XMLStreamException
	{
		final Equation[] eq = new Equation[1];
		Stage stage = DydXml.readAttributeStage(reader);
		XmlUtil.readUntilEndElement(
				EquationXml.ELEMENT_NAME,
				reader,
				() -> {
					switch (reader.getLocalName())
					{
					case EqualXml.ELEMENT_NAME:
						eq[0] = EqualXml.read(reader);
						break;
					case EquationXml.UNPARSED_EQUATION_ELEMENT_NAME:
						String text = XmlUtil.readUntilEndElement(
								EquationXml.UNPARSED_EQUATION_ELEMENT_NAME, reader, null);
						eq[0] = new UnparsedEquation(text);
						break;
					}
				});
		eq[0].setStage(stage);
		return eq[0];
	}

	public static void write(XMLStreamWriter w, Equation eq)
			throws XMLStreamException
	{
		w.writeStartElement(ELEMENT_NAME);
		DydXml.writeAttributeStage(w, eq.getStage());
		if (eq instanceof Equal)
			EqualXml.write((Equal) eq, w);
		else if (eq instanceof UnparsedEquation)
		{
			w.writeStartElement(UNPARSED_EQUATION_ELEMENT_NAME);
			w.writeCharacters(((UnparsedEquation) eq).getText());
			w.writeEndElement();
		}
		w.writeEndElement();
	}
}
