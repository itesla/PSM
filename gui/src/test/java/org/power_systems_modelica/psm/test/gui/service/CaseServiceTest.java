package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.h2.store.fs.FileUtils;
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

		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());

		Case c = CaseService.getCase(catalog, PathUtils.DATA_TEST.resolve("ieee14"));
		assertNotNull(c);
		assertEquals(c.getLocation(), PathUtils.DATA_TEST.resolve("ieee14").toString());

		String ddrPath = CaseService.getDefaultDdrLocation(c);
		assertNotNull(ddrPath);
	}

	@Test
	public void loadConvertedCase() throws IOException
	{

		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());

		FileUtils.createFile(PathUtils.DATA_TEST.resolve("ieee14").resolve("ieee14.mo").toString());
		CaseService.saveConvertedCaseProperties(PathUtils.DATA_TEST.resolve("ieee14").toString(),
				PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString(), "loadflowHelmflow",
				true, "OpenModelica");
		ConvertedCase c = CaseService.getConvertedCase(catalog,
				PathUtils.DATA_TEST.resolve("ieee14"));
		assertNotNull(c);
		assertEquals(c.getLocation(), PathUtils.DATA_TEST.resolve("ieee14").toString());
		
		PathUtils.DATA_TEST.resolve("ieee14").resolve("ieee14.mo").toFile().delete();
		PathUtils.DATA_TEST.resolve("ieee14").resolve("convertedCase.properties").toFile().delete();
	}

	@Test
	public void importCase() throws Exception
	{

		Case c = new Case();
		c.setLocation(PathUtils.DATA_TEST.resolve("ieee14").toString());
		Network n = CaseService.importCase(c);
		assertNotNull(n);
		assertEquals(14, Iterables.size(n.getBusView().getBuses()));
		assertEquals(5, n.getGeneratorCount());
	}
}
