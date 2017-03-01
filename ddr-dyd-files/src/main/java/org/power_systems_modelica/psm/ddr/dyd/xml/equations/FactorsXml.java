package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.equations.Expression;
import org.power_systems_modelica.psm.ddr.dyd.equations.ExpressionList;
import org.power_systems_modelica.psm.ddr.dyd.equations.Factors;
import org.power_systems_modelica.psm.ddr.dyd.equations.ForAll;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

public class FactorsXml
{
	private static final String	FACTOR_ELEMENT_NAME	= "factor";

	public static Factors read(XMLStreamReader reader, String untilEnd) throws XMLStreamException
	{
		final Factors[] f = new Factors[1];
		XmlUtil.readUntilEndElement(untilEnd, reader, () -> {
			switch (reader.getLocalName())
			{
			case ForAllXml.ELEMENT_NAME:
				f[0] = ForAllXml.read(reader);
				break;
			case FactorsXml.FACTOR_ELEMENT_NAME:
				if (f[0] == null) f[0] = new ExpressionList();
				// Either a ForAll or an expression list, we can not have both elements as a Factors object
				assert (f[0] instanceof ExpressionList);
				ExpressionList exs = (ExpressionList) f[0];
				exs.add(ExpressionXml.read(reader, FactorsXml.FACTOR_ELEMENT_NAME));
				break;
			}
		});
		return f[0];
	}

	public static void write(Factors f, XMLStreamWriter w) throws XMLStreamException
	{
		if (f instanceof ForAll) ForAllXml.write((ForAll) f, w);
		else if (f instanceof ExpressionList)
		{
			for (Expression e : ((ExpressionList) f).get())
			{
				w.writeStartElement(FactorsXml.FACTOR_ELEMENT_NAME);
				ExpressionXml.write(e, w);
				w.writeEndElement();
			}
		}
	}
}