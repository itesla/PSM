package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.EquipmentTopologyVisitor;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaSystemBuilder extends ModelicaNetworkBuilder
{
	public ModelicaSystemBuilder(DynamicDataRepository ddr, Network n, ModelicaEngine me)
	{
		super(ddr, n);
		this.modelicaEngine = me;
	}

	public ModelicaDocument build()
	{
		performFullModelInitialization();
		return buildModelicaSystem();
	}

	private void performFullModelInitialization()
	{
		FullModelInitializationBuilder i = new FullModelInitializationBuilder(
				getDdr(),
				getNetwork());
		Collection<ModelicaDocument> mos = i.buildModelicaDocuments();
		modelicaEngine.simulate(mos);
		ModelicaSimulationResults mor = modelicaEngine.getSimulationResults();
		InitializationResults results = new InitializationResults(mor);
		InitializationReferenceResolver ir = new InitializationReferenceResolver(results);
		registerResolver("INIT", ir);
	}

	private ModelicaDocument buildModelicaSystem()
	{
		Network n = getNetwork();

		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin("");
		ModelicaSystemModel sys = new ModelicaSystemModel(n.getName());
		mo.setSystemModel(sys);

		DynamicNetworkReferenceResolver dynnr = new DynamicNetworkReferenceResolver(n, mo);
		registerResolver("DYNN", dynnr);

		sys.addDeclarations(getDdr().getSystemDeclarations());
		addDynamicModels(mo, dynnr);
		// Add connections between models only after all models have been created
		addConnections(mo, dynnr);
		sys.addEquations(getDdr().getSystemEquations(sys));

		// TODO post-process resulting Modelica objects
		// TODO omegaRef should be computed as a weighted sum of omega variables of all machines
		// omegaRef = SUM(g.omega * g.SN * g.HIn) for all g in machines
		// m.addParameters(Arrays.asList(new ModelicaParameter(ModelicaType.Real, "omegaRef", "0.0")));

		return mo;
	}

	private void addDynamicModels(ModelicaDocument mo, DynamicNetworkReferenceResolver dynnr)
	{
		Network network = getNetwork();

		if (network.getBusBreakerView() == null) return;

		// For every equipment in the main connected component of the Network,
		// obtain the list of model declarations and equations

		final Set<Identifiable<?>> visited = new HashSet<>(network.getIdentifiables().size());
		for (Bus b : network.getBusBreakerView().getBuses())
		{
			if (isOnlyMainConnectedComponent() && !b.isInMainConnectedComponent()) continue;
			ModelicaModel db = getDdr().getModelicaModel(b);
			if (db == null) continue;

			addDynamicModel(db, mo);
			dynnr.addModel(db);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public void visitEquipment(Connectable<?> e)
				{
					ModelicaModel de = getDdr().getModelicaModel(e);
					if (de == null) return;

					if (!visited.contains(e))
					{
						addDynamicModel(de, mo);
						dynnr.addModel(de);
						visited.add(e);
					}
				}
			};
			if (isOnlyMainConnectedComponent()) b.visitConnectedEquipments(visitor);
			else b.visitConnectedOrConnectableEquipments(visitor);
		}
	}

	private void addConnections(ModelicaDocument mo, DynamicNetworkReferenceResolver dynnr)
	{
		dynnr.getModels().stream().forEach(m -> addConnections(m, mo));
	}

	private final ModelicaEngine modelicaEngine;
}
