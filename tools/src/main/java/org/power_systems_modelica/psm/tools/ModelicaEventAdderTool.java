package org.power_systems_modelica.psm.tools;

/*
 * #%L
 * Command line tools
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.dd.Event;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.events.ModelicaEventAdder;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;

import com.google.auto.service.AutoService;

import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;
import com.powsybl.tools.ToolRunningContext;
import com.powsybl.iidm.network.Identifiable;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.xml.NetworkXml;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
@AutoService(Tool.class)
public class ModelicaEventAdderTool implements Tool
{

	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "addevents";
		}

		@Override
		public String getTheme()
		{
			return "Events Adder";
		}

		@Override
		public String getDescription()
		{
			return "Add events to a given case in a Modelica file building a new Modelica file with events.";
		}

		@Override
		public Options getOptions()
		{
			Options options = new Options();
			options.addOption(Option.builder()
					.longOpt("i-mo")
					.desc("Input: Modelica file")
					.hasArg()
					.argName("MODELICA_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("i-iidm")
					.desc("Input: IIDM file")
					.hasArg()
					.argName("IIDM_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("i-ddr")
					.desc("Input: Dynamic Data Repository path")
					.hasArg()
					.argName("DDR_PATH")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("i-events")
					.desc("Input: Events file")
					.hasArg()
					.argName("EVENTS_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("o")
					.desc("Output: Modelica file")
					.hasArg()
					.argName("MODELICA_FILE")
					.required()
					.build());
			return options;
		}

		@Override
		public String getUsageFooter()
		{
			return null;
		}
	};

	@Override
	public Command getCommand()
	{
		return COMMAND;
	}

	@Override
	public void run(CommandLine cmd, ToolRunningContext context) throws Exception
	{
		String moInput = cmd.getOptionValue("i-mo");
		if (moInput == null)
		{
			System.err.println("Missing Modelica input file");
			return;
		}
		String ddrLocation = cmd.getOptionValue("i-ddr");
		if (ddrLocation == null)
		{
			System.err.println("Missing DDR path");
			return;
		}
		String iidmFilename = cmd.getOptionValue("i-iidm");
		if (iidmFilename == null)
		{
			System.err.println("Missing IIDM input file");
			return;
		}
		String moOutput = cmd.getOptionValue("o");
		if (moOutput == null)
		{
			System.err.println("Missing Modelica output file");
			return;
		}
		String eventFile = cmd.getOptionValue("i-events");
		if (eventFile == null)
		{
			System.err.println("Missing Event input file");
			return;
		}
		
		// Read Modelica input file
		ModelicaDocument mo = ModelicaParser.parse(Paths.get(moInput));

		// Read IIDM input
		Network n = NetworkXml.read(Paths.get(iidmFilename));

		// Read DDR input
		DynamicDataRepository ddr;
		ddr = DynamicDataRepositoryMainFactory.create("DYD", ddrLocation);
		ddr.connect();

		// Read events input file
		StringBuilder eventData = PathUtils.loadFile(Paths.get(eventFile));
		List<Event> events = eventsFrom(eventData.toString(), n);

		// Add events
		ModelicaEventAdder adder = new ModelicaEventAdder(mo, ddr, n, events);
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument moe = adder.addEvents(unresolved);

		// Export Modelica output file
		Path mof = Paths.get(moOutput);
		ModelicaTextPrinter.print(moe, mof, true);
	}

	public static List<Event> eventsFrom(String eventData, Network network)
	{
		List<Event> events = new ArrayList<>();
		String[] records = eventData.split("\n");
		for (String r : records)
		{
			String[] fields = r.split(",");
			String event = fields[0];
			String idelem = fields[1];
			Stream<String> paramss = Arrays.stream(fields, 2, fields.length);

			Identifiable<?> element = network.getIdentifiable(idelem);
			if (element == null)
			{
				continue;
			}
			Map<String, Object> params = paramss
					.map(ps -> ps.split("="))
					.collect(Collectors.toMap(p -> p[0], p -> p[1]));

			events.add(new Event(event, element, params));
		}
		return events;
	}
}
