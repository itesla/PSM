package org.power_systems_modelica.psm.tools;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import eu.itesla_project.commons.tools.Command;
import eu.itesla_project.commons.tools.Tool;

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

import org.apache.commons.cli.*;

/**
 *
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 */
public class Main
{
	private static final String TOOL_NAME = "psm";

	private Main()
	{
	}

	private static void printUsage()
	{
		StringBuilder usage = new StringBuilder();
		usage.append("Usage: " + TOOL_NAME + " COMMAND [ARGS]\n\nAvailable commands are:\n\n");

		List<Tool> allTools = Lists.newArrayList(ServiceLoader.load(Tool.class))
				.stream()
				.filter(t -> !t.getCommand().isHidden())
				.collect(Collectors.toList());

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
				usage.append(String.format("   %-30s %s", tool.getCommand().getName(),
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
			if (tool.getCommand().getName().equals(commandName))
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
			e.printStackTrace();
		}
	}
}
