package org.power_systems_modelica.psm.test.gui.service;

/*
 * #%L
 * Power Systems on Modelica GUI
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
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.FileUtils;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.ddr.dyd.Diagnostics;
import org.power_systems_modelica.psm.ddr.dyd.ModelMapping;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.power_systems_modelica.psm.gui.service.DdrService;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public class DdrServiceTest
{

	@Test
	public void loadDdrs() throws IOException
	{

		Ddr ddr = DdrService.getDdr("Reference cases", PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr"));

		assertNotNull(ddr);
		assertNotNull(ddr.nameProperty());
		assertNotNull(ddr.locationProperty());
		assertNotNull(ddr.getDescription());
		assertNotNull(ddr.descriptionProperty());
		assertEquals(DdrType.DYD, ddr.getType());
		assertNotNull(ddr.typeProperty());

		Diagnostics diagnostics = DdrService.check(ddr.getLocation());
		Map<String, String> xml = diagnostics.getXmlErrors();
		Map<String, ModelMapping> duplicates = diagnostics.getModelMapping();

		assertFalse(xml.size() > 0);
		for (String key : duplicates.keySet())
		{
			assertFalse(duplicates.get(key).isDuplicated());
		}

		Ddr ddrOut = new Ddr();
		ddrOut.setLocation(
				PathUtils.DATA_TEST.resolve("ieee14").resolve("ddrDuplicated").toString());
        DdrService.duplicateDdr(ddr, ddrOut);
		assertEquals(ddr.getName() + "Duplicated", ddrOut.getName());
		
		FileUtils.deleteDirectory(Paths.get(ddrOut.getLocation()));
	}
}
