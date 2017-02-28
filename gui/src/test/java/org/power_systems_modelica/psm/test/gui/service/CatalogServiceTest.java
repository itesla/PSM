package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CatalogService;

import javafx.collections.ObservableList;

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
