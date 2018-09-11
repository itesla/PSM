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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.Logs;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import com.powsybl.iidm.network.Identifiable;
import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
	public void configure(Configuration config)
	{
		this.config = config;
		ddrType = config.getParameter("ddrType");
		ddrLocation = config.getParameter("ddrLocation");
		modelicaEngine = config.getParameter("modelicaEngine");
		onlyMainConnectedComponent = config.getBoolean(
				"onlyMainConnectedComponent",
				true);
		checkOnly = config.getBoolean("checkOnly", false);
	}

	@Override
	public void run()
	{
		running();
		try
		{
			DynamicDataRepository ddr = prepareDdr();
			me = prepareModelicaEngine();
			Network n = (Network) workflow.getResults("network");
			ModelicaSystemBuilder builder = prepareModelicaBuilder(ddr, n, me);
			ModelicaDocument mo = null;
			Collection<UnresolvedRef> unresolved = new ArrayList<>();
			if (checkOnly) mo = builder.check(unresolved);
			else mo = builder.build(unresolved);
			publishResults(builder, mo, unresolved, me);

			if (unresolved.isEmpty()) succeded();
			else failed();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private DynamicDataRepository prepareDdr() throws ConnectionException
	{
		DynamicDataRepository ddr;
		ddr = DynamicDataRepositoryMainFactory.create(ddrType, ddrLocation);
		ddr.connect();
		return ddr;
	}

	private ModelicaEngine prepareModelicaEngine()
	{
		// If we are only checking we do not need a modelica engine
		if (checkOnly) return null;

		ModelicaEngine me;
		me = ModelicaEngineMainFactory.create(modelicaEngine);
		me.getModelicaEngineProgress().addObserver(new Observer()
		{
			@Override
			public void update(Observable o, Object arg)
			{
				progress("    " + arg);
			}
		});
		config.setParameter("stopTime", "0.01");
		config.setParameter("numOfIntervalsPerSecond", "1000");
		me.configure(config);
		return me;
	}

	private ModelicaSystemBuilder prepareModelicaBuilder(
			DynamicDataRepository ddr,
			Network n,
			ModelicaEngine me)
	{
		ModelicaSystemBuilder builder = new ModelicaSystemBuilder(ddr, n, me);
		builder.getProgress().addObserver(new Observer()
		{
			@Override
			public void update(Observable o, Object arg)
			{
				progress((String) arg);
			}
		});
		builder.setOnlyMainConnectedComponent(onlyMainConnectedComponent);
		return builder;
	}

	private void publishResults(
			ModelicaSystemBuilder builder,
			ModelicaDocument mo,
			Collection<UnresolvedRef> unresolved,
			ModelicaEngine me)
	{
		// TODO We could have logs from conversion process and from modelica engine (full model initialization),
		// they should be combined together instead of selecting only one of them
		Logs logs = null;
		if (!unresolved.isEmpty())
		{
			Logs logsu = new Logs("Modelica network builder");
			logsu.newActivity("Unresolved references");
			unresolved.forEach(u -> logsu.result(u.toString()));
			logs = logsu;
			publish(SCOPE_GLOBAL, "unresolvedReferences", logs);
		}
		else if (checkOnly)
		{
			Map<String, List<Model>> mappingAlternatives = builder.getMappingAlternatives();
			if (!mappingAlternatives.isEmpty())
			{
				Logs logsu = null;
				Set<String> keys = mappingAlternatives.keySet();
				for (String k: keys)
				{
					List<Model> l = mappingAlternatives.get(k);
					if (l.size() > 1)
					{
						if (logsu == null)
						{
							logsu = new Logs("Modelica network builder");
							logsu.newActivity("Mapping Alternatives");
						}
						logsu.detail("The static ID " + k + " is associated to several Dynamic Models: " + l.stream().map(m -> m.toString())
								.collect(Collectors.joining(",")) + "\n" + "The dynamic model " + l.get(0).toString() + " has been selected for the conversion.");
					}
				}
				logs = logsu;
				publish(SCOPE_GLOBAL, "mappingAlternatives", logs);
			}
		}
		else if (me != null)
		{
			logs = me.getLogs();
			publish(SCOPE_GLOBAL, "logs", logs);
		}

		publish(SCOPE_GLOBAL, "mo", mo);

		List<ElementModel> elementsModel = new ArrayList<ElementModel>();
		Map<String, ModelicaModel> dynamicModelsByStaticId = ModelicaUtil
				.groupByNormalizedStaticId(mo);
		dynamicModelsByStaticId.entrySet().forEach(
				e -> e.getValue().getDeclarations().forEach(
						d -> elementsModel
								.add(new ElementModel(
										e.getValue().getStaticId(),
										d.getOrigin(),
										d.getType()))));

		publish(SCOPE_GLOBAL, "models", elementsModel);

		Collection<Identifiable<?>> unmapped = builder.getElementsMissingDynamicModel();
		publish(SCOPE_GLOBAL, "elementsMissingDynamicModel", unmapped);
	}

	static public class ElementModel
	{
		private String	staticId;
		private String	dynamicType;
		private String	origin;

		public ElementModel(String staticId, String origin, String dynamicType)
		{
			this.staticId = staticId;
			this.origin = origin;
			this.dynamicType = dynamicType;
		}

		public String getStaticId()
		{
			return staticId;
		}

		public void setStaticId(String staticId)
		{
			this.staticId = staticId;
		}

		public String getOrigin()
		{
			return origin;
		}

		public String getDynamicType()
		{
			return dynamicType;
		}

		public void setDynamicType(String dynamicType)
		{
			this.dynamicType = dynamicType;
		}
	}

	@Override
	public void cancel()
	{
		if (me != null)
			try
			{
			me.close();
			}
			catch (Exception x)
			{
			failed(x);
			}
	}

	protected ModelicaEngine	me	= null;

	private Configuration		config;
	private String				ddrType;
	private String				ddrLocation;
	private String				modelicaEngine;
	private boolean				onlyMainConnectedComponent;
	private boolean				checkOnly;
}
