package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.util.Optional;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class ModelicaSimulatorTask extends WorkflowTask
{
	public ModelicaSimulatorTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Modelica Dynamic Simulation";
	}

	@Override
	public void configure(Configuration config)
	{
		this.config = config;
		source = config.getParameter("source");
		if (source == null) source = "mo";
		modelicaEngine = Optional.ofNullable(config.getParameter("modelicaEngine"))
				.orElse("OpenModelica");

		// TODO get Modelica Simulation engine parameters (method, tolerance, etc...) from task configuration
	}

	@Override
	public void run()
	{
		running();

		try
		{
			ModelicaDocument mo = (ModelicaDocument) workflow.getResults(source);

			ModelicaEngine me = ModelicaEngineMainFactory.create(modelicaEngine);
			me.configure(config);
			me.simulate(mo);
			dynSimulationParams = me.getSimulationResults();

			publish(SCOPE_GLOBAL,
					"simres",
					dynSimulationParams.getValue(
							mo.getSystemModel().getId(),
							"simulation",
							"path"));

			succeded();

		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private Configuration				config;
	private ModelicaSimulationResults	dynSimulationParams;
	private String						modelicaEngine;
	private String						source;

}
