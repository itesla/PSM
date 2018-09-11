package org.power_systems_modelica.psm.gui.utils;

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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.ddr.dyd.AssociationMapping;
import org.power_systems_modelica.psm.ddr.dyd.Diagnostics;
import org.power_systems_modelica.psm.ddr.dyd.ModelMapping;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.ConvertedCase;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public class Utils
{
	public static boolean isHades2Available()
	{
		return System.getProperty("os.name").startsWith("Linux");
	}

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

	public static StringBuilder getCheckDdrResult(Diagnostics diagnostics) {
		
		Map<String, String> xmlMapping = diagnostics.getXmlErrors();
		Map<String, ModelMapping> modelMapping = diagnostics.getModelMapping();
		Map<String, AssociationMapping> associationMapping = diagnostics.getAssociationMapping();

		StringBuilder ddrDuplicates = new StringBuilder();

		for (String key : modelMapping.keySet())
		{
			if (modelMapping.get(key).isDuplicated())
				ddrDuplicates.append("\t - " + modelMapping.get(key).toString() + "\n");
		}
		
		for (String key : associationMapping.keySet())
		{
			if (associationMapping.get(key).isDuplicated())
				ddrDuplicates.append("\t - " + associationMapping.get(key).toString() + "\n");
		}

		StringBuilder setDuplicates = new StringBuilder();

		if (diagnostics.isSetDuplicated())
		{
			setDuplicates
					.append(diagnostics.getSets().stream().map(s -> "\t - " + s.toString())
							.collect(Collectors.joining("\n")) + "\n");
		}

		StringBuilder paramDuplicates = new StringBuilder();

		if (diagnostics.isParameterDuplicated())
		{
			paramDuplicates.append(
					diagnostics.getParameters().stream().map(p -> "\t - " + p.toString())
							.collect(Collectors.joining("\n")) + "\n");
		}

		StringBuilder ddrXml = new StringBuilder();

		for (String key : xmlMapping.keySet())
		{
			ddrXml.append(key + " - " + xmlMapping.get(key) + "\n");
		}

		StringBuilder ddrContent = new StringBuilder();

		if (ddrXml.length() > 0)
		{
			ddrContent.append("Xml files with errors:\n");
			ddrContent.append(ddrXml);
		}
		if (ddrDuplicates.length() > 0)
		{
			ddrContent.append("Duplicated model mappings:\n");
			ddrContent.append(ddrDuplicates);
		}
		if (setDuplicates.length() > 0 || paramDuplicates.length() > 0)
		{
			ddrContent.append("Duplicated parameter mappings:\n");
			ddrContent.append(setDuplicates);
			ddrContent.append(paramDuplicates);
		}

		return ddrContent;
	}
	
	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}

}
