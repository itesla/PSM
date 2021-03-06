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

import java.nio.file.Path;
import java.nio.file.Paths;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.parser.ModelicaParser;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaParserTask extends WorkflowTask
{
	public ModelicaParserTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Modelica parser";
	}

	@Override
	public void configure(Configuration config)
	{
		source = config.getParameter("source");
		modelicaDocument = config.getParameter("modelicaDocument");
	}

	@Override
	public void run()
	{
		running();

		try
		{
			Path modelicaFile = Paths.get(source);
			ModelicaDocument mo = ModelicaParser.parse(modelicaFile);
			publish(SCOPE_GLOBAL, modelicaDocument, mo);
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

	private String	source;
	private String	modelicaDocument;
}
