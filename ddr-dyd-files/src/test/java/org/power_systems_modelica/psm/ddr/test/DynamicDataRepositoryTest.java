package org.power_systems_modelica.psm.ddr.test;

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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.power_systems_modelica.psm.commons.FileUtils;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

import com.powsybl.iidm.network.Identifiable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DynamicDataRepositoryTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void testIeee14() throws ConnectionException
	{	
		testIeee("test", "ieee14");
	}

	@Test
	public void testIeee30() throws ConnectionException
	{
		testIeee("test", "ieee30");
	}

	@Test
	public void testIeee57() throws ConnectionException
	{
		testIeee("test", "ieee57");
	}

	@Test
	public void testIeee118() throws ConnectionException
	{
		testIeee("test", "ieee118");
	}

	private void testIeee(String catalog, String case_) throws ConnectionException
	{
		String location = PathUtils.DATA.resolve(catalog).resolve(case_).resolve("ddr").toString();
		DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create("DYD", location);
		ddr.connect();
		Collection<String> events = ddr.getEvents();
		assertTrue(events.contains("BusFault"));
		assertTrue(events.contains("LineFault"));
		assertTrue(events.contains("LoadVariation"));
		assertTrue(events.contains("BankModification"));
		assertTrue(events.contains("LineOpenBothSides"));
		assertTrue(events.contains("LineOpenSendingSide"));
		assertTrue(events.contains("LineOpenReceiverSide"));
		assertTrue(events.contains("GeneratorVSetpointModification"));
		// Check that all parameters that are references to EVENT data source have units
		events.forEach(
				e -> ddr.getEventParameters(e)
						.forEach(p -> {
							assertTrue(p.getUnit() != null && !p.getUnit().equals(""));
						}));
	}

	@Test
	public void testXmlReadWriteIeee14() throws ConnectionException, XMLStreamException, IOException
	{
		testXmlReadWrite(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString());
	}

	public void testXmlReadWrite(String location)
			throws ConnectionException, XMLStreamException, IOException
	{
		// We write the given ddr to a new location, read it again and compare the results

		DynamicDataRepository ddre = DynamicDataRepositoryMainFactory.create("DYD", location);
		ddre.connect();

		Path tmp = PathUtils.DATA_TMP.resolve("dyd-write");
		if (Files.isDirectory(tmp)) FileUtils.deleteDirectory(tmp);
		
		String location1 = tmp.toString();
		assertTrue(ddre instanceof DynamicDataRepositoryDydFiles);
		DynamicDataRepositoryDydFiles dydse = (DynamicDataRepositoryDydFiles) ddre;
		dydse.setLocation(location1);
		dydse.write();

		DynamicDataRepository ddra = DynamicDataRepositoryMainFactory.create("DYD", location1);
		ddra.connect();

		// Compare names of events defined
		assertEquals(ddre.getEvents(), ddra.getEvents());

		// Compare model definitions are the same
		assertTrue(ddra instanceof DynamicDataRepositoryDydFiles);
		DynamicDataRepositoryDydFiles dydsa = (DynamicDataRepositoryDydFiles) ddra;
		DynamicDataRepositoryTestUtil.assertSameModelDefinitions(
				dydse.getAllModelDefinitions(),
				dydsa.getAllModelDefinitions());
	}
}
