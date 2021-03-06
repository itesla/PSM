package org.power_systems_modelica.psm.gui.utils.fx;

/*
 * #%L
 * Power Systems on Modelica GUI
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;

import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;

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
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class UtilsFX
{

	public static void showWarning(String title, String message)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
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
					
					DecimalFormat df = new DecimalFormat("0.0###");
					if (d.getYValue().doubleValue() < 0.0001  && d.getYValue().doubleValue() != 0.0)
						df = new DecimalFormat("0.0###E0");
					
					Tooltip.install(d.getNode(), new Tooltip(d.getXValue() + ": "
							+ df.format(d.getYValue().doubleValue())
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

	static class Delta
	{
		double x, y;
	}
}
