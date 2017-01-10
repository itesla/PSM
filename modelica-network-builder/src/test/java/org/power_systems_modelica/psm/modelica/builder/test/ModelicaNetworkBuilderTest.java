package org.power_systems_modelica.psm.modelica.builder.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.NEW_LINE;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.BusbarSection;
import eu.itesla_project.iidm.network.Country;
import eu.itesla_project.iidm.network.EnergySource;
import eu.itesla_project.iidm.network.Generator;
import eu.itesla_project.iidm.network.Load;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.NetworkFactory;
import eu.itesla_project.iidm.network.Substation;
import eu.itesla_project.iidm.network.Switch;
import eu.itesla_project.iidm.network.TopologyKind;
import eu.itesla_project.iidm.network.VoltageLevel;

public class ModelicaNetworkBuilderTest
{
	@Test
	public void testBuildEmptyNetwork() throws IOException
	{
		Network n = Mockito.mock(Network.class);
		Mockito.when(n.getName()).thenReturn("mocked_network");

		DynamicDataRepository ddr = Mockito.mock(DynamicDataRepository.class);
		Mockito.when(ddr.getSystemModel(Mockito.any(Stage.class))).thenReturn(Optional.empty());
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		ModelicaDocument mo = moc.build();

		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		mop.print(out);
		out.flush();
		out.close();
		String converted = sout.toString();

		String expected = String.join(NEW_LINE,
				"within ;",
				"model mocked_network",
				"equation",
				"  annotation (uses(Modelica(version=\"3.2.1\")));",
				"end mocked_network;",
				"");
		assertEquals(expected, converted);
	}

	@Test
	public void testBuildSingleElement() throws IOException
	{
		// Build the Network
		Network n = createTestNetwork();
		Bus firstBus = n.getBusBreakerView().getBuses().iterator().next();

		// Mocking the Dynamic Data Repository
		DynamicDataRepository ddr = Mockito.mock(DynamicDataRepository.class);
		Mockito.when(ddr.getSystemModel(Mockito.any(Stage.class))).thenReturn(Optional.empty());
		ModelicaArgument V = new ModelicaArgument("V", "1.0");
		ModelicaArgument A = new ModelicaArgument("A", "0.0");
		ModelicaDeclaration dbusi = new ModelicaDeclaration(
				"BusModel",
				"dynamicBus1",
				Arrays.asList(V, A),
				false,
				null);
		ModelicaModel dbus = new ModelicaModel("DM_bus1");
		dbus.setStaticId("bus1");
		dbus.addDeclarations(Arrays.asList(dbusi));
		// Mocked ddr will only return dynamic model for the first bus
		Mockito.when(ddr.getModelicaModel(firstBus, Stage.SIMULATION)).thenReturn(dbus);

		// Mocking the Modelica engine
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);

		// Build Modelica
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		ModelicaDocument mo = moc.build();
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		mop.print(out);
		out.flush();
		out.close();
		String converted = sout.toString();

		String expected = String.join(NEW_LINE,
				"within ;",
				"model network1",
				"  BusModel dynamicBus1 (",
				"    V = 1.0,",
				"    A = 0.0",
				"    ) annotation (Placement(transformation()));",
				"equation",
				"  annotation (uses(Modelica(version=\"3.2.1\")));",
				"end network1;",
				"");
		assertEquals(expected, converted);
	}

	@Test
	public void testBuildSingleElementParameterReference() throws IOException
	{
		// Build the Network
		Network n = createTestNetwork();
		Bus firstBus = n.getBusBreakerView().getBuses().iterator().next();
		firstBus.setV(1.234f);

		// Mocking the Dynamic Data Repository
		DynamicDataRepository ddr = Mockito.mock(DynamicDataRepository.class);
		Mockito.when(ddr.getSystemModel(Mockito.any(Stage.class))).thenReturn(Optional.empty());
		ModelicaArgument V = new ModelicaArgumentReference("V0", "IIDM", "V");
		ModelicaArgument A = new ModelicaArgument("A", "0.0");
		ModelicaDeclaration dbusi = new ModelicaDeclaration(
				"BusModel",
				"dynamicBus1",
				Arrays.asList(V, A),
				false,
				null);
		ModelicaModel dbus = new ModelicaModel("DM_bus1");
		String bid = firstBus.getVoltageLevel().getId() + "::" + firstBus.getId();
		dbus.setStaticId(bid);
		dbus.addDeclarations(Arrays.asList(dbusi));
		// Mocked ddr will only return dynamic model for the first bus
		Mockito.when(ddr.getModelicaModel(firstBus, Stage.SIMULATION)).thenReturn(dbus);

		// Mocking the Modelica engine
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);

		// Build Modelica
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		ModelicaDocument mo = moc.build();
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		mop.print(out);
		out.flush();
		out.close();
		String converted = sout.toString();

		String expected = String.join(NEW_LINE,
				"within ;",
				"model network1",
				"  BusModel dynamicBus1 (",
				"    V0 = 1.234,",
				"    A = 0.0",
				"    ) annotation (Placement(transformation()));",
				"equation",
				"  annotation (uses(Modelica(version=\"3.2.1\")));",
				"end network1;",
				"");
		assertEquals(expected, converted);
	}

	@SuppressWarnings("unused")
	public static Network createTestNetwork()
	{
		Network network = NetworkFactory.create("network1", "test");
		Substation substation1 = network.newSubstation()
				.setId("substation1")
				.setCountry(Country.ES)
				.setTso("TSO1")
				.setGeographicalTags("region1")
				.add();
		VoltageLevel voltageLevel1 = substation1.newVoltageLevel()
				.setId("voltageLevel1")
				.setNominalV(400)
				.setTopologyKind(TopologyKind.NODE_BREAKER)
				.add();
		VoltageLevel.NodeBreakerView topology1 = voltageLevel1.getNodeBreakerView();
		topology1.setNodeCount(10);
		BusbarSection voltageLevel1BusbarSection1 = topology1.newBusbarSection()
				.setId("voltageLevel1BusbarSection1")
				.setNode(0)
				.add();
		BusbarSection voltageLevel1BusbarSection2 = topology1.newBusbarSection()
				.setId("voltageLevel1BusbarSection2")
				.setNode(1)
				.add();
		Switch voltageLevel1Breaker1 = topology1.newBreaker()
				.setId("voltageLevel1Breaker1")
				.setRetained(true)
				.setOpen(false)
				.setNode1(voltageLevel1BusbarSection1.getTerminal().getNodeBreakerView().getNode())
				.setNode2(voltageLevel1BusbarSection2.getTerminal().getNodeBreakerView().getNode())
				.add();
		Load load1 = voltageLevel1.newLoad()
				.setId("load1")
				.setNode(2)
				.setP0(10)
				.setQ0(3)
				.add();
		Switch load1Disconnector1 = topology1.newDisconnector()
				.setId("load1Disconnector1")
				.setOpen(false)
				.setNode1(load1.getTerminal().getNodeBreakerView().getNode())
				.setNode2(3)
				.add();
		Switch load1Breaker1 = topology1.newDisconnector()
				.setId("load1Breaker1")
				.setOpen(false)
				.setNode1(3)
				.setNode2(voltageLevel1BusbarSection1.getTerminal().getNodeBreakerView().getNode())
				.add();
		Generator generator1 = voltageLevel1.newGenerator()
				.setId("generator1")
				.setEnergySource(EnergySource.NUCLEAR)
				.setMinP(200)
				.setMaxP(900)
				.setVoltageRegulatorOn(true)
				.setTargetP(900)
				.setTargetV(380)
				.setNode(5)
				.add();
		generator1.newReactiveCapabilityCurve()
				.beginPoint().setP(200).setMinQ(300).setMaxQ(500).endPoint()
				.beginPoint().setP(900).setMinQ(300).setMaxQ(500).endPoint()
				.add();
		Switch generator1Disconnector1 = topology1.newDisconnector()
				.setId("generator1Disconnector1")
				.setOpen(false)
				.setNode1(generator1.getTerminal().getNodeBreakerView().getNode())
				.setNode2(6)
				.add();
		Switch generator1Breaker1 = topology1.newDisconnector()
				.setId("generator1Breaker1")
				.setOpen(false)
				.setNode1(6)
				.setNode2(voltageLevel1BusbarSection2.getTerminal().getNodeBreakerView().getNode())
				.add();
		return network;
	}

	@Test
	public void buildIeee14() throws IOException, ConnectionException
	{
		testBuild(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ieee14/ddr",
				"itesla/ieee14bus_no_lf.mo",
				14,
				5);
	}

	@Test
	public void buildIeee30() throws IOException, ConnectionException
	{
		testBuild(
				"ieee30",
				"ieee30bus_EQ.xml",
				"ieee30/ddr",
				"itesla/ieee30bus_no_lf.mo",
				30,
				6);
	}

	@Test
	public void buildIeee57() throws IOException, ConnectionException
	{
		testBuild(
				"ieee57",
				"ieee57bus_EQ.xml",
				"ieee57/ddr",
				"itesla/ieee57bus_no_lf.mo",
				57,
				7);
	}

	@Test
	public void buildIeee118() throws IOException, ConnectionException
	{
		testBuild(
				"ieee118",
				"ieee118bus_EQ.xml",
				"ieee118/ddr",
				"itesla/ieee118bus_no_lf.mo",
				118,
				54);
	}

	@Test
	public void buildSmallCase1() throws IOException, ConnectionException
	{
		testBuild(
				"smallcase1",
				"case1_EQ.xml",
				"smallcase1/ddr",
				"itesla/case1_no_lf.mo",
				3,
				2); // There are one generator and one fixed injection but in IIDM both are Generators.
	}

	@Test
	public void buildSmallCase2() throws IOException, ConnectionException
	{
		testBuild(
				"smallcase2",
				"case2_EQ.xml",
				"smallcase2/ddr",
				"itesla/case2_no_lf.mo",
				3,
				1);
	}

	@Test // FIXME Fixed injections are not exported to the Modelica file.
	public void buildSmallCase3() throws IOException, ConnectionException
	{
		testBuild(
				"smallcase3",
				"case3_EQ.xml",
				"smallcase3/ddr",
				"itesla/case3_no_lf.mo",
				3,
				2); // There are one generator and one fixed injection but in IIDM both are Generators.
	}

	@Test
	public void build7buses() throws IOException, ConnectionException
	{
		testBuild(
				"7buses",
				"CIM_7buses_EQ.xml",
				"7buses/ddr",
				"itesla/CIM_7buses_no_lf.mo",
				7,
				3);
	}

	@Test
	public void buildNordic32() throws IOException, ConnectionException
	{
		testBuild(
				"Nordic32",
				"Nordic32_EQ.xml",
				"Nordic32/ddr",
				"itesla/Nordic32_no_lf.mo",
				52,
				20);
	}

	public void testBuild(
			String foldername,
			String casename,
			String ddrname,
			String expectedmoname,
			int expectedNumBuses,
			int expectedNumGenerators)
			throws IOException, ConnectionException
	{
		// TODO Use ShrinkWrap file system for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		Path cim = folder.resolve(casename);
		String ddrLocation = TEST_SAMPLES.resolve(ddrname).toString();
		String fakeInit = TEST_SAMPLES.resolve(ddrname).resolve("fake_init.csv").toString();
		String name = foldername;
		Path output = DATA_TMP.resolve("moBuilder_output_" + name + ".mo");
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("moBuilder_init" + name);
		Files.createDirectories(modelicaEngineWorkingDir);

		Network n = StaticNetworkImporter.import_(cim);
		assertNotNull(n);

		DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create("DYD", ddrLocation);
		ddr.connect();
		ModelicaEngine me = ModelicaEngineMainFactory.create("Fake");
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString());
		config.setParameter("fakeModelicaEngineResults", fakeInit);
		me.configure(config);
		ModelicaSystemBuilder builder = new ModelicaSystemBuilder(ddr, n, me);
		boolean onlyMainConnectedComponent = true;
		builder.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
		ModelicaDocument mo = builder.build();
		assertNotNull(mo);

		ModelicaTextPrinter printer = new ModelicaTextPrinter(mo);
		boolean includePsmAnnotations = false;
		printer.setIncludePsmAnnotations(includePsmAnnotations);
		try (PrintWriter out = new PrintWriter(output.toFile());)
		{
			printer.print(out);
		}

		assertEquals(expectedNumBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, n.getGeneratorCount());

		Path expected = folder.resolve(expectedmoname);
		Path actual = output;

		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}
}
