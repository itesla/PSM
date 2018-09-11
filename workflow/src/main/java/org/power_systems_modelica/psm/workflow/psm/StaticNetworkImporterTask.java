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

import java.nio.file.Paths;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class StaticNetworkImporterTask extends WorkflowTask
{
	public StaticNetworkImporterTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Static network importer";
	}

	@Override
	public void configure(Configuration config)
	{
		source = config.getParameter("source");
	}

	@Override
	public void run()
	{
		running();
		network = StaticNetworkImporter.import_(Paths.get(source));
		if (network != null)
		{
			publish(SCOPE_GLOBAL, "network", network);
			succeded();
		}
		else
		{
			failed();
		}
	}

	public Network getNetwork()
	{
		return network;
	}
	
	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}

	private String	source;
	private Network	network;
}
