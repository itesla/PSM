package org.power_systems_modelica.psm.ddr.dyd.xml;

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
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.dd.Parameter;
import org.power_systems_modelica.psm.dd.ParameterReference;
import org.power_systems_modelica.psm.dd.ParameterValue;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ParameterXml
{
	public static void write(XMLStreamWriter w, Parameter p) throws XMLStreamException
	{
		if (p instanceof ParameterValue) ParameterValueXml.write(w, (ParameterValue) p);
		else if (p instanceof ParameterReference) ParameterReferenceXml.write(w,
				(ParameterReference) p);
	}
}
