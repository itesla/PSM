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

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.workflow.WorkflowTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.computation.ComputationManager;
import com.powsybl.computation.local.LocalComputationManager;
import com.powsybl.iidm.network.Bus;
import com.powsybl.iidm.network.Connectable;
import com.powsybl.iidm.network.DanglingLine;
import com.powsybl.iidm.network.EquipmentTopologyVisitor;
import com.powsybl.iidm.network.Generator;
import com.powsybl.iidm.network.Line;
import com.powsybl.iidm.network.Load;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.network.ShuntCompensator;
import com.powsybl.iidm.network.StateManagerConstants;
import com.powsybl.iidm.network.Terminal;
import com.powsybl.iidm.network.ThreeWindingsTransformer;
import com.powsybl.iidm.network.Branch;
import com.powsybl.iidm.network.TwoWindingsTransformer;
import com.powsybl.iidm.network.util.Networks;
import com.powsybl.iidm.xml.NetworkXml;
import com.powsybl.loadflow.LoadFlow;
import com.powsybl.loadflow.LoadFlowFactory;
import com.powsybl.loadflow.LoadFlowParameters;
import com.powsybl.loadflow.LoadFlowResult;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
				.orElse(StateManagerConstants.INITIAL_STATE_ID);
		targetStateId = Optional.ofNullable(config.getParameter("targetStateId"))
				.orElse(sourceStateId);
		workingDirectory = Optional.ofNullable(config.getParameter("workingDirectory")).orElse(".");
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
		LOG.info("    checkResult                  = " + checkResult);
		LOG.info("    checkResultDeltaMaxThreshold = " + checkResultDeltaMaxThreshold);
	}

	@Override
	public void run()
	{
		running();

		try
		{
			// TODO use ModuleConfig getClassProperty from powsybl
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

			ComputationManager computationManager = new LocalComputationManager();
			int priority = 1;
			LoadFlow lf = loadFlowFactory.create(network, computationManager, priority);
			LoadFlowResult r = lf.run(network.getStateManager().getWorkingStateId(), loadFlowParams).join();

			// After run the LF export result data even if the result is not ok (in this case the exported iidm will have the initial values).
			NetworkXml.write(network, Paths.get(workingDirectory)
					.resolve(network.getId() + "_" + super.getId() + ".xiidm"));

			if (checkResult)
			{
				LOG.warn(
						"checkResult temporarily disabled, must add depedencies to loadflow-validation powsybl module");
				// boolean flowsOk = Validation.checkFlows(network, CheckFlowsConfig.load(), NullWriter.NULL_WRITER);
				// LOG.info("checkFlows = " + flowsOk);
				// boolean busBalancesOk = checkBusBalances(network,
				// checkResultDeltaMaxThreshold.get());
				// LOG.info("checkBusBalances = " + busBalancesOk);
			}

			if (!r.isOk()) throw new Exception("Loadflow is not Ok");
			if (r.getLogs().contains("Loadflow kernel invalid license"))
				throw new Exception("Loadflow kernel invalid license");

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
					double p = t.getP();
					double q = t.getQ();
					LOG.info(String.format("LFBB     %-5s %12.5f %12.5f %-64s", type, p, q, id));
					delta[0] += Double.isNaN(p) ? 0.0f : p;
					delta[1] += Double.isNaN(q) ? 0.0f : q;
				}

				@Override
				public void visitLine(Line line, Branch.Side side)
				{
					updateBalance("Line", line.getId(), line.getTerminal(side));
				}

				@Override
				public void visitTwoWindingsTransformer(TwoWindingsTransformer transformer,
						Branch.Side side)
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
	static public Map<String, Map<String, double[]>> gatherBusesValues(
			Network n,
			String caseId0,
			String caseId1,
			boolean comparision)
	{
		Map<String, Map<String, double[]>> allBusesValues = new HashMap<>();
		n.getBusBreakerView().getBuses().forEach(b -> {
			Map<String, double[]> bvalues = new HashMap<>();
			double[] Vs = new double[2];
			double[] As = new double[2];
			double[] Ps = new double[2];
			double[] Qs = new double[2];
			try
			{
				n.getStateManager().setWorkingState(caseId0);
				Vs[0] = b.getV() / b.getVoltageLevel().getNominalV();
				As[0] = b.getAngle();
				Ps[0] = zeroIfNaN(b.getP());
				Qs[0] = zeroIfNaN(b.getQ());
			}
			catch (Exception e)
			{
				if (comparision)
					LOG.error(e.getMessage());
			}
			try
			{
				n.getStateManager().setWorkingState(caseId1);
				Vs[1] = b.getV() / b.getVoltageLevel().getNominalV();
				As[1] = b.getAngle();
				Ps[1] = zeroIfNaN(b.getP());
				Qs[1] = zeroIfNaN(b.getQ());
			}
			catch (Exception e)
			{
				if (comparision)
					LOG.error(e.getMessage());
			}
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

	public static double calcDifference(double v0, double v1)
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

	public static double calcRelativeErrorOrAbsoluteIfSmallValues(double v0, double v1)
	{
		double absoluteError = Math.abs(v0 - v1);
		if (isAlmostZero(v0) || isAlmostZero(v1)) return absoluteError;
		double err = absoluteError / Math.abs(v0);
		return err;
	}

	static private boolean isAlmostZero(double value)
	{
		return Math.abs(value) < 1e-4f;
	}

	static private double zeroIfNaN(double value)
	{
		return Double.isNaN(value) ? 0.0 : value;
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
	private String					workingDirectory;
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
