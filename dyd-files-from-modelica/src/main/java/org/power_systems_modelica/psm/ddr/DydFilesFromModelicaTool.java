package org.power_systems_modelica.psm.ddr;

import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

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
			options.addOption("m", "modelica", true, "Modelica file");
			options.addOption("d", "ddr", true, "Output DDR path");
			options.addOption("y", "dyd", true, "Output DYD filename");
			options.addOption("p", "par", true, "Output PAR filename");
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
		String mofile = cmd.getOptionValue("modelica");
		if (mofile == null)
		{
			System.err.println("Missing modelica input file");
			return;
		}
		String ddrloc = cmd.getOptionValue("ddr");
		if (ddrloc == null)
		{
			System.err.println("Missing DDR output directory");
			return;
		}
		String dydname = cmd.getOptionValue("dyd");
		if (dydname == null)
		{
			System.err.println("Missing DYD file name");
			return;
		}
		String parname = cmd.getOptionValue("par");
		if (parname == null)
		{
			System.err.println("Missing PAR file name");
			return;
		}
		DydFilesFromModelica.mo2dyd(Paths.get(mofile), Paths.get(ddrloc), dydname, parname);
	}
}
