package org.power_systems_modelica.psm.gui.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class Utils
{
	public static String padString(String message, int length)
	{
		int needed = length - message.length();
		if (needed <= 0)
			return message;
		return message + StringUtils.repeat(" ", needed);
	}

	public static String replaceLast(String string, String substring, String replacement)
	{
		int index = string.lastIndexOf(substring);
		if (index == -1)
			return string;
		return string.substring(0, index) + replacement
				+ string.substring(index + substring.length());
	}

	public static void showWarning(String title, String message)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void resolveCasePath(String uri, ComboBox<Catalog> catalogCaseSource,
			ComboBox<Case> caseSource)
	{
		Path casePath;
		if (uri.endsWith(".xml"))
		{
			Path path = Paths.get(uri);
			casePath = path.getParent();
		}
		else
			casePath = Paths.get(uri);
		Path catalogPath = casePath.getParent();
		catalogCaseSource.getItems().stream().filter(c -> {
			try
			{
				return Files.isSameFile(Paths.get(c.getLocation()), catalogPath);
			}
			catch (IOException e)
			{
			}
			return false;
		}).findAny().ifPresent(c -> catalogCaseSource.getSelectionModel().select(c));
		caseSource.getItems().stream().filter(c -> {
			try
			{
				return Files.isSameFile(Paths.get(c.getLocation()), casePath);
			}
			catch (IOException e)
			{
			}
			return false;
		}).findAny().ifPresent(c -> caseSource.getSelectionModel().select(c));
	}

	public static void resolveDdrPath(String uri, ComboBox<Catalog> catalogDdrSource,
			ComboBox<Ddr> ddrSource)
	{
		Path ddrPath = Paths.get(uri).normalize();
		Path catalogPath = ddrPath.getParent().getParent();
		catalogDdrSource.getItems().stream().filter(c -> {
			try
			{
				return Files.isSameFile(Paths.get(c.getLocation()), catalogPath);
			}
			catch (IOException e)
			{
			}
			return false;
		}).findAny().ifPresent(c -> catalogDdrSource.getSelectionModel().select(c));
		ddrSource.getItems().stream().filter(c -> {
			try
			{
				return Files.isSameFile(Paths.get(c.getLocation()), ddrPath);
			}
			catch (IOException e)
			{
			}
			return false;
		}).findAny().ifPresent(c -> ddrSource.getSelectionModel().select(c));
	}

	public static void resolveConvertedCasePath(String uri, ComboBox<Catalog> catalogCaseSource,
			ComboBox<ConvertedCase> caseSource)
	{
		Path casePath;
		if (uri.endsWith(".mo") || uri.endsWith(".xml"))
		{
			Path path = Paths.get(uri);
			casePath = path.getParent();
		}
		else
			casePath = Paths.get(uri);
		Path catalogPath = casePath.getParent();
		catalogCaseSource.getItems().stream().filter(c -> {
			try
			{
				return Files.isSameFile(Paths.get(c.getLocation()), catalogPath);
			}
			catch (IOException e)
			{
			}
			return false;
		}).findAny().ifPresent(c -> catalogCaseSource.getSelectionModel().select(c));
		caseSource.getItems().stream().filter(c -> {
			try
			{
				return Files.isSameFile(Paths.get(c.getLocation()), casePath);
			}
			catch (IOException e)
			{
			}
			return false;
		}).findAny().ifPresent(c -> caseSource.getSelectionModel().select(c));
	}

	public static LoadflowEngine getLoadflowEngine(String engine)
	{
		if (engine.equals("loadflowHades2"))
			return LoadflowEngine.HADES2;
		if (engine.equals("loadflowHelmflow"))
			return LoadflowEngine.HELMFLOW;
		return LoadflowEngine.NONE;
	}

	public static DsEngine getDsEngine(String engine)
	{
		if (engine.equals("OpenModelica"))
			return DsEngine.OPENMODELICA;
		if (engine.equals("Dymola"))
			return DsEngine.DYMOLA;

		return DsEngine.FAKE;
	}

	public static void addTooltipLineChart(LineChart<Number, Number> chart)
	{
		ObservableList<XYChart.Series<Number, Number>> displayedDsSeries = chart.getData();
		for (XYChart.Series<Number, Number> s : displayedDsSeries)
		{
			Tooltip.install(s.getNode(), new Tooltip(s.getName()));
		}
	}

	public static void addTooltipLineChartPosition(LineChart<Number, Number> chart, String xVar,
			String xUnit,
			String yVar, String yUnit)
	{
		NumberAxis xAxis = (NumberAxis) chart.getXAxis();
		NumberAxis yAxis = (NumberAxis) chart.getYAxis();
		ObservableList<XYChart.Series<Number, Number>> displayedDsSeries = chart.getData();
		for (XYChart.Series<Number, Number> s : displayedDsSeries)
		{
			ObjectProperty<Point2D> mouseLocationInScene = new SimpleObjectProperty<>();
			Tooltip tooltip = new Tooltip();
			s.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, evt -> {
				if (!tooltip.isShowing())
				{
					mouseLocationInScene.set(new Point2D(evt.getSceneX(), evt.getSceneY()));
				}
			});
			tooltip.textProperty().bind(Bindings.createStringBinding(() -> {
				if (mouseLocationInScene.isNull().get())
				{
					return "";
				}
				double xInXAxis = xAxis.sceneToLocal(mouseLocationInScene.get()).getX();
				double x = xAxis.getValueForDisplay(xInXAxis).doubleValue();
				double yInYAxis = yAxis.sceneToLocal(mouseLocationInScene.get()).getY();
				double y = yAxis.getValueForDisplay(yInYAxis).doubleValue();
				return s.getName() + "\n" + xVar + ": " + String.format("%.3f %s", x, xUnit) + "\n"
						+ yVar + ": "
						+ String.format("%.3f %s", y, yUnit);
			}, mouseLocationInScene, xAxis.lowerBoundProperty(), xAxis.upperBoundProperty(),
					yAxis.lowerBoundProperty(),
					yAxis.upperBoundProperty()));
			Tooltip.install(s.getNode(), tooltip);
		}
	}

	public static void addTooltipScatterChart(ScatterChart<String, Number> chart, String unit)
	{
		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = chart.getData();
		for (XYChart.Series<String, Number> s : displayedVoltageSeries)
		{
			for (XYChart.Data<String, Number> d : s.getData())
			{
				Tooltip.install(d.getNode(),
						new Tooltip(d.getXValue() + ": " + d.getYValue() + " " + unit));
			}
		}
	}

	public static void addTooltipComparisonChart(ScatterChart<String, Number> chart,
			WorkflowResult results,
			String variable, String unit)
	{

		List<BusData> buses = results.getAllBusesValues();

		ObservableList<XYChart.Series<String, Number>> displayedVoltageSeries = chart.getData();
		for (XYChart.Series<String, Number> s : displayedVoltageSeries)
		{
			for (XYChart.Data<String, Number> d : s.getData())
			{
				String busName = d.getXValue();
				buses.stream().filter(b -> b.getName().equals(busName)).findAny().ifPresent(b -> {
					String comparisonString = "\nHelmflow: " + b.getData().get(variable)[0] + " "
							+ unit + "\n"
							+ "Hades2: " + b.getData().get(variable)[1] + " " + unit;
					Tooltip.install(d.getNode(), new Tooltip(d.getXValue() + ": "
							+ String.format("%,.4f%%", d.getYValue().doubleValue() * 100)
							+ comparisonString));
				});
			}
		}
	}

	public static void setDragablePane(Node node)
	{
		// allow the clock background to be used to drag the clock around.
		final Delta dragDelta = new Delta();
		node.setOnMousePressed(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent mouseEvent)
			{
				// record a delta distance for the drag and drop operation.
				dragDelta.x = node.getLayoutX() - mouseEvent.getScreenX();
				dragDelta.y = node.getLayoutY() - mouseEvent.getScreenY();
			}
		});
		node.setOnMouseDragged(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent mouseEvent)
			{
				node.setLayoutX(mouseEvent.getScreenX() + dragDelta.x);
				node.setLayoutY(mouseEvent.getScreenY() + dragDelta.y);
			}
		});

	}

	public static Properties getConversionProperties(Case cs, Ddr ddr, LoadflowEngine le,
			boolean onlyMainConnectedComponent, DsEngine dse) throws IOException
	{
		Properties properties = new Properties();
		properties.setProperty("casePath",
				PathUtils.findCasePath(Paths.get(cs.getLocation())).toString());
		properties.setProperty("ddrPath", ddr.getLocation());

		String loadflowId;
		switch (le)
		{
		case HADES2:
			loadflowId = "loadflowHades2";
			break;
		case HELMFLOW:
			loadflowId = "loadflowHelmflow";
			break;
		default:
			loadflowId = "none";
			break;
		}
		properties.setProperty("loadflowEngine", loadflowId);
		properties.setProperty("onlyMainConnectedComponent",
				Boolean.toString(onlyMainConnectedComponent));

		String dsId;
		switch (dse)
		{
		case OPENMODELICA:
			dsId = "OpenModelica";
			break;
		case DYMOLA:
			dsId = "Dymola";
			break;
		default:
			dsId = "fake";
			break;
		}
		properties.setProperty("fullModelInitializationEngine", dsId);

		return properties;
	}

	public static Properties getSimulationProperties(ConvertedCase cs, ObservableList<Event> events,
			DsEngine dse,
			String stopTime, String stepBySecond, boolean createFilteredMat) throws IOException
	{
		Properties properties = new Properties();

		properties.setProperty("casePath",
				PathUtils.findCasePath(Paths.get(cs.getLocation())).toString());

		if (!events.isEmpty())
			properties.setProperty("events",
					(String) events.stream().map(Object::toString)
							.collect(Collectors.joining("\n")));

		String simulationEngine = dse.equals(DsEngine.OPENMODELICA) ? "OpenModelica" : "Dymola";
		properties.setProperty("dsEngine", simulationEngine);
		properties.setProperty("dsStopTime", stopTime);
		properties.setProperty("dsStepBySecond", stepBySecond);
		properties.setProperty("createFilteredMat", Boolean.toString(createFilteredMat));

		return properties;
	}

	public static String randomDouble(double rangeMin, double rangeMax)
	{
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		symbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.#####", symbols);
		Random r = new Random();
		return df.format(rangeMin + (rangeMax - rangeMin) * r.nextDouble());
	}
	
	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}

	static class Delta
	{
		double x, y;
	}
}
