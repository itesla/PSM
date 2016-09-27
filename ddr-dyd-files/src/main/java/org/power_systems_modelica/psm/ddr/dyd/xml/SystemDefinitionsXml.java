package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.SystemDefinitions;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.xml.equations.EquationXml;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;

public class SystemDefinitionsXml
{
	public static final String SYSTEM_DEFINITIONS_ELEMENT_NAME = "system_definitions";

	public static SystemDefinitions read(XMLStreamReader r) throws XMLStreamException
	{
		final SystemDefinitions sd = new SystemDefinitions();
		XmlUtil.readUntilEndElement(SYSTEM_DEFINITIONS_ELEMENT_NAME, r, () -> {
			switch (r.getLocalName())
			{
			case ModelicaDeclarationXml.ELEMENT_NAME:
				sd.add(ModelicaDeclarationXml.read(r));
				break;
			case EquationXml.ELEMENT_NAME:
				sd.add(EquationXml.read(r));
				break;
			}
		});
		return sd;
	}

	public static void write(XMLStreamWriter w, SystemDefinitions sd)
			throws XMLStreamException
	{
		w.writeStartElement(SYSTEM_DEFINITIONS_ELEMENT_NAME);
		w.writeDefaultNamespace(XmlUtil.NAMESPACE);

		for (ModelicaDeclaration d : sd.getDeclarations())
			ModelicaDeclarationXml.write(w, d);
		for (Equation eq : sd.getEquations())
			EquationXml.write(w, eq);

		w.writeEndElement();
	}
}