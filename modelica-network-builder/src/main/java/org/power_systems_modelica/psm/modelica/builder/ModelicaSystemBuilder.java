package org.power_systems_modelica.psm.modelica.builder;

/*
 * #%L
 * Modelica network builder
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationFinalResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.iidm.network.Bus;
import com.powsybl.iidm.network.Connectable;
import com.powsybl.iidm.network.EquipmentTopologyVisitor;
import com.powsybl.iidm.network.Identifiable;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.network.Switch;
import com.powsybl.iidm.network.VoltageLevel;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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

	public ModelicaDocument build(Collection<UnresolvedRef> unresolved) throws Exception
	{
		performFullModelInitialization();
		return buildModelicaSystem(unresolved, false);
	}

	public ModelicaDocument check(Collection<UnresolvedRef> unresolved) throws Exception
	{
		// Try to build the dynamic system but without performing full model initialization,
		// so we will consider valid all references to initialization data
		// We will see if all the other references are valid and can be solved,
		// and if we have dynamic model mapping for every static element
		registerResolver("INIT", new ReferenceResolver()
		{
			@Override
			public Object resolveReference(
					ModelicaArgumentReference a,
					ModelicaModel m,
					BaseModelicaDeclaration d)
					throws ModelicaArgumentReferenceException
			{
				return 1969.0814f;
			}
		});
		return buildModelicaSystem(unresolved, true);
	}

	public Collection<Identifiable<?>> getElementsMissingDynamicModel()
	{
		return elementsMissingDynamicModel;
	}

	public Observable getProgress()
	{
		return progress;
	}
	
	public Map<String, List<Model>> getMappingAlternatives()
	{
		return mappingAlternatives;
	}

	private void performFullModelInitialization() throws Exception
	{
		FullModelInitializationBuilder i = new FullModelInitializationBuilder(
				getDdr(),
				getNetwork());
		Collection<ModelicaDocument> mos = i.buildModelicaDocuments();

		progress.report("Full Model Initialization");
		if (mos != null)
		{
			modelicaEngine.simulate(mos);
		}

		ModelicaSimulationFinalResults mor = modelicaEngine.getSimulationResults();
		modelicaEngine.close();
		InitializationResults results = new InitializationResults(mor);
		InitializationReferenceResolver ir = new InitializationReferenceResolver(results);
		registerResolver("INIT", ir);
	}

	private ModelicaDocument buildModelicaSystem(Collection<UnresolvedRef> unresolved,
			boolean mapAlternatives)
	{
		progress.report("Building Modelica System Document");
		createModelicaDocument(getNetwork().getName());
		registerResolver("DYNN", new DynamicNetworkReferenceResolver(getNetwork(), this));
		ModelicaSystemModel sys = getModelicaDocument().getSystemModel();
		Optional<ModelicaModel> ds = getDdr().getSystemModel(Stage.SIMULATION);
		if (ds.isPresent()) addDynamicModel(ds.get());
		progress.report("    Adding dynamic models");
		addDynamicModels(mapAlternatives);
		setAllDynamicModelsAdded(true);
		progress.report("    Resolving references");
		resolvePendingReferences();
		// Add connections between models only after all models have been created
		progress.report("    Adding interconnections between dynamic models");
		addInterconnections(unresolved);

		// TODO This is temporary while we allow omegaRef equations to be specified directly in DYD files
		List<ModelicaEquation> otherSystemEquations;
		otherSystemEquations = getDdr().getSystemOtherEquationsInContext(sys, Stage.SIMULATION);
		if (otherSystemEquations != null) sys.addEquations(otherSystemEquations);

		return getModelicaDocument();
	}

	private void addDynamicModels(boolean mapAlternatives)
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
			if (db == null)
			{
				elementsMissingDynamicModel.add(b);
				LOG.warn("No Dynamic model found for element " + b);
				continue;
			}
			if (mapAlternatives)
			{
				List<Model> mdefs = getDdr().getModels(b, Stage.SIMULATION);
				mappingAlternatives.put(b.getId(), mdefs);
			}

			addDynamicModel(db);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public <I extends Connectable<I>> void visitEquipment(Connectable<I> e)
				{
					ModelicaModel de = getDdr().getModelicaModel(e, Stage.SIMULATION);
					if (de == null)
					{
						elementsMissingDynamicModel.add(e);
						LOG.warn("No Dynamic model found for element " + e);
						return;
					}

					if (!visited.contains(e))
					{
						if (mapAlternatives)
						{
							List<Model> mdefs = getDdr().getModels(e, Stage.SIMULATION);
							mappingAlternatives.put(e.getId(), mdefs);
						}

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
					elementsMissingDynamicModel.add(sw);
					LOG.warn("No dynamic model found for switch " + sw);
					continue;
				}
				if (mapAlternatives)
				{
					List<Model> mdefs = getDdr().getModels(sw, Stage.SIMULATION);
					mappingAlternatives.put(sw.getId(), mdefs);
				}
				addDynamicModel(d);
			}
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

	private final ModelicaEngine		modelicaEngine;
	private final Progress				progress;
	private Collection<Identifiable<?>>	elementsMissingDynamicModel	= new ArrayList<>();
	private Map<String, List<Model>>	mappingAlternatives			= new HashMap<>();

	private static final Logger			LOG							= LoggerFactory
			.getLogger(ModelicaSystemBuilder.class);
}
