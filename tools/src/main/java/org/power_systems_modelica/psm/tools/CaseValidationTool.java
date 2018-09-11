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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.case_validation.CaseValidation;
import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.case_validation.model.VariableValidation;
import org.power_systems_modelica.psm.commons.Configuration;

import com.google.auto.service.AutoService;

import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
@AutoService(Tool.class)
public class CaseValidationTool implements Tool
{

	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "casevalidation";
		}
		
		@Override
		public String getTheme()
		{
			return "Case Validation";
		}
		
		@Override
		public String getDescription()
		{
			return "Validate a Modelica file";
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
					.longOpt("i-mapping")
					.desc("Input: Mapping file")
					.hasArg()
					.argName("MAPPING_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("i-reference")
					.desc("Input: Reference file")
					.hasArg()
					.argName("REFERENCE_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("step-size")
					.desc("Step size")
					.hasArg()
					.argName("STEP_SIZE")
					.required(false)
					.build());
			options.addOption(Option.builder()
					.longOpt("o")
					.desc("Output: Directory path for results")
					.hasArg()
					.argName("DIR_PATH")
					.required(false)
					.build());
			options.addOption(Option.builder()
					.longOpt("write-values")
					.desc("Write diff files")
					.hasArg()
					.argName("BOOLEAN")
					.required(false)
					.build());
			return options;
		}



		@Override
		public String getUsageFooter()
		{
			// TODO Auto-generated method stub
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
		String mappingFile = cmd.getOptionValue("i-mapping");
		if (mappingFile == null)
		{
			System.err.println("Missing mapping input file");
			return;
		}

		String refFile = cmd.getOptionValue("i-reference");
		if (refFile == null)
		{
			System.err.println("Missing reference input file");
			return;
		}

		String modelicaFile = cmd.getOptionValue("i-mo");
		if (modelicaFile == null)
		{
			System.err.println("Missing modelica input file");
			return;
		}

		String stepSize = cmd.getOptionValue("step-size");
		String outputPath = cmd.getOptionValue("o");
		String writeValues = cmd.getOptionValue("write-values");

		Configuration config = new Configuration();
		config.setParameter("pathNamesMapping", mappingFile);
		config.setParameter("pathRefData", refFile);
		config.setParameter("pathModelicaData", modelicaFile);
		if (stepSize != null)
		{
			config.setParameter("stepSize", stepSize);
		}
		if (writeValues != null)
		{
			config.setParameter("writeFile", writeValues);
		}
		if (outputPath == null)
		{
			outputPath = ".";
		}
		config.setParameter("pathOutput", outputPath);

		CaseValidation compare = new CaseValidation(config);
		compare.calculate();
		compare.validation();

		ValidationResult result = compare.getResult();
		List<String> elements = new ArrayList<>();
		elements.addAll(result.getElements().keySet());

		StringBuilder resume = new StringBuilder();
		StringBuilder detail = new StringBuilder();
		for (VariableValidation v : result.getVariables())
		{
			resume.append(v.getName() + "," + v.getRmesAbove() + "," + v.getAbsTotalOffset() + ","
					+ v.getRelTotalOffset() + "\n");

			elements.forEach(ke -> {

				Element e = result.getElements().get(ke);
				if (!e.getRefName().endsWith(v.getName())) return;

				detail.append(e.getModelicaName() + "," + e.getAbsRmes() + ","
						+ (1.0 * e.getAbsOffset() / v.getSteps()) + ","
						+ (1.0 * e.getRelOffset() / v.getSteps()) + "\n");
			});
		}

		Path p = Paths.get(outputPath);
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter(new FileWriter(p.resolve("DataResume.csv").toFile()));
		    writer.write(resume.toString());
		} finally {
		    if (writer != null) writer.close();
		}

		writer = null;
		try {
		    writer = new BufferedWriter(new FileWriter(p.resolve("DataDetail.csv").toFile()));
		    writer.write(detail.toString());
		} finally {
		    if (writer != null) writer.close();
		}
	}
}
