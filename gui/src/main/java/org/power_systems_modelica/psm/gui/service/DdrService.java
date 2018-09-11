package org.power_systems_modelica.psm.gui.service;

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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.power_systems_modelica.psm.ddr.dyd.Diagnostics;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public class DdrService
{

	public static boolean duplicateDdr(Ddr ddrIn, Ddr ddrOut)
	{

		ddrOut.setName(getDdrName(Paths.get(ddrOut.getLocation())));

		File source = new File(ddrIn.getLocation());
		File dest = new File(ddrOut.getLocation());
		try
		{
			FileUtils.copyDirectory(source, dest);
		}
		catch (IOException e)
		{
			return false;
		}

		return true;
	}

	public static Diagnostics check(String location)
	{
		Diagnostics diagnostics = null;
		DynamicDataRepositoryDydFiles ddr = new DynamicDataRepositoryDydFiles();
		ddr.setLocation(location);
		try
		{
			diagnostics = ddr.check();
		}
		catch (IOException e)
		{
			LOG.error(e.getMessage());
		}
		
		return diagnostics;
	}

	public static Ddr getDdr(String catalogName, Path path) throws IOException
	{
		
		return getDdr(CatalogService.getCatalogByName("ddrs", catalogName), path);
	}

	public static Ddr getDdr(Catalog catalog, Path path) throws IOException
	{

		List<Ddr> ddrs = getDdrs(catalog);

		for (Ddr ddr : ddrs)
		{
			if (Files.isSameFile(Paths.get(ddr.getLocation()), path))
				return ddr;
		}

		return null;
	}

	public static List<Ddr> getDdrs(String catalogName)
	{
		
		return getDdrs(CatalogService.getCatalogByName("ddrs", catalogName));
	}

	public static List<Ddr> getDdrs(Catalog catalog)
	{
		List<Ddr> ddrs = new ArrayList<>();
		Path catalogPath = Paths.get(catalog.getLocation());
		try
		{
			searchCatalogDdrs(ddrs, catalogPath);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return ddrs;
	}

	private static boolean searchCatalogDdrs(List<Ddr> ddrs, Path path) throws IOException
	{

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path))
		{
			for (Path entry : stream)
			{
				if (Files.isDirectory(entry))
				{
					if (searchCatalogDdrs(ddrs, entry))
					{
						Ddr d = new Ddr();
						d.setName(getDdrName(entry));
						d.setLocation(entry.toString());
						ddrs.add(d);

						Properties properties = getDdrProperties(entry);
						if (!properties.isEmpty())
						{

							String description = properties.getProperty("description");
							String type = properties.getProperty("type");

							d.setDescription(description);
							d.setType(DdrType.valueOf(type));
						}
					}
				}
				else if (entry.toString().endsWith(".dyd"))
					return true;
			}
		}

		return false;
	}

	private static String getDdrName(Path entry)
	{

		String previousName = null;
		String lastName = null;
		Iterator<Path> it = entry.iterator();
		while (it.hasNext())
		{

			previousName = lastName;
			lastName = it.next().getFileName().toString();
		}

		return previousName + "_" + lastName;
	}

	private static Properties getDdrProperties(Path path)
	{
		Properties properties = new Properties();
		try
		{
			try (InputStream is = Files.newInputStream(path.resolve("ddr.properties")))
			{
				properties.load(is);
			}
		}
		catch (NoSuchFileException e)
		{
			// Ignore
		}
		catch (IOException e)
		{
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return properties;
	}

	private static final Logger LOG = LoggerFactory.getLogger(DdrService.class);
}
