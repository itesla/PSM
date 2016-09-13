package org.power_systems_modelica.psm.network.import_;

import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.google.auto.service.AutoService;

import eu.itesla_project.commons.tools.Command;
import eu.itesla_project.commons.tools.Tool;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.xml.NetworkXml;

@AutoService(Tool.class)
public class StaticNetworkImporterTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "cim2iidm";
		}

		@Override
		public String getTheme()
		{
			return "Conversions";
		}

		@Override
		public String getDescription()
		{
			return "Build IIDM from a given static network file";
		}

		@Override
		public Options getOptions()
		{
			Options options = new Options();
			options.addOption("s", "static", true, "Static network input file");
			options.addOption("i", "iidm", true, "Output IIDM XML file");
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
		String staticFilename = cmd.getOptionValue("static");
		if (staticFilename == null)
		{
			System.err.println("Missing static network input file");
			return;
		}
		String iidmFilename = cmd.getOptionValue("iidm");
		if (iidmFilename == null)
		{
			System.err.println("Missing IIDM output xml file");
			return;
		}

		System.out.println("current directory = " + Paths.get("").toAbsolutePath().toString());
		System.out.println(
				"static            = " + Paths.get(staticFilename).toAbsolutePath().toString());
		System.out.println(
				"iidm              = " + Paths.get(iidmFilename).toAbsolutePath().toString());

		Network n = StaticNetworkImporter.import_(Paths.get(staticFilename));
		if (n == null) System.err.println("Errors during import");
		else NetworkXml.write(n, Paths.get(iidmFilename));
	}
}
