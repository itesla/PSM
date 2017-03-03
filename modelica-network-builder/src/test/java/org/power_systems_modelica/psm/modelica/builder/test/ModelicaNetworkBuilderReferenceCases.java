package org.power_systems_modelica.psm.modelica.builder.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.ddr.dyd.ModelMapping;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class ModelicaNetworkBuilderReferenceCases
{
	@Test
	public void buildIeee14() throws Exception
	{
		build("test", "ieee14", "ieee14bus_EQ.xml", "ieee14/ddr", "itesla/ieee14bus_no_lf.mo", 14,
				5);
	}

	@Test
	public void buildIeee30() throws Exception
	{
		build("test", "ieee30", "ieee30bus_EQ.xml", "ieee30/ddr", "itesla/ieee30bus_no_lf.mo", 30,
				6);
	}

	@Test
	public void buildIeee57() throws Exception
	{
		build("test", "ieee57", "ieee57bus_EQ.xml", "ieee57/ddr", "itesla/ieee57bus_no_lf.mo", 57,
				7);
	}

//	@Test
	public void buildIeee118() throws Exception
	{
		build("test_private", "ieee118", "ieee118bus_EQ.xml", "ieee118/ddr",
				"itesla/ieee118bus_no_lf.mo", 118, 54);
	}

	@Test
	public void buildSmallCase1() throws Exception
	{
		build("test", "smallcase1", "case1_EQ.xml", "smallcase1/ddr", "itesla/case1_no_lf.mo", 3,
				2);
	}

	@Test
	public void buildSmallCase2() throws Exception
	{
		build("test", "smallcase2", "case2_EQ.xml", "smallcase2/ddr", "itesla/case2_no_lf.mo", 3,
				1);
	}

	@Test
	public void buildSmallCase3() throws Exception
	{
		build("test", "smallcase3", "case3_EQ.xml", "smallcase3/ddr", "itesla/case3_no_lf.mo", 3,
				2);
	}

	@Test
	public void buildSmallCase4() throws Exception
	{
		build("test", "smallcase4", "case4_EQ.xml", "smallcase4/ddr", "itesla/case4_no_lf.mo", 3,
				2);
	}

	@Test
	public void build7buses() throws Exception
	{
		build("test", "7buses", "M7buses_EQ.xml", "7buses/ddr", "itesla/M7buses_no_lf.mo", 7, 3);
	}

	@Test
	public void buildNordic32() throws Exception
	{
		build("test", "Nordic32", "Nordic32_EQ.xml", "Nordic32/ddr", "itesla/Nordic32_no_lf.mo", 52,
				20);
	}

	public void build(
			String catalog,
			String foldername,
			String casename,
			String ddrname,
			String expectedmoname,
			int expectedNumBuses,
			int expectedNumGenerators)
			throws Exception
	{
		// TODO Use ShrinkWrap file system for temporal files used in tests
		Path folder = DATA.resolve(catalog).resolve(foldername);
		Path cim = folder.resolve(casename);
		String ddrLocation = DATA.resolve(catalog).resolve(ddrname).toString();
		String fakeInit = DATA.resolve(catalog).resolve(ddrname).resolve("fake_init.csv")
				.toString();
		String name = foldername;
		Path output = DATA_TMP.resolve("modelica_builder_output_" + name + ".mo");
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("modelica_builder_init" + name);
		Files.createDirectories(modelicaEngineWorkingDir);

		Network n = StaticNetworkImporter.import_(cim);
		assertNotNull(n);
		assertEquals(expectedNumBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, n.getGeneratorCount());

		// Check first the DDR we will use
		checkDyd(ddrLocation);
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
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument mo = builder.build(unresolved);
		assertNotNull(mo);
		assertTrue(unresolved.isEmpty());

		// Check that the Modelica document built is equal to the reference
		boolean includePsmAnnotations = false;
		ModelicaTextPrinter.print(mo, output, includePsmAnnotations);
		Path expected = folder.resolve(expectedmoname);
		Path actual = output;
		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);

		// Check that the obtained Modelica document can be printed with PSM annotations
		// And all annotations are recovered
		checkPrintWithAnnotations(mo, name);
	}

	private void checkPrintWithAnnotations(ModelicaDocument mo, String name) throws IOException
	{
		boolean includePsmAnnotations = true;
		Path outputa = DATA_TMP.resolve("modelica_builder_output_" + name + "_annotations.mo");
		Path outputa1 = DATA_TMP.resolve("modelica_builder_output_" + name + "_annotations1.mo");
		ModelicaTextPrinter.print(mo, outputa, includePsmAnnotations);
		ModelicaDocument mo1 = ModelicaParser.parse(outputa);

		ModelicaTextPrinter.print(mo1, outputa1, includePsmAnnotations);
		Path expected = outputa;
		Path actual = outputa1;
		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}

	private void checkDyd(String ddrLocation) throws IOException
	{
		DynamicDataRepositoryDydFiles ddr2 = new DynamicDataRepositoryDydFiles();
		ddr2.setLocation(ddrLocation);
		Map<String, ModelMapping> modelMapping = ddr2.checkDuplicates();
		for (String key : modelMapping.keySet())
		{
			assertFalse(modelMapping.get(key).isDuplicated());
			assertEquals(modelMapping.get(key).getModels().size(),
					modelMapping.get(key).getModelContainers().size());
		}
	}
}
