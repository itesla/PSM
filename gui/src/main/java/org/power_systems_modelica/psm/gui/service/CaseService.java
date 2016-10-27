package org.power_systems_modelica.psm.gui.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CaseService {

	public static ObservableList<Case> getCases(Catalog catalog) {
		LOG.debug("getCases " + catalog.getName());
		ObservableList<Case> cases = FXCollections.observableArrayList();

		if (catalog.getName().equals("Reference cases")) {
			Path catalogPath = Paths.get(catalog.getLocation());
			try
			{
				listFiles(cases, catalogPath);
			}
			catch (IOException e)
			{
			  e.printStackTrace();
			}
			
		} 
		else {
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

	private static boolean listFiles(ObservableList<Case> cases, Path path) throws IOException {
		
		boolean eq = false, sv = false, tp = false;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					if (listFiles(cases, entry)) {
						Case c = new Case();
						c.setName(entry.getFileName().toString());
						c.setLocation(entry.toString());
						cases.add(c);
					}
				}
				else if (entry.toString().endsWith("ME.xml"))
					return true;
				else if (entry.toString().endsWith("EQ.xml"))
					eq = true;
				else if (entry.toString().endsWith("SV.xml"))
					sv = true;
				else if (entry.toString().endsWith("TP.xml"))
					tp = true;
			}
		}
		if (eq && sv && tp)
			return true;
		
		return false;
	}

	private static final Logger LOG = LoggerFactory.getLogger(CaseService.class);
}
