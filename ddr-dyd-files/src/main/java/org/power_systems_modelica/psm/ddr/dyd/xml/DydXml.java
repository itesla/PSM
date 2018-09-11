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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.ddr.dyd.DydContent;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DydXml
{
	public static DydContent read(Path file)
			throws XMLStreamException, IOException
	{
		LOG.debug("read DYD file {}", file);
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);

		DydContent dyd = null;
		XMLStreamReader r = XmlUtil.reader(file);
		try
		{
			dyd = readDyd(r);
		}
		finally
		{
			r.close();
		}
		return dyd;
	}

	private static DydContent readDyd(XMLStreamReader r) throws XMLStreamException
	{
		while (r.hasNext())
		{
			switch (r.next())
			{
			case XMLEvent.START_ELEMENT:
				switch (r.getLocalName())
				{
				case ModelContainerXml.MODEL_CONTAINER_ELEMENT_NAME:
					return ModelContainerXml.read(r);
				}
			}
		}
		return null;
	}

	public static void write(Path file, DydContent dyd) throws XMLStreamException, IOException
	{
		LOG.debug("write DYD file {}", file);
		XMLStreamWriter w = XmlUtil.writer(file);
		try
		{
			write(w, dyd);
		}
		finally
		{
			w.close();
		}
		if (XmlUtil.isValidationActive) XmlUtil.validate(file);
	}

	public static void write(XMLStreamWriter w, DydContent dyd)
			throws XMLStreamException
	{
		w.writeStartDocument();

		if (dyd instanceof ModelContainer) ModelContainerXml.write(w, (ModelContainer) dyd);

		w.writeEndDocument();
		w.flush();
	}

	public static final Stage	DEFAULT_STAGE	= Stage.SIMULATION;

	public static void writeAttributeStage(XMLStreamWriter w, Stage stage)
			throws XMLStreamException
	{
		if (!stage.equals(DEFAULT_STAGE)) w.writeAttribute("stage", stage.name());
	}

	public static Stage readAttributeStage(XMLStreamReader r)
	{
		return Stage.valueOf(
				Optional.ofNullable(r.getAttributeValue(null, "stage"))
						.orElse(DEFAULT_STAGE.name()));
	}

	private static final Logger	LOG	= LoggerFactory.getLogger(DydXml.class);
}
