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

import org.junit.Test;
import org.power_systems_modelica.psm.ddr.dyd.ModelMapping;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;

public class DdrServiceTest
{

	@Test
	public void loadDdrs() throws IOException
	{

		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());
		Ddr ddr = DdrService.getDdr(catalog, PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr"));
		assertNotNull(ddr);

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

		Files.walk(Paths.get(ddrOut.getLocation()), FileVisitOption.FOLLOW_LINKS)
				.sorted(Comparator.reverseOrder())
				.map(Path::toFile)
				.peek(System.out::println)
				.forEach(File::delete);
	}
}
