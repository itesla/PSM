package org.power_systems_modelica.psm.modelica.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.ModelicaType;

public class ModelicaTypeTest
{
	@Test
	public void testType()
	{
		// Just to be sure that case sensitive definition of enumeration values is ok
		assertEquals(ModelicaType.Real, ModelicaType.valueOf("Real"));
		assertEquals(ModelicaType.Integer, ModelicaType.valueOf("Integer"));
		assertEquals(ModelicaType.Boolean, ModelicaType.valueOf("Boolean"));
		assertEquals(ModelicaType.Real, ModelicaType.valueOf("Real"));
	}
}
