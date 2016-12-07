package org.power_systems_modelica.psm.ddr.dyd.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.ddr.dyd.Declaration;
import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;

public class DeclarationXml
{
	public static final String ELEMENT_NAME = "declaration";

	public static void write(XMLStreamWriter w, Declaration d) throws XMLStreamException
	{
		w.writeEmptyElement(ELEMENT_NAME);
		DydXml.writeAttributeStage(w, d.getStage());

		ModelicaDeclaration md = d.getModelicaDeclaration();
		w.writeAttribute("type", md.getType());
		w.writeAttribute("id", md.getId());
		if (md.getValue() != null) w.writeAttribute("value", md.getValue().toString());
		w.writeAttribute("isParameter", Boolean.toString(md.isParameter()));
	}

	public static Declaration read(XMLStreamReader r)
	{
		String type = r.getAttributeValue(null, "type");
		String id = r.getAttributeValue(null, "id");
		Object value = r.getAttributeValue(null, "value");
		boolean isParameter = Boolean.valueOf(r.getAttributeValue(null, "isParameter"));
		Annotation annotation = null;
		ModelicaDeclaration md = new ModelicaDeclaration(type, id, value, isParameter, annotation);

		Stage stage = DydXml.readAttributeStage(r);
		
		Declaration d = new Declaration(md, stage);
		return d;
	}
}
