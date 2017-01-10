package org.power_systems_modelica.psm.commons.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.Configuration;

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
