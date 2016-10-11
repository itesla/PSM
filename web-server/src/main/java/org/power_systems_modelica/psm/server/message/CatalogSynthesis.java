package org.power_systems_modelica.psm.server.message;

import java.io.Serializable;
import java.util.List;

import org.power_systems_modelica.psm.dao.Catalog;

public class CatalogSynthesis implements Serializable
{
	private List<Catalog> catalogs;

	public CatalogSynthesis(List<Catalog> catalogs)
	{
		this.catalogs = catalogs;
	}

	public List<Catalog> getCatalogs()
	{
		return catalogs;
	}

	public void setCatalogs(List<Catalog> catalogs)
	{
		this.catalogs = catalogs;
	}
}
