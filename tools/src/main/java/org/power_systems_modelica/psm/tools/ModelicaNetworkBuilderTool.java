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
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;

import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.xml.NetworkXml;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
			return "Build a Modelica file from an IIDM network and a Dynamic Data Repository";
		}

		@Override
		public Options getOptions()
		{
			Options options = new Options();
			options.addOption(Option.builder()
					.longOpt("i-iidm")
					.desc("Input: IIDM file")
					.hasArg()
					.argName("IIDM_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("i-ddr")
					.desc("Input: Dynamic Data Repository path")
					.hasArg()
					.argName("DDR_PATH")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("o")
					.desc("Output: Modelica file")
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
					.longOpt("all-ac-connected-components")
					.desc("All AC-connected components")
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
		String ddrLocation = cmd.getOptionValue("i-ddr");
		if (ddrLocation == null)
		{
			System.err.println("Missing DDR path");
			return;
		}
		String iidmFilename = cmd.getOptionValue("i-iidm");
		if (iidmFilename == null)
		{
			System.err.println("Missing IIDM input file");
			return;
		}
		String moFilename = cmd.getOptionValue("o");
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
		boolean onlyMainConnectedComponent = !cmd.hasOption("all-ac-connected-components");

		// Read IIDM input
		Network n = NetworkXml.read(Paths.get(iidmFilename));

		// Read DDR input
		DynamicDataRepository ddr;
		ddr = DynamicDataRepositoryMainFactory.create("DYD", ddrLocation);
		ddr.connect();

		// Prepare Modelica Engine
		ModelicaEngine me = ModelicaEngineMainFactory.create(engine);
		Configuration config = new Configuration();
		config.setParameter("modelicaEngineWorkingDir", PathUtils.DATA_TMP.toString());
		config.setParameter("stopTime", "0.01");
		config.setParameter("numOfIntervalsPerSecond", "1000");
		config.setParameter("resultVariables", "");
		me.configure(config);

		// Build Modelica File
		ModelicaSystemBuilder b = new ModelicaSystemBuilder(ddr, n, me);
		b.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
		Collection<UnresolvedRef> unresolved = new ArrayList<>();
		ModelicaDocument mo = b.build(unresolved);
		
		Logs logs = me.getLogs();
		logs.dump(LOG);
		
		// Export Modelica File
		Path mof = Paths.get(moFilename);
		ModelicaTextPrinter.print(mo, mof, true);
	}
	
	private static final Logger				LOG		= LoggerFactory
			.getLogger(ModelicaNetworkBuilderTool.class);
}
