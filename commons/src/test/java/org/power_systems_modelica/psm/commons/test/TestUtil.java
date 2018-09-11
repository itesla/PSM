package org.power_systems_modelica.psm.commons.test;

/*
 * #%L
 * Commons
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class TestUtil
{
	public static final Path	TEST_SAMPLES	= Paths
														.get(System.getenv("PSM_DATA"))
														.resolve("test");
	public static final Path	DATA_TMP		= Paths
														.get(System.getenv("PSM_DATA"))
														.resolve("tmp");
	public static final Path	DATA			= Paths
														.get(System.getenv("PSM_DATA"));
	public static final String	NEW_LINE		= System.getProperty("line.separator").toString();
}
