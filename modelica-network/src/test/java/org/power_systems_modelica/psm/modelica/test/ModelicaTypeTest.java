package org.power_systems_modelica.psm.modelica.test;

/*
 * #%L
 * Modelica network model
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.ModelicaType;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
