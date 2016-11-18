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

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.StrReplace;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;

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

			listReader = new CsvListReader(
					new FileReader(path.get().toFile()),
					CsvPreference.STANDARD_PREFERENCE);

			// Read and process the header
			// https://super-csv.github.io/super-csv/apidocs/org/supercsv/io/ICsvReader.html#get-int-
			// column indexes begin at 1
			listReader.getHeader(true);
			int columns = listReader.length();
			String[] columnNames = new String[columns];
			for (int i = 2; i <= columns; i++)
			{
				List<DsData> dsData = new ArrayList<DsData>();
				columnNames[i-1] = listReader.get(i);
				values.put(columnNames[i-1], dsData);
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
	
	public static void resolveCasePath(String uri, ComboBox<Catalog> catalogCaseSource,
			ComboBox<Case> caseSource) {
		
		Path path = Paths.get(uri);
		Path casePath = path.getParent();
		Path catalogPath = casePath.getParent();
		
		catalogCaseSource.getItems().stream().filter(c -> c.getLocation().equals(catalogPath.toString())).findAny().ifPresent(c -> catalogCaseSource.getSelectionModel().select(c));
		caseSource.getItems().stream().filter(c -> c.getLocation().equals(casePath.toString())).findAny().ifPresent(c -> caseSource.getSelectionModel().select(c));
	}

	public static void resolveDdrPath(String uri, ComboBox<Catalog> catalogDdrSource, ComboBox<Ddr> ddrSource) {

		Path ddrPath = Paths.get(uri);
		Path catalogPath = ddrPath.getParent().getParent();
		
		catalogDdrSource.getItems().stream().filter(c -> c.getLocation().equals(catalogPath.toString())).findAny().ifPresent(c -> catalogDdrSource.getSelectionModel().select(c));
		ddrSource.getItems().stream().filter(c -> c.getLocation().equals(ddrPath.toString())).findAny().ifPresent(c -> ddrSource.getSelectionModel().select(c));
	}

	public static LoadflowEngine getLoadflowEngine(String engine) {
		if (engine.equals("loadflowHades2"))
			return LoadflowEngine.HADES2;
		if (engine.equals("loadflowHelmflow"))
			return LoadflowEngine.HELMFLOW;
		
		return LoadflowEngine.NONE;
	}

	public static DsEngine getDsEngine(String engine) {
		
		if (engine.equals("OpenModelica"))
			return DsEngine.OPENMODELICA;
					
		return DsEngine.DYMOLA;
	}
	
	public static void addTooltipLineChart(LineChart chart) {

		ObservableList<XYChart.Series> displayedDsSeries = chart.getData(); 
		for (XYChart.Series<Number, Number> s : displayedDsSeries) {
			Tooltip.install(s.getNode(), new Tooltip(s.getName()));
		}
		
	}
	
	public static void addTooltipScatterChart(ScatterChart chart) {
		
		ObservableList<XYChart.Series> displayedVoltageSeries = chart.getData(); 
		for (XYChart.Series<String, Number> s : displayedVoltageSeries) {
			for (XYChart.Data<String, Number> d : s.getData()) {
				Tooltip.install(d.getNode(), new Tooltip(d.getXValue() + ": " + d.getYValue()));
			}
		}
	}


	private static final String STR_DOUBLE_NAN = "" + Double.NaN;

}
