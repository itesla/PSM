package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

public class ModelicaSimulatorTask extends WorkflowTask implements Observer
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
			try
			{
				me = ModelicaEngineMainFactory.create(modelicaEngine);
				me.getModelicaEngineProgress().addObserver(this);
				me.configure(config);
				String modelName = mo.getSystemModel().getId();

				me.simulate(mo);

				dinSimulationResults = me.getSimulationResults();
				boolean hasResults = (boolean) dinSimulationResults.getValue(modelName, "successful");

				if (hasResults) {
					progress(String.format("Model %s has been simulated.", modelName));
					succeded();
				}
				else { 
					progress(String.format("Model %s has not been simulated.", modelName));
					failed();
				}

				publish(SCOPE_GLOBAL,
						"simres",
						dinSimulationResults.getValue(modelName, "simulation_path"));				
			}
			catch (Exception exc)
			{
				exc.printStackTrace();
			}
			finally
			{
				if (me != null)
				{
					try
					{
						me.close();
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		progress(arg1.toString());
	}
	
	protected ModelicaEngine				me	= null;
	private String							modelicaEngine;
	private Configuration					config;
	private ModelicaSimulationFinalResults	dinSimulationResults;
	private String							source;
}
