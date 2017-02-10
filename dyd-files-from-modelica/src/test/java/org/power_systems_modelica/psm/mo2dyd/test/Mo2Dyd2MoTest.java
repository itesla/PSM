package org.power_systems_modelica.psm.mo2dyd.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.commons.test.TestUtil.DATA_TMP;
import static org.power_systems_modelica.psm.commons.test.TestUtil.TEST_SAMPLES;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.ddr.dyd.xml.XmlUtil;
import org.power_systems_modelica.psm.mo2dyd.DydFilesFromModelica;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.test.ModelicaTestUtil;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class Mo2Dyd2MoTest
{
	@Before
	public void setup()
	{
		XmlUtil.isValidationActive = true;
	}

	@Test
	public void rebuildIeee14() throws Exception
	{
		rebuild(
				"ieee14",
				"itesla/ieee14bus_no_lf.mo",
				"itesla/init",
				"ieee14bus_EQ.xml",
				"ieee14_ddr",
				14,
				5);
	}

	@Test
	public void rebuildIeee30() throws Exception
	{
		rebuild(
				"ieee30",
				"itesla/ieee30bus_no_lf.mo",
				"itesla/init",
				"ieee30bus_EQ.xml",
				"ieee30_ddr",
				30,
				6);
	}

	@Test
	public void rebuildIeee57() throws Exception
	{
		rebuild(
				"ieee57",
				"itesla/ieee57bus_no_lf.mo",
				"itesla/init",
				"ieee57bus_EQ.xml",
				"ieee57_ddr",
				57,
				7);
	}

	@Test
	public void rebuildIeee118() throws Exception
	{
		rebuild(
				"ieee118",
				"itesla/ieee118bus_no_lf.mo",
				"itesla/init",
				"ieee118bus_EQ.xml",
				"ieee118_ddr",
				118,
				54);
	}

	@Test
	public void rebuildSmallCase1() throws Exception
	{
		rebuild(
				"smallcase1",
				"itesla/case1_no_lf.mo",
				"itesla/init",
				"case1_EQ.xml",
				"smallcase1_ddr",
				3,
				2);
	}

	@Test
	public void rebuildSmallCase2() throws Exception
	{
		rebuild(
				"smallcase2",
				"itesla/case2_no_lf.mo",
				"itesla/init",
				"case2_EQ.xml",
				"smallcase2_ddr",
				3,
				1);
	}

	@Test
	public void rebuildSmallCase3() throws Exception
	{
		rebuild(
				"smallcase3",
				"itesla/case3_no_lf.mo",
				"itesla/init",
				"case3_EQ.xml",
				"smallcase3_ddr",
				3,
				2);
	}

	@Test
	public void rebuildSmallCase4() throws Exception
	{
		rebuild(
				"smallcase4",
				"itesla/case4_no_lf.mo",
				"itesla/init",
				"case4_EQ.xml",
				"smallcase4_ddr",
				3,
				2);
	}

	@Test
	public void rebuild7buses() throws Exception
	{
		rebuild(
				"7buses",
				"itesla/M7buses_no_lf.mo",
				"itesla/init",
				"M7buses_EQ.xml",
				"M7buses_ddr",
				7,
				3);
	}

	@Test
	public void rebuildNordic32() throws Exception
	{
		rebuild(
				"Nordic32",
				"itesla/Nordic32_no_lf.mo",
				"itesla/init",
				"Nordic32_EQ.xml",
				"Nordic32_ddr",
				52,
				20);
	}

	protected void rebuild(
			String folderName,
			String moName,
			String moInitPathName,
			String caseName,
			String ddrName,
			int expectedNumBuses,
			int expectedNumGenerators)
			throws Exception
	{
		Path folder = TEST_SAMPLES.resolve(folderName);
		Path moInput = folder.resolve(moName);
		Path cim = folder.resolve(caseName);

		Path moOutput = DATA_TMP.resolve("mo2dyd2mo_output_" + folderName + ".mo");
		Path modelicaEngineWorkingDir = DATA_TMP.resolve("mo2dyd2mo_init_" + folderName);
		Files.createDirectories(modelicaEngineWorkingDir);

		// A new DDR will be created in the temporal folder
		Path ddrLocation = DATA_TMP.resolve(ddrName);
		Files.createDirectories(ddrLocation);
		String fakeInit = ddrLocation.resolve("fake_init.csv").toString();
		// If there is no Modelica file with initialization models
		// they will be built (inferred) from simulation models
		Path moInitPath = null;
		if (EXPLICIT_INIT_FILES) moInitPath = folder.resolve(moInitPathName);
		new DydFilesFromModelica(moInput, moInitPath, ddrLocation).mo2dyd();

		Network n = StaticNetworkImporter.import_(cim);
		assertNotNull(n);
		assertEquals(expectedNumBuses, Iterables.size(n.getBusView().getBuses()));
		assertEquals(expectedNumGenerators, n.getGeneratorCount());

		String sddrLocation = ddrLocation.toAbsolutePath().toString();
		DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create("DYD", sddrLocation);
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

		boolean includePsmAnnotations = false;
		ModelicaTextPrinter.print(mo, moOutput, includePsmAnnotations);

		Path expected = moInput;
		Path actual = moOutput;
		ModelicaTestUtil.assertEqualsNormalizedModelicaText(expected, actual);
	}

	protected static final boolean EXPLICIT_INIT_FILES = true;
}
