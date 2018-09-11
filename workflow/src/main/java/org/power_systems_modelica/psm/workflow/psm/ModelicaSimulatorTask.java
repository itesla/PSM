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

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaSimulatorTask extends WorkflowTask // implements Observer
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
		me = null;
		boolean failed = false;

		try
		{
			ModelicaDocument mo = (ModelicaDocument) workflow.getResults(source);
			me = ModelicaEngineMainFactory.create(modelicaEngine);
			me.getModelicaEngineProgress().addObserver(new Observer()
			{
				@Override
				public void update(Observable o, Object arg)
				{
					progress("    " + arg);
				}
			});
			me.configure(config);
			String modelName = mo.getSystemModel().getId();

			me.simulate(mo);

			dinSimulationResults = me.getSimulationResults();
			boolean successfully = (boolean) dinSimulationResults.getValue(modelName, "successful");

			if (!successfully)
			{
				progress(String.format("Dynamic simulation task for model %s has not been completed successfully.", modelName));
				failed();				
			} 
			else {
				progress(String.format("Dynamic simulation task for model %s has been completed successfully.", modelName));
				publish(SCOPE_GLOBAL,
						"simres",
						dinSimulationResults.getValue(modelName, "simulation_path"));
				succeded();	
			}
			
			Logs l = me.getLogs();
			publish(SCOPE_GLOBAL,"logs", l);
		}
		catch (Exception x)
		{
			failed(x);
			failed = true;
		}
		finally
		{
			if (me != null)
			{
				try
				{
					me.close();
				}
				catch (Exception x)
				{
					// Only fail with this information if not already failed
					if (!failed) failed(x);
				}
			}
		}
	}

	@Override
	public void cancel() {
		if(me != null)
		try
		{
			me.close();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	protected ModelicaEngine				me	= null;
	private String							modelicaEngine;
	private Configuration					config;
	private ModelicaSimulationFinalResults	dinSimulationResults;
	private String							source;
}
