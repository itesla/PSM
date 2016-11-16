package org.power_systems_modelica.psm.gui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.gui.model.DsData;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utils {

	public static String translateLocation(String input) {

		String[] inputTokens = input.split("/");
		List<String> outputTokens = new ArrayList<String>();
		for (String token : inputTokens) {
			if (token.startsWith("$")) {
				Path path = Paths.get(System.getenv(token.replace("$", "")));
				token = path.toString();
			}
			outputTokens.add(token);
		}

		Path path = null;
		for (String token : outputTokens) {
			if (path == null) {
				path = Paths.get(token);
				continue;
			}

			Path p = path.resolve(token);
			path = p;
		}

		return path.toString();
	}

	public static Path findCasePath(Path path) throws IOException {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				if (entry.toString().endsWith("ME.xml"))
					return entry;
				else if (entry.toString().endsWith("EQ.xml"))
					return entry;
			}
		}

		return null;
	}

	public static StringBuilder loadFile(String location, String file) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();

		Path path = Paths.get(location).resolve(file);
		InputStream inputStream = Files.newInputStream(path);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line).append("\n");
		}
		bufferedReader.close();
		inputStream.close();

		return stringBuilder;
	}

	public static void saveFile(String location, String file, StringBuilder ddrContent) throws IOException {
		// TODO Auto-generated method stub

		Path path = Paths.get(location).resolve(file);
		OutputStream outputStream = Files.newOutputStream(path);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.append(ddrContent);
		bufferedWriter.close();
		outputStream.close();
	}

	public static void showWarning(String title, String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static boolean existsFile(String location, String file) {

		try {
			Path path = Paths.get(location).resolve(file);
			return Files.exists(path);
		} catch (InvalidPathException e) {
			return false;
		}
	}

	private static CellProcessor[] getProcessors(int num) {

		CellProcessor[] processors = new CellProcessor[num];
		
		for (int i = 0; i < num; i++) {
			processors[i] = new ParseDouble();
		}

		return processors;
	}

	public static Map<String, List<DsData>> readVariableColumnsWithCsvListReader(String location, String extension) throws Exception {

		Map<String, List<DsData>> values = new HashMap<String, List<DsData>>();
		ICsvListReader listReader = null;
		try {

			List<Path> paths = Files.walk(Paths.get(location), FileVisitOption.FOLLOW_LINKS)
					.filter((p) -> !p.toFile().isDirectory() && p.toFile().getAbsolutePath().endsWith(extension))
					.collect(Collectors.toList());
			
			System.out.println("location: " + location + " extension: " + extension + " paths: " + paths.toString());
			listReader = new CsvListReader(new FileReader(paths.get(0).toFile()), CsvPreference.STANDARD_PREFERENCE);

			listReader.getHeader(true); // skip the header (can't be used with
										// CsvListReader)
			
			int columns = listReader.length();
			for (int i = 1; i < columns; i++) {
				
				List<DsData> dsData = new ArrayList<DsData>();
				values.put(listReader.get(i), dsData);
			}
			
			while ((listReader.read()) != null) {

				// use different processors depending on the number of columns
				final CellProcessor[] processors = getProcessors(columns);

				final List<Object> customerList = listReader.executeProcessors(processors);
				
				for (int i = 1; i < columns; i++) {
					
					List<DsData> dsData = values.get(listReader.get(i));
					dsData.add(new DsData((Double) customerList.get(0), (Double) customerList.get(i)));
				}
				
			}
			
		} finally {
			if (listReader != null) {
				listReader.close();
			}
		}
		return values;
	}

}
