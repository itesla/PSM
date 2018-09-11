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

import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.mo2dyd.DydFilesFromModelica;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DydFilesFromModelicaTask extends WorkflowTask
{
	public DydFilesFromModelicaTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "DYD repository from Modelica file";
	}

	@Override
	public void configure(Configuration config)
	{
		modelicaFile = config.getParameter("modelicaFile");
		modelicaInitPath = config.getParameter("modelicaInitPath");
		ddrLocation = config.getParameter("ddrLocation");
	}

	@Override
	public void run()
	{
		running();
		try
		{
			// If there is no Modelica file with initialization models
			// they will be built (inferred) from simulation models
			Path moInitPath = null;
			if (modelicaInitPath != null) moInitPath = Paths.get(modelicaInitPath);

			new DydFilesFromModelica(
					Paths.get(modelicaFile),
					moInitPath,
					Paths.get(ddrLocation)).mo2dyd();
			succeded();
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

	private String	modelicaFile;
	private String	modelicaInitPath;
	private String	ddrLocation;
}
