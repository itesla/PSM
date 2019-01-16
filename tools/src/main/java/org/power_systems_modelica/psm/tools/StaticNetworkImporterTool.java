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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.nio.file.Paths;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;
import com.google.auto.service.AutoService;
import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;
import com.powsybl.tools.ToolRunningContext;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.xml.NetworkXml;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
@AutoService(Tool.class)
public class StaticNetworkImporterTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "impnetwork";
		}

		@Override
		public String getTheme()
		{
			return "Static Network";
		}

		@Override
		public String getDescription()
		{
			return "Import an IIDM network from CIM files.";
		}

		@Override
		public Options getOptions()
		{
			Options options = new Options();
			options.addOption(Option.builder()
					.longOpt("i-cim")
					.desc("Input: CIM file")
					.hasArg()
					.argName("CIM_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("o")
					.desc("Output: IIDM file")
					.hasArg()
					.argName("IIDM_FILE")
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
	public void run(CommandLine cmd, ToolRunningContext context) throws Exception
	{
		String cimPath = cmd.getOptionValue("i-cim");
		if (cimPath == null)
		{
			System.err.println("Missing CIM input file");
			return;
		}
		String iidmOut = cmd.getOptionValue("o");
		if (iidmOut == null)
		{
			System.err.println("Missing output file");
			return;
		}

		Network network = StaticNetworkImporter.import_(Paths.get(cimPath));
		NetworkXml.write(network, Paths.get(iidmOut));

	}

}
