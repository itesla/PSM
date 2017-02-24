package org.power_systems_modelica.psm.tools;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
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
			return "Data conversion";
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
			options.addOption(Option.builder()
					.longOpt("iidm")
					.desc("Input IIDM XML file")
					.hasArg()
					.argName("IIDM_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("ddr")
					.desc("Input Dynamic Data Repository path")
					.hasArg()
					.argName("DDR_PATH")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("modelica")
					.desc("Modelica output file")
					.hasArg()
					.argName("MODELICA_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("engine")
					.desc("Modelica dynamic simulation engine")
					.hasArg()
					.argName("ENGINE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("include-model-annotations")
					.desc("Include additional info using annotations")
					.hasArg(false)
					.required(false)
					.build());
			return options;
		}

		@Override
		public String getUsageFooter()
		{
			return "Where ENGINE is one of " + ModelicaEngineMainFactory.getEngines();
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
		boolean includePsmAnnotations = cmd.hasOption("include-model-annotations");
		String engine = cmd.getOptionValue("engine");
		if (engine == null)
		{
			System.err.println("Missing Modelica engine");
			return;
		}

		System.out.println("current     = " + Paths.get("").toAbsolutePath().toString());
		System.out.println("iidm        = " + Paths.get(iidmFilename).toAbsolutePath().toString());
		System.out.println("ddr         = " + Paths.get(ddrLocation).toAbsolutePath().toString());
		System.out.println("modelica    = " + Paths.get(moFilename).toAbsolutePath().toString());
		System.out.println("annotations = " + includePsmAnnotations);
		System.out.println("engine      = " + engine);

		Network n = NetworkXml.read(Paths.get(iidmFilename));

		DynamicDataRepository ddr = new DynamicDataRepositoryDydFiles();
		ddr.setLocation(ddrLocation);
		ddr.connect();

		ModelicaEngine me = ModelicaEngineMainFactory.create(engine);
		// FIXME Think if Modelica Engine must be configured (it should read its own configuration silently)
		// Only fake modelica engine needs configuration that depends on each use (fake results for initializations)
		Configuration config = new Configuration();
		Path data = Paths.get(System.getenv("PSM_DATA"));
		config.setParameter("fakeModelicaEngineResults",
				data.resolve("test/ieee14/ddr/fake_init.csv").toString());
		config.setParameter("modelicaEngineWorkingDir", data.resolve("tmp").toString());
		config.setParameter("libraryDir", data.resolve("test/library").toString());
        config.setParameter("stopTime", "0.01");
        config.setParameter("numOfIntervalsPerSecond", "1000");
		me.configure(config);

		ModelicaSystemBuilder b = new ModelicaSystemBuilder(ddr, n, me);
		boolean onlyMainConnectedComponent = false;
		b.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument mo = b.build(unresolved);

		Path mof = Paths.get(moFilename);
		ModelicaTextPrinter.print(mo, mof, includePsmAnnotations);
		System.out.println("Modelica output sent to " + mof.toAbsolutePath().toString());
	}
}
