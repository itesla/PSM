package org.power_systems_modelica.psm.gui.service;

import static org.power_systems_modelica.psm.workflow.Workflow.TC;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.StaticNetworkImporterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Network;

public class CaseService {

	public static Network importCase(Case input) throws WorkflowCreationException {

		Network n = null;
		try {
			Path casePath = PathUtils.findCasePath(Paths.get(input.getLocation()));

			Workflow w = WF(TD(StaticNetworkImporterTask.class, "importer0", TC("source", casePath.toString())));
			w.start();

			n = (Network) w.getResults("network");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;
	}

	public static Case getCase(Catalog catalog, Path path) throws IOException {

		List<Case> cases = getCases(catalog);
		
		for (Case c: cases) {
			if (Files.isSameFile(Paths.get(c.getLocation()), path))
				return c;
		}

		return null;
	}

	public static List<Case> getCases(Catalog catalog) {
		LOG.debug("getCases " + catalog.getName());
		List<Case> cases = new ArrayList<>();

		Path catalogPath = Paths.get(catalog.getLocation());
		try {
			searchCatalogCases(cases, catalogPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cases;
	}

	public static ConvertedCase getConvertedCase(Catalog catalog, Path path) throws IOException {

		List<ConvertedCase> cases = getConvertedCases(catalog);
		
		for (ConvertedCase c: cases) {
			if (Files.isSameFile(Paths.get(c.getLocation()), path))
				return c;
		}

		return null;
	}
	
	public static List<ConvertedCase> getConvertedCases(Catalog catalog) {

		List<ConvertedCase> cases = new ArrayList<>();

		Path catalogPath = Paths.get(catalog.getLocation());
		try {
			searchCatalogConvertedCases(cases, catalogPath);
		} catch (IOException e) {
			e.printStackTrace();
		}


		return cases;
	}

	private static boolean searchCatalogCases(List<Case> cases, Path path) throws IOException {

		boolean eq = false, sv = false, tp = false;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					if (searchCatalogCases(cases, entry)) {
						Case c = new Case();
						c.setName(entry.getFileName().toString());
						c.setLocation(entry.toString());
						cases.add(c);

						Properties properties = getCaseProperties(entry);
						if (!properties.isEmpty()) {

							String description = properties.getProperty("description");
							int size = Integer.parseInt(properties.getProperty("size"));
							String format = properties.getProperty("format");

							c.setDescription(description);
							c.setSize(size);
							c.setFormat(format);
						}
					}
				} else if (entry.toString().endsWith("ME.xml"))
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

	private static boolean searchCatalogConvertedCases(List<ConvertedCase> cases, Path path)
			throws IOException {

		boolean eq = false, sv = false, tp = false;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (Files.isDirectory(entry)) {
					if (searchCatalogConvertedCases(cases, entry)) {
						
						if (!Files.exists(entry.resolve(entry.getFileName().toString() + ".mo")))
							continue;

						ConvertedCase c = new ConvertedCase();
						c.setName(entry.getFileName().toString());
						c.setLocation(entry.toString());
						cases.add(c);

						Properties properties = getConvertedCaseProperties(entry);
						if (!properties.isEmpty()) {

							String ddrLocation = properties.getProperty("ddrLocation");
							String loadflowEngine = properties.getProperty("loadflowEngine");
							String onlyMainConnectedComponent = properties.getProperty("onlyMainConnectedComponent");
							String fullModelInitializationEgine = properties.getProperty("fullModelInitializationEgine");

							c.setDdrLocation(ddrLocation);
							c.setLoadflowEngine(loadflowEngine);
							c.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
							c.setFullModelInitializationEgine(fullModelInitializationEgine);
						}
					}
				} else if (entry.toString().endsWith("ME.xml"))
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

	public static String getDefaultDdrLocation(Case c) {

		Path path = Paths.get(c.getLocation());
		Properties properties = getCaseProperties(path);

		if (!properties.isEmpty()) {
			String ddrLocation = properties.getProperty("ddrLocation");
			return path.resolve(ddrLocation).toString();
		}

		return null;
	}

	private static Properties getCaseProperties(Path path) {

		Properties properties = new Properties();
		try {
			try (InputStream is = Files.newInputStream(path.resolve("case.properties"))) {
				properties.load(is);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		return properties;
	}

	private static Properties getConvertedCaseProperties(Path path) {

		Properties properties = new Properties();
		try {
			try (InputStream is = Files.newInputStream(path.resolve("convertedCase.properties"))) {
				properties.load(is);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
		return properties;
	}

	public static void saveConvertedCaseProperties(String caseLocation, String ddrLocation, String loadflowEngine, boolean onlyMainConnectedComponent, String fullModelInitializationEgine) {

		try {
			Path defaultFile = Paths.get(caseLocation).resolve("convertedCase.properties");
			OutputStream out = Files.newOutputStream(defaultFile);

			Properties defaultProperties = new Properties();
			defaultProperties.setProperty("ddrLocation", ddrLocation);
			defaultProperties.setProperty("loadflowEngine", loadflowEngine);
			defaultProperties.setProperty("onlyMainConnectedComponent", onlyMainConnectedComponent?"Only main connected component":"All connected components");
			defaultProperties.setProperty("fullModelInitializationEgine", fullModelInitializationEgine);
			defaultProperties.store(out, "Converted case properties");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final Logger LOG = LoggerFactory.getLogger(CaseService.class);
}
