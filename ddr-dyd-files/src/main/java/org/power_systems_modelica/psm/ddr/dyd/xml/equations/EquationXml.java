package org.power_systems_modelica.psm.ddr.dyd.xml.equations;

/*
 * #%L
 * Dynamic Data Repository on DYD files
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.dd.equations.Equal;
import org.power_systems_modelica.psm.dd.equations.Equation;
import org.power_systems_modelica.psm.dd.equations.UnparsedEquation;
import org.power_systems_modelica.psm.ddr.dyd.xml.DydXml;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
		if (eq instanceof Equal) EqualXml.write((Equal) eq, w);
		else if (eq instanceof UnparsedEquation)
		{
			w.writeStartElement(UNPARSED_EQUATION_ELEMENT_NAME);
			w.writeCharacters(((UnparsedEquation) eq).getText());
			w.writeEndElement();
		}
		w.writeEndElement();
	}
}
