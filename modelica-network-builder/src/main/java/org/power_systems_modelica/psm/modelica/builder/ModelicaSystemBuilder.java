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
		createModelicaDocument(getNetwork().getName());
		registerResolver("DYNN", new DynamicNetworkReferenceResolver(getNetwork(), this));

		ModelicaSystemModel sys = getModelicaDocument().getSystemModel();
		sys.addDeclarations(getDdr().getSystemDeclarations());
		addDynamicModels();
		// Add connections between models only after all models have been created
		addInterconnections();
		// And system equations also after all models have been created
		sys.addEquations(getDdr().getSystemEquationsInContext(sys));

		return getModelicaDocument();
	}

	private void addDynamicModels()
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

			addDynamicModel(db);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public <I extends Connectable<I>> void visitEquipment(Connectable<I> e)
				{
					ModelicaModel de = getDdr().getModelicaModel(e);
					if (de == null) return;

					if (!visited.contains(e))
					{
						addDynamicModel(de);
						visited.add(e);
					}
				}
			};
			if (isOnlyMainConnectedComponent()) b.visitConnectedEquipments(visitor);
			else b.visitConnectedOrConnectableEquipments(visitor);
		}
	}

	private final ModelicaEngine modelicaEngine;
}
