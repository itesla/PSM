package org.power_systems_modelica.psm.network.import_.test;

/*
 * #%L
 * Static network importer
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;

import com.google.common.collect.Iterables;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class StaticNetworkImporterTest
{
	@Test
	public void testImportIEEE14()
	{
		Path file = PathUtils.DATA_TEST.resolve("ieee14/ieee14bus_EQ.xml");
		Network n = StaticNetworkImporter.import_(file);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}

	@Test
	public void testImportZippedIEEE14()
	{
		Path file = PathUtils.DATA_TEST.resolve("ieee14/ieee14bus.zip");
		Network n = StaticNetworkImporter.import_(file);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}

	@Test
	public void testImportMissingFiles()
	{
		Path file = PathUtils.DATA_TEST.resolve("missing_non_existent_EQ.xml");
		Network n = StaticNetworkImporter.import_(file);
		assertEquals(null, n);
	}
}
