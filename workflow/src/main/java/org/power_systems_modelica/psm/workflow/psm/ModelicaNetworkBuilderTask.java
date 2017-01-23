package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaSystemBuilder;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngineMainFactory;
import org.power_systems_modelica.psm.workflow.WorkflowTask;

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
		onlyMainConnectedComponent = config.getBoolean("onlyMainConnectedComponent");
	}

	@Override
	public void run()
	{
		running();
		try
		{
			DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create(
					ddrType,
					ddrLocation);
			ddr.connect();
			ModelicaEngine me = ModelicaEngineMainFactory.create(modelicaEngine);
			me.configure(config);
			Network n = (Network) workflow.getResults("network");
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
			ModelicaDocument mo = builder.build();

			List<ElementModel> elementsList = new ArrayList<ElementModel>();
			Map<String, ModelicaModel> dynamicGroupByStaticId = ModelicaUtil
					.groupByNormalizedStaticId(mo);
			dynamicGroupByStaticId.entrySet().forEach(
					entry -> entry.getValue().getDeclarations().forEach(
							e -> elementsList.add(new ElementModel(entry.getValue().getStaticId(),
									e.getType()))));

			publish(SCOPE_GLOBAL, "mo", mo);
			publish(SCOPE_GLOBAL, "models", elementsList);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	public class ElementModel
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
}
