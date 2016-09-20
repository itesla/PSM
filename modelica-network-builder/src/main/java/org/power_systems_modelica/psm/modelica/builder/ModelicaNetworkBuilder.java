package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.power_systems_modelica.psm.modelica.engine.ModelicaEngine;
import org.power_systems_modelica.psm.modelica.engine.ModelicaSimulationResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.EquipmentTopologyVisitor;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaNetworkBuilder extends ModelicaBuilder
{
	public ModelicaNetworkBuilder(DynamicDataRepository ddr, Network n, ModelicaEngine me)
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
		final ConnectorResources connectors = new ConnectorResources();
		for (Bus b : network.getBusBreakerView().getBuses())
		{
			if (isOnlyMainConnectedComponent() && !b.isInMainConnectedComponent()) continue;
			ModelicaModel db = getDdr().getModelicaModel(b);
			if (db == null) continue;

			addDynamicModel(m, db);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public void visitEquipment(Connectable<?> e)
				{
					ModelicaModel de = getDdr().getModelicaModel(e);
					if (de == null) return;

					if (!visited.contains(e))
					{
						addDynamicModel(m, de);
						visited.add(e);
					}
					try
					{
						addConnection(m, db, de, connectors);
					}
					catch (ConnectorException x)
					{
						LOG.warn(
								"could not add connection between bus {} and element {}, reason '{}'",
								b.getId(),
								e.getId(),
								x.getMessage());
					}
				}
			};
			if (isOnlyMainConnectedComponent()) b.visitConnectedEquipments(visitor);
			else b.visitConnectedOrConnectableEquipments(visitor);
		}
	}

	private void addDynamicModel(ModelicaSystemModel system, ModelicaModel m)
	{
		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel

		system.addDeclarations(resolveReferences(m.getDeclarations(), m));
		system.addEquations(m.getEquations());
	}

	private void addConnection(
			ModelicaSystemModel m,
			ModelicaModel m1,
			ModelicaModel m2,
			ConnectorResources connectors) throws ConnectorException
	{
		ModelicaConnector conn1 = acquireConnector(m1, m2, connectors);
		ModelicaConnector conn2 = acquireConnector(m2, m1, connectors);
		m.addEquation(new ModelicaConnect(conn1.getRef(), conn2.getRef()));
	}

	static class ConnectorResources
	{
		public boolean isUsed(ModelicaConnector conn)
		{
			return usedConnectors.contains(conn.getRef());
		}

		public void use(ModelicaConnector conn)
		{
			usedConnectors.add(conn.getRef());
		}

		Set<String> usedConnectors = new HashSet<>();
	}

	@SuppressWarnings("serial")
	static class ConnectorException extends Exception
	{
		ConnectorException(String message)
		{
			super(message);
		}
	}

	private ModelicaConnector acquireConnector(
			ModelicaModel m,
			ModelicaModel other,
			ConnectorResources connectors) throws ConnectorException
	{
		ModelicaConnector[] conns = m.getConnectors();
		if (conns.length == 0) throw new ConnectorException("no connectors found");
		ModelicaConnector conn = conns[0];
		if (conns.length > 1) conn = preferredConnector(m, other, conns);
		conn = acquireConnector(conn, connectors);
		if (conn != null) return conn;
		if (conns.length == 1) throw new ConnectorException("unique connector already used");
		conn = otherConnector(conn, conns);
		if (conn == null) throw new ConnectorException("second connector not found");
		conn = acquireConnector(conn, connectors);
		if (conn == null) throw new ConnectorException("second connector already used");
		return conn;
	}

	private ModelicaConnector acquireConnector(
			ModelicaConnector conn,
			ConnectorResources connectors)
	{
		if (conn.isReusable()) return conn;
		if (connectors.isUsed(conn)) return null;
		connectors.use(conn);
		return conn;
	}

	private ModelicaConnector otherConnector(ModelicaConnector conn, ModelicaConnector[] conns)
	{
		return (conn == conns[0] ? conns[1] : conns[0]);
	}

	private ModelicaConnector preferredConnector(
			ModelicaModel m,
			ModelicaModel other,
			ModelicaConnector[] conns)
	{
		return ModelicaTricks.preferredConnector(m, other, conns);
	}

	private final ModelicaEngine	modelicaEngine;
	private static final Logger		LOG	= LoggerFactory.getLogger(ModelicaNetworkBuilder.class);
}
