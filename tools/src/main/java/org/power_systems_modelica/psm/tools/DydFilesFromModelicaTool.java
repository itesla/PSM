package org.power_systems_modelica.psm.tools;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.mo2dyd.DydFilesFromModelica;

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
			return "Data conversion";
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
			options.addOption(Option.builder()
					.longOpt("modelica")
					.desc("Modelica file")
					.hasArg()
					.argName("MODELICA_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("modelica-init")
					.desc("Modelica initialization files folder")
					.hasArg()
					.argName("MODELICA_INIT_PATH")
					.required(false)
					.build());
			options.addOption(Option.builder()
					.longOpt("ddr")
					.desc("Output Dynamic Data Repository path")
					.hasArg()
					.argName("DDR_PATH")
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
	public void run(CommandLine cmd) throws Exception
	{
		String modelicaFile = cmd.getOptionValue("modelica");
		if (modelicaFile == null)
		{
			System.err.println("Missing modelica input file");
			return;
		}
		Path pmo = Paths.get(modelicaFile);

		// If there is no Modelica file with initialization models
		// they will be built (inferred) from simulation models
		Path pmoinit = null;
		String modelicaFileInit = cmd.getOptionValue("modelica-init");
		if (modelicaFileInit != null) pmoinit = Paths.get(modelicaFileInit);

		String ddrLocation = cmd.getOptionValue("ddr");
		if (ddrLocation == null)
		{
			System.err.println("Missing DDR output directory");
			return;
		}

		new DydFilesFromModelica(pmo, pmoinit, Paths.get(ddrLocation)).mo2dyd();
	}
}
