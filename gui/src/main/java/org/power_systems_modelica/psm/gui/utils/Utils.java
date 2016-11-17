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
import java.util.Optional;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.gui.model.DsData;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.StrReplace;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utils
{

	public static final Path	DATA_TEST		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test");

	public static final Path	DATA_TMP		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
	
	public static final Path	LIBRARY		= Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("test").resolve("library");
	
	public static String translateLocation(String input)
	{

		String[] inputTokens = input.split("/");
		List<String> outputTokens = new ArrayList<String>();
		for (String token : inputTokens)
		{
			if (token.startsWith("$"))
			{
				Path path = Paths.get(System.getenv(token.replace("$", "")));
				token = path.toString();
			}
			outputTokens.add(token);
		}

		Path path = null;
		for (String token : outputTokens)
		{
			if (path == null)
			{
				path = Paths.get(token);
				continue;
			}

			Path p = path.resolve(token);
			path = p;
		}

		return path.toString();
	}

	public static Path findCasePath(Path path) throws IOException
	{

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path))
		{
			for (Path entry : stream)
			{
				if (entry.toString().endsWith("ME.xml"))
					return entry;
				else if (entry.toString().endsWith("EQ.xml"))
					return entry;
			}
		}

		return null;
	}

	public static StringBuilder loadFile(String location, String file) throws IOException
	{

		StringBuilder stringBuilder = new StringBuilder();

		Path path = Paths.get(location).resolve(file);
		InputStream inputStream = Files.newInputStream(path);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = bufferedReader.readLine()) != null)
		{
			stringBuilder.append(line).append("\n");
		}
		bufferedReader.close();
		inputStream.close();

		return stringBuilder;
	}

	public static void saveFile(String location, String file, StringBuilder ddrContent)
			throws IOException
	{
		// TODO Auto-generated method stub

		Path path = Paths.get(location).resolve(file);
		OutputStream outputStream = Files.newOutputStream(path);
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.append(ddrContent);
		bufferedWriter.close();
		outputStream.close();
	}

	public static void showWarning(String title, String message)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static boolean existsFile(String location, String file)
	{

		try
		{
			Path path = Paths.get(location).resolve(file);
			return Files.exists(path);
		}
		catch (InvalidPathException e)
		{
			return false;
		}
	}

	public static Map<String, List<DsData>> readVariableColumnsWithCsvListReader(
			String location,
			String extension) throws Exception
	{

		Map<String, List<DsData>> values = new HashMap<String, List<DsData>>();
		ICsvListReader listReader = null;
		try
		{
			Optional<Path> path = Files
					.walk(Paths.get(location), FileVisitOption.FOLLOW_LINKS)
					.filter((p) -> !p.toFile().isDirectory()
							&& p.toFile().getAbsolutePath().endsWith(extension))
					.findFirst();
			if (!path.isPresent()) return null;

			System.out.println("location: " + location +
					" extension: " + extension +
					" first file found: " + path.get().toString());
			listReader = new CsvListReader(
					new FileReader(path.get().toFile()),
					CsvPreference.STANDARD_PREFERENCE);

			// Read and process the header
			listReader.getHeader(true);
			int columns = listReader.length();
			System.out.println("discovering column names from header");
			String[] columnNames = new String[columns];
			for (int i = 1; i < columns; i++)
			{
				List<DsData> dsData = new ArrayList<DsData>();
				columnNames[i] = listReader.get(i);
				values.put(columnNames[i], dsData);
				System.out.println("    " + i + "\t" + columnNames[i]);
			}
			final CellProcessor[] processors = getProcessors(columns);

			while ((listReader.read()) != null)
			{
				final List<Object> columnValues = listReader.executeProcessors(processors);
				Double time = (Double) columnValues.get(0);
				for (int i = 1; i < columns; i++)
				{
					List<DsData> dsData = values.get(columnNames[i]);
					dsData.add(new DsData(time, (Double) columnValues.get(i)));
				}
			}
		}
		finally
		{
			if (listReader != null)
			{
				listReader.close();
			}
		}
		return values;
	}

	private static CellProcessor[] getProcessors(int num)
	{
		CellProcessor[] processors = new CellProcessor[num];
		for (int i = 0; i < num; i++)
			processors[i] = new StrReplace("null", STR_DOUBLE_NAN, new ParseDouble());
		return processors;
	}

	private static final String STR_DOUBLE_NAN = "" + Double.NaN;
}
