package org.power_systems_modelica.psm.network.import_.test;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class StaticNetworkImporterTest
{
	private static final Path TEST_SAMPLES = Paths.get(System.getenv("PSM_DATA"))
			.resolve("test");

	@Test
	public void testImportIEEE14()
	{
		Path file = TEST_SAMPLES.resolve("ieee14/ieee14bus_EQ.xml");
		Network n = StaticNetworkImporter.import_(file);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}

	@Test
	public void testImportZippedIEEE14()
	{
		Path file = TEST_SAMPLES.resolve("ieee14/ieee14bus.zip");
		Network n = StaticNetworkImporter.import_(file);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}

	@Test
	public void testImportMissingFiles()
	{
		Path file = TEST_SAMPLES.resolve("missing_non_existent_EQ.xml");
		Network n = StaticNetworkImporter.import_(file);
		assertEquals(null, n);
	}
}
