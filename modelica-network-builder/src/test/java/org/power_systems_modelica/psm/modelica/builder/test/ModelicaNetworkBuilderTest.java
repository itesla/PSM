package org.power_systems_modelica.psm.modelica.builder.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;

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
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		ModelicaDocument mo = moc.build();

		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		boolean includePsmDummies = false;
		mop.print(out, includePsmDummies);
		out.flush();
		out.close();
		String converted = sout.toString();

		String expected = String.join("\n",
				"within ;",
				"model mocked_network",
				"equation",
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
		ModelicaArgument V = new ModelicaArgument("V", "1.0");
		ModelicaArgument A = new ModelicaArgument("A", "0.0");
		ModelicaDeclaration dbusi = new ModelicaDeclaration(
				"BusModel",
				"dynamicBus1",
				Arrays.asList(V, A),
				false,
				null);
		ModelicaModel dbus = new ModelicaModel("DM_bus1");
		dbus.addDeclarations(Arrays.asList(dbusi));
		// Mocked ddr will only return dynamic model for the first bus
		Mockito.when(ddr.getModelicaModel(firstBus)).thenReturn(dbus);

		// Mocking the Modelica engine
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);

		// Build Modelica
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		ModelicaDocument mo = moc.build();
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		boolean includePsmDummies = false;
		mop.print(out, includePsmDummies);
		out.flush();
		out.close();
		String converted = sout.toString();

		String expected = String.join("\n",
				"within ;",
				"model network1",
				"  BusModel dynamicBus1 (",
				"    V = 1.0,",
				"    A = 0.0",
				"    ) annotation (Placement(transformation()));",
				"equation",
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
		Mockito.when(ddr.getModelicaModel(firstBus)).thenReturn(dbus);

		// Mocking the Modelica engine
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);

		// Build Modelica
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		ModelicaDocument mo = moc.build();
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		boolean includePsmDummies = false;
		mop.print(out, includePsmDummies);
		out.flush();
		out.close();
		String converted = sout.toString();

		String expected = String.join("\n",
				"within ;",
				"model network1",
				"  BusModel dynamicBus1 (",
				"    V0 = 1.234,",
				"    A = 0.0",
				"    ) annotation (Placement(transformation()));",
				"equation",
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
}
