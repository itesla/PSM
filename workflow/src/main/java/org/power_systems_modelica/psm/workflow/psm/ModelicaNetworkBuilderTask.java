package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

import eu.itesla_project.iidm.network.Identifiable;
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
	public void configure(Configuration config)
	{
		this.config = config;
		ddrType = config.getParameter("ddrType");
		ddrLocation = config.getParameter("ddrLocation");
		modelicaEngine = config.getParameter("modelicaEngine");
		onlyMainConnectedComponent = config.getBoolean(
				"onlyMainConnectedComponent",
				true);
		checkElementsMissingDynamicModel = config.getBoolean(
				"checkElementsMissingDynamicModel",
				false);
	}

	@Override
	public void run()
	{
		running();
		try
		{
			DynamicDataRepository ddr = prepareDdr();
			ModelicaEngine me = prepareModelicaEngine();
			Network n = (Network) workflow.getResults("network");
			ModelicaSystemBuilder builder = prepareModelicaBuilder(ddr, n, me);
			if (checkElementsMissingDynamicModel)
			{
				Collection<Identifiable<?>> elems = builder.checkElementsMissingDynamicModel();
				publish(SCOPE_GLOBAL, "elementsMissingDynamicModel", elems);
			}
			else
			{
				ModelicaDocument mo = builder.build();
				publishResults(mo);
			}
			succeded();
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
		if (checkElementsMissingDynamicModel) return null;

		ModelicaEngine me;
		me = ModelicaEngineMainFactory.create(modelicaEngine);
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

	private void publishResults(ModelicaDocument mo)
	{
		List<ElementModel> elementsModel = new ArrayList<ElementModel>();
		Map<String, ModelicaModel> dynamicModelsByStaticId = ModelicaUtil
				.groupByNormalizedStaticId(mo);
		dynamicModelsByStaticId.entrySet().forEach(
				e -> e.getValue().getDeclarations().forEach(
						d -> elementsModel
								.add(new ElementModel(
										e.getValue().getStaticId(),
										d.getType()))));
		publish(SCOPE_GLOBAL, "mo", mo);
		publish(SCOPE_GLOBAL, "models", elementsModel);
	}

	static public class ElementModel
	{
		private String	staticId;
		private String	dynamicId;

		public ElementModel(String staticId, String dynamicId)
		{
			this.staticId = staticId;
			this.dynamicId = dynamicId;
		}

		public String getStaticId()
		{
			return staticId;
		}

		public void setStaticId(String staticId)
		{
			this.staticId = staticId;
		}

		public String getDynamicId()
		{
			return dynamicId;
		}

		public void setDynamicId(String dynamicId)
		{
			this.dynamicId = dynamicId;
		}
	}

	// private List<ElementModel> staticIdDynamicTypeList;
	private Configuration	config;
	private String			ddrType;
	private String			ddrLocation;
	private String			modelicaEngine;
	private boolean			onlyMainConnectedComponent;
	private boolean			checkElementsMissingDynamicModel;
}
