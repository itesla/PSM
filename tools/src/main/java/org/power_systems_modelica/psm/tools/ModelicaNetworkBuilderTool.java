package org.power_systems_modelica.psm.tools;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.ModelicaNetworkBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;

import com.google.auto.service.AutoService;

import eu.itesla_project.commons.tools.Command;
import eu.itesla_project.commons.tools.Tool;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.xml.NetworkXml;

@AutoService(Tool.class)
public class ModelicaNetworkBuilderTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "iidm2mo";
		}

		@Override
		public String getTheme()
		{
			return "Conversions";
		}

		@Override
		public String getDescription()
		{
			return "Build Modelica document from IIDM network and Dynamic Data Repository";
		}

		@Override
		public Options getOptions()
		{
			Options options = new Options();
			options.addOption("i", "iidm", true, "Input IIDM XML file");
			options.addOption("d", "ddr", true, "Input DDR path");
			options.addOption("m", "modelica", true, "Modelica output file");
			options.addOption("e", "engine", true, "Modelica engine");
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
		String ddrLocation = cmd.getOptionValue("ddr");
		if (ddrLocation == null)
		{
			System.err.println("Missing DDR path");
			return;
		}
		String iidmFilename = cmd.getOptionValue("iidm");
		if (iidmFilename == null)
		{
			System.err.println("Missing IIDM input xml file");
			return;
		}
		String moFilename = cmd.getOptionValue("modelica");
		if (moFilename == null)
		{
			System.err.println("Missing Modelica output file");
			return;
		}
		String engine = cmd.getOptionValue("engine");
		if (engine == null)
		{
			System.err.println("Missing Modelica engine");
			return;
		}

		System.out.println("current  = " + Paths.get("").toAbsolutePath().toString());
		System.out.println("iidm     = " + Paths.get(iidmFilename).toAbsolutePath().toString());
		System.out.println("ddr      = " + Paths.get(ddrLocation).toAbsolutePath().toString());
		System.out.println("modelica = " + Paths.get(moFilename).toAbsolutePath().toString());
		System.out.println("engine   = " + engine);

		Network n = NetworkXml.read(Paths.get(iidmFilename));

		DynamicDataRepository ddr = new DynamicDataRepositoryDydFiles();
		ddr.setLocation(ddrLocation);
		ddr.connect();

		ModelicaEngine me = ModelicaEngineMainFactory.create(engine);

		ModelicaNetworkBuilder b = new ModelicaNetworkBuilder(ddr, n, me);
		boolean onlyMainConnectedComponent = false;
		b.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
		ModelicaDocument mo = b.build();

		Path mof = Paths.get(moFilename);
		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
		try (PrintWriter out = new PrintWriter(mof.toFile());)
		{
			mop.print(out);
			System.out.println("Modelica output sent to " + mof.toAbsolutePath().toString());
		}
	}
}
