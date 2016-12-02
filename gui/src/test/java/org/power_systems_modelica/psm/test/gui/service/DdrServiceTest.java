package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;

import javafx.collections.ObservableList;

public class DdrServiceTest {

	@Test
	public void loadDdrs() {
		
		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());
		ObservableList<Ddr> list = DdrService.getDdrs(catalog);
		assertNotNull(list);
		assertEquals(5, list.size());
	}
}
