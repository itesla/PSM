package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
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

	static void validate(Path file)
	{
		String schemaName = DYD_SCHEMA_RESOUCE;
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

	private static final String						DYD_SCHEMA_RESOUCE			= "/xsd/dyd.xsd";
	private static final Supplier<XMLInputFactory>	XML_INPUT_FACTORY_SUPPLIER	= Suppliers
			.memoize(XMLInputFactory::newInstance);
	private static final Supplier<XMLOutputFactory>	XML_OUTPUT_FACTORY_SUPPLIER	= Suppliers
			.memoize(XMLOutputFactory::newInstance);
	private static final String						INDENTATION					= "    ";
	private static final Logger						LOG							= LoggerFactory
			.getLogger(XmlUtil.class);
}
