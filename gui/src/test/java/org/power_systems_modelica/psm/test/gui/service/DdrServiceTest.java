package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;

import org.apache.commons.io.FileSystemUtils;
import org.junit.Test;
import org.power_systems_modelica.psm.commons.FileUtils;
import org.power_systems_modelica.psm.ddr.dyd.ModelMapping;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;

public class DdrServiceTest
{

	@Test
	public void loadDdrs() throws IOException
	{

		Ddr ddr = DdrService.getDdr("Reference cases", PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr"));
		
		String t = ddr.toString();
		
		assertNotNull(ddr);
		assertNotNull(ddr.nameProperty());
		assertNotNull(ddr.locationProperty());
		assertNotNull(ddr.getDescription());
		assertNotNull(ddr.descriptionProperty());
		assertEquals(DdrType.DYD, ddr.getType());
		assertNotNull(ddr.typeProperty());

		Map<String, String> xml = DdrService.checkXml(ddr.getLocation());
		assertFalse(xml.size() > 0);

		Map<String, ModelMapping> duplicates = DdrService.checkDuplicates(ddr.getLocation());
		for (String key : duplicates.keySet())
		{
			assertFalse(duplicates.get(key).isDuplicated());
		}

		Ddr ddrOut = new Ddr();
		ddrOut.setLocation(
				PathUtils.DATA_TEST.resolve("ieee14").resolve("ddrDuplicated").toString());
		boolean duplicated = DdrService.duplicateDdr(ddr, ddrOut);
		assertEquals(ddr.getName() + "Duplicated", ddrOut.getName());
		
		FileUtils.deleteDirectory(Paths.get(ddrOut.getLocation()));
	}
}
