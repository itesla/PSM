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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public final class ConfigurationTest
{
	@Test
	public void testParameters()
	{
		Configuration c = new Configuration();
		c.setParameter("param", "value");
		c.setParameter("paramBoolTrue", "true");
		c.setParameter("paramBoolFalse", "false");
		assertEquals(c.getParameter("param"), "value");
		assertTrue(c.getBoolean("paramBoolTrue"));
		assertFalse(c.getBoolean("paramBoolFalse"));
		assertEquals(c.getParameter("missing"), null);
	}
}
