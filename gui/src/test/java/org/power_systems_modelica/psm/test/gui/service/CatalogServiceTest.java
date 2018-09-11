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
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CatalogService;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public class CatalogServiceTest {

	@Test
	public void loadCasesCatalog() {
		
		loadCatalog("cases", 1);
	}

	@Test
	public void loadDdrsCatalog() {
		
		loadCatalog("ddrs", 1);
	}
	
	@Test
	public void loadCatalogByName() {
		
		Catalog catalog = CatalogService.getCatalogByName("cases", "Reference cases");
		assertNotNull(catalog);
		assertEquals("Reference cases", catalog.getName());
	}

	private void loadCatalog(String name, int num) {
		
		List<Catalog> list = CatalogService.getCatalogs(name);
		
		assertNotNull(list);
		assertEquals(num, list.size());
	}
}
