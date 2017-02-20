package org.power_systems_modelica.psm.workflow.psm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.computation.ComputationManager;
import eu.itesla_project.computation.local.LocalComputationManager;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.StateManager;
import eu.itesla_project.loadflow.api.LoadFlow;
import eu.itesla_project.loadflow.api.LoadFlowFactory;
import eu.itesla_project.loadflow.api.LoadFlowParameters;
import eu.itesla_project.loadflow.api.LoadFlowResult;
import eu.itesla_project.online.db.debug.NetworkData;
import eu.itesla_project.online.db.debug.NetworkDataExporter;
import eu.itesla_project.online.db.debug.NetworkDataExtractor;

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
		// If source state identifier is not provided, always use INITIAL_STATE_ID
		// So, if we are not working with multithreaded state manager
		// and other task has changed the state id, this will reset

		sourceStateId = Optional.ofNullable(config.getParameter("sourceStateId"))
				.orElse(StateManager.INITIAL_STATE_ID);
		targetStateId = Optional.ofNullable(config.getParameter("targetStateId"))
				.orElse(sourceStateId);
		targetCsvFolder = Optional.ofNullable(config.getParameter("targetCsvFolder"));
		loadFlowFactoryClassName = config.getParameter("loadFlowFactoryClass");

		// TODO obtain LoadFlowParameters from task configuration
		loadFlowParams = new LoadFlowParameters();
		// TODO obtain computationManager parameters and priority from task configuration ?

		LOG.info("loadFlowFactoryClass = " + config.getParameter("loadFlowFactoryClass"));
		LOG.info("    sourceStateId    = " + sourceStateId);
		LOG.info("    targetStateId    = " + targetStateId);
		LOG.info("    targetCsvFolder  = " + targetCsvFolder);
	}

	@Override
	public void run()
	{
		running();

		try
		{
			// TODO use ModuleConfig getClassProperty from ipst
			Class<? extends LoadFlowFactory> loadFlowFactoryClass = Class
					.forName(loadFlowFactoryClassName)
					.asSubclass(LoadFlowFactory.class);
			loadFlowFactory = loadFlowFactoryClass.newInstance();

			Network network = (Network) workflow.getResults("network");
			network.getStateManager().allowStateMultiThreadAccess(true);
			network.getStateManager().setWorkingState(sourceStateId);
			if (!targetStateId.equals(sourceStateId))
			{
				network.getStateManager().cloneState(sourceStateId, targetStateId);
				network.getStateManager().setWorkingState(targetStateId);
			}

			// Export the input data
			if (targetCsvFolder.isPresent())
			{
				Path folder = Paths.get(targetCsvFolder.get()).resolve("input");
				Files.createDirectories(folder);
				NetworkData d = NetworkDataExtractor.extract(network);
				NetworkDataExporter.export(d, folder);
			}

			ComputationManager computationManager = new LocalComputationManager();
			int priority = 1;
			LoadFlow lf = loadFlowFactory.create(network, computationManager, priority);
			LoadFlowResult r = lf.run(loadFlowParams);

			// Export the data if requested, even if the result if not ok
			if (targetCsvFolder.isPresent())
			{
				Path folder = Paths.get(targetCsvFolder.get()).resolve("output");
				Files.createDirectories(folder);
				NetworkData d = NetworkDataExtractor.extract(network);
				NetworkDataExporter.export(d, folder);
			}

			if (!r.isOk()) throw new Exception("Loadflow is not Ok");

			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	// Helper function to be used from GUI and from Tests
	static public Map<String, Map<String, float[]>> gatherBusesValues(
			Network n,
			String caseId0,
			String caseId1)
	{
		Map<String, Map<String, float[]>> allBusesValues = new HashMap<>();
		n.getBusBreakerView().getBuses().forEach(b -> {
			Map<String, float[]> bvalues = new HashMap<>();
			float[] Vs = new float[2];
			float[] As = new float[2];
			float[] Ps = new float[2];
			float[] Qs = new float[2];
			n.getStateManager().setWorkingState(caseId0);
			Vs[0] = b.getV();
			As[0] = b.getAngle();
			Ps[0] = zeroIfNaN(b.getP());
			Qs[0] = zeroIfNaN(b.getQ());
			n.getStateManager().setWorkingState(caseId1);
			Vs[1] = b.getV();
			As[1] = b.getAngle();
			Ps[1] = zeroIfNaN(b.getP());
			Qs[1] = zeroIfNaN(b.getQ());
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);
			allBusesValues.put(b.getId(), bvalues);
		});
		return allBusesValues;
	}

	public static float calcRelativeError(float v0, float v1)
	{
		float absoluteError = Math.abs(v1 - v1);
		if (v0 == 0.0f) return absoluteError;
		float err = absoluteError / Math.abs(v0);
		return err;
	}

	static private float zeroIfNaN(float value)
	{
		return Float.isNaN(value) ? 0.0f : value;
	}

	private LoadFlowParameters	loadFlowParams;
	private LoadFlowFactory		loadFlowFactory;
	private String				sourceStateId;
	private String				targetStateId;
	private Optional<String>	targetCsvFolder;
	private String				loadFlowFactoryClassName;

	private static final Logger	LOG	= LoggerFactory.getLogger(LoadFlowTask.class);
}
