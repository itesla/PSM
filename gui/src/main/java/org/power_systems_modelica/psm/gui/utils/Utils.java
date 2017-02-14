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

/*import javafx.beans.binding.Bindings;
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
import javafx.scene.input.MouseEvent;*/

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

	public static Properties getSimulationProperties(ConvertedCase cs, List<Event> events,
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
}
