package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class XmlUtil
{
	public static XMLStreamReader reader(Path file) throws XMLStreamException, IOException
	{
		InputStream i = Files.newInputStream(file);
		return XML_INPUT_FACTORY_SUPPLIER.get().createXMLStreamReader(i);
	}

	public static XMLStreamWriter writer(Path file) throws XMLStreamException, IOException
	{
		OutputStream o = Files.newOutputStream(file);
		return XML_OUTPUT_FACTORY_SUPPLIER.get().createXMLStreamWriter(o);
	}

	private static final Supplier<XMLInputFactory>	XML_INPUT_FACTORY_SUPPLIER	= Suppliers
			.memoize(XMLInputFactory::newInstance);

	private static final Supplier<XMLOutputFactory>	XML_OUTPUT_FACTORY_SUPPLIER	= Suppliers
			.memoize(XMLOutputFactory::newInstance);
}
