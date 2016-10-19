package org.power_systems_modelica.psm.gui.service;

import org.power_systems_modelica.psm.gui.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CatalogService {

	public static ObservableList<Catalog> getCatalogs() {
		LOG.debug("getCatalogs");
		ObservableList<Catalog> catalogs = FXCollections.observableArrayList();

		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setDescription("Public IEEE reference cases from Washington archive. Exported to ENTSO-E CIM Profile V1 using ...");
		catalog.setLocation("/psm/data/samples");
		catalogs.add(catalog);

		catalog = new Catalog();
		catalog.setName("Internal testing");
		catalog.setDescription("Private cases used in the development environment");
		catalog.setLocation("/psm/data/private_samples");
		catalogs.add(catalog);

		return catalogs;
	}

	private static final Logger LOG = LoggerFactory.getLogger(CatalogService.class);
}
