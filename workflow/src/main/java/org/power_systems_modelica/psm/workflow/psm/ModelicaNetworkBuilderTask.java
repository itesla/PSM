package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.ModelicaNetworkBuilder;
import org.power_systems_modelica.psm.workflow.TaskConfiguration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import eu.itesla_project.iidm.network.Network;

public class ModelicaNetworkBuilderTask extends WorkflowTask
{
	public ModelicaNetworkBuilderTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Modelica network builder";
	}

	@Override
	public void configure(TaskConfiguration config)
	{
		ddrType = config.getParameter("ddrType");
		ddrLocation = config.getParameter("ddrLocation");
		onlyMainConnectedComponent = config.getBoolean("onlyMainConnectedComponent");
	}

	@Override
	public void run()
	{
		running();
		try
		{
			DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create(
					ddrType,
					ddrLocation);
			ddr.connect();
			Network n = (Network) workflow.getResults("network");
			ModelicaNetworkBuilder builder = new ModelicaNetworkBuilder(ddr, n);
			builder.setOnlyMain(onlyMainConnectedComponent);
			ModelicaDocument mo = builder.build();
			publish(SCOPE_GLOBAL, "mo", mo);
			succeded();
		}
		catch (Exception x)
		{
			failed();
		}
	}

	private String				ddrType;
	private String				ddrLocation;
	private boolean				onlyMainConnectedComponent;
}
