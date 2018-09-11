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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;

/**
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 *
 *         Adapted to PSM: apply filter to show only tools for this app
 */
public class Main
{
	private static final String TOOL_NAME = "psm";

	private Main()
	{
	}

	static final String psmPackage = getPackage3(Main.class);

	private static String getPackage3(Class<?> c)
	{
		String[] packages = c.getName().split("\\.");
		return String.format("%s.%s.%s", packages[0], packages[1], packages[2]);
	}

	private static boolean isPsm(Tool t)
	{
		return getPackage3(t.getClass()).equals(psmPackage);
	}

	private static void printUsage()
	{
		StringBuilder usage = new StringBuilder();
		usage.append("Usage: " + TOOL_NAME + " COMMAND [ARGS]\n\nAvailable commands are:\n\n");

		List<Tool> allTools = Lists.newArrayList(ServiceLoader.load(Tool.class))
				.stream()
				.filter(t -> !t.getCommand().isHidden())
				.collect(Collectors.toList());
		allTools = allTools.stream().filter(Main::isPsm).collect(Collectors.toList());

		// group commands by theme
		Multimap<String, Tool> toolsByTheme = Multimaps.index(allTools, new Function<Tool, String>()
		{
			@Override
			public String apply(Tool tool)
			{
				return tool.getCommand().getTheme();
			}
		});

		for (Map.Entry<String, Collection<Tool>> entry : toolsByTheme.asMap().entrySet())
		{
			String theme = entry.getKey();
			List<Tool> tools = new ArrayList<>(entry.getValue());
			Collections.sort(tools, new Comparator<Tool>()
			{
				@Override
				public int compare(Tool t1, Tool t2)
				{
					return t1.getCommand().getName().compareTo(t2.getCommand().getName());
				}
			});
			usage.append(theme != null ? theme : "Others").append(":\n");
			for (Tool tool : tools)
			{
				usage.append(String.format("   %-20s %s", tool.getCommand().getName(),
						tool.getCommand().getDescription())).append("\n");
			}
			usage.append("\n");
		}

		System.err.print(usage);
		System.exit(1);
	}

	private static Options getOptionsWithHelp(Options options)
	{
		Options optionsWithHelp = new Options();
		options.getOptions().forEach(o -> optionsWithHelp.addOption((Option) o));
		optionsWithHelp.addOption(
				Option.builder()
						.longOpt("help")
						.desc("display the help and quit")
						.build());
		return optionsWithHelp;
	}

	private static void printCommandUsage(Command command)
	{
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(
				80,
				TOOL_NAME + " " + command.getName(), "",
				getOptionsWithHelp(command.getOptions()),
				"\n" + Objects.toString(command.getUsageFooter(), ""),
				true);
	}

	private static Tool findTool(String commandName)
	{
		for (Tool tool : ServiceLoader.load(Tool.class))
		{
			if (isPsm(tool) && tool.getCommand().getName().equals(commandName))
			{
				return tool;
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException
	{
		if (args.length < 1)
		{
			printUsage();
		}

		Tool tool = findTool(args[0]);
		if (tool == null)
		{
			printUsage();
		}

		try
		{
			CommandLineParser parser = new DefaultParser();
			CommandLine line = parser.parse(
					getOptionsWithHelp(tool.getCommand().getOptions()),
					Arrays.copyOfRange(args, 1, args.length));
			if (line.hasOption("help"))
			{
				printCommandUsage(tool.getCommand());
			}
			else
			{
				tool.run(line);
			}
		}
		catch (ParseException e)
		{
			System.err.println("Error: " + e.getMessage());
			printCommandUsage(tool.getCommand());
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}
}
