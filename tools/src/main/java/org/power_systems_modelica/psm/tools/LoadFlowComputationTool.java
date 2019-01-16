package org.power_systems_modelica.psm.tools;

/*
 * #%L
 * Command line tools
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.network.import_.StaticNetworkImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;
import com.powsybl.tools.Command;
import com.powsybl.tools.Tool;
import com.powsybl.tools.ToolRunningContext;
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
import com.powsybl.iidm.xml.NetworkXml;
import com.powsybl.loadflow.LoadFlow;
import com.powsybl.loadflow.LoadFlowFactory;
import com.powsybl.loadflow.LoadFlowParameters;
import com.powsybl.loadflow.LoadFlowResult;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
@AutoService(Tool.class)
public class LoadFlowComputationTool implements Tool
{
	private static final Command COMMAND = new Command()
	{
		@Override
		public String getName()
		{
			return "loadflow";
		}

		@Override
		public String getTheme()
		{
			return "Loadflow";
		}

		@Override
		public String getDescription()
		{
			return "Loadflow computation from IIDM files.";
		}

		@Override
		public Options getOptions()
		{
			Options options = new Options();
			options.addOption(Option.builder()
					.longOpt("i-iidm")
					.desc("Input: IIDM file")
					.hasArg()
					.argName("IIDM_INPUT_FILE")
					.required()
					.build());
			options.addOption(Option.builder()
					.longOpt("o")
					.desc("Output: IIDM file")
					.hasArg()
					.argName("IIDM_OUTPUT_FILE")
					.required()
					.build());
			// only available for Linux
			if (isHades2Available())
			{
				options.addOption(Option.builder()
						.longOpt("engine")
						.desc("Loadflow engine")
						.hasArg()
						.argName("ENGINE")
						.required()
						.build());
			}
			return options;
		}

		@Override
		public String getUsageFooter()
		{
			if (isHades2Available()) return "Where ENGINE is one of " + "HELM-Flow or Hades2";
			return "Where ENGINE is HELM-Flow";
		}
	};

	@Override
	public Command getCommand()
	{
		return COMMAND;
	}

	@Override
	public void run(CommandLine cmd, ToolRunningContext context) throws Exception
	{
		String iidmFile = cmd.getOptionValue("i-iidm");
		if (iidmFile == null)
		{
			System.err.println("Missing CIM input file");
			return;
		}
		String outPutFile = cmd.getOptionValue("o");
		if (outPutFile == null)
		{
			System.err.println("Missing output file path");
			return;
		}

		String engine = cmd.getOptionValue("engine");
		if (!isHades2Available())
		{
			engine = Optional.ofNullable(cmd.getOptionValue("engine"))
					.orElse("HELM-Flow");
		}

		if (isHades2Available() && engine == null)
		{
			System.err.println("Missing Load Flow engine");
			return;
		}
		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// Date date = new Date();

		// LF engine
		String loadFlowFactoryClassName;
		switch (engine)
		{
		case "Hades2":
			loadFlowFactoryClassName = "com.rte_france.itesla.hades2.Hades2Factory";
			break;
		case "HELM-Flow":
			loadFlowFactoryClassName = "com.elequant.helmflow.ipst.HelmFlowFactory";
			break;
		default:
			loadFlowFactoryClassName = "com.elequant.helmflow.ipst.HelmFlowFactory";
			break;
		}

		// Working directory
		String workingDirectory = PathUtils.DATA_TMP.resolve("LF_computation_tool").toString();

		// Option sourceStateId and targetStateId not available. Default:INITIAL_STATE_ID
		String sourceStateId = Optional.ofNullable(cmd.getOptionValue("sourceStateId"))
				.orElse(StateManagerConstants.INITIAL_STATE_ID);
		String targetStateId = Optional.ofNullable(cmd.getOptionValue("targetStateId"))
				.orElse(sourceStateId);
		if (cmd.hasOption("Compare"))
		{
			// import static networks
			Network networkHELM = NetworkXml.read(Paths.get(iidmFile));
			Network networkHADES = NetworkXml.read(Paths.get(iidmFile));
			if (!targetStateId.equals(sourceStateId))
			{
				networkHELM.getStateManager().cloneState(sourceStateId, targetStateId);
				networkHELM.getStateManager().setWorkingState(targetStateId);
				networkHADES.getStateManager().cloneState(sourceStateId, targetStateId);
				networkHADES.getStateManager().setWorkingState(targetStateId);
			}
			// set configuration Networks
			networkHELM.getStateManager().allowStateMultiThreadAccess(true);
			networkHELM.getStateManager().setWorkingState(sourceStateId);
			networkHADES.getStateManager().allowStateMultiThreadAccess(true);
			networkHADES.getStateManager().setWorkingState(sourceStateId);

			LoadFlowCalculation(workingDirectory, HELMFLOW_FACTORY, networkHELM);
			NetworkXml.write(networkHELM, Paths.get(workingDirectory)
					.resolve(networkHELM.getId() + "_" + "loadflowHelmflow" + ".xiidm"));
			LoadFlowCalculation(workingDirectory, HADES2_FACTORY, networkHADES);
			NetworkXml.write(networkHADES, Paths.get(workingDirectory)
					.resolve(networkHADES.getId() + "_" + "loadflowHades2" + ".xiidm"));
		}
		else
		{
			// import static network
			Network network = NetworkXml.read(Paths.get(iidmFile));
			if (!targetStateId.equals(sourceStateId))
			{
				network.getStateManager().cloneState(sourceStateId, targetStateId);
				network.getStateManager().setWorkingState(targetStateId);
			}

			// set configuration Network
			network.getStateManager().allowStateMultiThreadAccess(true);
			network.getStateManager().setWorkingState(sourceStateId);
			LoadFlowCalculation(workingDirectory, loadFlowFactoryClassName, network);
			NetworkXml.write(network, Paths.get(outPutFile));

			/*
			 * System.out.println(BusBalances(network)[0] + "  " +BusBalances(network)[1] ); Map<String, Map<String, Float>> allBusesValues = gatherBusesValues(network, sourceStateId,sourceStateId); StringBuilder sb = new StringBuilder(); BufferedWriter br
			 * = new BufferedWriter(new FileWriter(Paths.get(workingDirectory).resolve("LoadFlowOutPut").toString())); sb.append(dateFormat.format(date)+"\n"); sb.append("Network  "+"\t:\t" + network.getId() + "\n"); sb.append("LF Solver"+"\t:\t" +
			 * LFSolver + "\n\n"); sb.append("Results"+"\t:\n\n"); sb.append("\t  Bus Name   " + "\tVoltage(pu)" + "\tAngle(Deg)" +"\tP(MW)" +"\tQ(MVA)\n\n"); String V; String A; String P; String Q; for(Map.Entry<String, Map<String, Float>> entry :
			 * allBusesValues.entrySet()) { String key = entry.getKey(); Map<String, Float> value = entry.getValue(); V = String.valueOf(value.get("V")); A = String.valueOf(value.get("A")); P = String.valueOf(value.get("P")); sb.append("\t" + key + "\t" +
			 * V + "\t"+A +"\t"+ P +"\t"+ Q + "\n"); // do what you have to do here // In your case, an other loop. }
			 * 
			 * br.write(sb.toString()); br.close();
			 */
		}

	}

	static private Map<String, Map<String, Double>> gatherBusesValues(
			Network n,
			String caseId0,
			String caseId1)
	{
		Map<String, Map<String, Double>> allBusesValues = new HashMap<>();
		n.getBusBreakerView().getBuses().forEach(b -> {
			Map<String, Double> bvalues = new HashMap<>();
			double Vs = (double) 0.0;
			double As = (double) 0.0;
			double Ps = (double) 0.0;
			double Qs = (double) 0.0;
			try
			{
				n.getStateManager().setWorkingState(caseId0);
				Vs = b.getV() / b.getVoltageLevel().getNominalV();
				As = b.getAngle();
				Ps = zeroIfNaN(b.getP());
				Qs = zeroIfNaN(b.getQ());
			}
			catch (Exception e)
			{

			}
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);
			allBusesValues.put(b.getId(), bvalues);
		});
		return allBusesValues;

	}

	static private void LoadFlowCalculation(String workingDirectory,
			String loadFlowFactoryClassName, Network network)
	{

		LoadFlowParameters loadFlowParams = new LoadFlowParameters();
		LoadFlowFactory loadFlowFactory;
		try
		{
			Class<? extends LoadFlowFactory> loadFlowFactoryClass = Class
					.forName(loadFlowFactoryClassName)
					.asSubclass(LoadFlowFactory.class);
			loadFlowFactory = loadFlowFactoryClass.newInstance();
			ComputationManager computationManager = new LocalComputationManager(
					Paths.get(workingDirectory));
			int priority = 1;
			LoadFlow lf = loadFlowFactory.create(network, computationManager, priority);
			LoadFlowResult r = lf.run(network.getStateManager().getWorkingStateId(), loadFlowParams).join();
			// After run the LF export result data even if the result is not ok (in this case the exported iidm will have the initial values)
			if (r.isOk())
			{
	             LOG.info("Load Flow has been calculated successfully");

			}
			else
			{
				throw new Exception("Loadflow is not Ok");
			}
			if (r.getLogs().contains("Loadflow kernel invalid license"))
				throw new Exception("Loadflow kernel invalid license");

		}
		catch (Exception x)
		{

		}
	}

	public static double calcRelativeErrorOrAbsoluteIfSmallValues(float v0, float v1)
	{
		float absoluteError = Math.abs(v0 - v1);
		if (isAlmostZero(v0) || isAlmostZero(v1)) return absoluteError;
		float err = absoluteError / Math.abs(v0);
		return err;
	}

	static public float[] BusBalances(Network n)
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
		return deltaMax;
	}

	static private boolean isAlmostZero(double value)
	{
		return Math.abs(value) < 1e-4f;
	}

	static private double zeroIfNaN(double value)
	{
		return Double.isNaN(value) ? 0.0f : value;
	}

	private static boolean isHades2Available()
	{
		// Hades is only available for Linux
		return System.getProperty("os.name").startsWith("Linux");
	}

	private static final String	HELMFLOW_FACTORY	= "com.elequant.helmflow.ipst.HelmFlowFactory";
	private static final String	HADES2_FACTORY		= "com.rte_france.itesla.hades2.Hades2Factory";
	private static final Logger	LOG					= LoggerFactory
			.getLogger(LoadFlowComputationTool.class);

}
