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

import org.power_systems_modelica.psm.dd.equations.Factors;
import org.power_systems_modelica.psm.dd.equations.Folding;
import org.power_systems_modelica.psm.dd.equations.Folding.Product;
import org.power_systems_modelica.psm.dd.equations.Folding.Sum;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class FoldingXml
{
	public static final String	SUM_ELEMENT_NAME		= "sum";
	public static final String	PRODUCT_ELEMENT_NAME	= "product";

	public static void write(Folding e, XMLStreamWriter w)
			throws XMLStreamException
	{
		String root = null;
		if (e instanceof Sum) root = SUM_ELEMENT_NAME;
		else if (e instanceof Product) root = PRODUCT_ELEMENT_NAME;
		w.writeStartElement(root);
		FactorsXml.write(e.getFactors(), w);
		w.writeEndElement();
	}

	public static Folding read(XMLStreamReader reader, String elementName)
			throws XMLStreamException
	{
		Factors factors = FactorsXml.read(reader, elementName);
		Folding f = null;
		switch (elementName)
		{
		case SUM_ELEMENT_NAME:
			f = new Sum(factors);
			break;
		case PRODUCT_ELEMENT_NAME:
			f = new Product(factors);
			break;
		}
		return f;
	}
}