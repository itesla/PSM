package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
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
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin("");

		// The model for the whole system
		ModelicaSystemModel m = new ModelicaSystemModel(getNetwork().getName());

		m.addDeclarations(getDdr().getSystemDeclarations());
		addDynamicModels(m);
		m.addEquations(getDdr().getSystemEquations(m));

		// TODO post-process resulting Modelica objects
		// TODO omegaRef should be computed as a weighted sum of omega variables of all machines
		// omegaRef = SUM(g.omega * g.SN * g.HIn) for all g in machines
		// m.addParameters(Arrays.asList(new ModelicaParameter(ModelicaType.Real, "omegaRef", "0.0")));

		mo.setSystemModel(m);
		return mo;
	}

	private void addDynamicModels(ModelicaSystemModel m)
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

			addDynamicModel(m, db, null);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public void visitEquipment(Connectable<?> e)
				{
					ModelicaModel de = getDdr().getModelicaModel(e);
					if (de == null) return;

					if (!visited.contains(e))
					{
						addDynamicModel(m, de, db);
						visited.add(e);
					}
					addConnections(m, de, db);
				}
			};
			if (isOnlyMainConnectedComponent()) b.visitConnectedEquipments(visitor);
			else b.visitConnectedOrConnectableEquipments(visitor);
		}
	}

	private void addDynamicModel(ModelicaSystemModel system, ModelicaModel m, ModelicaModel mbus)
	{
		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel

		system.addDeclarations(resolveReferences(m.getDeclarations(), m));
		system.addEquations(m.getEquations());
	}

	private void addConnections(ModelicaSystemModel system, ModelicaModel m, ModelicaModel mbus)
	{
		// Add connections with the rest of the system
		// TODO if we have problems resolving references (some models not yet created)
		// We could enqueue all pending connectors and review them at the end of the process
		Stream.of(m.getConnectors()).forEach(c -> c.getTarget()
				.map(t -> resolveTarget(t, m, mbus))
				.ifPresent(
						ct -> system.addEquation(new ModelicaConnect(ct.getRef(), c.getRef()))));
	}

	private ModelicaConnector resolveTarget(String target, ModelicaModel m, ModelicaModel mbus)
	{
		String atarget[] = target.split(":");
		String resolver = atarget[0];
		String item = atarget[1];

		if (resolver.equals("IIDM"))
		{
			String pin = atarget[2];
			if (item.equals("bus")
					|| item.equals("bus1") && ModelicaTricks.isBusAtSide(m, mbus, 1)
					|| item.equals("bus2") && ModelicaTricks.isBusAtSide(m, mbus, 2))
				return findConnector(pin, mbus.getConnectors());
		}
		return null;
	}

	private ModelicaConnector findConnector(String pin, ModelicaConnector[] connectors)
	{
		ModelicaConnector cf = Stream.of(connectors)
				.filter(c -> c.getPin().equals(pin))
				.findFirst()
				.get();
		return cf;
	}

	private final ModelicaEngine modelicaEngine;
}
