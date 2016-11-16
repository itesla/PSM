package org.power_systems_modelica.psm.tools;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineFactory;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;

import com.google.auto.service.AutoService;

import eu.itesla_project.commons.tools.Command;
import eu.itesla_project.commons.tools.Tool;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.xml.NetworkXml;

@AutoService(Tool.class)
public class ModelicaSimulatorTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "modsim";
		}

		@Override
		public String getTheme()
		{
			return "Modelica dynamic simulation";
		}

		@Override
		public String getDescription()
		{
			return "Run a dynamic simulation of a Modelica document using the specified Modelica dynamic simulator engine.";
		}

		@Override
		public Options getOptions()
		{
			//FIXME Pending to add all needed options
			Options options = new Options();
			options.addOption(Option.builder("wd").longOpt("working-dir")
								.hasArg(true).numberOfArgs(1)
								.desc("Modelica engine working directory")
								.build());
			options.addOption(Option.builder("ld").longOpt("library-dir")
								.hasArg(true).numberOfArgs(1)
								.desc("Library directory")
								.required(true)
								.build());
			options.addOption(Option.builder("rv").longOpt("result-variables")
								.hasArg(true)
								.desc("Result variables to extract in a CSV file")
								.build());
			
			options.addOption(Option.builder("e").longOpt("engine")
								.hasArg(true).numberOfArgs(1)
								.desc("Modelica simulator engine")
								.required(true)
								.build());
			
//			OptionGroup engGroup = new OptionGroup();
//			engGroup.setRequired(true);
//			engGroup.addOption(Option.builder("dy").longOpt("dymola")
//								.hasArg(false)
//								.desc("Dymola engine")
//								.build());
//			engGroup.addOption(Option.builder("om").longOpt("openmodelica")
//								.hasArg(false)
//								.desc("Open Modelica engine")
//								.build());
//			options.addOptionGroup(engGroup);
			
//			OptionGroup engOptionsGroup	= new OptionGroup();
//			engOptionsGroup.addOption(Option.builder("e-t0").longOpt("e-start-time")
//										.hasArg(true).numberOfArgs(1)
//										.desc("Dynamic simulation engine parameter: simulation start time")
//										.build());
//			engOptionsGroup.addOption(Option.builder("e-t1").longOpt("e-stop-time")
//										.hasArg(true).numberOfArgs(1)
//										.desc("Dynamic simulation engine parameter: simulation stop time")
//										.build());
//			
//			engOptionsGroup.addOption(Option.builder("e-m").longOpt("e-method")
//										.hasArg(true).numberOfArgs(1)
//										.desc("Dynamic simulation engine parameter: integration method")
//										.build());
//			engOptionsGroup.addOption(Option.builder("e-t").longOpt("e-tolerance")
//										.hasArg(true).numberOfArgs(1)
//										.desc("Dynamic simulation engine parameter: integration tolerance")
//										.build());
//			engOptionsGroup.addOption(Option.builder("e-s").longOpt("e-step-size")
//										.hasArg(true).numberOfArgs(1)
//										.desc("Dynamic simulation engine parameter: integration step size")
//										.build());
//			
//			engOptionsGroup.addOption(Option.builder("e-ni").longOpt("e-number-intervals")
//										.hasArg(true).numberOfArgs(1)
//										.desc("Dynamic simulation engine parameter: number of output intervals")
//										.build());
//			engOptionsGroup.addOption(Option.builder("e-il").longOpt("e-interval-length")
//										.hasArg(true).numberOfArgs(1)
//										.desc("Dynamic simulation engine parameter: length of output interval")
//										.build());
//			options.addOptionGroup(engOptionsGroup);
			
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
		String workingDir = cmd.getOptionValue("working-dir");
		if(workingDir == null) 
		{
			System.err.println("Missing working directory.");
		}
		
		String libraryDir = cmd.getOptionValue("library-dir");
		if(libraryDir == null) 
		{
			System.err.println("Missing library directory.");
			return;
		}
		
		String[] resultVars = cmd.getOptionValues("result-variables");
		
		String engine = cmd.getOptionValue("engine");
		if(engine == null) 
		{
			System.err.println("Missing Modelica simulator engine.");
			return;
		}
		
		String estart = cmd.getOptionValue("e-start-time");
		if(estart == null)
		{
			System.out.println("Missing simulation start time.");
		}
		
		String estop = cmd.getOptionValue("e-stop-time");
		if(estart == null)
		{
			System.out.println("Missing simulation start time.");
		}
		
		String emethod = cmd.getOptionValue("e-method");
		if(emethod == null)
		{
			System.out.println("Missing simulation stop time.");
		}

		String etolerance = cmd.getOptionValue("e-tolerance");
		if(etolerance == null)
		{
			System.out.println("Missing simulation tolerance");
		}
		
		String estep = cmd.getOptionValue("e-step-size");
		if(estep == null)
		{
			System.out.println("Missing simulation step size");
		}
		
		String eintervals = cmd.getOptionValue("e-number-intervals");
		if(eintervals == null)
		{
			System.out.println("Missing simulation number of intervals.");
		}

		String eintlength = cmd.getOptionValue("e-interval-lengtrh");
		if(eintlength == null)
		{
			System.out.println("Missing simulation interval length.");
		}
		
		System.out.println("working-dir        = " + Paths.get(workingDir).toString());
		System.out.println("library-dir        = " + Paths.get(libraryDir).toString());
		System.out.println("results-variables  = " + resultVars.toString());
		System.out.println("engine			   = " + engine);
		
//		Network n = NetworkXml.read(Paths.get(iidmFilename));
//		DynamicDataRepository ddr = new DynamicDataRepositoryDydFiles();
//		ddr.setLocation(ddrLocation);
//		ddr.connect();

		ModelicaEngine me = ModelicaEngineMainFactory.create(engine);
		System.out.println(me.toString());
//		me.
		Configuration config = new Configuration();
		
		me.configure(config);

//		ModelicaSystemBuilder b = new ModelicaSystemBuilder(ddr, n, me);
//		boolean onlyMainConnectedComponent = false;
//		b.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
//		ModelicaDocument mo = b.build();
//
//		Path mof = Paths.get(moFilename);
//		ModelicaTextPrinter mop = new ModelicaTextPrinter(mo);
//		mop.setIncludePsmAnnotations(includePsmAnnotations);
//		try (PrintWriter out = new PrintWriter(mof.toFile());)
//		{
//			mop.print(out);
//			System.out.println("Modelica output sent to " + mof.toAbsolutePath().toString());
//		}
	}
}
