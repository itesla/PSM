package org.power_systems_modelica.psm.gui.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.WorkflowService.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowService.LoadflowEngine;

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

	public static void showWarning(String title, String message)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
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
	
	public static void addTooltipLineChartPosition(LineChart chart, String xVar, String xUnit, String yVar, String yUnit) {

		NumberAxis xAxis = (NumberAxis) chart.getXAxis();
		NumberAxis yAxis = (NumberAxis) chart.getYAxis();
		ObservableList<XYChart.Series> displayedDsSeries = chart.getData(); 
		for (XYChart.Series<Number, Number> s : displayedDsSeries) {

			ObjectProperty<Point2D> mouseLocationInScene = new SimpleObjectProperty<>();

		    Tooltip tooltip = new Tooltip();

		    s.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, evt -> {
		        if (! tooltip.isShowing()) {
		            mouseLocationInScene.set(new Point2D(evt.getSceneX(), evt.getSceneY()));
		        }
		    });

		    tooltip.textProperty().bind(Bindings.createStringBinding(() -> {
		        if (mouseLocationInScene.isNull().get()) {
		            return "" ;
		        }
		        double xInXAxis = xAxis.sceneToLocal(mouseLocationInScene.get()).getX() ;
		        double x = xAxis.getValueForDisplay(xInXAxis).doubleValue();
		        double yInYAxis = yAxis.sceneToLocal(mouseLocationInScene.get()).getY() ;
		        double y = yAxis.getValueForDisplay(yInYAxis).doubleValue() ;
		        return s.getName() + "\n" + xVar + ": " + String.format("%.3f %s", x, xUnit) + "\n" + yVar + ": " + String.format("%.3f %s", y, yUnit);
		    }, mouseLocationInScene, xAxis.lowerBoundProperty(), xAxis.upperBoundProperty(),
		    yAxis.lowerBoundProperty(), yAxis.upperBoundProperty()));

		    Tooltip.install(s.getNode(), tooltip);
		}
		
	}

	public static void addTooltipScatterChart(ScatterChart chart, String unit) {
		
		ObservableList<XYChart.Series> displayedVoltageSeries = chart.getData(); 
		for (XYChart.Series<String, Number> s : displayedVoltageSeries) {
			for (XYChart.Data<String, Number> d : s.getData()) {
				Tooltip.install(d.getNode(), new Tooltip(d.getXValue() + ": " + d.getYValue() + " " + unit));
			}
		}
	}
	
	public static void addTooltipComparisonChart(ScatterChart chart, WorkflowResult results, String variable, String unit) {
		
		List<BusData> buses = results.getAllBusesValues();
		
		ObservableList<XYChart.Series> displayedVoltageSeries = chart.getData(); 
		for (XYChart.Series<String, Number> s : displayedVoltageSeries) {
			for (XYChart.Data<String, Number> d : s.getData()) {
				String busName = d.getXValue();
				
				buses.stream().filter(b -> b.getName().equals(busName)).findAny().ifPresent(b -> {
					
					String comparisonString = "\nHelmflow: " + b.getData().get(variable)[0] + " " + unit + "\n"
							+ "Hades2: " + b.getData().get(variable)[1] + " " + unit; 
					
					Tooltip.install(d.getNode(), new Tooltip(d.getXValue() + ": " + String.format("%,.4f%%", d.getYValue().doubleValue()*100) + comparisonString));
				});
				
			}
		}
	}
	
	public static void setDragablePane(Node node) {
    	// allow the clock background to be used to drag the clock around.
        final Delta dragDelta = new Delta();
        node.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = node.getLayoutX() - mouseEvent.getScreenX();
            dragDelta.y = node.getLayoutY() - mouseEvent.getScreenY();
          }
        });
        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
        	  node.setLayoutX(mouseEvent.getScreenX() + dragDelta.x);
        	  node.setLayoutY(mouseEvent.getScreenY() + dragDelta.y);
          }
        });
		
	}
	
	static class Delta { double x, y; }
}
