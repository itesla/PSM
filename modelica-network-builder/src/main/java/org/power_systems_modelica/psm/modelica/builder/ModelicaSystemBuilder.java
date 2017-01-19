package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		ModelicaSimulationFinalResults mor = modelicaEngine.getSimulationResults();
		InitializationResults results = new InitializationResults(mor);
		InitializationReferenceResolver ir = new InitializationReferenceResolver(results);
		registerResolver("INIT", ir);
	}

	private ModelicaDocument buildModelicaSystem()
	{
		createModelicaDocument(getNetwork().getName());
		registerResolver("DYNN", new DynamicNetworkReferenceResolver(getNetwork(), this));

		ModelicaSystemModel sys = getModelicaDocument().getSystemModel();

		Optional<ModelicaModel> ds = getDdr().getSystemModel(Stage.SIMULATION);
		if (ds.isPresent()) addDynamicModel(ds.get());
		addDynamicModels();
		setAllDynamicModelsAdded(true);
		resolvePendingReferences();
		// Add connections between models only after all models have been created
		addInterconnections();

		// TODO This is temporary while we allow omegaRef equations to be specified directly in DYD files
		List<ModelicaEquation> otherSystemEquations;
		otherSystemEquations = getDdr().getSystemOtherEquationsInContext(sys, Stage.SIMULATION);
		if (otherSystemEquations != null) sys.addEquations(otherSystemEquations);

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
			ModelicaModel db = getDdr().getModelicaModel(b, Stage.SIMULATION);
			if (db == null) continue;

			addDynamicModel(db);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public <I extends Connectable<I>> void visitEquipment(Connectable<I> e)
				{
					ModelicaModel de = getDdr().getModelicaModel(e, Stage.SIMULATION);
					if (de == null)
					{
						LOG.warn("No Modelica Model found for element " + e);
						return;
					}

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

	private void resolvePendingReferences()
	{
		// FIXME Design problem found when resolving pending references

		// We have a problem adding parts of every model to the global unique "system" model
		// (all declarations from different models are put together in the system model)
		// When we have to review the declarations that have been put inside the system model
		// we do not have a right "parent" model to resolve the reference

		// I think that a right alternative would be:
		// Models are selected from mappings and transformed in the different phases but are only added to
		// the system model in the last step, when we have finally solved all references

		// The problem also manifests in the ModelicaDeclaration class,
		// where now we need to replace arguments in an otherwise immutable Declaration
		// This need comes solely from the fact that some references can only be resolved when
		// they have been added to the system model,
		// and we are lazy and do not want to store/rebuild the system model (even partially)
		// due to the changed declarations

		// So, now we do not have the original ModelicaModel corresponding to a given declaration
		// (that could maybe needed to execute a resolveReferences call ...)
		LOG.warn("We do not have the original ModelicaModel for resolving pending references");
		ModelicaModel m = null;

		ModelicaSystemModel sys = getModelicaDocument().getSystemModel();
		sys.getDeclarations().stream().forEach(d -> {
			if (d.getArguments() == null) return;
			if (!d.containsAnyReference()) return;
			List<ModelicaArgument> args1 = d.getArguments().stream()
					.map(a -> resolveReference(a, m, d))
					.collect(Collectors.toList());
			d.replaceArguments(args1);
			if (d.containsAnyReference()) LOG.warn("Not all pending references have been resolved");
		});
	}

	private final ModelicaEngine	modelicaEngine;

	private static final Logger		LOG	= LoggerFactory.getLogger(ModelicaSystemBuilder.class);
}
