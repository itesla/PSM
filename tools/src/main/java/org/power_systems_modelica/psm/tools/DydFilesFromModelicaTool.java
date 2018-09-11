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

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.mo2dyd.DydFilesFromModelica;

import com.google.auto.service.AutoService;

import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;

// We do not want to make this class public
// @AutoService(Tool.class)
//public class DydFilesFromModelicaTool implements Tool
abstract class HiddenTool {
	public Command getCommand() {return null;}
	public void run(CommandLine cmd) throws Exception {}
}
/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DydFilesFromModelicaTool extends HiddenTool
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
					.longOpt("i-mo")
					.desc("Input: Modelica file")
					.hasArg()
					.argName("MODELICA_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("i-mo-init")
					.desc("Input: Modelica initialization files folder")
					.hasArg()
					.argName("MODELICA_INIT_PATH")
					.required(false)
					.build());
			options.addOption(Option.builder()
					.longOpt("o")
					.desc("Output: Dynamic Data Repository path")
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
		String modelicaFile = cmd.getOptionValue("i-mo");
		if (modelicaFile == null)
		{
			System.err.println("Missing Modelica input file");
			return;
		}
		Path pmo = Paths.get(modelicaFile);

		// If there is no Modelica file with initialization models
		// they will be built (inferred) from simulation models
		Path pmoinit = null;
		String modelicaFileInit = cmd.getOptionValue("i-mo-init");
		if (modelicaFileInit != null) pmoinit = Paths.get(modelicaFileInit);

		String ddrLocation = cmd.getOptionValue("o");
		if (ddrLocation == null)
		{
			System.err.println("Missing DDR output directory");
			return;
		}

		new DydFilesFromModelica(pmo, pmoinit, Paths.get(ddrLocation)).mo2dyd();
	}
}
