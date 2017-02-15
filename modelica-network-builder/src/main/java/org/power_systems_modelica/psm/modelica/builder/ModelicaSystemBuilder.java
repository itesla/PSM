package org.power_systems_modelica.psm.modelica.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
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
import eu.itesla_project.iidm.network.Switch;
import eu.itesla_project.iidm.network.VoltageLevel;

public class ModelicaSystemBuilder extends ModelicaNetworkBuilder
{
	static class Progress extends Observable
	{
		public void report(String message)
		{
			setChanged();
			notifyObservers(message);
		}
	}

	public ModelicaSystemBuilder(DynamicDataRepository ddr, Network n, ModelicaEngine me)
	{
		super(ddr, n);
		this.modelicaEngine = me;
		this.progress = new Progress();
	}

	public ModelicaDocument build() throws Exception
	{
		performFullModelInitialization();
		return buildModelicaSystem();
	}

	public Observable getProgress()
	{
		return progress;
	}

	private void performFullModelInitialization() throws Exception
	{
		FullModelInitializationBuilder i = new FullModelInitializationBuilder(
				getDdr(),
				getNetwork());
		Collection<ModelicaDocument> mos = i.buildModelicaDocuments();

		// FIXME Just temporarily, to avoid one more level of progress updates, we decide to iterate here
		// But it should be done down in the modelicaEngine, to have the opportunity to optimize loading library ...
		// Either that or we call explicitly "prepare" / "simulate"
		// modelicaEngine.simulate(mos);
		progress.report("Full Model Initialization");
		if (mos != null)
		{
			for (ModelicaDocument mo : mos) 
			{
				String modelName = mo.getSystemModel().getId();
				progress.report("    " + modelName);
				modelicaEngine.simulate(mo);
			};
		}

		ModelicaSimulationFinalResults mor = modelicaEngine.getSimulationResults();
		modelicaEngine.close();
		InitializationResults results = new InitializationResults(mor);
		InitializationReferenceResolver ir = new InitializationReferenceResolver(results);
		registerResolver("INIT", ir);
	}

	private ModelicaDocument buildModelicaSystem() throws Exception
	{
		progress.report("Building Modelica System Document");
		createModelicaDocument(getNetwork().getName());
		registerResolver("DYNN", new DynamicNetworkReferenceResolver(getNetwork(), this));

		ModelicaSystemModel sys = getModelicaDocument().getSystemModel();

		Optional<ModelicaModel> ds = getDdr().getSystemModel(Stage.SIMULATION);
		if (ds.isPresent()) addDynamicModel(ds.get());
		progress.report("    Adding dynamic models");
		addDynamicModels();
		setAllDynamicModelsAdded(true);
		progress.report("    Resolving references");
		resolvePendingReferences();
		// Add connections between models only after all models have been created
		progress.report("    Adding interconnections between dynamic models");
		addInterconnections();

		// TODO This is temporary while we allow omegaRef equations to be specified directly in DYD files
		List<ModelicaEquation> otherSystemEquations;
		otherSystemEquations = getDdr().getSystemOtherEquationsInContext(sys, Stage.SIMULATION);
		if (otherSystemEquations != null) sys.addEquations(otherSystemEquations);

		return getModelicaDocument();
	}

	private void addDynamicModels() throws Exception
	{
		Network network = getNetwork();

		if (network.getBusBreakerView() == null) return;

		// For every equipment in the main connected component of the Network,
		// obtain the list of model declarations and equations

		final Set<Identifiable<?>> visited = new HashSet<>(network.getIdentifiables().size());
		final List<Identifiable<?>> unmapped = new ArrayList<>();
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
						unmapped.add(e);
						LOG.warn("No Dynamic model found for element " + e);
						return;
					}

					if (!visited.contains(e))
					{
						addDynamicModel(de);
						visited.add(e);
					}
				}
			};
			b.visitConnectedEquipments(visitor);
		}

		// Because we have selected to export the bus breaker view, export all the switches
		for (VoltageLevel vl : network.getVoltageLevels())
		{
			for (Switch sw : vl.getBusBreakerView().getSwitches())
			{
				ModelicaModel d = getDdr().getModelicaModel(sw, Stage.SIMULATION);
				if (d == null)
				{
					unmapped.add(sw);
					LOG.warn("No dynamic model found for switch " + sw);
					continue;
				}
				addDynamicModel(d);
			}
		}
		
		if (!unmapped.isEmpty())
		{
			throw new Exception("No dynamic model found for elements: " + Arrays.toString(unmapped.toArray()));
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
	private final Progress			progress;

	private static final Logger		LOG	= LoggerFactory.getLogger(ModelicaSystemBuilder.class);
}
