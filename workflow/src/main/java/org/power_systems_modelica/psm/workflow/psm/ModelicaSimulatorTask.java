package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.util.Optional;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
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
			boolean validated = me.validate(mo, 2);
			if (validated) me.simulate(mo);
			dynSimulationParams = me.getSimulationResults();

			publish(SCOPE_GLOBAL,
					"simres",
					dynSimulationParams.getValue(mo.getSystemModel().getId(), "simulation_path"));

			succeded();

		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private Configuration					config;
	private ModelicaSimulationFinalResults	dynSimulationParams;
	private String							modelicaEngine;
	private String							source;

}
