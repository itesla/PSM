package org.power_systems_modelica.psm.gui.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.gui.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CatalogService {
	
	public static final Path	DATA_TEST		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");

	public static ObservableList<Catalog> getCatalogs() {
		LOG.debug("getCatalogs");
		ObservableList<Catalog> catalogs = FXCollections.observableArrayList();

		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setDescription("Public IEEE reference cases from Washington archive. Exported to ENTSO-E CIM Profile V1 using ...");
		catalog.setLocation(DATA_TEST.toString());
		catalogs.add(catalog);

		catalog = new Catalog();
		catalog.setName("Internal testing");
		catalog.setDescription("Private cases used in the development environment");
		catalog.setLocation("/psm/data/private_samples");
		catalogs.add(catalog);

		return catalogs;
	}

	public static Catalog getCatalogByName(String catalogName) {
		
		ObservableList<Catalog> catalogs = getCatalogs();
		for (Catalog c : catalogs) {
			if (c.getName().equals(catalogName))
				return c;
		}
		return null;
	}

	ObservableList<Catalog> catalogs;
	private static final Logger LOG = LoggerFactory.getLogger(CatalogService.class);
}
