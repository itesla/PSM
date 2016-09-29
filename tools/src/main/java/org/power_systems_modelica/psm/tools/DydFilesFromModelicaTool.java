package org.power_systems_modelica.psm.tools;

import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.ddr.DydFilesFromModelica;

import com.google.auto.service.AutoService;

import eu.itesla_project.commons.tools.Command;
import eu.itesla_project.commons.tools.Tool;

@AutoService(Tool.class)
public class DydFilesFromModelicaTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "mo2dyd";
		}

		@Override
		public String getTheme()
		{
			return "Conversions";
		}

		@Override
		public String getDescription()
		{
			return "Build DYD and PAR files from a given Modelica file";
		}

		@Override
		public Options getOptions()
		{
			Options options = new Options();
			options.addOption("m", "modelicaFile", true, "Modelica file");
			options.addOption("d", "ddrLocation", true, "Output DDR path");
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
	public void run(CommandLine cmd) throws Exception
	{
		String modelicaFile = cmd.getOptionValue("modelicaFile");
		if (modelicaFile == null)
		{
			System.err.println("Missing modelica input file");
			return;
		}
		String ddrLocation = cmd.getOptionValue("ddrLocation");
		if (ddrLocation == null)
		{
			System.err.println("Missing DDR output directory");
			return;
		}
		DydFilesFromModelica.mo2dyd(Paths.get(modelicaFile), Paths.get(ddrLocation));
	}
}
