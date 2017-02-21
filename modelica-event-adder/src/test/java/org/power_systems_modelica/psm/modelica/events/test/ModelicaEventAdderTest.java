package org.power_systems_modelica.psm.modelica.events.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.events.Event;
import org.power_systems_modelica.psm.modelica.events.ModelicaEventAdder;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaEventAdderTest
{
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
		String events = new StringBuilder(100)
				.append("BusFault")
				.append(",")
				.append("_BUS___10_TN")
				.append(",")
				.append("R=0.5")
				.append(",")
				.append("X=0.5")
				.append(",")
				.append("startTime=20.0")
				.append(",")
				.append("endTime=20.2")
				.append("\n")
				.toString();
		ModelicaDocument mo = addEvents("ieee14", "ieee14bus_EQ.xml", "ddr", events, 14, 5, 1, 1,
				reread);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		ModelicaModel mb = models.get("_BUS___10_TN");
		String busId = "bus__BUS___10_TN";
		String faultId = "pwFault__BUS___10_TN";
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
		String events = new StringBuilder(100)
				.append("LineFault")
				.append(",")
				.append("_BUS___10-BUS___11-1_AC")
				.append(",")
				.append("k=0.7")
				.append(",")
				.append("Rfault=0.01")
				.append(",")
				.append("Xfault=0.001")
				.append(",")
				.append("startTime=200")
				.append(",")
				.append("endTime=400")
				.append("\n")
				.toString();
		ModelicaDocument mo = addEvents("ieee14", "ieee14bus_EQ.xml", "ddr", events, 14, 5, 0, 0,
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
		String events = new StringBuilder(100)
				.append("GeneratorVSetpointModification")
				.append(",")
				.append("_FSSV.T11_SM")
				.append(",")
				.append("startTime=0.1")
				.append(",")
				.append("Vset=1.0")
				.append("\n")
				.toString();
		ModelicaDocument mo = addEvents("7buses", "M7buses_EQ.xml", "ddr", events, 7, 3, 1, 1,
				false);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		assertEquals(5, models.get("_FSSV_T11_SM").getDeclarations().size());
	}

	@Test
	public void addEvents7busesVSetPointReRead() throws Exception
	{
		String events = new StringBuilder(100)
				.append("GeneratorVSetpointModification")
				.append(",")
				.append("_FSSV.T11_SM")
				.append(",")
				.append("startTime=0.1")
				.append(",")
				.append("Vset=1.0")
				.append("\n")
				.toString();
		ModelicaDocument mo0 = addEvents("7buses", "M7buses_EQ.xml", "ddr", events, 7, 3, 1, 1,
				false);
		ModelicaDocument mo1 = addEvents("7buses", "M7buses_EQ.xml", "ddr", events, 7, 3, 1, 1,
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
		String events = new StringBuilder(100)
				.append("LoadVariation")
				.append(",")
				.append("_LOAD__10_EC")
				.append(",")
				.append("P=9.0")
				.append(",")
				.append("Q=1.0")
				.append(",")
				.append("startTime=20.0")
				.append("\n")
				.toString();
		ModelicaDocument mo = addEvents("ieee14", "ieee14bus_EQ.xml", "ddr", events, 14, 5, 0, 0,
				reread);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		ModelicaModel mb = models.get("_LOAD__10_EC");
		String loadVariationId = "pwLoadVDepwithVariation__LOAD__10_EC";
		Optional<ModelicaDeclaration> dv = mb.getDeclarations().stream()
				.filter(d -> d.getId().equals(loadVariationId))
				.findFirst();
		assertTrue(dv.isPresent());
		Optional<ModelicaArgument> alpha = dv.get().getArguments().stream()
				.filter(a -> a.getName().equals("alpha"))
				.findFirst();
		assertTrue(alpha.isPresent());
		assertEquals("1.5", alpha.get().getValue());
	}

	public ModelicaDocument addEvents(
			String foldername,
			String casename,
			String ddrname,
			String eventData,
			int expectedNumBuses,
			int expectedNumGenerators,
			int expectedAdditionalDeclarations,
			int expectedAdditionalEquations,
			boolean reread)
			throws Exception
	{
		Path folder = TEST_SAMPLES.resolve(foldername);
		Path cim = folder.resolve(casename);
		String ddrLocation = folder.resolve(ddrname).toString();
		Path outputbase = DATA_TMP.resolve("event_adder_base.mo");
		Path outputev = DATA_TMP.resolve("event_adder_events.mo");
		boolean printPsmAnnotations = true;

		Network n = StaticNetworkImporter.import_(cim);
		assertNotNull(n);
		assertEquals(expectedNumBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, n.getGeneratorCount());

		DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create("DYD", ddrLocation);
		ddr.connect();

		ModelicaDocument mo = convert(n, ddr);
		assertNotNull(mo);
		ModelicaTextPrinter.print(mo, outputbase, printPsmAnnotations);

		ModelicaDocument mobase = mo;
		if (reread) mobase = ModelicaParser.parse(outputbase);

		List<Event> events = eventsFrom(eventData, n);
		ModelicaEventAdder adder = new ModelicaEventAdder(mobase, ddr, n, events);
		ModelicaDocument moev = adder.addEvents();
		assertNotNull(moev);
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
		ModelicaDocument mo = builder.build();

		return mo;
	}

	// FIXME remove this, test function should accept a list of events,
	// FIXME workflow test should write this list as a string, then the task will read it from string
	private static List<Event> eventsFrom(String eventData, Network network)
	{
		List<Event> events = new ArrayList<>();
		String[] records = eventData.split("\n");
		for (String r : records)
		{
			String[] fields = r.split(",");
			String event = fields[0];
			String idelem = fields[1];
			Stream<String> paramss = Arrays.stream(fields, 2, fields.length);

			Identifiable<?> element = network.getIdentifiable(idelem);
			if (element == null)
			{
				continue;
			}
			Map<String, Object> params = paramss
					.map(ps -> ps.split("="))
					.collect(Collectors.toMap(p -> p[0], p -> p[1]));

			events.add(new Event(event, element, params));
		}
		return events;
	}
}
