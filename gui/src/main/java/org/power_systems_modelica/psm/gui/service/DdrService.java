package org.power_systems_modelica.psm.gui.service;

import org.power_systems_modelica.psm.gui.model.Ddr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DdrService {

	public static ObservableList<Ddr> getDdrs(String catalogName) {
		ObservableList<Ddr> ddrs = FXCollections.observableArrayList();
		if (catalogName.equals("Reference cases"))
		{

			Ddr ddr = new Ddr();
			ddr.setName("IEEE14");
			ddrs.add(ddr);
			
			ddr = new Ddr();
			ddr.setName("IEEE30");
			ddrs.add(ddr);
			
			ddr = new Ddr();
			ddr.setName("IEEE57");
			ddrs.add(ddr);
			
			ddr = new Ddr();
			ddr.setName("IEEE118");
			ddrs.add(ddr);
		}
		else
		{

			Ddr ddr = new Ddr();
			ddr.setName("RTE22");
			ddrs.add(ddr);
			
			ddr = new Ddr();
			ddr.setName("Nordic32");
			ddrs.add(ddr);
			
			ddr = new Ddr();
			ddr.setName("Nordic44");
			ddrs.add(ddr);
		}

		return ddrs;
	}
}
