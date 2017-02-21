package org.power_systems_modelica.psm.ddr.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.power_systems_modelica.psm.commons.FileUtils;
import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

import eu.itesla_project.iidm.network.Identifiable;

public class DynamicDataRepositoryTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void testSample0() throws ConnectionException
	{
		String location = TEST_SAMPLES.resolve("12n").resolve("BDD").toString();
		DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create("DYD", location);
		ddr.connect();
		Identifiable<?> e = Mockito.mock(Identifiable.class);
		Mockito.when(e.getId()).thenReturn("CIVAU7CIVAU1_NGU_SM");

		// Query the dynamic data repository
		ModelicaModel m = ddr.getModelicaModel(e, Stage.SIMULATION);
		List<ModelicaDeclaration> ds = m.getDeclarations();
		List<ModelicaEquation> eqs = m.getEquations();

		// Check the result (types and identifiers of declarations)
		List<String> types = ds.stream().map(i -> i.getType()).collect(Collectors.toList());
		List<String> ids = ds.stream().map(i -> i.getId()).collect(Collectors.toList());
		List<String> expectedTypes = Arrays.asList(
				"Dynamo.Electrical.Machines.DYNModelM1S",
				"Dynamo.Electrical.Controls.Machines.VoltageRegulator.DYNModelVoltageRegulator",
				"Dynamo.Electrical.Controls.Machines.DYNModelGover1",
				"Dynamo.Electrical.Controls.Machines.DYNModelRPCL",
				"Dynamo.Electrical.Controls.Machines.DYNModelSetPoint",
				"Dynamo.Electrical.Controls.Machines.DYNModelSetPoint",
				"Dynamo.Electrical.Controls.Machines.DYNModelSetPoint");
		List<String> expectedIds = Arrays.asList(
				"M1S_CIVAU7CIVAU1_NGU_SM",
				"VR_CIVAU7CIVAU1_NGU_SM",
				"GOVER_CIVAU7CIVAU1_NGU_SM",
				"RPCL_CIVAU7CIVAU1_NGU_SM",
				"SP_LAI_CIVAU7CIVAU1_NGU_SM",
				"SP_LIS_CIVAU7CIVAU1_NGU_SM",
				"SP_LIR_CIVAU7CIVAU1_NGU_SM");
		assertThat(types, is(equalTo(expectedTypes)));
		assertThat(ids, is(equalTo(expectedIds)));

		// Only check parameters for first declaration
		List<ModelicaArgument> as = ds.get(0).getArguments();
		List<String> ans = as.stream().map(a -> a.getName()).collect(Collectors.toList());
		List<String> avs = as.stream().map(a -> a.getValue().toString())
				.collect(Collectors.toList());
		List<String> eans = Arrays.asList(
				"mD0Pu",
				"rQ1Pu",
				"lDPu",
				"SNomTfo",
				"nQSat",
				"pPuWLMDV",
				"HIn",
				"DIn",
				"lStatIn",
				"rStatIn",
				"mCanPu",
				"xTfoIn",
				"nDSat",
				"PNom",
				"rDPu",
				"rTfoIn",
				"PNomAlt",
				"mQ0Pu",
				"mDSatIn",
				"lQ2Pu",
				"lRotIn",
				"mQSatIn",
				"lQ1Pu",
				"rQ2Pu",
				"rRotIn",
				"SNom",
				"P0",
				"Q0",
				"UNwBase",
				"UNwNomTfo",
				"UNomGenTfo",
				"uBMac",
				"U0",
				"UTeta0");
		List<String> eavs = Arrays.asList(
				"2.351",
				"0.01428",
				"0.13605",
				"1500",
				"5.57",
				"1500",
				"6.3",
				"0",
				"0.219",
				"0.00357",
				"0",
				"0.136",
				"5.57",
				"1500",
				"0.01771",
				"0",
				"1500",
				"2.351",
				"0.084",
				"0.10067",
				"0.21881",
				"0.084",
				"0.54458",
				"0.01842",
				"0.00088",
				"1500",
				"14.5452",
				"-0.6953555",
				"380.0",
				"380.0",
				"24",
				"24",
				"1.08269473684",
				"0.152089867247");
		assertThat(ans, is(equalTo(eans)));
		assertThat(avs, is(equalTo(eavs)));

		// Check only first equations
		ModelicaConnect eq0 = (ModelicaConnect) (eqs.get(0));
		assertEquals("VR_CIVAU7CIVAU1_NGU_SM.EfdPU", eq0.getRef1());
		assertEquals("M1S_CIVAU7CIVAU1_NGU_SM.efd", eq0.getRef2());
		ModelicaConnect eq1 = (ModelicaConnect) (eqs.get(1));
		assertEquals("VR_CIVAU7CIVAU1_NGU_SM.UsPU", eq1.getRef1());
		assertEquals("M1S_CIVAU7CIVAU1_NGU_SM.UReg", eq1.getRef2());
		ModelicaConnect eq2 = (ModelicaConnect) (eqs.get(2));
		assertEquals("VR_CIVAU7CIVAU1_NGU_SM.QsPU", eq2.getRef1());
		assertEquals("M1S_CIVAU7CIVAU1_NGU_SM.Qs", eq2.getRef2());
	}

	@Test
	public void testIeee14() throws ConnectionException
	{
		testIeee("ieee14");
	}

	@Test
	public void testIeee30() throws ConnectionException
	{
		testIeee("ieee30");
	}

	@Test
	public void testIeee57() throws ConnectionException
	{
		testIeee("ieee57");
	}

	@Test
	public void testIeee118() throws ConnectionException
	{
		testIeee("ieee118");
	}

	private void testIeee(String case_) throws ConnectionException
	{
		String location = TEST_SAMPLES.resolve(case_).resolve("ddr").toString();
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
	public void testXmlReadWrite12n() throws ConnectionException, XMLStreamException, IOException
	{
		testXmlReadWrite(TEST_SAMPLES.resolve("12n").resolve("BDD").toString());
	}

	@Test
	public void testXmlReadWriteIeee14() throws ConnectionException, XMLStreamException, IOException
	{
		testXmlReadWrite(TEST_SAMPLES.resolve("ieee14").resolve("ddr").toString());
	}

	public void testXmlReadWrite(String location)
			throws ConnectionException, XMLStreamException, IOException
	{
		// We write the given ddr to a new location, read it again and compare the results

		DynamicDataRepository ddre = DynamicDataRepositoryMainFactory.create("DYD", location);
		ddre.connect();

		Path tmp = DATA_TMP.resolve("dyd-write");
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

	private static final Path	DATA			= Paths.get(System.getenv("PSM_DATA"));
	private static final Path	TEST_SAMPLES	= DATA.resolve("test");
	private static final Path	DATA_TMP		= DATA.resolve("tmp");
}
