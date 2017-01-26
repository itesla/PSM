package org.power_systems_modelica.psm.modelica.events.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.events.Event;
import org.power_systems_modelica.psm.modelica.events.ModelicaEventAdder;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaEventAdderTest
{
	@Test
	public void addEventsIeee14BusFault() throws Exception
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
		ModelicaDocument mo = addEvents(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr",
				events,
				1,
				1);
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
		ModelicaDocument mo = addEvents(
				"ieee14",
				"ieee14bus_EQ.xml",
				"ddr",
				events,
				0,
				0);
		Map<String, ModelicaModel> models = ModelicaUtil.groupByNormalizedStaticId(mo);
		assertTrue(models.get("_BUS___10_BUS___11_1_AC").getDeclarations()
				.get(0)
				.getType()
				.endsWith("LineFault"));
	}

	public ModelicaDocument addEvents(
			String foldername,
			String casename,
			String ddrname,
			String eventData,
			int expectedAdditionalDeclarations,
			int expectedAdditionalEquations)
			throws Exception
	{
		// TODO Use ShrinkWrap filesystem for temporal files used in tests
		Path folder = TEST_SAMPLES.resolve(foldername);
		Path cim = folder.resolve(casename);
		String ddrLocation = folder.resolve(ddrname).toString();
		String fakeInit = folder.resolve(ddrname).resolve("fake_init.csv").toString();
		Path outputev = DATA_TMP.resolve("event_adder_events.mo");
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("event_adder");
		Files.createDirectories(modelicaEngineWorkingDir);

		Network n = StaticNetworkImporter.import_(cim);
		assertNotNull(n);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());

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

		List<Event> events = eventsFrom(eventData, n);
		ModelicaEventAdder adder = new ModelicaEventAdder(mo, ddr, n, events);
		ModelicaDocument moev = adder.addEvents();
		assertNotNull(moev);

		ModelicaTextPrinter printer = new ModelicaTextPrinter(moev);
		boolean includePsmAnnotations = false;
		printer.setIncludePsmAnnotations(includePsmAnnotations);
		try (PrintWriter out = new PrintWriter(outputev.toFile());)
		{
			printer.print(out);
		}

		int ndecls = mo.getSystemModel().getDeclarations().size();
		int ndeclsev = moev.getSystemModel().getDeclarations().size();
		assertEquals(ndecls + expectedAdditionalDeclarations, ndeclsev);
		int neqs = mo.getSystemModel().getEquations().size();
		int neqsev = moev.getSystemModel().getEquations().size();
		assertEquals(neqs + expectedAdditionalEquations, neqsev);
		return moev;
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
