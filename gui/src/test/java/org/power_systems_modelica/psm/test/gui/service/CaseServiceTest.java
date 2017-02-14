package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;

public class CaseServiceTest
{

	@Test
	public void loadCase() throws IOException
	{

		Case c = CaseService.getCase("Reference cases", PathUtils.DATA_TEST.resolve("ieee14"));
		
		String t = c.toString();
		
		assertNotNull(c);
		assertNotNull(c.nameProperty());
		assertEquals(PathUtils.DATA_TEST.resolve("ieee14").toString(), c.getLocation());
		assertNotNull(c.locationProperty());
		assertEquals("A portion of the American Electric Power System (in the Midwestern US) as of February, 1962.", c.getDescription());
		assertNotNull(c.descriptionProperty());
		assertEquals("CIM1", c.getFormat());
		assertNotNull(c.formatProperty());
		assertEquals(14, c.getSize());
		assertNotNull(c.sizeProperty());

		String ddrPath = CaseService.getDefaultDdrLocation(c);
		assertNotNull(ddrPath);
	}

	@Test
	public void loadConvertedCase() throws IOException
	{

		new File(PathUtils.DATA_TEST.resolve("ieee14").resolve("ieee14.mo").toString()).createNewFile();
		CaseService.saveConvertedCaseProperties(PathUtils.DATA_TEST.resolve("ieee14").toString(),
				PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString(), "loadflowHelmflow",
				true, "OpenModelica");
		ConvertedCase c = CaseService.getConvertedCase("Reference cases",
				PathUtils.DATA_TEST.resolve("ieee14"));
		
		String t = c.toString();
		
		assertNotNull(c);
		assertNotNull(c.nameProperty());
		assertNotNull(c.ddrLocationProperty());
		assertEquals(PathUtils.DATA_TEST.resolve("ieee14").toString(), c.getLocation());
		assertNotNull(c.locationProperty());
		assertEquals("loadflowHelmflow", c.getLoadflowEngine());
		assertNotNull(c.loadflowEngineProperty());
		assertEquals("Only main connected component", c.getOnlyMainConnectedComponent());
		assertNotNull(c.onlyMainConnectedComponentProperty());
		assertEquals("OpenModelica", c.getFullModelInitializationEgine());
		assertNotNull(c.fullModelInitializationEgineProperty());
		
		PathUtils.DATA_TEST.resolve("ieee14").resolve("ieee14.mo").toFile().delete();
		PathUtils.DATA_TEST.resolve("ieee14").resolve("convertedCase.properties").toFile().delete();
	}

	@Test
	public void importCase() throws Exception
	{

		Case c = new Case();
		c.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
		Network n = CaseService.getCaseSummary(c);
		assertNotNull(n);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}
}
