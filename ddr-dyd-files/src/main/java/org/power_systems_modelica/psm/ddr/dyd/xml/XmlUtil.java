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
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import javanet.staxutils.IndentingXMLStreamWriter;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class XmlUtil
{
	public static boolean	isValidationActive	= false;
	public static boolean	isIndentActive		= true;

	public static XMLStreamReader reader(Path file) throws XMLStreamException, IOException
	{
		InputStream i = Files.newInputStream(file);
		return XML_INPUT_FACTORY_SUPPLIER.get().createXMLStreamReader(i);
	}

	public static XMLStreamWriter writer(Path file) throws XMLStreamException, IOException
	{
		OutputStream o = Files.newOutputStream(file);
		XMLStreamWriter writer = XML_OUTPUT_FACTORY_SUPPLIER.get().createXMLStreamWriter(o,
				StandardCharsets.UTF_8.toString());
		if (isIndentActive)
		{
			IndentingXMLStreamWriter indentingWriter = new IndentingXMLStreamWriter(writer);
			indentingWriter.setIndent(INDENTATION);
			writer = indentingWriter;
		}
		return writer;
	}

	public static void validate(Path file)
	{
		String schemaName = DYD_AIA_SCHEMA_RESOUCE;
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source sschema = new StreamSource(XmlUtil.class.getResourceAsStream(schemaName));
		try (InputStream is = Files.newInputStream(file))
		{
			Source xml = new StreamSource(Files.newInputStream(file));
			Schema schema = factory.newSchema(sschema);
			Validator validator = schema.newValidator();
			validator.validate(xml);
			LOG.info("validated {} against schema {}",
					file.toAbsolutePath().toString(),
					schemaName);
		}
		catch (SAXException | IOException e)
		{
			LOG.error("validating {} against schema {}, reason {}",
					file.toAbsolutePath().toString(),
					schemaName,
					e.getMessage());
			validateDynamo(file);
		}
	}

	public static void validateDynamo(Path file)
	{
		String schemaName = DYD_DYN_SCHEMA_RESOUCE;
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source sschema = new StreamSource(XmlUtil.class.getResourceAsStream(schemaName));
		try (InputStream is = Files.newInputStream(file))
		{
			Source xml = new StreamSource(Files.newInputStream(file));
			Schema schema = factory.newSchema(sschema);
			Validator validator = schema.newValidator();
			validator.validate(xml);
			LOG.info("validated {} against schema {}",
					file.toAbsolutePath().toString(),
					schemaName);
		}
		catch (SAXException | IOException e)
		{
			LOG.error("validating {} against schema {}, reason {}",
					file.toAbsolutePath().toString(),
					schemaName,
					e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public interface XmlEventHandler
	{
		void onStartElement() throws XMLStreamException;
	}

	public static String readUntilEndElement(String endElementName, XMLStreamReader reader,
			XmlEventHandler eventHandler) throws XMLStreamException
	{
		String text = null;
		int event;
		while (!((event = reader.next()) == XMLStreamConstants.END_ELEMENT
				&& reader.getLocalName().equals(endElementName)))
		{
			text = null;
			switch (event)
			{
			case XMLStreamConstants.START_ELEMENT:
				if (eventHandler != null)
				{
					eventHandler.onStartElement();
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				text = reader.getText();
				break;
			}
		}
		return text;
	}

	public static final String						NAMESPACE					= "http://www.power_systems_on_modelica.org/schema/dyd/1_0";
	private static final String						DYD_AIA_SCHEMA_RESOUCE			= "/xsd/dyd_aia.xsd";
	private static final String						DYD_DYN_SCHEMA_RESOUCE			= "/xsd/dyd_dyn.xsd";
	private static final Supplier<XMLInputFactory>	XML_INPUT_FACTORY_SUPPLIER	= Suppliers
			.memoize(XMLInputFactory::newInstance);
	private static final Supplier<XMLOutputFactory>	XML_OUTPUT_FACTORY_SUPPLIER	= Suppliers
			.memoize(XMLOutputFactory::newInstance);
	private static final String						INDENTATION					= "    ";
	private static final Logger						LOG							= LoggerFactory
			.getLogger(XmlUtil.class);
}
