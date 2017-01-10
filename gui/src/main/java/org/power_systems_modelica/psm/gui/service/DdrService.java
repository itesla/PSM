package org.power_systems_modelica.psm.gui.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DdrService {

	public static ObservableList<Ddr> getDdrs(Catalog catalog) {
		ObservableList<Ddr> ddrs = FXCollections.observableArrayList();
		Path catalogPath = Paths.get(catalog.getLocation());
		try {
			listFiles(ddrs, catalogPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ddrs;
	}

	private static boolean listFiles(ObservableList<Ddr> ddrs, Path path) throws IOException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					if (listFiles(ddrs, entry)) {
						Ddr d = new Ddr();
						d.setName(getDdrName(entry));
						d.setLocation(entry.toString());
						ddrs.add(d);
					}
				} else if (entry.toString().endsWith(".dyd"))
					return true;
			}
		}

		return false;
	}

	private static String getDdrName(Path entry) {
		
		String previousName = null;
		String lastName = null;
		Iterator<Path> it = entry.iterator();
		while (it.hasNext()) {

			previousName = lastName;
			lastName = it.next().getFileName().toString();
		}
		
		return previousName + "_" + lastName;
	}
}
