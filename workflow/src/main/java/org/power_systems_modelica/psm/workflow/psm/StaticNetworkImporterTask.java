package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.nio.file.Paths;

import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;
import org.power_systems_modelica.psm.workflow.TaskConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import eu.itesla_project.iidm.network.Network;

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
	public void configure(TaskConfiguration config)
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

	private String	source;
	private Network	network;
}
