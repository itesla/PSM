package org.power_systems_modelica.psm.workflow.psm;

/*
 * #%L
 * Power systems on Modelica workflow
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaSimulatorTaskResults extends WorkflowTask
{

	public ModelicaSimulatorTaskResults(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{

		return "Modelica Dynamic Simulation Results";
	}

	@Override
	public void configure(Configuration config)
	{

		source = config.getParameter("source");
		if (source == null)
			source = "simres";
		target = config.getParameter("target");
	}

	@Override
	public void run()
	{
		running();

		try
		{
			Path modelicaEngineWorkingDir = Paths.get(workflow.getResults(source).toString());
			Files.walk(modelicaEngineWorkingDir, FileVisitOption.FOLLOW_LINKS)
					.filter(path -> path.toString().contains("_res") &&
							(path.toString().endsWith(".mat") || path.toString().endsWith(".csv")))
					.map(Path::toFile).forEach(file -> {
						try
						{
							Files.copy(file.toPath(), (new File(target, file.getName())).toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						}
						catch (IOException e)
						{
							failed();
						}
					});

			succeded();

			publish(SCOPE_GLOBAL,
					"simres_output",
					target);
		}
		catch (Exception x)
		{
			failed(x);
		}
	}
	
	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}

	private String	source;
	private String	target;
}
