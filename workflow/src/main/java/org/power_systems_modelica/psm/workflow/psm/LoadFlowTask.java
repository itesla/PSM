package org.power_systems_modelica.psm.workflow.psm;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import eu.itesla_project.computation.ComputationManager;
import eu.itesla_project.computation.local.LocalComputationManager;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.loadflow.api.LoadFlow;
import eu.itesla_project.loadflow.api.LoadFlowFactory;
import eu.itesla_project.loadflow.api.LoadFlowParameters;

public class LoadFlowTask extends WorkflowTask
{
	public LoadFlowTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "LoadFlow";
	}

	@Override
	public void configure(Configuration config)
	{
		// TODO Decide which Loadflow factory should be used
		try
		{
			// TODO use ModuleConfig getClassProperty from ipst
			Class<? extends LoadFlowFactory> loadFlowFactoryClass = Class
					.forName(config.getParameter("loadFlowFactoryClass"))
					.asSubclass(LoadFlowFactory.class);
			loadFlowFactory = loadFlowFactoryClass.newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		// TODO obtain LoadFlowParameters from task configuration
		loadFlowParams = new LoadFlowParameters();
		// TODO obtain computationManager parameters and priority from task configuration ?
	}

	@Override
	public void run()
	{
		running();

		try
		{
			Network network = (Network) workflow.getResults("network");
			ComputationManager computationManager = new LocalComputationManager();
			int priority = 1;
			LoadFlow lf = loadFlowFactory.create(network, computationManager, priority);
			lf.run(loadFlowParams);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private LoadFlowParameters	loadFlowParams;
	private LoadFlowFactory		loadFlowFactory;
}
