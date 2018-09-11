package org.power_systems_modelica.psm.modelica.events.test;

/*
 * #%L
 * Add events to existing Modelica network models
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.dd.Event;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.events.ModelicaEventAdder;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaEventAdderTest
{
	
	@Test
	public void addTwoBusFaultsToOneDevice() throws Exception
	{
		Network network = importCase("smallcase1", "case1_EQ.xml");
		
		List<Event> events = new ArrayList<Event>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("R", "0.5");
		params.put("X", "0.5");
		params.put("startTime", "0.5");
		params.put("endTime", "0.5");
		Event ev = new Event("BusFault", network.getIdentifiable("_GRID_____TN"), params);
//		ev.setInstance("0");
		events.add(ev);
		
		params = new HashMap<String, Object>();
		params.put("R", "0.5");
		params.put("X", "0.5");
		params.put("startTime", "1.1");
		params.put("endTime", "1.3");
		ev = new Event("BusFault", network.getIdentifiable("_GRID_____TN"), params);
//		ev.setInstance("1");
		events.add(ev);
//		String events = new StringBuilder(100)
//				.append("BusFault")
//				.append(",")
//				.append("_GRID_____TN")
//				.append(",")
//				.append("R=0.5")
//				.append(",")
//				.append("X=0.5")
//				.append(",")
//				.append("startTime=0.5")
//				.append(",")
//				.append("endTime=0.8")
//				.append("\n")
//				.append("BusFault")
//				.append(",")
//				.append("_GRID_____TN")
//				.append(",")
//				.append("R=0.5")
//				.append(",")
//				.append("X=0.5")
//				.append(",")
//				.append("startTime=1.1")
//				.append(",")
//				.append("endTime=1.3")
//				.append("\n")
//				.toString();
		
		
		ModelicaDocument mo = addEvents(network, "smallcase1", "case1_EQ.xml", "ddr", events, 3, 2, 2, 2, false);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		ModelicaModel mb = models.get("_GRID_____TN");
		String busId = "bus__GRID_____TN";
		String faultId = "pwFault__GRID_____TN_0";
		assertTrue(mb.getDeclarations().stream()
				.filter(d -> d.getType().endsWith("PwFault"))
				.findFirst()
				.isPresent());
		assertTrue(mb.getEquations().stream()
				.filter(eq -> eq instanceof ModelicaConnect)
				.filter(eq -> {
					ModelicaConnect eqc = ((ModelicaConnect) eq);
					String id1 = ModelicaUtil.ref2idvar(eqc.getRef1())[0];
					String id2 = ModelicaUtil.ref2idvar(eqc.getRef2())[0];
					return id1.equals(busId) && id2.equals(faultId) ||
							id1.equals(faultId) && id1.equals(busId);
				})
				.findFirst()
				.isPresent());
		
		String faultId2 = "pwFault__GRID_____TN_1";
		assertTrue(mb.getDeclarations().stream()
				.filter(d -> d.getType().endsWith("PwFault"))
				.findFirst()
				.isPresent());
		assertTrue(mb.getEquations().stream()
				.filter(eq -> eq instanceof ModelicaConnect)
				.filter(eq -> {
					ModelicaConnect eqc = ((ModelicaConnect) eq);
					String id1 = ModelicaUtil.ref2idvar(eqc.getRef1())[0];
					String id2 = ModelicaUtil.ref2idvar(eqc.getRef2())[0];
					return id1.equals(busId) && id2.equals(faultId2) ||
							id1.equals(faultId2) && id1.equals(busId);
				})
				.findFirst()
				.isPresent());
	}
	
	@Test
	public void addEventsIeee14BusFault() throws Exception
	{
		addEventsIeee14BusFault(false);
	}

	@Test
	public void addEventsIeee14BusFaultReread() throws Exception
	{
		addEventsIeee14BusFault(true);
	}
	
	private void addEventsIeee14BusFault(boolean reread) throws Exception
	{
		Network network = importCase("ieee14", "ieee14bus_EQ.xml");
		
		List<Event> events = new ArrayList<Event>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("R", "0.5");
		params.put("X", "0.5");
		params.put("startTime", "20.0");
		params.put("endTime", "20.2");
		Event ev = new Event("BusFault", network.getIdentifiable("_BUS___10_TN"), params);
		events.add(ev);
		
//		String events = new StringBuilder(100)
//				.append("BusFault")
//				.append(",")
//				.append("_BUS___10_TN")
//				.append(",")
//				.append("R=0.5")
//				.append(",")
//				.append("X=0.5")
//				.append(",")
//				.append("startTime=20.0")
//				.append(",")
//				.append("endTime=20.2")
//				.append("\n")
//				.toString();
		
		ModelicaDocument mo = addEvents(network, "ieee14", "ieee14bus_EQ.xml", "ddr", events, 14, 5, 1, 1,
				reread);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		ModelicaModel mb = models.get("_BUS___10_TN");
		String busId = "bus__BUS___10_TN";
		String faultId = "pwFault__BUS___10_TN_0";
		assertTrue(mb.getDeclarations().stream()
				.filter(d -> d.getType().endsWith("PwFault"))
				.findFirst()
				.isPresent());
		assertTrue(mb.getEquations().stream()
				.filter(eq -> eq instanceof ModelicaConnect)
				.filter(eq -> {
					ModelicaConnect eqc = ((ModelicaConnect) eq);
					String id1 = ModelicaUtil.ref2idvar(eqc.getRef1())[0];
					String id2 = ModelicaUtil.ref2idvar(eqc.getRef2())[0];
					return id1.equals(busId) && id2.equals(faultId) ||
							id1.equals(faultId) && id1.equals(busId);
				})
				.findFirst()
				.isPresent());
	}

	@Test
	public void addEventsIeee14LineFault() throws Exception
	{
		addEventsIeee14LineFault(false);
	}

	@Test
	public void addEventsIeee14LineFaultReread() throws Exception
	{
		addEventsIeee14LineFault(true);
	}

	private void addEventsIeee14LineFault(boolean reread) throws Exception
	{
		Network network = importCase("ieee14", "ieee14bus_EQ.xml");
		
		List<Event> events = new ArrayList<Event>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("faultLocalization", "0.7");
		params.put("Rfault", "0.01");
		params.put("Xfault", "0.001");
		params.put("startTime", "200");
		params.put("endTime", "400");
		Event ev = new Event("LineFault", network.getIdentifiable("_BUS___10-BUS___11-1_AC"), params);
		events.add(ev);
		
//		String events = new StringBuilder(100)
//				.append("LineFault")
//				.append(",")
//				.append("_BUS___10-BUS___11-1_AC")
//				.append(",")
//				.append("faultLocalization=0.7")
//				.append(",")
//				.append("Rfault=0.01")
//				.append(",")
//				.append("Xfault=0.001")
//				.append(",")
//				.append("startTime=200")
//				.append(",")
//				.append("endTime=400")
//				.append("\n")
//				.toString();
		ModelicaDocument mo = addEvents(network, "ieee14", "ieee14bus_EQ.xml", "ddr", events, 14, 5, 0, 0,
				reread);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		assertTrue(models.get("_BUS___10_BUS___11_1_AC").getDeclarations()
				.get(0)
				.getType()
				.endsWith("LineFault"));
	}

	@Test
	public void addEvents7busesVSetpoint() throws Exception
	{
		addEvents7busesVSetpoint(false);
	}

	@Test
	public void addEvents7busesVSetpointReread() throws Exception
	{
		addEvents7busesVSetpoint(true);
	}

	private void addEvents7busesVSetpoint(boolean reread) throws Exception
	{
		Network network = importCase("7buses", "M7buses_EQ.xml");
		
		List<Event> events = new ArrayList<Event>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", "0.1");
		params.put("Vset", "1.0");		
		Event ev = new Event("GeneratorVSetpointModification", network.getIdentifiable("_FSSV.T11_SM"), params);
		events.add(ev);
		
//		String events = new StringBuilder(100)
//				.append("GeneratorVSetpointModification")
//				.append(",")
//				.append("_FSSV.T11_SM")
//				.append(",")
//				.append("startTime=0.1")
//				.append(",")
//				.append("Vset=1.0")
//				.append("\n")
//				.toString();
		ModelicaDocument mo = addEvents(network, "7buses", "M7buses_EQ.xml", "ddr", events, 7, 3, 1, 1,
				false);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		assertEquals(5, models.get("_FSSV_T11_SM").getDeclarations().size());
	}

	@Test
	public void addEvents7busesVSetPointReRead() throws Exception
	{
		Network network = importCase("7buses", "M7buses_EQ.xml");
		
		List<Event> events = new ArrayList<Event>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", "0.1");
		params.put("Vset", "1.0");		
		Event ev = new Event("GeneratorVSetpointModification", network.getIdentifiable("_FSSV.T11_SM"), params);
		events.add(ev);
		
//		String events = new StringBuilder(100)
//				.append("GeneratorVSetpointModification")
//				.append(",")
//				.append("_FSSV.T11_SM")
//				.append(",")
//				.append("startTime=0.1")
//				.append(",")
//				.append("Vset=1.0")
//				.append("\n")
//				.toString();
		ModelicaDocument mo0 = addEvents(network, "7buses", "M7buses_EQ.xml", "ddr", events, 7, 3, 1, 1,
				false);
		
		network = importCase("7buses", "M7buses_EQ.xml");
		
		events = new ArrayList<Event>();
		params = new HashMap<String, Object>();
		params.put("startTime", "0.1");
		params.put("Vset", "1.0");		
		ev = new Event("GeneratorVSetpointModification", network.getIdentifiable("_FSSV.T11_SM"), params);
		events.add(ev);
		
		ModelicaDocument mo1 = addEvents(network, "7buses", "M7buses_EQ.xml", "ddr", events, 7, 3, 1, 1,
				true);
		Path output0 = DATA_TMP.resolve("event_adder_7buses_vset0.mo");
		Path output1 = DATA_TMP.resolve("event_adder_7buses_vset1.mo");
		boolean printPsmAnnotations = true;
		ModelicaTextPrinter.print(mo0, output0, printPsmAnnotations);
		ModelicaTextPrinter.print(mo1, output1, printPsmAnnotations);
		ModelicaTestUtil.assertEqualsNormalizedModelicaText(output0, output1);
	}

	@Test
	public void addEventsIeee14LoadVariation() throws Exception
	{
		addEventsIeee14LoadVariation(false);
	}

	@Test
	public void addEventsIeee14LoadVariationReread() throws Exception
	{
		addEventsIeee14LoadVariation(true);
	}

	private void addEventsIeee14LoadVariation(boolean reread) throws Exception
	{
		Network network = importCase("ieee14", "ieee14bus_EQ.xml");
		
		List<Event> events = new ArrayList<Event>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("P", "9.0");
		params.put("Q", "1.0");
		params.put("startTime", "20.0");
		Event ev = new Event("LoadVariation", network.getIdentifiable("_LOAD__10_EC"), params);
		events.add(ev);
		
//		String events = new StringBuilder(100)
//				.append("LoadVariation")
//				.append(",")
//				.append("_LOAD__10_EC")
//				.append(",")
//				.append("P=9.0")
//				.append(",")
//				.append("Q=1.0")
//				.append(",")
//				.append("startTime=20.0")
//				.append("\n")
//				.toString();
		ModelicaDocument mo = addEvents(network, "ieee14", "ieee14bus_EQ.xml", "ddr", events, 14, 5, 1, 2,
				reread);
		
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		ModelicaModel mb = models.get("_LOAD__10_EC");
		String loadVariationId = "pwLoadPQVariation__LOAD__10_EC_0";
		Optional<BaseModelicaDeclaration> dv = mb.getDeclarations().stream()
				.filter(d -> d.getId().equals(loadVariationId))
				.findFirst();
		assertTrue(dv.isPresent());
	}

	public ModelicaDocument addEvents(
			Network network,
			String foldername,
			String casename,
			String ddrname,
			List<Event> events,
			int expectedNumBuses,
			int expectedNumGenerators,
			int expectedAdditionalDeclarations,
			int expectedAdditionalEquations,
			boolean reread)
			throws Exception
	{
		Path folder = TEST_SAMPLES.resolve(foldername);		
		String ddrLocation = folder.resolve(ddrname).toString();
		Path outputbase = DATA_TMP.resolve("event_adder_base.mo");
		Path outputev = DATA_TMP.resolve("event_adder_events.mo");
		boolean printPsmAnnotations = true;

		assertNotNull(network);
		assertEquals(expectedNumBuses, Iterables.size(network.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, network.getGeneratorCount());

		DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create("DYD", ddrLocation);
		ddr.connect();

		ModelicaDocument mo = convert(network, ddr);
		assertNotNull(mo);
		ModelicaTextPrinter.print(mo, outputbase, printPsmAnnotations);

		ModelicaDocument mobase = mo;
		if (reread) mobase = ModelicaParser.parse(outputbase);

//		List<Event> events = eventsFrom(eventData, n);
		ModelicaEventAdder adder = new ModelicaEventAdder(mobase, ddr, network, events);
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument moev = adder.addEvents(unresolved);
		assertNotNull(moev);
		assertTrue(unresolved.isEmpty());
		ModelicaTextPrinter.print(moev, outputev, printPsmAnnotations);

		int ndecls = mo.getSystemModel().getDeclarations().size();
		int ndeclsev = moev.getSystemModel().getDeclarations().size();
		assertEquals(ndecls + expectedAdditionalDeclarations, ndeclsev);
		int neqs = mo.getSystemModel().getEquations().size();
		int neqsev = moev.getSystemModel().getEquations().size();
		assertEquals(neqs + expectedAdditionalEquations, neqsev);
		return moev;
	}

	private ModelicaDocument convert(Network n, DynamicDataRepository ddr) throws Exception
	{
		ModelicaEngine me = ModelicaEngineMainFactory.create("Fake");
		Configuration config = new Configuration();
		String fakeInit = Paths.get(ddr.getLocation()).resolve("fake_init.csv").toString();
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("event_adder");
		Files.createDirectories(modelicaEngineWorkingDir);
		config.setParameter("modelicaEngineWorkingDir", modelicaEngineWorkingDir.toString());
		config.setParameter("fakeModelicaEngineResults", fakeInit);
		me.configure(config);

		ModelicaSystemBuilder builder = new ModelicaSystemBuilder(ddr, n, me);
		boolean onlyMainConnectedComponent = true;
		builder.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument mo = builder.build(unresolved);

		return mo;
	}
	
	private static Network importCase(String foldername, String casename) {
		Path folder = TEST_SAMPLES.resolve(foldername);
		Path cim = folder.resolve(casename);
		
		return StaticNetworkImporter.import_(cim);
	}
}
