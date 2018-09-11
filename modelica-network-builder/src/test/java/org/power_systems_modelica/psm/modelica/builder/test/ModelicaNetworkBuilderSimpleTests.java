package org.power_systems_modelica.psm.modelica.builder.test;

/*
 * #%L
 * Modelica network builder
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.NEW_LINE;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;

import com.powsybl.iidm.network.Bus;
import com.powsybl.iidm.network.BusbarSection;
import com.powsybl.iidm.network.Country;
import com.powsybl.iidm.network.EnergySource;
import com.powsybl.iidm.network.Generator;
import com.powsybl.iidm.network.Load;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.network.NetworkFactory;
import com.powsybl.iidm.network.Substation;
import com.powsybl.iidm.network.Switch;
import com.powsybl.iidm.network.TopologyKind;
import com.powsybl.iidm.network.VoltageLevel;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaNetworkBuilderSimpleTests
{
	@Test
	public void testBuildEmptyNetwork() throws Exception
	{
		Network n = Mockito.mock(Network.class);
		Mockito.when(n.getName()).thenReturn("mocked_network");

		DynamicDataRepository ddr = Mockito.mock(DynamicDataRepository.class);
		Mockito.when(ddr.getSystemModel(Mockito.any(Stage.class))).thenReturn(Optional.empty());
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument mo = moc.build(unresolved);
		assertTrue(unresolved.isEmpty());

		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		ModelicaTextPrinter.print(mo, out, false);
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
	public void testBuildSingleElement() throws Exception
	{
		// Build the Network
		Network n = createTestNetwork();

		// Mocking the Dynamic Data Repository for this network
		boolean useReferences = false;
		DynamicDataRepository ddr = mockDdr(n, useReferences);

		// Mocking the Modelica engine
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);

		// Build Modelica Document and obtain as text
		String converted = convertToModelica(n, ddr, me);

		String expected = String.join(NEW_LINE,
				"within ;",
				"model network1",
				"  BusModel dynamicBus1 (",
				"    V = 1.0,",
				"    A = 0.0",
				"    ) annotation (Placement(transformation()));",
				"  LoadModel dynamicLoad1 (",
				"    P = 10.0,",
				"    Q = 10.0",
				"    ) annotation (Placement(transformation()));",
				"  SwitchModel dynamicSwitch1 (",
				"",
				"    ) annotation (Placement(transformation()));",
				"equation",
				"  annotation (uses(Modelica(version=\"3.2.1\")));",
				"end network1;",
				"");
		assertEquals(expected, converted);
	}

	@Test
	public void testBuildSingleElementParameterReference() throws Exception
	{
		// Build the Network
		Network n = createTestNetwork();
		Bus bus = n.getBusBreakerView().getBuses().iterator().next();
		bus.setV(1.234f);

		// Mocking the Dynamic Data Repository for this network
		boolean useReferences = true;
		DynamicDataRepository ddr = mockDdr(n, useReferences);

		// Mocking the Modelica engine
		ModelicaEngine me = Mockito.mock(ModelicaEngine.class);

		// Build Modelica Document and obtain as text
		String converted = convertToModelica(n, ddr, me);

		String expected = String.join(NEW_LINE,
				"within ;",
				"model network1",
				"  BusModel dynamicBus1 (",
				"    V0 = 1.234,",
				"    A = 0.0",
				"    ) annotation (Placement(transformation()));",
				"  LoadModel dynamicLoad1 (",
				"    P = 10.0,",
				"    Q = 10.0",
				"    ) annotation (Placement(transformation()));",
				"  SwitchModel dynamicSwitch1 (",
				"",
				"    ) annotation (Placement(transformation()));",
				"equation",
				"  annotation (uses(Modelica(version=\"3.2.1\")));",
				"end network1;",
				"");
		assertEquals(expected, converted);
	}

	private String convertToModelica(Network n, DynamicDataRepository ddr, ModelicaEngine me)
			throws Exception
	{
		ModelicaSystemBuilder moc = new ModelicaSystemBuilder(ddr, n, me);
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument mo = moc.build(unresolved);
		StringWriter sout = new StringWriter();
		PrintWriter out = new PrintWriter(sout);
		ModelicaTextPrinter.print(mo, out, false);
		out.flush();
		out.close();
		String converted = sout.toString();
		return converted;
	}

	private static DynamicDataRepository mockDdr(Network n, boolean useReferences)
	{
		Bus bus = n.getBusBreakerView().getBuses().iterator().next();
		Load load = n.getLoad("load1");
		Switch sw = bus.getVoltageLevel().getBusBreakerView()
				.getSwitch("voltageLevel1Breaker1");

		DynamicDataRepository ddr = Mockito.mock(DynamicDataRepository.class);
		Mockito.when(ddr.getSystemModel(Mockito.any(Stage.class))).thenReturn(Optional.empty());
		ModelicaArgument V;
		if (useReferences) V = new ModelicaArgumentReference("V0", "IIDM", "V");
		else V = new ModelicaArgument("V", "1.0");
		ModelicaArgument A = new ModelicaArgument("A", "0.0");
		ModelicaDeclaration dbusi = new ModelicaDeclaration(
				"BusModel",
				"dynamicBus1",
				Arrays.asList(V, A),
				false,
				null);
		ModelicaModel dbus = new ModelicaModel("DM_bus1");
		// dbus.setStaticId("bus1");
		String bid = bus.getVoltageLevel().getId() + "::" + bus.getId();
		dbus.setStaticId(bid);
		dbus.addDeclarations(Arrays.asList(dbusi));
		ModelicaArgument P = new ModelicaArgument("P", "10.0");
		ModelicaArgument Q = new ModelicaArgument("Q", "10.0");
		ModelicaDeclaration dloadd = new ModelicaDeclaration(
				"LoadModel",
				"dynamicLoad1",
				Arrays.asList(P, Q),
				false,
				null);
		ModelicaModel dload = new ModelicaModel("DM_load1");
		dload.setStaticId("load1");
		dload.addDeclarations(Arrays.asList(dloadd));
		ModelicaDeclaration dswd = new ModelicaDeclaration(
				"SwitchModel",
				"dynamicSwitch1",
				Arrays.asList(),
				false,
				null);
		ModelicaModel dsw = new ModelicaModel("DM_switch1");
		dsw.setStaticId("voltageLevel1Breaker1");
		dsw.addDeclarations(Arrays.asList(dswd));
		// Mocked ddr will only return dynamic model for the first bus
		Mockito.when(ddr.getModelicaModel(bus, Stage.SIMULATION)).thenReturn(dbus);
		Mockito.when(ddr.getModelicaModel(load, Stage.SIMULATION)).thenReturn(dload);
		Mockito.when(ddr.getModelicaModel(sw, Stage.SIMULATION)).thenReturn(dsw);

		return ddr;
	}

	private static Network createTestNetwork()
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
		topology1.newBreaker()
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
		topology1.newDisconnector()
				.setId("load1Disconnector1")
				.setOpen(false)
				.setNode1(load1.getTerminal().getNodeBreakerView().getNode())
				.setNode2(3)
				.add();
		topology1.newDisconnector()
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
		topology1.newDisconnector()
				.setId("generator1Disconnector1")
				.setOpen(false)
				.setNode1(generator1.getTerminal().getNodeBreakerView().getNode())
				.setNode2(6)
				.add();
		topology1.newDisconnector()
				.setId("generator1Breaker1")
				.setOpen(false)
				.setNode1(6)
				.setNode2(voltageLevel1BusbarSection2.getTerminal().getNodeBreakerView().getNode())
				.add();
		return network;
	}
}
