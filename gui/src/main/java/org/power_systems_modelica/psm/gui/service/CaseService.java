package org.power_systems_modelica.psm.gui.service;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CaseService {

	public static ObservableList<Case> getCases(String catalogName) {
		LOG.debug("getCases " + catalogName);
		ObservableList<Case> cases = FXCollections.observableArrayList();

		if (catalogName.equals("Reference cases"))
		{
			Case c = new Case();
			c.setName("IEEE14");
			c.setDescription("A portion of the American Electric Power System (in the Midwestern US) as of February, 1962.");
			c.setLocation("/home/demiguelm/psm/data/test/ieee14/ieee14bus_EQ.xml");
			c.setSize(14);
			cases.add(c);

			c = new Case();
			c.setName("IEEE30");
			c.setDescription("A portion of the American Electric Power System (in the Midwestern US) as of December, 1961.");
			c.setLocation("/data/psm/samples/ieee30");
			c.setSize(30);
			cases.add(c);

			c = new Case();
			c.setName("IEEE57");
			c.setDescription("A portion of the American Electric Power System (in the Midwestern US) as it was in the early 1960's.");
			c.setLocation("/data/psm/samples/ieee57");
			c.setSize(57);
			cases.add(c);

			c = new Case();
			c.setName("IEEE118");
			c.setDescription("A portion of the American Electric Power System (in the Midwestern US) as of December, 1962.");
			c.setLocation("/data/psm/samples/ieee118");
			c.setSize(118);
			cases.add(c);
		}
		else
		{
			Case c = new Case();
			c.setName("RTE22");
			c.setDescription("RTE 22 bus internal test case");
			c.setLocation("/data/psm/private_samples/rte22");
			c.setSize(22);
			cases.add(c);

			c = new Case();
			c.setName("Nordic32");
			c.setDescription("Nordic 32 bus internal test case");
			c.setLocation("/data/psm/private_samples/nordic32");
			c.setSize(32);
			cases.add(c);

			c = new Case();
			c.setName("Nordic44");
			c.setDescription("Nordic 44 bus internal test case");
			c.setLocation("/data/psm/private_samples/nordic44");
			c.setSize(44);
			cases.add(c);
		}

		return cases;
	}

	private static final Logger LOG = LoggerFactory.getLogger(CaseService.class);
}
