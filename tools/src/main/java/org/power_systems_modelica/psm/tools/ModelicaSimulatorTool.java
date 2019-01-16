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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;

import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;
import com.powsybl.tools.ToolRunningContext;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
@AutoService(Tool.class)
public class ModelicaSimulatorTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "dynamicsim";
		}

		@Override
		public String getTheme()
		{
			return "Modelica dynamic simulation";
		}

		@Override
		public String getDescription()
		{
			return "Run a dynamic simulation in a Modelica file using the specified Modelica dynamic simulation engine.";
		}

		@Override
		public Options getOptions()
		{
			// Only parameter that are available in the GUI will be availFIXME Pending to add all needed options
			// Parameters: engine, stop time, steps per second, create filtered MAT
			Options options = new Options();
			options.addOption(Option.builder()
					.longOpt("i-mo")
					.desc("Input: Modelica file")
					.hasArg(true)
//					.numberOfArgs(1)
					.argName("MODELICA_FILE")
					.required(true)
					.build());
			options.addOption(Option.builder()
					.longOpt("engine")
					.desc("Modelica simulator engine")
					.hasArg(true)
//					.numberOfArgs(1)
					.argName("ENGINE")
					.required(true)
					.build());
			options.addOption(Option.builder()
					.longOpt("stop-time")
					.desc("Simulation stop time in seconds")
					.hasArg(true)
//					.numberOfArgs(1)
					.argName("STOP_TIME")
					.required(true)
					.build());
			options.addOption(Option.builder()
					.longOpt("steps-per-second")
					.desc("Number of steps per second")
					.hasArg(true)
//					.numberOfArgs(1)
					.argName("STEPS_PER_SECOND")
					.required(false)
					.build());
			options.addOption(Option.builder()
					.longOpt("filtered-mat")
					.desc("Create a .MAT file with filtered variables")
					.hasArg(false)
					.required(false)
					.build());
			options.addOption(Option.builder()
					.longOpt("o")
					.desc("Output: Directory path for the dynamic simulation results")
					.hasArg(true)
//					.numberOfArgs(1)
					.argName("DIR_PATH")
					.required(true)
					.build());
			return options;
		}

		@Override
		public String getUsageFooter()
		{
			List<String> enginesList = new ArrayList<>();
			enginesList.addAll(ModelicaEngineMainFactory.getEngines());
			enginesList.removeIf(e -> e.equals("Fake"));
			
			return "Where ENGINE is one of " + enginesList.toString();
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
		String moFile = cmd.getOptionValue("i-mo");
		if (moFile == null)
		{
			System.err.println("Missing Modelica file.");
			return;
		}
		
		String engine = cmd.getOptionValue("engine");
		if (engine == null)
		{
			System.err.println("Missing Dynamic simulator engine.");
			return;
		}
		
		String stopTime = cmd.getOptionValue("stop-time");
		if (stopTime == null)
		{
			System.err.println("Missing simulation stop time.");
			return;
		}
	
		String outputDir = cmd.getOptionValue("o");
		if (outputDir == null)
		{
			System.err.println("Missing output directory.");
			return;
		}
		
		String stepsPerSecond = cmd.getOptionValue("steps-per-second");
		boolean filteredMat = cmd.hasOption("filtered-mat");
		
		//Configuration
		Path tempPath = PathUtils.DATA_TMP;
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", tempPath.toString());
       	config.setParameter("stopTime", stopTime);
       	if(stepsPerSecond != null)
       	{
       		config.setParameter("numOfIntervalsPerSecond", stepsPerSecond);
       	}
       	config.setParameter("createFilteredMat",String.valueOf(filteredMat));
        
		ModelicaDocument moDocument = ModelicaParser.parse(Paths.get(moFile));
		ModelicaEngine me = ModelicaEngineMainFactory.create(engine);
		
		me.configure(config);
		me.simulate(moDocument);
		String simPath = me.getSimulationResults().getValue(moDocument.getSystemModel().getId(), "simulation_path").toString();
		Path simulationPath = Paths.get(simPath);
		
		Files.list(simulationPath).forEach(file -> {
			try
			{
				FileUtils.copyFileToDirectory(file.toFile(), Paths.get(outputDir).toFile(),false);
			}
			catch (IOException e)
			{
				LOG.error("Error copying file from {} to {}, reason is {}",
						file.toFile(),
						outputDir, e.getMessage());
			}
		});
		
		Logs logs = me.getLogs();
		logs.dump(LOG);
		
		me.close();
	}
	
	private static final Logger				LOG		= LoggerFactory
			.getLogger(ModelicaSimulatorTool.class);
}
