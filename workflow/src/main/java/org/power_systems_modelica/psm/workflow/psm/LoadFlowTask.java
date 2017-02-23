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
import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.DanglingLine;
import eu.itesla_project.iidm.network.EquipmentTopologyVisitor;
import eu.itesla_project.iidm.network.Generator;
import eu.itesla_project.iidm.network.Line;
import eu.itesla_project.iidm.network.Load;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.ShuntCompensator;
import eu.itesla_project.iidm.network.StateManager;
import eu.itesla_project.iidm.network.Terminal;
import eu.itesla_project.iidm.network.ThreeWindingsTransformer;
import eu.itesla_project.iidm.network.TwoTerminalsConnectable;
import eu.itesla_project.iidm.network.TwoWindingsTransformer;
import eu.itesla_project.iidm.network.util.Networks;
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
		checkResult = config.getBoolean("checkResult", false);
		checkResultDeltaMaxThreshold = Optional
				.ofNullable(config.getParameter("checkResultDeltaMaxThreshold"))
				.map(Float::valueOf);

		// TODO obtain LoadFlowParameters from task configuration
		loadFlowParams = new LoadFlowParameters();
		// TODO obtain computationManager parameters and priority from task configuration ?

		LOG.info("loadFlowFactoryClass : " + config.getParameter("loadFlowFactoryClass"));
		LOG.info("    sourceStateId                = " + sourceStateId);
		LOG.info("    targetStateId                = " + targetStateId);
		LOG.info("    targetCsvFolder              = " + targetCsvFolder);
		LOG.info("    checkResult                  = " + checkResult);
		LOG.info("    checkResultDeltaMaxThreshold = " + checkResultDeltaMaxThreshold);
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
			if (checkResult)
			{
				boolean flowsOk = Networks.checkFlows(network);
				LOG.info("checkFlows = " + flowsOk);
				boolean busBalancesOk = checkBusBalances(network,
						checkResultDeltaMaxThreshold.get());
				LOG.info("checkBusBalances = " + busBalancesOk);
			}

			if (!r.isOk()) throw new Exception("Loadflow is not Ok");

			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	static public boolean checkBusBalances(Network n, float deltaMaxThreshold)
	{
		float deltaMax[] = { 0.0f, 0.0f };
		for (Bus b : n.getBusBreakerView().getBuses())
		{
			if (!b.isInMainConnectedComponent()) continue;
			float delta[] = { 0.0f, 0.0f };
			LOG.info(String.format("LFBB Bus %-5s %12.5f %12.5f   %-5s %12.5f %12.5f %-64s",
					"V,A",
					b.getV() / b.getVoltageLevel().getNominalV(),
					b.getAngle(),
					"P,Q",
					b.getP(),
					b.getQ(),
					b.getId()));
			b.visitConnectedEquipments(new EquipmentTopologyVisitor()
			{
				void updateBalance(String type, String id, Terminal t)
				{
					float p = t.getP();
					float q = t.getQ();
					LOG.info(String.format("LFBB     %-5s %12.5f %12.5f %-64s", type, p, q, id));
					delta[0] += Float.isNaN(p) ? 0.0f : p;
					delta[1] += Float.isNaN(q) ? 0.0f : q;
				}

				@Override
				public void visitLine(Line line, TwoTerminalsConnectable.Side side)
				{
					updateBalance("Line", line.getId(), line.getTerminal(side));
				}

				@Override
				public void visitTwoWindingsTransformer(TwoWindingsTransformer transformer,
						TwoTerminalsConnectable.Side side)
				{
					updateBalance("2W", transformer.getId(), transformer.getTerminal(side));
				}

				@Override
				public void visitThreeWindingsTransformer(ThreeWindingsTransformer transformer,
						ThreeWindingsTransformer.Side side)
				{
					updateBalance("3W", transformer.getId(), transformer.getTerminal(side));
				}

				@Override
				public void visitGenerator(Generator generator)
				{
					updateBalance("Gen", generator.getId(), generator.getTerminal());
				}

				@Override
				public void visitLoad(Load load)
				{
					updateBalance("Load", load.getId(), load.getTerminal());
				}

				@Override
				public void visitShuntCompensator(ShuntCompensator sc)
				{
					updateBalance("Shunt", sc.getId(), sc.getTerminal());
				}

				@Override
				public void visitDanglingLine(DanglingLine danglingLine)
				{
					updateBalance("Line", danglingLine.getId(), danglingLine.getTerminal());
				}

				@Override
				public <I extends Connectable<I>> void visitEquipment(Connectable<I> eq)
				{
				}
			});
			LOG.info(String.format("LFBB     %-5s %12.5f %12.5f", "Sum", delta[0], delta[1]));
			if (Math.abs(delta[0]) > Math.abs(deltaMax[0])) deltaMax[0] = delta[0];
			if (Math.abs(delta[1]) > Math.abs(deltaMax[1])) deltaMax[1] = delta[1];
		}
		LOG.info("checkBusBalances");
		LOG.info(String.format("LFBB     %-5s %12.5f %12.5f", "Max", deltaMax[0], deltaMax[1]));
		return Math.abs(deltaMax[0]) <= deltaMaxThreshold
				&& Math.abs(deltaMax[1]) <= deltaMaxThreshold;
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
			Vs[0] = b.getV() / b.getVoltageLevel().getNominalV();
			As[0] = b.getAngle();
			Ps[0] = zeroIfNaN(b.getP());
			Qs[0] = zeroIfNaN(b.getQ());
			n.getStateManager().setWorkingState(caseId1);
			Vs[1] = b.getV() / b.getVoltageLevel().getNominalV();
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

	static enum DiffMethod
	{
		ABS_ERROR, RELATIVE_ERROR_DIFF_SUM, RELATIVE_ERROR_ABS_IF_SMALL
	}

	public static float calcDifference(float v0, float v1)
	{
		switch (diffMethod)
		{
		case ABS_ERROR:
			return Math.abs(v0 - v1);
		case RELATIVE_ERROR_DIFF_SUM:
			return Math.abs(2 * (v0 - v1) / (v0 + v1));
		case RELATIVE_ERROR_ABS_IF_SMALL:
		default:
			return calcRelativeErrorOrAbsoluteIfSmallValues(v0, v1);
		}
	}

	public static float calcRelativeErrorOrAbsoluteIfSmallValues(float v0, float v1)
	{
		float absoluteError = Math.abs(v0 - v1);
		if (isAlmostZero(v0) || isAlmostZero(v1)) return absoluteError;
		float err = absoluteError / Math.abs(v0);
		return err;
	}

	static private boolean isAlmostZero(float value)
	{
		return Math.abs(value) < 1e-4f;
	}

	static private float zeroIfNaN(float value)
	{
		return Float.isNaN(value) ? 0.0f : value;
	}

	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}

	private LoadFlowParameters		loadFlowParams;
	private LoadFlowFactory			loadFlowFactory;
	private String					sourceStateId;
	private String					targetStateId;
	private Optional<String>		targetCsvFolder;
	private String					loadFlowFactoryClassName;
	private boolean					checkResult;
	private Optional<Float>			checkResultDeltaMaxThreshold;

	private static final Logger		LOG	= LoggerFactory.getLogger(LoadFlowTask.class);

	private static final DiffMethod	diffMethod;
	static
	{
		String sdiffMethod = System.getProperty("loadFlow.diffMethod",
				DiffMethod.ABS_ERROR.name());
		diffMethod = DiffMethod.valueOf(sdiffMethod);
		LOG.info("LoadFlow diff method is [" + diffMethod + "]");
	}
}
