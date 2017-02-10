package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;
import javafx.collections.ObservableList;

public class CaseServiceTest {
	
	@Test
	public void loadCases() {
		
		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());
		List<Case> list = CaseService.getCases(catalog);
		assertNotNull(list);
		assertEquals(10, list.size());
	}

	@Test
	public void importCase() throws Exception {
		
		Case c = new Case();
		c.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
		Network n = CaseService.importCase(c);
		assertNotNull(n);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}
}
