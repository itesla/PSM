package org.power_systems_modelica.psm.dymola.integration;

/*
 * #%L
 * Dynamic simulation using Dymola
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */


import org.junit.Test;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.dymola.integration.utils.ZipWriter;

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class ZipWriterTest
{
	private String zipFileName = "testiPSL.zip";

		@Test
		public void test() {
			ZipWriter zipper = new ZipWriter(zipFileName,
					PathUtils.DATA_TEST.resolve("library"),
					PathUtils.DATA_TEST.resolve("library"), null);
			zipper.createZip();
	    }
}
