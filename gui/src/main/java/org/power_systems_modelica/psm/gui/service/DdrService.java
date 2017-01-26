package org.power_systems_modelica.psm.gui.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	public static Ddr getDdr(Catalog catalog, Path path) throws IOException
	{

		ObservableList<Ddr> ddrs = getDdrs(catalog);

		for (Ddr ddr : ddrs)
		{
			if (Files.isSameFile(Paths.get(ddr.getLocation()), path))
				return ddr;
		}

		return null;
	}

	public static ObservableList<Ddr> getDdrs(Catalog catalog)
	{
		ObservableList<Ddr> ddrs = FXCollections.observableArrayList();
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

	private static boolean searchCatalogDdrs(ObservableList<Ddr> ddrs, Path path) throws IOException
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
