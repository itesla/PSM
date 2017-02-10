package org.power_systems_modelica.psm.gui.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogService {
	
	public static Catalog getCatalog(String name, Path path) throws IOException {
	
		List<Catalog> catalogs = getCatalogs(name);
		
		for (Catalog catalog: catalogs) {
			if (Files.isSameFile(Paths.get(catalog.getLocation()), path))
				return catalog;
		}
		
		return null;
	}

	public static List<Catalog> getCatalogs(String name) {
		
		LOG.debug("getCatalogs");
		List<Catalog> catalogs = new ArrayList<>();
		Path path = PathUtils.DATA_TEST.resolve("cfg").resolve(name + ".properties");
        Properties properties = new Properties();
        try {
            try (InputStream is = Files.newInputStream(path)) {
                properties.load(is);
                is.close();
            }
        } catch (IOException e) {
        	LOG.error(e.getMessage());
        }
		
        int num = Integer.parseInt(properties.getProperty("catalogs.num"));
        for(int i=1; i<=num; i++) {
        	String catalogName = properties.getProperty("catalogs." + i + ".name");
        	String catalogDescription = properties.getProperty("catalogs." + i + ".description");
        	String catalogLocation = PathUtils.translateLocation(properties.getProperty("catalogs." + i + ".location"));
        	        	
    		Catalog catalog = new Catalog();
    		catalog.setName(catalogName);
    		catalog.setDescription(catalogDescription);
    		catalog.setLocation(catalogLocation);
    		catalogs.add(catalog);
        }
        
		return catalogs;
	}

	public static Catalog getCatalogByName(String name, String catalogName) {
		
		List<Catalog> catalogs = getCatalogs(name);
		for (Catalog c : catalogs) {
			if (c.getName().equals(catalogName))
				return c;
		}
		return null;
	}

	private static final Logger LOG = LoggerFactory.getLogger(CatalogService.class);
}
